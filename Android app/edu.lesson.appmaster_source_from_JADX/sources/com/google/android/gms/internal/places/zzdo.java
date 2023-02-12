package com.google.android.gms.internal.places;

final class zzdo {
    static String zzd(zzw zzw) {
        zzdn zzdn = new zzdn(zzw);
        StringBuilder sb = new StringBuilder(zzdn.size());
        for (int i = 0; i < zzdn.size(); i++) {
            byte zzi = zzdn.zzi(i);
            switch (zzi) {
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
                    if (zzi >= 32 && zzi <= 126) {
                        sb.append((char) zzi);
                        break;
                    } else {
                        sb.append('\\');
                        sb.append((char) (((zzi >>> 6) & 3) + 48));
                        sb.append((char) (((zzi >>> 3) & 7) + 48));
                        sb.append((char) ((zzi & 7) + 48));
                        break;
                    }
            }
        }
        return sb.toString();
    }
}
