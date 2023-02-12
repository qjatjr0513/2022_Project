package com.google.android.gms.maps.internal;

import com.google.android.gms.internal.maps.zzb;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public abstract class zzj extends zzb implements ILocationSourceDelegate {
    public zzj() {
        super("com.google.android.gms.maps.internal.ILocationSourceDelegate");
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [android.os.IInterface] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(int r1, android.os.Parcel r2, android.os.Parcel r3, int r4) throws android.os.RemoteException {
        /*
            r0 = this;
            switch(r1) {
                case 1: goto L_0x0009;
                case 2: goto L_0x0005;
                default: goto L_0x0003;
            }
        L_0x0003:
            r1 = 0
            return r1
        L_0x0005:
            r0.deactivate()
            goto L_0x0029
        L_0x0009:
            android.os.IBinder r1 = r2.readStrongBinder()
            if (r1 != 0) goto L_0x0011
            r1 = 0
            goto L_0x0026
        L_0x0011:
            java.lang.String r2 = "com.google.android.gms.maps.internal.IOnLocationChangeListener"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r4 = r2 instanceof com.google.android.gms.maps.internal.zzaj
            if (r4 == 0) goto L_0x0020
            r1 = r2
            com.google.android.gms.maps.internal.zzaj r1 = (com.google.android.gms.maps.internal.zzaj) r1
            goto L_0x0026
        L_0x0020:
            com.google.android.gms.maps.internal.zzai r2 = new com.google.android.gms.maps.internal.zzai
            r2.<init>(r1)
            r1 = r2
        L_0x0026:
            r0.activate(r1)
        L_0x0029:
            r3.writeNoException()
            r1 = 1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzj.zza(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
