package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.internal.p010firebaseauthapi.zzaaz;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbh */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzbh<PrimitiveT, KeyProtoT extends zzaaz, PublicKeyProtoT extends zzaaz> extends zzap<PrimitiveT, KeyProtoT> implements zzan {
    private final zzbi<KeyProtoT, PublicKeyProtoT> zza;
    private final zzav<PublicKeyProtoT> zzb;

    public zzbh(zzbi<KeyProtoT, PublicKeyProtoT> zzbi, zzav<PublicKeyProtoT> zzav, Class<PrimitiveT> cls) {
        super(zzbi, cls);
        this.zza = zzbi;
        this.zzb = zzav;
    }

    public final zzie zze(zzyu zzyu) throws GeneralSecurityException {
        try {
            zzhd zzd = zzhd.zzd(zzyu, zzzj.zza());
            zzdg.zzl(zzd);
            zzhg zze = zzd.zze();
            this.zzb.zzi(zze);
            zzib zza2 = zzie.zza();
            zza2.zzb("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey");
            zza2.zzc(zze.zzo());
            zza2.zza(zzid.ASYMMETRIC_PUBLIC);
            return (zzie) zza2.zzk();
        } catch (zzaae e) {
            throw new GeneralSecurityException("expected serialized proto of type ", e);
        }
    }
}
