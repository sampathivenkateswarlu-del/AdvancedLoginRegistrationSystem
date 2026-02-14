<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
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
    <p><%= error %></p>
<% } %>

<form id="registerForm"
      action="<%= request.getContextPath() %>/register"
      method="post"
      novalidate>

    <label>Username:</label><br>
    <input type="text" id="username" name="username"><br>
    <div id="usernameError"></div><br>

    <label>Email:</label><br>
    <input type="email" id="email" name="email"><br>
    <div id="emailError"></div><br>

    <label>Mobile:</label><br>
    <input type="text" id="mobile" name="mobile"><br>
    <div id="mobileError"></div><br>

    <label>Password:</label><br>
    <input type="password" id="password" name="password"><br>
    <div id="passwordError"></div><br>

    <label>Confirm Password:</label><br>
    <input type="password" id="confirmPassword" name="confirmPassword"><br>
    <div id="confirmPasswordError"></div><br>

    <button type="submit" id="submitBtn" disabled>Register</button>

</form>

<p>
    Already have an account?
    <a href="<%= request.getContextPath() %>/user/login.jsp">Login</a>
</p>

<!-- External JS File -->
<script src="<%= request.getContextPath() %>/js/register-validation.js"></script>

</body>
</html>
