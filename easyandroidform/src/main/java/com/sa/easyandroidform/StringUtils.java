package com.sa.easyandroidform;

import androidx.core.util.PatternsCompat;

import java.util.Random;

public class StringUtils {

    public static boolean isValidEmail(String email) {
        return !StringUtils.isNullOrEmpty(email) && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static String trimEnd(String value) {
        // Use replaceFirst to remove trailing spaces.
        return value.replaceFirst("\\s+$", "");
    }

    public static String trimStart(String value) {
        // Remove leading spaces.
        return value.replaceFirst("^\\s+", "");
    }

    public static String stripTrailingLeadingNewLines(String text) {
        return trimEnd(trimStart(text));
    }

    /**
     * Check if a string is null or empty
     *
     * @param s string to check
     * @return True if null or empty
     */
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static String random(int length) {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        char tempChar;
        for (int i = 0; i < length; i++) {
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }
}
