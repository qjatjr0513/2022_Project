package com.google.android.gms.cloudmessaging;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.cloudmessaging.zzf;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.0.0 */
final class zzm implements ServiceConnection {
    int zza = 0;
    final Messenger zzb = new Messenger(new zzf(Looper.getMainLooper(), new zzf(this)));
    zzn zzc;
    final Queue<zzp<?>> zzd = new ArrayDeque();
    final SparseArray<zzp<?>> zze = new SparseArray<>();
    final /* synthetic */ zzs zzf;

    /* synthetic */ zzm(zzs zzs, zzl zzl) {
        this.zzf = zzs;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        this.zzf.zzc.execute(new zzj(this, iBinder));
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        this.zzf.zzc.execute(new zzg(this));
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zza(int i, String str) {
        zzb(i, str, (Throwable) null);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzb(int r4, java.lang.String r5, java.lang.Throwable r6) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.String r0 = "MessengerIpcClient"
            r1 = 3
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x0093 }
            if (r0 == 0) goto L_0x0025
            java.lang.String r0 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0093 }
            java.lang.String r1 = "Disconnected: "
            int r2 = r0.length()     // Catch:{ all -> 0x0093 }
            if (r2 == 0) goto L_0x001b
            java.lang.String r0 = r1.concat(r0)     // Catch:{ all -> 0x0093 }
            goto L_0x0020
        L_0x001b:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x0093 }
            r0.<init>(r1)     // Catch:{ all -> 0x0093 }
        L_0x0020:
            java.lang.String r1 = "MessengerIpcClient"
            android.util.Log.d(r1, r0)     // Catch:{ all -> 0x0093 }
        L_0x0025:
            int r0 = r3.zza     // Catch:{ all -> 0x0093 }
            r1 = 4
            switch(r0) {
                case 0: goto L_0x008d;
                case 1: goto L_0x0031;
                case 2: goto L_0x0031;
                case 3: goto L_0x002d;
                default: goto L_0x002b;
            }
        L_0x002b:
            monitor-exit(r3)
            return
        L_0x002d:
            r3.zza = r1     // Catch:{ all -> 0x0093 }
        L_0x002f:
            monitor-exit(r3)
            return
        L_0x0031:
            java.lang.String r0 = "MessengerIpcClient"
            r2 = 2
            boolean r0 = android.util.Log.isLoggable(r0, r2)     // Catch:{ all -> 0x0093 }
            if (r0 == 0) goto L_0x0041
            java.lang.String r0 = "MessengerIpcClient"
            java.lang.String r2 = "Unbinding service"
            android.util.Log.v(r0, r2)     // Catch:{ all -> 0x0093 }
        L_0x0041:
            r3.zza = r1     // Catch:{ all -> 0x0093 }
            com.google.android.gms.common.stats.ConnectionTracker r0 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ all -> 0x0093 }
            com.google.android.gms.cloudmessaging.zzs r1 = r3.zzf     // Catch:{ all -> 0x0093 }
            android.content.Context r1 = r1.zzb     // Catch:{ all -> 0x0093 }
            r0.unbindService(r1, r3)     // Catch:{ all -> 0x0093 }
            com.google.android.gms.cloudmessaging.zzq r0 = new com.google.android.gms.cloudmessaging.zzq     // Catch:{ all -> 0x0093 }
            r0.<init>(r4, r5, r6)     // Catch:{ all -> 0x0093 }
            java.util.Queue<com.google.android.gms.cloudmessaging.zzp<?>> r4 = r3.zzd     // Catch:{ all -> 0x0093 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0093 }
        L_0x005b:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x0093 }
            if (r5 == 0) goto L_0x006b
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x0093 }
            com.google.android.gms.cloudmessaging.zzp r5 = (com.google.android.gms.cloudmessaging.zzp) r5     // Catch:{ all -> 0x0093 }
            r5.zzc(r0)     // Catch:{ all -> 0x0093 }
            goto L_0x005b
        L_0x006b:
            java.util.Queue<com.google.android.gms.cloudmessaging.zzp<?>> r4 = r3.zzd     // Catch:{ all -> 0x0093 }
            r4.clear()     // Catch:{ all -> 0x0093 }
            r4 = 0
        L_0x0071:
            android.util.SparseArray<com.google.android.gms.cloudmessaging.zzp<?>> r5 = r3.zze     // Catch:{ all -> 0x0093 }
            int r5 = r5.size()     // Catch:{ all -> 0x0093 }
            if (r4 >= r5) goto L_0x0087
            android.util.SparseArray<com.google.android.gms.cloudmessaging.zzp<?>> r5 = r3.zze     // Catch:{ all -> 0x0093 }
            java.lang.Object r5 = r5.valueAt(r4)     // Catch:{ all -> 0x0093 }
            com.google.android.gms.cloudmessaging.zzp r5 = (com.google.android.gms.cloudmessaging.zzp) r5     // Catch:{ all -> 0x0093 }
            r5.zzc(r0)     // Catch:{ all -> 0x0093 }
            int r4 = r4 + 1
            goto L_0x0071
        L_0x0087:
            android.util.SparseArray<com.google.android.gms.cloudmessaging.zzp<?>> r4 = r3.zze     // Catch:{ all -> 0x0093 }
            r4.clear()     // Catch:{ all -> 0x0093 }
            goto L_0x002f
        L_0x008d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0093 }
            r4.<init>()     // Catch:{ all -> 0x0093 }
            throw r4     // Catch:{ all -> 0x0093 }
        L_0x0093:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cloudmessaging.zzm.zzb(int, java.lang.String, java.lang.Throwable):void");
    }

    /* access modifiers changed from: package-private */
    public final void zzc() {
        this.zzf.zzc.execute(new zzh(this));
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzd() {
        if (this.zza == 1) {
            zza(1, "Timed out while binding");
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zze(int i) {
        zzp zzp = this.zze.get(i);
        if (zzp != null) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Timing out request: ");
            sb.append(i);
            Log.w("MessengerIpcClient", sb.toString());
            this.zze.remove(i);
            zzp.zzc(new zzq(3, "Timed out waiting for response", (Throwable) null));
            zzf();
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzf() {
        if (this.zza == 2 && this.zzd.isEmpty() && this.zze.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.zza = 3;
            ConnectionTracker.getInstance().unbindService(this.zzf.zzb, this);
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zzg(zzp<?> zzp) {
        boolean z;
        switch (this.zza) {
            case 0:
                this.zzd.add(zzp);
                if (this.zza == 0) {
                    z = true;
                } else {
                    z = false;
                }
                Preconditions.checkState(z);
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Starting bind to GmsCore");
                }
                this.zza = 1;
                Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                intent.setPackage("com.google.android.gms");
                try {
                    if (ConnectionTracker.getInstance().bindService(this.zzf.zzb, intent, this, 1)) {
                        this.zzf.zzc.schedule(new zzi(this), 30, TimeUnit.SECONDS);
                        break;
                    } else {
                        zza(0, "Unable to bind to service");
                        break;
                    }
                } catch (SecurityException e) {
                    zzb(0, "Unable to bind to service", e);
                    break;
                }
            case 1:
                this.zzd.add(zzp);
                return true;
            case 2:
                this.zzd.add(zzp);
                zzc();
                return true;
            default:
                return false;
        }
        return true;
    }
}
