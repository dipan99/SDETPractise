package com.example.sdet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationResultTest {
    private final PasswordValidator validator = new PasswordValidator();

    @Test
    void validPassword_passes() {
        ValidationResult r = validator.validate("Abcdef1!");
        assertTrue(r.isValid());
        assertTrue(r.getErrors().isEmpty());
    }

    @Test
    void nullPassword_fails() {
        ValidationResult r = validator.validate(null);
        assertFalse(r.isValid());
        assertTrue(r.getErrors().contains("Password must not be null"));
    }

}