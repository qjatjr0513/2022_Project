package com.squareup.okhttp.internal.http;

import com.android.volley.toolbox.HttpClientStack;
import p004io.grpc.internal.GrpcUtil;

public final class HttpMethod {
    public static boolean invalidatesCache(String method) {
        return method.equals(GrpcUtil.HTTP_METHOD) || method.equals(HttpClientStack.HttpPatch.METHOD_NAME) || method.equals("PUT") || method.equals("DELETE") || method.equals("MOVE");
    }

    public static boolean requiresRequestBody(String method) {
        return method.equals(GrpcUtil.HTTP_METHOD) || method.equals("PUT") || method.equals(HttpClientStack.HttpPatch.METHOD_NAME) || method.equals("PROPPATCH") || method.equals("REPORT");
    }

    public static boolean permitsRequestBody(String method) {
        return requiresRequestBody(method) || method.equals("OPTIONS") || method.equals("DELETE") || method.equals("PROPFIND") || method.equals("MKCOL") || method.equals("LOCK");
    }

    public static boolean redirectsToGet(String method) {
        return !method.equals("PROPFIND");
    }

    private HttpMethod() {
    }
}
