package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.PhoneAuthCredential;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztv */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zztv extends zza implements zztx {
    zztv(IBinder iBinder) {
        super(iBinder, "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
    }

    public final void zzd(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzJ(11, zza);
    }

    public final void zze(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzJ(9, zza);
    }

    public final void zzf(zzvv zzvv) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzvv);
        zzJ(3, zza);
    }

    public final void zzg() throws RemoteException {
        zzJ(6, zza());
    }

    public final void zzh(zzny zzny) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzny);
        zzJ(14, zza);
    }

    public final void zzi(zzoa zzoa) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzoa);
        zzJ(15, zza);
    }

    public final void zzj(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, status);
        zzc.zzb(zza, phoneAuthCredential);
        zzJ(12, zza);
    }

    public final void zzk(Status status) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, status);
        zzJ(5, zza);
    }

    public final void zzl(zzwq zzwq, zzwj zzwj) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzwq);
        zzc.zzb(zza, zzwj);
        zzJ(2, zza);
    }

    public final void zzm(zzxb zzxb) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzxb);
        zzJ(4, zza);
    }

    public final void zzn() throws RemoteException {
        zzJ(7, zza());
    }

    public final void zzo(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzJ(8, zza);
    }

    public final void zzp() throws RemoteException {
        zzJ(13, zza());
    }

    public final void zzq(zzwq zzwq) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, zzwq);
        zzJ(1, zza);
    }

    public final void zzr(PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        Parcel zza = zza();
        zzc.zzb(zza, phoneAuthCredential);
        zzJ(10, zza);
    }
}
