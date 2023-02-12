package com.google.android.gms.internal.p010firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbf */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzbf<P> {
    private final ConcurrentMap<zzbe, List<zzbd<P>>> zza = new ConcurrentHashMap();
    private zzbd<P> zzb;
    private final Class<P> zzc;

    private zzbf(Class<P> cls) {
        this.zzc = cls;
    }

    public static <P> zzbf<P> zzb(Class<P> cls) {
        return new zzbf<>(cls);
    }

    public final zzbd<P> zza(P p, zziq zziq) throws GeneralSecurityException {
        byte[] bArr;
        if (zziq.zzc() == zzig.ENABLED) {
            zzjk zzjk = zzjk.UNKNOWN_PREFIX;
            switch (zziq.zzf().ordinal()) {
                case 1:
                    bArr = ByteBuffer.allocate(5).put((byte) 1).putInt(zziq.zza()).array();
                    break;
                case 2:
                case 4:
                    bArr = ByteBuffer.allocate(5).put((byte) 0).putInt(zziq.zza()).array();
                    break;
                case 3:
                    bArr = zzaj.zza;
                    break;
                default:
                    throw new GeneralSecurityException("unknown output prefix type");
            }
            zzbd zzbd = new zzbd(p, bArr, zziq.zzc(), zziq.zzf(), zziq.zza());
            ArrayList arrayList = new ArrayList();
            arrayList.add(zzbd);
            zzbe zzbe = new zzbe(zzbd.zzd(), (zzbc) null);
            List list = (List) this.zza.put(zzbe, Collections.unmodifiableList(arrayList));
            if (list != null) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(list);
                arrayList2.add(zzbd);
                this.zza.put(zzbe, Collections.unmodifiableList(arrayList2));
            }
            return zzbd;
        }
        throw new GeneralSecurityException("only ENABLED key is allowed");
    }

    public final Class<P> zzc() {
        return this.zzc;
    }

    public final List<zzbd<P>> zzd(byte[] bArr) {
        List<zzbd<P>> list = (List) this.zza.get(new zzbe(bArr, (zzbc) null));
        if (list != null) {
            return list;
        }
        return Collections.emptyList();
    }

    public final void zze(zzbd<P> zzbd) {
        if (zzbd.zza() != zzig.ENABLED) {
            throw new IllegalArgumentException("the primary entry has to be ENABLED");
        } else if (!zzd(zzbd.zzd()).isEmpty()) {
            this.zzb = zzbd;
        } else {
            throw new IllegalArgumentException("the primary entry cannot be set to an entry which is not held by this primitive set");
        }
    }
}
