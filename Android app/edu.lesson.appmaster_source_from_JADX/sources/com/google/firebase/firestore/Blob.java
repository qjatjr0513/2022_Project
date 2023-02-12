package com.google.firebase.firestore;

import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.ByteString;

public class Blob implements Comparable<Blob> {
    private final ByteString bytes;

    private Blob(ByteString bytes2) {
        this.bytes = bytes2;
    }

    public static Blob fromBytes(byte[] bytes2) {
        Preconditions.checkNotNull(bytes2, "Provided bytes array must not be null.");
        return new Blob(ByteString.copyFrom(bytes2));
    }

    public static Blob fromByteString(ByteString bytes2) {
        Preconditions.checkNotNull(bytes2, "Provided ByteString must not be null.");
        return new Blob(bytes2);
    }

    public byte[] toBytes() {
        return this.bytes.toByteArray();
    }

    public String toString() {
        return "Blob { bytes=" + Util.toDebugString(this.bytes) + " }";
    }

    public ByteString toByteString() {
        return this.bytes;
    }

    public boolean equals(Object other) {
        return (other instanceof Blob) && this.bytes.equals(((Blob) other).bytes);
    }

    public int hashCode() {
        return this.bytes.hashCode();
    }

    public int compareTo(Blob other) {
        return Util.compareByteStrings(this.bytes, other.bytes);
    }
}
