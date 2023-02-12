package com.google.firebase.components;

public final class Dependency {
    private final Class<?> anInterface;
    private final int injection;
    private final int type;

    private Dependency(Class<?> anInterface2, int type2, int injection2) {
        this.anInterface = (Class) Preconditions.checkNotNull(anInterface2, "Null dependency anInterface.");
        this.type = type2;
        this.injection = injection2;
    }

    @Deprecated
    public static Dependency optional(Class<?> anInterface2) {
        return new Dependency(anInterface2, 0, 0);
    }

    public static Dependency deferred(Class<?> anInterface2) {
        return new Dependency(anInterface2, 0, 2);
    }

    public static Dependency required(Class<?> anInterface2) {
        return new Dependency(anInterface2, 1, 0);
    }

    public static Dependency setOf(Class<?> anInterface2) {
        return new Dependency(anInterface2, 2, 0);
    }

    public static Dependency optionalProvider(Class<?> anInterface2) {
        return new Dependency(anInterface2, 0, 1);
    }

    public static Dependency requiredProvider(Class<?> anInterface2) {
        return new Dependency(anInterface2, 1, 1);
    }

    public static Dependency setOfProvider(Class<?> anInterface2) {
        return new Dependency(anInterface2, 2, 1);
    }

    public Class<?> getInterface() {
        return this.anInterface;
    }

    public boolean isRequired() {
        return this.type == 1;
    }

    public boolean isSet() {
        return this.type == 2;
    }

    public boolean isDirectInjection() {
        return this.injection == 0;
    }

    public boolean isDeferred() {
        return this.injection == 2;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Dependency)) {
            return false;
        }
        Dependency other = (Dependency) o;
        if (this.anInterface == other.anInterface && this.type == other.type && this.injection == other.injection) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((1000003 ^ this.anInterface.hashCode()) * 1000003) ^ this.type) * 1000003) ^ this.injection;
    }

    public String toString() {
        StringBuilder append = new StringBuilder("Dependency{anInterface=").append(this.anInterface).append(", type=");
        int i = this.type;
        return append.append(i == 1 ? "required" : i == 0 ? "optional" : "set").append(", injection=").append(describeInjection(this.injection)).append("}").toString();
    }

    private static String describeInjection(int injection2) {
        switch (injection2) {
            case 0:
                return "direct";
            case 1:
                return "provider";
            case 2:
                return "deferred";
            default:
                throw new AssertionError("Unsupported injection: " + injection2);
        }
    }
}
