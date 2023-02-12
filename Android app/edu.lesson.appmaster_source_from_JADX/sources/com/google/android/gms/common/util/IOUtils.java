package com.google.android.gms.common.util;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.Nullable;

@Deprecated
/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public final class IOUtils {
    private IOUtils() {
    }

    public static void closeQuietly(@Nullable ParcelFileDescriptor p) {
        if (p != null) {
            try {
                p.close();
            } catch (IOException e) {
            }
        }
    }

    @Deprecated
    public static long copyStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        return copyStream(inputStream, outputStream, false, 1024);
    }

    public static boolean isGzipByteBuffer(byte[] inputBytes) {
        if (inputBytes.length > 1) {
            if ((((inputBytes[1] & 255) << 8) | (inputBytes[0] & 255)) == 35615) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public static byte[] readInputStreamFully(InputStream is) throws IOException {
        return readInputStreamFully(is, true);
    }

    @Deprecated
    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Preconditions.checkNotNull(in);
        Preconditions.checkNotNull(byteArrayOutputStream);
        byte[] bArr = new byte[4096];
        while (true) {
            int read = in.read(bArr);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public static void closeQuietly(@Nullable Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
            }
        }
    }

    @Deprecated
    public static long copyStream(InputStream inputStream, OutputStream outputStream, boolean closeWhenDone, int bufferSizeBytes) throws IOException {
        byte[] bArr = new byte[bufferSizeBytes];
        long j = 0;
        while (true) {
            try {
                int read = inputStream.read(bArr, 0, bufferSizeBytes);
                if (read == -1) {
                    break;
                }
                j += (long) read;
                outputStream.write(bArr, 0, read);
            } catch (Throwable th) {
                if (closeWhenDone) {
                    closeQuietly((Closeable) inputStream);
                    closeQuietly((Closeable) outputStream);
                }
                throw th;
            }
        }
        if (closeWhenDone) {
            closeQuietly((Closeable) inputStream);
            closeQuietly((Closeable) outputStream);
        }
        return j;
    }

    @Deprecated
    public static byte[] readInputStreamFully(InputStream is, boolean closeWhenDone) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copyStream(is, byteArrayOutputStream, closeWhenDone, 1024);
        return byteArrayOutputStream.toByteArray();
    }
}
