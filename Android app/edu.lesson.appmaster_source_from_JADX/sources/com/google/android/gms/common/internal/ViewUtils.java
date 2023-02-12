package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public class ViewUtils {
    private ViewUtils() {
    }

    public static String getXmlAttributeString(String namespace, String name, Context context, AttributeSet attrs, boolean allowResources, boolean required, String logTag) {
        String str;
        if (attrs == null) {
            str = null;
        } else {
            str = attrs.getAttributeValue(namespace, name);
        }
        if (str != null && str.startsWith("@string/") && allowResources) {
            String substring = str.substring(8);
            String packageName = context.getPackageName();
            TypedValue typedValue = new TypedValue();
            try {
                Resources resources = context.getResources();
                StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 8 + String.valueOf(substring).length());
                sb.append(packageName);
                sb.append(":string/");
                sb.append(substring);
                resources.getValue(sb.toString(), typedValue, true);
            } catch (Resources.NotFoundException e) {
                StringBuilder sb2 = new StringBuilder(String.valueOf(name).length() + 30 + str.length());
                sb2.append("Could not find resource for ");
                sb2.append(name);
                sb2.append(": ");
                sb2.append(str);
                Log.w(logTag, sb2.toString());
            }
            if (typedValue.string != null) {
                str = typedValue.string.toString();
            } else {
                String obj = typedValue.toString();
                StringBuilder sb3 = new StringBuilder(String.valueOf(name).length() + 28 + obj.length());
                sb3.append("Resource ");
                sb3.append(name);
                sb3.append(" was not a string: ");
                sb3.append(obj);
                Log.w(logTag, sb3.toString());
            }
        }
        if (required && str == null) {
            StringBuilder sb4 = new StringBuilder(String.valueOf(name).length() + 33);
            sb4.append("Required XML attribute \"");
            sb4.append(name);
            sb4.append("\" missing");
            Log.w(logTag, sb4.toString());
        }
        return str;
    }
}
