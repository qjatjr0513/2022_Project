package com.google.android.material.navigation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.C2436R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.motion.MotionUtils;

public abstract class NavigationBarItemView extends FrameLayout implements MenuView.ItemView {
    private static final ActiveIndicatorTransform ACTIVE_INDICATOR_LABELED_TRANSFORM = new ActiveIndicatorTransform();
    private static final ActiveIndicatorTransform ACTIVE_INDICATOR_UNLABELED_TRANSFORM = new ActiveIndicatorUnlabeledTransform();
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final int INVALID_ITEM_POSITION = -1;
    private ValueAnimator activeIndicatorAnimator;
    private int activeIndicatorDesiredHeight = 0;
    private int activeIndicatorDesiredWidth = 0;
    private boolean activeIndicatorEnabled = false;
    private int activeIndicatorMarginHorizontal = 0;
    private float activeIndicatorProgress = 0.0f;
    private boolean activeIndicatorResizeable = false;
    private ActiveIndicatorTransform activeIndicatorTransform = ACTIVE_INDICATOR_LABELED_TRANSFORM;
    private final View activeIndicatorView;
    private BadgeDrawable badgeDrawable;
    /* access modifiers changed from: private */
    public final ImageView icon;
    private final FrameLayout iconContainer;
    private ColorStateList iconTint;
    private boolean initialized = false;
    private boolean isShifting;
    private MenuItemImpl itemData;
    private int itemPaddingBottom;
    private int itemPaddingTop;
    private int itemPosition = -1;
    private final ViewGroup labelGroup;
    private int labelVisibilityMode;
    private final TextView largeLabel;
    private Drawable originalIconDrawable;
    private float scaleDownFactor;
    private float scaleUpFactor;
    private float shiftAmount;
    private final TextView smallLabel;
    private Drawable wrappedIconDrawable;

    /* access modifiers changed from: protected */
    public abstract int getItemLayoutResId();

    public NavigationBarItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(getItemLayoutResId(), this, true);
        this.iconContainer = (FrameLayout) findViewById(C2436R.C2439id.navigation_bar_item_icon_container);
        this.activeIndicatorView = findViewById(C2436R.C2439id.navigation_bar_item_active_indicator_view);
        ImageView imageView = (ImageView) findViewById(C2436R.C2439id.navigation_bar_item_icon_view);
        this.icon = imageView;
        ViewGroup viewGroup = (ViewGroup) findViewById(C2436R.C2439id.navigation_bar_item_labels_group);
        this.labelGroup = viewGroup;
        TextView textView = (TextView) findViewById(C2436R.C2439id.navigation_bar_item_small_label_view);
        this.smallLabel = textView;
        TextView textView2 = (TextView) findViewById(C2436R.C2439id.navigation_bar_item_large_label_view);
        this.largeLabel = textView2;
        setBackgroundResource(getItemBackgroundResId());
        this.itemPaddingTop = getResources().getDimensionPixelSize(getItemDefaultMarginResId());
        this.itemPaddingBottom = viewGroup.getPaddingBottom();
        ViewCompat.setImportantForAccessibility(textView, 2);
        ViewCompat.setImportantForAccessibility(textView2, 2);
        setFocusable(true);
        calculateTextScaleFactors(textView.getTextSize(), textView2.getTextSize());
        if (imageView != null) {
            imageView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    if (NavigationBarItemView.this.icon.getVisibility() == 0) {
                        NavigationBarItemView navigationBarItemView = NavigationBarItemView.this;
                        navigationBarItemView.tryUpdateBadgeBounds(navigationBarItemView.icon);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public int getSuggestedMinimumWidth() {
        FrameLayout.LayoutParams labelGroupParams = (FrameLayout.LayoutParams) this.labelGroup.getLayoutParams();
        return Math.max(getSuggestedIconWidth(), labelGroupParams.leftMargin + this.labelGroup.getMeasuredWidth() + labelGroupParams.rightMargin);
    }

    /* access modifiers changed from: protected */
    public int getSuggestedMinimumHeight() {
        FrameLayout.LayoutParams labelGroupParams = (FrameLayout.LayoutParams) this.labelGroup.getLayoutParams();
        return getSuggestedIconHeight() + labelGroupParams.topMargin + this.labelGroup.getMeasuredHeight() + labelGroupParams.bottomMargin;
    }

    public void initialize(MenuItemImpl itemData2, int menuType) {
        CharSequence tooltipText;
        this.itemData = itemData2;
        setCheckable(itemData2.isCheckable());
        setChecked(itemData2.isChecked());
        setEnabled(itemData2.isEnabled());
        setIcon(itemData2.getIcon());
        setTitle(itemData2.getTitle());
        setId(itemData2.getItemId());
        if (!TextUtils.isEmpty(itemData2.getContentDescription())) {
            setContentDescription(itemData2.getContentDescription());
        }
        if (!TextUtils.isEmpty(itemData2.getTooltipText())) {
            tooltipText = itemData2.getTooltipText();
        } else {
            tooltipText = itemData2.getTitle();
        }
        if (Build.VERSION.SDK_INT < 21 || Build.VERSION.SDK_INT > 23) {
            TooltipCompat.setTooltipText(this, tooltipText);
        }
        setVisibility(itemData2.isVisible() ? 0 : 8);
        this.initialized = true;
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        removeBadge();
        this.itemData = null;
        this.activeIndicatorProgress = 0.0f;
        this.initialized = false;
    }

    private View getIconOrContainer() {
        FrameLayout frameLayout = this.iconContainer;
        return frameLayout != null ? frameLayout : this.icon;
    }

    public void setItemPosition(int position) {
        this.itemPosition = position;
    }

    public int getItemPosition() {
        return this.itemPosition;
    }

    public void setShifting(boolean shifting) {
        if (this.isShifting != shifting) {
            this.isShifting = shifting;
            refreshChecked();
        }
    }

    public void setLabelVisibilityMode(int mode) {
        if (this.labelVisibilityMode != mode) {
            this.labelVisibilityMode = mode;
            updateActiveIndicatorTransform();
            updateActiveIndicatorLayoutParams(getWidth());
            refreshChecked();
        }
    }

    public MenuItemImpl getItemData() {
        return this.itemData;
    }

    public void setTitle(CharSequence title) {
        CharSequence tooltipText;
        this.smallLabel.setText(title);
        this.largeLabel.setText(title);
        MenuItemImpl menuItemImpl = this.itemData;
        if (menuItemImpl == null || TextUtils.isEmpty(menuItemImpl.getContentDescription())) {
            setContentDescription(title);
        }
        MenuItemImpl menuItemImpl2 = this.itemData;
        if (menuItemImpl2 == null || TextUtils.isEmpty(menuItemImpl2.getTooltipText())) {
            tooltipText = title;
        } else {
            tooltipText = this.itemData.getTooltipText();
        }
        if (Build.VERSION.SDK_INT < 21 || Build.VERSION.SDK_INT > 23) {
            TooltipCompat.setTooltipText(this, tooltipText);
        }
    }

    public void setCheckable(boolean checkable) {
        refreshDrawableState();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        final int width = w;
        post(new Runnable() {
            public void run() {
                NavigationBarItemView.this.updateActiveIndicatorLayoutParams(width);
            }
        });
    }

    private void updateActiveIndicatorTransform() {
        if (isActiveIndicatorResizeableAndUnlabeled()) {
            this.activeIndicatorTransform = ACTIVE_INDICATOR_UNLABELED_TRANSFORM;
        } else {
            this.activeIndicatorTransform = ACTIVE_INDICATOR_LABELED_TRANSFORM;
        }
    }

    /* access modifiers changed from: private */
    public void setActiveIndicatorProgress(float progress, float target) {
        View view = this.activeIndicatorView;
        if (view != null) {
            this.activeIndicatorTransform.updateForProgress(progress, target, view);
        }
        this.activeIndicatorProgress = progress;
    }

    private void maybeAnimateActiveIndicatorToProgress(final float newProgress) {
        if (!this.activeIndicatorEnabled || !this.initialized || !ViewCompat.isAttachedToWindow(this)) {
            setActiveIndicatorProgress(newProgress, newProgress);
            return;
        }
        ValueAnimator valueAnimator = this.activeIndicatorAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.activeIndicatorAnimator = null;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.activeIndicatorProgress, newProgress});
        this.activeIndicatorAnimator = ofFloat;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                NavigationBarItemView.this.setActiveIndicatorProgress(((Float) animation.getAnimatedValue()).floatValue(), newProgress);
            }
        });
        this.activeIndicatorAnimator.setInterpolator(MotionUtils.resolveThemeInterpolator(getContext(), C2436R.attr.motionEasingStandard, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        this.activeIndicatorAnimator.setDuration((long) MotionUtils.resolveThemeDuration(getContext(), C2436R.attr.motionDurationLong1, getResources().getInteger(C2436R.integer.material_motion_duration_long_1)));
        this.activeIndicatorAnimator.start();
    }

    private void refreshChecked() {
        MenuItemImpl menuItemImpl = this.itemData;
        if (menuItemImpl != null) {
            setChecked(menuItemImpl.isChecked());
        }
    }

    public void setChecked(boolean checked) {
        TextView textView = this.largeLabel;
        textView.setPivotX((float) (textView.getWidth() / 2));
        TextView textView2 = this.largeLabel;
        textView2.setPivotY((float) textView2.getBaseline());
        TextView textView3 = this.smallLabel;
        textView3.setPivotX((float) (textView3.getWidth() / 2));
        TextView textView4 = this.smallLabel;
        textView4.setPivotY((float) textView4.getBaseline());
        maybeAnimateActiveIndicatorToProgress(checked ? 1.0f : 0.0f);
        switch (this.labelVisibilityMode) {
            case -1:
                if (!this.isShifting) {
                    updateViewPaddingBottom(this.labelGroup, this.itemPaddingBottom);
                    if (!checked) {
                        setViewTopMarginAndGravity(getIconOrContainer(), this.itemPaddingTop, 49);
                        TextView textView5 = this.largeLabel;
                        float f = this.scaleDownFactor;
                        setViewScaleValues(textView5, f, f, 4);
                        setViewScaleValues(this.smallLabel, 1.0f, 1.0f, 0);
                        break;
                    } else {
                        setViewTopMarginAndGravity(getIconOrContainer(), (int) (((float) this.itemPaddingTop) + this.shiftAmount), 49);
                        setViewScaleValues(this.largeLabel, 1.0f, 1.0f, 0);
                        TextView textView6 = this.smallLabel;
                        float f2 = this.scaleUpFactor;
                        setViewScaleValues(textView6, f2, f2, 4);
                        break;
                    }
                } else {
                    if (checked) {
                        setViewTopMarginAndGravity(getIconOrContainer(), this.itemPaddingTop, 49);
                        updateViewPaddingBottom(this.labelGroup, this.itemPaddingBottom);
                        this.largeLabel.setVisibility(0);
                    } else {
                        setViewTopMarginAndGravity(getIconOrContainer(), this.itemPaddingTop, 17);
                        updateViewPaddingBottom(this.labelGroup, 0);
                        this.largeLabel.setVisibility(4);
                    }
                    this.smallLabel.setVisibility(4);
                    break;
                }
            case 0:
                if (checked) {
                    setViewTopMarginAndGravity(getIconOrContainer(), this.itemPaddingTop, 49);
                    updateViewPaddingBottom(this.labelGroup, this.itemPaddingBottom);
                    this.largeLabel.setVisibility(0);
                } else {
                    setViewTopMarginAndGravity(getIconOrContainer(), this.itemPaddingTop, 17);
                    updateViewPaddingBottom(this.labelGroup, 0);
                    this.largeLabel.setVisibility(4);
                }
                this.smallLabel.setVisibility(4);
                break;
            case 1:
                updateViewPaddingBottom(this.labelGroup, this.itemPaddingBottom);
                if (!checked) {
                    setViewTopMarginAndGravity(getIconOrContainer(), this.itemPaddingTop, 49);
                    TextView textView7 = this.largeLabel;
                    float f3 = this.scaleDownFactor;
                    setViewScaleValues(textView7, f3, f3, 4);
                    setViewScaleValues(this.smallLabel, 1.0f, 1.0f, 0);
                    break;
                } else {
                    setViewTopMarginAndGravity(getIconOrContainer(), (int) (((float) this.itemPaddingTop) + this.shiftAmount), 49);
                    setViewScaleValues(this.largeLabel, 1.0f, 1.0f, 0);
                    TextView textView8 = this.smallLabel;
                    float f4 = this.scaleUpFactor;
                    setViewScaleValues(textView8, f4, f4, 4);
                    break;
                }
            case 2:
                setViewTopMarginAndGravity(getIconOrContainer(), this.itemPaddingTop, 17);
                this.largeLabel.setVisibility(8);
                this.smallLabel.setVisibility(8);
                break;
        }
        refreshDrawableState();
        setSelected(checked);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        BadgeDrawable badgeDrawable2 = this.badgeDrawable;
        if (badgeDrawable2 != null && badgeDrawable2.isVisible()) {
            CharSequence customContentDescription = this.itemData.getTitle();
            if (!TextUtils.isEmpty(this.itemData.getContentDescription())) {
                customContentDescription = this.itemData.getContentDescription();
            }
            info.setContentDescription(customContentDescription + ", " + this.badgeDrawable.getContentDescription());
        }
        AccessibilityNodeInfoCompat infoCompat = AccessibilityNodeInfoCompat.wrap(info);
        infoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, getItemVisiblePosition(), 1, false, isSelected()));
        if (isSelected()) {
            infoCompat.setClickable(false);
            infoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
        }
        infoCompat.setRoleDescription(getResources().getString(C2436R.string.item_view_role_description));
    }

    private int getItemVisiblePosition() {
        ViewGroup parent = (ViewGroup) getParent();
        int index = parent.indexOfChild(this);
        int visiblePosition = 0;
        for (int i = 0; i < index; i++) {
            View child = parent.getChildAt(i);
            if ((child instanceof NavigationBarItemView) && child.getVisibility() == 0) {
                visiblePosition++;
            }
        }
        return visiblePosition;
    }

    private static void setViewTopMarginAndGravity(View view, int topMargin, int gravity) {
        FrameLayout.LayoutParams viewParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        viewParams.topMargin = topMargin;
        viewParams.bottomMargin = topMargin;
        viewParams.gravity = gravity;
        view.setLayoutParams(viewParams);
    }

    private static void setViewScaleValues(View view, float scaleX, float scaleY, int visibility) {
        view.setScaleX(scaleX);
        view.setScaleY(scaleY);
        view.setVisibility(visibility);
    }

    private static void updateViewPaddingBottom(View view, int paddingBottom) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), paddingBottom);
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.smallLabel.setEnabled(enabled);
        this.largeLabel.setEnabled(enabled);
        this.icon.setEnabled(enabled);
        if (enabled) {
            ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(getContext(), 1002));
        } else {
            ViewCompat.setPointerIcon(this, (PointerIconCompat) null);
        }
    }

    public int[] onCreateDrawableState(int extraSpace) {
        int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        MenuItemImpl menuItemImpl = this.itemData;
        if (menuItemImpl != null && menuItemImpl.isCheckable() && this.itemData.isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    public void setShortcut(boolean showShortcut, char shortcutKey) {
    }

    public void setIcon(Drawable iconDrawable) {
        Drawable drawable;
        if (iconDrawable != this.originalIconDrawable) {
            this.originalIconDrawable = iconDrawable;
            if (iconDrawable != null) {
                Drawable.ConstantState state = iconDrawable.getConstantState();
                if (state == null) {
                    drawable = iconDrawable;
                } else {
                    drawable = state.newDrawable();
                }
                iconDrawable = DrawableCompat.wrap(drawable).mutate();
                this.wrappedIconDrawable = iconDrawable;
                ColorStateList colorStateList = this.iconTint;
                if (colorStateList != null) {
                    DrawableCompat.setTintList(iconDrawable, colorStateList);
                }
            }
            this.icon.setImageDrawable(iconDrawable);
        }
    }

    public boolean prefersCondensedTitle() {
        return false;
    }

    public boolean showsIcon() {
        return true;
    }

    public void setIconTintList(ColorStateList tint) {
        Drawable drawable;
        this.iconTint = tint;
        if (this.itemData != null && (drawable = this.wrappedIconDrawable) != null) {
            DrawableCompat.setTintList(drawable, tint);
            this.wrappedIconDrawable.invalidateSelf();
        }
    }

    public void setIconSize(int iconSize) {
        FrameLayout.LayoutParams iconParams = (FrameLayout.LayoutParams) this.icon.getLayoutParams();
        iconParams.width = iconSize;
        iconParams.height = iconSize;
        this.icon.setLayoutParams(iconParams);
    }

    public void setTextAppearanceInactive(int inactiveTextAppearance) {
        TextViewCompat.setTextAppearance(this.smallLabel, inactiveTextAppearance);
        calculateTextScaleFactors(this.smallLabel.getTextSize(), this.largeLabel.getTextSize());
    }

    public void setTextAppearanceActive(int activeTextAppearance) {
        TextViewCompat.setTextAppearance(this.largeLabel, activeTextAppearance);
        calculateTextScaleFactors(this.smallLabel.getTextSize(), this.largeLabel.getTextSize());
    }

    public void setTextColor(ColorStateList color) {
        if (color != null) {
            this.smallLabel.setTextColor(color);
            this.largeLabel.setTextColor(color);
        }
    }

    private void calculateTextScaleFactors(float smallLabelSize, float largeLabelSize) {
        this.shiftAmount = smallLabelSize - largeLabelSize;
        this.scaleUpFactor = (largeLabelSize * 1.0f) / smallLabelSize;
        this.scaleDownFactor = (1.0f * smallLabelSize) / largeLabelSize;
    }

    public void setItemBackground(int background) {
        Drawable backgroundDrawable;
        if (background == 0) {
            backgroundDrawable = null;
        } else {
            backgroundDrawable = ContextCompat.getDrawable(getContext(), background);
        }
        setItemBackground(backgroundDrawable);
    }

    public void setItemBackground(Drawable background) {
        if (!(background == null || background.getConstantState() == null)) {
            background = background.getConstantState().newDrawable().mutate();
        }
        ViewCompat.setBackground(this, background);
    }

    public void setItemPaddingTop(int paddingTop) {
        if (this.itemPaddingTop != paddingTop) {
            this.itemPaddingTop = paddingTop;
            refreshChecked();
        }
    }

    public void setItemPaddingBottom(int paddingBottom) {
        if (this.itemPaddingBottom != paddingBottom) {
            this.itemPaddingBottom = paddingBottom;
            refreshChecked();
        }
    }

    public void setActiveIndicatorEnabled(boolean enabled) {
        this.activeIndicatorEnabled = enabled;
        View view = this.activeIndicatorView;
        if (view != null) {
            view.setVisibility(enabled ? 0 : 8);
            requestLayout();
        }
    }

    public void setActiveIndicatorWidth(int width) {
        this.activeIndicatorDesiredWidth = width;
        updateActiveIndicatorLayoutParams(getWidth());
    }

    /* access modifiers changed from: private */
    public void updateActiveIndicatorLayoutParams(int availableWidth) {
        if (this.activeIndicatorView != null) {
            int newWidth = Math.min(this.activeIndicatorDesiredWidth, availableWidth - (this.activeIndicatorMarginHorizontal * 2));
            FrameLayout.LayoutParams indicatorParams = (FrameLayout.LayoutParams) this.activeIndicatorView.getLayoutParams();
            indicatorParams.height = isActiveIndicatorResizeableAndUnlabeled() ? newWidth : this.activeIndicatorDesiredHeight;
            indicatorParams.width = newWidth;
            this.activeIndicatorView.setLayoutParams(indicatorParams);
        }
    }

    private boolean isActiveIndicatorResizeableAndUnlabeled() {
        return this.activeIndicatorResizeable && this.labelVisibilityMode == 2;
    }

    public void setActiveIndicatorHeight(int height) {
        this.activeIndicatorDesiredHeight = height;
        updateActiveIndicatorLayoutParams(getWidth());
    }

    public void setActiveIndicatorMarginHorizontal(int marginHorizontal) {
        this.activeIndicatorMarginHorizontal = marginHorizontal;
        updateActiveIndicatorLayoutParams(getWidth());
    }

    public Drawable getActiveIndicatorDrawable() {
        View view = this.activeIndicatorView;
        if (view == null) {
            return null;
        }
        return view.getBackground();
    }

    public void setActiveIndicatorDrawable(Drawable activeIndicatorDrawable) {
        View view = this.activeIndicatorView;
        if (view != null) {
            view.setBackgroundDrawable(activeIndicatorDrawable);
        }
    }

    public void setActiveIndicatorResizeable(boolean resizeable) {
        this.activeIndicatorResizeable = resizeable;
    }

    /* access modifiers changed from: package-private */
    public void setBadge(BadgeDrawable badgeDrawable2) {
        this.badgeDrawable = badgeDrawable2;
        ImageView imageView = this.icon;
        if (imageView != null) {
            tryAttachBadgeToAnchor(imageView);
        }
    }

    public BadgeDrawable getBadge() {
        return this.badgeDrawable;
    }

    /* access modifiers changed from: package-private */
    public void removeBadge() {
        tryRemoveBadgeFromAnchor(this.icon);
    }

    private boolean hasBadge() {
        return this.badgeDrawable != null;
    }

    /* access modifiers changed from: private */
    public void tryUpdateBadgeBounds(View anchorView) {
        if (hasBadge()) {
            BadgeUtils.setBadgeDrawableBounds(this.badgeDrawable, anchorView, getCustomParentForBadge(anchorView));
        }
    }

    private void tryAttachBadgeToAnchor(View anchorView) {
        if (hasBadge() && anchorView != null) {
            setClipChildren(false);
            setClipToPadding(false);
            BadgeUtils.attachBadgeDrawable(this.badgeDrawable, anchorView, getCustomParentForBadge(anchorView));
        }
    }

    private void tryRemoveBadgeFromAnchor(View anchorView) {
        if (hasBadge()) {
            if (anchorView != null) {
                setClipChildren(true);
                setClipToPadding(true);
                BadgeUtils.detachBadgeDrawable(this.badgeDrawable, anchorView);
            }
            this.badgeDrawable = null;
        }
    }

    private FrameLayout getCustomParentForBadge(View anchorView) {
        if (anchorView != this.icon || !BadgeUtils.USE_COMPAT_PARENT) {
            return null;
        }
        return (FrameLayout) this.icon.getParent();
    }

    private int getSuggestedIconWidth() {
        int badgeWidth;
        BadgeDrawable badgeDrawable2 = this.badgeDrawable;
        if (badgeDrawable2 == null) {
            badgeWidth = 0;
        } else {
            badgeWidth = badgeDrawable2.getMinimumWidth() - this.badgeDrawable.getHorizontalOffset();
        }
        FrameLayout.LayoutParams iconContainerParams = (FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams();
        return Math.max(badgeWidth, iconContainerParams.leftMargin) + this.icon.getMeasuredWidth() + Math.max(badgeWidth, iconContainerParams.rightMargin);
    }

    private int getSuggestedIconHeight() {
        int badgeHeight = 0;
        BadgeDrawable badgeDrawable2 = this.badgeDrawable;
        if (badgeDrawable2 != null) {
            badgeHeight = badgeDrawable2.getMinimumHeight() / 2;
        }
        return Math.max(badgeHeight, ((FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams()).topMargin) + this.icon.getMeasuredWidth() + badgeHeight;
    }

    /* access modifiers changed from: protected */
    public int getItemBackgroundResId() {
        return C2436R.C2438drawable.mtrl_navigation_bar_item_background;
    }

    /* access modifiers changed from: protected */
    public int getItemDefaultMarginResId() {
        return C2436R.dimen.mtrl_navigation_bar_item_default_margin;
    }

    private static class ActiveIndicatorTransform {
        private static final float ALPHA_FRACTION = 0.2f;
        private static final float SCALE_X_HIDDEN = 0.4f;
        private static final float SCALE_X_SHOWN = 1.0f;

        private ActiveIndicatorTransform() {
        }

        /* access modifiers changed from: protected */
        public float calculateAlpha(float progress, float targetValue) {
            return AnimationUtils.lerp(0.0f, 1.0f, targetValue == 0.0f ? 0.8f : 0.0f, targetValue == 0.0f ? 1.0f : 0.2f, progress);
        }

        /* access modifiers changed from: protected */
        public float calculateScaleX(float progress, float targetValue) {
            return AnimationUtils.lerp((float) SCALE_X_HIDDEN, 1.0f, progress);
        }

        /* access modifiers changed from: protected */
        public float calculateScaleY(float progress, float targetValue) {
            return 1.0f;
        }

        public void updateForProgress(float progress, float targetValue, View indicator) {
            indicator.setScaleX(calculateScaleX(progress, targetValue));
            indicator.setScaleY(calculateScaleY(progress, targetValue));
            indicator.setAlpha(calculateAlpha(progress, targetValue));
        }
    }

    private static class ActiveIndicatorUnlabeledTransform extends ActiveIndicatorTransform {
        private ActiveIndicatorUnlabeledTransform() {
            super();
        }

        /* access modifiers changed from: protected */
        public float calculateScaleY(float progress, float targetValue) {
            return calculateScaleX(progress, targetValue);
        }
    }
}
