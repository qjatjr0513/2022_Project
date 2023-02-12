package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public enum zzaeq {
    DOUBLE(zzaer.DOUBLE, 1),
    FLOAT(zzaer.FLOAT, 5),
    INT64(zzaer.LONG, 0),
    UINT64(zzaer.LONG, 0),
    INT32(zzaer.INT, 0),
    FIXED64(zzaer.LONG, 1),
    FIXED32(zzaer.INT, 5),
    BOOL(zzaer.BOOLEAN, 0),
    STRING(zzaer.STRING, 2),
    GROUP(zzaer.MESSAGE, 3),
    MESSAGE(zzaer.MESSAGE, 2),
    BYTES(zzaer.BYTE_STRING, 2),
    UINT32(zzaer.INT, 0),
    ENUM(zzaer.ENUM, 0),
    SFIXED32(zzaer.INT, 5),
    SFIXED64(zzaer.LONG, 1),
    SINT32(zzaer.INT, 0),
    SINT64(zzaer.LONG, 0);
    
    private final zzaer zzt;

    private zzaeq(zzaer zzaer, int i) {
        this.zzt = zzaer;
    }

    public final zzaer zza() {
        return this.zzt;
    }
}
