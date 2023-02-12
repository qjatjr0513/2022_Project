package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.PhoneAuthCredential;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztx */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public interface zztx extends IInterface {
    void zzd(String str) throws RemoteException;

    void zze(String str) throws RemoteException;

    void zzf(zzvv zzvv) throws RemoteException;

    void zzg() throws RemoteException;

    void zzh(zzny zzny) throws RemoteException;

    void zzi(zzoa zzoa) throws RemoteException;

    void zzj(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException;

    void zzk(Status status) throws RemoteException;

    void zzl(zzwq zzwq, zzwj zzwj) throws RemoteException;

    void zzm(zzxb zzxb) throws RemoteException;

    void zzn() throws RemoteException;

    void zzo(String str) throws RemoteException;

    void zzp() throws RemoteException;

    void zzq(zzwq zzwq) throws RemoteException;

    void zzr(PhoneAuthCredential phoneAuthCredential) throws RemoteException;
}
