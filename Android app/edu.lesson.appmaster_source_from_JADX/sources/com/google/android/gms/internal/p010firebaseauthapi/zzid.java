package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzid */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public enum zzid implements zzzy {
    UNKNOWN_KEYMATERIAL(0),
    SYMMETRIC(1),
    ASYMMETRIC_PRIVATE(2),
    ASYMMETRIC_PUBLIC(3),
    REMOTE(4),
    UNRECOGNIZED(-1);
    
    private static final zzzz<zzid> zzg = null;
    private final int zzi;

    static {
        zzg = new zzic();
    }

    private zzid(int i) {
        this.zzi = i;
    }

    public static zzid zzb(int i) {
        switch (i) {
            case 0:
                return UNKNOWN_KEYMATERIAL;
            case 1:
                return SYMMETRIC;
            case 2:
                return ASYMMETRIC_PRIVATE;
            case 3:
                return ASYMMETRIC_PUBLIC;
            case 4:
                return REMOTE;
            default:
                return null;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(getClass().getName());
        sb.append('@');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this != UNRECOGNIZED) {
            sb.append(" number=");
            sb.append(zza());
        }
        sb.append(" name=");
        sb.append(name());
        sb.append('>');
        return sb.toString();
    }

    public final int zza() {
        if (this != UNRECOGNIZED) {
            return this.zzi;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
