package com.google.android.gms.internal.p010firebaseauthapi;

import java.lang.reflect.Type;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuh */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public final class zzuh {
    private static final String zza = zzuh.class.getName();

    private zzuh() {
    }

    public static Object zza(String str, Type type) throws zzpz {
        if (type == String.class) {
            try {
                zzvt zzvt = new zzvt();
                zzvt.zzb(str);
                if (zzvt.zzd()) {
                    return zzvt.zzc();
                }
                String valueOf = String.valueOf(str);
                throw new zzpz(valueOf.length() != 0 ? "No error message: ".concat(valueOf) : new String("No error message: "));
            } catch (Exception e) {
                String valueOf2 = String.valueOf(e.getMessage());
                throw new zzpz(valueOf2.length() != 0 ? "Json conversion failed! ".concat(valueOf2) : new String("Json conversion failed! "), e);
            }
        } else if (type == Void.class) {
            return null;
        } else {
            try {
                zzuj zzuj = (zzuj) ((Class) type).getConstructor(new Class[0]).newInstance(new Object[0]);
                try {
                    zzuj.zza(str);
                    return zzuj;
                } catch (Exception e2) {
                    String valueOf3 = String.valueOf(e2.getMessage());
                    throw new zzpz(valueOf3.length() != 0 ? "Json conversion failed! ".concat(valueOf3) : new String("Json conversion failed! "), e2);
                }
            } catch (Exception e3) {
                String valueOf4 = String.valueOf(type);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf4).length() + 38);
                sb.append("Instantiation of JsonResponse failed! ");
                sb.append(valueOf4);
                throw new zzpz(sb.toString(), e3);
            }
        }
    }
}
