package com.google.android.gms.internal.places;

public enum zzef {
    DOUBLE(zzem.DOUBLE, 1),
    FLOAT(zzem.FLOAT, 5),
    INT64(zzem.LONG, 0),
    UINT64(zzem.LONG, 0),
    INT32(zzem.INT, 0),
    FIXED64(zzem.LONG, 1),
    FIXED32(zzem.INT, 5),
    BOOL(zzem.BOOLEAN, 0),
    STRING(zzem.STRING, 2),
    GROUP(zzem.MESSAGE, 3),
    MESSAGE(zzem.MESSAGE, 2),
    BYTES(zzem.BYTE_STRING, 2),
    UINT32(zzem.INT, 0),
    ENUM(zzem.ENUM, 0),
    SFIXED32(zzem.INT, 5),
    SFIXED64(zzem.LONG, 1),
    SINT32(zzem.INT, 0),
    SINT64(zzem.LONG, 0);
    
    private final zzem zzob;
    private final int zzoc;

    private zzef(zzem zzem, int i) {
        this.zzob = zzem;
        this.zzoc = i;
    }

    public final zzem zzdr() {
        return this.zzob;
    }

    public final int zzds() {
        return this.zzoc;
    }
}
