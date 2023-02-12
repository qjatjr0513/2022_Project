package com.google.android.gms.internal.p010firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdq */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzdq implements zzki {
    private final String zza;
    private final int zzb;
    private zzfu zzc;
    private zzew zzd;
    private int zze;
    private zzgg zzf;

    zzdq(zzij zzij) throws GeneralSecurityException {
        String zzf2 = zzij.zzf();
        this.zza = zzf2;
        if (zzf2.equals(zzbp.zzb)) {
            try {
                zzfx zzd2 = zzfx.zzd(zzij.zze(), zzzj.zza());
                this.zzc = (zzfu) zzbn.zzd(zzij);
                this.zzb = zzd2.zza();
            } catch (zzaae e) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e);
            }
        } else if (zzf2.equals(zzbp.zza)) {
            try {
                zzez zzc2 = zzez.zzc(zzij.zze(), zzzj.zza());
                this.zzd = (zzew) zzbn.zzd(zzij);
                this.zze = zzc2.zzd().zza();
                this.zzb = this.zze + zzc2.zze().zza();
            } catch (zzaae e2) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e2);
            }
        } else if (zzf2.equals(zzdb.zza)) {
            try {
                zzgj zzd3 = zzgj.zzd(zzij.zze(), zzzj.zza());
                this.zzf = (zzgg) zzbn.zzd(zzij);
                this.zzb = zzd3.zza();
            } catch (zzaae e3) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e3);
            }
        } else {
            String valueOf = String.valueOf(zzf2);
            throw new GeneralSecurityException(valueOf.length() != 0 ? "unsupported AEAD DEM key type: ".concat(valueOf) : new String("unsupported AEAD DEM key type: "));
        }
    }

    public final int zza() {
        return this.zzb;
    }

    public final zzdr zzb(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length != this.zzb) {
            throw new GeneralSecurityException("Symmetric key has incorrect length");
        } else if (this.zza.equals(zzbp.zzb)) {
            zzft zzb2 = zzfu.zzb();
            zzb2.zzj(this.zzc);
            zzb2.zza(zzyu.zzo(bArr, 0, this.zzb));
            return new zzdr((zzag) zzbn.zzh(this.zza, (zzfu) zzb2.zzk(), zzag.class));
        } else if (this.zza.equals(zzbp.zza)) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, this.zze);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, this.zze, this.zzb);
            zzfb zzb3 = zzfc.zzb();
            zzb3.zzj(this.zzd.zze());
            zzb3.zza(zzyu.zzn(copyOfRange));
            zzhs zzb4 = zzht.zzb();
            zzb4.zzj(this.zzd.zzf());
            zzb4.zza(zzyu.zzn(copyOfRange2));
            zzev zzb5 = zzew.zzb();
            zzb5.zzc(this.zzd.zza());
            zzb5.zza((zzfc) zzb3.zzk());
            zzb5.zzb((zzht) zzb4.zzk());
            return new zzdr((zzag) zzbn.zzh(this.zza, (zzew) zzb5.zzk(), zzag.class));
        } else if (this.zza.equals(zzdb.zza)) {
            zzgf zzb6 = zzgg.zzb();
            zzb6.zzj(this.zzf);
            zzb6.zza(zzyu.zzo(bArr, 0, this.zzb));
            return new zzdr((zzak) zzbn.zzh(this.zza, (zzgg) zzb6.zzk(), zzak.class));
        } else {
            throw new GeneralSecurityException("unknown DEM key type");
        }
    }
}
