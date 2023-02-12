package com.google.firebase.platforminfo;

import android.content.Context;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.platforminfo.LibraryVersionComponent;

public final /* synthetic */ class LibraryVersionComponent$$ExternalSyntheticLambda0 implements ComponentFactory {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ LibraryVersionComponent.VersionExtractor f$1;

    public /* synthetic */ LibraryVersionComponent$$ExternalSyntheticLambda0(String str, LibraryVersionComponent.VersionExtractor versionExtractor) {
        this.f$0 = str;
        this.f$1 = versionExtractor;
    }

    public final Object create(ComponentContainer componentContainer) {
        return LibraryVersion.create(this.f$0, this.f$1.extract((Context) componentContainer.get(Context.class)));
    }
}
