package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.0.0 */
public enum zzmr {
    DOUBLE(zzms.DOUBLE, 1),
    FLOAT(zzms.FLOAT, 5),
    INT64(zzms.LONG, 0),
    UINT64(zzms.LONG, 0),
    INT32(zzms.INT, 0),
    FIXED64(zzms.LONG, 1),
    FIXED32(zzms.INT, 5),
    BOOL(zzms.BOOLEAN, 0),
    STRING(zzms.STRING, 2),
    GROUP(zzms.MESSAGE, 3),
    MESSAGE(zzms.MESSAGE, 2),
    BYTES(zzms.BYTE_STRING, 2),
    UINT32(zzms.INT, 0),
    ENUM(zzms.ENUM, 0),
    SFIXED32(zzms.INT, 5),
    SFIXED64(zzms.LONG, 1),
    SINT32(zzms.INT, 0),
    SINT64(zzms.LONG, 0);
    
    private final zzms zzt;

    private zzmr(zzms zzms, int i) {
        this.zzt = zzms;
    }

    public final zzms zza() {
        return this.zzt;
    }
}
