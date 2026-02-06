package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.MessageDigest;

import com.dao.UserDAO;
import com.model.User;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    @Override
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/user/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ✅ Ensure proper encoding
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 1. Mandatory validation
        if (email == null || email.isEmpty() ||
            password == null || password.isEmpty()) {

            request.setAttribute("error", "Email and Password are mandatory");
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
            return;
        }

        // 2. Fetch user
        User user = userDAO.getUserByEmail(email);

        if (user == null) {
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
            return;
        }

        // 3. Hash entered password
        String hashedInputPassword = hashPassword(password);

        // 4. Compare passwords
        if (!hashedInputPassword.equals(user.getPasswordHash())) {
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
            return;
        }

        // 5. Login success → create session
        HttpSession session = request.getSession();
        session.setAttribute("loggedUser", user);

        // 6. Role-based redirect (use context path)
        if ("ADMIN".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/admin/admindashboard.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/user/dashboard.jsp");
        }
    }

    // ✅ Hashing logic
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes("UTF-8")); // specify UTF-8

            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException("Password hashing failed", e);
        }
    }
}
