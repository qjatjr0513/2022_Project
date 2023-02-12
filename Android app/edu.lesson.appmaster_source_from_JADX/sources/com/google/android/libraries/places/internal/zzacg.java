package com.google.android.libraries.places.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzacg extends zzaak<String> implements RandomAccess, zzach {
    public static final zzach zza;
    private static final zzacg zzb;
    private final List<Object> zzc;

    static {
        zzacg zzacg = new zzacg(10);
        zzb = zzacg;
        zzacg.zzb();
        zza = zzacg;
    }

    public zzacg() {
        this(10);
    }

    private static String zzi(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzaax) {
            return ((zzaax) obj).zzm(zzaca.zza);
        }
        return zzaca.zzh((byte[]) obj);
    }

    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zza();
        this.zzc.add(i, (String) obj);
        this.modCount++;
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        zza();
        if (collection instanceof zzach) {
            collection = ((zzach) collection).zzh();
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
        return zzi(remove);
    }

    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zza();
        return zzi(this.zzc.set(i, (String) obj));
    }

    public final int size() {
        return this.zzc.size();
    }

    public final zzach zzd() {
        return zzc() ? new zzaeg(this) : this;
    }

    public final Object zze(int i) {
        return this.zzc.get(i);
    }

    public final /* bridge */ /* synthetic */ zzabz zzf(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzc);
            return new zzacg((ArrayList<Object>) arrayList);
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: zzg */
    public final String get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzaax) {
            zzaax zzaax = (zzaax) obj;
            String zzm = zzaax.zzm(zzaca.zza);
            if (zzaax.zzi()) {
                this.zzc.set(i, zzm);
            }
            return zzm;
        }
        byte[] bArr = (byte[]) obj;
        String zzh = zzaca.zzh(bArr);
        if (zzaca.zzi(bArr)) {
            this.zzc.set(i, zzh);
        }
        return zzh;
    }

    public final List<?> zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    public zzacg(int i) {
        this.zzc = new ArrayList(i);
    }

    private zzacg(ArrayList<Object> arrayList) {
        this.zzc = arrayList;
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }
}
