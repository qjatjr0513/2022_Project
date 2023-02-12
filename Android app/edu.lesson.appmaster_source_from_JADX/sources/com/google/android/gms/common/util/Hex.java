package com.google.android.gms.common.util;

import com.google.common.base.Ascii;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public class Hex {
    private static final char[] zza = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final char[] zzb = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String bytesToStringLowercase(byte[] bytes) {
        int length = bytes.length;
        char[] cArr = new char[(length + length)];
        int i = 0;
        for (byte b : bytes) {
            byte b2 = b & 255;
            int i2 = i + 1;
            char[] cArr2 = zzb;
            cArr[i] = cArr2[b2 >>> 4];
            i = i2 + 1;
            cArr[i2] = cArr2[b2 & Ascii.f62SI];
        }
        return new String(cArr);
    }

    public static String bytesToStringUppercase(byte[] bytes) {
        return bytesToStringUppercase(bytes, false);
    }

    public static byte[] stringToBytes(String hex) throws IllegalArgumentException {
        int length = hex.length();
        if (length % 2 == 0) {
            byte[] bArr = new byte[(length / 2)];
            int i = 0;
            while (i < length) {
                int i2 = i + 2;
                bArr[i / 2] = (byte) Integer.parseInt(hex.substring(i, i2), 16);
                i = i2;
            }
            return bArr;
        }
        throw new IllegalArgumentException("Hex string has odd number of characters");
    }

    public static String bytesToStringUppercase(byte[] bytes, boolean zeroTerminated) {
        int length = bytes.length;
        StringBuilder sb = new StringBuilder(length + length);
        int i = 0;
        while (i < length && (!zeroTerminated || i != length - 1 || (bytes[i] & 255) != 0)) {
            char[] cArr = zza;
            sb.append(cArr[(bytes[i] & 240) >>> 4]);
            sb.append(cArr[bytes[i] & Ascii.f62SI]);
            i++;
        }
        return sb.toString();
    }
}
