package com.cai.utils;

import java.util.Random;

public class RandomStringUtils {

    private static Random random;

    public static Random getInstance() {
        if(random == null) {
            synchronized (RandomStringUtils.class) {
                if(random == null) {
                    return new Random();
                }
            }
        }
        return random;
    }

    public static String randomStringNumber(int count) {
        if(count < 1)return null;
        Random random = getInstance();
        int[] numberAndBound = getNumberAndBound(count);
        return String.valueOf(numberAndBound[0] - random.nextInt(numberAndBound[1]));
    }

    private static int[] getNumberAndBound(int count) {
        int number = 1;
        int bound = 0;
        for (int i = 0; i < count; i++) {
            number *= 10;
        }
        bound = number - 1 - (number/10);
        number = number - 1;
        int[] numberAndBound = new int[2];
        numberAndBound[0] = number;
        numberAndBound[1] = bound;
        return numberAndBound;
    }

}
