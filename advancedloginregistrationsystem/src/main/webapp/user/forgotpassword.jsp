<%@ page import="java.lang.Long" %>

<%
    String step = (String) request.getAttribute("step");
    if (step == null) step = "EMAIL";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Reset Password</title>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet">

<!-- Custom CSS -->
<link rel="stylesheet"
      href="<%= request.getContextPath() %>/css/reset-password.css">

<script>
    let remainingSeconds = <% 
        Long expiry = (Long) session.getAttribute("OTP_EXPIRY");
        if (expiry != null) {
            long seconds = Math.max(0, (expiry - System.currentTimeMillis()) / 1000);
            out.print(seconds);
        } else {
            out.print(0);
        }
    %>;

    function startTimer() {

        const timerEl = document.getElementById("timer");
        const resendBtn = document.getElementById("resendBtn");

        const interval = setInterval(() => {

            if (remainingSeconds <= 0) {
                clearInterval(interval);
                timerEl.innerHTML = "OTP expired.";
                document.getElementById("otpInput").disabled = true;
                document.getElementById("verifyBtn").disabled = true;
                if (resendBtn) resendBtn.disabled = false;
            } else {
                timerEl.innerHTML = "OTP expires in: " + remainingSeconds + " seconds";
                remainingSeconds--;
            }

        }, 1000);
    }
</script>
</head>

<body class="reset-body">

<div class="container-fluid vh-100 d-flex justify-content-center align-items-center">
<div class="row w-100 justify-content-center">
<div class="col-11 col-sm-10 col-md-8 col-lg-5">

<div class="card reset-card shadow-lg">
<div class="card-body p-4">

<h2 class="text-center mb-4 text-ocean">Reset Password</h2>

<!-- STEP 1 -->
<% if ("EMAIL".equals(step)) { %>

<form action="<%=request.getContextPath()%>/send-forgot-otp" method="post">

    <div class="mb-3">
        <label class="form-label text-light">Email</label>
        <input type="email"
               name="email"
               class="form-control input-ocean"
               required />
    </div>

    <div class="d-grid">
        <button type="submit" class="btn btn-ocean">
            Send OTP
        </button>
    </div>
</form>

<% } %>

<!-- STEP 2 -->
<% if ("OTP".equals(step)) { %>

<div id="timer" class="timer-box mb-3"></div>

<% if (request.getAttribute("message") != null) { %>
<div class="alert alert-success text-center">
    <%= request.getAttribute("message") %>
</div>
<% } %>

<div class="dev-otp mb-3">
    <b>DEV OTP:</b> <%= session.getAttribute("DEV_OTP") %>
</div>

<form action="<%=request.getContextPath()%>/verify-forgot-otp"
      method="post"
      onsubmit="return remainingSeconds > 0;">

    <div class="mb-3">
        <label class="form-label text-light">Enter OTP</label>
        <input type="text"
               id="otpInput"
               name="otp"
               class="form-control input-ocean"
               required />
    </div>

    <div class="d-grid">
        <button type="submit"
                id="verifyBtn"
                class="btn btn-ocean">
            Verify OTP
        </button>
    </div>
</form>

<br>

<form action="<%=request.getContextPath()%>/send-forgot-otp" method="post">
    <input type="hidden" name="action" value="resend"/>
    <div class="d-grid">
        <button type="submit"
                id="resendBtn"
                class="btn btn-outline-ocean"
                disabled>
            Resend OTP
        </button>
    </div>
</form>

<script>
    startTimer();
</script>

<% } %>

<!-- STEP 3 -->
<% if ("RESET".equals(step)) { %>

<form action="<%=request.getContextPath()%>/reset-password" method="post">

    <div class="mb-3">
        <label class="form-label text-light">New Password</label>
        <input type="password"
               name="password"
               class="form-control input-ocean"
               required />
    </div>

    <div class="mb-3">
        <label class="form-label text-light">Confirm Password</label>
        <input type="password"
               name="confirmPassword"
               class="form-control input-ocean"
               required />
    </div>

    <div class="d-grid">
        <button type="submit" class="btn btn-ocean">
            Reset Password
        </button>
    </div>
</form>

<% } %>

<% if (request.getAttribute("error") != null) { %>
<div class="alert alert-danger text-center mt-3">
    <%= request.getAttribute("error") %>
</div>
<% } %>

</div>
</div>
</div>
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="<%= request.getContextPath() %>/js/reset-password-validation.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script src="<%= request.getContextPath() %>/js/reset-password-validation.js"></script>
</body>
</html>
