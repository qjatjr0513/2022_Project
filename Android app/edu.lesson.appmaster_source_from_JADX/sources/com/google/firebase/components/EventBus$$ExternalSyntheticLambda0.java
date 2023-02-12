package com.google.firebase.components;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import java.util.Map;

public final /* synthetic */ class EventBus$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Map.Entry f$0;
    public final /* synthetic */ Event f$1;

    public /* synthetic */ EventBus$$ExternalSyntheticLambda0(Map.Entry entry, Event event) {
        this.f$0 = entry;
        this.f$1 = event;
    }

    public final void run() {
        ((EventHandler) this.f$0.getKey()).handle(this.f$1);
    }
}
