package com.google.firebase.firestore.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FieldPath extends BasePath<FieldPath> {
    public static final FieldPath EMPTY_PATH = new FieldPath(Collections.emptyList());
    public static final FieldPath KEY_PATH = fromSingleSegment(DocumentKey.KEY_FIELD_NAME);

    private FieldPath(List<String> segments) {
        super(segments);
    }

    public static FieldPath fromSingleSegment(String fieldName) {
        return new FieldPath(Collections.singletonList(fieldName));
    }

    public static FieldPath fromSegments(List<String> segments) {
        return segments.isEmpty() ? EMPTY_PATH : new FieldPath(segments);
    }

    /* access modifiers changed from: package-private */
    public FieldPath createPathWithSegments(List<String> segments) {
        return new FieldPath(segments);
    }

    public static FieldPath fromServerFormat(String path) {
        List<String> res = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        int i = 0;
        boolean inBackticks = false;
        while (i < path.length()) {
            char c = path.charAt(i);
            if (c == '\\') {
                if (i + 1 != path.length()) {
                    i++;
                    builder.append(path.charAt(i));
                } else {
                    throw new IllegalArgumentException("Trailing escape character is not allowed");
                }
            } else if (c == '.') {
                if (!inBackticks) {
                    String elem = builder.toString();
                    if (!elem.isEmpty()) {
                        builder = new StringBuilder();
                        res.add(elem);
                    } else {
                        throw new IllegalArgumentException("Invalid field path (" + path + "). Paths must not be empty, begin with '.', end with '.', or contain '..'");
                    }
                } else {
                    builder.append(c);
                }
            } else if (c == '`') {
                inBackticks = !inBackticks;
            } else {
                builder.append(c);
            }
            i++;
        }
        String lastElem = builder.toString();
        if (!lastElem.isEmpty()) {
            res.add(lastElem);
            return new FieldPath(res);
        }
        throw new IllegalArgumentException("Invalid field path (" + path + "). Paths must not be empty, begin with '.', end with '.', or contain '..'");
    }

    private static boolean isValidIdentifier(String identifier) {
        if (identifier.isEmpty()) {
            return false;
        }
        char first = identifier.charAt(0);
        if (first != '_' && ((first < 'a' || first > 'z') && (first < 'A' || first > 'Z'))) {
            return false;
        }
        for (int i = 1; i < identifier.length(); i++) {
            char c = identifier.charAt(i);
            if (c != '_' && ((c < 'a' || c > 'z') && ((c < 'A' || c > 'Z') && (c < '0' || c > '9')))) {
                return false;
            }
        }
        return true;
    }

    public String canonicalString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.segments.size(); i++) {
            if (i > 0) {
                builder.append(".");
            }
            String escaped = ((String) this.segments.get(i)).replace("\\", "\\\\").replace("`", "\\`");
            if (!isValidIdentifier(escaped)) {
                escaped = '`' + escaped + '`';
            }
            builder.append(escaped);
        }
        return builder.toString();
    }

    public boolean isKeyField() {
        return equals(KEY_PATH);
    }
}
