package com.google.common.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalMath {
    private BigDecimalMath() {
    }

    public static double roundToDouble(BigDecimal x, RoundingMode mode) {
        return BigDecimalToDoubleRounder.INSTANCE.roundToDouble(x, mode);
    }

    private static class BigDecimalToDoubleRounder extends ToDoubleRounder<BigDecimal> {
        static final BigDecimalToDoubleRounder INSTANCE = new BigDecimalToDoubleRounder();

        private BigDecimalToDoubleRounder() {
        }

        /* access modifiers changed from: package-private */
        public double roundToDoubleArbitrarily(BigDecimal bigDecimal) {
            return bigDecimal.doubleValue();
        }

        /* access modifiers changed from: package-private */
        public int sign(BigDecimal bigDecimal) {
            return bigDecimal.signum();
        }

        /* access modifiers changed from: package-private */
        public BigDecimal toX(double d, RoundingMode mode) {
            return new BigDecimal(d);
        }

        /* access modifiers changed from: package-private */
        public BigDecimal minus(BigDecimal a, BigDecimal b) {
            return a.subtract(b);
        }
    }
}
