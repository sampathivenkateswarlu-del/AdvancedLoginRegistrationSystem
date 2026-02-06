package com.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.model.UserProfile;

import com.utility.DBConnection;

public class UserProfileDAO {

    public UserProfile getProfileByUserId(int userId) {
        String sql = "SELECT * FROM user_profile WHERE user_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UserProfile profile = new UserProfile();
                profile.setProfileId(rs.getInt("profile_id"));
                profile.setUserId(rs.getInt("user_id"));
                profile.setFullName(rs.getString("full_name"));
                profile.setDateOfBirth(rs.getDate("date_of_birth"));
                profile.setGender(rs.getString("gender"));
                profile.setAge(rs.getInt("age"));
                return profile;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createProfile(UserProfile profile) {
        String sql = "INSERT INTO user_profile (user_id, full_name, date_of_birth, gender, age) VALUES (?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, profile.getUserId());
            ps.setString(2, profile.getFullName());
            ps.setDate(3, profile.getDateOfBirth());
            ps.setString(4, profile.getGender());
            ps.setInt(5, profile.getAge());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProfile(UserProfile profile) {
        String sql = "UPDATE user_profile SET full_name=?, date_of_birth=?, gender=?, age=? WHERE user_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, profile.getFullName());
            ps.setDate(2, profile.getDateOfBirth());
            ps.setString(3, profile.getGender());
            ps.setInt(4, profile.getAge());
            ps.setInt(5, profile.getUserId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

