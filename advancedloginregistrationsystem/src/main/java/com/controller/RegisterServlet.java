package com.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.service.UserService;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

   
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        String result = userService.registerUser(
                username, email, mobile, password, confirmPassword
        );

        if ("SUCCESS".equals(result)) {
            request.setAttribute("success", "Registration successful. Please login.");
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", result);
            request.getRequestDispatcher("/user/register.jsp").forward(request, response);
        }
    }
}
