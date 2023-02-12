package com.google.android.material.shape;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import androidx.core.graphics.drawable.TintAwareDrawable;
import androidx.core.util.ObjectsCompat;
import com.google.android.material.C2436R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.shadow.ShadowRenderer;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.shape.ShapePath;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.BitSet;

public class MaterialShapeDrawable extends Drawable implements TintAwareDrawable, Shapeable {
    public static final int SHADOW_COMPAT_MODE_ALWAYS = 2;
    public static final int SHADOW_COMPAT_MODE_DEFAULT = 0;
    public static final int SHADOW_COMPAT_MODE_NEVER = 1;
    private static final float SHADOW_OFFSET_MULTIPLIER = 0.25f;
    private static final float SHADOW_RADIUS_MULTIPLIER = 0.75f;
    private static final String TAG = MaterialShapeDrawable.class.getSimpleName();
    private static final Paint clearPaint;
    /* access modifiers changed from: private */
    public final BitSet containsIncompatibleShadowOp;
    /* access modifiers changed from: private */
    public final ShapePath.ShadowCompatOperation[] cornerShadowOperation;
    private MaterialShapeDrawableState drawableState;
    /* access modifiers changed from: private */
    public final ShapePath.ShadowCompatOperation[] edgeShadowOperation;
    private final Paint fillPaint;
    private final RectF insetRectF;
    private final Matrix matrix;
    private final Path path;
    private final RectF pathBounds;
    /* access modifiers changed from: private */
    public boolean pathDirty;
    private final Path pathInsetByStroke;
    private final ShapeAppearancePathProvider pathProvider;
    private final ShapeAppearancePathProvider.PathListener pathShadowListener;
    private final RectF rectF;
    private int resolvedTintColor;
    private final Region scratchRegion;
    private boolean shadowBitmapDrawingEnable;
    private final ShadowRenderer shadowRenderer;
    private final Paint strokePaint;
    private ShapeAppearanceModel strokeShapeAppearance;
    private PorterDuffColorFilter strokeTintFilter;
    private PorterDuffColorFilter tintFilter;
    private final Region transparentRegion;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CompatibilityShadowMode {
    }

    static {
        Paint paint = new Paint(1);
        clearPaint = paint;
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    public static MaterialShapeDrawable createWithElevationOverlay(Context context) {
        return createWithElevationOverlay(context, 0.0f);
    }

    public static MaterialShapeDrawable createWithElevationOverlay(Context context, float elevation) {
        int colorSurface = MaterialColors.getColor(context, C2436R.attr.colorSurface, MaterialShapeDrawable.class.getSimpleName());
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        materialShapeDrawable.initializeElevationOverlay(context);
        materialShapeDrawable.setFillColor(ColorStateList.valueOf(colorSurface));
        materialShapeDrawable.setElevation(elevation);
        return materialShapeDrawable;
    }

    public MaterialShapeDrawable() {
        this(new ShapeAppearanceModel());
    }

    public MaterialShapeDrawable(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(ShapeAppearanceModel.builder(context, attrs, defStyleAttr, defStyleRes).build());
    }

    @Deprecated
    public MaterialShapeDrawable(ShapePathModel shapePathModel) {
        this((ShapeAppearanceModel) shapePathModel);
    }

    public MaterialShapeDrawable(ShapeAppearanceModel shapeAppearanceModel) {
        this(new MaterialShapeDrawableState(shapeAppearanceModel, (ElevationOverlayProvider) null));
    }

    private MaterialShapeDrawable(MaterialShapeDrawableState drawableState2) {
        this.cornerShadowOperation = new ShapePath.ShadowCompatOperation[4];
        this.edgeShadowOperation = new ShapePath.ShadowCompatOperation[4];
        this.containsIncompatibleShadowOp = new BitSet(8);
        this.matrix = new Matrix();
        this.path = new Path();
        this.pathInsetByStroke = new Path();
        this.rectF = new RectF();
        this.insetRectF = new RectF();
        this.transparentRegion = new Region();
        this.scratchRegion = new Region();
        Paint paint = new Paint(1);
        this.fillPaint = paint;
        Paint paint2 = new Paint(1);
        this.strokePaint = paint2;
        this.shadowRenderer = new ShadowRenderer();
        this.pathProvider = Looper.getMainLooper().getThread() == Thread.currentThread() ? ShapeAppearancePathProvider.getInstance() : new ShapeAppearancePathProvider();
        this.pathBounds = new RectF();
        this.shadowBitmapDrawingEnable = true;
        this.drawableState = drawableState2;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        updateTintFilter();
        updateColorsForState(getState());
        this.pathShadowListener = new ShapeAppearancePathProvider.PathListener() {
            public void onCornerPathCreated(ShapePath cornerPath, Matrix transform, int count) {
                MaterialShapeDrawable.this.containsIncompatibleShadowOp.set(count, cornerPath.containsIncompatibleShadowOp());
                MaterialShapeDrawable.this.cornerShadowOperation[count] = cornerPath.createShadowCompatOperation(transform);
            }

            public void onEdgePathCreated(ShapePath edgePath, Matrix transform, int count) {
                MaterialShapeDrawable.this.containsIncompatibleShadowOp.set(count + 4, edgePath.containsIncompatibleShadowOp());
                MaterialShapeDrawable.this.edgeShadowOperation[count] = edgePath.createShadowCompatOperation(transform);
            }
        };
    }

    public Drawable.ConstantState getConstantState() {
        return this.drawableState;
    }

    public Drawable mutate() {
        this.drawableState = new MaterialShapeDrawableState(this.drawableState);
        return this;
    }

    private static int modulateAlpha(int paintAlpha, int alpha) {
        return (paintAlpha * ((alpha >>> 7) + alpha)) >>> 8;
    }

    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        this.drawableState.shapeAppearanceModel = shapeAppearanceModel;
        invalidateSelf();
    }

    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.drawableState.shapeAppearanceModel;
    }

    @Deprecated
    public void setShapedViewModel(ShapePathModel shapedViewModel) {
        setShapeAppearanceModel(shapedViewModel);
    }

    @Deprecated
    public ShapePathModel getShapedViewModel() {
        ShapeAppearanceModel shapeAppearance = getShapeAppearanceModel();
        if (shapeAppearance instanceof ShapePathModel) {
            return (ShapePathModel) shapeAppearance;
        }
        return null;
    }

    public void setFillColor(ColorStateList fillColor) {
        if (this.drawableState.fillColor != fillColor) {
            this.drawableState.fillColor = fillColor;
            onStateChange(getState());
        }
    }

    public ColorStateList getFillColor() {
        return this.drawableState.fillColor;
    }

    public void setStrokeColor(ColorStateList strokeColor) {
        if (this.drawableState.strokeColor != strokeColor) {
            this.drawableState.strokeColor = strokeColor;
            onStateChange(getState());
        }
    }

    public ColorStateList getStrokeColor() {
        return this.drawableState.strokeColor;
    }

    public void setTintMode(PorterDuff.Mode tintMode) {
        if (this.drawableState.tintMode != tintMode) {
            this.drawableState.tintMode = tintMode;
            updateTintFilter();
            invalidateSelfIgnoreShape();
        }
    }

    public void setTintList(ColorStateList tintList) {
        this.drawableState.tintList = tintList;
        updateTintFilter();
        invalidateSelfIgnoreShape();
    }

    public ColorStateList getTintList() {
        return this.drawableState.tintList;
    }

    public ColorStateList getStrokeTintList() {
        return this.drawableState.strokeTintList;
    }

    public void setTint(int tintColor) {
        setTintList(ColorStateList.valueOf(tintColor));
    }

    public void setStrokeTint(ColorStateList tintList) {
        this.drawableState.strokeTintList = tintList;
        updateTintFilter();
        invalidateSelfIgnoreShape();
    }

    public void setStrokeTint(int tintColor) {
        setStrokeTint(ColorStateList.valueOf(tintColor));
    }

    public void setStroke(float strokeWidth, int strokeColor) {
        setStrokeWidth(strokeWidth);
        setStrokeColor(ColorStateList.valueOf(strokeColor));
    }

    public void setStroke(float strokeWidth, ColorStateList strokeColor) {
        setStrokeWidth(strokeWidth);
        setStrokeColor(strokeColor);
    }

    public float getStrokeWidth() {
        return this.drawableState.strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.drawableState.strokeWidth = strokeWidth;
        invalidateSelf();
    }

    public int getResolvedTintColor() {
        return this.resolvedTintColor;
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(int alpha) {
        if (this.drawableState.alpha != alpha) {
            this.drawableState.alpha = alpha;
            invalidateSelfIgnoreShape();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.drawableState.colorFilter = colorFilter;
        invalidateSelfIgnoreShape();
    }

    public Region getTransparentRegion() {
        this.transparentRegion.set(getBounds());
        calculatePath(getBoundsAsRectF(), this.path);
        this.scratchRegion.setPath(this.path, this.transparentRegion);
        this.transparentRegion.op(this.scratchRegion, Region.Op.DIFFERENCE);
        return this.transparentRegion;
    }

    /* access modifiers changed from: protected */
    public RectF getBoundsAsRectF() {
        this.rectF.set(getBounds());
        return this.rectF;
    }

    public void setCornerSize(float cornerSize) {
        setShapeAppearanceModel(this.drawableState.shapeAppearanceModel.withCornerSize(cornerSize));
    }

    public void setCornerSize(CornerSize cornerSize) {
        setShapeAppearanceModel(this.drawableState.shapeAppearanceModel.withCornerSize(cornerSize));
    }

    public boolean isPointInTransparentRegion(int x, int y) {
        return getTransparentRegion().contains(x, y);
    }

    public int getShadowCompatibilityMode() {
        return this.drawableState.shadowCompatMode;
    }

    public boolean getPadding(Rect padding) {
        if (this.drawableState.padding == null) {
            return super.getPadding(padding);
        }
        padding.set(this.drawableState.padding);
        return true;
    }

    public void setPadding(int left, int top, int right, int bottom) {
        if (this.drawableState.padding == null) {
            this.drawableState.padding = new Rect();
        }
        this.drawableState.padding.set(left, top, right, bottom);
        invalidateSelf();
    }

    public void setShadowCompatibilityMode(int mode) {
        if (this.drawableState.shadowCompatMode != mode) {
            this.drawableState.shadowCompatMode = mode;
            invalidateSelfIgnoreShape();
        }
    }

    @Deprecated
    public boolean isShadowEnabled() {
        return this.drawableState.shadowCompatMode == 0 || this.drawableState.shadowCompatMode == 2;
    }

    @Deprecated
    public void setShadowEnabled(boolean shadowEnabled) {
        setShadowCompatibilityMode(shadowEnabled ^ true ? 1 : 0);
    }

    public boolean isElevationOverlayEnabled() {
        return this.drawableState.elevationOverlayProvider != null && this.drawableState.elevationOverlayProvider.isThemeElevationOverlayEnabled();
    }

    public boolean isElevationOverlayInitialized() {
        return this.drawableState.elevationOverlayProvider != null;
    }

    public void initializeElevationOverlay(Context context) {
        this.drawableState.elevationOverlayProvider = new ElevationOverlayProvider(context);
        updateZ();
    }

    /* access modifiers changed from: protected */
    public int compositeElevationOverlayIfNeeded(int backgroundColor) {
        return this.drawableState.elevationOverlayProvider != null ? this.drawableState.elevationOverlayProvider.compositeOverlayIfNeeded(backgroundColor, getZ() + getParentAbsoluteElevation()) : backgroundColor;
    }

    public float getInterpolation() {
        return this.drawableState.interpolation;
    }

    public void setInterpolation(float interpolation) {
        if (this.drawableState.interpolation != interpolation) {
            this.drawableState.interpolation = interpolation;
            this.pathDirty = true;
            invalidateSelf();
        }
    }

    public float getParentAbsoluteElevation() {
        return this.drawableState.parentAbsoluteElevation;
    }

    public void setParentAbsoluteElevation(float parentAbsoluteElevation) {
        if (this.drawableState.parentAbsoluteElevation != parentAbsoluteElevation) {
            this.drawableState.parentAbsoluteElevation = parentAbsoluteElevation;
            updateZ();
        }
    }

    public float getElevation() {
        return this.drawableState.elevation;
    }

    public void setElevation(float elevation) {
        if (this.drawableState.elevation != elevation) {
            this.drawableState.elevation = elevation;
            updateZ();
        }
    }

    public float getTranslationZ() {
        return this.drawableState.translationZ;
    }

    public void setTranslationZ(float translationZ) {
        if (this.drawableState.translationZ != translationZ) {
            this.drawableState.translationZ = translationZ;
            updateZ();
        }
    }

    public float getZ() {
        return getElevation() + getTranslationZ();
    }

    public void setZ(float z) {
        setTranslationZ(z - getElevation());
    }

    private void updateZ() {
        float z = getZ();
        this.drawableState.shadowCompatRadius = (int) Math.ceil((double) (0.75f * z));
        this.drawableState.shadowCompatOffset = (int) Math.ceil((double) (SHADOW_OFFSET_MULTIPLIER * z));
        updateTintFilter();
        invalidateSelfIgnoreShape();
    }

    @Deprecated
    public int getShadowElevation() {
        return (int) getElevation();
    }

    @Deprecated
    public void setShadowElevation(int shadowElevation) {
        setElevation((float) shadowElevation);
    }

    public int getShadowVerticalOffset() {
        return this.drawableState.shadowCompatOffset;
    }

    public void setShadowBitmapDrawingEnable(boolean enable) {
        this.shadowBitmapDrawingEnable = enable;
    }

    public void setEdgeIntersectionCheckEnable(boolean enable) {
        this.pathProvider.setEdgeIntersectionCheckEnable(enable);
    }

    public void setShadowVerticalOffset(int shadowOffset) {
        if (this.drawableState.shadowCompatOffset != shadowOffset) {
            this.drawableState.shadowCompatOffset = shadowOffset;
            invalidateSelfIgnoreShape();
        }
    }

    public int getShadowCompatRotation() {
        return this.drawableState.shadowCompatRotation;
    }

    public void setShadowCompatRotation(int shadowRotation) {
        if (this.drawableState.shadowCompatRotation != shadowRotation) {
            this.drawableState.shadowCompatRotation = shadowRotation;
            invalidateSelfIgnoreShape();
        }
    }

    public int getShadowRadius() {
        return this.drawableState.shadowCompatRadius;
    }

    @Deprecated
    public void setShadowRadius(int shadowRadius) {
        this.drawableState.shadowCompatRadius = shadowRadius;
    }

    public boolean requiresCompatShadow() {
        return Build.VERSION.SDK_INT < 21 || (!isRoundRect() && !this.path.isConvex() && Build.VERSION.SDK_INT < 29);
    }

    public float getScale() {
        return this.drawableState.scale;
    }

    public void setScale(float scale) {
        if (this.drawableState.scale != scale) {
            this.drawableState.scale = scale;
            invalidateSelf();
        }
    }

    public void invalidateSelf() {
        this.pathDirty = true;
        super.invalidateSelf();
    }

    private void invalidateSelfIgnoreShape() {
        super.invalidateSelf();
    }

    public void setUseTintColorForShadow(boolean useTintColorForShadow) {
        if (this.drawableState.useTintColorForShadow != useTintColorForShadow) {
            this.drawableState.useTintColorForShadow = useTintColorForShadow;
            invalidateSelf();
        }
    }

    public void setShadowColor(int shadowColor) {
        this.shadowRenderer.setShadowColor(shadowColor);
        this.drawableState.useTintColorForShadow = false;
        invalidateSelfIgnoreShape();
    }

    public Paint.Style getPaintStyle() {
        return this.drawableState.paintStyle;
    }

    public void setPaintStyle(Paint.Style paintStyle) {
        this.drawableState.paintStyle = paintStyle;
        invalidateSelfIgnoreShape();
    }

    private boolean hasCompatShadow() {
        if (this.drawableState.shadowCompatMode == 1 || this.drawableState.shadowCompatRadius <= 0 || (this.drawableState.shadowCompatMode != 2 && !requiresCompatShadow())) {
            return false;
        }
        return true;
    }

    private boolean hasFill() {
        return this.drawableState.paintStyle == Paint.Style.FILL_AND_STROKE || this.drawableState.paintStyle == Paint.Style.FILL;
    }

    private boolean hasStroke() {
        return (this.drawableState.paintStyle == Paint.Style.FILL_AND_STROKE || this.drawableState.paintStyle == Paint.Style.STROKE) && this.strokePaint.getStrokeWidth() > 0.0f;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect bounds) {
        this.pathDirty = true;
        super.onBoundsChange(bounds);
    }

    public void draw(Canvas canvas) {
        this.fillPaint.setColorFilter(this.tintFilter);
        int prevAlpha = this.fillPaint.getAlpha();
        this.fillPaint.setAlpha(modulateAlpha(prevAlpha, this.drawableState.alpha));
        this.strokePaint.setColorFilter(this.strokeTintFilter);
        this.strokePaint.setStrokeWidth(this.drawableState.strokeWidth);
        int prevStrokeAlpha = this.strokePaint.getAlpha();
        this.strokePaint.setAlpha(modulateAlpha(prevStrokeAlpha, this.drawableState.alpha));
        if (this.pathDirty) {
            calculateStrokePath();
            calculatePath(getBoundsAsRectF(), this.path);
            this.pathDirty = false;
        }
        maybeDrawCompatShadow(canvas);
        if (hasFill()) {
            drawFillShape(canvas);
        }
        if (hasStroke()) {
            drawStrokeShape(canvas);
        }
        this.fillPaint.setAlpha(prevAlpha);
        this.strokePaint.setAlpha(prevStrokeAlpha);
    }

    private void maybeDrawCompatShadow(Canvas canvas) {
        if (hasCompatShadow()) {
            canvas.save();
            prepareCanvasForShadow(canvas);
            if (!this.shadowBitmapDrawingEnable) {
                drawCompatShadow(canvas);
                canvas.restore();
                return;
            }
            int pathExtraWidth = (int) (this.pathBounds.width() - ((float) getBounds().width()));
            int pathExtraHeight = (int) (this.pathBounds.height() - ((float) getBounds().height()));
            if (pathExtraWidth < 0 || pathExtraHeight < 0) {
                throw new IllegalStateException("Invalid shadow bounds. Check that the treatments result in a valid path.");
            }
            Bitmap shadowLayer = Bitmap.createBitmap(((int) this.pathBounds.width()) + (this.drawableState.shadowCompatRadius * 2) + pathExtraWidth, ((int) this.pathBounds.height()) + (this.drawableState.shadowCompatRadius * 2) + pathExtraHeight, Bitmap.Config.ARGB_8888);
            Canvas shadowCanvas = new Canvas(shadowLayer);
            float shadowLeft = (float) ((getBounds().left - this.drawableState.shadowCompatRadius) - pathExtraWidth);
            float shadowTop = (float) ((getBounds().top - this.drawableState.shadowCompatRadius) - pathExtraHeight);
            shadowCanvas.translate(-shadowLeft, -shadowTop);
            drawCompatShadow(shadowCanvas);
            canvas.drawBitmap(shadowLayer, shadowLeft, shadowTop, (Paint) null);
            shadowLayer.recycle();
            canvas.restore();
        }
    }

    /* access modifiers changed from: protected */
    public void drawShape(Canvas canvas, Paint paint, Path path2, RectF bounds) {
        drawShape(canvas, paint, path2, this.drawableState.shapeAppearanceModel, bounds);
    }

    private void drawShape(Canvas canvas, Paint paint, Path path2, ShapeAppearanceModel shapeAppearanceModel, RectF bounds) {
        if (shapeAppearanceModel.isRoundRect(bounds)) {
            float cornerSize = shapeAppearanceModel.getTopRightCornerSize().getCornerSize(bounds) * this.drawableState.interpolation;
            canvas.drawRoundRect(bounds, cornerSize, cornerSize, paint);
            return;
        }
        canvas.drawPath(path2, paint);
    }

    private void drawFillShape(Canvas canvas) {
        drawShape(canvas, this.fillPaint, this.path, this.drawableState.shapeAppearanceModel, getBoundsAsRectF());
    }

    /* access modifiers changed from: protected */
    public void drawStrokeShape(Canvas canvas) {
        drawShape(canvas, this.strokePaint, this.pathInsetByStroke, this.strokeShapeAppearance, getBoundsInsetByStroke());
    }

    private void prepareCanvasForShadow(Canvas canvas) {
        int shadowOffsetX = getShadowOffsetX();
        int shadowOffsetY = getShadowOffsetY();
        if (Build.VERSION.SDK_INT < 21 && this.shadowBitmapDrawingEnable) {
            Rect canvasClipBounds = canvas.getClipBounds();
            canvasClipBounds.inset(-this.drawableState.shadowCompatRadius, -this.drawableState.shadowCompatRadius);
            canvasClipBounds.offset(shadowOffsetX, shadowOffsetY);
            canvas.clipRect(canvasClipBounds, Region.Op.REPLACE);
        }
        canvas.translate((float) shadowOffsetX, (float) shadowOffsetY);
    }

    private void drawCompatShadow(Canvas canvas) {
        if (this.containsIncompatibleShadowOp.cardinality() > 0) {
            Log.w(TAG, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.drawableState.shadowCompatOffset != 0) {
            canvas.drawPath(this.path, this.shadowRenderer.getShadowPaint());
        }
        for (int index = 0; index < 4; index++) {
            this.cornerShadowOperation[index].draw(this.shadowRenderer, this.drawableState.shadowCompatRadius, canvas);
            this.edgeShadowOperation[index].draw(this.shadowRenderer, this.drawableState.shadowCompatRadius, canvas);
        }
        if (this.shadowBitmapDrawingEnable != 0) {
            int shadowOffsetX = getShadowOffsetX();
            int shadowOffsetY = getShadowOffsetY();
            canvas.translate((float) (-shadowOffsetX), (float) (-shadowOffsetY));
            canvas.drawPath(this.path, clearPaint);
            canvas.translate((float) shadowOffsetX, (float) shadowOffsetY);
        }
    }

    public int getShadowOffsetX() {
        return (int) (((double) this.drawableState.shadowCompatOffset) * Math.sin(Math.toRadians((double) this.drawableState.shadowCompatRotation)));
    }

    public int getShadowOffsetY() {
        return (int) (((double) this.drawableState.shadowCompatOffset) * Math.cos(Math.toRadians((double) this.drawableState.shadowCompatRotation)));
    }

    @Deprecated
    public void getPathForSize(int width, int height, Path path2) {
        calculatePathForSize(new RectF(0.0f, 0.0f, (float) width, (float) height), path2);
    }

    /* access modifiers changed from: protected */
    public final void calculatePathForSize(RectF bounds, Path path2) {
        this.pathProvider.calculatePath(this.drawableState.shapeAppearanceModel, this.drawableState.interpolation, bounds, this.pathShadowListener, path2);
    }

    private void calculateStrokePath() {
        final float strokeInsetLength = -getStrokeInsetLength();
        ShapeAppearanceModel withTransformedCornerSizes = getShapeAppearanceModel().withTransformedCornerSizes(new ShapeAppearanceModel.CornerSizeUnaryOperator() {
            public CornerSize apply(CornerSize cornerSize) {
                return cornerSize instanceof RelativeCornerSize ? cornerSize : new AdjustedCornerSize(strokeInsetLength, cornerSize);
            }
        });
        this.strokeShapeAppearance = withTransformedCornerSizes;
        this.pathProvider.calculatePath(withTransformedCornerSizes, this.drawableState.interpolation, getBoundsInsetByStroke(), this.pathInsetByStroke);
    }

    public void getOutline(Outline outline) {
        if (this.drawableState.shadowCompatMode != 2) {
            if (isRoundRect()) {
                outline.setRoundRect(getBounds(), getTopLeftCornerResolvedSize() * this.drawableState.interpolation);
                return;
            }
            calculatePath(getBoundsAsRectF(), this.path);
            if (this.path.isConvex() || Build.VERSION.SDK_INT >= 29) {
                try {
                    outline.setConvexPath(this.path);
                } catch (IllegalArgumentException e) {
                }
            }
        }
    }

    private void calculatePath(RectF bounds, Path path2) {
        calculatePathForSize(bounds, path2);
        if (this.drawableState.scale != 1.0f) {
            this.matrix.reset();
            this.matrix.setScale(this.drawableState.scale, this.drawableState.scale, bounds.width() / 2.0f, bounds.height() / 2.0f);
            path2.transform(this.matrix);
        }
        path2.computeBounds(this.pathBounds, true);
    }

    private boolean updateTintFilter() {
        PorterDuffColorFilter originalTintFilter = this.tintFilter;
        PorterDuffColorFilter originalStrokeTintFilter = this.strokeTintFilter;
        this.tintFilter = calculateTintFilter(this.drawableState.tintList, this.drawableState.tintMode, this.fillPaint, true);
        this.strokeTintFilter = calculateTintFilter(this.drawableState.strokeTintList, this.drawableState.tintMode, this.strokePaint, false);
        if (this.drawableState.useTintColorForShadow) {
            this.shadowRenderer.setShadowColor(this.drawableState.tintList.getColorForState(getState(), 0));
        }
        if (!ObjectsCompat.equals(originalTintFilter, this.tintFilter) || !ObjectsCompat.equals(originalStrokeTintFilter, this.strokeTintFilter)) {
            return true;
        }
        return false;
    }

    private PorterDuffColorFilter calculateTintFilter(ColorStateList tintList, PorterDuff.Mode tintMode, Paint paint, boolean requiresElevationOverlay) {
        if (tintList == null || tintMode == null) {
            return calculatePaintColorTintFilter(paint, requiresElevationOverlay);
        }
        return calculateTintColorTintFilter(tintList, tintMode, requiresElevationOverlay);
    }

    private PorterDuffColorFilter calculatePaintColorTintFilter(Paint paint, boolean requiresElevationOverlay) {
        if (!requiresElevationOverlay) {
            return null;
        }
        int paintColor = paint.getColor();
        int tintColor = compositeElevationOverlayIfNeeded(paintColor);
        this.resolvedTintColor = tintColor;
        if (tintColor != paintColor) {
            return new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN);
        }
        return null;
    }

    private PorterDuffColorFilter calculateTintColorTintFilter(ColorStateList tintList, PorterDuff.Mode tintMode, boolean requiresElevationOverlay) {
        int tintColor = tintList.getColorForState(getState(), 0);
        if (requiresElevationOverlay) {
            tintColor = compositeElevationOverlayIfNeeded(tintColor);
        }
        this.resolvedTintColor = tintColor;
        return new PorterDuffColorFilter(tintColor, tintMode);
    }

    public boolean isStateful() {
        return super.isStateful() || (this.drawableState.tintList != null && this.drawableState.tintList.isStateful()) || ((this.drawableState.strokeTintList != null && this.drawableState.strokeTintList.isStateful()) || ((this.drawableState.strokeColor != null && this.drawableState.strokeColor.isStateful()) || (this.drawableState.fillColor != null && this.drawableState.fillColor.isStateful())));
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] state) {
        boolean invalidateSelf = updateColorsForState(state) || updateTintFilter();
        if (invalidateSelf) {
            invalidateSelf();
        }
        return invalidateSelf;
    }

    private boolean updateColorsForState(int[] state) {
        int previousStrokeColor;
        int newStrokeColor;
        int previousFillColor;
        int newFillColor;
        boolean invalidateSelf = false;
        if (!(this.drawableState.fillColor == null || (previousFillColor = this.fillPaint.getColor()) == (newFillColor = this.drawableState.fillColor.getColorForState(state, previousFillColor)))) {
            this.fillPaint.setColor(newFillColor);
            invalidateSelf = true;
        }
        if (this.drawableState.strokeColor == null || (previousStrokeColor = this.strokePaint.getColor()) == (newStrokeColor = this.drawableState.strokeColor.getColorForState(state, previousStrokeColor))) {
            return invalidateSelf;
        }
        this.strokePaint.setColor(newStrokeColor);
        return true;
    }

    private float getStrokeInsetLength() {
        if (hasStroke()) {
            return this.strokePaint.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    private RectF getBoundsInsetByStroke() {
        this.insetRectF.set(getBoundsAsRectF());
        float inset = getStrokeInsetLength();
        this.insetRectF.inset(inset, inset);
        return this.insetRectF;
    }

    public float getTopLeftCornerResolvedSize() {
        return this.drawableState.shapeAppearanceModel.getTopLeftCornerSize().getCornerSize(getBoundsAsRectF());
    }

    public float getTopRightCornerResolvedSize() {
        return this.drawableState.shapeAppearanceModel.getTopRightCornerSize().getCornerSize(getBoundsAsRectF());
    }

    public float getBottomLeftCornerResolvedSize() {
        return this.drawableState.shapeAppearanceModel.getBottomLeftCornerSize().getCornerSize(getBoundsAsRectF());
    }

    public float getBottomRightCornerResolvedSize() {
        return this.drawableState.shapeAppearanceModel.getBottomRightCornerSize().getCornerSize(getBoundsAsRectF());
    }

    public boolean isRoundRect() {
        return this.drawableState.shapeAppearanceModel.isRoundRect(getBoundsAsRectF());
    }

    static final class MaterialShapeDrawableState extends Drawable.ConstantState {
        public int alpha = 255;
        public ColorFilter colorFilter;
        public float elevation = 0.0f;
        public ElevationOverlayProvider elevationOverlayProvider;
        public ColorStateList fillColor = null;
        public float interpolation = 1.0f;
        public Rect padding = null;
        public Paint.Style paintStyle = Paint.Style.FILL_AND_STROKE;
        public float parentAbsoluteElevation = 0.0f;
        public float scale = 1.0f;
        public int shadowCompatMode = 0;
        public int shadowCompatOffset = 0;
        public int shadowCompatRadius = 0;
        public int shadowCompatRotation = 0;
        public ShapeAppearanceModel shapeAppearanceModel;
        public ColorStateList strokeColor = null;
        public ColorStateList strokeTintList = null;
        public float strokeWidth;
        public ColorStateList tintList = null;
        public PorterDuff.Mode tintMode = PorterDuff.Mode.SRC_IN;
        public float translationZ = 0.0f;
        public boolean useTintColorForShadow = false;

        public MaterialShapeDrawableState(ShapeAppearanceModel shapeAppearanceModel2, ElevationOverlayProvider elevationOverlayProvider2) {
            this.shapeAppearanceModel = shapeAppearanceModel2;
            this.elevationOverlayProvider = elevationOverlayProvider2;
        }

        public MaterialShapeDrawableState(MaterialShapeDrawableState orig) {
            this.shapeAppearanceModel = orig.shapeAppearanceModel;
            this.elevationOverlayProvider = orig.elevationOverlayProvider;
            this.strokeWidth = orig.strokeWidth;
            this.colorFilter = orig.colorFilter;
            this.fillColor = orig.fillColor;
            this.strokeColor = orig.strokeColor;
            this.tintMode = orig.tintMode;
            this.tintList = orig.tintList;
            this.alpha = orig.alpha;
            this.scale = orig.scale;
            this.shadowCompatOffset = orig.shadowCompatOffset;
            this.shadowCompatMode = orig.shadowCompatMode;
            this.useTintColorForShadow = orig.useTintColorForShadow;
            this.interpolation = orig.interpolation;
            this.parentAbsoluteElevation = orig.parentAbsoluteElevation;
            this.elevation = orig.elevation;
            this.translationZ = orig.translationZ;
            this.shadowCompatRadius = orig.shadowCompatRadius;
            this.shadowCompatRotation = orig.shadowCompatRotation;
            this.strokeTintList = orig.strokeTintList;
            this.paintStyle = orig.paintStyle;
            if (orig.padding != null) {
                this.padding = new Rect(orig.padding);
            }
        }

        public Drawable newDrawable() {
            MaterialShapeDrawable msd = new MaterialShapeDrawable(this);
            boolean unused = msd.pathDirty = true;
            return msd;
        }

        public int getChangingConfigurations() {
            return 0;
        }
    }
}
