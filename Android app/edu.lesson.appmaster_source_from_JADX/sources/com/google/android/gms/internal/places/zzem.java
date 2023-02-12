package com.google.android.gms.internal.places;

public enum zzem {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(0.0d)),
    BOOLEAN(false),
    STRING(""),
    BYTE_STRING(zzw.zzeg),
    ENUM((String) null),
    MESSAGE((String) null);
    
    private final Object zzjr;

    private zzem(Object obj) {
        this.zzjr = obj;
    }
}
