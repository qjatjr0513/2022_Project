package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.util.Logger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

public class BundleReader {
    protected static final int BUFFER_CAPACITY = 1024;
    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private ByteBuffer buffer;
    private final InputStream bundleInputStream;
    long bytesRead;
    private final InputStreamReader dataReader;
    BundleMetadata metadata;
    private final BundleSerializer serializer;

    public BundleReader(BundleSerializer serializer2, InputStream bundleInputStream2) {
        this.serializer = serializer2;
        this.bundleInputStream = bundleInputStream2;
        this.dataReader = new InputStreamReader(bundleInputStream2);
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        this.buffer = allocate;
        allocate.flip();
    }

    public BundleMetadata getBundleMetadata() throws IOException, JSONException {
        BundleMetadata bundleMetadata = this.metadata;
        if (bundleMetadata != null) {
            return bundleMetadata;
        }
        BundleElement element = readNextElement();
        if (element instanceof BundleMetadata) {
            BundleMetadata bundleMetadata2 = (BundleMetadata) element;
            this.metadata = bundleMetadata2;
            this.bytesRead = 0;
            return bundleMetadata2;
        }
        throw abort("Expected first element in bundle to be a metadata object");
    }

    public BundleElement getNextElement() throws IOException, JSONException {
        getBundleMetadata();
        return readNextElement();
    }

    public long getBytesRead() {
        return this.bytesRead;
    }

    public void close() throws IOException {
        this.bundleInputStream.close();
    }

    private BundleElement readNextElement() throws IOException, JSONException {
        String lengthPrefix = readLengthPrefix();
        if (lengthPrefix == null) {
            return null;
        }
        int jsonStringByteCount = Integer.parseInt(lengthPrefix);
        String json = readJsonString(jsonStringByteCount);
        this.bytesRead += (long) (lengthPrefix.getBytes(UTF8_CHARSET).length + jsonStringByteCount);
        return decodeBundleElement(json);
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String readLengthPrefix() throws java.io.IOException {
        /*
            r4 = this;
        L_0x0000:
            int r0 = r4.indexOfOpenBracket()
            r1 = r0
            r2 = -1
            if (r0 != r2) goto L_0x000e
            boolean r0 = r4.pullMoreData()
            if (r0 != 0) goto L_0x0000
        L_0x000e:
            java.nio.ByteBuffer r0 = r4.buffer
            int r0 = r0.remaining()
            if (r0 != 0) goto L_0x0018
            r0 = 0
            return r0
        L_0x0018:
            if (r1 == r2) goto L_0x0030
            byte[] r0 = new byte[r1]
            java.nio.ByteBuffer r2 = r4.buffer
            r2.get(r0)
            java.nio.charset.Charset r2 = UTF8_CHARSET
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.wrap(r0)
            java.nio.CharBuffer r2 = r2.decode(r3)
            java.lang.String r2 = r2.toString()
            return r2
        L_0x0030:
            java.lang.String r0 = "Reached the end of bundle when a length string is expected."
            java.lang.IllegalArgumentException r0 = r4.abort(r0)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.bundle.BundleReader.readLengthPrefix():java.lang.String");
    }

    private int indexOfOpenBracket() {
        this.buffer.mark();
        int i = 0;
        while (i < this.buffer.remaining()) {
            try {
                if (this.buffer.get() == 123) {
                    return i;
                }
                i++;
            } finally {
                this.buffer.reset();
            }
        }
        this.buffer.reset();
        return -1;
    }

    private String readJsonString(int bytesToRead) throws IOException {
        ByteArrayOutputStream jsonBytes = new ByteArrayOutputStream();
        int remaining = bytesToRead;
        while (remaining > 0) {
            if (this.buffer.remaining() != 0 || pullMoreData()) {
                int read = Math.min(remaining, this.buffer.remaining());
                jsonBytes.write(this.buffer.array(), this.buffer.arrayOffset() + this.buffer.position(), read);
                ByteBuffer byteBuffer = this.buffer;
                byteBuffer.position(byteBuffer.position() + read);
                remaining -= read;
            } else {
                throw abort("Reached the end of bundle when more data was expected.");
            }
        }
        return jsonBytes.toString(UTF8_CHARSET.name());
    }

    private boolean pullMoreData() throws IOException {
        this.buffer.compact();
        int bytesRead2 = this.bundleInputStream.read(this.buffer.array(), this.buffer.arrayOffset() + this.buffer.position(), this.buffer.remaining());
        boolean readSuccess = bytesRead2 > 0;
        if (readSuccess) {
            ByteBuffer byteBuffer = this.buffer;
            byteBuffer.position(byteBuffer.position() + bytesRead2);
        }
        this.buffer.flip();
        return readSuccess;
    }

    private BundleElement decodeBundleElement(String json) throws JSONException, IOException {
        JSONObject object = new JSONObject(json);
        if (object.has("metadata")) {
            BundleMetadata metadata2 = this.serializer.decodeBundleMetadata(object.getJSONObject("metadata"));
            Logger.debug("BundleElement", "BundleMetadata element loaded", new Object[0]);
            return metadata2;
        } else if (object.has("namedQuery")) {
            NamedQuery namedQuery = this.serializer.decodeNamedQuery(object.getJSONObject("namedQuery"));
            Logger.debug("BundleElement", "Query loaded: " + namedQuery.getName(), new Object[0]);
            return namedQuery;
        } else if (object.has("documentMetadata")) {
            BundledDocumentMetadata documentMetadata = this.serializer.decodeBundledDocumentMetadata(object.getJSONObject("documentMetadata"));
            Logger.debug("BundleElement", "Document metadata loaded: " + documentMetadata.getKey(), new Object[0]);
            return documentMetadata;
        } else if (object.has("document")) {
            BundleDocument document = this.serializer.decodeDocument(object.getJSONObject("document"));
            Logger.debug("BundleElement", "Document loaded: " + document.getKey(), new Object[0]);
            return document;
        } else {
            throw abort("Cannot decode unknown Bundle element: " + json);
        }
    }

    private IllegalArgumentException abort(String message) throws IOException {
        close();
        throw new IllegalArgumentException("Invalid bundle: " + message);
    }
}
