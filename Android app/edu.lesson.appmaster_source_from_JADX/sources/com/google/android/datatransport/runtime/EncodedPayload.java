package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import java.util.Arrays;

public final class EncodedPayload {
    private final byte[] bytes;
    private final Encoding encoding;

    public EncodedPayload(Encoding encoding2, byte[] bytes2) {
        if (encoding2 == null) {
            throw new NullPointerException("encoding is null");
        } else if (bytes2 != null) {
            this.encoding = encoding2;
            this.bytes = bytes2;
        } else {
            throw new NullPointerException("bytes is null");
        }
    }

    public Encoding getEncoding() {
        return this.encoding;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EncodedPayload)) {
            return false;
        }
        EncodedPayload that = (EncodedPayload) o;
        if (!this.encoding.equals(that.encoding)) {
            return false;
        }
        return Arrays.equals(this.bytes, that.bytes);
    }

    public int hashCode() {
        return ((1000003 ^ this.encoding.hashCode()) * 1000003) ^ Arrays.hashCode(this.bytes);
    }

    public String toString() {
        return "EncodedPayload{encoding=" + this.encoding + ", bytes=[...]}";
    }
}
