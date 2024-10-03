package com.cursojava.mstarjetas.utils;

import java.util.Random;

public class SummaryGenerator {

    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final int LETTER_COUNT = 5;
    private static final int DIGIT_COUNT = 5;

    public static String generateRandomString() {
        Random random = new Random();

        // Generate random letters
        StringBuilder sb = new StringBuilder(LETTER_COUNT + DIGIT_COUNT);
        for (int i = 0; i < LETTER_COUNT; i++) {
            char letter = LETTERS.charAt(random.nextInt(LETTERS.length()));
            sb.append(letter);
        }

        // Generate random digits
        for (int i = 0; i < DIGIT_COUNT; i++) {
            char digit = DIGITS.charAt(random.nextInt(DIGITS.length()));
            sb.append(digit);
        }

        return sb.toString();
    }
}
