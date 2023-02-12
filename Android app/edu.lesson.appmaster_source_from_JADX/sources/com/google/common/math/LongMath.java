package com.google.common.math;

import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import com.google.common.primitives.UnsignedLongs;
import java.math.RoundingMode;

public final class LongMath {
    static final long FLOOR_SQRT_MAX_LONG = 3037000499L;
    static final long MAX_POWER_OF_SQRT2_UNSIGNED = -5402926248376769404L;
    static final long MAX_SIGNED_POWER_OF_TWO = 4611686018427387904L;
    private static final int SIEVE_30 = -545925251;
    static final int[] biggestBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3810779, 121977, 16175, 4337, 1733, 887, 534, 361, 265, 206, 169, 143, 125, 111, 101, 94, 88, 83, 79, 76, 74, 72, 70, 69, 68, 67, 67, 66, 66, 66, 66};
    static final int[] biggestSimpleBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2642246, 86251, 11724, 3218, 1313, 684, 419, 287, 214, 169, 139, 119, 105, 95, 87, 81, 76, 73, 70, 68, 66, 64, 63, 62, 62, 61, 61, 61};
    static final long[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
    static final long[] halfPowersOf10 = {3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, 3162277660L, 31622776601L, 316227766016L, 3162277660168L, 31622776601683L, 316227766016837L, 3162277660168379L, 31622776601683793L, 316227766016837933L, 3162277660168379331L};
    static final byte[] maxLog10ForLeadingZeros = {19, Ascii.DC2, Ascii.DC2, Ascii.DC2, Ascii.DC2, 17, 17, 17, Ascii.DLE, Ascii.DLE, Ascii.DLE, Ascii.f62SI, Ascii.f62SI, Ascii.f62SI, Ascii.f62SI, Ascii.f63SO, Ascii.f63SO, Ascii.f63SO, Ascii.f53CR, Ascii.f53CR, Ascii.f53CR, Ascii.f55FF, Ascii.f55FF, Ascii.f55FF, Ascii.f55FF, Ascii.f66VT, Ascii.f66VT, Ascii.f66VT, 10, 10, 10, 9, 9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0};
    private static final long[][] millerRabinBaseSets = {new long[]{291830, 126401071349994536L}, new long[]{885594168, 725270293939359937L, 3569819667048198375L}, new long[]{273919523040L, 15, 7363882082L, 992620450144556L}, new long[]{47636622961200L, 2, 2570940, 211991001, 3749873356L}, new long[]{7999252175582850L, 2, 4130806001517L, 149795463772692060L, 186635894390467037L, 3967304179347715805L}, new long[]{585226005592931976L, 2, 123635709730000L, 9233062284813009L, 43835965440333360L, 761179012939631437L, 1263739024124850375L}, new long[]{Long.MAX_VALUE, 2, 325, 9375, 28178, 450775, 9780504, 1795265022}};
    static final long[] powersOf10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000L, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L, 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L};

    public static long ceilingPowerOfTwo(long x) {
        MathPreconditions.checkPositive("x", x);
        if (x <= 4611686018427387904L) {
            return 1 << (-Long.numberOfLeadingZeros(x - 1));
        }
        throw new ArithmeticException(new StringBuilder(70).append("ceilingPowerOfTwo(").append(x).append(") is not representable as a long").toString());
    }

    public static long floorPowerOfTwo(long x) {
        MathPreconditions.checkPositive("x", x);
        return 1 << (63 - Long.numberOfLeadingZeros(x));
    }

    public static boolean isPowerOfTwo(long x) {
        boolean z = true;
        boolean z2 = x > 0;
        if (((x - 1) & x) != 0) {
            z = false;
        }
        return z2 & z;
    }

    static int lessThanBranchFree(long x, long y) {
        return (int) ((~(~(x - y))) >>> 63);
    }

    /* renamed from: com.google.common.math.LongMath$1 */
    static /* synthetic */ class C04601 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode;

        static {
            int[] iArr = new int[RoundingMode.values().length];
            $SwitchMap$java$math$RoundingMode = iArr;
            try {
                iArr[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public static int log2(long x, RoundingMode mode) {
        MathPreconditions.checkPositive("x", x);
        switch (C04601.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(x));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 64 - Long.numberOfLeadingZeros(x - 1);
            case 6:
            case 7:
            case 8:
                int leadingZeros = Long.numberOfLeadingZeros(x);
                return lessThanBranchFree(MAX_POWER_OF_SQRT2_UNSIGNED >>> leadingZeros, x) + (63 - leadingZeros);
            default:
                throw new AssertionError("impossible");
        }
        return 63 - Long.numberOfLeadingZeros(x);
    }

    public static int log10(long x, RoundingMode mode) {
        MathPreconditions.checkPositive("x", x);
        int logFloor = log10Floor(x);
        long floorPow = powersOf10[logFloor];
        switch (C04601.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(x == floorPow);
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return lessThanBranchFree(floorPow, x) + logFloor;
            case 6:
            case 7:
            case 8:
                return lessThanBranchFree(halfPowersOf10[logFloor], x) + logFloor;
            default:
                throw new AssertionError();
        }
        return logFloor;
    }

    static int log10Floor(long x) {
        byte y = maxLog10ForLeadingZeros[Long.numberOfLeadingZeros(x)];
        return y - lessThanBranchFree(x, powersOf10[y]);
    }

    public static long pow(long b, int k) {
        MathPreconditions.checkNonNegative("exponent", k);
        if (-2 > b || b > 2) {
            long accum = 1;
            while (true) {
                switch (k) {
                    case 0:
                        return accum;
                    case 1:
                        return accum * b;
                    default:
                        accum *= (k & 1) == 0 ? 1 : b;
                        b *= b;
                        k >>= 1;
                }
            }
        } else {
            switch ((int) b) {
                case -2:
                    if (k < 64) {
                        return (k & 1) == 0 ? 1 << k : -(1 << k);
                    }
                    return 0;
                case -1:
                    if ((k & 1) == 0) {
                        return 1;
                    }
                    return -1;
                case 0:
                    if (k == 0) {
                        return 1;
                    }
                    return 0;
                case 1:
                    return 1;
                case 2:
                    if (k < 64) {
                        return 1 << k;
                    }
                    return 0;
                default:
                    throw new AssertionError();
            }
        }
    }

    public static long sqrt(long x, RoundingMode mode) {
        MathPreconditions.checkNonNegative("x", x);
        if (fitsInInt(x)) {
            return (long) IntMath.sqrt((int) x, mode);
        }
        long guess = (long) Math.sqrt((double) x);
        long guessSquared = guess * guess;
        boolean z = true;
        switch (C04601.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                if (guessSquared != x) {
                    z = false;
                }
                MathPreconditions.checkRoundingUnnecessary(z);
                return guess;
            case 2:
            case 3:
                if (x < guessSquared) {
                    return guess - 1;
                }
                return guess;
            case 4:
            case 5:
                if (x > guessSquared) {
                    return 1 + guess;
                }
                return guess;
            case 6:
            case 7:
            case 8:
                if (x >= guessSquared) {
                    z = false;
                }
                long sqrtFloor = guess - (z ? 1 : 0);
                return ((long) lessThanBranchFree((sqrtFloor * sqrtFloor) + sqrtFloor, x)) + sqrtFloor;
            default:
                throw new AssertionError();
        }
    }

    public static long divide(long p, long q, RoundingMode mode) {
        boolean increment;
        RoundingMode roundingMode = mode;
        Preconditions.checkNotNull(mode);
        long div = p / q;
        long rem = p - (q * div);
        if (rem == 0) {
            return div;
        }
        boolean z = true;
        int signum = ((int) ((p ^ q) >> 63)) | 1;
        switch (C04601.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                if (rem != 0) {
                    z = false;
                }
                MathPreconditions.checkRoundingUnnecessary(z);
                break;
            case 2:
                break;
            case 3:
                if (signum >= 0) {
                    z = false;
                }
                increment = z;
                break;
            case 4:
                increment = true;
                break;
            case 5:
                if (signum <= 0) {
                    z = false;
                }
                increment = z;
                break;
            case 6:
            case 7:
            case 8:
                long absRem = Math.abs(rem);
                long cmpRemToHalfDivisor = absRem - (Math.abs(q) - absRem);
                if (cmpRemToHalfDivisor != 0) {
                    if (cmpRemToHalfDivisor <= 0) {
                        z = false;
                    }
                    increment = z;
                    break;
                } else {
                    boolean z2 = roundingMode == RoundingMode.HALF_UP;
                    boolean z3 = roundingMode == RoundingMode.HALF_EVEN;
                    if ((div & 1) == 0) {
                        z = false;
                    }
                    increment = (z3 & z) | z2;
                    break;
                }
            default:
                throw new AssertionError();
        }
        increment = false;
        return increment ? ((long) signum) + div : div;
    }

    public static int mod(long x, int m) {
        return (int) mod(x, (long) m);
    }

    public static long mod(long x, long m) {
        if (m > 0) {
            long result = x % m;
            return result >= 0 ? result : result + m;
        }
        throw new ArithmeticException("Modulus must be positive");
    }

    public static long gcd(long a, long b) {
        MathPreconditions.checkNonNegative("a", a);
        MathPreconditions.checkNonNegative("b", b);
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        int aTwos = Long.numberOfTrailingZeros(a);
        long a2 = a >> aTwos;
        int bTwos = Long.numberOfTrailingZeros(b);
        long b2 = b >> bTwos;
        while (a2 != b2) {
            long delta = a2 - b2;
            long minDeltaOrZero = (delta >> 63) & delta;
            long a3 = (delta - minDeltaOrZero) - minDeltaOrZero;
            b2 += minDeltaOrZero;
            a2 = a3 >> Long.numberOfTrailingZeros(a3);
        }
        return a2 << Math.min(aTwos, bTwos);
    }

    public static long checkedAdd(long a, long b) {
        long result = a + b;
        boolean z = true;
        boolean z2 = (a ^ b) < 0;
        if ((a ^ result) < 0) {
            z = false;
        }
        MathPreconditions.checkNoOverflow(z2 | z, "checkedAdd", a, b);
        return result;
    }

    public static long checkedSubtract(long a, long b) {
        long result = a - b;
        boolean z = true;
        boolean z2 = (a ^ b) >= 0;
        if ((a ^ result) < 0) {
            z = false;
        }
        MathPreconditions.checkNoOverflow(z2 | z, "checkedSubtract", a, b);
        return result;
    }

    public static long checkedMultiply(long a, long b) {
        long j = a;
        long j2 = b;
        int leadingZeros = Long.numberOfLeadingZeros(a) + Long.numberOfLeadingZeros(~j) + Long.numberOfLeadingZeros(b) + Long.numberOfLeadingZeros(~j2);
        if (leadingZeros > 65) {
            return j * j2;
        }
        MathPreconditions.checkNoOverflow(leadingZeros >= 64, "checkedMultiply", a, b);
        MathPreconditions.checkNoOverflow((j >= 0) | (j2 != Long.MIN_VALUE), "checkedMultiply", a, b);
        long result = j * j2;
        MathPreconditions.checkNoOverflow(j == 0 || result / j == j2, "checkedMultiply", a, b);
        return result;
    }

    public static long checkedPow(long b, int k) {
        MathPreconditions.checkNonNegative("exponent", k);
        if (!(b >= -2) || !(b <= 2)) {
            long accum = 1;
            while (true) {
                switch (k) {
                    case 0:
                        return accum;
                    case 1:
                        return checkedMultiply(accum, b);
                    default:
                        if ((k & 1) != 0) {
                            accum = checkedMultiply(accum, b);
                        }
                        k >>= 1;
                        if (k > 0) {
                            MathPreconditions.checkNoOverflow(-3037000499L <= b && b <= FLOOR_SQRT_MAX_LONG, "checkedPow", b, (long) k);
                            b *= b;
                        }
                        break;
                }
            }
        } else {
            switch ((int) b) {
                case -2:
                    MathPreconditions.checkNoOverflow(k < 64, "checkedPow", b, (long) k);
                    return (k & 1) == 0 ? 1 << k : -1 << k;
                case -1:
                    if ((k & 1) == 0) {
                        return 1;
                    }
                    return -1;
                case 0:
                    if (k == 0) {
                        return 1;
                    }
                    return 0;
                case 1:
                    return 1;
                case 2:
                    MathPreconditions.checkNoOverflow(k < 63, "checkedPow", b, (long) k);
                    return 1 << k;
                default:
                    throw new AssertionError();
            }
        }
    }

    public static long saturatedAdd(long a, long b) {
        long naiveSum = a + b;
        boolean z = true;
        boolean z2 = (a ^ b) < 0;
        if ((a ^ naiveSum) < 0) {
            z = false;
        }
        if (z2 || z) {
            return naiveSum;
        }
        return ((naiveSum >>> 63) ^ 1) + Long.MAX_VALUE;
    }

    public static long saturatedSubtract(long a, long b) {
        long naiveDifference = a - b;
        boolean z = true;
        boolean z2 = (a ^ b) >= 0;
        if ((a ^ naiveDifference) < 0) {
            z = false;
        }
        if (z2 || z) {
            return naiveDifference;
        }
        return ((naiveDifference >>> 63) ^ 1) + Long.MAX_VALUE;
    }

    public static long saturatedMultiply(long a, long b) {
        int leadingZeros = Long.numberOfLeadingZeros(a) + Long.numberOfLeadingZeros(~a) + Long.numberOfLeadingZeros(b) + Long.numberOfLeadingZeros(~b);
        if (leadingZeros > 65) {
            return a * b;
        }
        long limit = ((a ^ b) >>> 63) + Long.MAX_VALUE;
        boolean z = true;
        boolean z2 = leadingZeros < 64;
        boolean z3 = a < 0;
        if (b != Long.MIN_VALUE) {
            z = false;
        }
        if (z2 || (z & z3)) {
            return limit;
        }
        long result = a * b;
        if (a == 0 || result / a == b) {
            return result;
        }
        return limit;
    }

    public static long saturatedPow(long b, int k) {
        MathPreconditions.checkNonNegative("exponent", k);
        if ((b >= -2) && (b <= 2)) {
            switch ((int) b) {
                case -2:
                    if (k >= 64) {
                        return ((long) (k & 1)) + Long.MAX_VALUE;
                    }
                    return (k & 1) == 0 ? 1 << k : -1 << k;
                case -1:
                    if ((k & 1) == 0) {
                        return 1;
                    }
                    return -1;
                case 0:
                    if (k == 0) {
                        return 1;
                    }
                    return 0;
                case 1:
                    return 1;
                case 2:
                    if (k >= 63) {
                        return Long.MAX_VALUE;
                    }
                    return 1 << k;
                default:
                    throw new AssertionError();
            }
        } else {
            long accum = 1;
            long limit = ((b >>> 63) & ((long) (k & 1))) + Long.MAX_VALUE;
            while (true) {
                switch (k) {
                    case 0:
                        return accum;
                    case 1:
                        return saturatedMultiply(accum, b);
                    default:
                        if ((k & 1) != 0) {
                            accum = saturatedMultiply(accum, b);
                        }
                        k >>= 1;
                        if (k > 0) {
                            if ((-3037000499L > b) || (b > FLOOR_SQRT_MAX_LONG)) {
                                return limit;
                            }
                            b *= b;
                        }
                }
            }
        }
    }

    public static long factorial(int n) {
        MathPreconditions.checkNonNegative("n", n);
        long[] jArr = factorials;
        if (n < jArr.length) {
            return jArr[n];
        }
        return Long.MAX_VALUE;
    }

    public static long binomial(int n, int k) {
        int numeratorBits;
        long result;
        int numeratorBits2;
        int i = n;
        int k2 = k;
        MathPreconditions.checkNonNegative("n", i);
        MathPreconditions.checkNonNegative("k", k2);
        Preconditions.checkArgument(k2 <= i, "k (%s) > n (%s)", k2, i);
        if (k2 > (i >> 1)) {
            k2 = i - k2;
        }
        switch (k2) {
            case 0:
                return 1;
            case 1:
                return (long) i;
            default:
                long[] jArr = factorials;
                if (i < jArr.length) {
                    return jArr[i] / (jArr[k2] * jArr[i - k2]);
                }
                int[] iArr = biggestBinomials;
                if (k2 >= iArr.length || i > iArr[k2]) {
                    return Long.MAX_VALUE;
                }
                int[] iArr2 = biggestSimpleBinomials;
                if (k2 >= iArr2.length || i > iArr2[k2]) {
                    int nBits = log2((long) i, RoundingMode.CEILING);
                    int n2 = i - 1;
                    long numerator = (long) i;
                    int numeratorBits3 = nBits;
                    int n3 = n2;
                    long numerator2 = numerator;
                    long denominator = 1;
                    int i2 = 2;
                    long result2 = 1;
                    while (i2 <= k2) {
                        if (numeratorBits3 + nBits < 63) {
                            numerator2 *= (long) n3;
                            denominator *= (long) i2;
                            result = result2;
                            numeratorBits = numeratorBits3 + nBits;
                            numeratorBits2 = i2;
                        } else {
                            int i3 = numeratorBits3;
                            numeratorBits2 = i2;
                            result = multiplyFraction(result2, numerator2, denominator);
                            numeratorBits = nBits;
                            numerator2 = (long) n3;
                            denominator = (long) numeratorBits2;
                        }
                        i2 = numeratorBits2 + 1;
                        n3--;
                        numeratorBits3 = numeratorBits;
                        result2 = result;
                    }
                    int i4 = numeratorBits3;
                    int numeratorBits4 = i2;
                    return multiplyFraction(result2, numerator2, denominator);
                }
                int n4 = i - 1;
                long result3 = (long) i;
                for (int i5 = 2; i5 <= k2; i5++) {
                    result3 = (result3 * ((long) n4)) / ((long) i5);
                    n4--;
                }
                return result3;
        }
    }

    static long multiplyFraction(long x, long numerator, long denominator) {
        if (x == 1) {
            return numerator / denominator;
        }
        long commonDivisor = gcd(x, denominator);
        return (numerator / (denominator / commonDivisor)) * (x / commonDivisor);
    }

    static boolean fitsInInt(long x) {
        return ((long) ((int) x)) == x;
    }

    public static long mean(long x, long y) {
        return (x & y) + ((x ^ y) >> 1);
    }

    public static boolean isPrime(long n) {
        if (n < 2) {
            MathPreconditions.checkNonNegative("n", n);
            return false;
        } else if (n < 66) {
            if (((722865708377213483 >> (((int) n) - 2)) & 1) != 0) {
                return true;
            }
            return false;
        } else if ((SIEVE_30 & (1 << ((int) (n % 30)))) != 0 || n % 7 == 0 || n % 11 == 0 || n % 13 == 0) {
            return false;
        } else {
            if (n < 289) {
                return true;
            }
            for (long[] baseSet : millerRabinBaseSets) {
                if (n <= baseSet[0]) {
                    for (int i = 1; i < baseSet.length; i++) {
                        if (!MillerRabinTester.test(baseSet[i], n)) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            throw new AssertionError();
        }
    }

    private enum MillerRabinTester {
        SMALL {
            /* access modifiers changed from: package-private */
            public long mulMod(long a, long b, long m) {
                return (a * b) % m;
            }

            /* access modifiers changed from: package-private */
            public long squareMod(long a, long m) {
                return (a * a) % m;
            }
        },
        LARGE {
            private long plusMod(long a, long b, long m) {
                return a >= m - b ? (a + b) - m : a + b;
            }

            private long times2ToThe32Mod(long a, long m) {
                int remainingPowersOf2 = 32;
                do {
                    int shift = Math.min(remainingPowersOf2, Long.numberOfLeadingZeros(a));
                    a = UnsignedLongs.remainder(a << shift, m);
                    remainingPowersOf2 -= shift;
                } while (remainingPowersOf2 > 0);
                return a;
            }

            /* access modifiers changed from: package-private */
            public long mulMod(long a, long b, long m) {
                long j = m;
                long aHi = a >>> 32;
                long bHi = b >>> 32;
                long aLo = a & 4294967295L;
                long bLo = b & 4294967295L;
                long result = times2ToThe32Mod(aHi * bHi, j) + (aHi * bLo);
                if (result < 0) {
                    result = UnsignedLongs.remainder(result, j);
                }
                return plusMod(times2ToThe32Mod(result + (aLo * bHi), j), UnsignedLongs.remainder(aLo * bLo, j), m);
            }

            /* access modifiers changed from: package-private */
            public long squareMod(long a, long m) {
                long hiLo;
                long j = m;
                long aHi = a >>> 32;
                long aLo = a & 4294967295L;
                long result = times2ToThe32Mod(aHi * aHi, j);
                long hiLo2 = aHi * aLo * 2;
                if (hiLo2 < 0) {
                    hiLo = UnsignedLongs.remainder(hiLo2, j);
                } else {
                    hiLo = hiLo2;
                }
                return plusMod(times2ToThe32Mod(result + hiLo, j), UnsignedLongs.remainder(aLo * aLo, j), m);
            }
        };

        /* access modifiers changed from: package-private */
        public abstract long mulMod(long j, long j2, long j3);

        /* access modifiers changed from: package-private */
        public abstract long squareMod(long j, long j2);

        static boolean test(long base, long n) {
            return (n <= LongMath.FLOOR_SQRT_MAX_LONG ? SMALL : LARGE).testWitness(base, n);
        }

        private long powMod(long a, long p, long m) {
            long res = 1;
            while (p != 0) {
                if ((1 & p) != 0) {
                    res = mulMod(res, a, m);
                }
                a = squareMod(a, m);
                p >>= 1;
            }
            return res;
        }

        private boolean testWitness(long base, long n) {
            long j = n;
            int r = Long.numberOfTrailingZeros(j - 1);
            long d = (j - 1) >> r;
            long base2 = base % j;
            if (base2 == 0) {
                return true;
            }
            long a = powMod(base2, d, n);
            if (a == 1) {
                return true;
            }
            int j2 = 0;
            while (a != j - 1) {
                j2++;
                if (j2 == r) {
                    return false;
                }
                a = squareMod(a, j);
            }
            return true;
        }
    }

    public static double roundToDouble(long x, RoundingMode mode) {
        int cmpXToRoundArbitrarily;
        double roundCeilingAsDouble;
        double roundFloorAsDouble;
        long roundCeiling;
        long roundFloor;
        long roundFloor2;
        long j = x;
        double roundArbitrarily = (double) j;
        long roundArbitrarilyAsLong = (long) roundArbitrarily;
        if (roundArbitrarilyAsLong == Long.MAX_VALUE) {
            cmpXToRoundArbitrarily = -1;
        } else {
            cmpXToRoundArbitrarily = Longs.compare(j, roundArbitrarilyAsLong);
        }
        switch (C04601.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(cmpXToRoundArbitrarily == 0);
                return roundArbitrarily;
            case 2:
                if (j < 0) {
                    return cmpXToRoundArbitrarily <= 0 ? roundArbitrarily : Math.nextUp(roundArbitrarily);
                }
                if (cmpXToRoundArbitrarily >= 0) {
                    return roundArbitrarily;
                }
                return DoubleUtils.nextDown(roundArbitrarily);
            case 3:
                if (cmpXToRoundArbitrarily >= 0) {
                    return roundArbitrarily;
                }
                return DoubleUtils.nextDown(roundArbitrarily);
            case 4:
                if (j >= 0) {
                    return cmpXToRoundArbitrarily <= 0 ? roundArbitrarily : Math.nextUp(roundArbitrarily);
                }
                if (cmpXToRoundArbitrarily >= 0) {
                    return roundArbitrarily;
                }
                return DoubleUtils.nextDown(roundArbitrarily);
            case 5:
                return cmpXToRoundArbitrarily <= 0 ? roundArbitrarily : Math.nextUp(roundArbitrarily);
            case 6:
            case 7:
            case 8:
                if (cmpXToRoundArbitrarily >= 0) {
                    roundFloorAsDouble = roundArbitrarily;
                    roundCeilingAsDouble = Math.nextUp(roundArbitrarily);
                    roundCeiling = (long) Math.ceil(roundCeilingAsDouble);
                    roundFloor = roundArbitrarilyAsLong;
                } else {
                    roundCeilingAsDouble = roundArbitrarily;
                    roundCeiling = roundArbitrarilyAsLong;
                    roundFloorAsDouble = DoubleUtils.nextDown(roundArbitrarily);
                    roundFloor = (long) Math.floor(roundFloorAsDouble);
                }
                long j2 = roundArbitrarilyAsLong;
                long roundArbitrarilyAsLong2 = j - roundFloor;
                long deltaToCeiling = roundCeiling - j;
                if (roundCeiling == Long.MAX_VALUE) {
                    long j3 = roundFloor;
                    roundFloor2 = deltaToCeiling + 1;
                } else {
                    long j4 = roundFloor;
                    roundFloor2 = deltaToCeiling;
                }
                int diff = Longs.compare(roundArbitrarilyAsLong2, roundFloor2);
                if (diff < 0) {
                    return roundFloorAsDouble;
                }
                if (diff > 0) {
                    return roundCeilingAsDouble;
                }
                switch (C04601.$SwitchMap$java$math$RoundingMode[mode.ordinal()]) {
                    case 6:
                        return j >= 0 ? roundFloorAsDouble : roundCeilingAsDouble;
                    case 7:
                        return j >= 0 ? roundCeilingAsDouble : roundFloorAsDouble;
                    case 8:
                        if ((DoubleUtils.getSignificand(roundFloorAsDouble) & 1) == 0) {
                            return roundFloorAsDouble;
                        }
                        return roundCeilingAsDouble;
                    default:
                        throw new AssertionError("impossible");
                }
            default:
                long j5 = roundArbitrarilyAsLong;
                throw new AssertionError("impossible");
        }
    }

    private LongMath() {
    }
}
