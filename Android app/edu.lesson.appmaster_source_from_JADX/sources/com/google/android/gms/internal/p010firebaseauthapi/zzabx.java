package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabx */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzabx {
    static String zza(zzyu zzyu) {
        StringBuilder sb = new StringBuilder(zzyu.zzd());
        for (int i = 0; i < zzyu.zzd(); i++) {
            byte zza = zzyu.zza(i);
            switch (zza) {
                case 7:
                    sb.append("\\a");
                    break;
                case 8:
                    sb.append("\\b");
                    break;
                case 9:
                    sb.append("\\t");
                    break;
                case 10:
                    sb.append("\\n");
                    break;
                case 11:
                    sb.append("\\v");
                    break;
                case 12:
                    sb.append("\\f");
                    break;
                case 13:
                    sb.append("\\r");
                    break;
                case 34:
                    sb.append("\\\"");
                    break;
                case 39:
                    sb.append("\\'");
                    break;
                case 92:
                    sb.append("\\\\");
                    break;
                default:
                    if (zza >= 32 && zza <= 126) {
                        sb.append((char) zza);
                        break;
                    } else {
                        sb.append('\\');
                        sb.append((char) (((zza >>> 6) & 3) + 48));
                        sb.append((char) (((zza >>> 3) & 7) + 48));
                        sb.append((char) ((zza & 7) + 48));
                        break;
                    }
                    break;
            }
        }
        return sb.toString();
    }
}
