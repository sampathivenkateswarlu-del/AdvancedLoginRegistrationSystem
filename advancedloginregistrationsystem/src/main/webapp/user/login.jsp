<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Paynix - Login</title>
</head>
<body>

<h2>User Login</h2>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<p style="color:red;"><%= error %></p>
<% } %>

<form action="<%= request.getContextPath() %>/user/login" method="post">
    Email:<br>
    <input type="email" name="email" required><br><br>

    Password:<br>
    <input type="password" name="password" required><br><br>

    <button type="submit">Login</button>
</form>

<p>
    <a href="<%= request.getContextPath() %>/user/forgotpassword.jsp">Forgot Password?</a>
</p>

<p>
    New user?
    <a href="<%= request.getContextPath() %>/user/register.jsp">Register here</a>
</p>

</body>
</html>
