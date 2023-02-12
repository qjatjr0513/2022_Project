package com.google.android.gms.internal.maps;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public interface zzx extends IInterface {
    void zzA(float f) throws RemoteException;

    void zzB() throws RemoteException;

    boolean zzC(zzx zzx) throws RemoteException;

    boolean zzD() throws RemoteException;

    boolean zzE() throws RemoteException;

    boolean zzF() throws RemoteException;

    boolean zzG() throws RemoteException;

    float zzd() throws RemoteException;

    float zze() throws RemoteException;

    float zzf() throws RemoteException;

    int zzg() throws RemoteException;

    IObjectWrapper zzh() throws RemoteException;

    LatLng zzi() throws RemoteException;

    String zzj() throws RemoteException;

    String zzk() throws RemoteException;

    String zzl() throws RemoteException;

    void zzm() throws RemoteException;

    void zzn() throws RemoteException;

    void zzo(float f) throws RemoteException;

    void zzp(float f, float f2) throws RemoteException;

    void zzq(boolean z) throws RemoteException;

    void zzr(boolean z) throws RemoteException;

    void zzs(@Nullable IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzt(float f, float f2) throws RemoteException;

    void zzu(LatLng latLng) throws RemoteException;

    void zzv(float f) throws RemoteException;

    void zzw(@Nullable String str) throws RemoteException;

    void zzx(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzy(@Nullable String str) throws RemoteException;

    void zzz(boolean z) throws RemoteException;
}
