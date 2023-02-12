package com.google.firebase.database.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GAuthToken {
    private static final String AUTH_KEY = "auth";
    private static final String TOKEN_KEY = "token";
    private static final String TOKEN_PREFIX = "gauth|";
    private final Map<String, Object> auth;
    private final String token;

    public GAuthToken(String token2, Map<String, Object> auth2) {
        this.token = token2;
        this.auth = auth2;
    }

    public static GAuthToken tryParseFromString(String rawToken) {
        if (!rawToken.startsWith(TOKEN_PREFIX)) {
            return null;
        }
        try {
            Map<String, Object> tokenMap = JsonMapper.parseJson(rawToken.substring(TOKEN_PREFIX.length()));
            return new GAuthToken((String) tokenMap.get(TOKEN_KEY), (Map) tokenMap.get(AUTH_KEY));
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse gauth token", e);
        }
    }

    public String serializeToString() {
        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put(TOKEN_KEY, this.token);
        tokenMap.put(AUTH_KEY, this.auth);
        try {
            return TOKEN_PREFIX + JsonMapper.serializeJson(tokenMap);
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize gauth token", e);
        }
    }

    public String getToken() {
        return this.token;
    }

    public Map<String, Object> getAuth() {
        return this.auth;
    }
}
