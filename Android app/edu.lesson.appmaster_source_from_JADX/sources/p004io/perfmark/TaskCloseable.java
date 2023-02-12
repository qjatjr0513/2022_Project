package p004io.perfmark;

import java.io.Closeable;

/* renamed from: io.perfmark.TaskCloseable */
public final class TaskCloseable implements Closeable {
    static final TaskCloseable INSTANCE = new TaskCloseable();

    public void close() {
        PerfMark.stopTask();
    }

    private TaskCloseable() {
    }
}
