<%@ page import="com.model.Admin" %>
<%
    Admin admin = (Admin) session.getAttribute("ADMIN");
    if (admin == null) {
        response.sendRedirect(request.getContextPath() + "/admin/adminlogin.jsp");
        return;
    }
%>

<html>
<head><title>Admin Dashboard</title></head>
<body>

<h2>Welcome Admin: <%= admin.getUsername() %></h2>
Role: <%= admin.getRole() %><br><br>

<a href="<%= request.getContextPath() %>/admin/logout">Logout</a>

</body>
</html>
