package p004io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: io.grpc.internal.JsonParser */
public final class JsonParser {
    private static final Logger logger = Logger.getLogger(JsonParser.class.getName());

    private JsonParser() {
    }

    public static Object parse(String raw) throws IOException {
        JsonReader jr = new JsonReader(new StringReader(raw));
        try {
            return parseRecursive(jr);
        } finally {
            try {
                jr.close();
            } catch (IOException e) {
                logger.log(Level.WARNING, "Failed to close", e);
            }
        }
    }

    /* renamed from: io.grpc.internal.JsonParser$1 */
    static /* synthetic */ class C12621 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken;

        static {
            int[] iArr = new int[JsonToken.values().length];
            $SwitchMap$com$google$gson$stream$JsonToken = iArr;
            try {
                iArr[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    private static Object parseRecursive(JsonReader jr) throws IOException {
        Preconditions.checkState(jr.hasNext(), "unexpected end of JSON");
        switch (C12621.$SwitchMap$com$google$gson$stream$JsonToken[jr.peek().ordinal()]) {
            case 1:
                return parseJsonArray(jr);
            case 2:
                return parseJsonObject(jr);
            case 3:
                return jr.nextString();
            case 4:
                return Double.valueOf(jr.nextDouble());
            case 5:
                return Boolean.valueOf(jr.nextBoolean());
            case 6:
                return parseJsonNull(jr);
            default:
                throw new IllegalStateException("Bad token: " + jr.getPath());
        }
    }

    private static Map<String, ?> parseJsonObject(JsonReader jr) throws IOException {
        jr.beginObject();
        Map<String, Object> obj = new LinkedHashMap<>();
        while (jr.hasNext()) {
            obj.put(jr.nextName(), parseRecursive(jr));
        }
        Preconditions.checkState(jr.peek() == JsonToken.END_OBJECT, "Bad token: " + jr.getPath());
        jr.endObject();
        return Collections.unmodifiableMap(obj);
    }

    private static List<?> parseJsonArray(JsonReader jr) throws IOException {
        jr.beginArray();
        List<Object> array = new ArrayList<>();
        while (jr.hasNext()) {
            array.add(parseRecursive(jr));
        }
        Preconditions.checkState(jr.peek() == JsonToken.END_ARRAY, "Bad token: " + jr.getPath());
        jr.endArray();
        return Collections.unmodifiableList(array);
    }

    private static Void parseJsonNull(JsonReader jr) throws IOException {
        jr.nextNull();
        return null;
    }
}
