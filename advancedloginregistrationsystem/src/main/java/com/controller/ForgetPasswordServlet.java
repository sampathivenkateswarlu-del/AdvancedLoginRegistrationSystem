package com.controller;



import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.security.MessageDigest;

import com.dao.UserDAO;
import com.service.OTPService;

@WebServlet("/reset-password")
public class ForgetPasswordServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OTPService otpService = new OTPService();
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String otp = request.getParameter("otp");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirmPassword");

        if (email.isEmpty() || otp.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            request.setAttribute("error", "All fields are mandatory");
            request.getRequestDispatcher("/user/forgetpassword.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirm)) {
            request.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("/user/forgetpassword.jsp").forward(request, response);
            return;
        }

        if (!otpService.verifyOTP(email, otp)) {
            request.setAttribute("error", "Invalid or expired OTP");
            request.getRequestDispatcher("/user/forgetpassword.jsp").forward(request, response);
            return;
        }

        userDAO.updatePassword(email, hash(password));
        otpService.expireOTP(email);

        request.setAttribute("success", "Password reset successful. Please login.");
        request.getRequestDispatcher("/user/login.jsp").forward(request, response);
    }

    private String hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
