package com.google.firebase.auth.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzad implements Continuation<AuthResult, Task<AuthResult>> {
    final /* synthetic */ zzae zza;

    zzad(zzae zzae) {
        this.zza = zzae;
    }

    public final /* bridge */ /* synthetic */ Object then(Task task) throws Exception {
        if (this.zza.zzd == null) {
            return task;
        }
        if (task.isSuccessful()) {
            AuthResult authResult = (AuthResult) task.getResult();
            return Tasks.forResult(new zzr((zzx) authResult.getUser(), (zzp) authResult.getAdditionalUserInfo(), this.zza.zzd));
        }
        Exception exception = task.getException();
        if (exception instanceof FirebaseAuthUserCollisionException) {
            ((FirebaseAuthUserCollisionException) exception).zza(this.zza.zzd);
        }
        return Tasks.forException(exception);
    }
}
