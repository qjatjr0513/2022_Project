package com.google.firebase.auth;

import android.app.Activity;
import android.net.Uri;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.p010firebaseauthapi.zzwq;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public abstract class FirebaseUser extends AbstractSafeParcelable implements UserInfo {
    public Task<Void> delete() {
        return FirebaseAuth.getInstance(zza()).zza(this);
    }

    public abstract String getDisplayName();

    public abstract String getEmail();

    public Task<GetTokenResult> getIdToken(boolean forceRefresh) {
        return FirebaseAuth.getInstance(zza()).zzc(this, forceRefresh);
    }

    public abstract FirebaseUserMetadata getMetadata();

    public abstract MultiFactor getMultiFactor();

    public abstract String getPhoneNumber();

    public abstract Uri getPhotoUrl();

    public abstract List<? extends UserInfo> getProviderData();

    public abstract String getProviderId();

    public abstract String getTenantId();

    public abstract String getUid();

    public abstract boolean isAnonymous();

    public Task<AuthResult> linkWithCredential(AuthCredential credential) {
        Preconditions.checkNotNull(credential);
        return FirebaseAuth.getInstance(zza()).zzd(this, credential);
    }

    public Task<Void> reauthenticate(AuthCredential credential) {
        Preconditions.checkNotNull(credential);
        return FirebaseAuth.getInstance(zza()).zze(this, credential);
    }

    public Task<AuthResult> reauthenticateAndRetrieveData(AuthCredential credential) {
        Preconditions.checkNotNull(credential);
        return FirebaseAuth.getInstance(zza()).zzf(this, credential);
    }

    public Task<Void> reload() {
        FirebaseAuth instance = FirebaseAuth.getInstance(zza());
        return instance.zzg(this, new zzt(instance));
    }

    public Task<Void> sendEmailVerification() {
        return FirebaseAuth.getInstance(zza()).zzc(this, false).continueWithTask(new zzw(this));
    }

    public Task<AuthResult> startActivityForLinkWithProvider(Activity activity, FederatedAuthProvider federatedAuthProvider) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        return FirebaseAuth.getInstance(zza()).zzj(activity, federatedAuthProvider, this);
    }

    public Task<AuthResult> startActivityForReauthenticateWithProvider(Activity activity, FederatedAuthProvider federatedAuthProvider) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        return FirebaseAuth.getInstance(zza()).zzk(activity, federatedAuthProvider, this);
    }

    public Task<AuthResult> unlink(String provider) {
        Preconditions.checkNotEmpty(provider);
        return FirebaseAuth.getInstance(zza()).zzm(this, provider);
    }

    public Task<Void> updateEmail(String email) {
        Preconditions.checkNotEmpty(email);
        return FirebaseAuth.getInstance(zza()).zzn(this, email);
    }

    public Task<Void> updatePassword(String password) {
        Preconditions.checkNotEmpty(password);
        return FirebaseAuth.getInstance(zza()).zzo(this, password);
    }

    public Task<Void> updatePhoneNumber(PhoneAuthCredential credential) {
        return FirebaseAuth.getInstance(zza()).zzp(this, credential);
    }

    public Task<Void> updateProfile(UserProfileChangeRequest request) {
        Preconditions.checkNotNull(request);
        return FirebaseAuth.getInstance(zza()).zzq(this, request);
    }

    public Task<Void> verifyBeforeUpdateEmail(String newEmail) {
        return verifyBeforeUpdateEmail(newEmail, (ActionCodeSettings) null);
    }

    public abstract FirebaseApp zza();

    public abstract FirebaseUser zzb();

    public abstract FirebaseUser zzc(List<? extends UserInfo> list);

    public abstract zzwq zzd();

    public abstract String zze();

    public abstract String zzf();

    public abstract List<String> zzg();

    public abstract void zzh(zzwq zzwq);

    public abstract void zzi(List<MultiFactorInfo> list);

    public Task<Void> verifyBeforeUpdateEmail(String newEmail, ActionCodeSettings actionCodeSettings) {
        return FirebaseAuth.getInstance(zza()).zzc(this, false).continueWithTask(new zzy(this, newEmail, actionCodeSettings));
    }

    public Task<Void> sendEmailVerification(ActionCodeSettings actionCodeSettings) {
        return FirebaseAuth.getInstance(zza()).zzc(this, false).continueWithTask(new zzx(this, actionCodeSettings));
    }
}
