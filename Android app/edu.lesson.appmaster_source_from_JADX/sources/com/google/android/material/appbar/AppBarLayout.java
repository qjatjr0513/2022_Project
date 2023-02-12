package com.google.android.material.appbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.math.MathUtils;
import androidx.core.util.ObjectsCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.C2436R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class AppBarLayout extends LinearLayout implements CoordinatorLayout.AttachedBehavior {
    private static final int DEF_STYLE_RES = C2436R.style.Widget_Design_AppBarLayout;
    private static final int INVALID_SCROLL_RANGE = -1;
    static final int PENDING_ACTION_ANIMATE_ENABLED = 4;
    static final int PENDING_ACTION_COLLAPSED = 2;
    static final int PENDING_ACTION_EXPANDED = 1;
    static final int PENDING_ACTION_FORCE = 8;
    static final int PENDING_ACTION_NONE = 0;
    private int currentOffset;
    private int downPreScrollRange;
    private int downScrollRange;
    private ValueAnimator elevationOverlayAnimator;
    private boolean haveChildWithInterpolator;
    private WindowInsetsCompat lastInsets;
    private boolean liftOnScroll;
    /* access modifiers changed from: private */
    public final List<LiftOnScrollListener> liftOnScrollListeners;
    private WeakReference<View> liftOnScrollTargetView;
    private int liftOnScrollTargetViewId;
    private boolean liftable;
    private boolean liftableOverride;
    private boolean lifted;
    private List<BaseOnOffsetChangedListener> listeners;
    private int pendingAction;
    /* access modifiers changed from: private */
    public Drawable statusBarForeground;
    private int[] tmpStatesArray;
    private int totalScrollRange;

    public interface BaseOnOffsetChangedListener<T extends AppBarLayout> {
        void onOffsetChanged(T t, int i);
    }

    public static abstract class ChildScrollEffect {
        public abstract void onOffsetChanged(AppBarLayout appBarLayout, View view, float f);
    }

    public interface LiftOnScrollListener {
        void onUpdate(float f, int i);
    }

    public interface OnOffsetChangedListener extends BaseOnOffsetChangedListener<AppBarLayout> {
        void onOffsetChanged(AppBarLayout appBarLayout, int i);
    }

    public AppBarLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public AppBarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, C2436R.attr.appBarLayoutStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppBarLayout(android.content.Context r10, android.util.AttributeSet r11, int r12) {
        /*
            r9 = this;
            int r4 = DEF_STYLE_RES
            android.content.Context r0 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r10, r11, r12, r4)
            r9.<init>(r0, r11, r12)
            r6 = -1
            r9.totalScrollRange = r6
            r9.downPreScrollRange = r6
            r9.downScrollRange = r6
            r7 = 0
            r9.pendingAction = r7
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r9.liftOnScrollListeners = r0
            android.content.Context r10 = r9.getContext()
            r0 = 1
            r9.setOrientation(r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            r8 = 21
            if (r0 < r8) goto L_0x0036
            android.view.ViewOutlineProvider r0 = r9.getOutlineProvider()
            android.view.ViewOutlineProvider r1 = android.view.ViewOutlineProvider.BACKGROUND
            if (r0 != r1) goto L_0x0033
            com.google.android.material.appbar.ViewUtilsLollipop.setBoundsViewOutlineProvider(r9)
        L_0x0033:
            com.google.android.material.appbar.ViewUtilsLollipop.setStateListAnimatorFromAttrs(r9, r11, r12, r4)
        L_0x0036:
            int[] r2 = com.google.android.material.C2436R.styleable.AppBarLayout
            int[] r5 = new int[r7]
            r0 = r10
            r1 = r11
            r3 = r12
            android.content.res.TypedArray r0 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r0, r1, r2, r3, r4, r5)
            int r1 = com.google.android.material.C2436R.styleable.AppBarLayout_android_background
            android.graphics.drawable.Drawable r1 = r0.getDrawable(r1)
            androidx.core.view.ViewCompat.setBackground(r9, r1)
            android.graphics.drawable.Drawable r1 = r9.getBackground()
            boolean r1 = r1 instanceof android.graphics.drawable.ColorDrawable
            if (r1 == 0) goto L_0x006e
            android.graphics.drawable.Drawable r1 = r9.getBackground()
            android.graphics.drawable.ColorDrawable r1 = (android.graphics.drawable.ColorDrawable) r1
            com.google.android.material.shape.MaterialShapeDrawable r2 = new com.google.android.material.shape.MaterialShapeDrawable
            r2.<init>()
            int r3 = r1.getColor()
            android.content.res.ColorStateList r3 = android.content.res.ColorStateList.valueOf(r3)
            r2.setFillColor(r3)
            r2.initializeElevationOverlay(r10)
            androidx.core.view.ViewCompat.setBackground(r9, r2)
        L_0x006e:
            int r1 = com.google.android.material.C2436R.styleable.AppBarLayout_expanded
            boolean r1 = r0.hasValue(r1)
            if (r1 == 0) goto L_0x007f
            int r1 = com.google.android.material.C2436R.styleable.AppBarLayout_expanded
            boolean r1 = r0.getBoolean(r1, r7)
            r9.setExpanded(r1, r7, r7)
        L_0x007f:
            int r1 = android.os.Build.VERSION.SDK_INT
            if (r1 < r8) goto L_0x0095
            int r1 = com.google.android.material.C2436R.styleable.AppBarLayout_elevation
            boolean r1 = r0.hasValue(r1)
            if (r1 == 0) goto L_0x0095
            int r1 = com.google.android.material.C2436R.styleable.AppBarLayout_elevation
            int r1 = r0.getDimensionPixelSize(r1, r7)
            float r1 = (float) r1
            com.google.android.material.appbar.ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(r9, r1)
        L_0x0095:
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 26
            if (r1 < r2) goto L_0x00bd
            int r1 = com.google.android.material.C2436R.styleable.AppBarLayout_android_keyboardNavigationCluster
            boolean r1 = r0.hasValue(r1)
            if (r1 == 0) goto L_0x00ac
            int r1 = com.google.android.material.C2436R.styleable.AppBarLayout_android_keyboardNavigationCluster
            boolean r1 = r0.getBoolean(r1, r7)
            r9.setKeyboardNavigationCluster(r1)
        L_0x00ac:
            int r1 = com.google.android.material.C2436R.styleable.AppBarLayout_android_touchscreenBlocksFocus
            boolean r1 = r0.hasValue(r1)
            if (r1 == 0) goto L_0x00bd
            int r1 = com.google.android.material.C2436R.styleable.AppBarLayout_android_touchscreenBlocksFocus
            boolean r1 = r0.getBoolean(r1, r7)
            r9.setTouchscreenBlocksFocus(r1)
        L_0x00bd:
            int r1 = com.google.android.material.C2436R.styleable.AppBarLayout_liftOnScroll
            boolean r1 = r0.getBoolean(r1, r7)
            r9.liftOnScroll = r1
            int r1 = com.google.android.material.C2436R.styleable.AppBarLayout_liftOnScrollTargetViewId
            int r1 = r0.getResourceId(r1, r6)
            r9.liftOnScrollTargetViewId = r1
            int r1 = com.google.android.material.C2436R.styleable.AppBarLayout_statusBarForeground
            android.graphics.drawable.Drawable r1 = r0.getDrawable(r1)
            r9.setStatusBarForeground(r1)
            r0.recycle()
            com.google.android.material.appbar.AppBarLayout$1 r1 = new com.google.android.material.appbar.AppBarLayout$1
            r1.<init>()
            androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(r9, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void addOnOffsetChangedListener(BaseOnOffsetChangedListener listener) {
        if (this.listeners == null) {
            this.listeners = new ArrayList();
        }
        if (listener != null && !this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
    }

    public void addOnOffsetChangedListener(OnOffsetChangedListener listener) {
        addOnOffsetChangedListener((BaseOnOffsetChangedListener) listener);
    }

    public void removeOnOffsetChangedListener(BaseOnOffsetChangedListener listener) {
        List<BaseOnOffsetChangedListener> list = this.listeners;
        if (list != null && listener != null) {
            list.remove(listener);
        }
    }

    public void removeOnOffsetChangedListener(OnOffsetChangedListener listener) {
        removeOnOffsetChangedListener((BaseOnOffsetChangedListener) listener);
    }

    public void addLiftOnScrollListener(LiftOnScrollListener liftOnScrollListener) {
        this.liftOnScrollListeners.add(liftOnScrollListener);
    }

    public boolean removeLiftOnScrollListener(LiftOnScrollListener liftOnScrollListener) {
        return this.liftOnScrollListeners.remove(liftOnScrollListener);
    }

    public void clearLiftOnScrollListener() {
        this.liftOnScrollListeners.clear();
    }

    public void setStatusBarForeground(Drawable drawable) {
        Drawable drawable2 = this.statusBarForeground;
        if (drawable2 != drawable) {
            Drawable drawable3 = null;
            if (drawable2 != null) {
                drawable2.setCallback((Drawable.Callback) null);
            }
            if (drawable != null) {
                drawable3 = drawable.mutate();
            }
            this.statusBarForeground = drawable3;
            if (drawable3 != null) {
                if (drawable3.isStateful()) {
                    this.statusBarForeground.setState(getDrawableState());
                }
                DrawableCompat.setLayoutDirection(this.statusBarForeground, ViewCompat.getLayoutDirection(this));
                this.statusBarForeground.setVisible(getVisibility() == 0, false);
                this.statusBarForeground.setCallback(this);
            }
            updateWillNotDraw();
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setStatusBarForegroundColor(int color) {
        setStatusBarForeground(new ColorDrawable(color));
    }

    public void setStatusBarForegroundResource(int resId) {
        setStatusBarForeground(AppCompatResources.getDrawable(getContext(), resId));
    }

    public Drawable getStatusBarForeground() {
        return this.statusBarForeground;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (shouldDrawStatusBarForeground()) {
            int saveCount = canvas.save();
            canvas.translate(0.0f, (float) (-this.currentOffset));
            this.statusBarForeground.draw(canvas);
            canvas.restoreToCount(saveCount);
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] state = getDrawableState();
        Drawable d = this.statusBarForeground;
        if (d != null && d.isStateful() && d.setState(state)) {
            invalidateDrawable(d);
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who) || who == this.statusBarForeground;
    }

    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        boolean visible = visibility == 0;
        Drawable drawable = this.statusBarForeground;
        if (drawable != null) {
            drawable.setVisible(visible, false);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode != 1073741824 && ViewCompat.getFitsSystemWindows(this) && shouldOffsetFirstChild()) {
            int newHeight = getMeasuredHeight();
            switch (heightMode) {
                case Integer.MIN_VALUE:
                    newHeight = MathUtils.clamp(getMeasuredHeight() + getTopInset(), 0, View.MeasureSpec.getSize(heightMeasureSpec));
                    break;
                case 0:
                    newHeight += getTopInset();
                    break;
            }
            setMeasuredDimension(getMeasuredWidth(), newHeight);
        }
        invalidateScrollRanges();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        boolean z = true;
        if (ViewCompat.getFitsSystemWindows(this) && shouldOffsetFirstChild()) {
            int topInset = getTopInset();
            for (int z2 = getChildCount() - 1; z2 >= 0; z2--) {
                ViewCompat.offsetTopAndBottom(getChildAt(z2), topInset);
            }
        }
        invalidateScrollRanges();
        this.haveChildWithInterpolator = false;
        int i = 0;
        int z3 = getChildCount();
        while (true) {
            if (i >= z3) {
                break;
            } else if (((LayoutParams) getChildAt(i).getLayoutParams()).getScrollInterpolator() != null) {
                this.haveChildWithInterpolator = true;
                break;
            } else {
                i++;
            }
        }
        Drawable drawable = this.statusBarForeground;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getTopInset());
        }
        if (!this.liftableOverride) {
            if (!this.liftOnScroll && !hasCollapsibleChild()) {
                z = false;
            }
            setLiftableState(z);
        }
    }

    private void updateWillNotDraw() {
        setWillNotDraw(!shouldDrawStatusBarForeground());
    }

    private boolean shouldDrawStatusBarForeground() {
        return this.statusBarForeground != null && getTopInset() > 0;
    }

    private boolean hasCollapsibleChild() {
        int z = getChildCount();
        for (int i = 0; i < z; i++) {
            if (((LayoutParams) getChildAt(i).getLayoutParams()).isCollapsible()) {
                return true;
            }
        }
        return false;
    }

    private void invalidateScrollRanges() {
        this.totalScrollRange = -1;
        this.downPreScrollRange = -1;
        this.downScrollRange = -1;
    }

    public void setOrientation(int orientation) {
        if (orientation == 1) {
            super.setOrientation(orientation);
            return;
        }
        throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    public CoordinatorLayout.Behavior<AppBarLayout> getBehavior() {
        return new Behavior();
    }

    public void setElevation(float elevation) {
        super.setElevation(elevation);
        MaterialShapeUtils.setElevation(this, elevation);
    }

    public void setExpanded(boolean expanded) {
        setExpanded(expanded, ViewCompat.isLaidOut(this));
    }

    public void setExpanded(boolean expanded, boolean animate) {
        setExpanded(expanded, animate, true);
    }

    private void setExpanded(boolean expanded, boolean animate, boolean force) {
        int i = 0;
        int i2 = (expanded ? 1 : 2) | (animate ? 4 : 0);
        if (force) {
            i = 8;
        }
        this.pendingAction = i2 | i;
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        if (Build.VERSION.SDK_INT >= 19 && (p instanceof LinearLayout.LayoutParams)) {
            return new LayoutParams((LinearLayout.LayoutParams) p);
        }
        if (p instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) p);
        }
        return new LayoutParams(p);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clearLiftOnScrollTargetView();
    }

    /* access modifiers changed from: package-private */
    public boolean hasChildWithInterpolator() {
        return this.haveChildWithInterpolator;
    }

    public final int getTotalScrollRange() {
        int i = this.totalScrollRange;
        if (i != -1) {
            return i;
        }
        int range = 0;
        int i2 = 0;
        int z = getChildCount();
        while (true) {
            if (i2 >= z) {
                break;
            }
            View child = getChildAt(i2);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            int childHeight = child.getMeasuredHeight();
            int flags = lp.scrollFlags;
            if ((flags & 1) == 0) {
                break;
            }
            range += lp.topMargin + childHeight + lp.bottomMargin;
            if (i2 == 0 && ViewCompat.getFitsSystemWindows(child)) {
                range -= getTopInset();
            }
            if ((flags & 2) != 0) {
                range -= ViewCompat.getMinimumHeight(child);
                break;
            }
            i2++;
        }
        int max = Math.max(0, range);
        this.totalScrollRange = max;
        return max;
    }

    /* access modifiers changed from: package-private */
    public boolean hasScrollableChildren() {
        return getTotalScrollRange() != 0;
    }

    /* access modifiers changed from: package-private */
    public int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    /* access modifiers changed from: package-private */
    public int getDownNestedPreScrollRange() {
        int childRange;
        int i = this.downPreScrollRange;
        if (i != -1) {
            return i;
        }
        int range = 0;
        for (int i2 = getChildCount() - 1; i2 >= 0; i2--) {
            View child = getChildAt(i2);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            int childHeight = child.getMeasuredHeight();
            int flags = lp.scrollFlags;
            if ((flags & 5) == 5) {
                int childRange2 = lp.topMargin + lp.bottomMargin;
                if ((flags & 8) != 0) {
                    childRange = childRange2 + ViewCompat.getMinimumHeight(child);
                } else if ((flags & 2) != 0) {
                    childRange = childRange2 + (childHeight - ViewCompat.getMinimumHeight(child));
                } else {
                    childRange = childRange2 + childHeight;
                }
                if (i2 == 0 && ViewCompat.getFitsSystemWindows(child)) {
                    childRange = Math.min(childRange, childHeight - getTopInset());
                }
                range += childRange;
            } else if (range > 0) {
                break;
            }
        }
        int max = Math.max(0, range);
        this.downPreScrollRange = max;
        return max;
    }

    /* access modifiers changed from: package-private */
    public int getDownNestedScrollRange() {
        int i = this.downScrollRange;
        if (i != -1) {
            return i;
        }
        int range = 0;
        int i2 = 0;
        int z = getChildCount();
        while (true) {
            if (i2 >= z) {
                break;
            }
            View child = getChildAt(i2);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            int flags = lp.scrollFlags;
            if ((flags & 1) == 0) {
                break;
            }
            range += childHeight;
            if ((flags & 2) != 0) {
                range -= ViewCompat.getMinimumHeight(child);
                break;
            }
            i2++;
        }
        int max = Math.max(0, range);
        this.downScrollRange = max;
        return max;
    }

    /* access modifiers changed from: package-private */
    public void onOffsetChanged(int offset) {
        this.currentOffset = offset;
        if (!willNotDraw()) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        List<BaseOnOffsetChangedListener> list = this.listeners;
        if (list != null) {
            int z = list.size();
            for (int i = 0; i < z; i++) {
                BaseOnOffsetChangedListener listener = this.listeners.get(i);
                if (listener != null) {
                    listener.onOffsetChanged(this, offset);
                }
            }
        }
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        int minHeight = ViewCompat.getMinimumHeight(this);
        if (minHeight != 0) {
            return (minHeight * 2) + topInset;
        }
        int childCount = getChildCount();
        int lastChildMinHeight = childCount >= 1 ? ViewCompat.getMinimumHeight(getChildAt(childCount - 1)) : 0;
        if (lastChildMinHeight != 0) {
            return (lastChildMinHeight * 2) + topInset;
        }
        return getHeight() / 3;
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int extraSpace) {
        if (this.tmpStatesArray == null) {
            this.tmpStatesArray = new int[4];
        }
        int[] extraStates = this.tmpStatesArray;
        int[] states = super.onCreateDrawableState(extraStates.length + extraSpace);
        extraStates[0] = this.liftable ? C2436R.attr.state_liftable : -C2436R.attr.state_liftable;
        extraStates[1] = (!this.liftable || !this.lifted) ? -C2436R.attr.state_lifted : C2436R.attr.state_lifted;
        extraStates[2] = this.liftable ? C2436R.attr.state_collapsible : -C2436R.attr.state_collapsible;
        extraStates[3] = (!this.liftable || !this.lifted) ? -C2436R.attr.state_collapsed : C2436R.attr.state_collapsed;
        return mergeDrawableStates(states, extraStates);
    }

    public boolean setLiftable(boolean liftable2) {
        this.liftableOverride = true;
        return setLiftableState(liftable2);
    }

    public void setLiftableOverrideEnabled(boolean enabled) {
        this.liftableOverride = enabled;
    }

    private boolean setLiftableState(boolean liftable2) {
        if (this.liftable == liftable2) {
            return false;
        }
        this.liftable = liftable2;
        refreshDrawableState();
        return true;
    }

    public boolean setLifted(boolean lifted2) {
        return setLiftedState(lifted2, true);
    }

    public boolean isLifted() {
        return this.lifted;
    }

    /* access modifiers changed from: package-private */
    public boolean setLiftedState(boolean lifted2) {
        return setLiftedState(lifted2, !this.liftableOverride);
    }

    /* access modifiers changed from: package-private */
    public boolean setLiftedState(boolean lifted2, boolean force) {
        if (!force || this.lifted == lifted2) {
            return false;
        }
        this.lifted = lifted2;
        refreshDrawableState();
        if (!this.liftOnScroll || !(getBackground() instanceof MaterialShapeDrawable)) {
            return true;
        }
        startLiftOnScrollElevationOverlayAnimation((MaterialShapeDrawable) getBackground(), lifted2);
        return true;
    }

    private void startLiftOnScrollElevationOverlayAnimation(final MaterialShapeDrawable background, boolean lifted2) {
        float appBarElevation = getResources().getDimension(C2436R.dimen.design_appbar_elevation);
        float toElevation = 0.0f;
        float fromElevation = lifted2 ? 0.0f : appBarElevation;
        if (lifted2) {
            toElevation = appBarElevation;
        }
        ValueAnimator valueAnimator = this.elevationOverlayAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{fromElevation, toElevation});
        this.elevationOverlayAnimator = ofFloat;
        ofFloat.setDuration((long) getResources().getInteger(C2436R.integer.app_bar_elevation_anim_duration));
        this.elevationOverlayAnimator.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        this.elevationOverlayAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float elevation = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                background.setElevation(elevation);
                if (AppBarLayout.this.statusBarForeground instanceof MaterialShapeDrawable) {
                    ((MaterialShapeDrawable) AppBarLayout.this.statusBarForeground).setElevation(elevation);
                }
                for (LiftOnScrollListener liftOnScrollListener : AppBarLayout.this.liftOnScrollListeners) {
                    liftOnScrollListener.onUpdate(elevation, background.getResolvedTintColor());
                }
            }
        });
        this.elevationOverlayAnimator.start();
    }

    public void setLiftOnScroll(boolean liftOnScroll2) {
        this.liftOnScroll = liftOnScroll2;
    }

    public boolean isLiftOnScroll() {
        return this.liftOnScroll;
    }

    public void setLiftOnScrollTargetViewId(int liftOnScrollTargetViewId2) {
        this.liftOnScrollTargetViewId = liftOnScrollTargetViewId2;
        clearLiftOnScrollTargetView();
    }

    public int getLiftOnScrollTargetViewId() {
        return this.liftOnScrollTargetViewId;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldLift(View defaultScrollingView) {
        View scrollingView = findLiftOnScrollTargetView(defaultScrollingView);
        if (scrollingView == null) {
            scrollingView = defaultScrollingView;
        }
        return scrollingView != null && (scrollingView.canScrollVertically(-1) || scrollingView.getScrollY() > 0);
    }

    private View findLiftOnScrollTargetView(View defaultScrollingView) {
        int i;
        if (this.liftOnScrollTargetView == null && (i = this.liftOnScrollTargetViewId) != -1) {
            View targetView = null;
            if (defaultScrollingView != null) {
                targetView = defaultScrollingView.findViewById(i);
            }
            if (targetView == null && (getParent() instanceof ViewGroup)) {
                targetView = ((ViewGroup) getParent()).findViewById(this.liftOnScrollTargetViewId);
            }
            if (targetView != null) {
                this.liftOnScrollTargetView = new WeakReference<>(targetView);
            }
        }
        WeakReference<View> weakReference = this.liftOnScrollTargetView;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    private void clearLiftOnScrollTargetView() {
        WeakReference<View> weakReference = this.liftOnScrollTargetView;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.liftOnScrollTargetView = null;
    }

    @Deprecated
    public void setTargetElevation(float elevation) {
        if (Build.VERSION.SDK_INT >= 21) {
            ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, elevation);
        }
    }

    @Deprecated
    public float getTargetElevation() {
        return 0.0f;
    }

    /* access modifiers changed from: package-private */
    public int getPendingAction() {
        return this.pendingAction;
    }

    /* access modifiers changed from: package-private */
    public void resetPendingAction() {
        this.pendingAction = 0;
    }

    /* access modifiers changed from: package-private */
    public final int getTopInset() {
        WindowInsetsCompat windowInsetsCompat = this.lastInsets;
        if (windowInsetsCompat != null) {
            return windowInsetsCompat.getSystemWindowInsetTop();
        }
        return 0;
    }

    private boolean shouldOffsetFirstChild() {
        if (getChildCount() <= 0) {
            return false;
        }
        View firstChild = getChildAt(0);
        if (firstChild.getVisibility() == 8 || ViewCompat.getFitsSystemWindows(firstChild)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public WindowInsetsCompat onWindowInsetChanged(WindowInsetsCompat insets) {
        WindowInsetsCompat newInsets = null;
        if (ViewCompat.getFitsSystemWindows(this)) {
            newInsets = insets;
        }
        if (!ObjectsCompat.equals(this.lastInsets, newInsets)) {
            this.lastInsets = newInsets;
            updateWillNotDraw();
            requestLayout();
        }
        return insets;
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        static final int COLLAPSIBLE_FLAGS = 10;
        static final int FLAG_QUICK_RETURN = 5;
        static final int FLAG_SNAP = 17;
        private static final int SCROLL_EFFECT_COMPRESS = 1;
        private static final int SCROLL_EFFECT_NONE = 0;
        public static final int SCROLL_FLAG_ENTER_ALWAYS = 4;
        public static final int SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED = 8;
        public static final int SCROLL_FLAG_EXIT_UNTIL_COLLAPSED = 2;
        public static final int SCROLL_FLAG_NO_SCROLL = 0;
        public static final int SCROLL_FLAG_SCROLL = 1;
        public static final int SCROLL_FLAG_SNAP = 16;
        public static final int SCROLL_FLAG_SNAP_MARGINS = 32;
        private ChildScrollEffect scrollEffect;
        int scrollFlags = 1;
        Interpolator scrollInterpolator;

        @Retention(RetentionPolicy.SOURCE)
        public @interface ScrollFlags {
        }

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, C2436R.styleable.AppBarLayout_Layout);
            this.scrollFlags = a.getInt(C2436R.styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
            setScrollEffect(createScrollEffectFromInt(a.getInt(C2436R.styleable.AppBarLayout_Layout_layout_scrollEffect, 0)));
            if (a.hasValue(C2436R.styleable.AppBarLayout_Layout_layout_scrollInterpolator)) {
                this.scrollInterpolator = android.view.animation.AnimationUtils.loadInterpolator(c, a.getResourceId(C2436R.styleable.AppBarLayout_Layout_layout_scrollInterpolator, 0));
            }
            a.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(int width, int height, float weight) {
            super(width, height, weight);
        }

        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(LinearLayout.LayoutParams source) {
            super(source);
        }

        public LayoutParams(LayoutParams source) {
            super(source);
            this.scrollFlags = source.scrollFlags;
            this.scrollInterpolator = source.scrollInterpolator;
        }

        public void setScrollFlags(int flags) {
            this.scrollFlags = flags;
        }

        public int getScrollFlags() {
            return this.scrollFlags;
        }

        private ChildScrollEffect createScrollEffectFromInt(int scrollEffectInt) {
            switch (scrollEffectInt) {
                case 1:
                    return new CompressChildScrollEffect();
                default:
                    return null;
            }
        }

        public ChildScrollEffect getScrollEffect() {
            return this.scrollEffect;
        }

        public void setScrollEffect(ChildScrollEffect scrollEffect2) {
            this.scrollEffect = scrollEffect2;
        }

        public void setScrollInterpolator(Interpolator interpolator) {
            this.scrollInterpolator = interpolator;
        }

        public Interpolator getScrollInterpolator() {
            return this.scrollInterpolator;
        }

        /* access modifiers changed from: package-private */
        public boolean isCollapsible() {
            int i = this.scrollFlags;
            return (i & 1) == 1 && (i & 10) != 0;
        }
    }

    public static class Behavior extends BaseBehavior<AppBarLayout> {

        public static abstract class DragCallback extends BaseBehavior.BaseDragCallback<AppBarLayout> {
        }

        public /* bridge */ /* synthetic */ int getLeftAndRightOffset() {
            return super.getLeftAndRightOffset();
        }

        public /* bridge */ /* synthetic */ int getTopAndBottomOffset() {
            return super.getTopAndBottomOffset();
        }

        public /* bridge */ /* synthetic */ boolean isHorizontalOffsetEnabled() {
            return super.isHorizontalOffsetEnabled();
        }

        public /* bridge */ /* synthetic */ boolean isVerticalOffsetEnabled() {
            return super.isVerticalOffsetEnabled();
        }

        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i) {
            return super.onLayoutChild(coordinatorLayout, appBarLayout, i);
        }

        public /* bridge */ /* synthetic */ boolean onMeasureChild(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, int i2, int i3, int i4) {
            return super.onMeasureChild(coordinatorLayout, appBarLayout, i, i2, i3, i4);
        }

        public /* bridge */ /* synthetic */ void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int i2, int[] iArr, int i3) {
            super.onNestedPreScroll(coordinatorLayout, appBarLayout, view, i, i2, iArr, i3);
        }

        public /* bridge */ /* synthetic */ void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
            super.onNestedScroll(coordinatorLayout, appBarLayout, view, i, i2, i3, i4, i5, iArr);
        }

        public /* bridge */ /* synthetic */ void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, Parcelable parcelable) {
            super.onRestoreInstanceState(coordinatorLayout, appBarLayout, parcelable);
        }

        public /* bridge */ /* synthetic */ Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            return super.onSaveInstanceState(coordinatorLayout, appBarLayout);
        }

        public /* bridge */ /* synthetic */ boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i, int i2) {
            return super.onStartNestedScroll(coordinatorLayout, appBarLayout, view, view2, i, i2);
        }

        public /* bridge */ /* synthetic */ void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i) {
            super.onStopNestedScroll(coordinatorLayout, appBarLayout, view, i);
        }

        public /* bridge */ /* synthetic */ void setDragCallback(BaseBehavior.BaseDragCallback baseDragCallback) {
            super.setDragCallback(baseDragCallback);
        }

        public /* bridge */ /* synthetic */ void setHorizontalOffsetEnabled(boolean z) {
            super.setHorizontalOffsetEnabled(z);
        }

        public /* bridge */ /* synthetic */ boolean setLeftAndRightOffset(int i) {
            return super.setLeftAndRightOffset(i);
        }

        public /* bridge */ /* synthetic */ boolean setTopAndBottomOffset(int i) {
            return super.setTopAndBottomOffset(i);
        }

        public /* bridge */ /* synthetic */ void setVerticalOffsetEnabled(boolean z) {
            super.setVerticalOffsetEnabled(z);
        }

        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attrs) {
            super(context, attrs);
        }
    }

    protected static class BaseBehavior<T extends AppBarLayout> extends HeaderBehavior<T> {
        private static final int MAX_OFFSET_ANIMATION_DURATION = 600;
        private WeakReference<View> lastNestedScrollingChildRef;
        private int lastStartedType;
        private ValueAnimator offsetAnimator;
        /* access modifiers changed from: private */
        public int offsetDelta;
        private BaseDragCallback onDragCallback;
        private SavedState savedState;

        public static abstract class BaseDragCallback<T extends AppBarLayout> {
            public abstract boolean canDrag(T t);
        }

        public BaseBehavior() {
        }

        public BaseBehavior(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public boolean onStartNestedScroll(CoordinatorLayout parent, T child, View directTargetChild, View target, int nestedScrollAxes, int type) {
            ValueAnimator valueAnimator;
            boolean started = (nestedScrollAxes & 2) != 0 && (child.isLiftOnScroll() || canScrollChildren(parent, child, directTargetChild));
            if (started && (valueAnimator = this.offsetAnimator) != null) {
                valueAnimator.cancel();
            }
            this.lastNestedScrollingChildRef = null;
            this.lastStartedType = type;
            return started;
        }

        private boolean canScrollChildren(CoordinatorLayout parent, T child, View directTargetChild) {
            return child.hasScrollableChildren() && parent.getHeight() - directTargetChild.getHeight() <= child.getHeight();
        }

        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, T child, View target, int dx, int dy, int[] consumed, int type) {
            int max;
            int min;
            if (dy != 0) {
                if (dy < 0) {
                    int min2 = -child.getTotalScrollRange();
                    min = min2;
                    max = child.getDownNestedPreScrollRange() + min2;
                } else {
                    min = -child.getUpNestedPreScrollRange();
                    max = 0;
                }
                if (min != max) {
                    consumed[1] = scroll(coordinatorLayout, child, dy, min, max);
                }
            }
            if (child.isLiftOnScroll()) {
                T t = child;
                child.setLiftedState(child.shouldLift(target));
                return;
            }
            T t2 = child;
        }

        public void onNestedScroll(CoordinatorLayout coordinatorLayout, T child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, int[] consumed) {
            if (dyUnconsumed < 0) {
                consumed[1] = scroll(coordinatorLayout, child, dyUnconsumed, -child.getDownNestedScrollRange(), 0);
            }
            if (dyUnconsumed == 0) {
                updateAccessibilityActions(coordinatorLayout, child);
            }
        }

        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, T abl, View target, int type) {
            if (this.lastStartedType == 0 || type == 1) {
                snapToChildIfNeeded(coordinatorLayout, abl);
                if (abl.isLiftOnScroll()) {
                    abl.setLiftedState(abl.shouldLift(target));
                }
            }
            this.lastNestedScrollingChildRef = new WeakReference<>(target);
        }

        public void setDragCallback(BaseDragCallback callback) {
            this.onDragCallback = callback;
        }

        private void animateOffsetTo(CoordinatorLayout coordinatorLayout, T child, int offset, float velocity) {
            int duration;
            int distance = Math.abs(getTopBottomOffsetForScrollingSibling() - offset);
            float velocity2 = Math.abs(velocity);
            if (velocity2 > 0.0f) {
                duration = Math.round((((float) distance) / velocity2) * 1000.0f) * 3;
            } else {
                duration = (int) ((1.0f + (((float) distance) / ((float) child.getHeight()))) * 150.0f);
            }
            animateOffsetWithDuration(coordinatorLayout, child, offset, duration);
        }

        private void animateOffsetWithDuration(final CoordinatorLayout coordinatorLayout, final T child, int offset, int duration) {
            int currentOffset = getTopBottomOffsetForScrollingSibling();
            if (currentOffset == offset) {
                ValueAnimator valueAnimator = this.offsetAnimator;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.offsetAnimator.cancel();
                    return;
                }
                return;
            }
            ValueAnimator valueAnimator2 = this.offsetAnimator;
            if (valueAnimator2 == null) {
                ValueAnimator valueAnimator3 = new ValueAnimator();
                this.offsetAnimator = valueAnimator3;
                valueAnimator3.setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
                this.offsetAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animator) {
                        BaseBehavior.this.setHeaderTopBottomOffset(coordinatorLayout, child, ((Integer) animator.getAnimatedValue()).intValue());
                    }
                });
            } else {
                valueAnimator2.cancel();
            }
            this.offsetAnimator.setDuration((long) Math.min(duration, 600));
            this.offsetAnimator.setIntValues(new int[]{currentOffset, offset});
            this.offsetAnimator.start();
        }

        private int getChildIndexOnOffset(T abl, int offset) {
            int count = abl.getChildCount();
            for (int i = 0; i < count; i++) {
                View child = abl.getChildAt(i);
                int top = child.getTop();
                int bottom = child.getBottom();
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (checkFlag(lp.getScrollFlags(), 32)) {
                    top -= lp.topMargin;
                    bottom += lp.bottomMargin;
                }
                if (top <= (-offset) && bottom >= (-offset)) {
                    return i;
                }
            }
            return -1;
        }

        private void snapToChildIfNeeded(CoordinatorLayout coordinatorLayout, T abl) {
            int offset = getTopBottomOffsetForScrollingSibling();
            int offsetChildIndex = getChildIndexOnOffset(abl, offset);
            if (offsetChildIndex >= 0) {
                View offsetChild = abl.getChildAt(offsetChildIndex);
                LayoutParams lp = (LayoutParams) offsetChild.getLayoutParams();
                int flags = lp.getScrollFlags();
                if ((flags & 17) == 17) {
                    int snapTop = -offsetChild.getTop();
                    int snapBottom = -offsetChild.getBottom();
                    if (offsetChildIndex == abl.getChildCount() - 1) {
                        snapBottom += abl.getTopInset() + abl.getPaddingTop();
                    }
                    if (checkFlag(flags, 2)) {
                        snapBottom += ViewCompat.getMinimumHeight(offsetChild);
                    } else if (checkFlag(flags, 5)) {
                        int seam = ViewCompat.getMinimumHeight(offsetChild) + snapBottom;
                        if (offset < seam) {
                            snapTop = seam;
                        } else {
                            snapBottom = seam;
                        }
                    }
                    if (checkFlag(flags, 32)) {
                        snapTop += lp.topMargin;
                        snapBottom -= lp.bottomMargin;
                    }
                    animateOffsetTo(coordinatorLayout, abl, MathUtils.clamp(offset < (snapBottom + snapTop) / 2 ? snapBottom : snapTop, -abl.getTotalScrollRange(), 0), 0.0f);
                }
            }
        }

        private static boolean checkFlag(int flags, int check) {
            return (flags & check) == check;
        }

        public boolean onMeasureChild(CoordinatorLayout parent, T child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
            if (((CoordinatorLayout.LayoutParams) child.getLayoutParams()).height != -2) {
                return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
            }
            parent.onMeasureChild(child, parentWidthMeasureSpec, widthUsed, View.MeasureSpec.makeMeasureSpec(0, 0), heightUsed);
            return true;
        }

        public boolean onLayoutChild(CoordinatorLayout parent, T abl, int layoutDirection) {
            int offset;
            boolean handled = super.onLayoutChild(parent, abl, layoutDirection);
            int pendingAction = abl.getPendingAction();
            SavedState savedState2 = this.savedState;
            if (savedState2 == null || (pendingAction & 8) != 0) {
                if (pendingAction != 0) {
                    boolean animate = (pendingAction & 4) != 0;
                    if ((pendingAction & 2) != 0) {
                        int offset2 = -abl.getUpNestedPreScrollRange();
                        if (animate) {
                            animateOffsetTo(parent, abl, offset2, 0.0f);
                        } else {
                            setHeaderTopBottomOffset(parent, abl, offset2);
                        }
                    } else if ((pendingAction & 1) != 0) {
                        if (animate) {
                            animateOffsetTo(parent, abl, 0, 0.0f);
                        } else {
                            setHeaderTopBottomOffset(parent, abl, 0);
                        }
                    }
                }
            } else if (savedState2.fullyScrolled) {
                setHeaderTopBottomOffset(parent, abl, -abl.getTotalScrollRange());
            } else {
                View child = abl.getChildAt(this.savedState.firstVisibleChildIndex);
                int offset3 = -child.getBottom();
                if (this.savedState.firstVisibleChildAtMinimumHeight) {
                    offset = offset3 + ViewCompat.getMinimumHeight(child) + abl.getTopInset();
                } else {
                    offset = offset3 + Math.round(((float) child.getHeight()) * this.savedState.firstVisibleChildPercentageShown);
                }
                setHeaderTopBottomOffset(parent, abl, offset);
            }
            abl.resetPendingAction();
            this.savedState = null;
            setTopAndBottomOffset(MathUtils.clamp(getTopAndBottomOffset(), -abl.getTotalScrollRange(), 0));
            updateAppBarLayoutDrawableState(parent, abl, getTopAndBottomOffset(), 0, true);
            abl.onOffsetChanged(getTopAndBottomOffset());
            updateAccessibilityActions(parent, abl);
            return handled;
        }

        private void updateAccessibilityActions(CoordinatorLayout coordinatorLayout, T appBarLayout) {
            ViewCompat.removeAccessibilityAction(coordinatorLayout, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD.getId());
            ViewCompat.removeAccessibilityAction(coordinatorLayout, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD.getId());
            View scrollingView = findFirstScrollingChild(coordinatorLayout);
            if (scrollingView != null && appBarLayout.getTotalScrollRange() != 0 && (((CoordinatorLayout.LayoutParams) scrollingView.getLayoutParams()).getBehavior() instanceof ScrollingViewBehavior)) {
                addAccessibilityScrollActions(coordinatorLayout, appBarLayout, scrollingView);
            }
        }

        private void addAccessibilityScrollActions(CoordinatorLayout coordinatorLayout, T appBarLayout, View scrollingView) {
            if (getTopBottomOffsetForScrollingSibling() != (-appBarLayout.getTotalScrollRange()) && scrollingView.canScrollVertically(1)) {
                addActionToExpand(coordinatorLayout, appBarLayout, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD, false);
            }
            if (getTopBottomOffsetForScrollingSibling() == 0) {
                return;
            }
            if (scrollingView.canScrollVertically(-1)) {
                int dy = -appBarLayout.getDownNestedPreScrollRange();
                if (dy != 0) {
                    final CoordinatorLayout coordinatorLayout2 = coordinatorLayout;
                    final T t = appBarLayout;
                    final View view = scrollingView;
                    final int i = dy;
                    ViewCompat.replaceAccessibilityAction(coordinatorLayout, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD, (CharSequence) null, new AccessibilityViewCommand() {
                        public boolean perform(View view, AccessibilityViewCommand.CommandArguments arguments) {
                            BaseBehavior.this.onNestedPreScroll(coordinatorLayout2, t, view, 0, i, new int[]{0, 0}, 1);
                            return true;
                        }
                    });
                    return;
                }
                return;
            }
            addActionToExpand(coordinatorLayout, appBarLayout, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD, true);
        }

        private void addActionToExpand(CoordinatorLayout parent, final T appBarLayout, AccessibilityNodeInfoCompat.AccessibilityActionCompat action, final boolean expand) {
            ViewCompat.replaceAccessibilityAction(parent, action, (CharSequence) null, new AccessibilityViewCommand() {
                public boolean perform(View view, AccessibilityViewCommand.CommandArguments arguments) {
                    appBarLayout.setExpanded(expand);
                    return true;
                }
            });
        }

        /* access modifiers changed from: package-private */
        public boolean canDragView(T view) {
            BaseDragCallback baseDragCallback = this.onDragCallback;
            if (baseDragCallback != null) {
                return baseDragCallback.canDrag(view);
            }
            WeakReference<View> weakReference = this.lastNestedScrollingChildRef;
            if (weakReference == null) {
                return true;
            }
            View scrollingView = (View) weakReference.get();
            if (scrollingView == null || !scrollingView.isShown() || scrollingView.canScrollVertically(-1)) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public void onFlingFinished(CoordinatorLayout parent, T layout) {
            snapToChildIfNeeded(parent, layout);
            if (layout.isLiftOnScroll()) {
                layout.setLiftedState(layout.shouldLift(findFirstScrollingChild(parent)));
            }
        }

        /* access modifiers changed from: package-private */
        public int getMaxDragOffset(T view) {
            return -view.getDownNestedScrollRange();
        }

        /* access modifiers changed from: package-private */
        public int getScrollRangeForDragFling(T view) {
            return view.getTotalScrollRange();
        }

        /* access modifiers changed from: package-private */
        public int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, T appBarLayout, int newOffset, int minOffset, int maxOffset) {
            int curOffset = getTopBottomOffsetForScrollingSibling();
            int consumed = 0;
            if (minOffset == 0 || curOffset < minOffset || curOffset > maxOffset) {
                this.offsetDelta = 0;
            } else {
                int newOffset2 = MathUtils.clamp(newOffset, minOffset, maxOffset);
                if (curOffset != newOffset2) {
                    int interpolatedOffset = appBarLayout.hasChildWithInterpolator() ? interpolateOffset(appBarLayout, newOffset2) : newOffset2;
                    boolean offsetChanged = setTopAndBottomOffset(interpolatedOffset);
                    consumed = curOffset - newOffset2;
                    this.offsetDelta = newOffset2 - interpolatedOffset;
                    int i = 1;
                    if (offsetChanged) {
                        for (int i2 = 0; i2 < appBarLayout.getChildCount(); i2++) {
                            LayoutParams params = (LayoutParams) appBarLayout.getChildAt(i2).getLayoutParams();
                            ChildScrollEffect scrollEffect = params.getScrollEffect();
                            if (!(scrollEffect == null || (params.getScrollFlags() & 1) == 0)) {
                                scrollEffect.onOffsetChanged(appBarLayout, appBarLayout.getChildAt(i2), (float) getTopAndBottomOffset());
                            }
                        }
                    }
                    if (!offsetChanged && appBarLayout.hasChildWithInterpolator()) {
                        coordinatorLayout.dispatchDependentViewsChanged(appBarLayout);
                    }
                    appBarLayout.onOffsetChanged(getTopAndBottomOffset());
                    if (newOffset2 < curOffset) {
                        i = -1;
                    }
                    updateAppBarLayoutDrawableState(coordinatorLayout, appBarLayout, newOffset2, i, false);
                }
            }
            updateAccessibilityActions(coordinatorLayout, appBarLayout);
            return consumed;
        }

        /* access modifiers changed from: package-private */
        public boolean isOffsetAnimatorRunning() {
            ValueAnimator valueAnimator = this.offsetAnimator;
            return valueAnimator != null && valueAnimator.isRunning();
        }

        private int interpolateOffset(T layout, int offset) {
            int absOffset = Math.abs(offset);
            int i = 0;
            int z = layout.getChildCount();
            while (true) {
                if (i >= z) {
                    break;
                }
                View child = layout.getChildAt(i);
                LayoutParams childLp = (LayoutParams) child.getLayoutParams();
                Interpolator interpolator = childLp.getScrollInterpolator();
                if (absOffset < child.getTop() || absOffset > child.getBottom()) {
                    i++;
                } else if (interpolator != null) {
                    int childScrollableHeight = 0;
                    int flags = childLp.getScrollFlags();
                    if ((flags & 1) != 0) {
                        childScrollableHeight = 0 + child.getHeight() + childLp.topMargin + childLp.bottomMargin;
                        if ((flags & 2) != 0) {
                            childScrollableHeight -= ViewCompat.getMinimumHeight(child);
                        }
                    }
                    if (ViewCompat.getFitsSystemWindows(child)) {
                        childScrollableHeight -= layout.getTopInset();
                    }
                    if (childScrollableHeight > 0) {
                        return Integer.signum(offset) * (child.getTop() + Math.round(((float) childScrollableHeight) * interpolator.getInterpolation(((float) (absOffset - child.getTop())) / ((float) childScrollableHeight))));
                    }
                }
            }
            return offset;
        }

        private void updateAppBarLayoutDrawableState(CoordinatorLayout parent, T layout, int offset, int direction, boolean forceJump) {
            View child = getAppBarChildOnOffset(layout, offset);
            boolean lifted = false;
            if (child != null) {
                int flags = ((LayoutParams) child.getLayoutParams()).getScrollFlags();
                if ((flags & 1) != 0) {
                    int minHeight = ViewCompat.getMinimumHeight(child);
                    boolean z = false;
                    if (direction > 0 && (flags & 12) != 0) {
                        if ((-offset) >= (child.getBottom() - minHeight) - layout.getTopInset()) {
                            z = true;
                        }
                        lifted = z;
                    } else if ((flags & 2) != 0) {
                        if ((-offset) >= (child.getBottom() - minHeight) - layout.getTopInset()) {
                            z = true;
                        }
                        lifted = z;
                    }
                }
            }
            if (layout.isLiftOnScroll()) {
                lifted = layout.shouldLift(findFirstScrollingChild(parent));
            }
            boolean changed = layout.setLiftedState(lifted);
            if (forceJump || (changed && shouldJumpElevationState(parent, layout))) {
                layout.jumpDrawablesToCurrentState();
            }
        }

        private boolean shouldJumpElevationState(CoordinatorLayout parent, T layout) {
            List<View> dependencies = parent.getDependents(layout);
            int size = dependencies.size();
            for (int i = 0; i < size; i++) {
                CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) dependencies.get(i).getLayoutParams()).getBehavior();
                if (behavior instanceof ScrollingViewBehavior) {
                    return ((ScrollingViewBehavior) behavior).getOverlayTop() != 0;
                }
            }
            return false;
        }

        private static View getAppBarChildOnOffset(AppBarLayout layout, int offset) {
            int absOffset = Math.abs(offset);
            int z = layout.getChildCount();
            for (int i = 0; i < z; i++) {
                View child = layout.getChildAt(i);
                if (absOffset >= child.getTop() && absOffset <= child.getBottom()) {
                    return child;
                }
            }
            return null;
        }

        private View findFirstScrollingChild(CoordinatorLayout parent) {
            int z = parent.getChildCount();
            for (int i = 0; i < z; i++) {
                View child = parent.getChildAt(i);
                if ((child instanceof NestedScrollingChild) || (child instanceof ListView) || (child instanceof ScrollView)) {
                    return child;
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public int getTopBottomOffsetForScrollingSibling() {
            return getTopAndBottomOffset() + this.offsetDelta;
        }

        public Parcelable onSaveInstanceState(CoordinatorLayout parent, T abl) {
            Parcelable superState = super.onSaveInstanceState(parent, abl);
            int offset = getTopAndBottomOffset();
            int i = 0;
            int count = abl.getChildCount();
            while (i < count) {
                View child = abl.getChildAt(i);
                int visBottom = child.getBottom() + offset;
                if (child.getTop() + offset > 0 || visBottom < 0) {
                    i++;
                } else {
                    SavedState ss = new SavedState(superState);
                    boolean z = false;
                    ss.fullyScrolled = (-getTopAndBottomOffset()) >= abl.getTotalScrollRange();
                    ss.firstVisibleChildIndex = i;
                    if (visBottom == ViewCompat.getMinimumHeight(child) + abl.getTopInset()) {
                        z = true;
                    }
                    ss.firstVisibleChildAtMinimumHeight = z;
                    ss.firstVisibleChildPercentageShown = ((float) visBottom) / ((float) child.getHeight());
                    return ss;
                }
            }
            return superState;
        }

        public void onRestoreInstanceState(CoordinatorLayout parent, T appBarLayout, Parcelable state) {
            if (state instanceof SavedState) {
                SavedState savedState2 = (SavedState) state;
                this.savedState = savedState2;
                super.onRestoreInstanceState(parent, appBarLayout, savedState2.getSuperState());
                return;
            }
            super.onRestoreInstanceState(parent, appBarLayout, state);
            this.savedState = null;
        }

        protected static class SavedState extends AbsSavedState {
            public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
                public SavedState createFromParcel(Parcel source, ClassLoader loader) {
                    return new SavedState(source, loader);
                }

                public SavedState createFromParcel(Parcel source) {
                    return new SavedState(source, (ClassLoader) null);
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
            boolean firstVisibleChildAtMinimumHeight;
            int firstVisibleChildIndex;
            float firstVisibleChildPercentageShown;
            boolean fullyScrolled;

            public SavedState(Parcel source, ClassLoader loader) {
                super(source, loader);
                boolean z = true;
                this.fullyScrolled = source.readByte() != 0;
                this.firstVisibleChildIndex = source.readInt();
                this.firstVisibleChildPercentageShown = source.readFloat();
                this.firstVisibleChildAtMinimumHeight = source.readByte() == 0 ? false : z;
            }

            public SavedState(Parcelable superState) {
                super(superState);
            }

            public void writeToParcel(Parcel dest, int flags) {
                super.writeToParcel(dest, flags);
                dest.writeByte(this.fullyScrolled ? (byte) 1 : 0);
                dest.writeInt(this.firstVisibleChildIndex);
                dest.writeFloat(this.firstVisibleChildPercentageShown);
                dest.writeByte(this.firstVisibleChildAtMinimumHeight ? (byte) 1 : 0);
            }
        }
    }

    public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior {
        public /* bridge */ /* synthetic */ int getLeftAndRightOffset() {
            return super.getLeftAndRightOffset();
        }

        public /* bridge */ /* synthetic */ int getTopAndBottomOffset() {
            return super.getTopAndBottomOffset();
        }

        public /* bridge */ /* synthetic */ boolean isHorizontalOffsetEnabled() {
            return super.isHorizontalOffsetEnabled();
        }

        public /* bridge */ /* synthetic */ boolean isVerticalOffsetEnabled() {
            return super.isVerticalOffsetEnabled();
        }

        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            return super.onLayoutChild(coordinatorLayout, view, i);
        }

        public /* bridge */ /* synthetic */ boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
            return super.onMeasureChild(coordinatorLayout, view, i, i2, i3, i4);
        }

        public /* bridge */ /* synthetic */ void setHorizontalOffsetEnabled(boolean z) {
            super.setHorizontalOffsetEnabled(z);
        }

        public /* bridge */ /* synthetic */ boolean setLeftAndRightOffset(int i) {
            return super.setLeftAndRightOffset(i);
        }

        public /* bridge */ /* synthetic */ boolean setTopAndBottomOffset(int i) {
            return super.setTopAndBottomOffset(i);
        }

        public /* bridge */ /* synthetic */ void setVerticalOffsetEnabled(boolean z) {
            super.setVerticalOffsetEnabled(z);
        }

        public ScrollingViewBehavior() {
        }

        public ScrollingViewBehavior(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, C2436R.styleable.ScrollingViewBehavior_Layout);
            setOverlayTop(a.getDimensionPixelSize(C2436R.styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
            a.recycle();
        }

        public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
            return dependency instanceof AppBarLayout;
        }

        public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
            offsetChildAsNeeded(child, dependency);
            updateLiftedStateIfNeeded(child, dependency);
            return false;
        }

        public void onDependentViewRemoved(CoordinatorLayout parent, View child, View dependency) {
            if (dependency instanceof AppBarLayout) {
                ViewCompat.removeAccessibilityAction(parent, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD.getId());
                ViewCompat.removeAccessibilityAction(parent, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD.getId());
            }
        }

        public boolean onRequestChildRectangleOnScreen(CoordinatorLayout parent, View child, Rect rectangle, boolean immediate) {
            AppBarLayout header = findFirstDependency((List) parent.getDependencies(child));
            if (header != null) {
                rectangle.offset(child.getLeft(), child.getTop());
                Rect parentRect = this.tempRect1;
                parentRect.set(0, 0, parent.getWidth(), parent.getHeight());
                if (!parentRect.contains(rectangle)) {
                    header.setExpanded(false, !immediate);
                    return true;
                }
            }
            return false;
        }

        private void offsetChildAsNeeded(View child, View dependency) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) dependency.getLayoutParams()).getBehavior();
            if (behavior instanceof BaseBehavior) {
                ViewCompat.offsetTopAndBottom(child, (((dependency.getBottom() - child.getTop()) + ((BaseBehavior) behavior).offsetDelta) + getVerticalLayoutGap()) - getOverlapPixelsForOffset(dependency));
            }
        }

        /* access modifiers changed from: package-private */
        public float getOverlapRatioForOffset(View header) {
            int availScrollRange;
            if (header instanceof AppBarLayout) {
                AppBarLayout abl = (AppBarLayout) header;
                int totalScrollRange = abl.getTotalScrollRange();
                int preScrollDown = abl.getDownNestedPreScrollRange();
                int offset = getAppBarLayoutOffset(abl);
                if ((preScrollDown == 0 || totalScrollRange + offset > preScrollDown) && (availScrollRange = totalScrollRange - preScrollDown) != 0) {
                    return (((float) offset) / ((float) availScrollRange)) + 1.0f;
                }
            }
            return 0.0f;
        }

        private static int getAppBarLayoutOffset(AppBarLayout abl) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) abl.getLayoutParams()).getBehavior();
            if (behavior instanceof BaseBehavior) {
                return ((BaseBehavior) behavior).getTopBottomOffsetForScrollingSibling();
            }
            return 0;
        }

        /* access modifiers changed from: package-private */
        public AppBarLayout findFirstDependency(List<View> views) {
            int z = views.size();
            for (int i = 0; i < z; i++) {
                View view = views.get(i);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public int getScrollRange(View v) {
            if (v instanceof AppBarLayout) {
                return ((AppBarLayout) v).getTotalScrollRange();
            }
            return super.getScrollRange(v);
        }

        private void updateLiftedStateIfNeeded(View child, View dependency) {
            if (dependency instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) dependency;
                if (appBarLayout.isLiftOnScroll()) {
                    appBarLayout.setLiftedState(appBarLayout.shouldLift(child));
                }
            }
        }
    }

    public static class CompressChildScrollEffect extends ChildScrollEffect {
        private static final float COMPRESS_DISTANCE_FACTOR = 0.3f;
        private final Rect ghostRect = new Rect();
        private final Rect relativeRect = new Rect();

        private static void updateRelativeRect(Rect rect, AppBarLayout appBarLayout, View child) {
            child.getDrawingRect(rect);
            appBarLayout.offsetDescendantRectToMyCoords(child, rect);
            rect.offset(0, -appBarLayout.getTopInset());
        }

        public void onOffsetChanged(AppBarLayout appBarLayout, View child, float offset) {
            updateRelativeRect(this.relativeRect, appBarLayout, child);
            float distanceFromCeiling = ((float) this.relativeRect.top) - Math.abs(offset);
            if (distanceFromCeiling <= 0.0f) {
                float p = MathUtils.clamp(Math.abs(distanceFromCeiling / ((float) this.relativeRect.height())), 0.0f, 1.0f);
                float offsetY = (-distanceFromCeiling) - ((((float) this.relativeRect.height()) * COMPRESS_DISTANCE_FACTOR) * (1.0f - ((1.0f - p) * (1.0f - p))));
                child.setTranslationY(offsetY);
                child.getDrawingRect(this.ghostRect);
                this.ghostRect.offset(0, (int) (-offsetY));
                ViewCompat.setClipBounds(child, this.ghostRect);
                return;
            }
            ViewCompat.setClipBounds(child, (Rect) null);
            child.setTranslationY(0.0f);
        }
    }
}
