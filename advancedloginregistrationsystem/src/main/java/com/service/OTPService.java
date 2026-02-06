package com.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import jakarta.servlet.http.HttpSession;

public class OTPService {

    private static final Map<String, String> otpStore = new HashMap<>();

    public String generateOTP(String email, HttpSession session) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        otpStore.put(email, otp);

        // ✅ ADD THIS (for displaying in JSP – dev only)
        session.setAttribute("DEV_OTP", otp);

        System.out.println("Paynix OTP for " + email + " : " + otp);
        return otp;
    }

    public boolean verifyOTP(String email, String otp) {
        return otp.equals(otpStore.get(email));
    }

    public void expireOTP(String email) {
        otpStore.remove(email);
    }
}




//package com.service;
//
//import java.sql.Timestamp;
//import java.util.Random;
//
//import com.dao.OTPDAO;
//import com.dao.UserDAO;
//
//public class OTPService {
//
//    private OTPDAO otpDAO = new OTPDAO();
//    private UserDAO userDAO = new UserDAO();
//
//    // Generate & send OTP
//    public String sendOTP(String email) {
//
//        if (email == null || email.isEmpty()) {
//            return "Email is required";
//        }
//
//        if (userDAO.getUserByEmail(email) == null) {
//            return "Email not registered";
//        }
//
//        String otp = generateOTP();
//        Timestamp expiresAt = new Timestamp(System.currentTimeMillis() + (5 * 60 * 1000));
//
//        otpDAO.saveOTP(email, otp, expiresAt);
//
//        // 🔹 Mock email sending (real app → JavaMail)
//        System.out.println("Paynix OTP for " + email + " : " + otp);
//
//        return "OTP_SENT";
//    }
//
//    public boolean verifyOTP(String email, String otp) {
//        return otpDAO.validateOTP(email, otp);
//    }
//
//    public void expireOTP(String email) {
//        otpDAO.markOTPUsed(email);
//    }
//
//    private String generateOTP() {
//        return String.valueOf(100000 + new Random().nextInt(900000));
//    }
//}
