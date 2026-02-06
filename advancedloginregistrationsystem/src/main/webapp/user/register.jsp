<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User Registration</title>
</head>
<body>

<h2>Register</h2>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<p style="color:red;"><%= error %></p>
<% } %>

<form action="<%= request.getContextPath() %>/register" method="post">

    Username:<br>
    <input type="text" name="username" required><br><br>

    Email:<br>
    <input type="email" name="email" required><br><br>

    Mobile:<br>
    <input type="text" name="mobile" required><br><br>

    Password:<br>
    <input type="password" name="password" required><br><br>

    Confirm Password:<br>
    <input type="password" name="confirmPassword" required><br><br>

    <button type="submit">Register</button>
</form>

<p>
    Already have an account?
    <a href="<%= request.getContextPath() %>/user/login.jsp">Login</a>
</p>

</body>
</html>
