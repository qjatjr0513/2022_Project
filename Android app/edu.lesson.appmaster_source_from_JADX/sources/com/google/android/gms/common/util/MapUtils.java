package com.google.android.gms.common.util;

import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public class MapUtils {
    public static void writeStringMapToJson(StringBuilder sb, HashMap<String, String> stringMap) {
        sb.append("{");
        boolean z = true;
        for (String next : stringMap.keySet()) {
            if (!z) {
                sb.append(",");
            }
            String str = stringMap.get(next);
            sb.append("\"");
            sb.append(next);
            sb.append("\":");
            if (str == null) {
                sb.append("null");
                z = false;
            } else {
                sb.append("\"");
                sb.append(str);
                sb.append("\"");
                z = false;
            }
        }
        sb.append("}");
    }
}
