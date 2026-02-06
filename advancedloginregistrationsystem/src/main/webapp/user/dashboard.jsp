<%@ page import="com.model.User" %>
<%
    User user = (User) session.getAttribute("loggedUser");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/user/login.jsp");
        return;
    }
%>

<html>
<head>
    <title>User Dashboard</title>
</head>
<body>

<!-- Top Right Profile Button -->
<div style="text-align: right;">
    <a href="<%= request.getContextPath() %>/user/profile">Profile</a>
</div>

<h2>Welcome <%= user.getUsername() %></h2>

Email: <%= user.getEmail() %><br>
Role: <%= user.getRole() %><br><br>

<a href="<%= request.getContextPath() %>/user/logout">Logout</a>

</body>
</html>
