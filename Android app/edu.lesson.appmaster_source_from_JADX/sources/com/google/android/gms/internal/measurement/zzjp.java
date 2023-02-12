package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
public enum zzjp {
    DOUBLE(0, 1, zzki.DOUBLE),
    FLOAT(1, 1, zzki.FLOAT),
    INT64(2, 1, zzki.LONG),
    UINT64(3, 1, zzki.LONG),
    INT32(4, 1, zzki.INT),
    FIXED64(5, 1, zzki.LONG),
    FIXED32(6, 1, zzki.INT),
    BOOL(7, 1, zzki.BOOLEAN),
    STRING(8, 1, zzki.STRING),
    MESSAGE(9, 1, zzki.MESSAGE),
    BYTES(10, 1, zzki.BYTE_STRING),
    UINT32(11, 1, zzki.INT),
    ENUM(12, 1, zzki.ENUM),
    SFIXED32(13, 1, zzki.INT),
    SFIXED64(14, 1, zzki.LONG),
    SINT32(15, 1, zzki.INT),
    SINT64(16, 1, zzki.LONG),
    GROUP(17, 1, zzki.MESSAGE),
    DOUBLE_LIST(18, 2, zzki.DOUBLE),
    FLOAT_LIST(19, 2, zzki.FLOAT),
    INT64_LIST(20, 2, zzki.LONG),
    UINT64_LIST(21, 2, zzki.LONG),
    INT32_LIST(22, 2, zzki.INT),
    FIXED64_LIST(23, 2, zzki.LONG),
    FIXED32_LIST(24, 2, zzki.INT),
    BOOL_LIST(25, 2, zzki.BOOLEAN),
    STRING_LIST(26, 2, zzki.STRING),
    MESSAGE_LIST(27, 2, zzki.MESSAGE),
    BYTES_LIST(28, 2, zzki.BYTE_STRING),
    UINT32_LIST(29, 2, zzki.INT),
    ENUM_LIST(30, 2, zzki.ENUM),
    SFIXED32_LIST(31, 2, zzki.INT),
    SFIXED64_LIST(32, 2, zzki.LONG),
    SINT32_LIST(33, 2, zzki.INT),
    SINT64_LIST(34, 2, zzki.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzki.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzki.FLOAT),
    INT64_LIST_PACKED(37, 3, zzki.LONG),
    UINT64_LIST_PACKED(38, 3, zzki.LONG),
    INT32_LIST_PACKED(39, 3, zzki.INT),
    FIXED64_LIST_PACKED(40, 3, zzki.LONG),
    FIXED32_LIST_PACKED(41, 3, zzki.INT),
    BOOL_LIST_PACKED(42, 3, zzki.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzki.INT),
    ENUM_LIST_PACKED(44, 3, zzki.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzki.INT),
    SFIXED64_LIST_PACKED(46, 3, zzki.LONG),
    SINT32_LIST_PACKED(47, 3, zzki.INT),
    SINT64_LIST_PACKED(48, 3, zzki.LONG),
    GROUP_LIST(49, 2, zzki.MESSAGE),
    MAP(50, 4, zzki.VOID);
    
    private static final zzjp[] zzZ = null;
    private final zzki zzab;
    private final int zzac;
    private final Class<?> zzad;

    static {
        zzZ = new zzjp[r1];
        for (zzjp zzjp : values()) {
            zzZ[zzjp.zzac] = zzjp;
        }
    }

    private zzjp(int i, int i2, zzki zzki) {
        this.zzac = i;
        this.zzab = zzki;
        zzki zzki2 = zzki.VOID;
        switch (i2 - 1) {
            case 1:
                this.zzad = zzki.zza();
                break;
            case 3:
                this.zzad = zzki.zza();
                break;
            default:
                this.zzad = null;
                break;
        }
        if (i2 == 1) {
            zzki.ordinal();
        }
    }

    public final int zza() {
        return this.zzac;
    }
}
