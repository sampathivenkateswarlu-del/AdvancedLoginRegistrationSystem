package com.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import com.service.OTPService;

@WebServlet("/send-forgot-otp")
public class SendForgotOTPServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private OTPService otpService = new OTPService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String action = request.getParameter("action");

        // If resend, get email from session
        if ("resend".equals(action)) {
            email = (String) session.getAttribute("RESET_EMAIL");
        }

        if (email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "Email is required.");
            request.setAttribute("step", "EMAIL");
            request.getRequestDispatcher("/user/forgotpassword.jsp")
                   .forward(request, response);
            return;
        }

        session.setAttribute("RESET_EMAIL", email);

        // Generate new OTP (overwrites old one automatically)
        String otp = otpService.generateOTP(session);

        // DEV only
        session.setAttribute("DEV_OTP", otp);

        request.setAttribute("step", "OTP");
        request.setAttribute("message", 
            "resend".equals(action) ? "OTP resent successfully." : "OTP sent successfully.");

        request.getRequestDispatcher("/user/forgotpassword.jsp")
               .forward(request, response);
    }
}


