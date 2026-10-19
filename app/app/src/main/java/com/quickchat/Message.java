package com.quickchat;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Represents a single QuickChat message (Part 2).
 */
public class Message {

    public static final int MAX_LENGTH = 250;
    public static final int SEND = 1;
    public static final int DISREGARD = 2;
    public static final int STORE = 3;

    /** Same international-number rule as Login (code + max ten digits). */
    private static final Pattern CELL_PATTERN = Pattern.compile("^\\+\\d{1,3}\\d{1,10}$");

    // Shared lists for the running program
    private static final List<Message> sentMessages = new ArrayList<>();
    private static final List<Message> storedMessages = new ArrayList<>();
    private static int totalSent = 0;

    private final String messageId;
    private final int messageNumber;
    private final String recipient;
    private final String text;
    private final String messageHash;

    /** Creates a message with an auto-generated ID. */
    public Message(int messageNumber, String recipient, String text) {
        this(generateMessageId(), messageNumber, recipient, text);
    }

    /** Creates a message with a supplied ID (used for testing). */
    public Message(String messageId, int messageNumber, String recipient, String text) {
        this.messageId = messageId;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.text = text;
        this.messageHash = createMessageHash();
    }

    /** Random ten-digit ID, zero padded. */
    public static String generateMessageId() {
        long number = (long) (new Random().nextDouble() * 10_000_000_000L);
        return String.format("%010d", number);
    }

    /** Message ID must be no more than ten characters. */
    public boolean checkMessageID() {
        return messageId != null && messageId.length() <= 10;
    }

    /** Checks the recipient cell number. */
    public String checkRecipientCell() {
        if (recipient != null && CELL_PATTERN.matcher(recipient).matches()) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an "
                + "international code. Please correct the number and try again.";
    }

    /** Checks the message length (max 250 characters). */
    public String checkMessageLength() {
        if (text.length() <= MAX_LENGTH) {
            return "Message ready to send.";
        }
        return "Message exceeds 250 characters by " + (text.length() - MAX_LENGTH)
                + "; please reduce the size.";
    }

    /**
     * Hash = first two ID digits : message number : FIRSTWORDLASTWORD (upper case).
     * e.g. 00:0:HITONIGHT
     */
    public String createMessageHash() {
        String[] words = text.trim().split("\\s+");
        String first = cleanWord(words[0]);
        String last = cleanWord(words[words.length - 1]);
        return (messageId.substring(0, 2) + ":" + messageNumber + ":" + first + last)
                .toUpperCase();
    }

    /** Removes punctuation from a word. */
    private static String cleanWord(String word) {
        return word.replaceAll("[^A-Za-z0-9]", "");
    }

    /**
     * Handles the user's choice for the message.
     * @param choice 1 = send, 2 = disregard, 3 = store
     */
    public String sentMessage(int choice) {
        switch (choice) {
            case SEND:
                sentMessages.add(this);
                totalSent++;
                return "Message successfully sent.";
            case DISREGARD:
                return "Press 0 to delete the message.";
            case STORE:
                storedMessages.add(this);
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid option.";
        }
    }

    /** All sent messages, formatted for display. */
    public static String printMessages() {
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages) {
            sb.append(m.getDetails()).append("\n");
        }
        return sb.toString();
    }

    /** Total number of messages sent. */
    public static int returnTotalMessages() {
        return totalSent;
    }

    /** Formatted details: ID, Hash, Recipient, Message. */
    public String getDetails() {
        return "Message ID: " + messageId + "\n"
                + "Message Hash: " + messageHash + "\n"
                + "Recipient: " + recipient + "\n"
                + "Message: " + text + "\n";
    }

    /**
     * Writes all stored messages to messages.json.
     * JSON is built by hand so no extra library is needed. If you use a
     * tutorial/library for this, attribute it here (rubric requirement).
     */
    public static void storeMessage() {
        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < storedMessages.size(); i++) {
            Message m = storedMessages.get(i);
            json.append("  {\n")
                .append("    \"messageID\": \"").append(escape(m.messageId)).append("\",\n")
                .append("    \"messageNumber\": ").append(m.messageNumber).append(",\n")
                .append("    \"messageHash\": \"").append(escape(m.messageHash)).append("\",\n")
                .append("    \"recipient\": \"").append(escape(m.recipient)).append("\",\n")
                .append("    \"message\": \"").append(escape(m.text)).append("\"\n")
                .append("  }").append(i < storedMessages.size() - 1 ? "," : "").append("\n");
        }
        json.append("]");
        try (FileWriter writer = new FileWriter("messages.json")) {
            writer.write(json.toString());
        } catch (IOException e) {
            System.out.println("Could not write messages.json: " + e.getMessage());
        }
    }

    private static String escape(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    /** Clears static state (used by unit tests). */
    public static void resetAll() {
        sentMessages.clear();
        storedMessages.clear();
        totalSent = 0;
    }

    public String getMessageId() { return messageId; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getText() { return text; }
    public int getMessageNumber() { return messageNumber; }
}
