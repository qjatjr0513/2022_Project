package com.google.common.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

final class Java8Usage {

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    private @interface SomeTypeAnnotation {
    }

    static /* synthetic */ void lambda$performCheck$0() {
    }

    static String performCheck() {
        Java8Usage$$ExternalSyntheticLambda0.INSTANCE.run();
        return "";
    }

    private Java8Usage() {
    }
}
