document.addEventListener("DOMContentLoaded", function () {

    const form = document.getElementById("registerForm");

    const username = document.getElementById("username");
    const email = document.getElementById("email");
    const mobile = document.getElementById("mobile");
    const password = document.getElementById("password");
    const confirmPassword = document.getElementById("confirmPassword");
    const submitBtn = document.getElementById("submitBtn");

    // =========================
    // REGEX PATTERNS
    // =========================

    const usernamePattern = /^[A-Za-z][A-Za-z0-9_]{4,14}$/;
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const mobilePattern = /^[6-9]\d{9}$/;
    const strongPasswordPattern =
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&]).{8,}$/;

    // =========================
    // VALIDATION FUNCTIONS
    // =========================

    function validateUsername() {
        const value = username.value.trim();
        const errorDiv = document.getElementById("usernameError");

        if (!usernamePattern.test(value)) {
            setInvalid(username, errorDiv,
                "Username must start with letter, 5-15 chars, no spaces.");
            return false;
        }

        setValid(username, errorDiv);
        return true;
    }

    function validateEmail() {
        const value = email.value.trim();
        const errorDiv = document.getElementById("emailError");

        if (!emailPattern.test(value)) {
            setInvalid(email, errorDiv, "Enter valid email address.");
            return false;
        }

        setValid(email, errorDiv);
        return true;
    }

    function validateMobile() {
        const value = mobile.value.trim();
        const errorDiv = document.getElementById("mobileError");

        if (!mobilePattern.test(value)) {
            setInvalid(mobile, errorDiv,
                "Enter valid 10-digit Indian mobile number.");
            return false;
        }

        setValid(mobile, errorDiv);
        return true;
    }

    function validatePassword() {
        const value = password.value.trim();
        const errorDiv = document.getElementById("passwordError");

        if (!strongPasswordPattern.test(value)) {
            setInvalid(password, errorDiv,
                "Min 8 chars, upper, lower, number & special char.");
            return false;
        }

        setValid(password, errorDiv);
        return true;
    }

    function validateConfirmPassword() {
        const errorDiv = document.getElementById("confirmPasswordError");

        if (confirmPassword.value === "" ||
            confirmPassword.value !== password.value) {

            setInvalid(confirmPassword, errorDiv,
                "Passwords do not match.");
            return false;
        }

        setValid(confirmPassword, errorDiv);
        return true;
    }

    // =========================
    // HELPER FUNCTIONS
    // =========================

    function setInvalid(input, errorDiv, message) {
        input.classList.remove("valid");
        input.classList.add("invalid");
        errorDiv.textContent = message;
    }

    function setValid(input, errorDiv) {
        input.classList.remove("invalid");
        input.classList.add("valid");
        errorDiv.textContent = "";
    }

    // =========================
    // SUBMIT BUTTON CONTROL
    // =========================

    function updateSubmitState() {
        const allValid =
            username.classList.contains("valid") &&
            email.classList.contains("valid") &&
            mobile.classList.contains("valid") &&
            password.classList.contains("valid") &&
            confirmPassword.classList.contains("valid");

        submitBtn.disabled = !allValid;
    }

    // =========================
    // REAL-TIME LISTENERS
    // =========================

    username.addEventListener("input", function () {
        validateUsername();
        updateSubmitState();
    });

    email.addEventListener("input", function () {
        validateEmail();
        updateSubmitState();
    });

    mobile.addEventListener("input", function () {
        validateMobile();
        updateSubmitState();
    });

    password.addEventListener("input", function () {
        validatePassword();
        validateConfirmPassword();
        updateSubmitState();
    });

    confirmPassword.addEventListener("input", function () {
        validateConfirmPassword();
        updateSubmitState();
    });

    // Extra safety
    form.addEventListener("submit", function (e) {
        if (submitBtn.disabled) {
            e.preventDefault();
        }
    });

});
