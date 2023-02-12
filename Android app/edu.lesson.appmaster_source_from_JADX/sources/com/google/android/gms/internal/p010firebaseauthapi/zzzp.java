package com.google.android.gms.internal.p010firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzp */
/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
public enum zzzp {
    DOUBLE(0, 1, zzaaf.DOUBLE),
    FLOAT(1, 1, zzaaf.FLOAT),
    INT64(2, 1, zzaaf.LONG),
    UINT64(3, 1, zzaaf.LONG),
    INT32(4, 1, zzaaf.INT),
    FIXED64(5, 1, zzaaf.LONG),
    FIXED32(6, 1, zzaaf.INT),
    BOOL(7, 1, zzaaf.BOOLEAN),
    STRING(8, 1, zzaaf.STRING),
    MESSAGE(9, 1, zzaaf.MESSAGE),
    BYTES(10, 1, zzaaf.BYTE_STRING),
    UINT32(11, 1, zzaaf.INT),
    ENUM(12, 1, zzaaf.ENUM),
    SFIXED32(13, 1, zzaaf.INT),
    SFIXED64(14, 1, zzaaf.LONG),
    SINT32(15, 1, zzaaf.INT),
    SINT64(16, 1, zzaaf.LONG),
    GROUP(17, 1, zzaaf.MESSAGE),
    DOUBLE_LIST(18, 2, zzaaf.DOUBLE),
    FLOAT_LIST(19, 2, zzaaf.FLOAT),
    INT64_LIST(20, 2, zzaaf.LONG),
    UINT64_LIST(21, 2, zzaaf.LONG),
    INT32_LIST(22, 2, zzaaf.INT),
    FIXED64_LIST(23, 2, zzaaf.LONG),
    FIXED32_LIST(24, 2, zzaaf.INT),
    BOOL_LIST(25, 2, zzaaf.BOOLEAN),
    STRING_LIST(26, 2, zzaaf.STRING),
    MESSAGE_LIST(27, 2, zzaaf.MESSAGE),
    BYTES_LIST(28, 2, zzaaf.BYTE_STRING),
    UINT32_LIST(29, 2, zzaaf.INT),
    ENUM_LIST(30, 2, zzaaf.ENUM),
    SFIXED32_LIST(31, 2, zzaaf.INT),
    SFIXED64_LIST(32, 2, zzaaf.LONG),
    SINT32_LIST(33, 2, zzaaf.INT),
    SINT64_LIST(34, 2, zzaaf.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzaaf.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzaaf.FLOAT),
    INT64_LIST_PACKED(37, 3, zzaaf.LONG),
    UINT64_LIST_PACKED(38, 3, zzaaf.LONG),
    INT32_LIST_PACKED(39, 3, zzaaf.INT),
    FIXED64_LIST_PACKED(40, 3, zzaaf.LONG),
    FIXED32_LIST_PACKED(41, 3, zzaaf.INT),
    BOOL_LIST_PACKED(42, 3, zzaaf.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzaaf.INT),
    ENUM_LIST_PACKED(44, 3, zzaaf.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzaaf.INT),
    SFIXED64_LIST_PACKED(46, 3, zzaaf.LONG),
    SINT32_LIST_PACKED(47, 3, zzaaf.INT),
    SINT64_LIST_PACKED(48, 3, zzaaf.LONG),
    GROUP_LIST(49, 2, zzaaf.MESSAGE),
    MAP(50, 4, zzaaf.VOID);
    
    private static final zzzp[] zzZ = null;
    private final zzaaf zzab;
    private final int zzac;
    private final Class<?> zzad;

    static {
        zzZ = new zzzp[r1];
        for (zzzp zzzp : values()) {
            zzZ[zzzp.zzac] = zzzp;
        }
    }

    private zzzp(int i, int i2, zzaaf zzaaf) {
        this.zzac = i;
        this.zzab = zzaaf;
        zzaaf zzaaf2 = zzaaf.VOID;
        switch (i2 - 1) {
            case 1:
                this.zzad = zzaaf.zza();
                break;
            case 3:
                this.zzad = zzaaf.zza();
                break;
            default:
                this.zzad = null;
                break;
        }
        if (i2 == 1) {
            zzaaf.ordinal();
        }
    }

    public final int zza() {
        return this.zzac;
    }
}
