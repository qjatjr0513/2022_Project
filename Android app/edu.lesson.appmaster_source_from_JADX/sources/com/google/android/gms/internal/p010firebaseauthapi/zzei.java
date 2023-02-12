package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzei */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzei implements zzbb {
    private final zzbf<zzbb> zza;
    private final byte[] zzb = {0};

    /* synthetic */ zzei(zzbf zzbf, zzeh zzeh) {
        this.zza = zzbf;
    }

    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        if (length > 5) {
            byte[] copyOf = Arrays.copyOf(bArr, 5);
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 5, length);
            for (zzbd next : this.zza.zzd(copyOf)) {
                try {
                    if (next.zzb().equals(zzjk.LEGACY)) {
                        ((zzbb) next.zzc()).zza(copyOfRange, zzkd.zzc(bArr2, this.zzb));
                        return;
                    }
                    ((zzbb) next.zzc()).zza(copyOfRange, bArr2);
                    return;
                } catch (GeneralSecurityException e) {
                    Logger zzd = zzej.zza;
                    Level level = Level.INFO;
                    String valueOf = String.valueOf(e);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 45);
                    sb.append("tag prefix matches a key, but cannot verify: ");
                    sb.append(valueOf);
                    zzd.logp(level, "com.google.crypto.tink.mac.MacWrapper$WrappedMac", "verifyMac", sb.toString());
                }
            }
            for (zzbd zzc : this.zza.zzd(zzaj.zza)) {
                try {
                    ((zzbb) zzc.zzc()).zza(bArr, bArr2);
                    return;
                } catch (GeneralSecurityException e2) {
                }
            }
            throw new GeneralSecurityException("invalid MAC");
        }
        throw new GeneralSecurityException("tag too short");
    }
}
