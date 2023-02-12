package com.google.firebase.database.core.utilities;

public final class Pair<T, U> {
    private final T first;
    private final U second;

    public Pair(T first2, U second2) {
        this.first = first2;
        this.second = second2;
    }

    public T getFirst() {
        return this.first;
    }

    public U getSecond() {
        return this.second;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair pair = (Pair) o;
        T t = this.first;
        if (t == null ? pair.first != null : !t.equals(pair.first)) {
            return false;
        }
        U u = this.second;
        if (u == null ? pair.second == null : u.equals(pair.second)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        T t = this.first;
        int i = 0;
        int hashCode = (t != null ? t.hashCode() : 0) * 31;
        U u = this.second;
        if (u != null) {
            i = u.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "Pair(" + this.first + "," + this.second + ")";
    }
}
