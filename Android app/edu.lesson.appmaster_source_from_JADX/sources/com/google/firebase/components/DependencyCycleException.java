package com.google.firebase.components;

import java.util.Arrays;
import java.util.List;

public class DependencyCycleException extends DependencyException {
    private final List<Component<?>> componentsInCycle;

    public DependencyCycleException(List<Component<?>> componentsInCycle2) {
        super("Dependency cycle detected: " + Arrays.toString(componentsInCycle2.toArray()));
        this.componentsInCycle = componentsInCycle2;
    }

    public List<Component<?>> getComponentsInCycle() {
        return this.componentsInCycle;
    }
}
