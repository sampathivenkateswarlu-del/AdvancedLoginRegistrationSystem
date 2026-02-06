package com.service;



import java.security.MessageDigest;

import com.dao.AdminDAO;
import com.exception.AuthenticationException;
import com.model.Admin;

public class AdminAuthService {

    private AdminDAO adminDAO = new AdminDAO();

    public Admin login(String username, String password) throws AuthenticationException {

        Admin admin = adminDAO.getAdminByUsername(username);

        if (admin == null) {
            throw new AuthenticationException("Invalid username or password");
        }

        String hashedInputPassword = hashPassword(password);

        if (!hashedInputPassword.equals(admin.getPasswordHash())) {
            throw new AuthenticationException("Invalid username or password");
        }

        return admin;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
