package p004io.grpc;

import java.util.logging.Level;
import java.util.logging.Logger;
import p004io.grpc.Context;

/* renamed from: io.grpc.ThreadLocalContextStorage */
final class ThreadLocalContextStorage extends Context.Storage {
    static final ThreadLocal<Context> localContext = new ThreadLocal<>();
    private static final Logger log = Logger.getLogger(ThreadLocalContextStorage.class.getName());

    ThreadLocalContextStorage() {
    }

    public Context doAttach(Context toAttach) {
        Context current = current();
        localContext.set(toAttach);
        return current;
    }

    public void detach(Context toDetach, Context toRestore) {
        if (current() != toDetach) {
            log.log(Level.SEVERE, "Context was not attached when detaching", new Throwable().fillInStackTrace());
        }
        if (toRestore != Context.ROOT) {
            localContext.set(toRestore);
        } else {
            localContext.set((Object) null);
        }
    }

    public Context current() {
        Context current = localContext.get();
        if (current == null) {
            return Context.ROOT;
        }
        return current;
    }
}
