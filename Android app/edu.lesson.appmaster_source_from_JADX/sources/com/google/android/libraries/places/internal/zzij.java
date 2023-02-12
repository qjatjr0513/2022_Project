package com.google.android.libraries.places.internal;

import android.os.Build;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzij extends zzid {
    private static final AtomicReference<zzif> zza = new AtomicReference<>();
    private static final AtomicLong zzb = new AtomicLong();
    private static final ConcurrentLinkedQueue<zzii> zzc = new ConcurrentLinkedQueue<>();
    private volatile zzhl zzd;

    private zzij(String str) {
        super(str);
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (Build.FINGERPRINT != null) {
            z = "robolectric".equals(Build.FINGERPRINT);
        } else {
            z = true;
        }
        if (!"goldfish".equals(Build.HARDWARE)) {
            z2 = "ranchu".equals(Build.HARDWARE);
        } else {
            z2 = true;
        }
        if (!"eng".equals(Build.TYPE) && !"userdebug".equals(Build.TYPE)) {
            z3 = false;
        }
        if (z || z2) {
            this.zzd = new zzie().zza(zza());
        } else {
            this.zzd = z3 ? new zzil().zzb(false).zza(zza()) : null;
        }
    }

    public static zzhl zzb(String str) {
        AtomicReference<zzif> atomicReference = zza;
        if (atomicReference.get() != null) {
            return atomicReference.get().zza(str);
        }
        zzij zzij = new zzij(str.replace('$', '.'));
        zzih.zza.offer(zzij);
        if (atomicReference.get() != null) {
            while (true) {
                zzij poll = zzih.zza.poll();
                if (poll == null) {
                    break;
                }
                poll.zzd = zza.get().zza(poll.zza());
            }
            if (zzc.poll() != null) {
                zzb.getAndDecrement();
                throw null;
            }
        }
        return zzij;
    }
}
