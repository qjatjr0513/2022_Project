package com.google.firebase.components;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class Component<T> {
    private final Set<Dependency> dependencies;
    private final ComponentFactory<T> factory;
    private final int instantiation;
    private final Set<Class<? super T>> providedInterfaces;
    private final Set<Class<?>> publishedEvents;
    private final int type;

    private Component(Set<Class<? super T>> providedInterfaces2, Set<Dependency> dependencies2, int instantiation2, int type2, ComponentFactory<T> factory2, Set<Class<?>> publishedEvents2) {
        this.providedInterfaces = Collections.unmodifiableSet(providedInterfaces2);
        this.dependencies = Collections.unmodifiableSet(dependencies2);
        this.instantiation = instantiation2;
        this.type = type2;
        this.factory = factory2;
        this.publishedEvents = Collections.unmodifiableSet(publishedEvents2);
    }

    public Set<Class<? super T>> getProvidedInterfaces() {
        return this.providedInterfaces;
    }

    public Set<Dependency> getDependencies() {
        return this.dependencies;
    }

    public ComponentFactory<T> getFactory() {
        return this.factory;
    }

    public Set<Class<?>> getPublishedEvents() {
        return this.publishedEvents;
    }

    public boolean isLazy() {
        return this.instantiation == 0;
    }

    public boolean isAlwaysEager() {
        return this.instantiation == 1;
    }

    public boolean isEagerInDefaultApp() {
        return this.instantiation == 2;
    }

    public boolean isValue() {
        return this.type == 0;
    }

    public String toString() {
        return "Component<" + Arrays.toString(this.providedInterfaces.toArray()) + ">{" + this.instantiation + ", type=" + this.type + ", deps=" + Arrays.toString(this.dependencies.toArray()) + "}";
    }

    public static <T> Builder<T> builder(Class<T> anInterface) {
        return new Builder<>(anInterface, new Class[0]);
    }

    @SafeVarargs
    public static <T> Builder<T> builder(Class<T> anInterface, Class<? super T>... additionalInterfaces) {
        return new Builder<>(anInterface, additionalInterfaces);
    }

    static /* synthetic */ Object lambda$of$0(Object value, ComponentContainer args) {
        return value;
    }

    @Deprecated
    /* renamed from: of */
    public static <T> Component<T> m182of(Class<T> anInterface, T value) {
        return builder(anInterface).factory(new Component$$ExternalSyntheticLambda1(value)).build();
    }

    static /* synthetic */ Object lambda$of$1(Object value, ComponentContainer args) {
        return value;
    }

    @SafeVarargs
    /* renamed from: of */
    public static <T> Component<T> m183of(T value, Class<T> anInterface, Class<? super T>... additionalInterfaces) {
        return builder(anInterface, additionalInterfaces).factory(new Component$$ExternalSyntheticLambda2(value)).build();
    }

    public static <T> Builder<T> intoSetBuilder(Class<T> anInterface) {
        return builder(anInterface).intoSet();
    }

    public static <T> Component<T> intoSet(T value, Class<T> anInterface) {
        return intoSetBuilder(anInterface).factory(new Component$$ExternalSyntheticLambda0(value)).build();
    }

    static /* synthetic */ Object lambda$intoSet$2(Object value, ComponentContainer c) {
        return value;
    }

    public static class Builder<T> {
        private final Set<Dependency> dependencies;
        private ComponentFactory<T> factory;
        private int instantiation;
        private final Set<Class<? super T>> providedInterfaces;
        private Set<Class<?>> publishedEvents;
        private int type;

        @SafeVarargs
        private Builder(Class<T> anInterface, Class<? super T>... additionalInterfaces) {
            HashSet hashSet = new HashSet();
            this.providedInterfaces = hashSet;
            this.dependencies = new HashSet();
            this.instantiation = 0;
            this.type = 0;
            this.publishedEvents = new HashSet();
            Preconditions.checkNotNull(anInterface, "Null interface");
            hashSet.add(anInterface);
            for (Class<? super T> iface : additionalInterfaces) {
                Preconditions.checkNotNull(iface, "Null interface");
            }
            Collections.addAll(this.providedInterfaces, additionalInterfaces);
        }

        public Builder<T> add(Dependency dependency) {
            Preconditions.checkNotNull(dependency, "Null dependency");
            validateInterface(dependency.getInterface());
            this.dependencies.add(dependency);
            return this;
        }

        public Builder<T> alwaysEager() {
            return setInstantiation(1);
        }

        public Builder<T> eagerInDefaultApp() {
            return setInstantiation(2);
        }

        public Builder<T> publishes(Class<?> eventType) {
            this.publishedEvents.add(eventType);
            return this;
        }

        private Builder<T> setInstantiation(int instantiation2) {
            Preconditions.checkState(this.instantiation == 0, "Instantiation type has already been set.");
            this.instantiation = instantiation2;
            return this;
        }

        private void validateInterface(Class<?> anInterface) {
            Preconditions.checkArgument(!this.providedInterfaces.contains(anInterface), "Components are not allowed to depend on interfaces they themselves provide.");
        }

        public Builder<T> factory(ComponentFactory<T> value) {
            this.factory = (ComponentFactory) Preconditions.checkNotNull(value, "Null factory");
            return this;
        }

        /* access modifiers changed from: private */
        public Builder<T> intoSet() {
            this.type = 1;
            return this;
        }

        public Component<T> build() {
            Preconditions.checkState(this.factory != null, "Missing required property: factory.");
            return new Component(new HashSet(this.providedInterfaces), new HashSet(this.dependencies), this.instantiation, this.type, this.factory, this.publishedEvents);
        }
    }
}
