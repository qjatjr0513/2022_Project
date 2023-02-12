package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.0.0 */
public final class zzfs extends zzgp {
    /* access modifiers changed from: private */
    public static final AtomicLong zza = new AtomicLong(Long.MIN_VALUE);
    /* access modifiers changed from: private */
    public zzfr zzb;
    /* access modifiers changed from: private */
    public zzfr zzc;
    private final PriorityBlockingQueue<zzfq<?>> zzd = new PriorityBlockingQueue<>();
    private final BlockingQueue<zzfq<?>> zze = new LinkedBlockingQueue();
    private final Thread.UncaughtExceptionHandler zzf = new zzfp(this, "Thread death: Uncaught exception on worker thread");
    private final Thread.UncaughtExceptionHandler zzg = new zzfp(this, "Thread death: Uncaught exception on network thread");
    /* access modifiers changed from: private */
    public final Object zzh = new Object();
    /* access modifiers changed from: private */
    public final Semaphore zzi = new Semaphore(2);
    /* access modifiers changed from: private */
    public volatile boolean zzj;

    zzfs(zzfv zzfv) {
        super(zzfv);
    }

    private final void zzt(zzfq<?> zzfq) {
        synchronized (this.zzh) {
            this.zzd.add(zzfq);
            zzfr zzfr = this.zzb;
            if (zzfr == null) {
                zzfr zzfr2 = new zzfr(this, "Measurement Worker", this.zzd);
                this.zzb = zzfr2;
                zzfr2.setUncaughtExceptionHandler(this.zzf);
                this.zzb.start();
            } else {
                zzfr.zza();
            }
        }
    }

    public final void zzax() {
        if (Thread.currentThread() != this.zzc) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    /* access modifiers changed from: package-private */
    public final <T> T zzd(AtomicReference<T> atomicReference, long j, String str, Runnable runnable) {
        synchronized (atomicReference) {
            this.zzs.zzaz().zzp(runnable);
            try {
                atomicReference.wait(j);
            } catch (InterruptedException e) {
                this.zzs.zzay().zzk().zza(str.length() != 0 ? "Interrupted waiting for ".concat(str) : new String("Interrupted waiting for "));
                return null;
            }
        }
        T t = atomicReference.get();
        if (t == null) {
            this.zzs.zzay().zzk().zza(str.length() != 0 ? "Timed out waiting for ".concat(str) : new String("Timed out waiting for "));
        }
        return t;
    }

    /* access modifiers changed from: protected */
    public final boolean zzf() {
        return false;
    }

    public final void zzg() {
        if (Thread.currentThread() != this.zzb) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public final <V> Future<V> zzh(Callable<V> callable) throws IllegalStateException {
        zzu();
        Preconditions.checkNotNull(callable);
        zzfq zzfq = new zzfq(this, callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzb) {
            if (!this.zzd.isEmpty()) {
                this.zzs.zzay().zzk().zza("Callable skipped the worker queue.");
            }
            zzfq.run();
        } else {
            zzt(zzfq);
        }
        return zzfq;
    }

    public final <V> Future<V> zzi(Callable<V> callable) throws IllegalStateException {
        zzu();
        Preconditions.checkNotNull(callable);
        zzfq zzfq = new zzfq(this, callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzb) {
            zzfq.run();
        } else {
            zzt(zzfq);
        }
        return zzfq;
    }

    public final void zzo(Runnable runnable) throws IllegalStateException {
        zzu();
        Preconditions.checkNotNull(runnable);
        zzfq zzfq = new zzfq(this, runnable, false, "Task exception on network thread");
        synchronized (this.zzh) {
            this.zze.add(zzfq);
            zzfr zzfr = this.zzc;
            if (zzfr == null) {
                zzfr zzfr2 = new zzfr(this, "Measurement Network", this.zze);
                this.zzc = zzfr2;
                zzfr2.setUncaughtExceptionHandler(this.zzg);
                this.zzc.start();
            } else {
                zzfr.zza();
            }
        }
    }

    public final void zzp(Runnable runnable) throws IllegalStateException {
        zzu();
        Preconditions.checkNotNull(runnable);
        zzt(new zzfq(this, runnable, false, "Task exception on worker thread"));
    }

    public final void zzq(Runnable runnable) throws IllegalStateException {
        zzu();
        Preconditions.checkNotNull(runnable);
        zzt(new zzfq(this, runnable, true, "Task exception on worker thread"));
    }

    public final boolean zzs() {
        return Thread.currentThread() == this.zzb;
    }
}
