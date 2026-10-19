package com.quickchat;

import java.util.Scanner;

/**
 * Console entry point: registration, login, then the QuickChat menu.
 */
public class QuickChat {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("=== Registration ===");
        System.out.print("First name: ");
        String first = in.nextLine().trim();
        System.out.print("Last name: ");
        String last = in.nextLine().trim();
        Login login = new Login(first, last);

        boolean registered = false;
        while (!registered) {
            System.out.print("Username: ");
            String username = in.nextLine().trim();
            System.out.print("Password: ");
            String password = in.nextLine();
            System.out.print("Cell number (e.g. +27838968976): ");
            String cell = in.nextLine().trim();

            String result = login.registerUser(username, password, cell);
            System.out.println(result);
            registered = result.contains("registered successfully");

            if (registered) {
                System.out.println("\n=== Login ===");
                boolean loggedIn = false;
                while (!loggedIn) {
                    System.out.print("Username: ");
                    String u = in.nextLine().trim();
                    System.out.print("Password: ");
                    String p = in.nextLine();
                    loggedIn = login.loginUser(u, p);
                    System.out.println(login.returnLoginStatus(loggedIn));
                }
            }
        }

        runMenu(in);
        in.close();
    }

    /** Part 2: welcome message, menu and message sending. */
    private static void runMenu(Scanner in) {
        System.out.println("\nWelcome to QuickChat.");
        int maxMessages = readInt(in, "How many messages would you like to enter? ");
        int enteredCount = 0;
        boolean running = true;

        while (running) {
            System.out.println("\n1) Send Messages\n2) Show recently sent messages\n3) Quit");
            int option = readInt(in, "Choose an option: ");

            switch (option) {
                case 1:
                    if (enteredCount >= maxMessages) {
                        System.out.println("You have already entered all " + maxMessages + " messages.");
                        break;
                    }
                    int remaining = maxMessages - enteredCount;
                    for (int i = 0; i < remaining; i++) {
                        enteredCount++;
                        processMessage(in, enteredCount - 1);
                    }
                    System.out.println("\nTotal messages sent: " + Message.returnTotalMessages());
                    break;
                case 2:
                    System.out.println("Coming Soon.");
                    break;
                case 3:
                    running = false;
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Please choose 1, 2 or 3.");
            }
        }
    }

    /** Collects and processes a single message. */
    private static void processMessage(Scanner in, int messageNumber) {
        String recipient;
        Message message;

        while (true) {
            System.out.print("\nRecipient cell number (with international code): ");
            recipient = in.nextLine().trim();
            Message check = new Message(messageNumber, recipient, "x");
            String cellResult = check.checkRecipientCell();
            System.out.println(cellResult);
            if (cellResult.startsWith("Cell phone number successfully")) {
                break;
            }
        }

        while (true) {
            System.out.print("Enter message (max 250 characters): ");
            String text = in.nextLine().trim();
            if (text.isEmpty()) {
                System.out.println("Message cannot be empty.");
                continue;
            }
            message = new Message(messageNumber, recipient, text);
            if (text.length() > Message.MAX_LENGTH) {
                System.out.println("Please enter a message of less than 250 characters.");
                System.out.println(message.checkMessageLength());
            } else {
                System.out.println("Message sent");
                break;
            }
        }

        System.out.println("1) Send Message  2) Disregard Message  3) Store Message to send later");
        int choice = readInt(in, "Choose: ");
        String result = message.sentMessage(choice);
        System.out.println(result);

        if (choice == Message.DISREGARD) {
            String zero = in.nextLine().trim();
            System.out.println(zero.equals("0") ? "Message deleted." : "Message kept as disregarded.");
        }
        if (choice == Message.SEND) {
            System.out.println("\n" + message.getDetails());
        }
    }

    /** Reads an integer, re-prompting on invalid input. */
    private static int readInt(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = in.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
