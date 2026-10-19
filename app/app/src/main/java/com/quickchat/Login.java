package com.quickchat;

import java.util.regex.Pattern;

/**
 * Handles user registration and login for QuickChat (Part 1).
 */
public class Login {

    /*
     * Cell number rule: a '+' international code (1-3 digits) followed by a
     * subscriber number of no more than ten digits.
     * Regex written for this PoE; replace/extend this attribution with the
     * source(s) you researched (Harvard style), e.g.
     * Author (Year) Title. Available at: URL (Accessed: date).
     */
    private static final Pattern CELL_PATTERN = Pattern.compile("^\\+\\d{1,3}\\d{1,10}$");

    private String storedUsername;
    private String storedPassword;
    private String storedCellNumber;
    private final String firstName;
    private final String lastName;

    /**
     * @param firstName user's first name (used in the welcome message)
     * @param lastName  user's last name (used in the welcome message)
     */
    public Login(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /** Username must contain an underscore and be no more than five characters. */
    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    /** Password: 8+ characters, a capital letter, a number and a special character. */
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetter(c)) {
                hasSpecial = true;
            }
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    /** Cell number must start with an international code and be correctly sized. */
    public boolean checkCellPhoneNumber(String cellNumber) {
        return cellNumber != null && CELL_PATTERN.matcher(cellNumber).matches();
    }

    /** Message for the username check. */
    public String usernameMessage(String username) {
        if (checkUserName(username)) {
            return "Username successfully captured.";
        }
        return "Username is not correctly formatted; please ensure that your username "
                + "contains an underscore and is no more than five characters in length.";
    }

    /** Message for the password check. */
    public String passwordMessage(String password) {
        if (checkPasswordComplexity(password)) {
            return "Password successfully captured.";
        }
        return "Password is not correctly formatted; please ensure that the password "
                + "contains at least eight characters, a capital letter, a number, and "
                + "a special character.";
    }

    /** Message for the cell number check. */
    public String cellMessage(String cellNumber) {
        if (checkCellPhoneNumber(cellNumber)) {
            return "Cell number successfully captured.";
        }
        return "Cell number is incorrectly formatted or does not contain an international "
                + "code; please correct the number and try again.";
    }

    /**
     * Validates the details and registers the user when all are valid.
     * @return the registration messaging
     */
    public String registerUser(String username, String password, String cellNumber) {
        String result = usernameMessage(username) + "\n"
                + passwordMessage(password) + "\n"
                + cellMessage(cellNumber);

        if (checkUserName(username) && checkPasswordComplexity(password)
                && checkCellPhoneNumber(cellNumber)) {
            storedUsername = username;
            storedPassword = password;
            storedCellNumber = cellNumber;
            result += "\nUser has been registered successfully.";
        }
        return result;
    }

    /** Verifies the details match those stored at registration. */
    public boolean loginUser(String username, String password) {
        return storedUsername != null
                && storedUsername.equals(username)
                && storedPassword.equals(password);
    }

    /** Returns the login status message. */
    public String returnLoginStatus(boolean loggedIn) {
        if (loggedIn) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }

    public String getStoredCellNumber() {
        return storedCellNumber;
    }
}
