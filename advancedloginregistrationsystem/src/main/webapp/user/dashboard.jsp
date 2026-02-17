<%@ page import="com.model.User" %>
<%
    User user = (User) session.getAttribute("loggedUser");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/user/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>User Dashboard</title>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet">

<!-- Custom CSS -->
<link rel="stylesheet"
      href="<%= request.getContextPath() %>/css/user-dashboard.css">

</head>

<body class="dashboard-body">

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-dark dashboard-navbar px-4">
    <div class="container-fluid">

        <span class="navbar-brand text-rainbow">
            Paynix Dashboard
        </span>

        <div class="ms-auto">
            <a class="nav-link d-inline nav-ocean"
               href="<%= request.getContextPath() %>/user/profile">
               Profile
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

<div class="card dashboard-card shadow-lg">
<div class="card-body p-4 text-center">

<h2 class="welcome-text mb-3">
    Welcome <%= user.getUsername() %>
</h2>

<div class="info-box">
    <p><strong>Email:</strong> <%= user.getEmail() %></p>
    <p><strong>Role:</strong> <%= user.getRole() %></p>
</div>

</div>
</div>

</div>
</div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
