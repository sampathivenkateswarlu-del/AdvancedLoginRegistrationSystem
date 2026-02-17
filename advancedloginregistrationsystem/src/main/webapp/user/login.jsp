<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Paynix - Login</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
          rel="stylesheet">

    <!-- Custom CSS -->
    <link rel="stylesheet" 
          href="<%= request.getContextPath() %>/css/login.css">
</head>

<body class="login-body">

<div class="container-fluid vh-100 d-flex justify-content-center align-items-center">
    <div class="row w-100 justify-content-center">
        <div class="col-11 col-sm-8 col-md-6 col-lg-4">

            <div class="card login-card shadow-lg">
                <div class="card-body p-4">

                    <h2 class="text-center mb-4 text-ocean">User Login</h2>

                    <% 
                        String error = (String) request.getAttribute("error");
                        if (error != null) {
                    %>
                        <div class="alert alert-danger text-center" role="alert">
                            <%= error %>
                        </div>
                    <% } %>

                    <form action="<%= request.getContextPath() %>/user/login" method="post">

                        <div class="mb-3">
                            <label class="form-label text-light">Email</label>
                            <input type="email" 
                                   class="form-control input-ocean" 
                                   name="email" 
                                   required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label text-light">Password</label>
                            <input type="password" 
                                   class="form-control input-ocean" 
                                   name="password" 
                                   required>
                        </div>

                        <div class="d-grid">
                            <button type="submit" 
                                    class="btn btn-ocean">
                                Login
                            </button>
                        </div>
                    </form>

                    <div class="text-center mt-3">
                        <a class="link-ocean"
                           href="<%= request.getContextPath() %>/user/forgotpassword.jsp">
                           Forgot Password?
                        </a>
                    </div>

                    <div class="text-center mt-2 text-light">
                        New user?
                        <a class="link-ocean"
                           href="<%= request.getContextPath() %>/user/register.jsp">
                           Register here
                        </a>
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
