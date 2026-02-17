<%@ page import="com.model.User,com.model.UserProfile" %>
<%
    User user = (User) request.getAttribute("user");
    UserProfile profile = (UserProfile) request.getAttribute("profile");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>User Profile</title>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet">

<!-- Custom CSS -->
<link rel="stylesheet"
      href="<%= request.getContextPath() %>/css/user-profile.css">

</head>

<body class="profile-body">

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-dark profile-navbar px-4">
    <div class="container-fluid">

        <span class="navbar-brand text-rainbow">
            Paynix Profile
        </span>

        <div class="ms-auto">
            <a class="nav-link d-inline nav-ocean"
               href="<%= request.getContextPath() %>/user/dashboard.jsp">
               Dashboard
            </a>

            <a class="nav-link d-inline nav-danger ms-3"
               href="<%= request.getContextPath() %>/user/logout">
               Logout
            </a>
        </div>

    </div>
</nav>

<!-- MAIN CONTENT -->
<div class="container-fluid vh-100 d-flex justify-content-center align-items-center">
<div class="row w-100 justify-content-center">
<div class="col-11 col-sm-10 col-md-8 col-lg-6">

<div class="card profile-card shadow-lg">
<div class="card-body p-4">

<h2 class="text-center text-ocean mb-4">User Profile</h2>

<form method="post" action="<%=request.getContextPath()%>/user/profile">

    <div class="mb-3">
        <label class="form-label text-light">Full Name</label>
        <input type="text"
               class="form-control input-ocean"
               name="fullName"
               value="<%= profile != null ? profile.getFullName() : "" %>"
               required>
    </div>

    <div class="mb-3">
        <label class="form-label text-light">Email</label>
        <input type="text"
               class="form-control input-disabled"
               value="<%= user.getEmail() %>"
               disabled>
    </div>

    <div class="mb-3">
        <label class="form-label text-light">Phone</label>
        <input type="text"
               class="form-control input-disabled"
               value="<%= user.getMobileNumber() %>"
               disabled>
    </div>

    <div class="mb-3">
        <label class="form-label text-light">Date of Birth</label>
        <input type="date"
               class="form-control input-ocean"
               name="dob"
               value="<%= profile != null ? profile.getDateOfBirth() : "" %>"
               required>
    </div>

    <div class="mb-3">
        <label class="form-label text-light">Gender</label>
        <select name="gender"
                class="form-select input-ocean"
                required>
            <option value="">Select</option>
            <option value="Male" <%= profile != null && "Male".equals(profile.getGender()) ? "selected" : "" %>>Male</option>
            <option value="Female" <%= profile != null && "Female".equals(profile.getGender()) ? "selected" : "" %>>Female</option>
            <option value="Other" <%= profile != null && "Other".equals(profile.getGender()) ? "selected" : "" %>>Other</option>
        </select>
    </div>

    <div class="mb-3">
        <label class="form-label text-light">Age</label>
        <input type="text"
               class="form-control input-disabled"
               value="<%= profile != null ? profile.getAge() : "" %>"
               disabled>
    </div>

    <div class="d-grid">
        <button type="submit"
                class="btn btn-ocean">
            Update Profile
        </button>
    </div>

</form>

</div>
</div>

</div>
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
