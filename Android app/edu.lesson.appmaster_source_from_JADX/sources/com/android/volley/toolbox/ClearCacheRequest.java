package com.android.volley.toolbox;

import android.os.Handler;
import android.os.Looper;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

public class ClearCacheRequest extends Request<Object> {
    private final Cache mCache;
    private final Runnable mCallback;

    public ClearCacheRequest(Cache cache, Runnable callback) {
        super(0, (String) null, (Response.ErrorListener) null);
        this.mCache = cache;
        this.mCallback = callback;
    }

    public boolean isCanceled() {
        this.mCache.clear();
        if (this.mCallback == null) {
            return true;
        }
        new Handler(Looper.getMainLooper()).postAtFrontOfQueue(this.mCallback);
        return true;
    }

    public Request.Priority getPriority() {
        return Request.Priority.IMMEDIATE;
    }

    /* access modifiers changed from: protected */
    public Response<Object> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    /* access modifiers changed from: protected */
    public void deliverResponse(Object response) {
    }
}
