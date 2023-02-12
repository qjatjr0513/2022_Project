package com.google.android.datatransport;

public final class Encoding {
    private final String name;

    /* renamed from: of */
    public static Encoding m395of(String name2) {
        return new Encoding(name2);
    }

    public String getName() {
        return this.name;
    }

    private Encoding(String name2) {
        if (name2 != null) {
            this.name = name2;
            return;
        }
        throw new NullPointerException("name is null");
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Encoding)) {
            return false;
        }
        return this.name.equals(((Encoding) o).name);
    }

    public int hashCode() {
        return 1000003 ^ this.name.hashCode();
    }

    public String toString() {
        return "Encoding{name=\"" + this.name + "\"}";
    }
}
