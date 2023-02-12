package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.PhoneAuthCredential;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztw */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public abstract class zztw extends zzb implements zztx {
    public zztw() {
        super("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzq((zzwq) zzc.zza(parcel, zzwq.CREATOR));
                return true;
            case 2:
                zzl((zzwq) zzc.zza(parcel, zzwq.CREATOR), (zzwj) zzc.zza(parcel, zzwj.CREATOR));
                return true;
            case 3:
                zzf((zzvv) zzc.zza(parcel, zzvv.CREATOR));
                return true;
            case 4:
                zzm((zzxb) zzc.zza(parcel, zzxb.CREATOR));
                return true;
            case 5:
                zzk((Status) zzc.zza(parcel, Status.CREATOR));
                return true;
            case 6:
                zzg();
                return true;
            case 7:
                zzn();
                return true;
            case 8:
                zzo(parcel.readString());
                return true;
            case 9:
                zze(parcel.readString());
                return true;
            case 10:
                zzr((PhoneAuthCredential) zzc.zza(parcel, PhoneAuthCredential.CREATOR));
                return true;
            case 11:
                zzd(parcel.readString());
                return true;
            case 12:
                zzj((Status) zzc.zza(parcel, Status.CREATOR), (PhoneAuthCredential) zzc.zza(parcel, PhoneAuthCredential.CREATOR));
                return true;
            case 13:
                zzp();
                return true;
            case 14:
                zzh((zzny) zzc.zza(parcel, zzny.CREATOR));
                return true;
            case 15:
                zzi((zzoa) zzc.zza(parcel, zzoa.CREATOR));
                return true;
            default:
                return false;
        }
    }
}
