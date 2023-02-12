package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjt;
import com.google.android.gms.internal.measurement.zzjx;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
public abstract class zzjx<MessageType extends zzjx<MessageType, BuilderType>, BuilderType extends zzjt<MessageType, BuilderType>> extends zzih<MessageType, BuilderType> {
    private static final Map<Object, zzjx<?, ?>> zza = new ConcurrentHashMap();
    protected zzmc zzc = zzmc.zzc();
    protected int zzd = -1;

    protected static <E> zzke<E> zzbA() {
        return zzll.zze();
    }

    protected static <E> zzke<E> zzbB(zzke<E> zzke) {
        int i;
        int size = zzke.size();
        if (size == 0) {
            i = 10;
        } else {
            i = size + size;
        }
        return zzke.zzd(i);
    }

    static Object zzbE(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static Object zzbF(zzlc zzlc, String str, Object[] objArr) {
        return new zzlm(zzlc, str, objArr);
    }

    protected static <T extends zzjx> void zzbG(Class<T> cls, T t) {
        zza.put(cls, t);
    }

    static <T extends zzjx> T zzbw(Class<T> cls) {
        Map<Object, zzjx<?, ?>> map = zza;
        T t = (zzjx) map.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzjx) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzjx) ((zzjx) zzml.zze(cls)).zzl(6, (Object) null, (Object) null);
            if (t != null) {
                map.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static zzkc zzbx() {
        return zzjy.zzf();
    }

    protected static zzkd zzby() {
        return zzkr.zzf();
    }

    protected static zzkd zzbz(zzkd zzkd) {
        int i;
        int size = zzkd.size();
        if (size == 0) {
            i = 10;
        } else {
            i = size + size;
        }
        return zzkd.zze(i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzlk.zza().zzb(getClass()).zzi(this, (zzjx) obj);
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        if (i != 0) {
            return i;
        }
        int zzb = zzlk.zza().zzb(getClass()).zzb(this);
        this.zzb = zzb;
        return zzb;
    }

    public final String toString() {
        return zzle.zza(this, super.toString());
    }

    public final /* synthetic */ zzlb zzbC() {
        return (zzjt) zzl(5, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzlb zzbD() {
        zzjt zzjt = (zzjt) zzl(5, (Object) null, (Object) null);
        zzjt.zzay(this);
        return zzjt;
    }

    public final void zzbH(zzje zzje) throws IOException {
        zzlk.zza().zzb(getClass()).zzm(this, zzjf.zza(zzje));
    }

    public final /* synthetic */ zzlc zzbL() {
        return (zzjx) zzl(6, (Object) null, (Object) null);
    }

    /* access modifiers changed from: package-private */
    public final int zzbo() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final void zzbr(int i) {
        this.zzd = i;
    }

    public final int zzbt() {
        int i = this.zzd;
        if (i != -1) {
            return i;
        }
        int zza2 = zzlk.zza().zzb(getClass()).zza(this);
        this.zzd = zza2;
        return zza2;
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends zzjx<MessageType, BuilderType>, BuilderType extends zzjt<MessageType, BuilderType>> BuilderType zzbu() {
        return (zzjt) zzl(5, (Object) null, (Object) null);
    }

    public final BuilderType zzbv() {
        BuilderType buildertype = (zzjt) zzl(5, (Object) null, (Object) null);
        buildertype.zzay(this);
        return buildertype;
    }

    /* access modifiers changed from: protected */
    public abstract Object zzl(int i, Object obj, Object obj2);
}
