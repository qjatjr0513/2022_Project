package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;

final class AutoValue_PersistedEvent extends PersistedEvent {
    private final EventInternal event;

    /* renamed from: id */
    private final long f419id;
    private final TransportContext transportContext;

    AutoValue_PersistedEvent(long id, TransportContext transportContext2, EventInternal event2) {
        this.f419id = id;
        if (transportContext2 != null) {
            this.transportContext = transportContext2;
            if (event2 != null) {
                this.event = event2;
                return;
            }
            throw new NullPointerException("Null event");
        }
        throw new NullPointerException("Null transportContext");
    }

    public long getId() {
        return this.f419id;
    }

    public TransportContext getTransportContext() {
        return this.transportContext;
    }

    public EventInternal getEvent() {
        return this.event;
    }

    public String toString() {
        return "PersistedEvent{id=" + this.f419id + ", transportContext=" + this.transportContext + ", event=" + this.event + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PersistedEvent)) {
            return false;
        }
        PersistedEvent that = (PersistedEvent) o;
        if (this.f419id != that.getId() || !this.transportContext.equals(that.getTransportContext()) || !this.event.equals(that.getEvent())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.f419id;
        return (((((1 * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.transportContext.hashCode()) * 1000003) ^ this.event.hashCode();
    }
}
