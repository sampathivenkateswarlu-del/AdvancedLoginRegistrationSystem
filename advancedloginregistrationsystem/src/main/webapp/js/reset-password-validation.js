document.addEventListener("DOMContentLoaded", function () {

    /* ==========================================================
       COMMON FUNCTION: CLEAR EXISTING ERRORS
    ========================================================== */
    function clearValidation(input) {
        input.classList.remove("is-invalid");
        const existing = input.parentElement.querySelector(".validation-error");
        if (existing) existing.remove();
    }

    /* ==========================================================
       COMMON FUNCTION: SHOW ERROR
    ========================================================== */
    function showValidation(input, message) {
        clearValidation(input);

        input.classList.add("is-invalid");

        const error = document.createElement("div");
        error.className = "validation-error text-danger small mt-1";
        error.innerText = message;

        input.parentElement.appendChild(error);
    }

    /* ==========================================================
       STEP 1 — EMAIL VALIDATION
       (Ignore resend hidden form)
    ========================================================== */
    const emailForms = document.querySelectorAll("form[action*='send-forgot-otp']");

    emailForms.forEach(function (form) {

        // Skip resend form
        const hiddenAction = form.querySelector("input[name='action']");
        if (hiddenAction) return;

        form.addEventListener("submit", function (e) {

            const emailInput = form.querySelector("input[name='email']");
            if (!emailInput) return;

            const emailValue = emailInput.value.trim();
            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if (!emailPattern.test(emailValue)) {
                e.preventDefault();
                showValidation(emailInput, "Please enter a valid email address.");
            } else {
                clearValidation(emailInput);
            }

        });
    });

    /* ==========================================================
       STEP 2 — OTP VALIDATION
    ========================================================== */
    const otpForm = document.querySelector("form[action*='verify-forgot-otp']");

    if (otpForm) {

        otpForm.addEventListener("submit", function (e) {

            const otpInput = document.getElementById("otpInput");
            if (!otpInput) return;

            const otpValue = otpInput.value.trim();

            if (!/^\d{4,6}$/.test(otpValue)) {
                e.preventDefault();
                showValidation(otpInput, "OTP must contain 4–6 digits only.");
                return;
            } else {
                clearValidation(otpInput);
            }

            // Check expiry safely
            if (typeof remainingSeconds !== "undefined") {
                if (remainingSeconds <= 0) {
                    e.preventDefault();
                    showValidation(otpInput, "OTP expired. Please resend OTP.");
                }
            }

        });
    }

    /* ==========================================================
       STEP 3 — PASSWORD VALIDATION
    ========================================================== */
    const resetForm = document.querySelector("form[action*='reset-password']");

    if (resetForm) {

        resetForm.addEventListener("submit", function (e) {

            const passwordInput = resetForm.querySelector("input[name='password']");
            const confirmInput = resetForm.querySelector("input[name='confirmPassword']");

            if (!passwordInput || !confirmInput) return;

            const password = passwordInput.value.trim();
            const confirmPassword = confirmInput.value.trim();

            const strongPasswordRegex =
                /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&]).{8,}$/;

            let valid = true;

            if (!strongPasswordRegex.test(password)) {
                showValidation(
                    passwordInput,
                    "Password must be at least 8 characters and include uppercase, lowercase, number, and special character."
                );
                valid = false;
            } else {
                clearValidation(passwordInput);
            }

            if (password !== confirmPassword) {
                showValidation(confirmInput, "Passwords do not match.");
                valid = false;
            } else {
                clearValidation(confirmInput);
            }

            if (!valid) {
                e.preventDefault();
            }

        });
    }

});
