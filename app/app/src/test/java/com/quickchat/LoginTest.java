package com.quickchat;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private Login login;

    @BeforeEach
    void setUp() {
        login = new Login("Kyle", "Smith");
    }

    @Test
    void usernameCorrectlyFormatted() {
        assertEquals("Username successfully captured.", login.usernameMessage("kyl_1"));
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    void usernameIncorrectlyFormatted() {
        assertEquals("Username is not correctly formatted; please ensure that your username "
                + "contains an underscore and is no more than five characters in length.",
                login.usernameMessage("kyle!!!!!!!"));
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    void passwordMeetsComplexity() {
        assertEquals("Password successfully captured.", login.passwordMessage("Ch&&sec@ke99!"));
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    void passwordFailsComplexity() {
        assertEquals("Password is not correctly formatted; please ensure that the password "
                + "contains at least eight characters, a capital letter, a number, and "
                + "a special character.", login.passwordMessage("password"));
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    void cellNumberCorrectlyFormatted() {
        assertEquals("Cell number successfully captured.", login.cellMessage("+27838968976"));
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    void cellNumberIncorrectlyFormatted() {
        assertEquals("Cell number is incorrectly formatted or does not contain an international "
                + "code; please correct the number and try again.", login.cellMessage("08966553"));
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    void loginSuccessful() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
        assertEquals("Welcome Kyle, Smith it is great to see you again.",
                login.returnLoginStatus(true));
    }

    @Test
    void loginFailed() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrong"));
        assertEquals("Username or password incorrect, please try again.",
                login.returnLoginStatus(false));
    }

    @Test
    void registerUserReportsSuccess() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(result.contains("registered successfully"));
    }
}
