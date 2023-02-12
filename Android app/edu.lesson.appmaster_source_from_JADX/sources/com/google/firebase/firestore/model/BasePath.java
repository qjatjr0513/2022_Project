package com.google.firebase.firestore.model;

import com.google.firebase.firestore.model.BasePath;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayList;
import java.util.List;

public abstract class BasePath<B extends BasePath<B>> implements Comparable<B> {
    final List<String> segments;

    public abstract String canonicalString();

    /* access modifiers changed from: package-private */
    public abstract B createPathWithSegments(List<String> list);

    BasePath(List<String> segments2) {
        this.segments = segments2;
    }

    public String getSegment(int index) {
        return this.segments.get(index);
    }

    public B append(String segment) {
        List<String> newPath = new ArrayList<>(this.segments);
        newPath.add(segment);
        return createPathWithSegments(newPath);
    }

    public B append(B path) {
        List<String> newPath = new ArrayList<>(this.segments);
        newPath.addAll(path.segments);
        return createPathWithSegments(newPath);
    }

    public B popFirst() {
        return popFirst(1);
    }

    public B popFirst(int count) {
        int length = length();
        Assert.hardAssert(length >= count, "Can't call popFirst with count > length() (%d > %d)", Integer.valueOf(count), Integer.valueOf(length));
        return createPathWithSegments(this.segments.subList(count, length));
    }

    public B popLast() {
        return createPathWithSegments(this.segments.subList(0, length() - 1));
    }

    public B keepFirst(int count) {
        return createPathWithSegments(this.segments.subList(0, count));
    }

    public int compareTo(B o) {
        int i = 0;
        int myLength = length();
        int theirLength = o.length();
        while (i < myLength && i < theirLength) {
            int localCompare = getSegment(i).compareTo(o.getSegment(i));
            if (localCompare != 0) {
                return localCompare;
            }
            i++;
        }
        return Util.compareIntegers(myLength, theirLength);
    }

    public String getLastSegment() {
        return this.segments.get(length() - 1);
    }

    public String getFirstSegment() {
        return this.segments.get(0);
    }

    public boolean isEmpty() {
        return length() == 0;
    }

    public boolean isPrefixOf(B path) {
        if (length() > path.length()) {
            return false;
        }
        for (int i = 0; i < length(); i++) {
            if (!getSegment(i).equals(path.getSegment(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isImmediateParentOf(B potentialChild) {
        if (length() + 1 != potentialChild.length()) {
            return false;
        }
        for (int i = 0; i < length(); i++) {
            if (!getSegment(i).equals(potentialChild.getSegment(i))) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return canonicalString();
    }

    public int length() {
        return this.segments.size();
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BasePath) || compareTo((BasePath) o) != 0) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (37 * ((37 * 1) + getClass().hashCode())) + this.segments.hashCode();
    }
}
