package com.google.firebase.encoders;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class FieldDescriptor {
    private final String name;
    private final Map<Class<?>, Object> properties;

    private FieldDescriptor(String name2, Map<Class<?>, Object> properties2) {
        this.name = name2;
        this.properties = properties2;
    }

    public String getName() {
        return this.name;
    }

    public <T extends Annotation> T getProperty(Class<T> type) {
        return (Annotation) this.properties.get(type);
    }

    /* renamed from: of */
    public static FieldDescriptor m195of(String name2) {
        return new FieldDescriptor(name2, Collections.emptyMap());
    }

    public static Builder builder(String name2) {
        return new Builder(name2);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FieldDescriptor)) {
            return false;
        }
        FieldDescriptor that = (FieldDescriptor) o;
        if (!this.name.equals(that.name) || !this.properties.equals(that.properties)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.properties.hashCode();
    }

    public String toString() {
        return "FieldDescriptor{name=" + this.name + ", properties=" + this.properties.values() + "}";
    }

    public static final class Builder {
        private final String name;
        private Map<Class<?>, Object> properties = null;

        Builder(String name2) {
            this.name = name2;
        }

        public <T extends Annotation> Builder withProperty(T value) {
            if (this.properties == null) {
                this.properties = new HashMap();
            }
            this.properties.put(value.annotationType(), value);
            return this;
        }

        public FieldDescriptor build() {
            Map map;
            String str = this.name;
            if (this.properties == null) {
                map = Collections.emptyMap();
            } else {
                map = Collections.unmodifiableMap(new HashMap(this.properties));
            }
            return new FieldDescriptor(str, map);
        }
    }
}
