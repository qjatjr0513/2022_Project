package com.google.android.gms.internal.p010firebaseauthapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneMultiFactorInfo;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsn */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzsn extends zzux<Void, PhoneAuthProvider.OnVerificationStateChangedCallbacks> {
    private final zznm zza;

    public zzsn(PhoneMultiFactorInfo phoneMultiFactorInfo, String str, String str2, long j, boolean z, boolean z2, String str3, String str4, boolean z3) {
        super(8);
        Preconditions.checkNotNull(phoneMultiFactorInfo);
        Preconditions.checkNotEmpty(str);
        this.zza = new zznm(phoneMultiFactorInfo, str, str2, j, z, z2, str3, str4, z3);
    }

    public final TaskApiCall<zztm, Void> zza() {
        return TaskApiCall.builder().run(new zzsm(this)).build();
    }

    public final String zzb() {
        return "startMfaSignInWithPhoneNumber";
    }

    public final void zzc() {
    }

    public final /* synthetic */ void zzd(zztm zztm, TaskCompletionSource taskCompletionSource) throws RemoteException {
        this.zzv = new zzuw(this, taskCompletionSource);
        zztm.zzq().zzB(this.zza, this.zzc);
    }
}
