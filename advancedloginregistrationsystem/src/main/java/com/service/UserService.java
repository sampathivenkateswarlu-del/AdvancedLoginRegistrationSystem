package com.service;


import java.security.MessageDigest;

import com.dao.UserDAO;
import com.model.User;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    // ✅ Registration logic
    public String registerUser(String username, String email, String mobile,
                               String password, String confirmPassword) {

        // 1. Mandatory field validation
        if (username == null || username.isEmpty() ||
            email == null || email.isEmpty() ||
            mobile == null || mobile.isEmpty() ||
            password == null || password.isEmpty() ||
            confirmPassword == null || confirmPassword.isEmpty()) {

            return "All fields are mandatory";
        }

        // 2. Password match
        if (!password.equals(confirmPassword)) {
            return "Password and Confirm Password do not match";
        }

        // 3. Email uniqueness
        if (userDAO.emailExists(email)) {
            return "Email already registered";
        }

        // 4. Mobile uniqueness
        if (userDAO.mobileExists(mobile)) {
            return "Mobile number already registered";
        }

        // 5. Hash password
        String hashedPassword = hashPassword(password);

        // 6. Save user
        User user = new User(username, email, mobile, hashedPassword);
        boolean success = userDAO.registerUser(user);

        return success ? "SUCCESS" : "Registration failed";
    }

    // ✅ SHA-256 password hashing
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException("Password hashing failed", e);
        }
    }
}

