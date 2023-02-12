package com.google.android.gms.internal.p010firebaseauthapi;

import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyx */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public abstract class zzyx {
    int zza;
    zzyy zzb;

    /* synthetic */ zzyx(zzyv zzyv) {
    }

    public static int zzs(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long zzt(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    static zzyx zzu(byte[] bArr, int i, int i2, boolean z) {
        zzyw zzyw = new zzyw(bArr, 0, i2, z, (zzyv) null);
        try {
            zzyw.zzc(i2);
            return zzyw;
        } catch (zzaae e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract int zzb();

    public abstract int zzc(int i) throws zzaae;

    public abstract int zzf() throws IOException;

    public abstract zzyu zzj() throws IOException;

    public abstract String zzk() throws IOException;

    public abstract String zzl() throws IOException;

    public abstract void zzm(int i) throws zzaae;

    public abstract void zzn(int i);

    public abstract boolean zzp() throws IOException;

    public abstract boolean zzq() throws IOException;

    public abstract boolean zzr(int i) throws IOException;
}
