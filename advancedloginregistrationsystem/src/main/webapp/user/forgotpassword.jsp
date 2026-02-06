<%@ page import="java.lang.Long" %>

<%
    String step = (String) request.getAttribute("step");
    if (step == null) step = "EMAIL";
%>

<html>
<head>
    <title>Reset Password</title>

    <!-- OTP TIMER SCRIPT -->
    <script>
        let remainingSeconds = <%= 
            (session.getAttribute("OTP_EXPIRY") != null)
            ? ((Long) session.getAttribute("OTP_EXPIRY") - System.currentTimeMillis()) / 1000
            : 0
        %>;

        function startTimer() {
            const timerEl = document.getElementById("timer");

            const interval = setInterval(() => {
                if (remainingSeconds <= 0) {
                    clearInterval(interval);
                    timerEl.innerHTML = "OTP expired. Please resend OTP.";
                    document.getElementById("otpInput").disabled = true;
                    document.getElementById("verifyBtn").disabled = true;
                } else {
                    timerEl.innerHTML = "OTP expires in: " + remainingSeconds + " seconds";
                    remainingSeconds--;
                }
            }, 1000);
        }
    </script>
</head>

<body>

<h2>Reset Password</h2>

<!-- ================= STEP 1 : EMAIL ================= -->
<% if ("EMAIL".equals(step)) { %>

<form action="<%=request.getContextPath()%>/send-forgot-otp" method="post">
    Email:
    <input type="email" name="email" required />
    <br><br>
    <button type="submit">Send OTP</button>
</form>

<% } %>

<!-- ================= STEP 2 : OTP ================= -->
<% if ("OTP".equals(step)) { %>

<p id="timer" style="color:red;"></p>

<p style="color:green;">
    OTP sent successfully.<br>
    <b>DEV OTP:</b> <%= session.getAttribute("DEV_OTP") %>
</p>

<form action="<%=request.getContextPath()%>/verify-forgot-otp"
      method="post"
      onsubmit="return remainingSeconds > 0;">

    <input type="hidden" name="email"
           value="<%= session.getAttribute("RESET_EMAIL") %>" />

    OTP:
    <input type="text" id="otpInput" name="otp" required />
    <br><br>

    <button type="submit" id="verifyBtn">Verify OTP</button>
</form>

<script>
    startTimer();
</script>

<% } %>

<!-- ================= STEP 3 : RESET PASSWORD ================= -->
<% if ("RESET".equals(step)) { %>

<form action="<%=request.getContextPath()%>/reset-password" method="post">
    <input type="hidden" name="email"
           value="<%= session.getAttribute("RESET_EMAIL") %>" />

    <input type="hidden" name="otp"
           value="<%= session.getAttribute("VERIFIED_OTP") %>" />

    New Password:
    <input type="password" name="password" required /><br><br>

    Confirm Password:
    <input type="password" name="confirmPassword" required /><br><br>

    <button type="submit">Reset Password</button>
</form>

<% } %>

<!-- ERROR MESSAGE -->
<% if (request.getAttribute("error") != null) { %>
<p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

</body>
</html>
