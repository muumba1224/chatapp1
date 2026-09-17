/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Login class
 * Tests username validation, password complexity, cell phone validation,
 * registration, and login functionality.
 * 
 * @author [Your Name]
 * @version 1.0
 */
public class LoginTest {
    
    private Login login;
    
    /**
     * Set up a fresh Login instance before each test
     */
    @BeforeEach
    public void setUp() {
        login = new Login();
    }
    
    // ============================================================
    // TEST: checkUserName() - Username Validation
    // Username must contain underscore and be no more than 5 characters
    // ============================================================
    
    @Test
    public void testCheckUserName_ValidUsername_ReturnsTrue() {
        // Test Data: "kyl_1"
        assertTrue(login.checkUserName("kyl_1"),
                "Username 'kyl_1' should be valid");
    }
    
    @Test
    public void testCheckUserName_ValidUsernameMaxLength_ReturnsTrue() {
        // Test Data: "abc_1" - exactly 5 characters
        assertTrue(login.checkUserName("abc_1"),
                "Username 'abc_1' should be valid (max length)");
    }
    
    @Test
    public void testCheckUserName_NoUnderscore_ReturnsFalse() {
        // Test Data: "kyle!!!!!!" - no underscore, too long
        assertFalse(login.checkUserName("kyle!!!!!!"),
                "Username without underscore should be invalid");
    }
    
    @Test
    public void testCheckUserName_TooLong_ReturnsFalse() {
        // Test Data: "username_test" - contains underscore but too long
        assertFalse(login.checkUserName("username_test"),
                "Username longer than 5 characters should be invalid");
    }
    
    @Test
    public void testCheckUserName_NullUsername_ReturnsFalse() {
        assertFalse(login.checkUserName(null),
                "Null username should be invalid");
    }
    
    @Test
    public void testCheckUserName_EmptyUsername_ReturnsFalse() {
        assertFalse(login.checkUserName(""),
                "Empty username should be invalid");
    }
    
    // ============================================================
    // TEST: checkPasswordComplexity() - Password Validation
    // Password must be: at least 8 chars, contain capital letter,
    // contain a number, and contain a special character
    // ============================================================
    
    @Test
    public void testCheckPasswordComplexity_ValidPassword_ReturnsTrue() {
        // Test Data: "Ch&sec@ke99!"
        assertTrue(login.checkPasswordComplexity("Ch&sec@ke99!"),
                "Password 'Ch&sec@ke99!' should be valid");
    }
    
    @Test
    public void testCheckPasswordComplexity_ValidPasswordDifferent_ReturnsTrue() {
        // Test Data: "P@ssw0rd123!"
        assertTrue(login.checkPasswordComplexity("P@ssw0rd123!"),
                "Password 'P@ssw0rd123!' should be valid");
    }
    
    @Test
    public void testCheckPasswordComplexity_TooShort_ReturnsFalse() {
        // Test Data: "pass" - less than 8 characters
        assertFalse(login.checkPasswordComplexity("pass"),
                "Password shorter than 8 characters should be invalid");
    }
    
    @Test
    public void testCheckPasswordComplexity_NoCapital_ReturnsFalse() {
        // Test Data: "password123!" - no capital letter
        assertFalse(login.checkPasswordComplexity("password123!"),
                "Password without capital letter should be invalid");
    }
    
    @Test
    public void testCheckPasswordComplexity_NoNumber_ReturnsFalse() {
        // Test Data: "Password!!!" - no number
        assertFalse(login.checkPasswordComplexity("Password!!!"),
                "Password without number should be invalid");
    }
    
    @Test
    public void testCheckPasswordComplexity_NoSpecial_ReturnsFalse() {
        // Test Data: "Password123" - no special character
        assertFalse(login.checkPasswordComplexity("Password123"),
                "Password without special character should be invalid");
    }
    
    @Test
    public void testCheckPasswordComplexity_NullPassword_ReturnsFalse() {
        assertFalse(login.checkPasswordComplexity(null),
                "Null password should be invalid");
    }
    
    @Test
    public void testCheckPasswordComplexity_EmptyPassword_ReturnsFalse() {
        assertFalse(login.checkPasswordComplexity(""),
                "Empty password should be invalid");
    }
    
    // ============================================================
    // TEST: checkCellPhoneNumber() - Cell Phone Validation
    // Must contain international country code (+27) followed by
    // 9-10 digits
    // ============================================================
    
    @Test
    public void testCheckCellPhoneNumber_ValidSANumber_ReturnsTrue() {
        // Test Data: +27838968976
        assertTrue(login.checkCellPhoneNumber("+27838968976"),
                "Cell number '+27838968976' should be valid");
    }
    
    @Test
    public void testCheckCellPhoneNumber_ValidSANumberDifferent_ReturnsTrue() {
        // Test Data: +27731234567
        assertTrue(login.checkCellPhoneNumber("+27731234567"),
                "Cell number '+27731234567' should be valid");
    }
    
    @Test
    public void testCheckCellPhoneNumber_ValidSANumberMaxDigits_ReturnsTrue() {
        // Test Data: +278312345678 (10 digits after country code)
        assertTrue(login.checkCellPhoneNumber("+278312345678"),
                "Cell number with 10 digits should be valid");
    }
    
    @Test
    public void testCheckCellPhoneNumber_NoInternationalCode_ReturnsFalse() {
        // Test Data: 08966553
        assertFalse(login.checkCellPhoneNumber("08966553"),
                "Cell number without international code should be invalid");
    }
    
    @Test
    public void testCheckCellPhoneNumber_WrongCountryCode_ReturnsFalse() {
        // Test Data: +19876543210
        assertFalse(login.checkCellPhoneNumber("+19876543210"),
                "Non-SA country code should be invalid");
    }
    
    @Test
    public void testCheckCellPhoneNumber_TooShort_ReturnsFalse() {
        // Test Data: +27123
        assertFalse(login.checkCellPhoneNumber("+27123"),
                "Cell number too short should be invalid");
    }
    
    @Test
    public void testCheckCellPhoneNumber_ContainsLetters_ReturnsFalse() {
        // Test Data: +27ABC12345
        assertFalse(login.checkCellPhoneNumber("+27ABC12345"),
                "Cell number with letters should be invalid");
    }
    
    @Test
    public void testCheckCellPhoneNumber_NullNumber_ReturnsFalse() {
        assertFalse(login.checkCellPhoneNumber(null),
                "Null cell number should be invalid");
    }
    
    @Test
    public void testCheckCellPhoneNumber_EmptyNumber_ReturnsFalse() {
        assertFalse(login.checkCellPhoneNumber(""),
                "Empty cell number should be invalid");
    }
    
    // ============================================================
    // TEST: registerUser() - Registration Process
    // ============================================================
    
    @Test
    public void testRegisterUser_AllValid_ReturnsSuccessMessage() {
        String result = login.registerUser("kyl_1", "Ch&sec@ke99!",
                "+27831234567", "Kyle", "Smith");
        
        assertTrue(result.contains("User registered successfully"),
                "Registration should succeed with valid data");
        assertTrue(result.contains("Kyle"),
                "Welcome message should contain first name");
        assertTrue(result.contains("Smith"),
                "Welcome message should contain last name");
    }
    
    @Test
    public void testRegisterUser_InvalidUsername_ReturnsErrorMessage() {
        String result = login.registerUser("kyle!!!!!!", "Ch&sec@ke99!",
                "+27831234567", "Kyle", "Smith");
        
        assertTrue(result.contains("Username is not correctly formatted"),
                "Should show username error message");
        assertFalse(result.contains("User registered successfully"),
                "Registration should fail with invalid username");
    }
    
    @Test
    public void testRegisterUser_InvalidPassword_ReturnsErrorMessage() {
        String result = login.registerUser("kyl_1", "password",
                "+27831234567", "Kyle", "Smith");
        
        assertTrue(result.contains("Password is not correctly formatted"),
                "Should show password error message");
        assertFalse(result.contains("User registered successfully"),
                "Registration should fail with invalid password");
    }
    
    @Test
    public void testRegisterUser_InvalidCellNumber_ReturnsErrorMessage() {
        String result = login.registerUser("kyl_1", "Ch&sec@ke99!",
                "08966553", "Kyle", "Smith");
        
        assertTrue(result.contains("Cell phone number incorrectly formatted"),
                "Should show cell number error message");
        assertFalse(result.contains("User registered successfully"),
                "Registration should fail with invalid cell number");
    }
    
    @Test
    public void testRegisterUser_AllInvalid_ReturnsAllErrorMessages() {
        String result = login.registerUser("kyle!!!!!!", "password",
                "08966553", "Kyle", "Smith");
        
        assertTrue(result.contains("Username is not correctly formatted"),
                "Should show username error");
        assertTrue(result.contains("Password is not correctly formatted"),
                "Should show password error");
        assertTrue(result.contains("Cell phone number incorrectly formatted"),
                "Should show cell number error");
        assertFalse(result.contains("User registered successfully"),
                "Registration should fail with all invalid data");
    }
    
    // ============================================================
    // TEST: loginUser() - Login Verification
    // ============================================================
    
    @Test
    public void testLoginUser_ValidCredentials_ReturnsTrue() {
        // Register a user first
        login.registerUser("kyl_1", "Ch&sec@ke99!",
                "+27831234567", "Kyle", "Smith");
        
        assertTrue(login.loginUser("kyl_1", "Ch&sec@ke99!"),
                "Valid credentials should allow login");
    }
    
    @Test
    public void testLoginUser_InvalidUsername_ReturnsFalse() {
        login.registerUser("kyl_1", "Ch&sec@ke99!",
                "+27831234567", "Kyle", "Smith");
        
        assertFalse(login.loginUser("wronguser", "Ch&sec@ke99!"),
                "Invalid username should not allow login");
    }
    
    @Test
    public void testLoginUser_InvalidPassword_ReturnsFalse() {
        login.registerUser("kyl_1", "Ch&sec@ke99!",
                "+27831234567", "Kyle", "Smith");
        
        assertFalse(login.loginUser("kyl_1", "wrongpass"),
                "Invalid password should not allow login");
    }
    
    @Test
    public void testLoginUser_NoUserRegistered_ReturnsFalse() {
        assertFalse(login.loginUser("kyl_1", "Ch&sec@ke99!"),
                "Login should fail when no user is registered");
    }
    
    // ============================================================
    // TEST: returnLoginStatus() - Login Status Messages
    // ============================================================
    
    @Test
    public void testReturnLoginStatus_ValidCredentials_ReturnsWelcomeMessage() {
        login.registerUser("kyl_1", "Ch&sec@ke99!",
                "+27831234567", "Kyle", "Smith");
        
        String status = login.returnLoginStatus("kyl_1", "Ch&sec@ke99!");
        
        assertEquals("Welcome Kyle, Smith it is great to see you again.", status,
                "Should display welcome message for valid login");
    }
    
    @Test
    public void testReturnLoginStatus_InvalidCredentials_ReturnsErrorMessage() {
        login.registerUser("kyl_1", "Ch&sec@ke99!",
                "+27831234567", "Kyle", "Smith");
        
        String status = login.returnLoginStatus("wronguser", "wrongpass");
        
        assertEquals("Username or password incorrect, please try again.", status,
                "Should display error message for invalid login");
    }
    
    @Test
    public void testReturnLoginStatus_NoUserRegistered_ReturnsErrorMessage() {
        String status = login.returnLoginStatus("kyl_1", "Ch&sec@ke99!");
        
        assertEquals("Username or password incorrect, please try again.", status,
                "Should display error message when no user is registered");
    }
}