package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
public class PatternItem extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PatternItem> CREATOR = new zzj();
    private static final String zza = PatternItem.class.getSimpleName();
    private final int zzb;
    private final Float zzc;

    public PatternItem(int i, Float f) {
        boolean z = false;
        if (i == 1) {
            z = true;
        } else if (f != null && f.floatValue() >= 0.0f) {
            z = true;
        }
        String valueOf = String.valueOf(f);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 45);
        sb.append("Invalid PatternItem: type=");
        sb.append(i);
        sb.append(" length=");
        sb.append(valueOf);
        Preconditions.checkArgument(z, sb.toString());
        this.zzb = i;
        this.zzc = f;
    }

    static List<PatternItem> zza(List<PatternItem> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (PatternItem next : list) {
            if (next != null) {
                int i = next.zzb;
                boolean z = true;
                switch (i) {
                    case 0:
                        if (next.zzc == null) {
                            z = false;
                        }
                        Preconditions.checkState(z, "length must not be null.");
                        next = new Dash(next.zzc.floatValue());
                        break;
                    case 1:
                        next = new Dot();
                        break;
                    case 2:
                        if (next.zzc == null) {
                            z = false;
                        }
                        Preconditions.checkState(z, "length must not be null.");
                        next = new Gap(next.zzc.floatValue());
                        break;
                    default:
                        String str = zza;
                        StringBuilder sb = new StringBuilder(37);
                        sb.append("Unknown PatternItem type: ");
                        sb.append(i);
                        Log.w(str, sb.toString());
                        break;
                }
            } else {
                next = null;
            }
            arrayList.add(next);
        }
        return arrayList;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatternItem)) {
            return false;
        }
        PatternItem patternItem = (PatternItem) o;
        return this.zzb == patternItem.zzb && Objects.equal(this.zzc, patternItem.zzc);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzb), this.zzc);
    }

    public String toString() {
        int i = this.zzb;
        String valueOf = String.valueOf(this.zzc);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 39);
        sb.append("[PatternItem: type=");
        sb.append(i);
        sb.append(" length=");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel out, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(out);
        SafeParcelWriter.writeInt(out, 2, this.zzb);
        SafeParcelWriter.writeFloatObject(out, 3, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(out, beginObjectHeader);
    }
}
