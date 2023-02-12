package com.google.common.eventbus;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventBus {
    private static final Logger logger = Logger.getLogger(EventBus.class.getName());
    private final Dispatcher dispatcher;
    private final SubscriberExceptionHandler exceptionHandler;
    private final Executor executor;
    private final String identifier;
    private final SubscriberRegistry subscribers;

    public EventBus() {
        this("default");
    }

    public EventBus(String identifier2) {
        this(identifier2, MoreExecutors.directExecutor(), Dispatcher.perThreadDispatchQueue(), LoggingHandler.INSTANCE);
    }

    public EventBus(SubscriberExceptionHandler exceptionHandler2) {
        this("default", MoreExecutors.directExecutor(), Dispatcher.perThreadDispatchQueue(), exceptionHandler2);
    }

    EventBus(String identifier2, Executor executor2, Dispatcher dispatcher2, SubscriberExceptionHandler exceptionHandler2) {
        this.subscribers = new SubscriberRegistry(this);
        this.identifier = (String) Preconditions.checkNotNull(identifier2);
        this.executor = (Executor) Preconditions.checkNotNull(executor2);
        this.dispatcher = (Dispatcher) Preconditions.checkNotNull(dispatcher2);
        this.exceptionHandler = (SubscriberExceptionHandler) Preconditions.checkNotNull(exceptionHandler2);
    }

    public final String identifier() {
        return this.identifier;
    }

    /* access modifiers changed from: package-private */
    public final Executor executor() {
        return this.executor;
    }

    /* access modifiers changed from: package-private */
    public void handleSubscriberException(Throwable e, SubscriberExceptionContext context) {
        Preconditions.checkNotNull(e);
        Preconditions.checkNotNull(context);
        try {
            this.exceptionHandler.handleException(e, context);
        } catch (Throwable e2) {
            logger.log(Level.SEVERE, String.format(Locale.ROOT, "Exception %s thrown while handling exception: %s", new Object[]{e2, e}), e2);
        }
    }

    public void register(Object object) {
        this.subscribers.register(object);
    }

    public void unregister(Object object) {
        this.subscribers.unregister(object);
    }

    public void post(Object event) {
        Iterator<Subscriber> eventSubscribers = this.subscribers.getSubscribers(event);
        if (eventSubscribers.hasNext()) {
            this.dispatcher.dispatch(event, eventSubscribers);
        } else if (!(event instanceof DeadEvent)) {
            post(new DeadEvent(this, event));
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).addValue((Object) this.identifier).toString();
    }

    static final class LoggingHandler implements SubscriberExceptionHandler {
        static final LoggingHandler INSTANCE = new LoggingHandler();

        LoggingHandler() {
        }

        public void handleException(Throwable exception, SubscriberExceptionContext context) {
            Logger logger = logger(context);
            if (logger.isLoggable(Level.SEVERE)) {
                logger.log(Level.SEVERE, message(context), exception);
            }
        }

        private static Logger logger(SubscriberExceptionContext context) {
            String name = EventBus.class.getName();
            String identifier = context.getEventBus().identifier();
            return Logger.getLogger(new StringBuilder(String.valueOf(name).length() + 1 + String.valueOf(identifier).length()).append(name).append(".").append(identifier).toString());
        }

        private static String message(SubscriberExceptionContext context) {
            Method method = context.getSubscriberMethod();
            String name = method.getName();
            String name2 = method.getParameterTypes()[0].getName();
            String valueOf = String.valueOf(context.getSubscriber());
            String valueOf2 = String.valueOf(context.getEvent());
            return new StringBuilder(String.valueOf(name).length() + 80 + String.valueOf(name2).length() + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length()).append("Exception thrown by subscriber method ").append(name).append('(').append(name2).append(')').append(" on subscriber ").append(valueOf).append(" when dispatching event: ").append(valueOf2).toString();
        }
    }
}
