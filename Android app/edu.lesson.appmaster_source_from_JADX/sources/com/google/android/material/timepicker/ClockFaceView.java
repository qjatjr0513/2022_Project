package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.C2436R;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.timepicker.ClockHandView;
import java.util.Arrays;

class ClockFaceView extends RadialViewGroup implements ClockHandView.OnRotateListener {
    private static final float EPSILON = 0.001f;
    private static final int INITIAL_CAPACITY = 12;
    private static final String VALUE_PLACEHOLDER = "";
    /* access modifiers changed from: private */
    public final int clockHandPadding;
    /* access modifiers changed from: private */
    public final ClockHandView clockHandView;
    private final int clockSize;
    private float currentHandRotation;
    private final int[] gradientColors;
    private final float[] gradientPositions;
    private final int minimumHeight;
    private final int minimumWidth;
    private final RectF scratch;
    private final ColorStateList textColor;
    /* access modifiers changed from: private */
    public final SparseArray<TextView> textViewPool;
    private final Rect textViewRect;
    private final AccessibilityDelegateCompat valueAccessibilityDelegate;
    private String[] values;

    public ClockFaceView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ClockFaceView(Context context, AttributeSet attrs) {
        this(context, attrs, C2436R.attr.materialClockStyle);
    }

    public ClockFaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        int i;
        this.textViewRect = new Rect();
        this.scratch = new RectF();
        this.textViewPool = new SparseArray<>();
        this.gradientPositions = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray a = context.obtainStyledAttributes(attrs, C2436R.styleable.ClockFaceView, defStyleAttr, C2436R.style.Widget_MaterialComponents_TimePicker_Clock);
        Resources res = getResources();
        ColorStateList colorStateList = MaterialResources.getColorStateList(context, a, C2436R.styleable.ClockFaceView_clockNumberTextColor);
        this.textColor = colorStateList;
        LayoutInflater.from(context).inflate(C2436R.layout.material_clockface_view, this, true);
        ClockHandView clockHandView2 = (ClockHandView) findViewById(C2436R.C2439id.material_clock_hand);
        this.clockHandView = clockHandView2;
        this.clockHandPadding = res.getDimensionPixelSize(C2436R.dimen.material_clock_hand_padding);
        int clockHandTextColor = colorStateList.getColorForState(new int[]{16842913}, colorStateList.getDefaultColor());
        this.gradientColors = new int[]{clockHandTextColor, clockHandTextColor, colorStateList.getDefaultColor()};
        clockHandView2.addOnRotateListener(this);
        int defaultBackgroundColor = AppCompatResources.getColorStateList(context, C2436R.C2437color.material_timepicker_clockface).getDefaultColor();
        ColorStateList backgroundColor = MaterialResources.getColorStateList(context, a, C2436R.styleable.ClockFaceView_clockFaceBackgroundColor);
        if (backgroundColor == null) {
            i = defaultBackgroundColor;
        } else {
            i = backgroundColor.getDefaultColor();
        }
        setBackgroundColor(i);
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                if (!ClockFaceView.this.isShown()) {
                    return true;
                }
                ClockFaceView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                ClockFaceView.this.setRadius(((ClockFaceView.this.getHeight() / 2) - ClockFaceView.this.clockHandView.getSelectorRadius()) - ClockFaceView.this.clockHandPadding);
                return true;
            }
        });
        setFocusable(true);
        a.recycle();
        this.valueAccessibilityDelegate = new AccessibilityDelegateCompat() {
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                int index = ((Integer) host.getTag(C2436R.C2439id.material_value_index)).intValue();
                if (index > 0) {
                    info.setTraversalAfter((View) ClockFaceView.this.textViewPool.get(index - 1));
                }
                info.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, index, 1, false, host.isSelected()));
            }
        };
        String[] initialValues = new String[12];
        Arrays.fill(initialValues, "");
        setValues(initialValues, 0);
        this.minimumHeight = res.getDimensionPixelSize(C2436R.dimen.material_time_picker_minimum_screen_height);
        this.minimumWidth = res.getDimensionPixelSize(C2436R.dimen.material_time_picker_minimum_screen_width);
        this.clockSize = res.getDimensionPixelSize(C2436R.dimen.material_clock_size);
    }

    public void setValues(String[] values2, int contentDescription) {
        this.values = values2;
        updateTextViews(contentDescription);
    }

    /* JADX WARNING: type inference failed for: r5v7, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateTextViews(int r9) {
        /*
            r8 = this;
            android.content.Context r0 = r8.getContext()
            android.view.LayoutInflater r0 = android.view.LayoutInflater.from(r0)
            android.util.SparseArray<android.widget.TextView> r1 = r8.textViewPool
            int r1 = r1.size()
            r2 = 0
        L_0x000f:
            java.lang.String[] r3 = r8.values
            int r3 = r3.length
            int r3 = java.lang.Math.max(r3, r1)
            if (r2 >= r3) goto L_0x0078
            android.util.SparseArray<android.widget.TextView> r3 = r8.textViewPool
            java.lang.Object r3 = r3.get(r2)
            android.widget.TextView r3 = (android.widget.TextView) r3
            java.lang.String[] r4 = r8.values
            int r4 = r4.length
            if (r2 < r4) goto L_0x002e
            r8.removeView(r3)
            android.util.SparseArray<android.widget.TextView> r4 = r8.textViewPool
            r4.remove(r2)
            goto L_0x0075
        L_0x002e:
            r4 = 0
            if (r3 != 0) goto L_0x0042
            int r5 = com.google.android.material.C2436R.layout.material_clockface_textview
            android.view.View r5 = r0.inflate(r5, r8, r4)
            r3 = r5
            android.widget.TextView r3 = (android.widget.TextView) r3
            android.util.SparseArray<android.widget.TextView> r5 = r8.textViewPool
            r5.put(r2, r3)
            r8.addView(r3)
        L_0x0042:
            r3.setVisibility(r4)
            java.lang.String[] r5 = r8.values
            r5 = r5[r2]
            r3.setText(r5)
            int r5 = com.google.android.material.C2436R.C2439id.material_value_index
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)
            r3.setTag(r5, r6)
            androidx.core.view.AccessibilityDelegateCompat r5 = r8.valueAccessibilityDelegate
            androidx.core.view.ViewCompat.setAccessibilityDelegate(r3, r5)
            android.content.res.ColorStateList r5 = r8.textColor
            r3.setTextColor(r5)
            if (r9 == 0) goto L_0x0075
            android.content.res.Resources r5 = r8.getResources()
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]
            java.lang.String[] r7 = r8.values
            r7 = r7[r2]
            r6[r4] = r7
            java.lang.String r4 = r5.getString(r9, r6)
            r3.setContentDescription(r4)
        L_0x0075:
            int r2 = r2 + 1
            goto L_0x000f
        L_0x0078:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.timepicker.ClockFaceView.updateTextViews(int):void");
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        AccessibilityNodeInfoCompat.wrap(info).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, this.values.length, false, 1));
    }

    public void setRadius(int radius) {
        if (radius != getRadius()) {
            super.setRadius(radius);
            this.clockHandView.setCircleRadius(getRadius());
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        findIntersectingTextView();
    }

    public void setHandRotation(float rotation) {
        this.clockHandView.setHandRotation(rotation);
        findIntersectingTextView();
    }

    private void findIntersectingTextView() {
        RectF selectorBox = this.clockHandView.getCurrentSelectorBox();
        for (int i = 0; i < this.textViewPool.size(); i++) {
            TextView tv = this.textViewPool.get(i);
            if (tv != null) {
                tv.getDrawingRect(this.textViewRect);
                this.textViewRect.offset(tv.getPaddingLeft(), tv.getPaddingTop());
                offsetDescendantRectToMyCoords(tv, this.textViewRect);
                this.scratch.set(this.textViewRect);
                tv.getPaint().setShader(getGradientForTextView(selectorBox, this.scratch));
                tv.invalidate();
            }
        }
    }

    private RadialGradient getGradientForTextView(RectF selectorBox, RectF tvBox) {
        if (!RectF.intersects(selectorBox, tvBox)) {
            return null;
        }
        return new RadialGradient(selectorBox.centerX() - this.scratch.left, selectorBox.centerY() - this.scratch.top, 0.5f * selectorBox.width(), this.gradientColors, this.gradientPositions, Shader.TileMode.CLAMP);
    }

    public void onRotate(float rotation, boolean animating) {
        if (Math.abs(this.currentHandRotation - rotation) > EPSILON) {
            this.currentHandRotation = rotation;
            findIntersectingTextView();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int size = (int) (((float) this.clockSize) / max3(((float) this.minimumHeight) / ((float) displayMetrics.heightPixels), ((float) this.minimumWidth) / ((float) displayMetrics.widthPixels), 1.0f));
        int spec = View.MeasureSpec.makeMeasureSpec(size, 1073741824);
        setMeasuredDimension(size, size);
        super.onMeasure(spec, spec);
    }

    private static float max3(float a, float b, float c) {
        return Math.max(Math.max(a, b), c);
    }
}
