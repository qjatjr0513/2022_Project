package com.google.android.libraries.places.internal;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzda {
    public static String zza(PackageManager packageManager, String str) {
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 64);
            if (!(packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length == 0)) {
                if (packageInfo.signatures[0] != null) {
                    return zzb(packageInfo.signatures[0]);
                }
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(str);
            Log.e("CredentialsHelper", valueOf.length() != 0 ? "Unable to get certificate fingerprint for package: ".concat(valueOf) : new String("Unable to get certificate fingerprint for package: "), e);
            return null;
        }
    }

    private static String zzb(Signature signature) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-1").digest(signature.toByteArray());
            return zzjn.zzd().zze(digest, 0, digest.length);
        } catch (NoSuchAlgorithmException e) {
            Log.e("CredentialsHelper", "Unable to get certificate fingerprint.", e);
            return null;
        }
    }
}
