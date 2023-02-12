package com.google.android.gms.maps.internal;

import com.google.android.gms.internal.maps.zzb;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public abstract class zzbq extends zzb implements zzbr {
    public zzbq() {
        super("com.google.android.gms.maps.internal.IOnStreetViewPanoramaReadyCallback");
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [android.os.IInterface] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(int r2, android.os.Parcel r3, android.os.Parcel r4, int r5) throws android.os.RemoteException {
        /*
            r1 = this;
            r5 = 1
            if (r2 != r5) goto L_0x0027
            android.os.IBinder r2 = r3.readStrongBinder()
            if (r2 != 0) goto L_0x000b
            r2 = 0
            goto L_0x0020
        L_0x000b:
            java.lang.String r3 = "com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r0 = r3 instanceof com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
            if (r0 == 0) goto L_0x001a
            r2 = r3
            com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate r2 = (com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate) r2
            goto L_0x0020
        L_0x001a:
            com.google.android.gms.maps.internal.zzbv r3 = new com.google.android.gms.maps.internal.zzbv
            r3.<init>(r2)
            r2 = r3
        L_0x0020:
            r1.zzb(r2)
            r4.writeNoException()
            return r5
        L_0x0027:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzbq.zza(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
