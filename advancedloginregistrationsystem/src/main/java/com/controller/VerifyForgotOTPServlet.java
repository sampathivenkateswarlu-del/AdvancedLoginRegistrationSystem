package com.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import com.service.OTPService;

@WebServlet("/verify-forgot-otp")
public class VerifyForgotOTPServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private OTPService otpService = new OTPService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String enteredOtp = request.getParameter("otp");

        if (enteredOtp == null || enteredOtp.trim().isEmpty()) {
            request.setAttribute("error", "OTP is required.");
            request.setAttribute("step", "OTP");
            request.getRequestDispatcher("/user/forgotpassword.jsp")
                   .forward(request, response);
            return;
        }

        // Expiry check
        if (otpService.isOTPExpired(session)) {

            otpService.clearOTP(session);

            request.setAttribute("error", "OTP expired. Please resend OTP.");
            request.setAttribute("step", "OTP");

            request.getRequestDispatcher("/user/forgotpassword.jsp")
                   .forward(request, response);
            return;
        }

        // Validate OTP
        if (!otpService.verifyOTP(session, enteredOtp)) {

            request.setAttribute("error", "Invalid OTP.");
            request.setAttribute("step", "OTP");

            request.getRequestDispatcher("/user/forgotpassword.jsp")
                   .forward(request, response);
            return;
        }

        // Success
        session.setAttribute("VERIFIED", true);
        otpService.clearOTP(session);

        request.setAttribute("step", "RESET");

        request.getRequestDispatcher("/user/forgotpassword.jsp")
               .forward(request, response);
    }
}

