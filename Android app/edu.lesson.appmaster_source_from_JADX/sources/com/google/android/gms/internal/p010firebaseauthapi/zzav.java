package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.internal.p010firebaseauthapi.zzaaz;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzav */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public abstract class zzav<KeyProtoT extends zzaaz> {
    private final Class<KeyProtoT> zza;
    private final Map<Class<?>, zzau<?, KeyProtoT>> zzb;
    private final Class<?> zzc;

    @SafeVarargs
    protected zzav(Class<KeyProtoT> cls, zzau<?, KeyProtoT>... zzauArr) {
        this.zza = cls;
        HashMap hashMap = new HashMap();
        int i = 0;
        while (i <= 0) {
            zzau<?, KeyProtoT> zzau = zzauArr[i];
            if (hashMap.containsKey(zzau.zza())) {
                String valueOf = String.valueOf(zzau.zza().getCanonicalName());
                throw new IllegalArgumentException(valueOf.length() != 0 ? "KeyTypeManager constructed with duplicate factories for primitive ".concat(valueOf) : new String("KeyTypeManager constructed with duplicate factories for primitive "));
            } else {
                hashMap.put(zzau.zza(), zzau);
                i++;
            }
        }
        this.zzc = zzauArr[0].zza();
        this.zzb = Collections.unmodifiableMap(hashMap);
    }

    public zzat<?, KeyProtoT> zza() {
        throw new UnsupportedOperationException("Creating keys is not supported.");
    }

    public abstract zzid zzb();

    public abstract KeyProtoT zzc(zzyu zzyu) throws zzaae;

    /* access modifiers changed from: package-private */
    public final Class<?> zzd() {
        return this.zzc;
    }

    public final Class<KeyProtoT> zze() {
        return this.zza;
    }

    public final <P> P zzf(KeyProtoT keyprotot, Class<P> cls) throws GeneralSecurityException {
        zzau zzau = this.zzb.get(cls);
        if (zzau != null) {
            return zzau.zzb(keyprotot);
        }
        String canonicalName = cls.getCanonicalName();
        StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 41);
        sb.append("Requested primitive class ");
        sb.append(canonicalName);
        sb.append(" not supported.");
        throw new IllegalArgumentException(sb.toString());
    }

    public abstract String zzg();

    public final Set<Class<?>> zzh() {
        return this.zzb.keySet();
    }

    public abstract void zzi(KeyProtoT keyprotot) throws GeneralSecurityException;
}
