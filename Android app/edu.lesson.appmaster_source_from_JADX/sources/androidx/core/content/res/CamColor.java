package androidx.core.content.res;

import androidx.core.graphics.ColorUtils;

class CamColor {
    private static final float CHROMA_SEARCH_ENDPOINT = 0.4f;
    private static final float DE_MAX = 1.0f;
    private static final float DL_MAX = 0.2f;
    private static final float LIGHTNESS_SEARCH_ENDPOINT = 0.01f;
    private final float mAstar;
    private final float mBstar;
    private final float mChroma;
    private final float mHue;

    /* renamed from: mJ */
    private final float f378mJ;
    private final float mJstar;

    /* renamed from: mM */
    private final float f379mM;

    /* renamed from: mQ */
    private final float f380mQ;

    /* renamed from: mS */
    private final float f381mS;

    /* access modifiers changed from: package-private */
    public float getHue() {
        return this.mHue;
    }

    /* access modifiers changed from: package-private */
    public float getChroma() {
        return this.mChroma;
    }

    /* access modifiers changed from: package-private */
    public float getJ() {
        return this.f378mJ;
    }

    /* access modifiers changed from: package-private */
    public float getQ() {
        return this.f380mQ;
    }

    /* access modifiers changed from: package-private */
    public float getM() {
        return this.f379mM;
    }

    /* access modifiers changed from: package-private */
    public float getS() {
        return this.f381mS;
    }

    /* access modifiers changed from: package-private */
    public float getJStar() {
        return this.mJstar;
    }

    /* access modifiers changed from: package-private */
    public float getAStar() {
        return this.mAstar;
    }

    /* access modifiers changed from: package-private */
    public float getBStar() {
        return this.mBstar;
    }

    CamColor(float hue, float chroma, float j, float q, float m, float s, float jStar, float aStar, float bStar) {
        this.mHue = hue;
        this.mChroma = chroma;
        this.f378mJ = j;
        this.f380mQ = q;
        this.f379mM = m;
        this.f381mS = s;
        this.mJstar = jStar;
        this.mAstar = aStar;
        this.mBstar = bStar;
    }

    static int toColor(float hue, float chroma, float lStar) {
        return toColor(hue, chroma, lStar, ViewingConditions.DEFAULT);
    }

    static CamColor fromColor(int color) {
        return fromColorInViewingConditions(color, ViewingConditions.DEFAULT);
    }

    static CamColor fromColorInViewingConditions(int color, ViewingConditions viewingConditions) {
        float f;
        float[] xyz = CamUtils.xyzFromInt(color);
        float[][] matrix = CamUtils.XYZ_TO_CAM16RGB;
        float rT = (xyz[0] * matrix[0][0]) + (xyz[1] * matrix[0][1]) + (xyz[2] * matrix[0][2]);
        float gT = (xyz[0] * matrix[1][0]) + (xyz[1] * matrix[1][1]) + (xyz[2] * matrix[1][2]);
        float bT = (xyz[0] * matrix[2][0]) + (xyz[1] * matrix[2][1]) + (xyz[2] * matrix[2][2]);
        float rD = viewingConditions.getRgbD()[0] * rT;
        float gD = viewingConditions.getRgbD()[1] * gT;
        float bD = viewingConditions.getRgbD()[2] * bT;
        float rAF = (float) Math.pow(((double) (viewingConditions.getFl() * Math.abs(rD))) / 100.0d, 0.42d);
        float gAF = (float) Math.pow(((double) (viewingConditions.getFl() * Math.abs(gD))) / 100.0d, 0.42d);
        float bAF = (float) Math.pow(((double) (viewingConditions.getFl() * Math.abs(bD))) / 100.0d, 0.42d);
        float rA = ((Math.signum(rD) * 400.0f) * rAF) / (rAF + 27.13f);
        float gA = ((Math.signum(gD) * 400.0f) * gAF) / (gAF + 27.13f);
        float bA = ((Math.signum(bD) * 400.0f) * bAF) / (27.13f + bAF);
        float[] fArr = xyz;
        float[][] fArr2 = matrix;
        float a = ((float) (((((double) rA) * 11.0d) + (((double) gA) * -12.0d)) + ((double) bA))) / 11.0f;
        float f2 = rD;
        float b = ((float) (((double) (rA + gA)) - (((double) bA) * 2.0d))) / 9.0f;
        float u = (((rA * 20.0f) + (gA * 20.0f)) + (21.0f * bA)) / 20.0f;
        float p2 = (((40.0f * rA) + (gA * 20.0f)) + bA) / 20.0f;
        float f3 = rT;
        float f4 = gD;
        float f5 = bD;
        float atan2 = (float) Math.atan2((double) b, (double) a);
        float atanDegrees = (atan2 * 180.0f) / 3.1415927f;
        if (atanDegrees < 0.0f) {
            f = atanDegrees + 360.0f;
        } else {
            f = atanDegrees >= 360.0f ? atanDegrees - 360.0f : atanDegrees;
        }
        float hue = f;
        float f6 = atan2;
        float atan22 = hue;
        float hueRadians = (atan22 * 3.1415927f) / 180.0f;
        float ac = viewingConditions.getNbb() * p2;
        float f7 = atanDegrees;
        float f8 = ac;
        float f9 = gT;
        float f10 = bT;
        float j = ((float) Math.pow((double) (ac / viewingConditions.getAw()), (double) (viewingConditions.getC() * viewingConditions.getZ()))) * 100.0f;
        float f11 = rAF;
        float q = (4.0f / viewingConditions.getC()) * ((float) Math.sqrt((double) (j / 100.0f))) * (viewingConditions.getAw() + 4.0f) * viewingConditions.getFlRoot();
        float eHue = ((float) (Math.cos(((((double) (((double) atan22) < 20.14d ? atan22 + 360.0f : atan22)) * 3.141592653589793d) / 180.0d) + 2.0d) + 3.8d)) * 0.25f;
        float p1 = 3846.1538f * eHue * viewingConditions.getNc() * viewingConditions.getNcb();
        float f12 = a;
        float f13 = b;
        float t = (((float) Math.sqrt((double) ((a * a) + (b * b)))) * p1) / (0.305f + u);
        float f14 = p1;
        float f15 = eHue;
        float f16 = gAF;
        float f17 = gA;
        float alpha = ((float) Math.pow(1.64d - Math.pow(0.29d, (double) viewingConditions.getN()), 0.73d)) * ((float) Math.pow((double) t, 0.9d));
        float c = ((float) Math.sqrt(((double) j) / 100.0d)) * alpha;
        float m = viewingConditions.getFlRoot() * c;
        float jstar = (1.7f * j) / ((0.007f * j) + 1.0f);
        float f18 = t;
        float f19 = alpha;
        float mstar = ((float) Math.log((double) ((0.0228f * m) + 1.0f))) * 43.85965f;
        float f20 = bA;
        float f21 = bAF;
        float f22 = rA;
        return new CamColor(atan22, c, j, q, m, ((float) Math.sqrt((double) ((viewingConditions.getC() * alpha) / (viewingConditions.getAw() + 4.0f)))) * 50.0f, jstar, ((float) Math.cos((double) hueRadians)) * mstar, ((float) Math.sin((double) hueRadians)) * mstar);
    }

    private static CamColor fromJch(float j, float c, float h) {
        return fromJchInFrame(j, c, h, ViewingConditions.DEFAULT);
    }

    private static CamColor fromJchInFrame(float j, float c, float h, ViewingConditions viewingConditions) {
        float f = j;
        float q = (4.0f / viewingConditions.getC()) * ((float) Math.sqrt(((double) f) / 100.0d)) * (viewingConditions.getAw() + 4.0f) * viewingConditions.getFlRoot();
        float m = c * viewingConditions.getFlRoot();
        float s = ((float) Math.sqrt((double) ((viewingConditions.getC() * (c / ((float) Math.sqrt(((double) f) / 100.0d)))) / (viewingConditions.getAw() + 4.0f)))) * 50.0f;
        float hueRadians = (3.1415927f * h) / 180.0f;
        float jstar = (1.7f * f) / ((0.007f * f) + 1.0f);
        float mstar = ((float) Math.log((((double) m) * 0.0228d) + 1.0d)) * 43.85965f;
        return new CamColor(h, c, j, q, m, s, jstar, mstar * ((float) Math.cos((double) hueRadians)), mstar * ((float) Math.sin((double) hueRadians)));
    }

    /* access modifiers changed from: package-private */
    public float distance(CamColor other) {
        float dJ = getJStar() - other.getJStar();
        float dA = getAStar() - other.getAStar();
        float dB = getBStar() - other.getBStar();
        return (float) (Math.pow(Math.sqrt((double) ((dJ * dJ) + (dA * dA) + (dB * dB))), 0.63d) * 1.41d);
    }

    /* access modifiers changed from: package-private */
    public int viewedInSrgb() {
        return viewed(ViewingConditions.DEFAULT);
    }

    /* access modifiers changed from: package-private */
    public int viewed(ViewingConditions viewingConditions) {
        float alpha;
        if (((double) getChroma()) == 0.0d || ((double) getJ()) == 0.0d) {
            alpha = 0.0f;
        } else {
            alpha = getChroma() / ((float) Math.sqrt(((double) getJ()) / 100.0d));
        }
        float t = (float) Math.pow(((double) alpha) / Math.pow(1.64d - Math.pow(0.29d, (double) viewingConditions.getN()), 0.73d), 1.1111111111111112d);
        float hRad = (getHue() * 3.1415927f) / 180.0f;
        float eHue = ((float) (Math.cos(((double) hRad) + 2.0d) + 3.8d)) * 0.25f;
        float ac = viewingConditions.getAw() * ((float) Math.pow(((double) getJ()) / 100.0d, (1.0d / ((double) viewingConditions.getC())) / ((double) viewingConditions.getZ())));
        float p1 = 3846.1538f * eHue * viewingConditions.getNc() * viewingConditions.getNcb();
        float p2 = ac / viewingConditions.getNbb();
        float hSin = (float) Math.sin((double) hRad);
        float hCos = (float) Math.cos((double) hRad);
        float gamma = (((0.305f + p2) * 23.0f) * t) / (((23.0f * p1) + ((11.0f * t) * hCos)) + ((108.0f * t) * hSin));
        float a = gamma * hCos;
        float b = gamma * hSin;
        float rA = (((p2 * 460.0f) + (451.0f * a)) + (288.0f * b)) / 1403.0f;
        float gA = (((p2 * 460.0f) - (891.0f * a)) - (261.0f * b)) / 1403.0f;
        float bA = (((460.0f * p2) - (220.0f * a)) - (6300.0f * b)) / 1403.0f;
        float f = alpha;
        float f2 = t;
        float rCBase = (float) Math.max(0.0d, (((double) Math.abs(rA)) * 27.13d) / (400.0d - ((double) Math.abs(rA))));
        float f3 = p2;
        float f4 = hRad;
        float rC = Math.signum(rA) * (100.0f / viewingConditions.getFl()) * ((float) Math.pow((double) rCBase, 2.380952380952381d));
        float gCBase = (float) Math.max(0.0d, (((double) Math.abs(gA)) * 27.13d) / (400.0d - ((double) Math.abs(gA))));
        float f5 = p1;
        float f6 = gCBase;
        float gC = Math.signum(gA) * (100.0f / viewingConditions.getFl()) * ((float) Math.pow((double) gCBase, 2.380952380952381d));
        float bCBase = (float) Math.max(0.0d, (((double) Math.abs(bA)) * 27.13d) / (400.0d - ((double) Math.abs(bA))));
        float f7 = ac;
        float f8 = eHue;
        float bC = Math.signum(bA) * (100.0f / viewingConditions.getFl()) * ((float) Math.pow((double) bCBase, 2.380952380952381d));
        float rF = rC / viewingConditions.getRgbD()[0];
        float gF = gC / viewingConditions.getRgbD()[1];
        float bF = bC / viewingConditions.getRgbD()[2];
        float[][] matrix = CamUtils.CAM16RGB_TO_XYZ;
        float x = (matrix[0][0] * rF) + (matrix[0][1] * gF) + (matrix[0][2] * bF);
        float f9 = rCBase;
        float f10 = rC;
        float f11 = bCBase;
        float f12 = bC;
        float f13 = gC;
        float f14 = rF;
        float f15 = x;
        return ColorUtils.XYZToColor((double) x, (double) ((matrix[1][0] * rF) + (matrix[1][1] * gF) + (matrix[1][2] * bF)), (double) ((matrix[2][0] * rF) + (matrix[2][1] * gF) + (matrix[2][2] * bF)));
    }

    static int toColor(float hue, float chroma, float lstar, ViewingConditions viewingConditions) {
        if (((double) chroma) < 1.0d || ((double) Math.round(lstar)) <= 0.0d || ((double) Math.round(lstar)) >= 100.0d) {
            return CamUtils.intFromLStar(lstar);
        }
        float f = 0.0f;
        if (hue >= 0.0f) {
            f = Math.min(360.0f, hue);
        }
        float hue2 = f;
        float high = chroma;
        float mid = chroma;
        float low = 0.0f;
        boolean isFirstLoop = true;
        CamColor answer = null;
        while (Math.abs(low - high) >= CHROMA_SEARCH_ENDPOINT) {
            CamColor possibleAnswer = findCamByJ(hue2, mid, lstar);
            if (!isFirstLoop) {
                if (possibleAnswer == null) {
                    high = mid;
                } else {
                    answer = possibleAnswer;
                    low = mid;
                }
                mid = low + ((high - low) / 2.0f);
            } else if (possibleAnswer != null) {
                return possibleAnswer.viewed(viewingConditions);
            } else {
                isFirstLoop = false;
                mid = low + ((high - low) / 2.0f);
            }
        }
        if (answer == null) {
            return CamUtils.intFromLStar(lstar);
        }
        return answer.viewed(viewingConditions);
    }

    private static CamColor findCamByJ(float hue, float chroma, float lstar) {
        float low = 0.0f;
        float high = 100.0f;
        float bestdL = 1000.0f;
        float bestdE = 1000.0f;
        CamColor bestCam = null;
        while (Math.abs(low - high) > LIGHTNESS_SEARCH_ENDPOINT) {
            float mid = low + ((high - low) / 2.0f);
            int clipped = fromJch(mid, chroma, hue).viewedInSrgb();
            float clippedLstar = CamUtils.lStarFromInt(clipped);
            float dL = Math.abs(lstar - clippedLstar);
            if (dL < 0.2f) {
                CamColor camClipped = fromColor(clipped);
                float dE = camClipped.distance(fromJch(camClipped.getJ(), camClipped.getChroma(), hue));
                if (dE <= 1.0f) {
                    bestdL = dL;
                    bestdE = dE;
                    bestCam = camClipped;
                }
            }
            if (bestdL == 0.0f && bestdE == 0.0f) {
                break;
            } else if (clippedLstar < lstar) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return bestCam;
    }
}
