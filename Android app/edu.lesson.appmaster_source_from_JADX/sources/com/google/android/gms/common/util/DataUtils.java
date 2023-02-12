package com.google.android.gms.common.util;

import android.database.CharArrayBuffer;
import android.graphics.Bitmap;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public final class DataUtils {
    public static void copyStringToBuffer(String toCopy, CharArrayBuffer dataOut) {
        if (TextUtils.isEmpty(toCopy)) {
            dataOut.sizeCopied = 0;
            return;
        }
        if (dataOut.data == null || dataOut.data.length < toCopy.length()) {
            dataOut.data = toCopy.toCharArray();
        } else {
            toCopy.getChars(0, toCopy.length(), dataOut.data, 0);
        }
        dataOut.sizeCopied = toCopy.length();
    }

    public static byte[] loadImageBytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
