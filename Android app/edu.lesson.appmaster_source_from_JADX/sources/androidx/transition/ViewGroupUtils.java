package androidx.transition;

import android.os.Build;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ViewGroupUtils {
    private static Method sGetChildDrawingOrderMethod;
    private static boolean sGetChildDrawingOrderMethodFetched;
    private static boolean sTryHiddenSuppressLayout = true;

    static ViewGroupOverlayImpl getOverlay(ViewGroup group) {
        if (Build.VERSION.SDK_INT >= 18) {
            return new ViewGroupOverlayApi18(group);
        }
        return ViewGroupOverlayApi14.createFrom(group);
    }

    static void suppressLayout(ViewGroup group, boolean suppress) {
        if (Build.VERSION.SDK_INT >= 29) {
            group.suppressLayout(suppress);
        } else if (Build.VERSION.SDK_INT >= 18) {
            hiddenSuppressLayout(group, suppress);
        } else {
            ViewGroupUtilsApi14.suppressLayout(group, suppress);
        }
    }

    private static void hiddenSuppressLayout(ViewGroup group, boolean suppress) {
        if (sTryHiddenSuppressLayout) {
            try {
                group.suppressLayout(suppress);
            } catch (NoSuchMethodError e) {
                sTryHiddenSuppressLayout = false;
            }
        }
    }

    static int getChildDrawingOrder(ViewGroup viewGroup, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            return viewGroup.getChildDrawingOrder(i);
        }
        if (!sGetChildDrawingOrderMethodFetched) {
            Class<ViewGroup> cls = ViewGroup.class;
            try {
                Method declaredMethod = cls.getDeclaredMethod("getChildDrawingOrder", new Class[]{Integer.TYPE, Integer.TYPE});
                sGetChildDrawingOrderMethod = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
            }
            sGetChildDrawingOrderMethodFetched = true;
        }
        Method method = sGetChildDrawingOrderMethod;
        if (method != null) {
            try {
                return ((Integer) method.invoke(viewGroup, new Object[]{Integer.valueOf(viewGroup.getChildCount()), Integer.valueOf(i)})).intValue();
            } catch (IllegalAccessException | InvocationTargetException e2) {
            }
        }
        return i;
    }

    private ViewGroupUtils() {
    }
}
