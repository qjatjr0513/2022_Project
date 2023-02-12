package com.google.firebase.database.core.utilities;

import com.google.firebase.database.snapshot.ChildKey;
import java.util.Random;

public class PushIdGenerator {
    private static final int MAX_KEY_LEN = 786;
    private static final char MAX_PUSH_CHAR = 'z';
    private static final char MIN_PUSH_CHAR = '-';
    private static final String PUSH_CHARS = "-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz";
    private static long lastPushTime = 0;
    private static final int[] lastRandChars = new int[12];
    private static final Random randGen = new Random();

    public static synchronized String generatePushChildName(long now) {
        String sb;
        synchronized (PushIdGenerator.class) {
            boolean z = false;
            boolean duplicateTime = now == lastPushTime;
            lastPushTime = now;
            char[] timeStampChars = new char[8];
            StringBuilder result = new StringBuilder(20);
            for (int i = 7; i >= 0; i--) {
                timeStampChars[i] = PUSH_CHARS.charAt((int) (now % 64));
                now /= 64;
            }
            Utilities.hardAssert(now == 0);
            result.append(timeStampChars);
            if (!duplicateTime) {
                for (int i2 = 0; i2 < 12; i2++) {
                    lastRandChars[i2] = randGen.nextInt(64);
                }
            } else {
                incrementArray();
            }
            for (int i3 = 0; i3 < 12; i3++) {
                result.append(PUSH_CHARS.charAt(lastRandChars[i3]));
            }
            if (result.length() == 20) {
                z = true;
            }
            Utilities.hardAssert(z);
            sb = result.toString();
        }
        return sb;
    }

    public static final String predecessor(String key) {
        Validation.validateNullableKey(key);
        Integer num = Utilities.tryParseInt(key);
        if (num == null) {
            StringBuilder next = new StringBuilder(key);
            if (next.charAt(next.length() - 1) != '-') {
                next.setCharAt(next.length() - 1, PUSH_CHARS.charAt(PUSH_CHARS.indexOf(next.charAt(next.length() - 1)) - 1));
                return next.append(new String(new char[(786 - next.length())]).replace("\u0000", "z")).toString();
            } else if (next.length() == 1) {
                return String.valueOf(Integer.MAX_VALUE);
            } else {
                return next.substring(0, next.length() - 1);
            }
        } else if (num.intValue() == Integer.MIN_VALUE) {
            return ChildKey.MIN_KEY_NAME;
        } else {
            return String.valueOf(num.intValue() - 1);
        }
    }

    public static final String successor(String key) {
        Validation.validateNullableKey(key);
        Integer num = Utilities.tryParseInt(key);
        if (num == null) {
            StringBuilder next = new StringBuilder(key);
            if (next.length() < MAX_KEY_LEN) {
                next.append(MIN_PUSH_CHAR);
                return next.toString();
            }
            int i = next.length() - 1;
            while (i >= 0 && next.charAt(i) == 'z') {
                i--;
            }
            if (i == -1) {
                return ChildKey.MAX_KEY_NAME;
            }
            next.replace(i, i + 1, String.valueOf(PUSH_CHARS.charAt(PUSH_CHARS.indexOf(next.charAt(i)) + 1)));
            return next.substring(0, i + 1);
        } else if (num.intValue() == Integer.MAX_VALUE) {
            return String.valueOf(MIN_PUSH_CHAR);
        } else {
            return String.valueOf(num.intValue() + 1);
        }
    }

    private static void incrementArray() {
        int i = 11;
        while (i >= 0) {
            int[] iArr = lastRandChars;
            if (iArr[i] != 63) {
                iArr[i] = iArr[i] + 1;
                return;
            } else {
                iArr[i] = 0;
                i--;
            }
        }
    }
}
