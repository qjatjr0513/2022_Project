package com.google.firebase.auth;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p010firebaseauthapi.zzti;
import com.google.android.gms.internal.p010firebaseauthapi.zztk;
import com.google.android.gms.internal.p010firebaseauthapi.zzto;
import com.google.android.gms.internal.p010firebaseauthapi.zzue;
import com.google.android.gms.internal.p010firebaseauthapi.zzug;
import com.google.android.gms.internal.p010firebaseauthapi.zzun;
import com.google.android.gms.internal.p010firebaseauthapi.zzvh;
import com.google.android.gms.internal.p010firebaseauthapi.zzvr;
import com.google.android.gms.internal.p010firebaseauthapi.zzwq;
import com.google.android.gms.internal.p010firebaseauthapi.zzxd;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.auth.internal.zzag;
import com.google.firebase.auth.internal.zzay;
import com.google.firebase.auth.internal.zzbg;
import com.google.firebase.auth.internal.zzbi;
import com.google.firebase.auth.internal.zzbj;
import com.google.firebase.auth.internal.zzbk;
import com.google.firebase.auth.internal.zzbm;
import com.google.firebase.auth.internal.zzf;
import com.google.firebase.auth.internal.zzr;
import com.google.firebase.auth.internal.zzw;
import com.google.firebase.auth.internal.zzx;
import com.google.firebase.internal.InternalTokenResult;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public abstract class FirebaseAuth implements InternalAuthProvider {
    private FirebaseApp zza;
    /* access modifiers changed from: private */
    public final List<IdTokenListener> zzb = new CopyOnWriteArrayList();
    /* access modifiers changed from: private */
    public final List<com.google.firebase.auth.internal.IdTokenListener> zzc = new CopyOnWriteArrayList();
    /* access modifiers changed from: private */
    public List<AuthStateListener> zzd = new CopyOnWriteArrayList();
    /* access modifiers changed from: private */
    public zzti zze;
    /* access modifiers changed from: private */
    public FirebaseUser zzf;
    /* access modifiers changed from: private */
    public zzw zzg;
    private final Object zzh = new Object();
    /* access modifiers changed from: private */
    public String zzi;
    private final Object zzj = new Object();
    private String zzk;
    private final zzbg zzl;
    private final zzbm zzm;
    private final zzf zzn;
    private zzbi zzo;
    private zzbj zzp = zzbj.zza();

    /* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
    public interface AuthStateListener {
        void onAuthStateChanged(FirebaseAuth firebaseAuth);
    }

    /* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
    public interface IdTokenListener {
        void onIdTokenChanged(FirebaseAuth firebaseAuth);
    }

    public FirebaseAuth(FirebaseApp firebaseApp) {
        zzwq zzb2;
        zzti zza2 = zzug.zza(firebaseApp.getApplicationContext(), zzue.zza(Preconditions.checkNotEmpty(firebaseApp.getOptions().getApiKey())));
        zzbg zzbg = new zzbg(firebaseApp.getApplicationContext(), firebaseApp.getPersistenceKey());
        zzbm zzc2 = zzbm.zzc();
        zzf zzb3 = zzf.zzb();
        this.zza = (FirebaseApp) Preconditions.checkNotNull(firebaseApp);
        this.zze = (zzti) Preconditions.checkNotNull(zza2);
        zzbg zzbg2 = (zzbg) Preconditions.checkNotNull(zzbg);
        this.zzl = zzbg2;
        this.zzg = new zzw();
        zzbm zzbm = (zzbm) Preconditions.checkNotNull(zzc2);
        this.zzm = zzbm;
        this.zzn = (zzf) Preconditions.checkNotNull(zzb3);
        FirebaseUser zza3 = zzbg2.zza();
        this.zzf = zza3;
        if (!(zza3 == null || (zzb2 = zzbg2.zzb(zza3)) == null)) {
            zzG(this, this.zzf, zzb2, false, false);
        }
        zzbm.zze(this);
    }

    public static FirebaseAuth getInstance() {
        return (FirebaseAuth) FirebaseApp.getInstance().get(FirebaseAuth.class);
    }

    public static void zzE(FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            StringBuilder sb = new StringBuilder(String.valueOf(uid).length() + 47);
            sb.append("Notifying auth state listeners about user ( ");
            sb.append(uid);
            sb.append(" ).");
            Log.d("FirebaseAuth", sb.toString());
        } else {
            Log.d("FirebaseAuth", "Notifying auth state listeners about a sign-out event.");
        }
        firebaseAuth.zzp.execute(new zzm(firebaseAuth));
    }

    public static void zzF(FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        String str;
        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            StringBuilder sb = new StringBuilder(String.valueOf(uid).length() + 45);
            sb.append("Notifying id token listeners about user ( ");
            sb.append(uid);
            sb.append(" ).");
            Log.d("FirebaseAuth", sb.toString());
        } else {
            Log.d("FirebaseAuth", "Notifying id token listeners about a sign-out event.");
        }
        if (firebaseUser != null) {
            str = firebaseUser.zze();
        } else {
            str = null;
        }
        firebaseAuth.zzp.execute(new zzl(firebaseAuth, new InternalTokenResult(str)));
    }

    static void zzG(FirebaseAuth firebaseAuth, FirebaseUser firebaseUser, zzwq zzwq, boolean z, boolean z2) {
        boolean z3;
        boolean z4;
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzwq);
        boolean z5 = false;
        boolean z6 = true;
        if (firebaseAuth.zzf == null || !firebaseUser.getUid().equals(firebaseAuth.zzf.getUid())) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (z3 || !z2) {
            FirebaseUser firebaseUser2 = firebaseAuth.zzf;
            if (firebaseUser2 == null) {
                z4 = true;
            } else {
                boolean z7 = !firebaseUser2.zzd().zze().equals(zzwq.zze());
                if (!z3 || z7) {
                    z5 = true;
                }
                z4 = true ^ z3;
                z6 = z5;
            }
            Preconditions.checkNotNull(firebaseUser);
            FirebaseUser firebaseUser3 = firebaseAuth.zzf;
            if (firebaseUser3 == null) {
                firebaseAuth.zzf = firebaseUser;
            } else {
                firebaseUser3.zzc(firebaseUser.getProviderData());
                if (!firebaseUser.isAnonymous()) {
                    firebaseAuth.zzf.zzb();
                }
                firebaseAuth.zzf.zzi(firebaseUser.getMultiFactor().getEnrolledFactors());
            }
            if (z) {
                firebaseAuth.zzl.zzd(firebaseAuth.zzf);
            }
            if (z6) {
                FirebaseUser firebaseUser4 = firebaseAuth.zzf;
                if (firebaseUser4 != null) {
                    firebaseUser4.zzh(zzwq);
                }
                zzF(firebaseAuth, firebaseAuth.zzf);
            }
            if (z4) {
                zzE(firebaseAuth, firebaseAuth.zzf);
            }
            if (z) {
                firebaseAuth.zzl.zze(firebaseUser, zzwq);
            }
            FirebaseUser firebaseUser5 = firebaseAuth.zzf;
            if (firebaseUser5 != null) {
                zzx(firebaseAuth).zzd(firebaseUser5.zzd());
            }
        }
    }

    /* access modifiers changed from: private */
    public final PhoneAuthProvider.OnVerificationStateChangedCallbacks zzJ(String str, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks) {
        return (!this.zzg.zzd() || str == null || !str.equals(this.zzg.zza())) ? onVerificationStateChangedCallbacks : new zzq(this, onVerificationStateChangedCallbacks);
    }

    private final boolean zzK(String str) {
        ActionCodeUrl parseLink = ActionCodeUrl.parseLink(str);
        return parseLink != null && !TextUtils.equals(this.zzk, parseLink.zza());
    }

    public static zzbi zzx(FirebaseAuth firebaseAuth) {
        if (firebaseAuth.zzo == null) {
            firebaseAuth.zzo = new zzbi((FirebaseApp) Preconditions.checkNotNull(firebaseAuth.zza));
        }
        return firebaseAuth.zzo;
    }

    public void addAuthStateListener(AuthStateListener listener) {
        this.zzd.add(listener);
        this.zzp.execute(new zzk(this, listener));
    }

    public void addIdTokenListener(IdTokenListener listener) {
        this.zzb.add(listener);
        ((zzbj) Preconditions.checkNotNull(this.zzp)).execute(new zzj(this, listener));
    }

    public Task<Void> applyActionCode(String code) {
        Preconditions.checkNotEmpty(code);
        return this.zze.zze(this.zza, code, this.zzk);
    }

    public Task<ActionCodeResult> checkActionCode(String code) {
        Preconditions.checkNotEmpty(code);
        return this.zze.zzf(this.zza, code, this.zzk);
    }

    public Task<Void> confirmPasswordReset(String code, String newPassword) {
        Preconditions.checkNotEmpty(code);
        Preconditions.checkNotEmpty(newPassword);
        return this.zze.zzg(this.zza, code, newPassword, this.zzk);
    }

    public Task<AuthResult> createUserWithEmailAndPassword(String email, String password) {
        Preconditions.checkNotEmpty(email);
        Preconditions.checkNotEmpty(password);
        return this.zze.zzh(this.zza, email, password, this.zzk, new zzs(this));
    }

    public Task<SignInMethodQueryResult> fetchSignInMethodsForEmail(String email) {
        Preconditions.checkNotEmpty(email);
        return this.zze.zzj(this.zza, email, this.zzk);
    }

    public final Task<GetTokenResult> getAccessToken(boolean z) {
        return zzc(this.zzf, z);
    }

    public FirebaseApp getApp() {
        return this.zza;
    }

    public FirebaseUser getCurrentUser() {
        return this.zzf;
    }

    public FirebaseAuthSettings getFirebaseAuthSettings() {
        return this.zzg;
    }

    public String getLanguageCode() {
        String str;
        synchronized (this.zzh) {
            str = this.zzi;
        }
        return str;
    }

    public Task<AuthResult> getPendingAuthResult() {
        return this.zzm.zza();
    }

    public String getTenantId() {
        String str;
        synchronized (this.zzj) {
            str = this.zzk;
        }
        return str;
    }

    public final String getUid() {
        FirebaseUser firebaseUser = this.zzf;
        if (firebaseUser == null) {
            return null;
        }
        return firebaseUser.getUid();
    }

    public boolean isSignInWithEmailLink(String link) {
        return EmailAuthCredential.zzi(link);
    }

    public void removeAuthStateListener(AuthStateListener listener) {
        this.zzd.remove(listener);
    }

    public void removeIdTokenListener(IdTokenListener listener) {
        this.zzb.remove(listener);
    }

    public Task<Void> sendPasswordResetEmail(String email) {
        Preconditions.checkNotEmpty(email);
        return sendPasswordResetEmail(email, (ActionCodeSettings) null);
    }

    public Task<Void> sendSignInLinkToEmail(String email, ActionCodeSettings actionCodeSettings) {
        Preconditions.checkNotEmpty(email);
        Preconditions.checkNotNull(actionCodeSettings);
        if (actionCodeSettings.canHandleCodeInApp()) {
            String str = this.zzi;
            if (str != null) {
                actionCodeSettings.zzf(str);
            }
            return this.zze.zzz(this.zza, email, actionCodeSettings, this.zzk);
        }
        throw new IllegalArgumentException("You must set canHandleCodeInApp in your ActionCodeSettings to true for Email-Link Sign-in.");
    }

    public Task<Void> setFirebaseUIVersion(String firebaseUIVersion) {
        return this.zze.zzA(firebaseUIVersion);
    }

    public void setLanguageCode(String languageCode) {
        Preconditions.checkNotEmpty(languageCode);
        synchronized (this.zzh) {
            this.zzi = languageCode;
        }
    }

    public void setTenantId(String tenantId) {
        Preconditions.checkNotEmpty(tenantId);
        synchronized (this.zzj) {
            this.zzk = tenantId;
        }
    }

    public Task<AuthResult> signInAnonymously() {
        FirebaseUser firebaseUser = this.zzf;
        if (firebaseUser == null || !firebaseUser.isAnonymous()) {
            return this.zze.zzB(this.zza, new zzs(this), this.zzk);
        }
        zzx zzx = (zzx) this.zzf;
        zzx.zzq(false);
        return Tasks.forResult(new zzr(zzx));
    }

    public Task<AuthResult> signInWithCredential(AuthCredential credential) {
        Preconditions.checkNotNull(credential);
        AuthCredential zza2 = credential.zza();
        if (zza2 instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) zza2;
            if (!emailAuthCredential.zzg()) {
                return this.zze.zzE(this.zza, emailAuthCredential.zzd(), Preconditions.checkNotEmpty(emailAuthCredential.zze()), this.zzk, new zzs(this));
            }
            if (zzK(Preconditions.checkNotEmpty(emailAuthCredential.zzf()))) {
                return Tasks.forException(zzto.zza(new Status(17072)));
            }
            return this.zze.zzF(this.zza, emailAuthCredential, new zzs(this));
        } else if (!(zza2 instanceof PhoneAuthCredential)) {
            return this.zze.zzC(this.zza, zza2, this.zzk, new zzs(this));
        } else {
            return this.zze.zzG(this.zza, (PhoneAuthCredential) zza2, this.zzk, new zzs(this));
        }
    }

    public Task<AuthResult> signInWithCustomToken(String token) {
        Preconditions.checkNotEmpty(token);
        return this.zze.zzD(this.zza, token, this.zzk, new zzs(this));
    }

    public Task<AuthResult> signInWithEmailAndPassword(String email, String password) {
        Preconditions.checkNotEmpty(email);
        Preconditions.checkNotEmpty(password);
        return this.zze.zzE(this.zza, email, password, this.zzk, new zzs(this));
    }

    public Task<AuthResult> signInWithEmailLink(String email, String link) {
        return signInWithCredential(EmailAuthProvider.getCredentialWithLink(email, link));
    }

    public void signOut() {
        zzC();
        zzbi zzbi = this.zzo;
        if (zzbi != null) {
            zzbi.zzb();
        }
    }

    public Task<AuthResult> startActivityForSignInWithProvider(Activity activity, FederatedAuthProvider federatedAuthProvider) {
        Preconditions.checkNotNull(federatedAuthProvider);
        Preconditions.checkNotNull(activity);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!this.zzm.zzi(activity, taskCompletionSource, this)) {
            return Tasks.forException(zzto.zza(new Status(17057)));
        }
        this.zzm.zzg(activity.getApplicationContext(), this);
        federatedAuthProvider.zzc(activity);
        return taskCompletionSource.getTask();
    }

    public void useAppLanguage() {
        synchronized (this.zzh) {
            this.zzi = zzun.zza();
        }
    }

    public void useEmulator(String host, int port) {
        Preconditions.checkNotEmpty(host);
        boolean z = false;
        if (port >= 0 && port <= 65535) {
            z = true;
        }
        Preconditions.checkArgument(z, "Port number must be in the range 0-65535");
        zzvr.zzf(this.zza, host, port);
    }

    public Task<String> verifyPasswordResetCode(String code) {
        Preconditions.checkNotEmpty(code);
        return this.zze.zzQ(this.zza, code, this.zzk);
    }

    public final void zzC() {
        Preconditions.checkNotNull(this.zzl);
        FirebaseUser firebaseUser = this.zzf;
        if (firebaseUser != null) {
            zzbg zzbg = this.zzl;
            Preconditions.checkNotNull(firebaseUser);
            zzbg.zzc(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[]{firebaseUser.getUid()}));
            this.zzf = null;
        }
        this.zzl.zzc("com.google.firebase.auth.FIREBASE_USER");
        zzF(this, (FirebaseUser) null);
        zzE(this, (FirebaseUser) null);
    }

    public final void zzD(FirebaseUser firebaseUser, zzwq zzwq, boolean z) {
        zzG(this, firebaseUser, zzwq, true, false);
    }

    public final void zzH(PhoneAuthOptions phoneAuthOptions) {
        boolean z;
        String str;
        if (phoneAuthOptions.zzk()) {
            FirebaseAuth zzb2 = phoneAuthOptions.zzb();
            if (((zzag) Preconditions.checkNotNull(phoneAuthOptions.zzc())).zze()) {
                str = Preconditions.checkNotEmpty(phoneAuthOptions.zzh());
            } else {
                str = Preconditions.checkNotEmpty(((PhoneMultiFactorInfo) Preconditions.checkNotNull(phoneAuthOptions.zzf())).getUid());
            }
            if (phoneAuthOptions.zzd() == null || !zzvh.zzd(str, phoneAuthOptions.zze(), (Activity) Preconditions.checkNotNull(phoneAuthOptions.zza()), phoneAuthOptions.zzi())) {
                zzb2.zzn.zza(zzb2, phoneAuthOptions.zzh(), (Activity) Preconditions.checkNotNull(phoneAuthOptions.zza()), zztk.zzb()).addOnCompleteListener(new zzp(zzb2, phoneAuthOptions));
                return;
            }
            return;
        }
        FirebaseAuth zzb3 = phoneAuthOptions.zzb();
        String checkNotEmpty = Preconditions.checkNotEmpty(phoneAuthOptions.zzh());
        long longValue = phoneAuthOptions.zzg().longValue();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        PhoneAuthProvider.OnVerificationStateChangedCallbacks zze2 = phoneAuthOptions.zze();
        Activity activity = (Activity) Preconditions.checkNotNull(phoneAuthOptions.zza());
        Executor zzi2 = phoneAuthOptions.zzi();
        if (phoneAuthOptions.zzd() != null) {
            z = true;
        } else {
            z = false;
        }
        if (z || !zzvh.zzd(checkNotEmpty, zze2, activity, zzi2)) {
            zzb3.zzn.zza(zzb3, checkNotEmpty, activity, zztk.zzb()).addOnCompleteListener(new zzo(zzb3, checkNotEmpty, longValue, timeUnit, zze2, activity, zzi2, z));
        }
    }

    public final void zzI(String str, long j, TimeUnit timeUnit, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor, boolean z, String str2, String str3) {
        long j2 = j;
        long convert = TimeUnit.SECONDS.convert(j, timeUnit);
        if (convert < 0 || convert > 120) {
            throw new IllegalArgumentException("We only support 0-120 seconds for sms-auto-retrieval timeout");
        }
        String str4 = str;
        this.zze.zzS(this.zza, new zzxd(str, convert, z, this.zzi, this.zzk, str2, zztk.zzb(), str3), zzJ(str, onVerificationStateChangedCallbacks), activity, executor);
    }

    public final Task<Void> zza(FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        return this.zze.zzi(firebaseUser, new zzi(this, firebaseUser));
    }

    public final Task<Void> zzb(FirebaseUser firebaseUser, MultiFactorAssertion multiFactorAssertion, String str) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(multiFactorAssertion);
        if (!(multiFactorAssertion instanceof PhoneMultiFactorAssertion)) {
            return Tasks.forException(zzto.zza(new Status(FirebaseError.ERROR_INTERNAL_ERROR)));
        }
        return this.zze.zzk(this.zza, (PhoneMultiFactorAssertion) multiFactorAssertion, firebaseUser, str, new zzs(this));
    }

    public final Task<GetTokenResult> zzc(FirebaseUser firebaseUser, boolean z) {
        if (firebaseUser == null) {
            return Tasks.forException(zzto.zza(new Status(FirebaseError.ERROR_NO_SIGNED_IN_USER)));
        }
        zzwq zzd2 = firebaseUser.zzd();
        if (!zzd2.zzj() || z) {
            return this.zze.zzm(this.zza, firebaseUser, zzd2.zzf(), new zzn(this));
        }
        return Tasks.forResult(zzay.zza(zzd2.zze()));
    }

    public final Task<AuthResult> zzd(FirebaseUser firebaseUser, AuthCredential authCredential) {
        Preconditions.checkNotNull(authCredential);
        Preconditions.checkNotNull(firebaseUser);
        return this.zze.zzn(this.zza, firebaseUser, authCredential.zza(), new zzt(this));
    }

    public final Task<Void> zze(FirebaseUser firebaseUser, AuthCredential authCredential) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(authCredential);
        AuthCredential zza2 = authCredential.zza();
        if (zza2 instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) zza2;
            if ("password".equals(emailAuthCredential.getSignInMethod())) {
                return this.zze.zzs(this.zza, firebaseUser, emailAuthCredential.zzd(), Preconditions.checkNotEmpty(emailAuthCredential.zze()), firebaseUser.getTenantId(), new zzt(this));
            } else if (zzK(Preconditions.checkNotEmpty(emailAuthCredential.zzf()))) {
                return Tasks.forException(zzto.zza(new Status(17072)));
            } else {
                return this.zze.zzq(this.zza, firebaseUser, emailAuthCredential, new zzt(this));
            }
        } else if (zza2 instanceof PhoneAuthCredential) {
            return this.zze.zzu(this.zza, firebaseUser, (PhoneAuthCredential) zza2, this.zzk, new zzt(this));
        } else {
            return this.zze.zzo(this.zza, firebaseUser, zza2, firebaseUser.getTenantId(), new zzt(this));
        }
    }

    public final Task<AuthResult> zzf(FirebaseUser firebaseUser, AuthCredential authCredential) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(authCredential);
        AuthCredential zza2 = authCredential.zza();
        if (zza2 instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) zza2;
            if ("password".equals(emailAuthCredential.getSignInMethod())) {
                return this.zze.zzt(this.zza, firebaseUser, emailAuthCredential.zzd(), Preconditions.checkNotEmpty(emailAuthCredential.zze()), firebaseUser.getTenantId(), new zzt(this));
            } else if (zzK(Preconditions.checkNotEmpty(emailAuthCredential.zzf()))) {
                return Tasks.forException(zzto.zza(new Status(17072)));
            } else {
                return this.zze.zzr(this.zza, firebaseUser, emailAuthCredential, new zzt(this));
            }
        } else if (zza2 instanceof PhoneAuthCredential) {
            return this.zze.zzv(this.zza, firebaseUser, (PhoneAuthCredential) zza2, this.zzk, new zzt(this));
        } else {
            return this.zze.zzp(this.zza, firebaseUser, zza2, firebaseUser.getTenantId(), new zzt(this));
        }
    }

    public final Task<Void> zzg(FirebaseUser firebaseUser, zzbk zzbk) {
        Preconditions.checkNotNull(firebaseUser);
        return this.zze.zzw(this.zza, firebaseUser, zzbk);
    }

    public final Task<AuthResult> zzh(MultiFactorAssertion multiFactorAssertion, zzag zzag, FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(multiFactorAssertion);
        Preconditions.checkNotNull(zzag);
        return this.zze.zzl(this.zza, firebaseUser, (PhoneMultiFactorAssertion) multiFactorAssertion, Preconditions.checkNotEmpty(zzag.zzd()), new zzs(this));
    }

    public final Task<Void> zzi(ActionCodeSettings actionCodeSettings, String str) {
        Preconditions.checkNotEmpty(str);
        if (this.zzi != null) {
            if (actionCodeSettings == null) {
                actionCodeSettings = ActionCodeSettings.zzb();
            }
            actionCodeSettings.zzf(this.zzi);
        }
        return this.zze.zzx(this.zza, actionCodeSettings, str);
    }

    public final Task<AuthResult> zzj(Activity activity, FederatedAuthProvider federatedAuthProvider, FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        Preconditions.checkNotNull(firebaseUser);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!this.zzm.zzj(activity, taskCompletionSource, this, firebaseUser)) {
            return Tasks.forException(zzto.zza(new Status(17057)));
        }
        this.zzm.zzh(activity.getApplicationContext(), this, firebaseUser);
        federatedAuthProvider.zza(activity);
        return taskCompletionSource.getTask();
    }

    public final Task<AuthResult> zzk(Activity activity, FederatedAuthProvider federatedAuthProvider, FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(federatedAuthProvider);
        Preconditions.checkNotNull(firebaseUser);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        if (!this.zzm.zzj(activity, taskCompletionSource, this, firebaseUser)) {
            return Tasks.forException(zzto.zza(new Status(17057)));
        }
        this.zzm.zzh(activity.getApplicationContext(), this, firebaseUser);
        federatedAuthProvider.zzb(activity);
        return taskCompletionSource.getTask();
    }

    public final Task<Void> zzl(FirebaseUser firebaseUser, String str) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotEmpty(str);
        return this.zze.zzJ(this.zza, firebaseUser, str, new zzt(this)).continueWithTask(new zzr(this));
    }

    public final Task<AuthResult> zzm(FirebaseUser firebaseUser, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(firebaseUser);
        return this.zze.zzK(this.zza, firebaseUser, str, new zzt(this));
    }

    public final Task<Void> zzn(FirebaseUser firebaseUser, String str) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotEmpty(str);
        return this.zze.zzL(this.zza, firebaseUser, str, new zzt(this));
    }

    public final Task<Void> zzo(FirebaseUser firebaseUser, String str) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotEmpty(str);
        return this.zze.zzM(this.zza, firebaseUser, str, new zzt(this));
    }

    public final Task<Void> zzp(FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(phoneAuthCredential);
        return this.zze.zzN(this.zza, firebaseUser, phoneAuthCredential.clone(), new zzt(this));
    }

    public final Task<Void> zzq(FirebaseUser firebaseUser, UserProfileChangeRequest userProfileChangeRequest) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(userProfileChangeRequest);
        return this.zze.zzO(this.zza, firebaseUser, userProfileChangeRequest, new zzt(this));
    }

    public final Task<Void> zzr(String str, String str2, ActionCodeSettings actionCodeSettings) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        if (actionCodeSettings == null) {
            actionCodeSettings = ActionCodeSettings.zzb();
        }
        String str3 = this.zzi;
        if (str3 != null) {
            actionCodeSettings.zzf(str3);
        }
        return this.zze.zzP(str, str2, actionCodeSettings);
    }

    public final synchronized zzbi zzw() {
        return zzx(this);
    }

    public void removeIdTokenListener(com.google.firebase.auth.internal.IdTokenListener listenerToRemove) {
        Preconditions.checkNotNull(listenerToRemove);
        this.zzc.remove(listenerToRemove);
        zzw().zzc(this.zzc.size());
    }

    public Task<Void> updateCurrentUser(FirebaseUser user) {
        String str;
        if (user != null) {
            String tenantId = user.getTenantId();
            if ((tenantId != null && !tenantId.equals(this.zzk)) || ((str = this.zzk) != null && !str.equals(tenantId))) {
                return Tasks.forException(zzto.zza(new Status(17072)));
            }
            String apiKey = user.zza().getOptions().getApiKey();
            String apiKey2 = this.zza.getOptions().getApiKey();
            if (!user.zzd().zzj() || !apiKey2.equals(apiKey)) {
                return zzg(user, new zzu(this));
            }
            zzD(zzx.zzk(this.zza, user), user.zzd(), true);
            return Tasks.forResult(null);
        }
        throw new IllegalArgumentException("Cannot update current user with null user!");
    }

    public static FirebaseAuth getInstance(FirebaseApp firebaseApp) {
        return (FirebaseAuth) firebaseApp.get(FirebaseAuth.class);
    }

    public Task<Void> sendPasswordResetEmail(String email, ActionCodeSettings actionCodeSettings) {
        Preconditions.checkNotEmpty(email);
        if (actionCodeSettings == null) {
            actionCodeSettings = ActionCodeSettings.zzb();
        }
        String str = this.zzi;
        if (str != null) {
            actionCodeSettings.zzf(str);
        }
        actionCodeSettings.zzg(1);
        return this.zze.zzy(this.zza, email, actionCodeSettings, this.zzk);
    }

    public void addIdTokenListener(com.google.firebase.auth.internal.IdTokenListener listener) {
        Preconditions.checkNotNull(listener);
        this.zzc.add(listener);
        zzw().zzc(this.zzc.size());
    }
}
