package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;

@Immutable
final class MacHashFunction extends AbstractHashFunction {
    private final int bits;
    private final Key key;
    private final Mac prototype;
    private final boolean supportsClone;
    private final String toString;

    MacHashFunction(String algorithmName, Key key2, String toString2) {
        Mac mac = getMac(algorithmName, key2);
        this.prototype = mac;
        this.key = (Key) Preconditions.checkNotNull(key2);
        this.toString = (String) Preconditions.checkNotNull(toString2);
        this.bits = mac.getMacLength() * 8;
        this.supportsClone = supportsClone(mac);
    }

    public int bits() {
        return this.bits;
    }

    private static boolean supportsClone(Mac mac) {
        try {
            mac.clone();
            return true;
        } catch (CloneNotSupportedException e) {
            return false;
        }
    }

    private static Mac getMac(String algorithmName, Key key2) {
        try {
            Mac mac = Mac.getInstance(algorithmName);
            mac.init(key2);
            return mac;
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        } catch (InvalidKeyException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public Hasher newHasher() {
        if (this.supportsClone) {
            try {
                return new MacHasher((Mac) this.prototype.clone());
            } catch (CloneNotSupportedException e) {
            }
        }
        return new MacHasher(getMac(this.prototype.getAlgorithm(), this.key));
    }

    public String toString() {
        return this.toString;
    }

    private static final class MacHasher extends AbstractByteHasher {
        private boolean done;
        private final Mac mac;

        private MacHasher(Mac mac2) {
            this.mac = mac2;
        }

        /* access modifiers changed from: protected */
        public void update(byte b) {
            checkNotDone();
            this.mac.update(b);
        }

        /* access modifiers changed from: protected */
        public void update(byte[] b) {
            checkNotDone();
            this.mac.update(b);
        }

        /* access modifiers changed from: protected */
        public void update(byte[] b, int off, int len) {
            checkNotDone();
            this.mac.update(b, off, len);
        }

        /* access modifiers changed from: protected */
        public void update(ByteBuffer bytes) {
            checkNotDone();
            Preconditions.checkNotNull(bytes);
            this.mac.update(bytes);
        }

        private void checkNotDone() {
            Preconditions.checkState(!this.done, "Cannot re-use a Hasher after calling hash() on it");
        }

        public HashCode hash() {
            checkNotDone();
            this.done = true;
            return HashCode.fromBytesNoCopy(this.mac.doFinal());
        }
    }
}
