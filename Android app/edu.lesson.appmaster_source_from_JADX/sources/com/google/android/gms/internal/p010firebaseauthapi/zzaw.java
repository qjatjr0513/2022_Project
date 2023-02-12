package com.google.android.gms.internal.p010firebaseauthapi;

import java.io.IOException;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaw */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzaw {
    private final zzir zza;

    private zzaw(zzir zzir) {
        this.zza = zzir;
    }

    static final zzaw zza(zzir zzir) throws GeneralSecurityException {
        zzf(zzir);
        return new zzaw(zzir);
    }

    public static void zzf(zzir zzir) throws GeneralSecurityException {
        if (zzir == null || zzir.zza() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    public static final zzaw zzi(zzdy zzdy, zzag zzag) throws GeneralSecurityException, IOException {
        zzho zza2 = zzdy.zza();
        if (zza2 == null || zza2.zzd().zzd() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        try {
            zzir zzf = zzir.zzf(zzag.zza(zza2.zzd().zzs(), new byte[0]), zzzj.zza());
            zzf(zzf);
            return new zzaw(zzf);
        } catch (zzaae e) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    public final String toString() {
        return zzbo.zza(this.zza).toString();
    }

    /* access modifiers changed from: package-private */
    public final zzir zzc() {
        return this.zza;
    }

    public final zziw zzd() {
        return zzbo.zza(this.zza);
    }

    public final <P> P zze(Class<P> cls) throws GeneralSecurityException {
        Class<?> zze = zzbn.zze(cls);
        if (zze == null) {
            String valueOf = String.valueOf(cls.getName());
            throw new GeneralSecurityException(valueOf.length() != 0 ? "No wrapper found for ".concat(valueOf) : new String("No wrapper found for "));
        }
        zzbo.zzb(this.zza);
        zzbf<P> zzb = zzbf.zzb(zze);
        for (zziq next : this.zza.zzg()) {
            if (next.zzc() == zzig.ENABLED) {
                zzbd<P> zza2 = zzb.zza(zzbn.zzg(next.zzb(), zze), next);
                if (next.zza() == this.zza.zzb()) {
                    zzb.zze(zza2);
                }
            }
        }
        return zzbn.zzj(zzb, cls);
    }

    public final void zzg(zzay zzay, zzag zzag) throws GeneralSecurityException, IOException {
        zzir zzir = this.zza;
        byte[] zzb = zzag.zzb(zzir.zzr(), new byte[0]);
        try {
            if (zzir.zzf(zzag.zza(zzb, new byte[0]), zzzj.zza()).equals(zzir)) {
                zzhn zza2 = zzho.zza();
                zza2.zza(zzyu.zzn(zzb));
                zza2.zzb(zzbo.zza(zzir));
                zzay.zzb((zzho) zza2.zzk());
                return;
            }
            throw new GeneralSecurityException("cannot encrypt keyset");
        } catch (zzaae e) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0010  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzh(com.google.android.gms.internal.p010firebaseauthapi.zzay r5) throws java.security.GeneralSecurityException, java.io.IOException {
        /*
            r4 = this;
            com.google.android.gms.internal.firebase-auth-api.zzir r0 = r4.zza
            java.util.List r0 = r0.zzg()
            java.util.Iterator r0 = r0.iterator()
        L_0x000a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0064
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.firebase-auth-api.zziq r1 = (com.google.android.gms.internal.p010firebaseauthapi.zziq) r1
            com.google.android.gms.internal.firebase-auth-api.zzie r2 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzid r2 = r2.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzid r3 = com.google.android.gms.internal.p010firebaseauthapi.zzid.UNKNOWN_KEYMATERIAL
            if (r2 == r3) goto L_0x003b
            com.google.android.gms.internal.firebase-auth-api.zzie r2 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzid r2 = r2.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzid r3 = com.google.android.gms.internal.p010firebaseauthapi.zzid.SYMMETRIC
            if (r2 == r3) goto L_0x003b
            com.google.android.gms.internal.firebase-auth-api.zzie r2 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzid r2 = r2.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzid r3 = com.google.android.gms.internal.p010firebaseauthapi.zzid.ASYMMETRIC_PRIVATE
            if (r2 == r3) goto L_0x003b
            goto L_0x000a
        L_0x003b:
            java.security.GeneralSecurityException r5 = new java.security.GeneralSecurityException
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r2 = 0
            com.google.android.gms.internal.firebase-auth-api.zzie r3 = r1.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzid r3 = r3.zzb()
            java.lang.String r3 = r3.name()
            r0[r2] = r3
            r2 = 1
            com.google.android.gms.internal.firebase-auth-api.zzie r1 = r1.zzb()
            java.lang.String r1 = r1.zzf()
            r0[r2] = r1
            java.lang.String r1 = "keyset contains key material of type %s for type url %s"
            java.lang.String r0 = java.lang.String.format(r1, r0)
            r5.<init>(r0)
            throw r5
        L_0x0064:
            com.google.android.gms.internal.firebase-auth-api.zzir r0 = r4.zza
            r5.zzc(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p010firebaseauthapi.zzaw.zzh(com.google.android.gms.internal.firebase-auth-api.zzay):void");
    }

    public final zzaw zzb() throws GeneralSecurityException {
        if (this.zza != null) {
            zzio zzc = zzir.zzc();
            for (zziq next : this.zza.zzg()) {
                zzie zzb = next.zzb();
                if (zzb.zzb() == zzid.ASYMMETRIC_PRIVATE) {
                    zzie zzb2 = zzbn.zzb(zzb.zzf(), zzb.zze());
                    zzbn.zzf(zzb2);
                    zzip zzd = zziq.zzd();
                    zzd.zzj(next);
                    zzd.zza(zzb2);
                    zzc.zzb((zziq) zzd.zzk());
                } else {
                    throw new GeneralSecurityException("The keyset contains a non-private key");
                }
            }
            zzc.zzc(this.zza.zzb());
            return new zzaw((zzir) zzc.zzk());
        }
        throw new GeneralSecurityException("cleartext keyset is not available");
    }
}
