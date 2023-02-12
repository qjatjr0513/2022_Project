package com.google.android.material.appbar;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.badge.BadgeDrawable;
import java.util.List;

abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior<View> {
    private int overlayTop;
    final Rect tempRect1 = new Rect();
    final Rect tempRect2 = new Rect();
    private int verticalLayoutGap = 0;

    /* access modifiers changed from: package-private */
    public abstract View findFirstDependency(List<View> list);

    public HeaderScrollingViewBehavior() {
    }

    public HeaderScrollingViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onMeasureChild(CoordinatorLayout parent, View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        WindowInsetsCompat parentInsets;
        int childLpHeight = child.getLayoutParams().height;
        if (childLpHeight == -1 || childLpHeight == -2) {
            View header = findFirstDependency(parent.getDependencies(child));
            if (header != null) {
                int availableHeight = View.MeasureSpec.getSize(parentHeightMeasureSpec);
                if (availableHeight <= 0) {
                    availableHeight = parent.getHeight();
                } else if (ViewCompat.getFitsSystemWindows(header) && (parentInsets = parent.getLastWindowInsets()) != null) {
                    availableHeight += parentInsets.getSystemWindowInsetTop() + parentInsets.getSystemWindowInsetBottom();
                }
                int height = getScrollRange(header) + availableHeight;
                int headerHeight = header.getMeasuredHeight();
                if (shouldHeaderOverlapScrollingChild()) {
                    child.setTranslationY((float) (-headerHeight));
                } else {
                    View view = child;
                    height -= headerHeight;
                }
                parent.onMeasureChild(child, parentWidthMeasureSpec, widthUsed, View.MeasureSpec.makeMeasureSpec(height, childLpHeight == -1 ? 1073741824 : Integer.MIN_VALUE), heightUsed);
                return true;
            }
            View view2 = child;
            return false;
        }
        View view3 = child;
        return false;
    }

    /* access modifiers changed from: protected */
    public void layoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        View header = findFirstDependency(parent.getDependencies(child));
        if (header != null) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            Rect available = this.tempRect1;
            available.set(parent.getPaddingLeft() + lp.leftMargin, header.getBottom() + lp.topMargin, (parent.getWidth() - parent.getPaddingRight()) - lp.rightMargin, ((parent.getHeight() + header.getBottom()) - parent.getPaddingBottom()) - lp.bottomMargin);
            WindowInsetsCompat parentInsets = parent.getLastWindowInsets();
            if (parentInsets != null && ViewCompat.getFitsSystemWindows(parent) && !ViewCompat.getFitsSystemWindows(child)) {
                available.left += parentInsets.getSystemWindowInsetLeft();
                available.right -= parentInsets.getSystemWindowInsetRight();
            }
            Rect out = this.tempRect2;
            GravityCompat.apply(resolveGravity(lp.gravity), child.getMeasuredWidth(), child.getMeasuredHeight(), available, out, layoutDirection);
            int overlap = getOverlapPixelsForOffset(header);
            child.layout(out.left, out.top - overlap, out.right, out.bottom - overlap);
            this.verticalLayoutGap = out.top - header.getBottom();
            return;
        }
        super.layoutChild(parent, child, layoutDirection);
        this.verticalLayoutGap = 0;
    }

    /* access modifiers changed from: protected */
    public boolean shouldHeaderOverlapScrollingChild() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public float getOverlapRatioForOffset(View header) {
        return 1.0f;
    }

    /* access modifiers changed from: package-private */
    public final int getOverlapPixelsForOffset(View header) {
        if (this.overlayTop == 0) {
            return 0;
        }
        float overlapRatioForOffset = getOverlapRatioForOffset(header);
        int i = this.overlayTop;
        return MathUtils.clamp((int) (overlapRatioForOffset * ((float) i)), 0, i);
    }

    private static int resolveGravity(int gravity) {
        return gravity == 0 ? BadgeDrawable.TOP_START : gravity;
    }

    /* access modifiers changed from: package-private */
    public int getScrollRange(View v) {
        return v.getMeasuredHeight();
    }

    /* access modifiers changed from: package-private */
    public final int getVerticalLayoutGap() {
        return this.verticalLayoutGap;
    }

    public final void setOverlayTop(int overlayTop2) {
        this.overlayTop = overlayTop2;
    }

    public final int getOverlayTop() {
        return this.overlayTop;
    }
}
