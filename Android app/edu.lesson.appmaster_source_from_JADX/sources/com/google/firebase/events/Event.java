package com.google.firebase.events;

import com.google.firebase.components.Preconditions;

public class Event<T> {
    private final T payload;
    private final Class<T> type;

    public Event(Class<T> type2, T payload2) {
        this.type = (Class) Preconditions.checkNotNull(type2);
        this.payload = Preconditions.checkNotNull(payload2);
    }

    public Class<T> getType() {
        return this.type;
    }

    public T getPayload() {
        return this.payload;
    }

    public String toString() {
        return String.format("Event{type: %s, payload: %s}", new Object[]{this.type, this.payload});
    }
}
