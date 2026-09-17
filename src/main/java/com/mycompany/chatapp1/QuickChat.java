/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp1;



import java.util.Scanner;

/**
 * Main entry point for the QuickChat application.
 * Handles user interaction for registration and login (Part 1).
 */
public class QuickChat {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("========================================");
        System.out.println("   Welcome to QuickChat Registration    ");
        System.out.println("========================================");

        // ---------- REGISTRATION ----------
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter username (must contain '_' and be <= 5 chars): ");
        String username = scanner.nextLine();

        System.out.print("Enter password (min 8 chars, capital, number, special): ");
        String password = scanner.nextLine();

        System.out.print("Enter cell phone (e.g. +27838968976): ");
        String cellPhone = scanner.nextLine();

        String registrationMessage = login.registerUser(
                username, password, cellPhone, firstName, lastName);

        System.out.println("\n--- Registration Result ---");
        System.out.println(registrationMessage);

        // If registration failed, exit early
        if (!registrationMessage.contains("User registered successfully")) {
            System.out.println("\nRegistration failed. Please restart and try again.");
            scanner.close();
            return;
        }

        // ---------- LOGIN ----------
        System.out.println("\n========================================");
        System.out.println("              Login                     ");
        System.out.println("========================================");

        System.out.print("Enter username: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = scanner.nextLine();

        boolean loginSuccess = login.loginUser(loginUsername, loginPassword);
        System.out.println("\n" + login.returnLoginStatus(loginSuccess));

        if (loginSuccess) {
            System.out.println("\nLogin successful! Welcome to QuickChat.");
            // Part 2 features would be added here in the next submission.
        } else {
            System.out.println("\nPlease check your credentials and try again.");
        }

        scanner.close();
    }
}