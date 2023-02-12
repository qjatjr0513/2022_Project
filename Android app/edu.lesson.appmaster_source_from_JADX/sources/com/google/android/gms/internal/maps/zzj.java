package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public final class zzj extends zza implements zzl {
    zzj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ICircleDelegate");
    }

    public final boolean zzA() throws RemoteException {
        Parcel zzH = zzH(16, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final double zzd() throws RemoteException {
        Parcel zzH = zzH(6, zza());
        double readDouble = zzH.readDouble();
        zzH.recycle();
        return readDouble;
    }

    public final float zze() throws RemoteException {
        Parcel zzH = zzH(8, zza());
        float readFloat = zzH.readFloat();
        zzH.recycle();
        return readFloat;
    }

    public final float zzf() throws RemoteException {
        Parcel zzH = zzH(14, zza());
        float readFloat = zzH.readFloat();
        zzH.recycle();
        return readFloat;
    }

    public final int zzg() throws RemoteException {
        Parcel zzH = zzH(12, zza());
        int readInt = zzH.readInt();
        zzH.recycle();
        return readInt;
    }

    public final int zzh() throws RemoteException {
        Parcel zzH = zzH(10, zza());
        int readInt = zzH.readInt();
        zzH.recycle();
        return readInt;
    }

    public final int zzi() throws RemoteException {
        Parcel zzH = zzH(18, zza());
        int readInt = zzH.readInt();
        zzH.recycle();
        return readInt;
    }

    public final IObjectWrapper zzj() throws RemoteException {
        Parcel zzH = zzH(24, zza());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(zzH.readStrongBinder());
        zzH.recycle();
        return asInterface;
    }

    public final LatLng zzk() throws RemoteException {
        Parcel zzH = zzH(4, zza());
        LatLng latLng = (LatLng) zzc.zza(zzH, LatLng.CREATOR);
        zzH.recycle();
        return latLng;
    }

    public final String zzl() throws RemoteException {
        Parcel zzH = zzH(2, zza());
        String readString = zzH.readString();
        zzH.recycle();
        return readString;
    }

    public final List<PatternItem> zzm() throws RemoteException {
        Parcel zzH = zzH(22, zza());
        ArrayList<PatternItem> createTypedArrayList = zzH.createTypedArrayList(PatternItem.CREATOR);
        zzH.recycle();
        return createTypedArrayList;
    }

    public final void zzn() throws RemoteException {
        zzc(1, zza());
    }

    public final void zzo(LatLng latLng) throws RemoteException {
        Parcel zza = zza();
        zzc.zzd(zza, latLng);
        zzc(3, zza);
    }

    public final void zzp(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(19, zza);
    }

    public final void zzq(int i) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i);
        zzc(11, zza);
    }

    public final void zzr(double d) throws RemoteException {
        Parcel zza = zza();
        zza.writeDouble(d);
        zzc(5, zza);
    }

    public final void zzs(int i) throws RemoteException {
        Parcel zza = zza();
        zza.writeInt(i);
        zzc(9, zza);
    }

    public final void zzt(List<PatternItem> list) throws RemoteException {
        Parcel zza = zza();
        zza.writeTypedList(list);
        zzc(21, zza);
    }

    public final void zzu(float f) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzc(7, zza);
    }

    public final void zzv(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, iObjectWrapper);
        zzc(23, zza);
    }

    public final void zzw(boolean z) throws RemoteException {
        Parcel zza = zza();
        zzc.zzc(zza, z);
        zzc(15, zza);
    }

    public final void zzx(float f) throws RemoteException {
        Parcel zza = zza();
        zza.writeFloat(f);
        zzc(13, zza);
    }

    public final boolean zzy(zzl zzl) throws RemoteException {
        Parcel zza = zza();
        zzc.zzf(zza, zzl);
        Parcel zzH = zzH(17, zza);
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }

    public final boolean zzz() throws RemoteException {
        Parcel zzH = zzH(20, zza());
        boolean zzg = zzc.zzg(zzH);
        zzH.recycle();
        return zzg;
    }
}
