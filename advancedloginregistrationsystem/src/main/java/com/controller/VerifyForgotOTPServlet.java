package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.service.OTPService;


@WebServlet("/verify-forgot-otp")
public class VerifyForgotOTPServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private OTPService otpService = new OTPService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String otp = request.getParameter("otp");

        Long expiryTime = (Long) session.getAttribute("OTP_EXPIRY");

        // ❌ Expiry missing
        if (expiryTime == null || System.currentTimeMillis() > expiryTime) {
            request.setAttribute("error", "OTP expired. Please resend OTP.");
            request.setAttribute("step", "EMAIL");

            session.removeAttribute("OTP_EXPIRY");
            session.removeAttribute("DEV_OTP");

            request.getRequestDispatcher("/user/forgotpassword.jsp")
                   .forward(request, response);
            return;
        }

        // ❌ Wrong OTP
        if (!otpService.verifyOTP(email, otp)) {
            request.setAttribute("error", "Invalid OTP");
            request.setAttribute("step", "OTP");
            request.getRequestDispatcher("/user/forgotpassword.jsp")
                   .forward(request, response);
            return;
        }

        // ✅ Success
        session.setAttribute("VERIFIED_OTP", otp);
        request.setAttribute("step", "RESET");

        request.getRequestDispatcher("/user/forgotpassword.jsp")
               .forward(request, response);
    }
}



//@WebServlet("/verify-forgot-otp")
//public class VerifyForgotOTPServlet extends HttpServlet {
//
//   
//	private static final long serialVersionUID = 1L;
//	private OTPService otpService = new OTPService();
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String email = request.getParameter("email");
//        String otp = request.getParameter("otp");
//
//        if (!otpService.verifyOTP(email, otp)) {
//            request.setAttribute("error", "Invalid OTP");
//            request.setAttribute("step", "OTP");
//            request.getRequestDispatcher("/user/forgotpassword.jsp").forward(request, response);
//            return;
//        }
//
//        request.getSession().setAttribute("VERIFIED_OTP", otp);
//        request.setAttribute("step", "RESET");
//
//        request.getRequestDispatcher("/user/forgotpassword.jsp").forward(request, response);
//    }
//}
