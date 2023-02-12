package com.google.firebase.database.android;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.core.TokenProvider;

public final /* synthetic */ class AndroidAuthTokenProvider$$ExternalSyntheticLambda1 implements OnSuccessListener {
    public final /* synthetic */ TokenProvider.GetTokenCompletionListener f$0;

    public /* synthetic */ AndroidAuthTokenProvider$$ExternalSyntheticLambda1(TokenProvider.GetTokenCompletionListener getTokenCompletionListener) {
        this.f$0 = getTokenCompletionListener;
    }

    public final void onSuccess(Object obj) {
        this.f$0.onSuccess(((GetTokenResult) obj).getToken());
    }
}
