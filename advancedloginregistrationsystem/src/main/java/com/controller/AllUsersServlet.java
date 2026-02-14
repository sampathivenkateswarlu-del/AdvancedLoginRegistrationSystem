package com.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

import com.dao.UserDAO;
import com.model.Admin;
import com.model.User;

@WebServlet("/all-users")
public class AllUsersServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        Admin admin = (session != null) ?
                (Admin) session.getAttribute("adminUser") : null;

        // ✅ Proper admin check
        if (admin == null) {
            response.sendRedirect(request.getContextPath() + "/admin/adminlogin.jsp");
            return;
        }

        // Fetch all users
        List<User> users = userDAO.getAllUsers();

        request.setAttribute("usersList", users);

        RequestDispatcher rd =
                request.getRequestDispatcher("/admin/allusers.jsp");
        rd.forward(request, response);
    }
}
