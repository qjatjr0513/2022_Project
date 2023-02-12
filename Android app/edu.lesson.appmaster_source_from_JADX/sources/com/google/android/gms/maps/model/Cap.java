package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public class Cap extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Cap> CREATOR = new zzb();
    private static final String zza = Cap.class.getSimpleName();
    private final int zzb;
    private final BitmapDescriptor zzc;
    private final Float zzd;

    protected Cap(int i) {
        this(i, (BitmapDescriptor) null, (Float) null);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cap)) {
            return false;
        }
        Cap cap = (Cap) o;
        return this.zzb == cap.zzb && Objects.equal(this.zzc, cap.zzc) && Objects.equal(this.zzd, cap.zzd);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzb), this.zzc, this.zzd);
    }

    public String toString() {
        int i = this.zzb;
        StringBuilder sb = new StringBuilder(23);
        sb.append("[Cap: type=");
        sb.append(i);
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel out, int i) {
        IBinder iBinder;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(out);
        SafeParcelWriter.writeInt(out, 2, this.zzb);
        BitmapDescriptor bitmapDescriptor = this.zzc;
        if (bitmapDescriptor == null) {
            iBinder = null;
        } else {
            iBinder = bitmapDescriptor.zza().asBinder();
        }
        SafeParcelWriter.writeIBinder(out, 3, iBinder, false);
        SafeParcelWriter.writeFloatObject(out, 4, this.zzd, false);
        SafeParcelWriter.finishObjectHeader(out, beginObjectHeader);
    }

    /* access modifiers changed from: package-private */
    public final Cap zza() {
        boolean z;
        int i = this.zzb;
        switch (i) {
            case 0:
                return new ButtCap();
            case 1:
                return new SquareCap();
            case 2:
                return new RoundCap();
            case 3:
                boolean z2 = true;
                if (this.zzc != null) {
                    z = true;
                } else {
                    z = false;
                }
                Preconditions.checkState(z, "bitmapDescriptor must not be null");
                if (this.zzd == null) {
                    z2 = false;
                }
                Preconditions.checkState(z2, "bitmapRefWidth must not be null");
                return new CustomCap(this.zzc, this.zzd.floatValue());
            default:
                String str = zza;
                StringBuilder sb = new StringBuilder(29);
                sb.append("Unknown Cap type: ");
                sb.append(i);
                Log.w(str, sb.toString());
                return this;
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    Cap(int r2, android.os.IBinder r3, java.lang.Float r4) {
        /*
            r1 = this;
            if (r3 != 0) goto L_0x0004
            r3 = 0
            goto L_0x000e
        L_0x0004:
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            com.google.android.gms.maps.model.BitmapDescriptor r0 = new com.google.android.gms.maps.model.BitmapDescriptor
            r0.<init>(r3)
            r3 = r0
        L_0x000e:
            r1.<init>((int) r2, (com.google.android.gms.maps.model.BitmapDescriptor) r3, (java.lang.Float) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.Cap.<init>(int, android.os.IBinder, java.lang.Float):void");
    }

    private Cap(int i, BitmapDescriptor bitmapDescriptor, Float f) {
        boolean z;
        boolean z2;
        if (f == null || f.floatValue() <= 0.0f) {
            z = false;
        } else {
            z = true;
        }
        if (i != 3) {
            z2 = true;
        } else if (bitmapDescriptor == null || !z) {
            z2 = false;
            i = 3;
        } else {
            z2 = true;
            i = 3;
        }
        Preconditions.checkArgument(z2, String.format("Invalid Cap: type=%s bitmapDescriptor=%s bitmapRefWidth=%s", new Object[]{Integer.valueOf(i), bitmapDescriptor, f}));
        this.zzb = i;
        this.zzc = bitmapDescriptor;
        this.zzd = f;
    }

    protected Cap(BitmapDescriptor bitmapDescriptor, float bitmapRefWidth) {
        this(3, bitmapDescriptor, Float.valueOf(bitmapRefWidth));
    }
}
