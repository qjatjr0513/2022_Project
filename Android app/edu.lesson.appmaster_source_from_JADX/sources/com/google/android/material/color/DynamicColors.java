package com.google.android.material.color;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import com.google.android.material.C2436R;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DynamicColors {
    private static final Precondition ALWAYS_ALLOW = new Precondition() {
        public boolean shouldApplyDynamicColors(Activity activity, int theme) {
            return true;
        }
    };
    private static final DeviceSupportCondition DEFAULT_DEVICE_SUPPORT_CONDITION;
    private static final Map<String, DeviceSupportCondition> DYNAMIC_COLOR_SUPPORTED_BRANDS;
    private static final Map<String, DeviceSupportCondition> DYNAMIC_COLOR_SUPPORTED_MANUFACTURERS;
    private static final int[] DYNAMIC_COLOR_THEME_OVERLAY_ATTRIBUTE = {C2436R.attr.dynamicColorThemeOverlay};
    private static final DeviceSupportCondition SAMSUNG_DEVICE_SUPPORT_CONDITION;
    private static final int USE_DEFAULT_THEME_OVERLAY = 0;

    private interface DeviceSupportCondition {
        boolean isSupported();
    }

    public interface Precondition {
        boolean shouldApplyDynamicColors(Activity activity, int i);
    }

    static {
        C20381 r0 = new DeviceSupportCondition() {
            public boolean isSupported() {
                return true;
            }
        };
        DEFAULT_DEVICE_SUPPORT_CONDITION = r0;
        C20392 r1 = new DeviceSupportCondition() {
            private Long version;

            public boolean isSupported() {
                if (this.version == null) {
                    try {
                        Method method = Build.class.getDeclaredMethod("getLong", new Class[]{String.class});
                        method.setAccessible(true);
                        this.version = Long.valueOf(((Long) method.invoke((Object) null, new Object[]{"ro.build.version.oneui"})).longValue());
                    } catch (Exception e) {
                        this.version = -1L;
                    }
                }
                if (this.version.longValue() >= 40100) {
                    return true;
                }
                return false;
            }
        };
        SAMSUNG_DEVICE_SUPPORT_CONDITION = r1;
        Map<String, DeviceSupportCondition> deviceMap = new HashMap<>();
        deviceMap.put("oppo", r0);
        deviceMap.put("realme", r0);
        deviceMap.put("oneplus", r0);
        deviceMap.put("vivo", r0);
        deviceMap.put("xiaomi", r0);
        deviceMap.put("motorola", r0);
        deviceMap.put("itel", r0);
        deviceMap.put("tecno mobile limited", r0);
        deviceMap.put("infinix mobility limited", r0);
        deviceMap.put("hmd global", r0);
        deviceMap.put("sharp", r0);
        deviceMap.put("sony", r0);
        deviceMap.put("tcl", r0);
        deviceMap.put("lenovo", r0);
        deviceMap.put("lge", r0);
        deviceMap.put("google", r0);
        deviceMap.put("robolectric", r0);
        deviceMap.put("samsung", r1);
        DYNAMIC_COLOR_SUPPORTED_MANUFACTURERS = Collections.unmodifiableMap(deviceMap);
        Map<String, DeviceSupportCondition> deviceMap2 = new HashMap<>();
        deviceMap2.put("asus", r0);
        deviceMap2.put("jio", r0);
        DYNAMIC_COLOR_SUPPORTED_BRANDS = Collections.unmodifiableMap(deviceMap2);
    }

    private DynamicColors() {
    }

    public static void applyToActivitiesIfAvailable(Application application) {
        applyToActivitiesIfAvailable(application, 0);
    }

    public static void applyToActivitiesIfAvailable(Application application, int theme) {
        applyToActivitiesIfAvailable(application, theme, ALWAYS_ALLOW);
    }

    public static void applyToActivitiesIfAvailable(Application application, Precondition precondition) {
        applyToActivitiesIfAvailable(application, 0, precondition);
    }

    public static void applyToActivitiesIfAvailable(Application application, int theme, Precondition precondition) {
        application.registerActivityLifecycleCallbacks(new DynamicColorsActivityLifecycleCallbacks(theme, precondition));
    }

    public static void applyIfAvailable(Activity activity) {
        applyIfAvailable(activity, 0);
    }

    public static void applyIfAvailable(Activity activity, int theme) {
        applyIfAvailable(activity, theme, ALWAYS_ALLOW);
    }

    public static void applyIfAvailable(Activity activity, Precondition precondition) {
        applyIfAvailable(activity, 0, precondition);
    }

    /* access modifiers changed from: private */
    public static void applyIfAvailable(Activity activity, int theme, Precondition precondition) {
        if (isDynamicColorAvailable()) {
            if (theme == 0) {
                theme = getDefaultThemeOverlay(activity);
            }
            if (theme != 0 && precondition.shouldApplyDynamicColors(activity, theme)) {
                activity.setTheme(theme);
            }
        }
    }

    public static Context wrapContextIfAvailable(Context originalContext) {
        return wrapContextIfAvailable(originalContext, 0);
    }

    public static Context wrapContextIfAvailable(Context originalContext, int theme) {
        if (!isDynamicColorAvailable()) {
            return originalContext;
        }
        if (theme == 0) {
            theme = getDefaultThemeOverlay(originalContext);
        }
        return theme == 0 ? originalContext : new ContextThemeWrapper(originalContext, theme);
    }

    public static boolean isDynamicColorAvailable() {
        if (Build.VERSION.SDK_INT < 31) {
            return false;
        }
        DeviceSupportCondition deviceSupportCondition = DYNAMIC_COLOR_SUPPORTED_MANUFACTURERS.get(Build.MANUFACTURER.toLowerCase());
        if (deviceSupportCondition == null) {
            deviceSupportCondition = DYNAMIC_COLOR_SUPPORTED_BRANDS.get(Build.BRAND.toLowerCase());
        }
        if (deviceSupportCondition == null || !deviceSupportCondition.isSupported()) {
            return false;
        }
        return true;
    }

    private static int getDefaultThemeOverlay(Context context) {
        TypedArray dynamicColorAttributes = context.obtainStyledAttributes(DYNAMIC_COLOR_THEME_OVERLAY_ATTRIBUTE);
        int theme = dynamicColorAttributes.getResourceId(0, 0);
        dynamicColorAttributes.recycle();
        return theme;
    }

    private static class DynamicColorsActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        private final int dynamicColorThemeOverlay;
        private final Precondition precondition;

        DynamicColorsActivityLifecycleCallbacks(int theme, Precondition condition) {
            this.dynamicColorThemeOverlay = theme;
            this.precondition = condition;
        }

        public void onActivityPreCreated(Activity activity, Bundle savedInstanceState) {
            DynamicColors.applyIfAvailable(activity, this.dynamicColorThemeOverlay, this.precondition);
        }

        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        public void onActivityDestroyed(Activity activity) {
        }
    }
}
