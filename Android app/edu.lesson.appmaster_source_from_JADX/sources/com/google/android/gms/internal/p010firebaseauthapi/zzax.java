package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.common.base.Ascii;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Iterator;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzax */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzax {
    private final zzio zza;

    private zzax(zzio zzio) {
        this.zza = zzio;
    }

    public static zzax zze() {
        return new zzax(zzir.zzc());
    }

    public static zzax zzf(zzaw zzaw) {
        return new zzax((zzio) zzaw.zzc().zzu());
    }

    private final synchronized int zzg() {
        int zzh;
        zzh = zzh();
        while (zzk(zzh)) {
            zzh = zzh();
        }
        return zzh;
    }

    private static int zzh() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bArr = new byte[4];
        byte b = 0;
        while (b == 0) {
            secureRandom.nextBytes(bArr);
            b = ((bArr[0] & Ascii.DEL) << Ascii.CAN) | ((bArr[1] & 255) << Ascii.DLE) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        }
        return b;
    }

    private final synchronized zziq zzi(zzie zzie, zzjk zzjk) throws GeneralSecurityException {
        zzip zzd;
        int zzg = zzg();
        if (zzjk != zzjk.UNKNOWN_PREFIX) {
            zzd = zziq.zzd();
            zzd.zza(zzie);
            zzd.zzb(zzg);
            zzd.zzd(zzig.ENABLED);
            zzd.zzc(zzjk);
        } else {
            throw new GeneralSecurityException("unknown output prefix type");
        }
        return (zziq) zzd.zzk();
    }

    private final synchronized zziq zzj(zzij zzij) throws GeneralSecurityException {
        return zzi(zzbn.zzc(zzij), zzij.zzd());
    }

    private final synchronized boolean zzk(int i) {
        boolean z;
        Iterator<zziq> it = this.zza.zze().iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().zza() == i) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        return z;
    }

    @Deprecated
    public final synchronized int zza(zzij zzij, boolean z) throws GeneralSecurityException {
        zziq zzj;
        zzj = zzj(zzij);
        this.zza.zzb(zzj);
        return zzj.zza();
    }

    public final synchronized zzaw zzb() throws GeneralSecurityException {
        return zzaw.zza((zzir) this.zza.zzk());
    }

    public final synchronized zzax zzc(zzaq zzaq) throws GeneralSecurityException {
        zza(zzaq.zza(), false);
        return this;
    }

    public final synchronized zzax zzd(int i) throws GeneralSecurityException {
        int i2 = 0;
        while (i2 < this.zza.zza()) {
            zziq zzd = this.zza.zzd(i2);
            if (zzd.zza() != i) {
                i2++;
            } else if (zzd.zzc().equals(zzig.ENABLED)) {
                this.zza.zzc(i);
            } else {
                StringBuilder sb = new StringBuilder(63);
                sb.append("cannot set key as primary because it's not enabled: ");
                sb.append(i);
                throw new GeneralSecurityException(sb.toString());
            }
        }
        StringBuilder sb2 = new StringBuilder(26);
        sb2.append("key not found: ");
        sb2.append(i);
        throw new GeneralSecurityException(sb2.toString());
        return this;
    }
}
