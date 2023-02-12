package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.internal.p010firebaseauthapi.zzzs;
import com.google.android.gms.internal.p010firebaseauthapi.zzzw;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzw */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public abstract class zzzw<MessageType extends zzzw<MessageType, BuilderType>, BuilderType extends zzzs<MessageType, BuilderType>> extends zzye<MessageType, BuilderType> {
    private static final Map<Object, zzzw<?, ?>> zzb = new ConcurrentHashMap();
    protected zzaca zzc = zzaca.zzc();
    protected int zzd = -1;

    protected static <E> zzaab<E> zzA(zzaab<E> zzaab) {
        int i;
        int size = zzaab.size();
        if (size == 0) {
            i = 10;
        } else {
            i = size + size;
        }
        return zzaab.zzd(i);
    }

    static Object zzD(Method method, Object obj, Object... objArr) {
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

    protected static Object zzE(zzaaz zzaaz, String str, Object[] objArr) {
        return new zzabj(zzaaz, str, objArr);
    }

    protected static <T extends zzzw> void zzF(Class<T> cls, T t) {
        zzb.put(cls, t);
    }

    private static <T extends zzzw<T, ?>> T zza(T t) throws zzaae {
        if (t == null || t.zzH()) {
            return t;
        }
        zzaae zzaae = new zzaae(new zzaby(t).getMessage());
        zzaae.zzh(t);
        throw zzaae;
    }

    static <T extends zzzw> T zzv(Class<T> cls) {
        Map<Object, zzzw<?, ?>> map = zzb;
        T t = (zzzw) map.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzzw) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzzw) ((zzzw) zzacj.zze(cls)).zzj(6, (Object) null, (Object) null);
            if (t != null) {
                map.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static <T extends zzzw<T, ?>> T zzw(T t, zzyu zzyu, zzzj zzzj) throws zzaae {
        T t2;
        try {
            zzyx zzh = zzyu.zzh();
            t2 = (zzzw) t.zzj(4, (Object) null, (Object) null);
            zzabl<?> zzb2 = zzabh.zza().zzb(t2.getClass());
            zzb2.zzh(t2, zzyy.zzq(zzh), zzzj);
            zzb2.zzf(t2);
            zzh.zzm(0);
            zza(t2);
            return t2;
        } catch (zzaae e) {
            e.zzh(t2);
            throw e;
        } catch (IOException e2) {
            if (e2.getCause() instanceof zzaae) {
                throw ((zzaae) e2.getCause());
            }
            zzaae zzaae = new zzaae(e2);
            zzaae.zzh(t2);
            throw zzaae;
        } catch (RuntimeException e3) {
            if (e3.getCause() instanceof zzaae) {
                throw ((zzaae) e3.getCause());
            }
            throw e3;
        } catch (zzaae e4) {
            e4.zzh(t2);
            throw e4;
        } catch (zzaae e5) {
            throw e5;
        }
    }

    protected static <T extends zzzw<T, ?>> T zzx(T t, byte[] bArr, zzzj zzzj) throws zzaae {
        T zzy = zzy(t, bArr, 0, bArr.length, zzzj);
        zza(zzy);
        return zzy;
    }

    static <T extends zzzw<T, ?>> T zzy(T t, byte[] bArr, int i, int i2, zzzj zzzj) throws zzaae {
        T t2 = (zzzw) t.zzj(4, (Object) null, (Object) null);
        try {
            zzabl<?> zzb2 = zzabh.zza().zzb(t2.getClass());
            zzb2.zzi(t2, bArr, 0, i2, new zzyh(zzzj));
            zzb2.zzf(t2);
            if (t2.zza == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (zzaae e) {
            e.zzh(t2);
            throw e;
        } catch (IOException e2) {
            if (e2.getCause() instanceof zzaae) {
                throw ((zzaae) e2.getCause());
            }
            zzaae zzaae = new zzaae(e2);
            zzaae.zzh(t2);
            throw zzaae;
        } catch (IndexOutOfBoundsException e3) {
            zzaae zzi = zzaae.zzi();
            zzi.zzh(t2);
            throw zzi;
        }
    }

    protected static <E> zzaab<E> zzz() {
        return zzabi.zze();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zzabh.zza().zzb(getClass()).zzj(this, (zzzw) obj);
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int zzb2 = zzabh.zza().zzb(getClass()).zzb(this);
        this.zza = zzb2;
        return zzb2;
    }

    public final String toString() {
        return zzabb.zza(this, super.toString());
    }

    public final /* bridge */ /* synthetic */ zzaay zzB() {
        return (zzzs) zzj(5, (Object) null, (Object) null);
    }

    public final /* bridge */ /* synthetic */ zzaay zzC() {
        zzzs zzzs = (zzzs) zzj(5, (Object) null, (Object) null);
        zzzs.zzj(this);
        return zzzs;
    }

    public final void zzG(zzze zzze) throws IOException {
        zzabh.zza().zzb(getClass()).zzn(this, zzzf.zza(zzze));
    }

    public final boolean zzH() {
        zzzw zzzw;
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) zzj(1, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzk = zzabh.zza().zzb(getClass()).zzk(this);
        if (!booleanValue) {
            return zzk;
        }
        if (true != zzk) {
            zzzw = null;
        } else {
            zzzw = this;
        }
        zzj(2, zzzw, (Object) null);
        return zzk;
    }

    public final /* bridge */ /* synthetic */ zzaaz zzI() {
        return (zzzw) zzj(6, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public abstract Object zzj(int i, Object obj, Object obj2);

    /* access modifiers changed from: package-private */
    public final int zzn() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final void zzp(int i) {
        this.zzd = i;
    }

    public final int zzs() {
        int i = this.zzd;
        if (i != -1) {
            return i;
        }
        int zza = zzabh.zza().zzb(getClass()).zza(this);
        this.zzd = zza;
        return zza;
    }

    /* access modifiers changed from: protected */
    public final <MessageType extends zzzw<MessageType, BuilderType>, BuilderType extends zzzs<MessageType, BuilderType>> BuilderType zzt() {
        return (zzzs) zzj(5, (Object) null, (Object) null);
    }

    public final BuilderType zzu() {
        BuilderType buildertype = (zzzs) zzj(5, (Object) null, (Object) null);
        buildertype.zzj(this);
        return buildertype;
    }
}
