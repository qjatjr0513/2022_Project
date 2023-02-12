package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.DoNotMock;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@DoNotMock("Call forGraph or forTree, passing a lambda or a Graph with the desired edges (built with GraphBuilder)")
public abstract class Traverser<N> {
    private final SuccessorsFunction<N> successorFunction;

    private enum InsertionOrder {
        FRONT {
            /* access modifiers changed from: package-private */
            public <T> void insertInto(Deque<T> deque, T value) {
                deque.addFirst(value);
            }
        },
        BACK {
            /* access modifiers changed from: package-private */
            public <T> void insertInto(Deque<T> deque, T value) {
                deque.addLast(value);
            }
        };

        /* access modifiers changed from: package-private */
        public abstract <T> void insertInto(Deque<T> deque, T t);
    }

    /* access modifiers changed from: package-private */
    public abstract Traversal<N> newTraversal();

    private Traverser(SuccessorsFunction<N> successorFunction2) {
        this.successorFunction = (SuccessorsFunction) Preconditions.checkNotNull(successorFunction2);
    }

    public static <N> Traverser<N> forGraph(final SuccessorsFunction<N> graph) {
        return new Traverser<N>(graph) {
            /* access modifiers changed from: package-private */
            public Traversal<N> newTraversal() {
                return Traversal.inGraph(graph);
            }
        };
    }

    public static <N> Traverser<N> forTree(final SuccessorsFunction<N> tree) {
        if (tree instanceof BaseGraph) {
            Preconditions.checkArgument(((BaseGraph) tree).isDirected(), "Undirected graphs can never be trees.");
        }
        if (tree instanceof Network) {
            Preconditions.checkArgument(((Network) tree).isDirected(), "Undirected networks can never be trees.");
        }
        return new Traverser<N>(tree) {
            /* access modifiers changed from: package-private */
            public Traversal<N> newTraversal() {
                return Traversal.inTree(tree);
            }
        };
    }

    public final Iterable<N> breadthFirst(N startNode) {
        return breadthFirst(ImmutableSet.m84of(startNode));
    }

    public final Iterable<N> breadthFirst(Iterable<? extends N> startNodes) {
        final ImmutableSet<N> validated = validate(startNodes);
        return new Iterable<N>() {
            public Iterator<N> iterator() {
                return Traverser.this.newTraversal().breadthFirst(validated.iterator());
            }
        };
    }

    public final Iterable<N> depthFirstPreOrder(N startNode) {
        return depthFirstPreOrder(ImmutableSet.m84of(startNode));
    }

    public final Iterable<N> depthFirstPreOrder(Iterable<? extends N> startNodes) {
        final ImmutableSet<N> validated = validate(startNodes);
        return new Iterable<N>() {
            public Iterator<N> iterator() {
                return Traverser.this.newTraversal().preOrder(validated.iterator());
            }
        };
    }

    public final Iterable<N> depthFirstPostOrder(N startNode) {
        return depthFirstPostOrder(ImmutableSet.m84of(startNode));
    }

    public final Iterable<N> depthFirstPostOrder(Iterable<? extends N> startNodes) {
        final ImmutableSet<N> validated = validate(startNodes);
        return new Iterable<N>() {
            public Iterator<N> iterator() {
                return Traverser.this.newTraversal().postOrder(validated.iterator());
            }
        };
    }

    private ImmutableSet<N> validate(Iterable<? extends N> startNodes) {
        ImmutableSet<N> copy = ImmutableSet.copyOf(startNodes);
        UnmodifiableIterator<N> it = copy.iterator();
        while (it.hasNext()) {
            this.successorFunction.successors(it.next());
        }
        return copy;
    }

    private static abstract class Traversal<N> {
        final SuccessorsFunction<N> successorFunction;

        /* access modifiers changed from: package-private */
        @NullableDecl
        public abstract N visitNext(Deque<Iterator<? extends N>> deque);

        Traversal(SuccessorsFunction<N> successorFunction2) {
            this.successorFunction = successorFunction2;
        }

        static <N> Traversal<N> inGraph(SuccessorsFunction<N> graph) {
            final Set<N> visited = new HashSet<>();
            return new Traversal<N>(graph) {
                /* access modifiers changed from: package-private */
                public N visitNext(Deque<Iterator<? extends N>> horizon) {
                    Iterator<? extends N> top = horizon.getFirst();
                    while (top.hasNext()) {
                        N element = Preconditions.checkNotNull(top.next());
                        if (visited.add(element)) {
                            return element;
                        }
                    }
                    horizon.removeFirst();
                    return null;
                }
            };
        }

        static <N> Traversal<N> inTree(SuccessorsFunction<N> tree) {
            return new Traversal<N>(tree) {
                /* access modifiers changed from: package-private */
                public N visitNext(Deque<Iterator<? extends N>> horizon) {
                    Iterator<? extends N> top = horizon.getFirst();
                    if (top.hasNext()) {
                        return Preconditions.checkNotNull(top.next());
                    }
                    horizon.removeFirst();
                    return null;
                }
            };
        }

        /* access modifiers changed from: package-private */
        public final Iterator<N> breadthFirst(Iterator<? extends N> startNodes) {
            return topDown(startNodes, InsertionOrder.BACK);
        }

        /* access modifiers changed from: package-private */
        public final Iterator<N> preOrder(Iterator<? extends N> startNodes) {
            return topDown(startNodes, InsertionOrder.FRONT);
        }

        private Iterator<N> topDown(Iterator<? extends N> startNodes, final InsertionOrder order) {
            final Deque<Iterator<? extends N>> horizon = new ArrayDeque<>();
            horizon.add(startNodes);
            return new AbstractIterator<N>() {
                /* access modifiers changed from: protected */
                public N computeNext() {
                    do {
                        N next = Traversal.this.visitNext(horizon);
                        if (next != null) {
                            Iterator<? extends N> successors = Traversal.this.successorFunction.successors(next).iterator();
                            if (successors.hasNext()) {
                                order.insertInto(horizon, successors);
                            }
                            return next;
                        }
                    } while (!horizon.isEmpty());
                    return endOfData();
                }
            };
        }

        /* access modifiers changed from: package-private */
        public final Iterator<N> postOrder(Iterator<? extends N> startNodes) {
            final Deque<N> ancestorStack = new ArrayDeque<>();
            final Deque<Iterator<? extends N>> horizon = new ArrayDeque<>();
            horizon.add(startNodes);
            return new AbstractIterator<N>() {
                /* access modifiers changed from: protected */
                public N computeNext() {
                    N next = Traversal.this.visitNext(horizon);
                    while (next != null) {
                        Iterator<? extends N> successors = Traversal.this.successorFunction.successors(next).iterator();
                        if (!successors.hasNext()) {
                            return next;
                        }
                        horizon.addFirst(successors);
                        ancestorStack.push(next);
                        next = Traversal.this.visitNext(horizon);
                    }
                    return ancestorStack.isEmpty() ? endOfData() : ancestorStack.pop();
                }
            };
        }
    }
}
