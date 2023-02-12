package com.google.android.gms.internal.maps;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.List;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public interface zzaa extends IInterface {
    void zzA(float f) throws RemoteException;

    boolean zzB(@Nullable zzaa zzaa) throws RemoteException;

    boolean zzC() throws RemoteException;

    boolean zzD() throws RemoteException;

    boolean zzE() throws RemoteException;

    float zzd() throws RemoteException;

    float zze() throws RemoteException;

    int zzf() throws RemoteException;

    int zzg() throws RemoteException;

    int zzh() throws RemoteException;

    int zzi() throws RemoteException;

    IObjectWrapper zzj() throws RemoteException;

    String zzk() throws RemoteException;

    List zzl() throws RemoteException;

    List<LatLng> zzm() throws RemoteException;

    List<PatternItem> zzn() throws RemoteException;

    void zzo() throws RemoteException;

    void zzp(boolean z) throws RemoteException;

    void zzq(int i) throws RemoteException;

    void zzr(boolean z) throws RemoteException;

    void zzs(List list) throws RemoteException;

    void zzt(List<LatLng> list) throws RemoteException;

    void zzu(int i) throws RemoteException;

    void zzv(int i) throws RemoteException;

    void zzw(@Nullable List<PatternItem> list) throws RemoteException;

    void zzx(float f) throws RemoteException;

    void zzy(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzz(boolean z) throws RemoteException;
}
