package com.google.firebase.firestore.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteProgram;
import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteTransactionListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.FileUtil;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Supplier;
import com.google.firebase.firestore.util.Util;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class SQLitePersistence extends Persistence {
    public static final int MAX_ARGS = 900;
    private final SQLiteBundleCache bundleCache;

    /* renamed from: db */
    private SQLiteDatabase f166db;
    private final OpenHelper opener;
    /* access modifiers changed from: private */
    public final SQLiteLruReferenceDelegate referenceDelegate;
    private final SQLiteRemoteDocumentCache remoteDocumentCache;
    private final LocalSerializer serializer;
    private boolean started;
    private final SQLiteTargetCache targetCache;
    private final SQLiteTransactionListener transactionListener;

    public static String databaseName(String persistenceKey, DatabaseId databaseId) {
        try {
            return "firestore." + URLEncoder.encode(persistenceKey, "utf-8") + "." + URLEncoder.encode(databaseId.getProjectId(), "utf-8") + "." + URLEncoder.encode(databaseId.getDatabaseId(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public SQLitePersistence(Context context, String persistenceKey, DatabaseId databaseId, LocalSerializer serializer2, LruGarbageCollector.Params params) {
        this(serializer2, params, new OpenHelper(context, serializer2, databaseName(persistenceKey, databaseId)));
    }

    public SQLitePersistence(LocalSerializer serializer2, LruGarbageCollector.Params params, OpenHelper openHelper) {
        this.transactionListener = new SQLiteTransactionListener() {
            public void onBegin() {
                SQLitePersistence.this.referenceDelegate.onTransactionStarted();
            }

            public void onCommit() {
                SQLitePersistence.this.referenceDelegate.onTransactionCommitted();
            }

            public void onRollback() {
            }
        };
        this.opener = openHelper;
        this.serializer = serializer2;
        this.targetCache = new SQLiteTargetCache(this, serializer2);
        this.bundleCache = new SQLiteBundleCache(this, serializer2);
        this.remoteDocumentCache = new SQLiteRemoteDocumentCache(this, serializer2);
        this.referenceDelegate = new SQLiteLruReferenceDelegate(this, params);
    }

    public void start() {
        Assert.hardAssert(!this.started, "SQLitePersistence double-started!", new Object[0]);
        this.started = true;
        try {
            this.f166db = this.opener.getWritableDatabase();
            this.targetCache.start();
            this.referenceDelegate.start(this.targetCache.getHighestListenSequenceNumber());
        } catch (SQLiteDatabaseLockedException e) {
            throw new RuntimeException("Failed to gain exclusive lock to the Cloud Firestore client's offline persistence. This generally means you are using Cloud Firestore from multiple processes in your app. Keep in mind that multi-process Android apps execute the code in your Application class in all processes, so you may need to avoid initializing Cloud Firestore in your Application class. If you are intentionally using Cloud Firestore from multiple processes, you can only enable offline persistence (that is, call setPersistenceEnabled(true)) in one of them.", e);
        }
    }

    public void shutdown() {
        Assert.hardAssert(this.started, "SQLitePersistence shutdown without start!", new Object[0]);
        this.started = false;
        this.f166db.close();
        this.f166db = null;
    }

    public boolean isStarted() {
        return this.started;
    }

    public SQLiteLruReferenceDelegate getReferenceDelegate() {
        return this.referenceDelegate;
    }

    /* access modifiers changed from: package-private */
    public MutationQueue getMutationQueue(User user, IndexManager indexManager) {
        return new SQLiteMutationQueue(this, this.serializer, user, indexManager);
    }

    /* access modifiers changed from: package-private */
    public SQLiteTargetCache getTargetCache() {
        return this.targetCache;
    }

    /* access modifiers changed from: package-private */
    public IndexManager getIndexManager(User user) {
        return new SQLiteIndexManager(this, this.serializer, user);
    }

    /* access modifiers changed from: package-private */
    public BundleCache getBundleCache() {
        return this.bundleCache;
    }

    /* access modifiers changed from: package-private */
    public DocumentOverlayCache getDocumentOverlay(User user) {
        return new SQLiteDocumentOverlayCache(this, this.serializer, user);
    }

    /* access modifiers changed from: package-private */
    public OverlayMigrationManager getOverlayMigrationManager() {
        return new SQLiteOverlayMigrationManager(this);
    }

    /* access modifiers changed from: package-private */
    public RemoteDocumentCache getRemoteDocumentCache() {
        return this.remoteDocumentCache;
    }

    /* access modifiers changed from: package-private */
    public void runTransaction(String action, Runnable operation) {
        Logger.debug(TAG, "Starting transaction: %s", action);
        this.f166db.beginTransactionWithListener(this.transactionListener);
        try {
            operation.run();
            this.f166db.setTransactionSuccessful();
        } finally {
            this.f166db.endTransaction();
        }
    }

    /* access modifiers changed from: package-private */
    public <T> T runTransaction(String action, Supplier<T> operation) {
        Logger.debug(TAG, "Starting transaction: %s", action);
        this.f166db.beginTransactionWithListener(this.transactionListener);
        try {
            T value = operation.get();
            this.f166db.setTransactionSuccessful();
            return value;
        } finally {
            this.f166db.endTransaction();
        }
    }

    public static void clearPersistence(Context context, DatabaseId databaseId, String persistenceKey) throws FirebaseFirestoreException {
        String sqLitePath = context.getDatabasePath(databaseName(persistenceKey, databaseId)).getPath();
        File sqLiteFile = new File(sqLitePath);
        File journalFile = new File(sqLitePath + "-journal");
        File walFile = new File(sqLitePath + "-wal");
        try {
            FileUtil.delete(sqLiteFile);
            FileUtil.delete(journalFile);
            FileUtil.delete(walFile);
        } catch (IOException e) {
            throw new FirebaseFirestoreException("Failed to clear persistence." + e, FirebaseFirestoreException.Code.UNKNOWN);
        }
    }

    /* access modifiers changed from: package-private */
    public long getByteSize() {
        return getPageCount() * getPageSize();
    }

    private long getPageSize() {
        return ((Long) query("PRAGMA page_size").firstValue(SQLitePersistence$$ExternalSyntheticLambda1.INSTANCE)).longValue();
    }

    private long getPageCount() {
        return ((Long) query("PRAGMA page_count").firstValue(SQLitePersistence$$ExternalSyntheticLambda0.INSTANCE)).longValue();
    }

    static class OpenHelper extends SQLiteOpenHelper {
        private boolean configured;
        private final LocalSerializer serializer;

        private OpenHelper(Context context, LocalSerializer serializer2, String databaseName) {
            this(context, serializer2, databaseName, 16);
        }

        OpenHelper(Context context, LocalSerializer serializer2, String databaseName, int schemaVersion) {
            super(context, databaseName, (SQLiteDatabase.CursorFactory) null, schemaVersion);
            this.serializer = serializer2;
        }

        public void onConfigure(SQLiteDatabase db) {
            this.configured = true;
            db.rawQuery("PRAGMA locking_mode = EXCLUSIVE", new String[0]).close();
        }

        private void ensureConfigured(SQLiteDatabase db) {
            if (!this.configured) {
                onConfigure(db);
            }
        }

        public void onCreate(SQLiteDatabase db) {
            ensureConfigured(db);
            new SQLiteSchema(db, this.serializer).runSchemaUpgrades(0);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            ensureConfigured(db);
            new SQLiteSchema(db, this.serializer).runSchemaUpgrades(oldVersion);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            ensureConfigured(db);
        }

        public void onOpen(SQLiteDatabase db) {
            ensureConfigured(db);
        }
    }

    /* access modifiers changed from: package-private */
    public void execute(String sql, Object... args) {
        this.f166db.execSQL(sql, args);
    }

    /* access modifiers changed from: package-private */
    public SQLiteStatement prepare(String sql) {
        return this.f166db.compileStatement(sql);
    }

    /* access modifiers changed from: package-private */
    public int execute(SQLiteStatement statement, Object... args) {
        statement.clearBindings();
        bind(statement, args);
        return statement.executeUpdateDelete();
    }

    /* access modifiers changed from: package-private */
    public Query query(String sql) {
        return new Query(this.f166db, sql);
    }

    static class Query {
        private SQLiteDatabase.CursorFactory cursorFactory;

        /* renamed from: db */
        private final SQLiteDatabase f168db;
        private final String sql;

        Query(SQLiteDatabase db, String sql2) {
            this.f168db = db;
            this.sql = sql2;
        }

        /* access modifiers changed from: package-private */
        public Query binding(Object... args) {
            this.cursorFactory = new SQLitePersistence$Query$$ExternalSyntheticLambda0(args);
            return this;
        }

        /* access modifiers changed from: package-private */
        public int forEach(Consumer<Cursor> consumer) {
            int rowsProcessed = 0;
            Cursor cursor = startQuery();
            while (cursor.moveToNext()) {
                try {
                    rowsProcessed++;
                    consumer.accept(cursor);
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return rowsProcessed;
            throw th;
        }

        /* access modifiers changed from: package-private */
        public int first(Consumer<Cursor> consumer) {
            Cursor cursor = startQuery();
            try {
                if (cursor.moveToFirst()) {
                    consumer.accept(cursor);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return 1;
                }
                if (cursor != null) {
                    cursor.close();
                }
                return 0;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        /* access modifiers changed from: package-private */
        public <T> T firstValue(Function<Cursor, T> function) {
            Cursor cursor = startQuery();
            try {
                if (cursor.moveToFirst()) {
                    T apply = function.apply(cursor);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return apply;
                }
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        /* access modifiers changed from: package-private */
        public boolean isEmpty() {
            Cursor cursor = startQuery();
            try {
                boolean z = !cursor.moveToFirst();
                if (cursor != null) {
                    cursor.close();
                }
                return z;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        }

        private Cursor startQuery() {
            SQLiteDatabase.CursorFactory cursorFactory2 = this.cursorFactory;
            if (cursorFactory2 != null) {
                return this.f168db.rawQueryWithFactory(cursorFactory2, this.sql, (String[]) null, (String) null);
            }
            return this.f168db.rawQuery(this.sql, (String[]) null);
        }
    }

    static class LongQuery {
        private static final int LIMIT = 900;
        private final List<Object> argsHead;
        private final Iterator<Object> argsIter;

        /* renamed from: db */
        private final SQLitePersistence f167db;
        private final String head;
        private int subqueriesPerformed = 0;
        private final String tail;

        LongQuery(SQLitePersistence db, String head2, List<Object> allArgs, String tail2) {
            this.f167db = db;
            this.head = head2;
            this.argsHead = Collections.emptyList();
            this.tail = tail2;
            this.argsIter = allArgs.iterator();
        }

        LongQuery(SQLitePersistence db, String head2, List<Object> argsHead2, List<Object> allArgs, String tail2) {
            this.f167db = db;
            this.head = head2;
            this.argsHead = argsHead2;
            this.tail = tail2;
            this.argsIter = allArgs.iterator();
        }

        /* access modifiers changed from: package-private */
        public boolean hasMoreSubqueries() {
            return this.argsIter.hasNext();
        }

        private Object[] getNextSubqueryArgs() {
            List<Object> subqueryArgs = new ArrayList<>(this.argsHead);
            int i = 0;
            while (this.argsIter.hasNext() && i < 900 - this.argsHead.size()) {
                subqueryArgs.add(this.argsIter.next());
                i++;
            }
            return subqueryArgs.toArray();
        }

        /* access modifiers changed from: package-private */
        public Query performNextSubquery() {
            this.subqueriesPerformed++;
            Object[] subqueryArgs = getNextSubqueryArgs();
            return this.f167db.query(this.head + Util.repeatSequence("?", subqueryArgs.length, ", ") + this.tail).binding(subqueryArgs);
        }

        /* access modifiers changed from: package-private */
        public void executeNextSubquery() {
            this.subqueriesPerformed++;
            Object[] subqueryArgs = getNextSubqueryArgs();
            this.f167db.execute(this.head + Util.repeatSequence("?", subqueryArgs.length, ", ") + this.tail, subqueryArgs);
        }

        /* access modifiers changed from: package-private */
        public int getSubqueriesPerformed() {
            return this.subqueriesPerformed;
        }
    }

    /* access modifiers changed from: private */
    public static void bind(SQLiteProgram program, Object[] bindArgs) {
        for (int i = 0; i < bindArgs.length; i++) {
            Object arg = bindArgs[i];
            if (arg == null) {
                program.bindNull(i + 1);
            } else if (arg instanceof String) {
                program.bindString(i + 1, (String) arg);
            } else if (arg instanceof Integer) {
                program.bindLong(i + 1, (long) ((Integer) arg).intValue());
            } else if (arg instanceof Long) {
                program.bindLong(i + 1, ((Long) arg).longValue());
            } else if (arg instanceof Double) {
                program.bindDouble(i + 1, ((Double) arg).doubleValue());
            } else if (arg instanceof byte[]) {
                program.bindBlob(i + 1, (byte[]) arg);
            } else {
                throw Assert.fail("Unknown argument %s of type %s", arg, arg.getClass());
            }
        }
    }
}
