package com.google.android.gms.internal.places;

import java.lang.reflect.Type;

public enum zzaw {
    DOUBLE(0, zzay.SCALAR, zzbm.DOUBLE),
    FLOAT(1, zzay.SCALAR, zzbm.FLOAT),
    INT64(2, zzay.SCALAR, zzbm.LONG),
    UINT64(3, zzay.SCALAR, zzbm.LONG),
    INT32(4, zzay.SCALAR, zzbm.INT),
    FIXED64(5, zzay.SCALAR, zzbm.LONG),
    FIXED32(6, zzay.SCALAR, zzbm.INT),
    BOOL(7, zzay.SCALAR, zzbm.BOOLEAN),
    STRING(8, zzay.SCALAR, zzbm.STRING),
    MESSAGE(9, zzay.SCALAR, zzbm.MESSAGE),
    BYTES(10, zzay.SCALAR, zzbm.BYTE_STRING),
    UINT32(11, zzay.SCALAR, zzbm.INT),
    ENUM(12, zzay.SCALAR, zzbm.ENUM),
    SFIXED32(13, zzay.SCALAR, zzbm.INT),
    SFIXED64(14, zzay.SCALAR, zzbm.LONG),
    SINT32(15, zzay.SCALAR, zzbm.INT),
    SINT64(16, zzay.SCALAR, zzbm.LONG),
    GROUP(17, zzay.SCALAR, zzbm.MESSAGE),
    DOUBLE_LIST(18, zzay.VECTOR, zzbm.DOUBLE),
    FLOAT_LIST(19, zzay.VECTOR, zzbm.FLOAT),
    INT64_LIST(20, zzay.VECTOR, zzbm.LONG),
    UINT64_LIST(21, zzay.VECTOR, zzbm.LONG),
    INT32_LIST(22, zzay.VECTOR, zzbm.INT),
    FIXED64_LIST(23, zzay.VECTOR, zzbm.LONG),
    FIXED32_LIST(24, zzay.VECTOR, zzbm.INT),
    BOOL_LIST(25, zzay.VECTOR, zzbm.BOOLEAN),
    STRING_LIST(26, zzay.VECTOR, zzbm.STRING),
    MESSAGE_LIST(27, zzay.VECTOR, zzbm.MESSAGE),
    BYTES_LIST(28, zzay.VECTOR, zzbm.BYTE_STRING),
    UINT32_LIST(29, zzay.VECTOR, zzbm.INT),
    ENUM_LIST(30, zzay.VECTOR, zzbm.ENUM),
    SFIXED32_LIST(31, zzay.VECTOR, zzbm.INT),
    SFIXED64_LIST(32, zzay.VECTOR, zzbm.LONG),
    SINT32_LIST(33, zzay.VECTOR, zzbm.INT),
    SINT64_LIST(34, zzay.VECTOR, zzbm.LONG),
    DOUBLE_LIST_PACKED(35, zzay.PACKED_VECTOR, zzbm.DOUBLE),
    FLOAT_LIST_PACKED(36, zzay.PACKED_VECTOR, zzbm.FLOAT),
    INT64_LIST_PACKED(37, zzay.PACKED_VECTOR, zzbm.LONG),
    UINT64_LIST_PACKED(38, zzay.PACKED_VECTOR, zzbm.LONG),
    INT32_LIST_PACKED(39, zzay.PACKED_VECTOR, zzbm.INT),
    FIXED64_LIST_PACKED(40, zzay.PACKED_VECTOR, zzbm.LONG),
    FIXED32_LIST_PACKED(41, zzay.PACKED_VECTOR, zzbm.INT),
    BOOL_LIST_PACKED(42, zzay.PACKED_VECTOR, zzbm.BOOLEAN),
    UINT32_LIST_PACKED(43, zzay.PACKED_VECTOR, zzbm.INT),
    ENUM_LIST_PACKED(44, zzay.PACKED_VECTOR, zzbm.ENUM),
    SFIXED32_LIST_PACKED(45, zzay.PACKED_VECTOR, zzbm.INT),
    SFIXED64_LIST_PACKED(46, zzay.PACKED_VECTOR, zzbm.LONG),
    SINT32_LIST_PACKED(47, zzay.PACKED_VECTOR, zzbm.INT),
    SINT64_LIST_PACKED(48, zzay.PACKED_VECTOR, zzbm.LONG),
    GROUP_LIST(49, zzay.VECTOR, zzbm.MESSAGE),
    MAP(50, zzay.MAP, zzbm.VOID);
    
    private static final zzaw[] zzhq = null;
    private static final Type[] zzhr = null;

    /* renamed from: id */
    private final int f420id;
    private final zzbm zzhm;
    private final zzay zzhn;
    private final Class<?> zzho;
    private final boolean zzhp;

    private zzaw(int i, zzay zzay, zzbm zzbm) {
        this.f420id = i;
        this.zzhn = zzay;
        this.zzhm = zzbm;
        switch (zzaz.zzhz[zzay.ordinal()]) {
            case 1:
                this.zzho = zzbm.zzbw();
                break;
            case 2:
                this.zzho = zzbm.zzbw();
                break;
            default:
                this.zzho = null;
                break;
        }
        boolean z = false;
        if (zzay == zzay.SCALAR) {
            switch (zzaz.zzia[zzbm.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    break;
                default:
                    z = true;
                    break;
            }
        }
        this.zzhp = z;
    }

    /* renamed from: id */
    public final int mo34787id() {
        return this.f420id;
    }

    static {
        int i;
        zzhr = new Type[0];
        zzaw[] values = values();
        zzhq = new zzaw[values.length];
        for (zzaw zzaw : values) {
            zzhq[zzaw.f420id] = zzaw;
        }
    }
}
