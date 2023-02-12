package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public enum zzabn {
    DOUBLE(0, 1, zzacd.DOUBLE),
    FLOAT(1, 1, zzacd.FLOAT),
    INT64(2, 1, zzacd.LONG),
    UINT64(3, 1, zzacd.LONG),
    INT32(4, 1, zzacd.INT),
    FIXED64(5, 1, zzacd.LONG),
    FIXED32(6, 1, zzacd.INT),
    BOOL(7, 1, zzacd.BOOLEAN),
    STRING(8, 1, zzacd.STRING),
    MESSAGE(9, 1, zzacd.MESSAGE),
    BYTES(10, 1, zzacd.BYTE_STRING),
    UINT32(11, 1, zzacd.INT),
    ENUM(12, 1, zzacd.ENUM),
    SFIXED32(13, 1, zzacd.INT),
    SFIXED64(14, 1, zzacd.LONG),
    SINT32(15, 1, zzacd.INT),
    SINT64(16, 1, zzacd.LONG),
    GROUP(17, 1, zzacd.MESSAGE),
    DOUBLE_LIST(18, 2, zzacd.DOUBLE),
    FLOAT_LIST(19, 2, zzacd.FLOAT),
    INT64_LIST(20, 2, zzacd.LONG),
    UINT64_LIST(21, 2, zzacd.LONG),
    INT32_LIST(22, 2, zzacd.INT),
    FIXED64_LIST(23, 2, zzacd.LONG),
    FIXED32_LIST(24, 2, zzacd.INT),
    BOOL_LIST(25, 2, zzacd.BOOLEAN),
    STRING_LIST(26, 2, zzacd.STRING),
    MESSAGE_LIST(27, 2, zzacd.MESSAGE),
    BYTES_LIST(28, 2, zzacd.BYTE_STRING),
    UINT32_LIST(29, 2, zzacd.INT),
    ENUM_LIST(30, 2, zzacd.ENUM),
    SFIXED32_LIST(31, 2, zzacd.INT),
    SFIXED64_LIST(32, 2, zzacd.LONG),
    SINT32_LIST(33, 2, zzacd.INT),
    SINT64_LIST(34, 2, zzacd.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzacd.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzacd.FLOAT),
    INT64_LIST_PACKED(37, 3, zzacd.LONG),
    UINT64_LIST_PACKED(38, 3, zzacd.LONG),
    INT32_LIST_PACKED(39, 3, zzacd.INT),
    FIXED64_LIST_PACKED(40, 3, zzacd.LONG),
    FIXED32_LIST_PACKED(41, 3, zzacd.INT),
    BOOL_LIST_PACKED(42, 3, zzacd.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzacd.INT),
    ENUM_LIST_PACKED(44, 3, zzacd.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzacd.INT),
    SFIXED64_LIST_PACKED(46, 3, zzacd.LONG),
    SINT32_LIST_PACKED(47, 3, zzacd.INT),
    SINT64_LIST_PACKED(48, 3, zzacd.LONG),
    GROUP_LIST(49, 2, zzacd.MESSAGE),
    MAP(50, 4, zzacd.VOID);
    
    private static final zzabn[] zzZ = null;
    private final zzacd zzab;
    private final int zzac;
    private final Class<?> zzad;

    static {
        zzZ = new zzabn[r1];
        for (zzabn zzabn : values()) {
            zzZ[zzabn.zzac] = zzabn;
        }
    }

    private zzabn(int i, int i2, zzacd zzacd) {
        this.zzac = i;
        this.zzab = zzacd;
        zzacd zzacd2 = zzacd.VOID;
        switch (i2 - 1) {
            case 1:
                this.zzad = zzacd.zza();
                break;
            case 3:
                this.zzad = zzacd.zza();
                break;
            default:
                this.zzad = null;
                break;
        }
        if (i2 == 1) {
            zzacd.ordinal();
        }
    }

    public final int zza() {
        return this.zzac;
    }
}
