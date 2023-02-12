package com.google.android.gms.internal.p010firebaseauthapi;

import com.google.android.gms.common.Feature;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzd */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzd {
    public static final Feature zza;
    public static final Feature zzb;
    public static final Feature zzc;
    public static final Feature[] zzd;

    static {
        Feature feature = new Feature("firebase_auth", 11);
        zza = feature;
        Feature feature2 = new Feature("firebase_auth_aidl_migration", 1);
        zzb = feature2;
        Feature feature3 = new Feature("firebase_auth_multi_factor_auth", 1);
        zzc = feature3;
        zzd = new Feature[]{feature, feature2, feature3};
    }
}
