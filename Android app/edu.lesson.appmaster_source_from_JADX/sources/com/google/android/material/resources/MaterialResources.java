package com.google.android.material.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TintTypedArray;

public class MaterialResources {
    private static final float FONT_SCALE_1_3 = 1.3f;
    private static final float FONT_SCALE_2_0 = 2.0f;

    private MaterialResources() {
    }

    public static ColorStateList getColorStateList(Context context, TypedArray attributes, int index) {
        int color;
        int resourceId;
        ColorStateList value;
        if (attributes.hasValue(index) && (resourceId = attributes.getResourceId(index, 0)) != 0 && (value = AppCompatResources.getColorStateList(context, resourceId)) != null) {
            return value;
        }
        if (Build.VERSION.SDK_INT > 15 || (color = attributes.getColor(index, -1)) == -1) {
            return attributes.getColorStateList(index);
        }
        return ColorStateList.valueOf(color);
    }

    public static ColorStateList getColorStateList(Context context, TintTypedArray attributes, int index) {
        int color;
        int resourceId;
        ColorStateList value;
        if (attributes.hasValue(index) && (resourceId = attributes.getResourceId(index, 0)) != 0 && (value = AppCompatResources.getColorStateList(context, resourceId)) != null) {
            return value;
        }
        if (Build.VERSION.SDK_INT > 15 || (color = attributes.getColor(index, -1)) == -1) {
            return attributes.getColorStateList(index);
        }
        return ColorStateList.valueOf(color);
    }

    public static Drawable getDrawable(Context context, TypedArray attributes, int index) {
        int resourceId;
        Drawable value;
        if (!attributes.hasValue(index) || (resourceId = attributes.getResourceId(index, 0)) == 0 || (value = AppCompatResources.getDrawable(context, resourceId)) == null) {
            return attributes.getDrawable(index);
        }
        return value;
    }

    public static TextAppearance getTextAppearance(Context context, TypedArray attributes, int index) {
        int resourceId;
        if (!attributes.hasValue(index) || (resourceId = attributes.getResourceId(index, 0)) == 0) {
            return null;
        }
        return new TextAppearance(context, resourceId);
    }

    public static int getDimensionPixelSize(Context context, TypedArray attributes, int index, int defaultValue) {
        TypedValue value = new TypedValue();
        if (!attributes.getValue(index, value) || value.type != 2) {
            return attributes.getDimensionPixelSize(index, defaultValue);
        }
        TypedArray styledAttrs = context.getTheme().obtainStyledAttributes(new int[]{value.data});
        int dimension = styledAttrs.getDimensionPixelSize(0, defaultValue);
        styledAttrs.recycle();
        return dimension;
    }

    public static boolean isFontScaleAtLeast1_3(Context context) {
        return context.getResources().getConfiguration().fontScale >= FONT_SCALE_1_3;
    }

    public static boolean isFontScaleAtLeast2_0(Context context) {
        return context.getResources().getConfiguration().fontScale >= 2.0f;
    }

    static int getIndexWithValue(TypedArray attributes, int a, int b) {
        if (attributes.hasValue(a)) {
            return a;
        }
        return b;
    }
}
