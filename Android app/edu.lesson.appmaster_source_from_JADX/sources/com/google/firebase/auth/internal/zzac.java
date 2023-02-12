package com.google.firebase.auth.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.MultiFactor;
import com.google.firebase.auth.MultiFactorAssertion;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.MultiFactorSession;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzac extends MultiFactor {
    private final zzx zza;

    public zzac(zzx zzx) {
        Preconditions.checkNotNull(zzx);
        this.zza = zzx;
    }

    public final Task<Void> enroll(MultiFactorAssertion multiFactorAssertion, String str) {
        Preconditions.checkNotNull(multiFactorAssertion);
        zzx zzx = this.zza;
        return FirebaseAuth.getInstance(zzx.zza()).zzb(zzx, multiFactorAssertion, str);
    }

    public final List<MultiFactorInfo> getEnrolledFactors() {
        return this.zza.zzn();
    }

    public final Task<MultiFactorSession> getSession() {
        return this.zza.getIdToken(false).continueWithTask(new zzab(this));
    }

    public final Task<Void> unenroll(MultiFactorInfo multiFactorInfo) {
        Preconditions.checkNotNull(multiFactorInfo);
        String uid = multiFactorInfo.getUid();
        Preconditions.checkNotEmpty(uid);
        zzx zzx = this.zza;
        return FirebaseAuth.getInstance(zzx.zza()).zzl(zzx, uid);
    }

    public final Task<Void> unenroll(String str) {
        Preconditions.checkNotEmpty(str);
        zzx zzx = this.zza;
        return FirebaseAuth.getInstance(zzx.zza()).zzl(zzx, str);
    }
}
