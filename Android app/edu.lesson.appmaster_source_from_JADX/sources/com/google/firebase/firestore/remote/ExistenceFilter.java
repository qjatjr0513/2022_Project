package com.google.firebase.firestore.remote;

public final class ExistenceFilter {
    private final int count;

    public ExistenceFilter(int count2) {
        this.count = count2;
    }

    public int getCount() {
        return this.count;
    }

    public String toString() {
        return "ExistenceFilter{count=" + this.count + '}';
    }
}
