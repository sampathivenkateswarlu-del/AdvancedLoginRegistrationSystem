<%@ page import="com.model.User,com.model.UserProfile" %>
<%
    User user = (User) request.getAttribute("user");
    UserProfile profile = (UserProfile) request.getAttribute("profile");
%>

<html>
<head>
    <title>User Profile</title>
</head>
<body>

<h2>User Profile</h2>

<div style="text-align: right;">
    <a href="<%= request.getContextPath() %>/user/dashboard.jsp">Back</a>
</div>

<form method="post" action="<%=request.getContextPath()%>/user/profile">

    Full Name:
    <input type="text" name="fullName"
           value="<%= profile != null ? profile.getFullName() : "" %>" required><br><br>

    Email:
    <input type="text" value="<%= user.getEmail() %>" disabled><br><br>

    Phone:
    <input type="text" value="<%= user.getMobileNumber() %>" disabled><br><br>

    Date of Birth:
    <input type="date" name="dob"
           value="<%= profile != null ? profile.getDateOfBirth() : "" %>" required><br><br>

    Gender:
    <select name="gender" required>
        <option value="">Select</option>
        <option value="Male" <%= profile != null && "Male".equals(profile.getGender()) ? "selected" : "" %>>Male</option>
        <option value="Female" <%= profile != null && "Female".equals(profile.getGender()) ? "selected" : "" %>>Female</option>
        <option value="Other" <%= profile != null && "Other".equals(profile.getGender()) ? "selected" : "" %>>Other</option>
    </select><br><br>

    Age:
    <input type="text" value="<%= profile != null ? profile.getAge() : "" %>" disabled><br><br>

    <button type="submit">Update Profile</button>

</form>

</body>
</html>
