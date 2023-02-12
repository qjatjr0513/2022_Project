package com.android.volley.toolbox;

import com.android.volley.AsyncCache;
import com.android.volley.Cache;

public class NoAsyncCache extends AsyncCache {
    public void get(String key, AsyncCache.OnGetCompleteCallback callback) {
        callback.onGetComplete((Cache.Entry) null);
    }

    public void put(String key, Cache.Entry entry, AsyncCache.OnWriteCompleteCallback callback) {
        callback.onWriteComplete();
    }

    public void clear(AsyncCache.OnWriteCompleteCallback callback) {
        callback.onWriteComplete();
    }

    public void initialize(AsyncCache.OnWriteCompleteCallback callback) {
        callback.onWriteComplete();
    }

    public void invalidate(String key, boolean fullExpire, AsyncCache.OnWriteCompleteCallback callback) {
        callback.onWriteComplete();
    }

    public void remove(String key, AsyncCache.OnWriteCompleteCallback callback) {
        callback.onWriteComplete();
    }
}
