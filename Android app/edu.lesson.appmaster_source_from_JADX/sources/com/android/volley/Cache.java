package com.android.volley;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface Cache {
    void clear();

    Entry get(String str);

    void initialize();

    void invalidate(String str, boolean z);

    void put(String str, Entry entry);

    void remove(String str);

    public static class Entry {
        public List<Header> allResponseHeaders;
        public byte[] data;
        public String etag;
        public long lastModified;
        public Map<String, String> responseHeaders = Collections.emptyMap();
        public long serverDate;
        public long softTtl;
        public long ttl;

        public boolean isExpired() {
            return isExpired(System.currentTimeMillis());
        }

        /* access modifiers changed from: package-private */
        public boolean isExpired(long currentTimeMillis) {
            return this.ttl < currentTimeMillis;
        }

        public boolean refreshNeeded() {
            return refreshNeeded(System.currentTimeMillis());
        }

        /* access modifiers changed from: package-private */
        public boolean refreshNeeded(long currentTimeMillis) {
            return this.softTtl < currentTimeMillis;
        }
    }
}
