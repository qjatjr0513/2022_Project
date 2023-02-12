package com.google.firebase.auth.internal;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.internal.p010firebaseauthapi.zzah;
import com.google.android.gms.internal.p010firebaseauthapi.zzal;
import com.google.android.gms.internal.p010firebaseauthapi.zzay;
import com.google.android.gms.internal.p010firebaseauthapi.zzdj;
import com.google.android.gms.internal.p010firebaseauthapi.zzdo;
import com.google.android.gms.internal.p010firebaseauthapi.zzdt;
import com.google.android.gms.internal.p010firebaseauthapi.zzdu;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzk {
    private static zzk zza;
    private final String zzb;
    private final zzdu zzc;

    private zzk(Context context, String str, boolean z) {
        zzdu zzdu;
        this.zzb = str;
        try {
            zzdj.zza();
            zzdt zzdt = new zzdt();
            zzdt.zzf(context, "GenericIdpKeyset", String.format("com.google.firebase.auth.api.crypto.%s", new Object[]{str}));
            zzdt.zzd(zzdo.zza);
            zzdt.zze(String.format("android-keystore://firebear_master_key_id.%s", new Object[]{str}));
            zzdu = zzdt.zzg();
        } catch (IOException | GeneralSecurityException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.e("FirebearCryptoHelper", valueOf.length() != 0 ? "Exception encountered during crypto setup:\n".concat(valueOf) : new String("Exception encountered during crypto setup:\n"));
            zzdu = null;
        }
        this.zzc = zzdu;
    }

    public static zzk zza(Context context, String str) {
        String str2;
        zzk zzk = zza;
        if (zzk == null || ((str2 = zzk.zzb) != str && (str2 == null || !str2.equals(str)))) {
            zza = new zzk(context, str, true);
        }
        return zza;
    }

    public final String zzb(String str) {
        String str2;
        zzdu zzdu = this.zzc;
        if (zzdu != null) {
            try {
                synchronized (zzdu) {
                    str2 = new String(((zzal) this.zzc.zza().zze(zzal.class)).zza(Base64.decode(str, 8), (byte[]) null), "UTF-8");
                }
                return str2;
            } catch (UnsupportedEncodingException | GeneralSecurityException e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.e("FirebearCryptoHelper", valueOf.length() != 0 ? "Exception encountered while decrypting bytes:\n".concat(valueOf) : new String("Exception encountered while decrypting bytes:\n"));
                return null;
            }
        } else {
            Log.e("FirebearCryptoHelper", "KeysetManager failed to initialize - unable to decrypt payload");
            return null;
        }
    }

    public final String zzc() {
        if (this.zzc == null) {
            Log.e("FirebearCryptoHelper", "KeysetManager failed to initialize - unable to get Public key");
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        zzay zza2 = zzah.zza(byteArrayOutputStream);
        try {
            synchronized (this.zzc) {
                this.zzc.zza().zzb().zzh(zza2);
            }
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 8);
        } catch (IOException | GeneralSecurityException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.e("FirebearCryptoHelper", valueOf.length() != 0 ? "Exception encountered when attempting to get Public Key:\n".concat(valueOf) : new String("Exception encountered when attempting to get Public Key:\n"));
            return null;
        }
    }
}
