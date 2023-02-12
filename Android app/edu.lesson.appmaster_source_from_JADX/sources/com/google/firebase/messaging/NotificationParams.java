package com.google.firebase.messaging;

import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.firebase.messaging.Constants;
import java.util.Arrays;
import java.util.MissingFormatArgumentException;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: com.google.firebase:firebase-messaging@@23.0.0 */
public class NotificationParams {
    private final Bundle data;

    public NotificationParams(Bundle data2) {
        if (data2 != null) {
            this.data = new Bundle(data2);
            return;
        }
        throw new NullPointerException(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
    }

    private static int getLightColor(String str) {
        int parseColor = Color.parseColor(str);
        if (parseColor != -16777216) {
            return parseColor;
        }
        throw new IllegalArgumentException("Transparent color is invalid");
    }

    private static boolean isAnalyticsKey(String str) {
        return str.startsWith(Constants.AnalyticsKeys.PREFIX) || str.equals("from");
    }

    private static boolean isReservedKey(String str) {
        return str.startsWith(Constants.MessagePayloadKeys.RESERVED_CLIENT_LIB_PREFIX) || str.startsWith(Constants.MessageNotificationKeys.NOTIFICATION_PREFIX) || str.startsWith(Constants.MessageNotificationKeys.NOTIFICATION_PREFIX_OLD);
    }

    private static String keyWithOldPrefix(String str) {
        if (!str.startsWith(Constants.MessageNotificationKeys.NOTIFICATION_PREFIX)) {
            return str;
        }
        return str.replace(Constants.MessageNotificationKeys.NOTIFICATION_PREFIX, Constants.MessageNotificationKeys.NOTIFICATION_PREFIX_OLD);
    }

    private String normalizePrefix(String str) {
        if (!this.data.containsKey(str) && str.startsWith(Constants.MessageNotificationKeys.NOTIFICATION_PREFIX)) {
            String keyWithOldPrefix = keyWithOldPrefix(str);
            if (this.data.containsKey(keyWithOldPrefix)) {
                return keyWithOldPrefix;
            }
        }
        return str;
    }

    private static String userFriendlyKey(String str) {
        return str.startsWith(Constants.MessageNotificationKeys.NOTIFICATION_PREFIX) ? str.substring(6) : str;
    }

    public boolean getBoolean(String key) {
        String key2 = getString(key);
        return "1".equals(key2) || Boolean.parseBoolean(key2);
    }

    public Integer getInteger(String key) {
        String string = getString(key);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(string));
        } catch (NumberFormatException e) {
            String userFriendlyKey = userFriendlyKey(key);
            StringBuilder sb = new StringBuilder(String.valueOf(userFriendlyKey).length() + 38 + String.valueOf(string).length());
            sb.append("Couldn't parse value of ");
            sb.append(userFriendlyKey);
            sb.append("(");
            sb.append(string);
            sb.append(") into an int");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
    }

    public JSONArray getJSONArray(String key) {
        String string = getString(key);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            return new JSONArray(string);
        } catch (JSONException e) {
            String userFriendlyKey = userFriendlyKey(key);
            StringBuilder sb = new StringBuilder(String.valueOf(userFriendlyKey).length() + 50 + String.valueOf(string).length());
            sb.append("Malformed JSON for key ");
            sb.append(userFriendlyKey);
            sb.append(": ");
            sb.append(string);
            sb.append(", falling back to default");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public int[] getLightSettings() {
        JSONArray jSONArray = getJSONArray(Constants.MessageNotificationKeys.LIGHT_SETTINGS);
        if (jSONArray == null) {
            return null;
        }
        int[] iArr = new int[3];
        try {
            if (jSONArray.length() == 3) {
                iArr[0] = getLightColor(jSONArray.optString(0));
                iArr[1] = jSONArray.optInt(1);
                iArr[2] = jSONArray.optInt(2);
                return iArr;
            }
            throw new JSONException("lightSettings don't have all three fields");
        } catch (JSONException e) {
            String obj = jSONArray.toString();
            StringBuilder sb = new StringBuilder(obj.length() + 58);
            sb.append("LightSettings is invalid: ");
            sb.append(obj);
            sb.append(". Skipping setting LightSettings");
            Log.w("NotificationParams", sb.toString());
            return null;
        } catch (IllegalArgumentException e2) {
            String obj2 = jSONArray.toString();
            String message = e2.getMessage();
            StringBuilder sb2 = new StringBuilder(obj2.length() + 60 + String.valueOf(message).length());
            sb2.append("LightSettings is invalid: ");
            sb2.append(obj2);
            sb2.append(". ");
            sb2.append(message);
            sb2.append(". Skipping setting LightSettings");
            Log.w("NotificationParams", sb2.toString());
            return null;
        }
    }

    public Uri getLink() {
        String string = getString(Constants.MessageNotificationKeys.LINK_ANDROID);
        if (TextUtils.isEmpty(string)) {
            string = getString(Constants.MessageNotificationKeys.LINK);
        }
        if (!TextUtils.isEmpty(string)) {
            return Uri.parse(string);
        }
        return null;
    }

    public Object[] getLocalizationArgsForKey(String key) {
        JSONArray jSONArray = getJSONArray(String.valueOf(key).concat(Constants.MessageNotificationKeys.TEXT_ARGS_SUFFIX));
        if (jSONArray == null) {
            return null;
        }
        int length = jSONArray.length();
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = jSONArray.optString(i);
        }
        return strArr;
    }

    public String getLocalizationResourceForKey(String key) {
        return getString(String.valueOf(key).concat(Constants.MessageNotificationKeys.TEXT_RESOURCE_SUFFIX));
    }

    public String getLocalizedString(Resources resources, String packageName, String key) {
        String localizationResourceForKey = getLocalizationResourceForKey(key);
        if (TextUtils.isEmpty(localizationResourceForKey)) {
            return null;
        }
        int identifier = resources.getIdentifier(localizationResourceForKey, TypedValues.Custom.S_STRING, packageName);
        if (identifier == 0) {
            String userFriendlyKey = userFriendlyKey(String.valueOf(key).concat(Constants.MessageNotificationKeys.TEXT_RESOURCE_SUFFIX));
            StringBuilder sb = new StringBuilder(String.valueOf(userFriendlyKey).length() + 49 + String.valueOf(key).length());
            sb.append(userFriendlyKey);
            sb.append(" resource not found: ");
            sb.append(key);
            sb.append(" Default value will be used.");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
        Object[] localizationArgsForKey = getLocalizationArgsForKey(key);
        if (localizationArgsForKey == null) {
            return resources.getString(identifier);
        }
        try {
            return resources.getString(identifier, localizationArgsForKey);
        } catch (MissingFormatArgumentException e) {
            String userFriendlyKey2 = userFriendlyKey(key);
            String arrays = Arrays.toString(localizationArgsForKey);
            StringBuilder sb2 = new StringBuilder(String.valueOf(userFriendlyKey2).length() + 58 + String.valueOf(arrays).length());
            sb2.append("Missing format argument for ");
            sb2.append(userFriendlyKey2);
            sb2.append(": ");
            sb2.append(arrays);
            sb2.append(" Default value will be used.");
            Log.w("NotificationParams", sb2.toString(), e);
            return null;
        }
    }

    public Long getLong(String key) {
        String string = getString(key);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            return Long.valueOf(Long.parseLong(string));
        } catch (NumberFormatException e) {
            String userFriendlyKey = userFriendlyKey(key);
            StringBuilder sb = new StringBuilder(String.valueOf(userFriendlyKey).length() + 38 + String.valueOf(string).length());
            sb.append("Couldn't parse value of ");
            sb.append(userFriendlyKey);
            sb.append("(");
            sb.append(string);
            sb.append(") into a long");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
    }

    public String getNotificationChannelId() {
        return getString(Constants.MessageNotificationKeys.CHANNEL);
    }

    /* access modifiers changed from: package-private */
    public Integer getNotificationCount() {
        Integer integer = getInteger(Constants.MessageNotificationKeys.NOTIFICATION_COUNT);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() >= 0) {
            return integer;
        }
        String obj = integer.toString();
        StringBuilder sb = new StringBuilder(obj.length() + 67);
        sb.append("notificationCount is invalid: ");
        sb.append(obj);
        sb.append(". Skipping setting notificationCount.");
        Log.w(Constants.TAG, sb.toString());
        return null;
    }

    /* access modifiers changed from: package-private */
    public Integer getNotificationPriority() {
        Integer integer = getInteger(Constants.MessageNotificationKeys.NOTIFICATION_PRIORITY);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() >= -2 && integer.intValue() <= 2) {
            return integer;
        }
        String obj = integer.toString();
        StringBuilder sb = new StringBuilder(obj.length() + 72);
        sb.append("notificationPriority is invalid ");
        sb.append(obj);
        sb.append(". Skipping setting notificationPriority.");
        Log.w(Constants.TAG, sb.toString());
        return null;
    }

    public String getPossiblyLocalizedString(Resources resources, String packageName, String key) {
        String string = getString(key);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        return getLocalizedString(resources, packageName, key);
    }

    public String getSoundResourceName() {
        String string = getString(Constants.MessageNotificationKeys.SOUND_2);
        return TextUtils.isEmpty(string) ? getString(Constants.MessageNotificationKeys.SOUND) : string;
    }

    public String getString(String key) {
        return this.data.getString(normalizePrefix(key));
    }

    public long[] getVibrateTimings() {
        JSONArray jSONArray = getJSONArray(Constants.MessageNotificationKeys.VIBRATE_TIMINGS);
        if (jSONArray == null) {
            return null;
        }
        try {
            if (jSONArray.length() > 1) {
                int length = jSONArray.length();
                long[] jArr = new long[length];
                for (int i = 0; i < length; i++) {
                    jArr[i] = jSONArray.optLong(i);
                }
                return jArr;
            }
            throw new JSONException("vibrateTimings have invalid length");
        } catch (NumberFormatException | JSONException e) {
            String obj = jSONArray.toString();
            StringBuilder sb = new StringBuilder(obj.length() + 74);
            sb.append("User defined vibrateTimings is invalid: ");
            sb.append(obj);
            sb.append(". Skipping setting vibrateTimings.");
            Log.w("NotificationParams", sb.toString());
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Integer getVisibility() {
        Integer integer = getInteger(Constants.MessageNotificationKeys.VISIBILITY);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() >= -1 && integer.intValue() <= 1) {
            return integer;
        }
        String obj = integer.toString();
        StringBuilder sb = new StringBuilder(obj.length() + 53);
        sb.append("visibility is invalid: ");
        sb.append(obj);
        sb.append(". Skipping setting visibility.");
        Log.w("NotificationParams", sb.toString());
        return null;
    }

    public boolean hasImage() {
        return !TextUtils.isEmpty(getString(Constants.MessageNotificationKeys.IMAGE_URL));
    }

    public boolean isNotification() {
        return getBoolean(Constants.MessageNotificationKeys.ENABLE_NOTIFICATION);
    }

    public Bundle paramsForAnalyticsIntent() {
        Bundle bundle = new Bundle(this.data);
        for (String str : this.data.keySet()) {
            if (!isAnalyticsKey(str)) {
                bundle.remove(str);
            }
        }
        return bundle;
    }

    public Bundle paramsWithReservedKeysRemoved() {
        Bundle bundle = new Bundle(this.data);
        for (String str : this.data.keySet()) {
            if (isReservedKey(str)) {
                bundle.remove(str);
            }
        }
        return bundle;
    }

    public static boolean isNotification(Bundle data2) {
        return "1".equals(data2.getString(Constants.MessageNotificationKeys.ENABLE_NOTIFICATION)) || "1".equals(data2.getString(keyWithOldPrefix(Constants.MessageNotificationKeys.ENABLE_NOTIFICATION)));
    }
}
