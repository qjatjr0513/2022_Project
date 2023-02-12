package com.google.android.gms.internal.maps;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.List;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public interface zzad extends IInterface {
    void zzA(float f) throws RemoteException;

    boolean zzB(@Nullable zzad zzad) throws RemoteException;

    boolean zzC() throws RemoteException;

    boolean zzD() throws RemoteException;

    boolean zzE() throws RemoteException;

    float zzd() throws RemoteException;

    float zze() throws RemoteException;

    int zzf() throws RemoteException;

    int zzg() throws RemoteException;

    int zzh() throws RemoteException;

    IObjectWrapper zzi() throws RemoteException;

    Cap zzj() throws RemoteException;

    Cap zzk() throws RemoteException;

    String zzl() throws RemoteException;

    List<PatternItem> zzm() throws RemoteException;

    List<LatLng> zzn() throws RemoteException;

    void zzo() throws RemoteException;

    void zzp(boolean z) throws RemoteException;

    void zzq(int i) throws RemoteException;

    void zzr(Cap cap) throws RemoteException;

    void zzs(boolean z) throws RemoteException;

    void zzt(int i) throws RemoteException;

    void zzu(@Nullable List<PatternItem> list) throws RemoteException;

    void zzv(List<LatLng> list) throws RemoteException;

    void zzw(Cap cap) throws RemoteException;

    void zzx(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzy(boolean z) throws RemoteException;

    void zzz(float f) throws RemoteException;
}
