package com.google.android.libraries.places.internal;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public abstract class zzgg<K, V> implements Map<K, V>, Serializable {
    @CheckForNull
    private transient zzgh<Map.Entry<K, V>> zza;
    @CheckForNull
    private transient zzgh<K> zzb;
    @CheckForNull
    private transient zzgb<V> zzc;

    zzgg() {
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public final boolean containsKey(@CheckForNull Object obj) {
        return get(obj) != null;
    }

    public final boolean containsValue(@CheckForNull Object obj) {
        return values().contains(obj);
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        return entrySet().equals(((Map) obj).entrySet());
    }

    @CheckForNull
    public abstract V get(@CheckForNull Object obj);

    @CheckForNull
    public final V getOrDefault(@CheckForNull Object obj, @CheckForNull V v) {
        V v2 = get(obj);
        return v2 != null ? v2 : v;
    }

    public final int hashCode() {
        return zzgx.zza(entrySet());
    }

    public final boolean isEmpty() {
        return size() == 0;
    }

    public final /* bridge */ /* synthetic */ Set keySet() {
        zzgh<K> zzgh = this.zzb;
        if (zzgh != null) {
            return zzgh;
        }
        zzgh<K> zzd = zzd();
        this.zzb = zzd;
        return zzd;
    }

    @CheckForNull
    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @CheckForNull
    @Deprecated
    public final V remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        if (size >= 0) {
            StringBuilder sb = new StringBuilder((int) Math.min(((long) size) * 8, 1073741824));
            sb.append('{');
            boolean z = true;
            for (Map.Entry entry : entrySet()) {
                if (!z) {
                    sb.append(", ");
                }
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
                z = false;
            }
            sb.append('}');
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder(44);
        sb2.append("size cannot be negative but was: ");
        sb2.append(size);
        throw new IllegalArgumentException(sb2.toString());
    }

    /* access modifiers changed from: package-private */
    public abstract zzgb<V> zza();

    /* renamed from: zzb */
    public final zzgb<V> values() {
        zzgb<V> zzgb = this.zzc;
        if (zzgb != null) {
            return zzgb;
        }
        zzgb<V> zza2 = zza();
        this.zzc = zza2;
        return zza2;
    }

    /* access modifiers changed from: package-private */
    public abstract zzgh<Map.Entry<K, V>> zzc();

    /* access modifiers changed from: package-private */
    public abstract zzgh<K> zzd();

    /* renamed from: zze */
    public final zzgh<Map.Entry<K, V>> entrySet() {
        zzgh<Map.Entry<K, V>> zzgh = this.zza;
        if (zzgh != null) {
            return zzgh;
        }
        zzgh<Map.Entry<K, V>> zzc2 = zzc();
        this.zza = zzc2;
        return zzc2;
    }
}
