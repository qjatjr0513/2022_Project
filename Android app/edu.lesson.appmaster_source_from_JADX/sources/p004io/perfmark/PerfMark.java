package p004io.perfmark;

import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: io.perfmark.PerfMark */
public final class PerfMark {
    private static final Impl impl;

    static {
        Impl instance = null;
        Level level = Level.WARNING;
        Throwable err = null;
        Class<?> clz = null;
        try {
            clz = Class.forName("io.perfmark.impl.SecretPerfMarkImpl$PerfMarkImpl");
        } catch (ClassNotFoundException e) {
            level = Level.FINE;
            err = e;
        } catch (Throwable t) {
            err = t;
        }
        if (clz != null) {
            try {
                instance = (Impl) clz.asSubclass(Impl.class).getConstructor(new Class[]{Tag.class}).newInstance(new Object[]{Impl.NO_TAG});
            } catch (Throwable t2) {
                err = t2;
            }
        }
        if (instance != null) {
            impl = instance;
        } else {
            impl = new Impl(Impl.NO_TAG);
        }
        if (err != null) {
            Logger.getLogger(PerfMark.class.getName()).log(level, "Error during PerfMark.<clinit>", err);
        }
    }

    public static void setEnabled(boolean value) {
        impl.setEnabled(value);
    }

    public static void startTask(String taskName, Tag tag) {
        impl.startTask(taskName, tag);
    }

    public static void startTask(String taskName) {
        impl.startTask(taskName);
    }

    public static <T> void startTask(T taskNameObject, StringFunction<? super T> taskNameFunction) {
        impl.startTask(taskNameObject, taskNameFunction);
    }

    public static void startTask(String taskName, String subTaskName) {
        impl.startTask(taskName, subTaskName);
    }

    public static TaskCloseable traceTask(String taskName) {
        impl.startTask(taskName);
        return TaskCloseable.INSTANCE;
    }

    public static <T> TaskCloseable traceTask(T taskNameObject, StringFunction<? super T> taskNameFunction) {
        impl.startTask(taskNameObject, taskNameFunction);
        return TaskCloseable.INSTANCE;
    }

    public static void event(String eventName, Tag tag) {
        impl.event(eventName, tag);
    }

    public static void event(String eventName) {
        impl.event(eventName);
    }

    public static void event(String eventName, String subEventName) {
        impl.event(eventName, subEventName);
    }

    public static void stopTask() {
        impl.stopTask();
    }

    public static void stopTask(String taskName, Tag tag) {
        impl.stopTask(taskName, tag);
    }

    public static void stopTask(String taskName) {
        impl.stopTask(taskName);
    }

    public static void stopTask(String taskName, String subTaskName) {
        impl.stopTask(taskName, subTaskName);
    }

    public static Tag createTag() {
        return Impl.NO_TAG;
    }

    public static Tag createTag(long id) {
        return impl.createTag("", id);
    }

    public static Tag createTag(String name) {
        return impl.createTag(name, Long.MIN_VALUE);
    }

    public static Tag createTag(String name, long id) {
        return impl.createTag(name, id);
    }

    @Deprecated
    public static Link link() {
        return Impl.NO_LINK;
    }

    public static Link linkOut() {
        return impl.linkOut();
    }

    public static void linkIn(Link link) {
        impl.linkIn(link);
    }

    public static void attachTag(Tag tag) {
        impl.attachTag(tag);
    }

    public static void attachTag(String tagName, String tagValue) {
        impl.attachTag(tagName, tagValue);
    }

    public static void attachTag(String tagName, long tagValue) {
        impl.attachTag(tagName, tagValue);
    }

    public static void attachTag(String tagName, long tagValue0, long tagValue1) {
        impl.attachTag(tagName, tagValue0, tagValue1);
    }

    public static <T> void attachTag(String tagName, T tagObject, StringFunction<? super T> stringFunction) {
        impl.attachTag(tagName, tagObject, stringFunction);
    }

    private PerfMark() {
    }
}
