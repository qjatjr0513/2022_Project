package com.google.android.libraries.places.internal;

import android.location.Location;
import android.os.Build;
import android.os.Looper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzp {
    private static final long zza = TimeUnit.SECONDS.toMillis(10);
    private static final long zzb = TimeUnit.SECONDS.toNanos(24);
    private static final long zzc = TimeUnit.SECONDS.toMillis(59);
    private final zza zzd;
    private final FusedLocationProviderClient zze;
    private final zzcq zzf;

    zzp(zza zza2, FusedLocationProviderClient fusedLocationProviderClient, zzcq zzcq) {
        this.zzd = zza2;
        this.zze = fusedLocationProviderClient;
        this.zzf = zzcq;
    }

    public final Task<Location> zza(CancellationToken cancellationToken) {
        TaskCompletionSource taskCompletionSource;
        zzcq zzcq = this.zzf;
        Task<Location> lastLocation = this.zze.getLastLocation();
        long j = zza;
        if (cancellationToken == null) {
            taskCompletionSource = new TaskCompletionSource();
        } else {
            taskCompletionSource = new TaskCompletionSource(cancellationToken);
        }
        zzcq.zza(taskCompletionSource, j, "Location timeout.");
        lastLocation.continueWithTask(new zzcn(zzcq, taskCompletionSource));
        taskCompletionSource.getTask().addOnCompleteListener(new zzco(zzcq, taskCompletionSource));
        return taskCompletionSource.getTask().continueWithTask(new zzl(this, cancellationToken));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Task zzb(CancellationToken cancellationToken, Task task) throws Exception {
        TaskCompletionSource taskCompletionSource;
        if (task.isSuccessful()) {
            zza zza2 = this.zzd;
            Location location = (Location) task.getResult();
            if (location != null && (Build.VERSION.SDK_INT < 17 || zza2.zzb() - location.getElapsedRealtimeNanos() <= zzb)) {
                return task;
            }
        }
        if (cancellationToken != null) {
            taskCompletionSource = new TaskCompletionSource(cancellationToken);
        } else {
            taskCompletionSource = new TaskCompletionSource();
        }
        LocationRequest priority = LocationRequest.create().setPriority(100);
        long j = zza;
        LocationRequest numUpdates = priority.setExpirationDuration(j).setInterval(zzc).setFastestInterval(10).setNumUpdates(1);
        zzo zzo = new zzo(this, taskCompletionSource);
        this.zze.requestLocationUpdates(numUpdates, zzo, Looper.getMainLooper()).continueWithTask(new zzm(this, taskCompletionSource));
        this.zzf.zza(taskCompletionSource, j, "Location timeout.");
        taskCompletionSource.getTask().addOnCompleteListener(new zzn(this, zzo, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(LocationCallback locationCallback, TaskCompletionSource taskCompletionSource, Task task) {
        this.zze.removeLocationUpdates(locationCallback);
        this.zzf.zzb(taskCompletionSource);
    }
}
