package com.android.volley.toolbox;

import com.android.volley.Cache;

public class NoCache implements Cache {
    public void clear() {
    }

    public Cache.Entry get(String key) {
        return null;
    }

    public void put(String key, Cache.Entry entry) {
    }

    public void invalidate(String key, boolean fullExpire) {
    }

    public void remove(String key) {
    }

    public void initialize() {
    }
}
