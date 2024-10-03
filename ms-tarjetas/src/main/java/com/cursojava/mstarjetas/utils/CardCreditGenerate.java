package com.cursojava.mstarjetas.utils;

import com.cursojava.mstarjetas.enums.CardType;

import java.util.Random;

public class CardCreditGenerate {

    private static final Random random = new Random();

    public static String generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder();

        cardNumber.append(4);

        for (int i = 0; i < 14; i++) {
            cardNumber.append(random.nextInt(10));
        }

        int checksum = calculateLuhnChecksum(cardNumber.toString());
        cardNumber.append(checksum);

        return cardNumber.toString();
    }

    private static int calculateLuhnChecksum(String cardNumber) {
        int sum = 0;
        boolean alternate = false;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum * 9) % 10;
    }

    public static Long generateCVV() {
        return (long) (random.nextInt(9000) + 1000);
    }

    public static String getCardType(String cardCategory) {
        switch (cardCategory.toUpperCase()) {
            case "BASIC":
                return CardType.A.name();
            case "GOLD":
                return CardType.B.name();
            case "BLACK":
                return CardType.C.name();
            default:
                throw new IllegalArgumentException("Invalid card category: " + cardCategory);
        }
    }
}
