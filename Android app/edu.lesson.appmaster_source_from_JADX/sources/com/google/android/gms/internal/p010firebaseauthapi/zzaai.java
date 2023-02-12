package com.google.android.gms.internal.p010firebaseauthapi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaai */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzaai extends zzyf<String> implements RandomAccess, zzaaj {
    public static final zzaaj zza;
    private static final zzaai zzb;
    private final List<Object> zzc;

    static {
        zzaai zzaai = new zzaai(10);
        zzb = zzaai;
        zzaai.zzb();
        zza = zzaai;
    }

    public zzaai() {
        this(10);
    }

    private static String zzj(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzyu) {
            return ((zzyu) obj).zzr(zzaac.zza);
        }
        return zzaac.zzh((byte[]) obj);
    }

    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zza();
        this.zzc.add(i, (String) obj);
        this.modCount++;
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        zza();
        if (collection instanceof zzaaj) {
            collection = ((zzaaj) collection).zzh();
        }
        boolean addAll = this.zzc.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final void clear() {
        zza();
        this.zzc.clear();
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zza();
        Object remove = this.zzc.remove(i);
        this.modCount++;
        return zzj(remove);
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zza();
        return zzj(this.zzc.set(i, (String) obj));
    }

    public final int size() {
        return this.zzc.size();
    }

    public final /* bridge */ /* synthetic */ zzaab zzd(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzc);
            return new zzaai((ArrayList<Object>) arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final zzaaj zze() {
        return zzc() ? new zzace(this) : this;
    }

    public final Object zzf(int i) {
        return this.zzc.get(i);
    }

    /* renamed from: zzg */
    public final String get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzyu) {
            zzyu zzyu = (zzyu) obj;
            String zzr = zzyu.zzr(zzaac.zza);
            if (zzyu.zzk()) {
                this.zzc.set(i, zzr);
            }
            return zzr;
        }
        byte[] bArr = (byte[]) obj;
        String zzh = zzaac.zzh(bArr);
        if (zzaac.zzi(bArr)) {
            this.zzc.set(i, zzh);
        }
        return zzh;
    }

    public final List<?> zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    public final void zzi(zzyu zzyu) {
        zza();
        this.zzc.add(zzyu);
        this.modCount++;
    }

    public zzaai(int i) {
        this.zzc = new ArrayList(i);
    }

    private zzaai(ArrayList<Object> arrayList) {
        this.zzc = arrayList;
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }
}
