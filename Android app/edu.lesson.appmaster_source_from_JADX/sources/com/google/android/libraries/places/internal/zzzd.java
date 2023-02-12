package com.google.android.libraries.places.internal;

import java.math.RoundingMode;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final /* synthetic */ class zzzd {
    static final /* synthetic */ int[] zza;

    static {
        int[] iArr = new int[RoundingMode.values().length];
        zza = iArr;
        try {
            iArr[RoundingMode.UNNECESSARY.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            zza[RoundingMode.DOWN.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            zza[RoundingMode.FLOOR.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            zza[RoundingMode.UP.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            zza[RoundingMode.CEILING.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            zza[RoundingMode.HALF_DOWN.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            zza[RoundingMode.HALF_UP.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
        try {
            zza[RoundingMode.HALF_EVEN.ordinal()] = 8;
        } catch (NoSuchFieldError e8) {
        }
    }
}
