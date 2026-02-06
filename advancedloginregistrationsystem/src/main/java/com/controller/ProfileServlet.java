package com.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import com.dao.UserProfileDAO;
import com.model.User;
import com.model.UserProfile;

@WebServlet("/user/profile")
public class ProfileServlet extends HttpServlet {

 
	private static final long serialVersionUID = 1L;
	private UserProfileDAO profileDAO = new UserProfileDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("loggedUser");

        UserProfile profile = profileDAO.getProfileByUserId(user.getId());
        request.setAttribute("profile", profile);
        request.setAttribute("user", user);

        request.getRequestDispatcher("/user/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("loggedUser");

        String fullName = request.getParameter("fullName");
        String dobStr = request.getParameter("dob");
        String gender = request.getParameter("gender");

        LocalDate dob = LocalDate.parse(dobStr);
        int age = Period.between(dob, LocalDate.now()).getYears();

        UserProfile profile = new UserProfile();
        profile.setUserId(user.getId());
        profile.setFullName(fullName);
        profile.setDateOfBirth(Date.valueOf(dob));
        profile.setGender(gender);
        profile.setAge(age);

        if (profileDAO.getProfileByUserId(user.getId()) == null) {
            profileDAO.createProfile(profile);
        } else {
            profileDAO.updateProfile(profile);
        }

        response.sendRedirect(request.getContextPath() + "/user/profile");
    }
}
