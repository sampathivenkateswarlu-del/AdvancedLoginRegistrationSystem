package com.dao;

import java.sql.*;

import com.utility.DBConnection;

public class OTPDAO {

    // Save or replace OTP
    public void saveOTP(String email, String otp, Timestamp expiresAt) {

        String sql = """
            REPLACE INTO password_reset
            (email, otp_code, expires_at, used)
            VALUES (?, ?, ?, false)
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, otp);
            ps.setTimestamp(3, expiresAt);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Validate OTP
    public boolean validateOTP(String email, String otp) {

        String sql = """
            SELECT * FROM password_reset
            WHERE email = ? AND otp_code = ? AND used = false AND expires_at > NOW()
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, otp);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Mark OTP as used
    public void markOTPUsed(String email) {

        String sql = "UPDATE password_reset SET used = true WHERE email = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

