package com.google.firebase.platforminfo;

import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.Dependency;
import java.util.Iterator;
import java.util.Set;

public class DefaultUserAgentPublisher implements UserAgentPublisher {
    private final GlobalLibraryVersionRegistrar gamesSDKRegistrar;
    private final String javaSDKVersionUserAgent;

    DefaultUserAgentPublisher(Set<LibraryVersion> libraryVersions, GlobalLibraryVersionRegistrar gamesSDKRegistrar2) {
        this.javaSDKVersionUserAgent = toUserAgent(libraryVersions);
        this.gamesSDKRegistrar = gamesSDKRegistrar2;
    }

    public String getUserAgent() {
        if (this.gamesSDKRegistrar.getRegisteredVersions().isEmpty()) {
            return this.javaSDKVersionUserAgent;
        }
        return this.javaSDKVersionUserAgent + ' ' + toUserAgent(this.gamesSDKRegistrar.getRegisteredVersions());
    }

    private static String toUserAgent(Set<LibraryVersion> tokens) {
        StringBuilder sb = new StringBuilder();
        Iterator<LibraryVersion> iterator = tokens.iterator();
        while (iterator.hasNext()) {
            LibraryVersion token = iterator.next();
            sb.append(token.getLibraryName()).append('/').append(token.getVersion());
            if (iterator.hasNext()) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    public static Component<UserAgentPublisher> component() {
        return Component.builder(UserAgentPublisher.class).add(Dependency.setOf(LibraryVersion.class)).factory(DefaultUserAgentPublisher$$ExternalSyntheticLambda0.INSTANCE).build();
    }

    static /* synthetic */ UserAgentPublisher lambda$component$0(ComponentContainer c) {
        return new DefaultUserAgentPublisher(c.setOf(LibraryVersion.class), GlobalLibraryVersionRegistrar.getInstance());
    }
}
