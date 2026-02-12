package com.service;

import java.util.Random;

import jakarta.servlet.http.HttpSession;

public class OTPService {

    private static final int OTP_VALIDITY_MS = 2 * 60 * 1000; // 2 minutes

    public String generateOTP(HttpSession session) {

        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        long expiryTime = System.currentTimeMillis() + OTP_VALIDITY_MS;

        session.setAttribute("OTP_VALUE", otp);
        session.setAttribute("OTP_EXPIRY", expiryTime);

        System.out.println("Generated OTP: " + otp);

        return otp;
    }

    public boolean isOTPExpired(HttpSession session) {
        Long expiry = (Long) session.getAttribute("OTP_EXPIRY");
        return (expiry == null || System.currentTimeMillis() > expiry);
    }

    public boolean verifyOTP(HttpSession session, String enteredOtp) {

        String sessionOtp = (String) session.getAttribute("OTP_VALUE");

        if (sessionOtp == null) return false;

        return sessionOtp.equals(enteredOtp);
    }

    public void clearOTP(HttpSession session) {
        session.removeAttribute("OTP_VALUE");
        session.removeAttribute("OTP_EXPIRY");
    }
}


