package com.google.android.gms.internal.p010firebaseauthapi;

import android.util.Log;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Arrays;
import java.util.Locale;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdx */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzdx implements zzaz {
    private static final String zza = zzdx.class.getSimpleName();
    private KeyStore zzb = new zzdw().zza;

    public final synchronized zzag zza(String str) throws GeneralSecurityException {
        zzdv zzdv;
        zzdv = new zzdv(zzli.zza("android-keystore://", str), this.zzb);
        byte[] zza2 = zzlg.zza(10);
        byte[] bArr = new byte[0];
        if (!Arrays.equals(zza2, zzdv.zza(zzdv.zzb(zza2, bArr), bArr))) {
            throw new KeyStoreException("cannot use Android Keystore: encryption/decryption of non-empty message and empty aad returns an incorrect result");
        }
        return zzdv;
    }

    public final synchronized boolean zzb(String str) {
        return str.toLowerCase(Locale.US).startsWith("android-keystore://");
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zzc(String str) throws GeneralSecurityException {
        String zza2;
        zza2 = zzli.zza("android-keystore://", str);
        try {
        } catch (IOException e) {
            throw new GeneralSecurityException(e);
        } catch (InterruptedException e2) {
        } catch (NullPointerException e3) {
            Log.w(zza, "Keystore is temporarily unavailable, wait 20ms, reinitialize Keystore and try again.");
            Thread.sleep(20);
            KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
            this.zzb = instance;
            instance.load((KeyStore.LoadStoreParameter) null);
        }
        return this.zzb.containsAlias(zza2);
        return this.zzb.containsAlias(zza2);
    }
}
