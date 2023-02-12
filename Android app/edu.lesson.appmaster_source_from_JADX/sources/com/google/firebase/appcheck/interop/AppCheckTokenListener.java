package com.google.firebase.appcheck.interop;

import com.google.firebase.appcheck.AppCheckTokenResult;

public interface AppCheckTokenListener {
    void onAppCheckTokenChanged(AppCheckTokenResult appCheckTokenResult);
}
