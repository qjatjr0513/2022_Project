package com.google.android.gms.internal.p010firebaseauthapi;

import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import com.google.android.gms.stats.CodePackage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.ProviderException;
import javax.crypto.KeyGenerator;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdt */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzdt {
    /* access modifiers changed from: private */
    public zzay zza = null;
    private String zzb = null;
    /* access modifiers changed from: private */
    public zzag zzc = null;
    private zzaq zzd = null;
    /* access modifiers changed from: private */
    public zzax zze;
    private zzdy zzf = null;

    private final zzag zzh() throws GeneralSecurityException {
        if (Build.VERSION.SDK_INT >= 23) {
            zzdx zzdx = new zzdx();
            boolean zzc2 = zzdx.zzc(this.zzb);
            if (!zzc2) {
                try {
                    String str = this.zzb;
                    if (!new zzdx().zzc(str)) {
                        String zza2 = zzli.zza("android-keystore://", str);
                        KeyGenerator instance = KeyGenerator.getInstance("AES", "AndroidKeyStore");
                        instance.init(new KeyGenParameterSpec.Builder(zza2, 3).setKeySize(256).setBlockModes(new String[]{CodePackage.GCM}).setEncryptionPaddings(new String[]{"NoPadding"}).build());
                        instance.generateKey();
                    } else {
                        throw new IllegalArgumentException(String.format("cannot generate a new key %s because it already exists; please delete it with deleteKey() and try again", new Object[]{str}));
                    }
                } catch (GeneralSecurityException | ProviderException e) {
                    Log.w(zzdu.zzb, "cannot use Android Keystore, it'll be disabled", e);
                    return null;
                }
            }
            try {
                return zzdx.zza(this.zzb);
            } catch (GeneralSecurityException | ProviderException e2) {
                if (!zzc2) {
                    Log.w(zzdu.zzb, "cannot use Android Keystore, it'll be disabled", e2);
                    return null;
                }
                throw new KeyStoreException(String.format("the master key %s exists but is unusable", new Object[]{this.zzb}), e2);
            }
        } else {
            Log.w(zzdu.zzb, "Android Keystore requires at least Android M");
            return null;
        }
    }

    private final zzax zzi() throws GeneralSecurityException, IOException {
        zzag zzag = this.zzc;
        if (zzag != null) {
            try {
                return zzax.zzf(zzaw.zzi(this.zzf, zzag));
            } catch (zzaae | GeneralSecurityException e) {
                Log.w(zzdu.zzb, "cannot decrypt keyset: ", e);
            }
        }
        return zzax.zzf(zzai.zzb(this.zzf));
    }

    @Deprecated
    public final zzdt zzd(zzij zzij) {
        int i;
        String zzf2 = zzij.zzf();
        byte[] zzs = zzij.zze().zzs();
        zzjk zzd2 = zzij.zzd();
        int i2 = zzdu.zza;
        zzjk zzjk = zzjk.UNKNOWN_PREFIX;
        switch (zzd2.ordinal()) {
            case 1:
                i = 1;
                break;
            case 2:
                i = 2;
                break;
            case 3:
                i = 3;
                break;
            case 4:
                i = 4;
                break;
            default:
                throw new IllegalArgumentException("Unknown output prefix type");
        }
        this.zzd = zzaq.zze(zzf2, zzs, i);
        return this;
    }

    public final zzdt zze(String str) {
        if (str.startsWith("android-keystore://")) {
            this.zzb = str;
            return this;
        }
        throw new IllegalArgumentException("key URI must start with android-keystore://");
    }

    public final zzdt zzf(Context context, String str, String str2) throws IOException {
        if (context != null) {
            this.zzf = new zzdy(context, "GenericIdpKeyset", str2);
            this.zza = new zzdz(context, "GenericIdpKeyset", str2);
            return this;
        }
        throw new IllegalArgumentException("need an Android context");
    }

    public final synchronized zzdu zzg() throws GeneralSecurityException, IOException {
        zzax zzax;
        if (this.zzb != null) {
            this.zzc = zzh();
        }
        try {
            zzax = zzi();
        } catch (FileNotFoundException e) {
            Log.i(zzdu.zzb, "keyset not found, will generate a new one", e);
            if (this.zzd != null) {
                zzax = zzax.zze();
                zzax.zzc(this.zzd);
                zzax.zzd(zzax.zzb().zzd().zzb(0).zza());
                if (this.zzc != null) {
                    zzax.zzb().zzg(this.zza, this.zzc);
                } else {
                    zzai.zza(zzax.zzb(), this.zza);
                }
            } else {
                throw new GeneralSecurityException("cannot read or generate keyset");
            }
        }
        this.zze = zzax;
        return new zzdu(this, (zzds) null);
    }
}
