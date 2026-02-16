package com.example.sdet;

import java.util.ArrayList;
import java.util.List;

public final class PasswordValidator {

    public static final int MIN_LEN = 8;
    public static final int MAX_LEN = 64;

    public ValidationResult validate(String password) {
        List<String> errors = new ArrayList<>();

        if (password == null) {
            errors.add("Password must not be null");
            return new ValidationResult(false, errors);
        }

        int len = password.length();
        if (len < MIN_LEN || len > MAX_LEN) {
            errors.add("Password length must be between " + MIN_LEN + " and " + MAX_LEN);
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (int i = 0; i < len; i++) {
            char c = password.charAt(i);
            if (Character.isWhitespace(c)) {
                errors.add("Password must not contain whitespace");
                break;
            }
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else hasSpecial = true;
        }

        if (!hasUpper) errors.add("Password must include at least one uppercase letter");
        if (!hasLower) errors.add("Password must include at least one lowercase letter");
        if (!hasDigit) errors.add("Password must include at least one digit");
        if (!hasSpecial) errors.add("Password must include at least one special character");

        return new ValidationResult(errors.isEmpty(), errors);
    }
}