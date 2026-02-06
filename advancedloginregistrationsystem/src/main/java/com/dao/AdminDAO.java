package com.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.Admin;
import com.utility.DBConnection;



public class AdminDAO {

    private static final String FIND_BY_USERNAME =
        "SELECT admin_id, username, password_hash, role FROM admin WHERE username = ?";

    public Admin findByUsername(String username) {

        Admin admin = null;

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(FIND_BY_USERNAME)
        ) {
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setUsername(rs.getString("username"));
                admin.setPasswordHash(rs.getString("password_hash"));
                admin.setRole(rs.getString("role"));
            }

        } catch (Exception e) {
            e.printStackTrace(); // replace with logging in real apps
        }

        return admin;
    }
    
    public Admin getAdminByUsername(String username) {

        String sql = "SELECT * FROM admin WHERE username = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Admin admin = new Admin();
                
                admin.setUsername(rs.getString("username"));
                admin.setPasswordHash(rs.getString("password"));
                admin.setRole(rs.getString("role"));
                return admin;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}

