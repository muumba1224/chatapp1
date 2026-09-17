package com.mycompany.chatapp1;

import java.util.regex.Pattern;

/**
 * The Login class handles user registration and authentication
 * for the QuickChat application.
 * 
 * @author Student
 * @version 1.0
 */
public class Login {

    // Stored user details (populated during registration)
    private String storedUsername;
    private String storedPassword;
    private String storedCellPhone;
    private String storedFirstName;
    private String storedLastName;

    // Regex for South African international cell phone number
    // Reference: https://www.baeldung.com/java-regex-validate-phone-numbers
    private static final String CELL_PHONE_REGEX = "^\\+27[0-9]{9}$";

    /**
     * Checks that the username contains an underscore and
     * is no more than five characters long.
     *
     * @param username the username to validate
     * @return true if valid, false otherwise
     */
    public boolean checkUserName(String username) {
        if (username == null) {
            return false;
        }
        return username.contains("_") && username.length() <= 5;
    }

    /**
     * Checks that the password meets complexity rules:
     * - At least 8 characters long
     * - Contains a capital letter
     * - Contains a number
     * - Contains a special character
     *
     * @param password the password to validate
     * @return true if valid, false otherwise
     */
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        return hasCapital && hasNumber && hasSpecial;
    }

    /**
     * Checks that the cell phone number contains the international
     * country code (+27) followed by 9 digits (max 10 characters after code).
     *
     * @param cellPhone the cell phone number to validate
     * @return true if valid, false otherwise
     */
    public boolean checkCellPhoneNumber(String cellPhone) {
        if (cellPhone == null) {
            return false;
        }
        return Pattern.matches(CELL_PHONE_REGEX, cellPhone);
    }

    /**
     * Registers the user by validating all fields and storing them.
     *
     * @param username  the chosen username
     * @param password  the chosen password
     * @param cellPhone the user's cell phone number
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @return a message indicating the registration status
     */
    public String registerUser(String username, String password,
                               String cellPhone, String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your "
                    + "username contains an underscore and is no more than five "
                    + "characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the "
                    + "password contains at least eight characters, a capital "
                    + "letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellPhone)) {
            return "Cell phone number incorrectly formatted or does not contain "
                    + "international code.";
        }

        // Store the user details
        this.storedUsername = username;
        this.storedPassword = password;
        this.storedCellPhone = cellPhone;
        this.storedFirstName = firstName;
        this.storedLastName = lastName;

        return "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell phone number successfully added.\n"
                + "User registered successfully.";
    }

    /**
     * Verifies that the entered login details match the stored details.
     *
     * @param username the entered username
     * @param password the entered password
     * @return true if the credentials match, false otherwise
     */
    public boolean loginUser(String username, String password) {
        if (storedUsername == null || storedPassword == null) {
            return false;
        }
        return storedUsername.equals(username) && storedPassword.equals(password);
    }

    /**
     * Returns the appropriate login status message.
     *
     * @param loginSuccess whether login was successful
     * @return the status message
     */
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + storedFirstName + ", " + storedLastName
                    + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // ---- Getters (useful for testing) ----
    public String getStoredUsername() { return storedUsername; }
    public String getStoredPassword() { return storedPassword; }
    public String getStoredCellPhone() { return storedCellPhone; }
    public String getStoredFirstName() { return storedFirstName; }
    public String getStoredLastName() { return storedLastName; }

    String returnLoginStatus(String kyl_1, String chsecke99) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
