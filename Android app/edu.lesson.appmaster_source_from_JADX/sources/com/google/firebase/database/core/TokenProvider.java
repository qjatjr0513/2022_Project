package com.google.firebase.database.core;

import java.util.concurrent.ExecutorService;

public interface TokenProvider {

    public interface GetTokenCompletionListener {
        void onError(String str);

        void onSuccess(String str);
    }

    public interface TokenChangeListener {
        void onTokenChange();

        void onTokenChange(String str);
    }

    void addTokenChangeListener(ExecutorService executorService, TokenChangeListener tokenChangeListener);

    void getToken(boolean z, GetTokenCompletionListener getTokenCompletionListener);

    void removeTokenChangeListener(TokenChangeListener tokenChangeListener);
}
