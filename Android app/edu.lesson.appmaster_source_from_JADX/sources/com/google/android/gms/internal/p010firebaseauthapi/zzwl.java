package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwl */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzwl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzwl> CREATOR = new zzwm();
    private final List<zzwj> zza;

    public zzwl() {
        this.zza = new ArrayList();
    }

    public static zzwl zza(zzwl zzwl) {
        Preconditions.checkNotNull(zzwl);
        List<zzwj> list = zzwl.zza;
        zzwl zzwl2 = new zzwl();
        if (list != null && !list.isEmpty()) {
            zzwl2.zza.addAll(list);
        }
        return zzwl2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final List<zzwj> zzb() {
        return this.zza;
    }

    zzwl(List<zzwj> list) {
        List<zzwj> list2;
        if (list == null) {
            list2 = Collections.emptyList();
        } else {
            list2 = Collections.unmodifiableList(list);
        }
        this.zza = list2;
    }
}
