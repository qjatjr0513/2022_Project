package com.google.common.base;

import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class Objects extends ExtraObjectsMethodsForWeb {
    private Objects() {
    }

    public static boolean equal(@NullableDecl Object a, @NullableDecl Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static int hashCode(@NullableDecl Object... objects) {
        return Arrays.hashCode(objects);
    }
}
