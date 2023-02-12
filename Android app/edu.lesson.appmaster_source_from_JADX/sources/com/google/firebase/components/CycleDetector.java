package com.google.firebase.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class CycleDetector {
    CycleDetector() {
    }

    private static class Dep {
        private final Class<?> anInterface;
        /* access modifiers changed from: private */
        public final boolean set;

        private Dep(Class<?> anInterface2, boolean set2) {
            this.anInterface = anInterface2;
            this.set = set2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Dep)) {
                return false;
            }
            Dep dep = (Dep) obj;
            if (!dep.anInterface.equals(this.anInterface) || dep.set != this.set) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return ((1000003 ^ this.anInterface.hashCode()) * 1000003) ^ Boolean.valueOf(this.set).hashCode();
        }
    }

    private static class ComponentNode {
        private final Component<?> component;
        private final Set<ComponentNode> dependencies = new HashSet();
        private final Set<ComponentNode> dependents = new HashSet();

        ComponentNode(Component<?> component2) {
            this.component = component2;
        }

        /* access modifiers changed from: package-private */
        public void addDependency(ComponentNode node) {
            this.dependencies.add(node);
        }

        /* access modifiers changed from: package-private */
        public void addDependent(ComponentNode node) {
            this.dependents.add(node);
        }

        /* access modifiers changed from: package-private */
        public Set<ComponentNode> getDependencies() {
            return this.dependencies;
        }

        /* access modifiers changed from: package-private */
        public void removeDependent(ComponentNode node) {
            this.dependents.remove(node);
        }

        /* access modifiers changed from: package-private */
        public Component<?> getComponent() {
            return this.component;
        }

        /* access modifiers changed from: package-private */
        public boolean isRoot() {
            return this.dependents.isEmpty();
        }

        /* access modifiers changed from: package-private */
        public boolean isLeaf() {
            return this.dependencies.isEmpty();
        }
    }

    static void detect(List<Component<?>> components) {
        Set<ComponentNode> graph = toGraph(components);
        Set<ComponentNode> roots = getRoots(graph);
        int numVisited = 0;
        while (!roots.isEmpty()) {
            ComponentNode node = roots.iterator().next();
            roots.remove(node);
            numVisited++;
            for (ComponentNode dependent : node.getDependencies()) {
                dependent.removeDependent(node);
                if (dependent.isRoot()) {
                    roots.add(dependent);
                }
            }
        }
        if (numVisited != components.size()) {
            List<Component<?>> componentsInCycle = new ArrayList<>();
            for (ComponentNode node2 : graph) {
                if (!node2.isRoot() && !node2.isLeaf()) {
                    componentsInCycle.add(node2.getComponent());
                }
            }
            throw new DependencyCycleException(componentsInCycle);
        }
    }

    private static Set<ComponentNode> toGraph(List<Component<?>> components) {
        Set<ComponentNode> depComponents;
        Map<Dep, Set<ComponentNode>> componentIndex = new HashMap<>(components.size());
        for (Component<?> component : components) {
            ComponentNode node = new ComponentNode(component);
            Iterator<Class<? super Object>> it = component.getProvidedInterfaces().iterator();
            while (true) {
                if (it.hasNext()) {
                    Class<?> anInterface = it.next();
                    Dep cmp = new Dep(anInterface, !component.isValue());
                    if (!componentIndex.containsKey(cmp)) {
                        componentIndex.put(cmp, new HashSet());
                    }
                    Set<ComponentNode> nodes = componentIndex.get(cmp);
                    if (nodes.isEmpty() || cmp.set) {
                        nodes.add(node);
                    } else {
                        throw new IllegalArgumentException(String.format("Multiple components provide %s.", new Object[]{anInterface}));
                    }
                }
            }
        }
        for (Set<ComponentNode> componentNodes : componentIndex.values()) {
            for (ComponentNode node2 : componentNodes) {
                for (Dependency dependency : node2.getComponent().getDependencies()) {
                    if (dependency.isDirectInjection() && (depComponents = componentIndex.get(new Dep(dependency.getInterface(), dependency.isSet()))) != null) {
                        for (ComponentNode depComponent : depComponents) {
                            node2.addDependency(depComponent);
                            depComponent.addDependent(node2);
                        }
                    }
                }
            }
        }
        HashSet<ComponentNode> result = new HashSet<>();
        for (Set<ComponentNode> componentNodes2 : componentIndex.values()) {
            result.addAll(componentNodes2);
        }
        return result;
    }

    private static Set<ComponentNode> getRoots(Set<ComponentNode> components) {
        Set<ComponentNode> roots = new HashSet<>();
        for (ComponentNode component : components) {
            if (component.isRoot()) {
                roots.add(component);
            }
        }
        return roots;
    }
}
