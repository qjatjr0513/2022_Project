package com.google.android.libraries.places.internal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
public final class zzbq {
    private final Gson zza = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    public final <T> T zza(String str, Class<T> cls) throws zzao {
        try {
            return this.zza.fromJson(str, cls);
        } catch (JsonSyntaxException e) {
            String name = cls.getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 55);
            sb.append("Could not convert JSON string to ");
            sb.append(name);
            sb.append(" due to syntax errors.");
            throw new zzao(sb.toString());
        }
    }
}
