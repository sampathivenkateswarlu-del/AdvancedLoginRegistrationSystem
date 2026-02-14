<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.model.Admin" %>

<%
    Admin admin = (Admin) session.getAttribute("adminUser");

    if (admin == null) {
        response.sendRedirect(request.getContextPath() + "/admin/adminlogin.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>

<h1>Admin Dashboard</h1>

<h2>
    Welcome to : <%= admin.getUsername() %>
</h2>

<hr>

<a href="<%= request.getContextPath() %>/all-users">All Users</a> |
<a href="<%= request.getContextPath() %>/logout">Logout</a>

</body>
</html>
