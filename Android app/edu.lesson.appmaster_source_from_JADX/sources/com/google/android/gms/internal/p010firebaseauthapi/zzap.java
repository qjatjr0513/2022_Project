package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.internal.p010firebaseauthapi.zzaaz;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzap */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
class zzap<PrimitiveT, KeyProtoT extends zzaaz> implements zzan<PrimitiveT> {
    private final zzav<KeyProtoT> zza;
    private final Class<PrimitiveT> zzb;

    public zzap(zzav<KeyProtoT> zzav, Class<PrimitiveT> cls) {
        if (zzav.zzh().contains(cls) || Void.class.equals(cls)) {
            this.zza = zzav;
            this.zzb = cls;
            return;
        }
        throw new IllegalArgumentException(String.format("Given internalKeyMananger %s does not support primitive class %s", new Object[]{zzav.toString(), cls.getName()}));
    }

    private final zzao<?, KeyProtoT> zze() {
        return new zzao<>(this.zza.zza());
    }

    private final PrimitiveT zzf(KeyProtoT keyprotot) throws GeneralSecurityException {
        if (!Void.class.equals(this.zzb)) {
            this.zza.zzi(keyprotot);
            return this.zza.zzf(keyprotot, this.zzb);
        }
        throw new GeneralSecurityException("Cannot create a primitive for Void");
    }

    public final zzie zza(zzyu zzyu) throws GeneralSecurityException {
        try {
            zzaaz zza2 = zze().zza(zzyu);
            zzib zza3 = zzie.zza();
            zza3.zzb(this.zza.zzg());
            zza3.zzc(zza2.zzo());
            zza3.zza(this.zza.zzb());
            return (zzie) zza3.zzk();
        } catch (zzaae e) {
            throw new GeneralSecurityException("Unexpected proto", e);
        }
    }

    public final zzaaz zzb(zzyu zzyu) throws GeneralSecurityException {
        try {
            return zze().zza(zzyu);
        } catch (zzaae e) {
            String valueOf = String.valueOf(this.zza.zza().zzb().getName());
            throw new GeneralSecurityException(valueOf.length() != 0 ? "Failures parsing proto of type ".concat(valueOf) : new String("Failures parsing proto of type "), e);
        }
    }

    public final PrimitiveT zzc(zzyu zzyu) throws GeneralSecurityException {
        try {
            return zzf(this.zza.zzc(zzyu));
        } catch (zzaae e) {
            String valueOf = String.valueOf(this.zza.zze().getName());
            throw new GeneralSecurityException(valueOf.length() != 0 ? "Failures parsing proto of type ".concat(valueOf) : new String("Failures parsing proto of type "), e);
        }
    }

    public final PrimitiveT zzd(zzaaz zzaaz) throws GeneralSecurityException {
        String valueOf = String.valueOf(this.zza.zze().getName());
        String concat = valueOf.length() != 0 ? "Expected proto of type ".concat(valueOf) : new String("Expected proto of type ");
        if (this.zza.zze().isInstance(zzaaz)) {
            return zzf(zzaaz);
        }
        throw new GeneralSecurityException(concat);
    }
}
