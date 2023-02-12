package com.google.firebase.firestore.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ResourcePath extends BasePath<ResourcePath> {
    public static final ResourcePath EMPTY = new ResourcePath(Collections.emptyList());

    private ResourcePath(List<String> segments) {
        super(segments);
    }

    /* access modifiers changed from: package-private */
    public ResourcePath createPathWithSegments(List<String> segments) {
        return new ResourcePath(segments);
    }

    public static ResourcePath fromSegments(List<String> segments) {
        return segments.isEmpty() ? EMPTY : new ResourcePath(segments);
    }

    public static ResourcePath fromString(String path) {
        if (!path.contains("//")) {
            String[] rawSegments = path.split("/");
            ArrayList<String> segments = new ArrayList<>(rawSegments.length);
            for (String segment : rawSegments) {
                if (!segment.isEmpty()) {
                    segments.add(segment);
                }
            }
            return new ResourcePath(segments);
        }
        throw new IllegalArgumentException("Invalid path (" + path + "). Paths must not contain // in them.");
    }

    public String canonicalString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.segments.size(); i++) {
            if (i > 0) {
                builder.append("/");
            }
            builder.append((String) this.segments.get(i));
        }
        return builder.toString();
    }
}
