package com.google.firebase.firestore;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.firestore.remote.FirebaseClientGrpcMetadataProvider;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.util.Arrays;
import java.util.List;

public class FirestoreRegistrar implements ComponentRegistrar {
    public List<Component<?>> getComponents() {
        return Arrays.asList(new Component[]{Component.builder(FirestoreMultiDbComponent.class).add(Dependency.required(FirebaseApp.class)).add(Dependency.required(Context.class)).add(Dependency.optionalProvider(HeartBeatInfo.class)).add(Dependency.optionalProvider(UserAgentPublisher.class)).add(Dependency.deferred(InternalAuthProvider.class)).add(Dependency.deferred(InternalAppCheckTokenProvider.class)).add(Dependency.optional(FirebaseOptions.class)).factory(FirestoreRegistrar$$ExternalSyntheticLambda0.INSTANCE).build(), LibraryVersionComponent.create("fire-fst", BuildConfig.VERSION_NAME)});
    }

    static /* synthetic */ FirestoreMultiDbComponent lambda$getComponents$0(ComponentContainer c) {
        return new FirestoreMultiDbComponent((Context) c.get(Context.class), (FirebaseApp) c.get(FirebaseApp.class), c.getDeferred(InternalAuthProvider.class), c.getDeferred(InternalAppCheckTokenProvider.class), new FirebaseClientGrpcMetadataProvider(c.getProvider(UserAgentPublisher.class), c.getProvider(HeartBeatInfo.class), (FirebaseOptions) c.get(FirebaseOptions.class)));
    }
}
