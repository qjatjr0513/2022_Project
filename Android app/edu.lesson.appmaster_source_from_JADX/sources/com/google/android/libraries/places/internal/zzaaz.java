package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzaaz extends zzabb {
    private final byte[] zzb;
    private int zzc;
    private int zzd;
    private int zze = Integer.MAX_VALUE;

    /* synthetic */ zzaaz(byte[] bArr, int i, int i2, boolean z, zzaay zzaay) {
        super((zzaba) null);
        this.zzb = bArr;
        this.zzc = 0;
    }

    public final int zza(int i) throws zzacc {
        int i2 = this.zze;
        this.zze = 0;
        int i3 = this.zzc + this.zzd;
        this.zzc = i3;
        if (i3 > 0) {
            this.zzd = i3;
            this.zzc = 0;
        } else {
            this.zzd = 0;
        }
        return i2;
    }
}
