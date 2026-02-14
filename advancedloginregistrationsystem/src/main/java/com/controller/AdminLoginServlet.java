package com.controller;

import java.io.IOException;

import com.exception.AuthenticationException;
import com.model.Admin;
import com.service.AdminAuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AdminAuthService authService = new AdminAuthService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {

            Admin admin = authService.login(username, password);

            if (admin == null) {
                throw new AuthenticationException("Invalid credentials");
            }

            HttpSession session = req.getSession(true);

            // ✅ Store admin in session
            session.setAttribute("adminUser", admin);

            resp.sendRedirect(req.getContextPath() + "/admin/admindashboard.jsp");

        } catch (AuthenticationException e) {

            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/admin/adminlogin.jsp")
               .forward(req, resp);
        }
    }
}
