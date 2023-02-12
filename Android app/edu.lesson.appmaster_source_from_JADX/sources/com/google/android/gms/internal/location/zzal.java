package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SleepSegmentRequest;
import com.google.android.gms.location.zzbq;

/* compiled from: com.google.android.gms:play-services-location@@18.0.0 */
public final class zzal extends zza implements zzam {
    zzal(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.internal.IGoogleLocationManagerService");
    }

    public final void zzd(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, zzak zzak) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, geofencingRequest);
        zzc.zzc(zza, pendingIntent);
        zzc.zzd(zza, zzak);
        zzx(57, zza);
    }

    public final void zze(PendingIntent pendingIntent, zzak zzak, String str) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, pendingIntent);
        zzc.zzd(zza, zzak);
        zza.writeString(str);
        zzx(2, zza);
    }

    public final void zzf(String[] strArr, zzak zzak, String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeStringArray(strArr);
        zzc.zzd(zza, zzak);
        zza.writeString(str);
        zzx(3, zza);
    }

    public final void zzg(zzbq zzbq, zzak zzak) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, zzbq);
        zzc.zzd(zza, zzak);
        zzx(74, zza);
    }

    public final void zzh(long j, boolean z, PendingIntent pendingIntent) throws RemoteException {
        Parcel zza = zza();
        zza.writeLong(j);
        zzc.zza(zza, true);
        zzc.zzc(zza, pendingIntent);
        zzx(5, zza);
    }

    public final void zzi(ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, activityTransitionRequest);
        zzc.zzc(zza, pendingIntent);
        zzc.zzd(zza, iStatusCallback);
        zzx(72, zza);
    }

    public final void zzj(PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, pendingIntent);
        zzc.zzd(zza, iStatusCallback);
        zzx(73, zza);
    }

    public final void zzk(PendingIntent pendingIntent) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, pendingIntent);
        zzx(6, zza);
    }

    public final void zzl(PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, pendingIntent);
        zzc.zzd(zza, iStatusCallback);
        zzx(69, zza);
    }

    public final Location zzm() throws RemoteException {
        Parcel zzw = zzw(7, zza());
        Location location = (Location) zzc.zzb(zzw, Location.CREATOR);
        zzw.recycle();
        return location;
    }

    public final Location zzn(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        Parcel zzw = zzw(80, zza);
        Location location = (Location) zzc.zzb(zzw, Location.CREATOR);
        zzw.recycle();
        return location;
    }

    public final void zzo(zzbc zzbc) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, zzbc);
        zzx(59, zza);
    }

    public final void zzp(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zza(zza, z);
        zzx(12, zza);
    }

    public final void zzq(Location location) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, location);
        zzx(13, zza);
    }

    public final void zzr(zzai zzai) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, zzai);
        zzx(67, zza);
    }

    public final LocationAvailability zzs(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        Parcel zzw = zzw(34, zza);
        LocationAvailability locationAvailability = (LocationAvailability) zzc.zzb(zzw, LocationAvailability.CREATOR);
        zzw.recycle();
        return locationAvailability;
    }

    public final void zzt(LocationSettingsRequest locationSettingsRequest, zzao zzao, String str) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, locationSettingsRequest);
        zzc.zzd(zza, zzao);
        zza.writeString((String) null);
        zzx(63, zza);
    }

    public final void zzu(zzl zzl) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, zzl);
        zzx(75, zza);
    }

    public final void zzv(PendingIntent pendingIntent, SleepSegmentRequest sleepSegmentRequest, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, pendingIntent);
        zzc.zzc(zza, sleepSegmentRequest);
        zzc.zzd(zza, iStatusCallback);
        zzx(79, zza);
    }
}
