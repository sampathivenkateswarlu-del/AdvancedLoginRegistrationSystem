package com.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.security.MessageDigest;

import com.dao.UserDAO;

@WebServlet("/reset-password")
public class ForgetPasswordServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("VERIFIED") == null) {
            response.sendRedirect(request.getContextPath() + "/user/forgotpassword.jsp");
            return;
        }

        String email = (String) session.getAttribute("RESET_EMAIL");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirmPassword");

        if (password == null || confirm == null ||
            password.trim().isEmpty() || confirm.trim().isEmpty()) {

            request.setAttribute("error", "All fields are mandatory.");
            request.setAttribute("step", "RESET");
            request.getRequestDispatcher("/user/forgotpassword.jsp")
                   .forward(request, response);
            return;
        }

        if (!password.equals(confirm)) {
            request.setAttribute("error", "Passwords do not match.");
            request.setAttribute("step", "RESET");
            request.getRequestDispatcher("/user/forgotpassword.jsp")
                   .forward(request, response);
            return;
        }

        try {
            String hashedPassword = hash(password);
            userDAO.updatePassword(email, hashedPassword);
        } catch (Exception e) {
            request.setAttribute("error", "Something went wrong. Try again.");
            request.setAttribute("step", "RESET");
            request.getRequestDispatcher("/user/forgotpassword.jsp")
                   .forward(request, response);
            return;
        }

        // Cleanup session completely after success
        session.removeAttribute("VERIFIED");
        session.removeAttribute("RESET_EMAIL");
        session.removeAttribute("DEV_OTP");

        request.setAttribute("success", "Password reset successful. Please login.");
        request.getRequestDispatcher("/user/login.jsp")
               .forward(request, response);
    }

    private String hash(String password) {

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

