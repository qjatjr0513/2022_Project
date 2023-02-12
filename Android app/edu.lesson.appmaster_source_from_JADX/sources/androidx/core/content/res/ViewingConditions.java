package androidx.core.content.res;

final class ViewingConditions {
    static final ViewingConditions DEFAULT = make(CamUtils.WHITE_POINT_D65, (float) ((((double) CamUtils.yFromLStar(50.0f)) * 63.66197723675813d) / 100.0d), 50.0f, 2.0f, false);
    private final float mAw;

    /* renamed from: mC */
    private final float f382mC;
    private final float mFl;
    private final float mFlRoot;

    /* renamed from: mN */
    private final float f383mN;
    private final float mNbb;
    private final float mNc;
    private final float mNcb;
    private final float[] mRgbD;

    /* renamed from: mZ */
    private final float f384mZ;

    /* access modifiers changed from: package-private */
    public float getAw() {
        return this.mAw;
    }

    /* access modifiers changed from: package-private */
    public float getN() {
        return this.f383mN;
    }

    /* access modifiers changed from: package-private */
    public float getNbb() {
        return this.mNbb;
    }

    /* access modifiers changed from: package-private */
    public float getNcb() {
        return this.mNcb;
    }

    /* access modifiers changed from: package-private */
    public float getC() {
        return this.f382mC;
    }

    /* access modifiers changed from: package-private */
    public float getNc() {
        return this.mNc;
    }

    /* access modifiers changed from: package-private */
    public float[] getRgbD() {
        return this.mRgbD;
    }

    /* access modifiers changed from: package-private */
    public float getFl() {
        return this.mFl;
    }

    /* access modifiers changed from: package-private */
    public float getFlRoot() {
        return this.mFlRoot;
    }

    /* access modifiers changed from: package-private */
    public float getZ() {
        return this.f384mZ;
    }

    private ViewingConditions(float n, float aw, float nbb, float ncb, float c, float nc, float[] rgbD, float fl, float fLRoot, float z) {
        this.f383mN = n;
        this.mAw = aw;
        this.mNbb = nbb;
        this.mNcb = ncb;
        this.f382mC = c;
        this.mNc = nc;
        this.mRgbD = rgbD;
        this.mFl = fl;
        this.mFlRoot = fLRoot;
        this.f384mZ = z;
    }

    static ViewingConditions make(float[] whitepoint, float adaptingLuminance, float backgroundLstar, float surround, boolean discountingIlluminant) {
        float f = adaptingLuminance;
        float[][] matrix = CamUtils.XYZ_TO_CAM16RGB;
        float[] xyz = whitepoint;
        float rW = (xyz[0] * matrix[0][0]) + (xyz[1] * matrix[0][1]) + (xyz[2] * matrix[0][2]);
        float gW = (xyz[0] * matrix[1][0]) + (xyz[1] * matrix[1][1]) + (xyz[2] * matrix[1][2]);
        float bW = (xyz[0] * matrix[2][0]) + (xyz[1] * matrix[2][1]) + (xyz[2] * matrix[2][2]);
        float f2 = (surround / 10.0f) + 0.8f;
        float c = ((double) f2) >= 0.9d ? CamUtils.lerp(0.59f, 0.69f, (f2 - 0.9f) * 10.0f) : CamUtils.lerp(0.525f, 0.59f, (f2 - 0.8f) * 10.0f);
        float d = discountingIlluminant ? 1.0f : (1.0f - (((float) Math.exp((double) (((-f) - 42.0f) / 92.0f))) * 0.2777778f)) * f2;
        float d2 = ((double) d) > 1.0d ? 1.0f : ((double) d) < 0.0d ? 0.0f : d;
        float[] rgbD = {(((100.0f / rW) * d2) + 1.0f) - d2, (((100.0f / gW) * d2) + 1.0f) - d2, (((100.0f / bW) * d2) + 1.0f) - d2};
        float k = 1.0f / ((5.0f * f) + 1.0f);
        float k4 = k * k * k * k;
        float k4F = 1.0f - k4;
        float gW2 = gW;
        float fl = (k4 * f) + (0.1f * k4F * k4F * ((float) Math.cbrt(((double) f) * 5.0d)));
        float n = CamUtils.yFromLStar(backgroundLstar) / whitepoint[1];
        float z = ((float) Math.sqrt((double) n)) + 1.48f;
        float fl2 = fl;
        float nbb = 0.725f / ((float) Math.pow((double) n, 0.2d));
        float ncb = nbb;
        float f3 = rW;
        float[] rgbAFactors = {(float) Math.pow(((double) ((rgbD[0] * fl2) * rW)) / 100.0d, 0.42d), (float) Math.pow(((double) ((rgbD[1] * fl2) * gW2)) / 100.0d, 0.42d), (float) Math.pow(((double) ((rgbD[2] * fl2) * bW)) / 100.0d, 0.42d)};
        float[] rgbA = {(rgbAFactors[0] * 400.0f) / (rgbAFactors[0] + 27.13f), (rgbAFactors[1] * 400.0f) / (rgbAFactors[1] + 27.13f), (rgbAFactors[2] * 400.0f) / (rgbAFactors[2] + 27.13f)};
        float fl3 = fl2;
        float[][] fArr = matrix;
        return new ViewingConditions(n, ((rgbA[0] * 2.0f) + rgbA[1] + (rgbA[2] * 0.05f)) * nbb, nbb, ncb, c, f2, rgbD, fl3, (float) Math.pow((double) fl3, 0.25d), z);
    }
}
