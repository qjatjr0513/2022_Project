package com.google.android.gms.common.util;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public final class JsonUtils {
    private static final Pattern zza = Pattern.compile("\\\\.");
    private static final Pattern zzb = Pattern.compile("[\\\\\"/\b\f\n\r\t]");

    private JsonUtils() {
    }

    public static boolean areJsonValuesEquivalent(Object a, Object b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if ((a instanceof JSONObject) && (b instanceof JSONObject)) {
            JSONObject jSONObject = (JSONObject) a;
            JSONObject jSONObject2 = (JSONObject) b;
            if (jSONObject.length() != jSONObject2.length()) {
                return false;
            }
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!jSONObject2.has(next)) {
                    return false;
                }
                try {
                    Preconditions.checkNotNull(next);
                    if (!areJsonValuesEquivalent(jSONObject.get(next), jSONObject2.get(next))) {
                        return false;
                    }
                } catch (JSONException e) {
                    return false;
                }
            }
            return true;
        } else if (!(a instanceof JSONArray) || !(b instanceof JSONArray)) {
            return a.equals(b);
        } else {
            JSONArray jSONArray = (JSONArray) a;
            JSONArray jSONArray2 = (JSONArray) b;
            if (jSONArray.length() != jSONArray2.length()) {
                return false;
            }
            int i = 0;
            while (i < jSONArray.length()) {
                try {
                    if (!areJsonValuesEquivalent(jSONArray.get(i), jSONArray2.get(i))) {
                        return false;
                    }
                    i++;
                } catch (JSONException e2) {
                    return false;
                }
            }
            return true;
        }
    }

    public static String escapeString(String text) {
        if (TextUtils.isEmpty(text)) {
            return text;
        }
        Matcher matcher = zzb.matcher(text);
        StringBuffer stringBuffer = null;
        while (matcher.find()) {
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer();
            }
            switch (matcher.group().charAt(0)) {
                case 8:
                    matcher.appendReplacement(stringBuffer, "\\\\b");
                    break;
                case 9:
                    matcher.appendReplacement(stringBuffer, "\\\\t");
                    break;
                case 10:
                    matcher.appendReplacement(stringBuffer, "\\\\n");
                    break;
                case 12:
                    matcher.appendReplacement(stringBuffer, "\\\\f");
                    break;
                case 13:
                    matcher.appendReplacement(stringBuffer, "\\\\r");
                    break;
                case '\"':
                    matcher.appendReplacement(stringBuffer, "\\\\\\\"");
                    break;
                case '/':
                    matcher.appendReplacement(stringBuffer, "\\\\/");
                    break;
                case '\\':
                    matcher.appendReplacement(stringBuffer, "\\\\\\\\");
                    break;
            }
        }
        if (stringBuffer == null) {
            return text;
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public static String unescapeString(String text) {
        if (TextUtils.isEmpty(text)) {
            return text;
        }
        String zza2 = zzc.zza(text);
        Matcher matcher = zza.matcher(zza2);
        StringBuffer stringBuffer = null;
        while (matcher.find()) {
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer();
            }
            switch (matcher.group().charAt(1)) {
                case '\"':
                    matcher.appendReplacement(stringBuffer, "\"");
                    break;
                case '/':
                    matcher.appendReplacement(stringBuffer, "/");
                    break;
                case '\\':
                    matcher.appendReplacement(stringBuffer, "\\\\");
                    break;
                case 'b':
                    matcher.appendReplacement(stringBuffer, "\b");
                    break;
                case 'f':
                    matcher.appendReplacement(stringBuffer, "\f");
                    break;
                case 'n':
                    matcher.appendReplacement(stringBuffer, "\n");
                    break;
                case 'r':
                    matcher.appendReplacement(stringBuffer, "\r");
                    break;
                case 't':
                    matcher.appendReplacement(stringBuffer, "\t");
                    break;
                default:
                    throw new IllegalStateException("Found an escaped character that should never be.");
            }
        }
        if (stringBuffer == null) {
            return zza2;
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
