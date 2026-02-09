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

        String email = request.getParameter("email");

        HttpSession session = request.getSession();
        otpService.generateOTP(email, session);

        session.setAttribute("RESET_EMAIL", email);
        request.setAttribute("step", "OTP");

        request.getRequestDispatcher("/user/forgotpassword.jsp").forward(request, response);
    }
}







//@WebServlet("/send-otp")
//public class SendOTPServlet extends HttpServlet {
//
//    
//	private static final long serialVersionUID = 1L;
//	private OTPService otpService = new OTPService();
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String email = request.getParameter("email");
//        String result = otpService.sendOTP(email);
//
//        if ("OTP_SENT".equals(result)) {
//            request.setAttribute("email", email);
//            request.setAttribute("otpSent", true);
//        } else {
//            request.setAttribute("error", result);
//        }
//
//        request.getRequestDispatcher("/user/forgetpassword.jsp")
//               .forward(request, response);
//    }
//}
//
