package com.google.android.gms.internal.places;

final class zzee extends IllegalArgumentException {
    zzee(int i, int i2) {
        super(new StringBuilder(54).append("Unpaired surrogate at index ").append(i).append(" of ").append(i2).toString());
    }
}
