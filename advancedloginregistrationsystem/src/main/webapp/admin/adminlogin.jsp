<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Admin Login</title></head>
<body>

<h2>Admin Login</h2>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<p style="color:red;"><%= error %></p>
<% } %>

<form action="<%= request.getContextPath() %>/adminLogin" method="post">
    Username:<br>
    <input type="text" name="username" required><br><br>

    Password:<br>
    <input type="password" name="password" required><br><br>

    <button type="submit">Login</button>
</form>

</body>
</html>
