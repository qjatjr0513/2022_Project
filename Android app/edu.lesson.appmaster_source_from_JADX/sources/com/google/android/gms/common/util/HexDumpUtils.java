package com.google.android.gms.common.util;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public final class HexDumpUtils {
    public static String dump(byte[] data, int offset, int length, boolean outputText) {
        int length2;
        int i;
        if (data == null || (length2 = data.length) == 0 || offset < 0 || length <= 0 || offset + length > length2) {
            return null;
        }
        if (outputText) {
            i = 75;
        } else {
            i = 57;
        }
        StringBuilder sb = new StringBuilder(i * ((length + 15) / 16));
        int i2 = length;
        int i3 = 0;
        int i4 = 0;
        while (i2 > 0) {
            if (i3 == 0) {
                if (length < 65536) {
                    sb.append(String.format("%04X:", new Object[]{Integer.valueOf(offset)}));
                } else {
                    sb.append(String.format("%08X:", new Object[]{Integer.valueOf(offset)}));
                }
                i4 = offset;
            } else if (i3 == 8) {
                sb.append(" -");
            }
            sb.append(String.format(" %02X", new Object[]{Integer.valueOf(data[offset] & 255)}));
            i2--;
            i3++;
            if (outputText && (i3 == 16 || i2 == 0)) {
                int i5 = 16 - i3;
                if (i5 > 0) {
                    for (int i6 = 0; i6 < i5; i6++) {
                        sb.append("   ");
                    }
                }
                if (i5 >= 8) {
                    sb.append("  ");
                }
                sb.append("  ");
                for (int i7 = 0; i7 < i3; i7++) {
                    char c = (char) data[i4 + i7];
                    if (c < ' ') {
                        c = '.';
                    } else if (c > '~') {
                        c = '.';
                    }
                    sb.append(c);
                }
            }
            if (i3 == 16 || i2 == 0) {
                sb.append(10);
                i3 = 0;
            }
            offset++;
        }
        return sb.toString();
    }
}
