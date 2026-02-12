<%@ page import="java.lang.Long" %>

<%
    String step = (String) request.getAttribute("step");
    if (step == null) step = "EMAIL";
%>

<html>
<head>
<title>Reset Password</title>

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

<body>

<h2>Reset Password</h2>

<!-- STEP 1 -->
<% if ("EMAIL".equals(step)) { %>

<form action="<%=request.getContextPath()%>/send-forgot-otp" method="post">
    Email:
    <input type="email" name="email" required />
    <br><br>
    <button type="submit">Send OTP</button>
</form>

<% } %>

<!-- STEP 2 -->
<% if ("OTP".equals(step)) { %>

<p id="timer" style="color:red;"></p>

<% if (request.getAttribute("message") != null) { %>
<p style="color:green;"><%= request.getAttribute("message") %></p>
<% } %>

<p><b>DEV OTP:</b> <%= session.getAttribute("DEV_OTP") %></p>

<form action="<%=request.getContextPath()%>/verify-forgot-otp"
      method="post"
      onsubmit="return remainingSeconds > 0;">

    OTP:
    <input type="text" id="otpInput" name="otp" required />
    <br><br>

    <button type="submit" id="verifyBtn">Verify OTP</button>
</form>

<br>

<form action="<%=request.getContextPath()%>/send-forgot-otp" method="post">
    <input type="hidden" name="action" value="resend"/>
    <button type="submit" id="resendBtn" disabled>Resend OTP</button>
</form>

<script>
    startTimer();
</script>

<% } %>

<!-- STEP 3 -->
<% if ("RESET".equals(step)) { %>

<form action="<%=request.getContextPath()%>/reset-password" method="post">

    New Password:
    <input type="password" name="password" required /><br><br>

    Confirm Password:
    <input type="password" name="confirmPassword" required /><br><br>

    <button type="submit">Reset Password</button>
</form>

<% } %>

<% if (request.getAttribute("error") != null) { %>
<p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>

</body>
</html>
