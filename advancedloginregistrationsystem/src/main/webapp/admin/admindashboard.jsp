<%@ page import="com.model.User" %>
<%
    User user = (User) session.getAttribute("loggedUser");

    if (user != null) {
        if ("ADMIN".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/admin/admindashboard.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/user/dashboard.jsp");
        }
    } else {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
%>
