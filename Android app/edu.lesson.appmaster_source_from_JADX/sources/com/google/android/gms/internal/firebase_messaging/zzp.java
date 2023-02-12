package com.google.android.gms.internal.firebase_messaging;

import java.io.OutputStream;

/* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
final class zzp extends OutputStream {
    private long zza = 0;

    zzp() {
    }

    public final void write(int i) {
        this.zza++;
    }

    public final void write(byte[] bArr) {
        this.zza += (long) bArr.length;
    }

    /* access modifiers changed from: package-private */
    public final long zza() {
        return this.zza;
    }

    public final void write(byte[] bArr, int i, int i2) {
        int length;
        int i3;
        if (i < 0 || i > (length = bArr.length) || i2 < 0 || (i3 = i + i2) > length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        this.zza += (long) i2;
    }
}
