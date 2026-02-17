<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <!-- Custom CSS -->
    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/register.css">
</head>

<body class="register-body">

<div class="container-fluid vh-100 d-flex justify-content-center align-items-center">
    <div class="row w-100 justify-content-center">
        <div class="col-11 col-sm-10 col-md-8 col-lg-5">

            <div class="card register-card shadow-lg">
                <div class="card-body p-4">

                    <h2 class="text-center mb-4 text-ocean">Register</h2>

                    <%
                        String error = (String) request.getAttribute("error");
                        if (error != null) {
                    %>
                        <div class="alert alert-danger text-center" role="alert">
                            <%= error %>
                        </div>
                    <% } %>

                    <form id="registerForm"
                          action="<%= request.getContextPath() %>/register"
                          method="post"
                          novalidate>

                        <div class="mb-3">
                            <label class="form-label text-light">Username</label>
                            <input type="text"
                                   id="username"
                                   name="username"
                                   class="form-control input-ocean">
                            <div id="usernameError" class="error-text"></div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label text-light">Email</label>
                            <input type="email"
                                   id="email"
                                   name="email"
                                   class="form-control input-ocean">
                            <div id="emailError" class="error-text"></div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label text-light">Mobile</label>
                            <input type="text"
                                   id="mobile"
                                   name="mobile"
                                   class="form-control input-ocean">
                            <div id="mobileError" class="error-text"></div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label text-light">Password</label>
                            <input type="password"
                                   id="password"
                                   name="password"
                                   class="form-control input-ocean">
                            <div id="passwordError" class="error-text"></div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label text-light">Confirm Password</label>
                            <input type="password"
                                   id="confirmPassword"
                                   name="confirmPassword"
                                   class="form-control input-ocean">
                            <div id="confirmPasswordError" class="error-text"></div>
                        </div>

                        <div class="d-grid">
                            <button type="submit"
                                    id="submitBtn"
                                    class="btn btn-ocean"
                                    disabled>
                                Register
                            </button>
                        </div>

                    </form>

                    <div class="text-center mt-3 text-light">
                        Already have an account?
                        <a class="link-ocean"
                           href="<%= request.getContextPath() %>/user/login.jsp">
                           Login
                        </a>
                    </div>

                </div>
            </div>

        </div>
    </div>
</div>

<!-- External JS File (UNCHANGED) -->
<script src="<%= request.getContextPath() %>/js/register-validation.js"></script>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
