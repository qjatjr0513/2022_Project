package com.google.android.gms.tasks;

/* compiled from: com.google.android.gms:play-services-tasks@@18.0.1 */
public class TaskCompletionSource<TResult> {
    /* access modifiers changed from: private */
    public final zzw<TResult> zza = new zzw<>();

    public TaskCompletionSource() {
    }

    public TaskCompletionSource(CancellationToken cancellationToken) {
        cancellationToken.onCanceledRequested(new zzs(this));
    }

    public Task<TResult> getTask() {
        return this.zza;
    }

    public void setException(Exception e) {
        this.zza.zza(e);
    }

    public void setResult(TResult result) {
        this.zza.zzb(result);
    }

    public boolean trySetException(Exception e) {
        return this.zza.zzd(e);
    }

    public boolean trySetResult(TResult result) {
        return this.zza.zze(result);
    }
}
