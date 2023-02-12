package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
public final class zzav {
    private final zzbg<zzam> zza;
    private final Context zzb;
    private boolean zzc = false;
    private final Map<ListenerHolder.ListenerKey<LocationListener>, zzau> zzd = new HashMap();
    private final Map<ListenerHolder.ListenerKey, zzas> zze = new HashMap();
    private final Map<ListenerHolder.ListenerKey<LocationCallback>, zzar> zzf = new HashMap();

    public zzav(Context context, zzbg<zzam> zzbg) {
        this.zzb = context;
        this.zza = zzbg;
    }

    public final Location zza(String str) throws RemoteException {
        ((zzh) this.zza).zza.checkConnected();
        return ((zzh) this.zza).zza().zzn(str);
    }

    @Deprecated
    public final Location zzb() throws RemoteException {
        ((zzh) this.zza).zza.checkConnected();
        return ((zzh) this.zza).zza().zzm();
    }

    public final LocationAvailability zzc() throws RemoteException {
        ((zzh) this.zza).zza.checkConnected();
        return ((zzh) this.zza).zza().zzs(this.zzb.getPackageName());
    }

    /* JADX WARNING: type inference failed for: r10v0, types: [android.os.IBinder] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(com.google.android.gms.location.LocationRequest r12, com.google.android.gms.common.api.internal.ListenerHolder<com.google.android.gms.location.LocationListener> r13, com.google.android.gms.internal.location.zzai r14) throws android.os.RemoteException {
        /*
            r11 = this;
            com.google.android.gms.internal.location.zzbg<com.google.android.gms.internal.location.zzam> r0 = r11.zza
            com.google.android.gms.internal.location.zzh r0 = (com.google.android.gms.internal.location.zzh) r0
            com.google.android.gms.internal.location.zzi r0 = r0.zza
            r0.checkConnected()
            com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey r0 = r13.getListenerKey()
            r1 = 0
            if (r0 != 0) goto L_0x0012
            r7 = r1
            goto L_0x002d
        L_0x0012:
            java.util.Map<com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey<com.google.android.gms.location.LocationListener>, com.google.android.gms.internal.location.zzau> r2 = r11.zzd
            monitor-enter(r2)
            java.util.Map<com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey<com.google.android.gms.location.LocationListener>, com.google.android.gms.internal.location.zzau> r3 = r11.zzd     // Catch:{ all -> 0x004a }
            java.lang.Object r3 = r3.get(r0)     // Catch:{ all -> 0x004a }
            com.google.android.gms.internal.location.zzau r3 = (com.google.android.gms.internal.location.zzau) r3     // Catch:{ all -> 0x004a }
            if (r3 != 0) goto L_0x0025
            com.google.android.gms.internal.location.zzau r3 = new com.google.android.gms.internal.location.zzau     // Catch:{ all -> 0x004a }
            r3.<init>(r13)     // Catch:{ all -> 0x004a }
            goto L_0x0026
        L_0x0025:
        L_0x0026:
            java.util.Map<com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey<com.google.android.gms.location.LocationListener>, com.google.android.gms.internal.location.zzau> r13 = r11.zzd     // Catch:{ all -> 0x004a }
            r13.put(r0, r3)     // Catch:{ all -> 0x004a }
            monitor-exit(r2)     // Catch:{ all -> 0x004a }
            r7 = r3
        L_0x002d:
            if (r7 != 0) goto L_0x0030
            return
        L_0x0030:
            com.google.android.gms.internal.location.zzbg<com.google.android.gms.internal.location.zzam> r13 = r11.zza
            com.google.android.gms.internal.location.zzh r13 = (com.google.android.gms.internal.location.zzh) r13
            com.google.android.gms.internal.location.zzam r13 = r13.zza()
            com.google.android.gms.internal.location.zzba r6 = com.google.android.gms.internal.location.zzba.zza(r1, r12)
            com.google.android.gms.internal.location.zzbc r12 = new com.google.android.gms.internal.location.zzbc
            r5 = 1
            r8 = 0
            r9 = 0
            r4 = r12
            r10 = r14
            r4.<init>(r5, r6, r7, r8, r9, r10)
            r13.zzo(r12)
            return
        L_0x004a:
            r12 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x004a }
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzav.zzd(com.google.android.gms.location.LocationRequest, com.google.android.gms.common.api.internal.ListenerHolder, com.google.android.gms.internal.location.zzai):void");
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [android.os.IBinder] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zze(com.google.android.gms.internal.location.zzba r11, com.google.android.gms.common.api.internal.ListenerHolder<com.google.android.gms.location.LocationCallback> r12, com.google.android.gms.internal.location.zzai r13) throws android.os.RemoteException {
        /*
            r10 = this;
            com.google.android.gms.internal.location.zzbg<com.google.android.gms.internal.location.zzam> r0 = r10.zza
            com.google.android.gms.internal.location.zzh r0 = (com.google.android.gms.internal.location.zzh) r0
            com.google.android.gms.internal.location.zzi r0 = r0.zza
            r0.checkConnected()
            com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey r0 = r12.getListenerKey()
            if (r0 != 0) goto L_0x0012
            r12 = 0
            r8 = r12
            goto L_0x002e
        L_0x0012:
            java.util.Map<com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey<com.google.android.gms.location.LocationCallback>, com.google.android.gms.internal.location.zzar> r1 = r10.zzf
            monitor-enter(r1)
            java.util.Map<com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey<com.google.android.gms.location.LocationCallback>, com.google.android.gms.internal.location.zzar> r2 = r10.zzf     // Catch:{ all -> 0x0048 }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.location.zzar r2 = (com.google.android.gms.internal.location.zzar) r2     // Catch:{ all -> 0x0048 }
            if (r2 != 0) goto L_0x0026
            com.google.android.gms.internal.location.zzar r2 = new com.google.android.gms.internal.location.zzar     // Catch:{ all -> 0x0048 }
            r2.<init>(r12)     // Catch:{ all -> 0x0048 }
            r12 = r2
            goto L_0x0027
        L_0x0026:
            r12 = r2
        L_0x0027:
            java.util.Map<com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey<com.google.android.gms.location.LocationCallback>, com.google.android.gms.internal.location.zzar> r2 = r10.zzf     // Catch:{ all -> 0x0048 }
            r2.put(r0, r12)     // Catch:{ all -> 0x0048 }
            monitor-exit(r1)     // Catch:{ all -> 0x0048 }
            r8 = r12
        L_0x002e:
            if (r8 != 0) goto L_0x0031
            return
        L_0x0031:
            com.google.android.gms.internal.location.zzbg<com.google.android.gms.internal.location.zzam> r12 = r10.zza
            com.google.android.gms.internal.location.zzh r12 = (com.google.android.gms.internal.location.zzh) r12
            com.google.android.gms.internal.location.zzam r12 = r12.zza()
            com.google.android.gms.internal.location.zzbc r0 = new com.google.android.gms.internal.location.zzbc
            r4 = 1
            r6 = 0
            r7 = 0
            r3 = r0
            r5 = r11
            r9 = r13
            r3.<init>(r4, r5, r6, r7, r8, r9)
            r12.zzo(r0)
            return
        L_0x0048:
            r11 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0048 }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzav.zze(com.google.android.gms.internal.location.zzba, com.google.android.gms.common.api.internal.ListenerHolder, com.google.android.gms.internal.location.zzai):void");
    }

    public final void zzf(zzba zzba, PendingIntent pendingIntent, zzai zzai) throws RemoteException {
        ((zzh) this.zza).zza.checkConnected();
        ((zzh) this.zza).zza().zzo(zzbc.zzb(zzba, pendingIntent, zzai));
    }

    public final void zzg(LocationRequest locationRequest, PendingIntent pendingIntent, zzai zzai) throws RemoteException {
        ((zzh) this.zza).zza.checkConnected();
        ((zzh) this.zza).zza().zzo(zzbc.zzb(zzba.zza((String) null, locationRequest), pendingIntent, zzai));
    }

    public final void zzh(ListenerHolder.ListenerKey<LocationListener> listenerKey, zzai zzai) throws RemoteException {
        ((zzh) this.zza).zza.checkConnected();
        Preconditions.checkNotNull(listenerKey, "Invalid null listener key");
        synchronized (this.zzd) {
            zzau remove = this.zzd.remove(listenerKey);
            if (remove != null) {
                remove.zzc();
                ((zzh) this.zza).zza().zzo(zzbc.zza(remove, zzai));
            }
        }
    }

    public final void zzi(ListenerHolder.ListenerKey<LocationCallback> listenerKey, zzai zzai) throws RemoteException {
        ((zzh) this.zza).zza.checkConnected();
        Preconditions.checkNotNull(listenerKey, "Invalid null listener key");
        synchronized (this.zzf) {
            zzar remove = this.zzf.remove(listenerKey);
            if (remove != null) {
                remove.zzc();
                ((zzh) this.zza).zza().zzo(zzbc.zzc(remove, zzai));
            }
        }
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [android.os.IBinder] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzj(android.app.PendingIntent r10, com.google.android.gms.internal.location.zzai r11) throws android.os.RemoteException {
        /*
            r9 = this;
            com.google.android.gms.internal.location.zzbg<com.google.android.gms.internal.location.zzam> r0 = r9.zza
            com.google.android.gms.internal.location.zzh r0 = (com.google.android.gms.internal.location.zzh) r0
            com.google.android.gms.internal.location.zzi r0 = r0.zza
            r0.checkConnected()
            com.google.android.gms.internal.location.zzbg<com.google.android.gms.internal.location.zzam> r0 = r9.zza
            com.google.android.gms.internal.location.zzh r0 = (com.google.android.gms.internal.location.zzh) r0
            com.google.android.gms.internal.location.zzam r0 = r0.zza()
            com.google.android.gms.internal.location.zzbc r8 = new com.google.android.gms.internal.location.zzbc
            r2 = 2
            r3 = 0
            r4 = 0
            r6 = 0
            r1 = r8
            r5 = r10
            r7 = r11
            r1.<init>(r2, r3, r4, r5, r6, r7)
            r0.zzo(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzav.zzj(android.app.PendingIntent, com.google.android.gms.internal.location.zzai):void");
    }

    public final void zzk(boolean z) throws RemoteException {
        ((zzh) this.zza).zza.checkConnected();
        ((zzh) this.zza).zza().zzp(z);
        this.zzc = z;
    }

    public final void zzl(Location location) throws RemoteException {
        ((zzh) this.zza).zza.checkConnected();
        ((zzh) this.zza).zza().zzq(location);
    }

    public final void zzm(zzai zzai) throws RemoteException {
        ((zzh) this.zza).zza.checkConnected();
        ((zzh) this.zza).zza().zzr(zzai);
    }

    public final void zzn() throws RemoteException {
        synchronized (this.zzd) {
            for (zzau next : this.zzd.values()) {
                if (next != null) {
                    ((zzh) this.zza).zza().zzo(zzbc.zza(next, (zzai) null));
                }
            }
            this.zzd.clear();
        }
        synchronized (this.zzf) {
            for (zzar next2 : this.zzf.values()) {
                if (next2 != null) {
                    ((zzh) this.zza).zza().zzo(zzbc.zzc(next2, (zzai) null));
                }
            }
            this.zzf.clear();
        }
        synchronized (this.zze) {
            for (zzas next3 : this.zze.values()) {
                if (next3 != null) {
                    ((zzh) this.zza).zza().zzu(new zzl(2, (zzj) null, next3, (IBinder) null));
                }
            }
            this.zze.clear();
        }
    }

    public final void zzo() throws RemoteException {
        if (this.zzc) {
            zzk(false);
        }
    }
}
