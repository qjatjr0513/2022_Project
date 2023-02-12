package com.google.firebase.auth.internal;

import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.internal.p010firebaseauthapi.zzae;
import com.google.android.gms.internal.p010firebaseauthapi.zzll;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-auth@@21.0.1 */
final class zzaz {
    private static final Logger zza = new Logger("JSONParser", new String[0]);

    static List<Object> zza(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                obj = zza((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = zzd((JSONObject) obj);
            }
            arrayList.add(obj);
        }
        return arrayList;
    }

    public static Map<String, Object> zzb(String str) {
        Preconditions.checkNotEmpty(str);
        List<String> zzd = zzae.zzb('.').zzd(str);
        if (zzd.size() < 2) {
            Logger logger = zza;
            String valueOf = String.valueOf(str);
            logger.mo31546e(valueOf.length() != 0 ? "Invalid idToken ".concat(valueOf) : new String("Invalid idToken "), new Object[0]);
            return new HashMap();
        }
        try {
            Map<String, Object> zzc = zzc(new String(Base64Utils.decodeUrlSafeNoPadding(zzd.get(1)), "UTF-8"));
            return zzc == null ? new HashMap() : zzc;
        } catch (UnsupportedEncodingException e) {
            zza.mo31545e("Unable to decode token", e, new Object[0]);
            return new HashMap();
        }
    }

    public static Map<String, Object> zzc(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject != JSONObject.NULL) {
                return zzd(jSONObject);
            }
            return null;
        } catch (Exception e) {
            Log.d("JSONParser", "Failed to parse JSONObject into Map.");
            throw new zzll(e);
        }
    }

    static Map<String, Object> zzd(JSONObject jSONObject) throws JSONException {
        ArrayMap arrayMap = new ArrayMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof JSONArray) {
                obj = zza((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = zzd((JSONObject) obj);
            }
            arrayMap.put(next, obj);
        }
        return arrayMap;
    }
}
