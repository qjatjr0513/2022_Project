package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
final class zzfq<V> extends FutureTask<V> implements Comparable<zzfq<V>> {
    final boolean zza;
    final /* synthetic */ zzfs zzb;
    private final long zzc;
    private final String zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfq(zzfs zzfs, Runnable runnable, boolean z, String str) {
        super(runnable, (Object) null);
        this.zzb = zzfs;
        Preconditions.checkNotNull(str);
        long andIncrement = zzfs.zza.getAndIncrement();
        this.zzc = andIncrement;
        this.zzd = str;
        this.zza = z;
        if (andIncrement == Long.MAX_VALUE) {
            zzfs.zzs.zzay().zzd().zza("Tasks index overflow");
        }
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        zzfq zzfq = (zzfq) obj;
        boolean z = this.zza;
        if (z != zzfq.zza) {
            return !z ? 1 : -1;
        }
        int i = (this.zzc > zzfq.zzc ? 1 : (this.zzc == zzfq.zzc ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        if (i > 0) {
            return 1;
        }
        this.zzb.zzs.zzay().zzh().zzb("Two tasks share the same index. index", Long.valueOf(this.zzc));
        return 0;
    }

    /* access modifiers changed from: protected */
    public final void setException(Throwable th) {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
        this.zzb.zzs.zzay().zzd().zzb(this.zzd, th);
        if ((th instanceof zzfo) && (defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()) != null) {
            defaultUncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfq(zzfs zzfs, Callable<V> callable, boolean z, String str) {
        super(callable);
        this.zzb = zzfs;
        Preconditions.checkNotNull("Task exception on worker thread");
        long andIncrement = zzfs.zza.getAndIncrement();
        this.zzc = andIncrement;
        this.zzd = "Task exception on worker thread";
        this.zza = z;
        if (andIncrement == Long.MAX_VALUE) {
            zzfs.zzs.zzay().zzd().zza("Tasks index overflow");
        }
    }
}
