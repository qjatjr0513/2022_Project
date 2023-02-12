package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.C2299R;

public class ImageFilterView extends AppCompatImageView {
    private Drawable mAltDrawable = null;
    private float mCrossfade = 0.0f;
    private Drawable mDrawable = null;
    private ImageMatrix mImageMatrix = new ImageMatrix();
    LayerDrawable mLayer;
    Drawable[] mLayers = new Drawable[2];
    private boolean mOverlay = true;
    float mPanX = Float.NaN;
    float mPanY = Float.NaN;
    private Path mPath;
    RectF mRect;
    float mRotate = Float.NaN;
    /* access modifiers changed from: private */
    public float mRound = Float.NaN;
    /* access modifiers changed from: private */
    public float mRoundPercent = 0.0f;
    ViewOutlineProvider mViewOutlineProvider;
    float mZoom = Float.NaN;

    static class ImageMatrix {

        /* renamed from: m */
        float[] f373m = new float[20];
        float mBrightness = 1.0f;
        ColorMatrix mColorMatrix = new ColorMatrix();
        float mContrast = 1.0f;
        float mSaturation = 1.0f;
        ColorMatrix mTmpColorMatrix = new ColorMatrix();
        float mWarmth = 1.0f;

        ImageMatrix() {
        }

        private void saturation(float saturationStrength) {
            float S = saturationStrength;
            float MS = 1.0f - S;
            float Rt = 0.2999f * MS;
            float Gt = 0.587f * MS;
            float Bt = 0.114f * MS;
            float[] fArr = this.f373m;
            fArr[0] = Rt + S;
            fArr[1] = Gt;
            fArr[2] = Bt;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = Rt;
            fArr[6] = Gt + S;
            fArr[7] = Bt;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = Rt;
            fArr[11] = Gt;
            fArr[12] = Bt + S;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }

        private void warmth(float warmth) {
            float colorG;
            float colorR;
            float colorB;
            float colorG2;
            float colorR2;
            float colorB2;
            float warmth2 = warmth <= 0.0f ? 0.01f : warmth;
            float centiKelvin = (5000.0f / warmth2) / 100.0f;
            if (centiKelvin > 66.0f) {
                float tmp = centiKelvin - 60.0f;
                float f = warmth2;
                colorR = ((float) Math.pow((double) tmp, -0.13320475816726685d)) * 329.69873f;
                colorG = ((float) Math.pow((double) tmp, 0.07551484555006027d)) * 288.12216f;
            } else {
                colorG = (((float) Math.log((double) centiKelvin)) * 99.4708f) - 161.11957f;
                colorR = 255.0f;
            }
            if (centiKelvin >= 66.0f) {
                colorB = 255.0f;
            } else if (centiKelvin > 19.0f) {
                colorB = (((float) Math.log((double) (centiKelvin - 10.0f))) * 138.51773f) - 305.0448f;
            } else {
                colorB = 0.0f;
            }
            float tmpColor_r = Math.min(255.0f, Math.max(colorR, 0.0f));
            float tmpColor_g = Math.min(255.0f, Math.max(colorG, 0.0f));
            float color_r = tmpColor_r;
            float color_g = tmpColor_g;
            float color_b = Math.min(255.0f, Math.max(colorB, 0.0f));
            float centiKelvin2 = 5000.0f / 100.0f;
            if (centiKelvin2 > 66.0f) {
                float tmp2 = centiKelvin2 - 60.0f;
                float f2 = tmpColor_g;
                colorR2 = ((float) Math.pow((double) tmp2, -0.13320475816726685d)) * 329.69873f;
                colorG2 = ((float) Math.pow((double) tmp2, 0.07551484555006027d)) * 288.12216f;
            } else {
                colorG2 = (((float) Math.log((double) centiKelvin2)) * 99.4708f) - 161.11957f;
                colorR2 = 255.0f;
            }
            if (centiKelvin2 >= 66.0f) {
                colorB2 = 255.0f;
            } else if (centiKelvin2 > 19.0f) {
                colorB2 = (((float) Math.log((double) (centiKelvin2 - 10.0f))) * 138.51773f) - 305.0448f;
            } else {
                colorB2 = 0.0f;
            }
            float tmpColor_r2 = Math.min(255.0f, Math.max(colorR2, 0.0f));
            float tmpColor_g2 = Math.min(255.0f, Math.max(colorG2, 0.0f));
            float[] fArr = this.f373m;
            fArr[0] = color_r / tmpColor_r2;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = 0.0f;
            fArr[6] = color_g / tmpColor_g2;
            fArr[7] = 0.0f;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = 0.0f;
            fArr[11] = 0.0f;
            fArr[12] = color_b / Math.min(255.0f, Math.max(colorB2, 0.0f));
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }

        private void brightness(float brightness) {
            float[] fArr = this.f373m;
            fArr[0] = brightness;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = 0.0f;
            fArr[6] = brightness;
            fArr[7] = 0.0f;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = 0.0f;
            fArr[11] = 0.0f;
            fArr[12] = brightness;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
        }

        /* access modifiers changed from: package-private */
        public void updateMatrix(ImageView view) {
            this.mColorMatrix.reset();
            boolean filter = false;
            float f = this.mSaturation;
            if (f != 1.0f) {
                saturation(f);
                this.mColorMatrix.set(this.f373m);
                filter = true;
            }
            float f2 = this.mContrast;
            if (f2 != 1.0f) {
                this.mTmpColorMatrix.setScale(f2, f2, f2, 1.0f);
                this.mColorMatrix.postConcat(this.mTmpColorMatrix);
                filter = true;
            }
            float f3 = this.mWarmth;
            if (f3 != 1.0f) {
                warmth(f3);
                this.mTmpColorMatrix.set(this.f373m);
                this.mColorMatrix.postConcat(this.mTmpColorMatrix);
                filter = true;
            }
            float f4 = this.mBrightness;
            if (f4 != 1.0f) {
                brightness(f4);
                this.mTmpColorMatrix.set(this.f373m);
                this.mColorMatrix.postConcat(this.mTmpColorMatrix);
                filter = true;
            }
            if (filter) {
                view.setColorFilter(new ColorMatrixColorFilter(this.mColorMatrix));
            } else {
                view.clearColorFilter();
            }
        }
    }

    public float getImagePanX() {
        return this.mPanX;
    }

    public float getImagePanY() {
        return this.mPanY;
    }

    public float getImageZoom() {
        return this.mZoom;
    }

    public float getImageRotate() {
        return this.mRotate;
    }

    public void setImagePanX(float pan) {
        this.mPanX = pan;
        updateViewMatrix();
    }

    public void setImagePanY(float pan) {
        this.mPanY = pan;
        updateViewMatrix();
    }

    public void setImageZoom(float zoom) {
        this.mZoom = zoom;
        updateViewMatrix();
    }

    public void setImageRotate(float rotation) {
        this.mRotate = rotation;
        updateViewMatrix();
    }

    public void setImageDrawable(Drawable drawable) {
        if (this.mAltDrawable == null || drawable == null) {
            super.setImageDrawable(drawable);
            return;
        }
        Drawable mutate = drawable.mutate();
        this.mDrawable = mutate;
        Drawable[] drawableArr = this.mLayers;
        drawableArr[0] = mutate;
        drawableArr[1] = this.mAltDrawable;
        LayerDrawable layerDrawable = new LayerDrawable(this.mLayers);
        this.mLayer = layerDrawable;
        super.setImageDrawable(layerDrawable);
        setCrossfade(this.mCrossfade);
    }

    public void setImageResource(int resId) {
        if (this.mAltDrawable != null) {
            Drawable mutate = AppCompatResources.getDrawable(getContext(), resId).mutate();
            this.mDrawable = mutate;
            Drawable[] drawableArr = this.mLayers;
            drawableArr[0] = mutate;
            drawableArr[1] = this.mAltDrawable;
            LayerDrawable layerDrawable = new LayerDrawable(this.mLayers);
            this.mLayer = layerDrawable;
            super.setImageDrawable(layerDrawable);
            setCrossfade(this.mCrossfade);
            return;
        }
        super.setImageResource(resId);
    }

    public void setAltImageResource(int resId) {
        Drawable mutate = AppCompatResources.getDrawable(getContext(), resId).mutate();
        this.mAltDrawable = mutate;
        Drawable[] drawableArr = this.mLayers;
        drawableArr[0] = this.mDrawable;
        drawableArr[1] = mutate;
        LayerDrawable layerDrawable = new LayerDrawable(this.mLayers);
        this.mLayer = layerDrawable;
        super.setImageDrawable(layerDrawable);
        setCrossfade(this.mCrossfade);
    }

    private void updateViewMatrix() {
        if (!Float.isNaN(this.mPanX) || !Float.isNaN(this.mPanY) || !Float.isNaN(this.mZoom) || !Float.isNaN(this.mRotate)) {
            setMatrix();
        } else {
            setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    }

    private void setMatrix() {
        if (!Float.isNaN(this.mPanX) || !Float.isNaN(this.mPanY) || !Float.isNaN(this.mZoom) || !Float.isNaN(this.mRotate)) {
            float rota = 0.0f;
            float panX = Float.isNaN(this.mPanX) ? 0.0f : this.mPanX;
            float panY = Float.isNaN(this.mPanY) ? 0.0f : this.mPanY;
            float zoom = Float.isNaN(this.mZoom) ? 1.0f : this.mZoom;
            if (!Float.isNaN(this.mRotate)) {
                rota = this.mRotate;
            }
            Matrix imageMatrix = new Matrix();
            imageMatrix.reset();
            float iw = (float) getDrawable().getIntrinsicWidth();
            float ih = (float) getDrawable().getIntrinsicHeight();
            float sw = (float) getWidth();
            float sh = (float) getHeight();
            float scale = (iw * sh < ih * sw ? sw / iw : sh / ih) * zoom;
            imageMatrix.postScale(scale, scale);
            imageMatrix.postTranslate(((((sw - (scale * iw)) * panX) + sw) - (scale * iw)) * 0.5f, ((((sh - (scale * ih)) * panY) + sh) - (scale * ih)) * 0.5f);
            imageMatrix.postRotate(rota, sw / 2.0f, sh / 2.0f);
            setImageMatrix(imageMatrix);
            setScaleType(ImageView.ScaleType.MATRIX);
        }
    }

    public ImageFilterView(Context context) {
        super(context);
        init(context, (AttributeSet) null);
    }

    public ImageFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ImageFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, C2299R.styleable.ImageFilterView);
            int N = a.getIndexCount();
            this.mAltDrawable = a.getDrawable(C2299R.styleable.ImageFilterView_altSrc);
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == C2299R.styleable.ImageFilterView_crossfade) {
                    this.mCrossfade = a.getFloat(attr, 0.0f);
                } else if (attr == C2299R.styleable.ImageFilterView_warmth) {
                    setWarmth(a.getFloat(attr, 0.0f));
                } else if (attr == C2299R.styleable.ImageFilterView_saturation) {
                    setSaturation(a.getFloat(attr, 0.0f));
                } else if (attr == C2299R.styleable.ImageFilterView_contrast) {
                    setContrast(a.getFloat(attr, 0.0f));
                } else if (attr == C2299R.styleable.ImageFilterView_brightness) {
                    setBrightness(a.getFloat(attr, 0.0f));
                } else if (attr == C2299R.styleable.ImageFilterView_round) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        setRound(a.getDimension(attr, 0.0f));
                    }
                } else if (attr == C2299R.styleable.ImageFilterView_roundPercent) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        setRoundPercent(a.getFloat(attr, 0.0f));
                    }
                } else if (attr == C2299R.styleable.ImageFilterView_overlay) {
                    setOverlay(a.getBoolean(attr, this.mOverlay));
                } else if (attr == C2299R.styleable.ImageFilterView_imagePanX) {
                    setImagePanX(a.getFloat(attr, this.mPanX));
                } else if (attr == C2299R.styleable.ImageFilterView_imagePanY) {
                    setImagePanY(a.getFloat(attr, this.mPanY));
                } else if (attr == C2299R.styleable.ImageFilterView_imageRotate) {
                    setImageRotate(a.getFloat(attr, this.mRotate));
                } else if (attr == C2299R.styleable.ImageFilterView_imageZoom) {
                    setImageZoom(a.getFloat(attr, this.mZoom));
                }
            }
            a.recycle();
            Drawable drawable = getDrawable();
            this.mDrawable = drawable;
            if (this.mAltDrawable == null || drawable == null) {
                Drawable drawable2 = getDrawable();
                this.mDrawable = drawable2;
                if (drawable2 != null) {
                    Drawable[] drawableArr = this.mLayers;
                    Drawable mutate = drawable2.mutate();
                    this.mDrawable = mutate;
                    drawableArr[0] = mutate;
                    return;
                }
                return;
            }
            Drawable[] drawableArr2 = this.mLayers;
            Drawable mutate2 = getDrawable().mutate();
            this.mDrawable = mutate2;
            drawableArr2[0] = mutate2;
            this.mLayers[1] = this.mAltDrawable.mutate();
            LayerDrawable layerDrawable = new LayerDrawable(this.mLayers);
            this.mLayer = layerDrawable;
            layerDrawable.getDrawable(1).setAlpha((int) (this.mCrossfade * 255.0f));
            if (!this.mOverlay) {
                this.mLayer.getDrawable(0).setAlpha((int) ((1.0f - this.mCrossfade) * 255.0f));
            }
            super.setImageDrawable(this.mLayer);
        }
    }

    private void setOverlay(boolean overlay) {
        this.mOverlay = overlay;
    }

    public void setSaturation(float saturation) {
        this.mImageMatrix.mSaturation = saturation;
        this.mImageMatrix.updateMatrix(this);
    }

    public float getSaturation() {
        return this.mImageMatrix.mSaturation;
    }

    public void setContrast(float contrast) {
        this.mImageMatrix.mContrast = contrast;
        this.mImageMatrix.updateMatrix(this);
    }

    public float getContrast() {
        return this.mImageMatrix.mContrast;
    }

    public void setWarmth(float warmth) {
        this.mImageMatrix.mWarmth = warmth;
        this.mImageMatrix.updateMatrix(this);
    }

    public float getWarmth() {
        return this.mImageMatrix.mWarmth;
    }

    public void setCrossfade(float crossfade) {
        this.mCrossfade = crossfade;
        if (this.mLayers != null) {
            if (!this.mOverlay) {
                this.mLayer.getDrawable(0).setAlpha((int) ((1.0f - this.mCrossfade) * 255.0f));
            }
            this.mLayer.getDrawable(1).setAlpha((int) (this.mCrossfade * 255.0f));
            super.setImageDrawable(this.mLayer);
        }
    }

    public float getCrossfade() {
        return this.mCrossfade;
    }

    public void setBrightness(float brightness) {
        this.mImageMatrix.mBrightness = brightness;
        this.mImageMatrix.updateMatrix(this);
    }

    public float getBrightness() {
        return this.mImageMatrix.mBrightness;
    }

    public void setRoundPercent(float round) {
        boolean change = this.mRoundPercent != round;
        this.mRoundPercent = round;
        if (round != 0.0f) {
            if (this.mPath == null) {
                this.mPath = new Path();
            }
            if (this.mRect == null) {
                this.mRect = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.mViewOutlineProvider == null) {
                    C15681 r2 = new ViewOutlineProvider() {
                        public void getOutline(View view, Outline outline) {
                            int w = ImageFilterView.this.getWidth();
                            int h = ImageFilterView.this.getHeight();
                            outline.setRoundRect(0, 0, w, h, (((float) Math.min(w, h)) * ImageFilterView.this.mRoundPercent) / 2.0f);
                        }
                    };
                    this.mViewOutlineProvider = r2;
                    setOutlineProvider(r2);
                }
                setClipToOutline(true);
            }
            int w = getWidth();
            int h = getHeight();
            float r = (((float) Math.min(w, h)) * this.mRoundPercent) / 2.0f;
            this.mRect.set(0.0f, 0.0f, (float) w, (float) h);
            this.mPath.reset();
            this.mPath.addRoundRect(this.mRect, r, r, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (change && Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public void setRound(float round) {
        if (Float.isNaN(round)) {
            this.mRound = round;
            float tmp = this.mRoundPercent;
            this.mRoundPercent = -1.0f;
            setRoundPercent(tmp);
            return;
        }
        boolean change = this.mRound != round;
        this.mRound = round;
        if (round != 0.0f) {
            if (this.mPath == null) {
                this.mPath = new Path();
            }
            if (this.mRect == null) {
                this.mRect = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.mViewOutlineProvider == null) {
                    C15692 r2 = new ViewOutlineProvider() {
                        public void getOutline(View view, Outline outline) {
                            outline.setRoundRect(0, 0, ImageFilterView.this.getWidth(), ImageFilterView.this.getHeight(), ImageFilterView.this.mRound);
                        }
                    };
                    this.mViewOutlineProvider = r2;
                    setOutlineProvider(r2);
                }
                setClipToOutline(true);
            }
            this.mRect.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            this.mPath.reset();
            Path path = this.mPath;
            RectF rectF = this.mRect;
            float f = this.mRound;
            path.addRoundRect(rectF, f, f, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (change && Build.VERSION.SDK_INT >= 21) {
            invalidateOutline();
        }
    }

    public float getRoundPercent() {
        return this.mRoundPercent;
    }

    public float getRound() {
        return this.mRound;
    }

    public void draw(Canvas canvas) {
        boolean clip = false;
        if (!(Build.VERSION.SDK_INT >= 21 || this.mRoundPercent == 0.0f || this.mPath == null)) {
            clip = true;
            canvas.save();
            canvas.clipPath(this.mPath);
        }
        super.draw(canvas);
        if (clip) {
            canvas.restore();
        }
    }

    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        setMatrix();
    }
}
