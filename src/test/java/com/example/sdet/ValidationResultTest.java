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

    @Test
    void doesNotHaveUpper_fails() {
        ValidationResult r = validator.validate("abcdef1!");
        assertFalse(r.isValid());
        assertTrue(r.getErrors().contains("Password must include at least one uppercase letter"));
    }

    @Test
    void doesNotHaveLower_fails() {
        ValidationResult r = validator.validate("ABCDEF1!");
        assertFalse(r.isValid());
        assertTrue(r.getErrors().contains("Password must include at least one lowercase letter"));
    }

    @Test
    void doesNotHaveNumbers_fails() {
        ValidationResult r = validator.validate("ABCDEFG!");
        assertFalse(r.isValid());
        assertTrue(r.getErrors().contains("Password must include at least one digit"));
    }

    @Test
    void doesNotHaveSpecialCharacters_fails() {
        ValidationResult r = validator.validate("ABCDEFGH");
        assertFalse(r.isValid());
        assertTrue(r.getErrors().contains("Password must include at least one special character"));
    }

}