package com.google.firebase.firestore.bundle;

import android.util.Base64;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.core.Bound;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.remote.RemoteSerializer;
import com.google.firestore.p002v1.ArrayValue;
import com.google.firestore.p002v1.MapValue;
import com.google.firestore.p002v1.Value;
import com.google.protobuf.ByteString;
import com.google.protobuf.NullValue;
import com.google.type.LatLng;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BundleSerializer {
    private static final long MILLIS_PER_SECOND = 1000;
    private final RemoteSerializer remoteSerializer;
    private final SimpleDateFormat timestampFormat;

    public BundleSerializer(RemoteSerializer remoteSerializer2) {
        this.remoteSerializer = remoteSerializer2;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        this.timestampFormat = simpleDateFormat;
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        calendar.setGregorianChange(new Date(Long.MIN_VALUE));
        simpleDateFormat.setCalendar(calendar);
    }

    public NamedQuery decodeNamedQuery(JSONObject namedQuery) throws JSONException {
        return new NamedQuery(namedQuery.getString(AppMeasurementSdk.ConditionalUserProperty.NAME), decodeBundledQuery(namedQuery.getJSONObject("bundledQuery")), decodeSnapshotVersion(namedQuery.get("readTime")));
    }

    public BundleMetadata decodeBundleMetadata(JSONObject bundleMetadata) throws JSONException {
        return new BundleMetadata(bundleMetadata.getString("id"), bundleMetadata.getInt("version"), decodeSnapshotVersion(bundleMetadata.get("createTime")), bundleMetadata.getInt("totalDocuments"), bundleMetadata.getLong("totalBytes"));
    }

    public BundledDocumentMetadata decodeBundledDocumentMetadata(JSONObject bundledDocumentMetadata) throws JSONException {
        DocumentKey key = DocumentKey.fromPath(decodeName(bundledDocumentMetadata.getString(AppMeasurementSdk.ConditionalUserProperty.NAME)));
        SnapshotVersion readTime = decodeSnapshotVersion(bundledDocumentMetadata.get("readTime"));
        boolean exists = bundledDocumentMetadata.optBoolean("exists", false);
        JSONArray queriesJson = bundledDocumentMetadata.optJSONArray("queries");
        List<String> queries = new ArrayList<>();
        if (queriesJson != null) {
            for (int i = 0; i < queriesJson.length(); i++) {
                queries.add(queriesJson.getString(i));
            }
        }
        return new BundledDocumentMetadata(key, readTime, exists, queries);
    }

    /* access modifiers changed from: package-private */
    public BundleDocument decodeDocument(JSONObject document) throws JSONException {
        DocumentKey key = DocumentKey.fromPath(decodeName(document.getString(AppMeasurementSdk.ConditionalUserProperty.NAME)));
        SnapshotVersion updateTime = decodeSnapshotVersion(document.get("updateTime"));
        Value.Builder value = Value.newBuilder();
        decodeMapValue(value, document.getJSONObject("fields"));
        return new BundleDocument(MutableDocument.newFoundDocument(key, updateTime, ObjectValue.fromMap(value.getMapValue().getFieldsMap())));
    }

    private ResourcePath decodeName(String name) {
        ResourcePath resourcePath = ResourcePath.fromString(name);
        if (this.remoteSerializer.isLocalResourceName(resourcePath)) {
            return (ResourcePath) resourcePath.popFirst(5);
        }
        throw new IllegalArgumentException("Resource name is not valid for current instance: " + name);
    }

    private SnapshotVersion decodeSnapshotVersion(Object timestamp) throws JSONException {
        return new SnapshotVersion(decodeTimestamp(timestamp));
    }

    /* JADX WARNING: type inference failed for: r8v11, types: [com.google.firebase.firestore.model.BasePath] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.firebase.firestore.bundle.BundledQuery decodeBundledQuery(org.json.JSONObject r25) throws org.json.JSONException {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            java.lang.String r2 = "structuredQuery"
            org.json.JSONObject r2 = r1.getJSONObject(r2)
            r0.verifyNoSelect(r2)
            java.lang.String r3 = "parent"
            java.lang.String r3 = r1.getString(r3)
            com.google.firebase.firestore.model.ResourcePath r3 = r0.decodeName(r3)
            java.lang.String r4 = "from"
            org.json.JSONArray r4 = r2.getJSONArray(r4)
            r0.verifyCollectionSelector(r4)
            r5 = 0
            org.json.JSONObject r6 = r4.getJSONObject(r5)
            java.lang.String r7 = "allDescendants"
            boolean r5 = r6.optBoolean(r7, r5)
            r7 = 0
            java.lang.String r8 = "collectionId"
            if (r5 == 0) goto L_0x0035
            java.lang.String r7 = r6.getString(r8)
            goto L_0x0040
        L_0x0035:
            java.lang.String r8 = r6.getString(r8)
            com.google.firebase.firestore.model.BasePath r8 = r3.append((java.lang.String) r8)
            r3 = r8
            com.google.firebase.firestore.model.ResourcePath r3 = (com.google.firebase.firestore.model.ResourcePath) r3
        L_0x0040:
            java.lang.String r8 = "where"
            org.json.JSONObject r8 = r2.optJSONObject(r8)
            java.util.List r17 = r0.decodeWhere(r8)
            java.lang.String r8 = "orderBy"
            org.json.JSONArray r8 = r2.optJSONArray(r8)
            java.util.List r18 = r0.decodeOrderBy(r8)
            java.lang.String r8 = "startAt"
            org.json.JSONObject r8 = r2.optJSONObject(r8)
            com.google.firebase.firestore.core.Bound r19 = r0.decodeStartAtBound(r8)
            java.lang.String r8 = "endAt"
            org.json.JSONObject r8 = r2.optJSONObject(r8)
            com.google.firebase.firestore.core.Bound r20 = r0.decodeEndAtBound(r8)
            r0.verifyNoOffset(r2)
            int r15 = r0.decodeLimit(r2)
            com.google.firebase.firestore.core.Query$LimitType r13 = r24.decodeLimitType(r25)
            com.google.firebase.firestore.bundle.BundledQuery r14 = new com.google.firebase.firestore.bundle.BundledQuery
            com.google.firebase.firestore.core.Target r12 = new com.google.firebase.firestore.core.Target
            long r10 = (long) r15
            r8 = r12
            r9 = r3
            r21 = r10
            r10 = r7
            r11 = r17
            r0 = r12
            r12 = r18
            r23 = r2
            r1 = r13
            r2 = r14
            r13 = r21
            r21 = r15
            r15 = r19
            r16 = r20
            r8.<init>(r9, r10, r11, r12, r13, r15, r16)
            r2.<init>(r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.bundle.BundleSerializer.decodeBundledQuery(org.json.JSONObject):com.google.firebase.firestore.bundle.BundledQuery");
    }

    private int decodeLimit(JSONObject structuredQuery) {
        JSONObject limit = structuredQuery.optJSONObject("limit");
        if (limit != null) {
            return limit.optInt("value", -1);
        }
        return structuredQuery.optInt("limit", -1);
    }

    private Bound decodeStartAtBound(JSONObject bound) throws JSONException {
        if (bound == null) {
            return null;
        }
        return new Bound(decodePosition(bound), bound.optBoolean("before", false));
    }

    private Bound decodeEndAtBound(JSONObject bound) throws JSONException {
        if (bound == null) {
            return null;
        }
        return new Bound(decodePosition(bound), !bound.optBoolean("before", false));
    }

    private List<Value> decodePosition(JSONObject bound) throws JSONException {
        List<Value> cursor = new ArrayList<>();
        JSONArray values = bound.optJSONArray("values");
        if (values != null) {
            for (int i = 0; i < values.length(); i++) {
                cursor.add(decodeValue(values.getJSONObject(i)));
            }
        }
        return cursor;
    }

    private List<OrderBy> decodeOrderBy(JSONArray orderBys) throws JSONException {
        OrderBy.Direction direction;
        List<OrderBy> result = new ArrayList<>();
        if (orderBys != null) {
            for (int i = 0; i < orderBys.length(); i++) {
                JSONObject orderBy = orderBys.getJSONObject(i);
                FieldPath fieldPath = decodeFieldReference(orderBy.getJSONObject("field"));
                if (orderBy.optString("direction", "ASCENDING").equals("ASCENDING")) {
                    direction = OrderBy.Direction.ASCENDING;
                } else {
                    direction = OrderBy.Direction.DESCENDING;
                }
                result.add(OrderBy.getInstance(direction, fieldPath));
            }
        }
        return result;
    }

    private List<Filter> decodeWhere(JSONObject where) throws JSONException {
        List<Filter> result = new ArrayList<>();
        if (where != null) {
            decodeFilter(result, where);
        }
        return result;
    }

    private void decodeFilter(List<Filter> result, JSONObject structuredQuery) throws JSONException {
        if (structuredQuery.has("compositeFilter")) {
            decodeCompositeFilter(result, structuredQuery.getJSONObject("compositeFilter"));
        } else if (structuredQuery.has("fieldFilter")) {
            decodeFieldFilter(result, structuredQuery.getJSONObject("fieldFilter"));
        } else if (structuredQuery.has("unaryFilter")) {
            decodeUnaryFilter(result, structuredQuery.getJSONObject("unaryFilter"));
        }
    }

    private void decodeCompositeFilter(List<Filter> result, JSONObject compositeFilter) throws JSONException {
        if (compositeFilter.getString("op").equals("AND")) {
            JSONArray filters = compositeFilter.optJSONArray("filters");
            if (filters != null) {
                for (int i = 0; i < filters.length(); i++) {
                    decodeFilter(result, filters.getJSONObject(i));
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("The Android SDK only supports composite filters of type 'AND'");
    }

    private void decodeFieldFilter(List<Filter> result, JSONObject fieldFilter) throws JSONException {
        result.add(FieldFilter.create(decodeFieldReference(fieldFilter.getJSONObject("field")), decodeFieldFilterOperator(fieldFilter.getString("op")), decodeValue(fieldFilter.getJSONObject("value"))));
    }

    private Value decodeValue(JSONObject value) throws JSONException {
        Value.Builder builder = Value.newBuilder();
        if (value.has("nullValue")) {
            builder.setNullValue(NullValue.NULL_VALUE);
        } else if (value.has("booleanValue")) {
            builder.setBooleanValue(value.optBoolean("booleanValue", false));
        } else if (value.has("integerValue")) {
            builder.setIntegerValue(value.optLong("integerValue"));
        } else if (value.has("doubleValue")) {
            builder.setDoubleValue(value.optDouble("doubleValue"));
        } else if (value.has("timestampValue")) {
            decodeTimestamp(builder, value.get("timestampValue"));
        } else if (value.has("stringValue")) {
            builder.setStringValue(value.optString("stringValue", ""));
        } else if (value.has("bytesValue")) {
            builder.setBytesValue(ByteString.copyFrom(Base64.decode(value.getString("bytesValue"), 0)));
        } else if (value.has("referenceValue")) {
            builder.setReferenceValue(value.getString("referenceValue"));
        } else if (value.has("geoPointValue")) {
            decodeGeoPoint(builder, value.getJSONObject("geoPointValue"));
        } else if (value.has("arrayValue")) {
            decodeArrayValue(builder, value.getJSONObject("arrayValue").optJSONArray("values"));
        } else if (value.has("mapValue")) {
            decodeMapValue(builder, value.getJSONObject("mapValue").optJSONObject("fields"));
        } else {
            throw new IllegalArgumentException("Unexpected value type: " + value);
        }
        return (Value) builder.build();
    }

    private void decodeArrayValue(Value.Builder builder, JSONArray values) throws JSONException {
        ArrayValue.Builder arrayBuilder = ArrayValue.newBuilder();
        if (values != null) {
            for (int i = 0; i < values.length(); i++) {
                arrayBuilder.addValues(decodeValue(values.getJSONObject(i)));
            }
        }
        builder.setArrayValue(arrayBuilder);
    }

    private void decodeMapValue(Value.Builder builder, JSONObject map) throws JSONException {
        MapValue.Builder mapBuilder = MapValue.newBuilder();
        if (map != null) {
            Iterator<String> it = map.keys();
            while (it.hasNext()) {
                String key = it.next();
                mapBuilder.putFields(key, decodeValue(map.getJSONObject(key)));
            }
        }
        builder.setMapValue(mapBuilder);
    }

    private void decodeGeoPoint(Value.Builder builder, JSONObject geoPoint) {
        builder.setGeoPointValue(LatLng.newBuilder().setLatitude(geoPoint.optDouble("latitude")).setLongitude(geoPoint.optDouble("longitude")));
    }

    private Timestamp decodeTimestamp(JSONObject timestamp) {
        return new Timestamp(timestamp.optLong("seconds"), timestamp.optInt("nanos"));
    }

    private Timestamp decodeTimestamp(String timestamp) {
        String str = timestamp;
        try {
            int dayOffset = str.indexOf(84);
            if (dayOffset != -1) {
                int timezoneOffsetPosition = str.indexOf(90, dayOffset);
                if (timezoneOffsetPosition == -1) {
                    timezoneOffsetPosition = str.indexOf(43, dayOffset);
                }
                if (timezoneOffsetPosition == -1) {
                    timezoneOffsetPosition = str.indexOf(45, dayOffset);
                }
                if (timezoneOffsetPosition != -1) {
                    int nanos = 0;
                    String timeValue = str.substring(0, timezoneOffsetPosition);
                    String secondValue = timeValue;
                    String nanoValue = "";
                    int pointPosition = timeValue.indexOf(46);
                    if (pointPosition != -1) {
                        secondValue = timeValue.substring(0, pointPosition);
                        nanoValue = timeValue.substring(pointPosition + 1);
                    }
                    try {
                        long seconds = this.timestampFormat.parse(secondValue).getTime() / 1000;
                        if (!nanoValue.isEmpty()) {
                            nanos = parseNanos(nanoValue);
                        }
                        if (str.charAt(timezoneOffsetPosition) != 'Z') {
                            long offset = decodeTimezoneOffset(str.substring(timezoneOffsetPosition + 1));
                            int i = dayOffset;
                            if (str.charAt(timezoneOffsetPosition) == '+') {
                                seconds -= offset;
                            } else {
                                seconds += offset;
                            }
                        } else if (timestamp.length() == timezoneOffsetPosition + 1) {
                            int i2 = dayOffset;
                        } else {
                            throw new IllegalArgumentException("Invalid timestamp: Invalid trailing data \"" + str.substring(timezoneOffsetPosition) + "\"");
                        }
                        return new Timestamp(seconds, nanos);
                    } catch (ParseException e) {
                        e = e;
                        throw new IllegalArgumentException("Failed to parse timestamp", e);
                    }
                } else {
                    int i3 = dayOffset;
                    throw new IllegalArgumentException("Invalid timestamp: Missing valid timezone offset: " + str);
                }
            } else {
                int i4 = dayOffset;
                throw new IllegalArgumentException("Invalid timestamp: " + str);
            }
        } catch (ParseException e2) {
            e = e2;
            throw new IllegalArgumentException("Failed to parse timestamp", e);
        }
    }

    private Timestamp decodeTimestamp(Object timestamp) throws JSONException {
        if (timestamp instanceof String) {
            return decodeTimestamp((String) timestamp);
        }
        if (timestamp instanceof JSONObject) {
            return decodeTimestamp((JSONObject) timestamp);
        }
        throw new IllegalArgumentException("Timestamps must be either ISO 8601-formatted strings or JSON objects");
    }

    private void decodeTimestamp(Value.Builder builder, Object timestamp) throws JSONException {
        Timestamp decoded = decodeTimestamp(timestamp);
        builder.setTimestampValue(com.google.protobuf.Timestamp.newBuilder().setSeconds(decoded.getSeconds()).setNanos(decoded.getNanoseconds()));
    }

    private static int parseNanos(String value) {
        int result = 0;
        for (int i = 0; i < 9; i++) {
            result *= 10;
            if (i < value.length()) {
                if (value.charAt(i) < '0' || value.charAt(i) > '9') {
                    throw new IllegalArgumentException("Invalid nanoseconds: " + value);
                }
                result += value.charAt(i) - '0';
            }
        }
        return result;
    }

    private static long decodeTimezoneOffset(String value) {
        int pos = value.indexOf(58);
        if (pos != -1) {
            return ((Long.parseLong(value.substring(0, pos)) * 60) + Long.parseLong(value.substring(pos + 1))) * 60;
        }
        throw new IllegalArgumentException("Invalid offset value: " + value);
    }

    private FieldFilter.Operator decodeFieldFilterOperator(String operator) {
        return FieldFilter.Operator.valueOf(operator);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void decodeUnaryFilter(java.util.List<com.google.firebase.firestore.core.Filter> r6, org.json.JSONObject r7) throws org.json.JSONException {
        /*
            r5 = this;
            java.lang.String r0 = "field"
            org.json.JSONObject r0 = r7.getJSONObject(r0)
            com.google.firebase.firestore.model.FieldPath r0 = r5.decodeFieldReference(r0)
            java.lang.String r1 = "op"
            java.lang.String r1 = r7.getString(r1)
            int r2 = r1.hashCode()
            switch(r2) {
                case -2125479834: goto L_0x0036;
                case -1465346180: goto L_0x002c;
                case -244195494: goto L_0x0022;
                case 1019893512: goto L_0x0018;
                default: goto L_0x0017;
            }
        L_0x0017:
            goto L_0x0040
        L_0x0018:
            java.lang.String r2 = "IS_NOT_NULL"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0017
            r2 = 3
            goto L_0x0041
        L_0x0022:
            java.lang.String r2 = "IS_NOT_NAN"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0017
            r2 = 2
            goto L_0x0041
        L_0x002c:
            java.lang.String r2 = "IS_NULL"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0017
            r2 = 1
            goto L_0x0041
        L_0x0036:
            java.lang.String r2 = "IS_NAN"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0017
            r2 = 0
            goto L_0x0041
        L_0x0040:
            r2 = -1
        L_0x0041:
            switch(r2) {
                case 0: goto L_0x0081;
                case 1: goto L_0x0075;
                case 2: goto L_0x0069;
                case 3: goto L_0x005d;
                default: goto L_0x0044;
            }
        L_0x0044:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Unexpected unary filter: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r1)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        L_0x005d:
            com.google.firebase.firestore.core.FieldFilter$Operator r2 = com.google.firebase.firestore.core.FieldFilter.Operator.NOT_EQUAL
            com.google.firestore.v1.Value r3 = com.google.firebase.firestore.model.Values.NULL_VALUE
            com.google.firebase.firestore.core.FieldFilter r2 = com.google.firebase.firestore.core.FieldFilter.create(r0, r2, r3)
            r6.add(r2)
            goto L_0x008d
        L_0x0069:
            com.google.firebase.firestore.core.FieldFilter$Operator r2 = com.google.firebase.firestore.core.FieldFilter.Operator.NOT_EQUAL
            com.google.firestore.v1.Value r3 = com.google.firebase.firestore.model.Values.NAN_VALUE
            com.google.firebase.firestore.core.FieldFilter r2 = com.google.firebase.firestore.core.FieldFilter.create(r0, r2, r3)
            r6.add(r2)
            goto L_0x008d
        L_0x0075:
            com.google.firebase.firestore.core.FieldFilter$Operator r2 = com.google.firebase.firestore.core.FieldFilter.Operator.EQUAL
            com.google.firestore.v1.Value r3 = com.google.firebase.firestore.model.Values.NULL_VALUE
            com.google.firebase.firestore.core.FieldFilter r2 = com.google.firebase.firestore.core.FieldFilter.create(r0, r2, r3)
            r6.add(r2)
            goto L_0x008d
        L_0x0081:
            com.google.firebase.firestore.core.FieldFilter$Operator r2 = com.google.firebase.firestore.core.FieldFilter.Operator.EQUAL
            com.google.firestore.v1.Value r3 = com.google.firebase.firestore.model.Values.NAN_VALUE
            com.google.firebase.firestore.core.FieldFilter r2 = com.google.firebase.firestore.core.FieldFilter.create(r0, r2, r3)
            r6.add(r2)
        L_0x008d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.bundle.BundleSerializer.decodeUnaryFilter(java.util.List, org.json.JSONObject):void");
    }

    private FieldPath decodeFieldReference(JSONObject fieldReference) throws JSONException {
        return FieldPath.fromServerFormat(fieldReference.getString("fieldPath"));
    }

    private Query.LimitType decodeLimitType(JSONObject bundledQuery) {
        String limitType = bundledQuery.optString("limitType", "FIRST");
        if (limitType.equals("FIRST")) {
            return Query.LimitType.LIMIT_TO_FIRST;
        }
        if (limitType.equals("LAST")) {
            return Query.LimitType.LIMIT_TO_LAST;
        }
        throw new IllegalArgumentException("Invalid limit type for bundle query: " + limitType);
    }

    private void verifyCollectionSelector(JSONArray from) {
        if (from.length() != 1) {
            throw new IllegalArgumentException("Only queries with a single 'from' clause are supported by the Android SDK");
        }
    }

    private void verifyNoOffset(JSONObject structuredQuery) {
        if (structuredQuery.has(TypedValues.CycleType.S_WAVE_OFFSET)) {
            throw new IllegalArgumentException("Queries with offsets are not supported by the Android SDK");
        }
    }

    private void verifyNoSelect(JSONObject structuredQuery) {
        if (structuredQuery.has("select")) {
            throw new IllegalArgumentException("Queries with 'select' statements are not supported by the Android SDK");
        }
    }
}
