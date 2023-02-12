package com.google.firestore.p002v1;

import com.google.firestore.p002v1.Cursor;
import com.google.firestore.p002v1.Value;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.firestore.v1.StructuredQuery */
public final class StructuredQuery extends GeneratedMessageLite<StructuredQuery, Builder> implements StructuredQueryOrBuilder {
    /* access modifiers changed from: private */
    public static final StructuredQuery DEFAULT_INSTANCE;
    public static final int END_AT_FIELD_NUMBER = 8;
    public static final int FROM_FIELD_NUMBER = 2;
    public static final int LIMIT_FIELD_NUMBER = 5;
    public static final int OFFSET_FIELD_NUMBER = 6;
    public static final int ORDER_BY_FIELD_NUMBER = 4;
    private static volatile Parser<StructuredQuery> PARSER = null;
    public static final int SELECT_FIELD_NUMBER = 1;
    public static final int START_AT_FIELD_NUMBER = 7;
    public static final int WHERE_FIELD_NUMBER = 3;
    private Cursor endAt_;
    private Internal.ProtobufList<CollectionSelector> from_ = emptyProtobufList();
    private Int32Value limit_;
    private int offset_;
    private Internal.ProtobufList<Order> orderBy_ = emptyProtobufList();
    private Projection select_;
    private Cursor startAt_;
    private Filter where_;

    /* renamed from: com.google.firestore.v1.StructuredQuery$CollectionSelectorOrBuilder */
    public interface CollectionSelectorOrBuilder extends MessageLiteOrBuilder {
        boolean getAllDescendants();

        String getCollectionId();

        ByteString getCollectionIdBytes();
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$CompositeFilterOrBuilder */
    public interface CompositeFilterOrBuilder extends MessageLiteOrBuilder {
        Filter getFilters(int i);

        int getFiltersCount();

        List<Filter> getFiltersList();

        CompositeFilter.Operator getOp();

        int getOpValue();
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$FieldFilterOrBuilder */
    public interface FieldFilterOrBuilder extends MessageLiteOrBuilder {
        FieldReference getField();

        FieldFilter.Operator getOp();

        int getOpValue();

        Value getValue();

        boolean hasField();

        boolean hasValue();
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$FieldReferenceOrBuilder */
    public interface FieldReferenceOrBuilder extends MessageLiteOrBuilder {
        String getFieldPath();

        ByteString getFieldPathBytes();
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$FilterOrBuilder */
    public interface FilterOrBuilder extends MessageLiteOrBuilder {
        CompositeFilter getCompositeFilter();

        FieldFilter getFieldFilter();

        Filter.FilterTypeCase getFilterTypeCase();

        UnaryFilter getUnaryFilter();

        boolean hasCompositeFilter();

        boolean hasFieldFilter();

        boolean hasUnaryFilter();
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$OrderOrBuilder */
    public interface OrderOrBuilder extends MessageLiteOrBuilder {
        Direction getDirection();

        int getDirectionValue();

        FieldReference getField();

        boolean hasField();
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$ProjectionOrBuilder */
    public interface ProjectionOrBuilder extends MessageLiteOrBuilder {
        FieldReference getFields(int i);

        int getFieldsCount();

        List<FieldReference> getFieldsList();
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$UnaryFilterOrBuilder */
    public interface UnaryFilterOrBuilder extends MessageLiteOrBuilder {
        FieldReference getField();

        UnaryFilter.Operator getOp();

        int getOpValue();

        UnaryFilter.OperandTypeCase getOperandTypeCase();

        boolean hasField();
    }

    private StructuredQuery() {
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$Direction */
    public enum Direction implements Internal.EnumLite {
        DIRECTION_UNSPECIFIED(0),
        ASCENDING(1),
        DESCENDING(2),
        UNRECOGNIZED(-1);
        
        public static final int ASCENDING_VALUE = 1;
        public static final int DESCENDING_VALUE = 2;
        public static final int DIRECTION_UNSPECIFIED_VALUE = 0;
        private static final Internal.EnumLiteMap<Direction> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<Direction>() {
                public Direction findValueByNumber(int number) {
                    return Direction.forNumber(number);
                }
            };
        }

        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static Direction valueOf(int value2) {
            return forNumber(value2);
        }

        public static Direction forNumber(int value2) {
            switch (value2) {
                case 0:
                    return DIRECTION_UNSPECIFIED;
                case 1:
                    return ASCENDING;
                case 2:
                    return DESCENDING;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<Direction> internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return DirectionVerifier.INSTANCE;
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$Direction$DirectionVerifier */
        private static final class DirectionVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = null;

            private DirectionVerifier() {
            }

            static {
                INSTANCE = new DirectionVerifier();
            }

            public boolean isInRange(int number) {
                return Direction.forNumber(number) != null;
            }
        }

        private Direction(int value2) {
            this.value = value2;
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$CollectionSelector */
    public static final class CollectionSelector extends GeneratedMessageLite<CollectionSelector, Builder> implements CollectionSelectorOrBuilder {
        public static final int ALL_DESCENDANTS_FIELD_NUMBER = 3;
        public static final int COLLECTION_ID_FIELD_NUMBER = 2;
        /* access modifiers changed from: private */
        public static final CollectionSelector DEFAULT_INSTANCE;
        private static volatile Parser<CollectionSelector> PARSER;
        private boolean allDescendants_;
        private String collectionId_ = "";

        private CollectionSelector() {
        }

        public String getCollectionId() {
            return this.collectionId_;
        }

        public ByteString getCollectionIdBytes() {
            return ByteString.copyFromUtf8(this.collectionId_);
        }

        /* access modifiers changed from: private */
        public void setCollectionId(String value) {
            value.getClass();
            this.collectionId_ = value;
        }

        /* access modifiers changed from: private */
        public void clearCollectionId() {
            this.collectionId_ = getDefaultInstance().getCollectionId();
        }

        /* access modifiers changed from: private */
        public void setCollectionIdBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.collectionId_ = value.toStringUtf8();
        }

        public boolean getAllDescendants() {
            return this.allDescendants_;
        }

        /* access modifiers changed from: private */
        public void setAllDescendants(boolean value) {
            this.allDescendants_ = value;
        }

        /* access modifiers changed from: private */
        public void clearAllDescendants() {
            this.allDescendants_ = false;
        }

        public static CollectionSelector parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CollectionSelector parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CollectionSelector parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CollectionSelector parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CollectionSelector parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CollectionSelector parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CollectionSelector parseFrom(InputStream input) throws IOException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CollectionSelector parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CollectionSelector parseDelimitedFrom(InputStream input) throws IOException {
            return (CollectionSelector) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static CollectionSelector parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CollectionSelector) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CollectionSelector parseFrom(CodedInputStream input) throws IOException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CollectionSelector parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CollectionSelector) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(CollectionSelector prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$CollectionSelector$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<CollectionSelector, Builder> implements CollectionSelectorOrBuilder {
            /* synthetic */ Builder(C08571 x0) {
                this();
            }

            private Builder() {
                super(CollectionSelector.DEFAULT_INSTANCE);
            }

            public String getCollectionId() {
                return ((CollectionSelector) this.instance).getCollectionId();
            }

            public ByteString getCollectionIdBytes() {
                return ((CollectionSelector) this.instance).getCollectionIdBytes();
            }

            public Builder setCollectionId(String value) {
                copyOnWrite();
                ((CollectionSelector) this.instance).setCollectionId(value);
                return this;
            }

            public Builder clearCollectionId() {
                copyOnWrite();
                ((CollectionSelector) this.instance).clearCollectionId();
                return this;
            }

            public Builder setCollectionIdBytes(ByteString value) {
                copyOnWrite();
                ((CollectionSelector) this.instance).setCollectionIdBytes(value);
                return this;
            }

            public boolean getAllDescendants() {
                return ((CollectionSelector) this.instance).getAllDescendants();
            }

            public Builder setAllDescendants(boolean value) {
                copyOnWrite();
                ((CollectionSelector) this.instance).setAllDescendants(value);
                return this;
            }

            public Builder clearAllDescendants() {
                copyOnWrite();
                ((CollectionSelector) this.instance).clearAllDescendants();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C08571.f230xa1df5c61[method.ordinal()]) {
                case 1:
                    return new CollectionSelector();
                case 2:
                    return new Builder((C08571) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002Ȉ\u0003\u0007", new Object[]{"collectionId_", "allDescendants_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<CollectionSelector> parser = PARSER;
                    if (parser == null) {
                        synchronized (CollectionSelector.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            CollectionSelector defaultInstance = new CollectionSelector();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(CollectionSelector.class, defaultInstance);
        }

        public static CollectionSelector getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CollectionSelector> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$1 */
    static /* synthetic */ class C08571 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f230xa1df5c61;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            f230xa1df5c61 = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f230xa1df5c61[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f230xa1df5c61[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f230xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f230xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f230xa1df5c61[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f230xa1df5c61[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$Filter */
    public static final class Filter extends GeneratedMessageLite<Filter, Builder> implements FilterOrBuilder {
        public static final int COMPOSITE_FILTER_FIELD_NUMBER = 1;
        /* access modifiers changed from: private */
        public static final Filter DEFAULT_INSTANCE;
        public static final int FIELD_FILTER_FIELD_NUMBER = 2;
        private static volatile Parser<Filter> PARSER = null;
        public static final int UNARY_FILTER_FIELD_NUMBER = 3;
        private int filterTypeCase_ = 0;
        private Object filterType_;

        private Filter() {
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$Filter$FilterTypeCase */
        public enum FilterTypeCase {
            COMPOSITE_FILTER(1),
            FIELD_FILTER(2),
            UNARY_FILTER(3),
            FILTERTYPE_NOT_SET(0);
            
            private final int value;

            private FilterTypeCase(int value2) {
                this.value = value2;
            }

            @Deprecated
            public static FilterTypeCase valueOf(int value2) {
                return forNumber(value2);
            }

            public static FilterTypeCase forNumber(int value2) {
                switch (value2) {
                    case 0:
                        return FILTERTYPE_NOT_SET;
                    case 1:
                        return COMPOSITE_FILTER;
                    case 2:
                        return FIELD_FILTER;
                    case 3:
                        return UNARY_FILTER;
                    default:
                        return null;
                }
            }

            public int getNumber() {
                return this.value;
            }
        }

        public FilterTypeCase getFilterTypeCase() {
            return FilterTypeCase.forNumber(this.filterTypeCase_);
        }

        /* access modifiers changed from: private */
        public void clearFilterType() {
            this.filterTypeCase_ = 0;
            this.filterType_ = null;
        }

        public boolean hasCompositeFilter() {
            return this.filterTypeCase_ == 1;
        }

        public CompositeFilter getCompositeFilter() {
            if (this.filterTypeCase_ == 1) {
                return (CompositeFilter) this.filterType_;
            }
            return CompositeFilter.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setCompositeFilter(CompositeFilter value) {
            value.getClass();
            this.filterType_ = value;
            this.filterTypeCase_ = 1;
        }

        /* access modifiers changed from: private */
        public void mergeCompositeFilter(CompositeFilter value) {
            value.getClass();
            if (this.filterTypeCase_ != 1 || this.filterType_ == CompositeFilter.getDefaultInstance()) {
                this.filterType_ = value;
            } else {
                this.filterType_ = ((CompositeFilter.Builder) CompositeFilter.newBuilder((CompositeFilter) this.filterType_).mergeFrom(value)).buildPartial();
            }
            this.filterTypeCase_ = 1;
        }

        /* access modifiers changed from: private */
        public void clearCompositeFilter() {
            if (this.filterTypeCase_ == 1) {
                this.filterTypeCase_ = 0;
                this.filterType_ = null;
            }
        }

        public boolean hasFieldFilter() {
            return this.filterTypeCase_ == 2;
        }

        public FieldFilter getFieldFilter() {
            if (this.filterTypeCase_ == 2) {
                return (FieldFilter) this.filterType_;
            }
            return FieldFilter.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setFieldFilter(FieldFilter value) {
            value.getClass();
            this.filterType_ = value;
            this.filterTypeCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void mergeFieldFilter(FieldFilter value) {
            value.getClass();
            if (this.filterTypeCase_ != 2 || this.filterType_ == FieldFilter.getDefaultInstance()) {
                this.filterType_ = value;
            } else {
                this.filterType_ = ((FieldFilter.Builder) FieldFilter.newBuilder((FieldFilter) this.filterType_).mergeFrom(value)).buildPartial();
            }
            this.filterTypeCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void clearFieldFilter() {
            if (this.filterTypeCase_ == 2) {
                this.filterTypeCase_ = 0;
                this.filterType_ = null;
            }
        }

        public boolean hasUnaryFilter() {
            return this.filterTypeCase_ == 3;
        }

        public UnaryFilter getUnaryFilter() {
            if (this.filterTypeCase_ == 3) {
                return (UnaryFilter) this.filterType_;
            }
            return UnaryFilter.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setUnaryFilter(UnaryFilter value) {
            value.getClass();
            this.filterType_ = value;
            this.filterTypeCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void mergeUnaryFilter(UnaryFilter value) {
            value.getClass();
            if (this.filterTypeCase_ != 3 || this.filterType_ == UnaryFilter.getDefaultInstance()) {
                this.filterType_ = value;
            } else {
                this.filterType_ = ((UnaryFilter.Builder) UnaryFilter.newBuilder((UnaryFilter) this.filterType_).mergeFrom(value)).buildPartial();
            }
            this.filterTypeCase_ = 3;
        }

        /* access modifiers changed from: private */
        public void clearUnaryFilter() {
            if (this.filterTypeCase_ == 3) {
                this.filterTypeCase_ = 0;
                this.filterType_ = null;
            }
        }

        public static Filter parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Filter parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Filter parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Filter parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Filter parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Filter parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Filter parseFrom(InputStream input) throws IOException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Filter parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Filter parseDelimitedFrom(InputStream input) throws IOException {
            return (Filter) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Filter parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Filter) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Filter parseFrom(CodedInputStream input) throws IOException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Filter parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Filter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Filter prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$Filter$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<Filter, Builder> implements FilterOrBuilder {
            /* synthetic */ Builder(C08571 x0) {
                this();
            }

            private Builder() {
                super(Filter.DEFAULT_INSTANCE);
            }

            public FilterTypeCase getFilterTypeCase() {
                return ((Filter) this.instance).getFilterTypeCase();
            }

            public Builder clearFilterType() {
                copyOnWrite();
                ((Filter) this.instance).clearFilterType();
                return this;
            }

            public boolean hasCompositeFilter() {
                return ((Filter) this.instance).hasCompositeFilter();
            }

            public CompositeFilter getCompositeFilter() {
                return ((Filter) this.instance).getCompositeFilter();
            }

            public Builder setCompositeFilter(CompositeFilter value) {
                copyOnWrite();
                ((Filter) this.instance).setCompositeFilter(value);
                return this;
            }

            public Builder setCompositeFilter(CompositeFilter.Builder builderForValue) {
                copyOnWrite();
                ((Filter) this.instance).setCompositeFilter((CompositeFilter) builderForValue.build());
                return this;
            }

            public Builder mergeCompositeFilter(CompositeFilter value) {
                copyOnWrite();
                ((Filter) this.instance).mergeCompositeFilter(value);
                return this;
            }

            public Builder clearCompositeFilter() {
                copyOnWrite();
                ((Filter) this.instance).clearCompositeFilter();
                return this;
            }

            public boolean hasFieldFilter() {
                return ((Filter) this.instance).hasFieldFilter();
            }

            public FieldFilter getFieldFilter() {
                return ((Filter) this.instance).getFieldFilter();
            }

            public Builder setFieldFilter(FieldFilter value) {
                copyOnWrite();
                ((Filter) this.instance).setFieldFilter(value);
                return this;
            }

            public Builder setFieldFilter(FieldFilter.Builder builderForValue) {
                copyOnWrite();
                ((Filter) this.instance).setFieldFilter((FieldFilter) builderForValue.build());
                return this;
            }

            public Builder mergeFieldFilter(FieldFilter value) {
                copyOnWrite();
                ((Filter) this.instance).mergeFieldFilter(value);
                return this;
            }

            public Builder clearFieldFilter() {
                copyOnWrite();
                ((Filter) this.instance).clearFieldFilter();
                return this;
            }

            public boolean hasUnaryFilter() {
                return ((Filter) this.instance).hasUnaryFilter();
            }

            public UnaryFilter getUnaryFilter() {
                return ((Filter) this.instance).getUnaryFilter();
            }

            public Builder setUnaryFilter(UnaryFilter value) {
                copyOnWrite();
                ((Filter) this.instance).setUnaryFilter(value);
                return this;
            }

            public Builder setUnaryFilter(UnaryFilter.Builder builderForValue) {
                copyOnWrite();
                ((Filter) this.instance).setUnaryFilter((UnaryFilter) builderForValue.build());
                return this;
            }

            public Builder mergeUnaryFilter(UnaryFilter value) {
                copyOnWrite();
                ((Filter) this.instance).mergeUnaryFilter(value);
                return this;
            }

            public Builder clearUnaryFilter() {
                copyOnWrite();
                ((Filter) this.instance).clearUnaryFilter();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C08571.f230xa1df5c61[method.ordinal()]) {
                case 1:
                    return new Filter();
                case 2:
                    return new Builder((C08571) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000", new Object[]{"filterType_", "filterTypeCase_", CompositeFilter.class, FieldFilter.class, UnaryFilter.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Filter> parser = PARSER;
                    if (parser == null) {
                        synchronized (Filter.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            Filter defaultInstance = new Filter();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Filter.class, defaultInstance);
        }

        public static Filter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Filter> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$CompositeFilter */
    public static final class CompositeFilter extends GeneratedMessageLite<CompositeFilter, Builder> implements CompositeFilterOrBuilder {
        /* access modifiers changed from: private */
        public static final CompositeFilter DEFAULT_INSTANCE;
        public static final int FILTERS_FIELD_NUMBER = 2;
        public static final int OP_FIELD_NUMBER = 1;
        private static volatile Parser<CompositeFilter> PARSER;
        private Internal.ProtobufList<Filter> filters_ = emptyProtobufList();
        private int op_;

        private CompositeFilter() {
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$CompositeFilter$Operator */
        public enum Operator implements Internal.EnumLite {
            OPERATOR_UNSPECIFIED(0),
            AND(1),
            UNRECOGNIZED(-1);
            
            public static final int AND_VALUE = 1;
            public static final int OPERATOR_UNSPECIFIED_VALUE = 0;
            private static final Internal.EnumLiteMap<Operator> internalValueMap = null;
            private final int value;

            static {
                internalValueMap = new Internal.EnumLiteMap<Operator>() {
                    public Operator findValueByNumber(int number) {
                        return Operator.forNumber(number);
                    }
                };
            }

            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Deprecated
            public static Operator valueOf(int value2) {
                return forNumber(value2);
            }

            public static Operator forNumber(int value2) {
                switch (value2) {
                    case 0:
                        return OPERATOR_UNSPECIFIED;
                    case 1:
                        return AND;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<Operator> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return OperatorVerifier.INSTANCE;
            }

            /* renamed from: com.google.firestore.v1.StructuredQuery$CompositeFilter$Operator$OperatorVerifier */
            private static final class OperatorVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = null;

                private OperatorVerifier() {
                }

                static {
                    INSTANCE = new OperatorVerifier();
                }

                public boolean isInRange(int number) {
                    return Operator.forNumber(number) != null;
                }
            }

            private Operator(int value2) {
                this.value = value2;
            }
        }

        public int getOpValue() {
            return this.op_;
        }

        public Operator getOp() {
            Operator result = Operator.forNumber(this.op_);
            return result == null ? Operator.UNRECOGNIZED : result;
        }

        /* access modifiers changed from: private */
        public void setOpValue(int value) {
            this.op_ = value;
        }

        /* access modifiers changed from: private */
        public void setOp(Operator value) {
            this.op_ = value.getNumber();
        }

        /* access modifiers changed from: private */
        public void clearOp() {
            this.op_ = 0;
        }

        public List<Filter> getFiltersList() {
            return this.filters_;
        }

        public List<? extends FilterOrBuilder> getFiltersOrBuilderList() {
            return this.filters_;
        }

        public int getFiltersCount() {
            return this.filters_.size();
        }

        public Filter getFilters(int index) {
            return (Filter) this.filters_.get(index);
        }

        public FilterOrBuilder getFiltersOrBuilder(int index) {
            return (FilterOrBuilder) this.filters_.get(index);
        }

        private void ensureFiltersIsMutable() {
            Internal.ProtobufList<Filter> tmp = this.filters_;
            if (!tmp.isModifiable()) {
                this.filters_ = GeneratedMessageLite.mutableCopy(tmp);
            }
        }

        /* access modifiers changed from: private */
        public void setFilters(int index, Filter value) {
            value.getClass();
            ensureFiltersIsMutable();
            this.filters_.set(index, value);
        }

        /* access modifiers changed from: private */
        public void addFilters(Filter value) {
            value.getClass();
            ensureFiltersIsMutable();
            this.filters_.add(value);
        }

        /* access modifiers changed from: private */
        public void addFilters(int index, Filter value) {
            value.getClass();
            ensureFiltersIsMutable();
            this.filters_.add(index, value);
        }

        /* access modifiers changed from: private */
        public void addAllFilters(Iterable<? extends Filter> values) {
            ensureFiltersIsMutable();
            AbstractMessageLite.addAll(values, this.filters_);
        }

        /* access modifiers changed from: private */
        public void clearFilters() {
            this.filters_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeFilters(int index) {
            ensureFiltersIsMutable();
            this.filters_.remove(index);
        }

        public static CompositeFilter parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CompositeFilter parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CompositeFilter parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CompositeFilter parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CompositeFilter parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static CompositeFilter parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static CompositeFilter parseFrom(InputStream input) throws IOException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CompositeFilter parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CompositeFilter parseDelimitedFrom(InputStream input) throws IOException {
            return (CompositeFilter) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static CompositeFilter parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CompositeFilter) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static CompositeFilter parseFrom(CodedInputStream input) throws IOException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static CompositeFilter parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (CompositeFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(CompositeFilter prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$CompositeFilter$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<CompositeFilter, Builder> implements CompositeFilterOrBuilder {
            /* synthetic */ Builder(C08571 x0) {
                this();
            }

            private Builder() {
                super(CompositeFilter.DEFAULT_INSTANCE);
            }

            public int getOpValue() {
                return ((CompositeFilter) this.instance).getOpValue();
            }

            public Builder setOpValue(int value) {
                copyOnWrite();
                ((CompositeFilter) this.instance).setOpValue(value);
                return this;
            }

            public Operator getOp() {
                return ((CompositeFilter) this.instance).getOp();
            }

            public Builder setOp(Operator value) {
                copyOnWrite();
                ((CompositeFilter) this.instance).setOp(value);
                return this;
            }

            public Builder clearOp() {
                copyOnWrite();
                ((CompositeFilter) this.instance).clearOp();
                return this;
            }

            public List<Filter> getFiltersList() {
                return Collections.unmodifiableList(((CompositeFilter) this.instance).getFiltersList());
            }

            public int getFiltersCount() {
                return ((CompositeFilter) this.instance).getFiltersCount();
            }

            public Filter getFilters(int index) {
                return ((CompositeFilter) this.instance).getFilters(index);
            }

            public Builder setFilters(int index, Filter value) {
                copyOnWrite();
                ((CompositeFilter) this.instance).setFilters(index, value);
                return this;
            }

            public Builder setFilters(int index, Filter.Builder builderForValue) {
                copyOnWrite();
                ((CompositeFilter) this.instance).setFilters(index, (Filter) builderForValue.build());
                return this;
            }

            public Builder addFilters(Filter value) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addFilters(value);
                return this;
            }

            public Builder addFilters(int index, Filter value) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addFilters(index, value);
                return this;
            }

            public Builder addFilters(Filter.Builder builderForValue) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addFilters((Filter) builderForValue.build());
                return this;
            }

            public Builder addFilters(int index, Filter.Builder builderForValue) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addFilters(index, (Filter) builderForValue.build());
                return this;
            }

            public Builder addAllFilters(Iterable<? extends Filter> values) {
                copyOnWrite();
                ((CompositeFilter) this.instance).addAllFilters(values);
                return this;
            }

            public Builder clearFilters() {
                copyOnWrite();
                ((CompositeFilter) this.instance).clearFilters();
                return this;
            }

            public Builder removeFilters(int index) {
                copyOnWrite();
                ((CompositeFilter) this.instance).removeFilters(index);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C08571.f230xa1df5c61[method.ordinal()]) {
                case 1:
                    return new CompositeFilter();
                case 2:
                    return new Builder((C08571) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\f\u0002\u001b", new Object[]{"op_", "filters_", Filter.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<CompositeFilter> parser = PARSER;
                    if (parser == null) {
                        synchronized (CompositeFilter.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            CompositeFilter defaultInstance = new CompositeFilter();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(CompositeFilter.class, defaultInstance);
        }

        public static CompositeFilter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CompositeFilter> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$FieldFilter */
    public static final class FieldFilter extends GeneratedMessageLite<FieldFilter, Builder> implements FieldFilterOrBuilder {
        /* access modifiers changed from: private */
        public static final FieldFilter DEFAULT_INSTANCE;
        public static final int FIELD_FIELD_NUMBER = 1;
        public static final int OP_FIELD_NUMBER = 2;
        private static volatile Parser<FieldFilter> PARSER = null;
        public static final int VALUE_FIELD_NUMBER = 3;
        private FieldReference field_;
        private int op_;
        private Value value_;

        private FieldFilter() {
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$FieldFilter$Operator */
        public enum Operator implements Internal.EnumLite {
            OPERATOR_UNSPECIFIED(0),
            LESS_THAN(1),
            LESS_THAN_OR_EQUAL(2),
            GREATER_THAN(3),
            GREATER_THAN_OR_EQUAL(4),
            EQUAL(5),
            NOT_EQUAL(6),
            ARRAY_CONTAINS(7),
            IN(8),
            ARRAY_CONTAINS_ANY(9),
            NOT_IN(10),
            UNRECOGNIZED(-1);
            
            public static final int ARRAY_CONTAINS_ANY_VALUE = 9;
            public static final int ARRAY_CONTAINS_VALUE = 7;
            public static final int EQUAL_VALUE = 5;
            public static final int GREATER_THAN_OR_EQUAL_VALUE = 4;
            public static final int GREATER_THAN_VALUE = 3;
            public static final int IN_VALUE = 8;
            public static final int LESS_THAN_OR_EQUAL_VALUE = 2;
            public static final int LESS_THAN_VALUE = 1;
            public static final int NOT_EQUAL_VALUE = 6;
            public static final int NOT_IN_VALUE = 10;
            public static final int OPERATOR_UNSPECIFIED_VALUE = 0;
            private static final Internal.EnumLiteMap<Operator> internalValueMap = null;
            private final int value;

            static {
                internalValueMap = new Internal.EnumLiteMap<Operator>() {
                    public Operator findValueByNumber(int number) {
                        return Operator.forNumber(number);
                    }
                };
            }

            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Deprecated
            public static Operator valueOf(int value2) {
                return forNumber(value2);
            }

            public static Operator forNumber(int value2) {
                switch (value2) {
                    case 0:
                        return OPERATOR_UNSPECIFIED;
                    case 1:
                        return LESS_THAN;
                    case 2:
                        return LESS_THAN_OR_EQUAL;
                    case 3:
                        return GREATER_THAN;
                    case 4:
                        return GREATER_THAN_OR_EQUAL;
                    case 5:
                        return EQUAL;
                    case 6:
                        return NOT_EQUAL;
                    case 7:
                        return ARRAY_CONTAINS;
                    case 8:
                        return IN;
                    case 9:
                        return ARRAY_CONTAINS_ANY;
                    case 10:
                        return NOT_IN;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<Operator> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return OperatorVerifier.INSTANCE;
            }

            /* renamed from: com.google.firestore.v1.StructuredQuery$FieldFilter$Operator$OperatorVerifier */
            private static final class OperatorVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = null;

                private OperatorVerifier() {
                }

                static {
                    INSTANCE = new OperatorVerifier();
                }

                public boolean isInRange(int number) {
                    return Operator.forNumber(number) != null;
                }
            }

            private Operator(int value2) {
                this.value = value2;
            }
        }

        public boolean hasField() {
            return this.field_ != null;
        }

        public FieldReference getField() {
            FieldReference fieldReference = this.field_;
            return fieldReference == null ? FieldReference.getDefaultInstance() : fieldReference;
        }

        /* access modifiers changed from: private */
        public void setField(FieldReference value) {
            value.getClass();
            this.field_ = value;
        }

        /* access modifiers changed from: private */
        public void mergeField(FieldReference value) {
            value.getClass();
            FieldReference fieldReference = this.field_;
            if (fieldReference == null || fieldReference == FieldReference.getDefaultInstance()) {
                this.field_ = value;
            } else {
                this.field_ = (FieldReference) ((FieldReference.Builder) FieldReference.newBuilder(this.field_).mergeFrom(value)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearField() {
            this.field_ = null;
        }

        public int getOpValue() {
            return this.op_;
        }

        public Operator getOp() {
            Operator result = Operator.forNumber(this.op_);
            return result == null ? Operator.UNRECOGNIZED : result;
        }

        /* access modifiers changed from: private */
        public void setOpValue(int value) {
            this.op_ = value;
        }

        /* access modifiers changed from: private */
        public void setOp(Operator value) {
            this.op_ = value.getNumber();
        }

        /* access modifiers changed from: private */
        public void clearOp() {
            this.op_ = 0;
        }

        public boolean hasValue() {
            return this.value_ != null;
        }

        public Value getValue() {
            Value value = this.value_;
            return value == null ? Value.getDefaultInstance() : value;
        }

        /* access modifiers changed from: private */
        public void setValue(Value value) {
            value.getClass();
            this.value_ = value;
        }

        /* access modifiers changed from: private */
        public void mergeValue(Value value) {
            value.getClass();
            Value value2 = this.value_;
            if (value2 == null || value2 == Value.getDefaultInstance()) {
                this.value_ = value;
            } else {
                this.value_ = (Value) ((Value.Builder) Value.newBuilder(this.value_).mergeFrom(value)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearValue() {
            this.value_ = null;
        }

        public static FieldFilter parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FieldFilter parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FieldFilter parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FieldFilter parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FieldFilter parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FieldFilter parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FieldFilter parseFrom(InputStream input) throws IOException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FieldFilter parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FieldFilter parseDelimitedFrom(InputStream input) throws IOException {
            return (FieldFilter) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static FieldFilter parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FieldFilter) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FieldFilter parseFrom(CodedInputStream input) throws IOException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FieldFilter parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FieldFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(FieldFilter prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$FieldFilter$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<FieldFilter, Builder> implements FieldFilterOrBuilder {
            /* synthetic */ Builder(C08571 x0) {
                this();
            }

            private Builder() {
                super(FieldFilter.DEFAULT_INSTANCE);
            }

            public boolean hasField() {
                return ((FieldFilter) this.instance).hasField();
            }

            public FieldReference getField() {
                return ((FieldFilter) this.instance).getField();
            }

            public Builder setField(FieldReference value) {
                copyOnWrite();
                ((FieldFilter) this.instance).setField(value);
                return this;
            }

            public Builder setField(FieldReference.Builder builderForValue) {
                copyOnWrite();
                ((FieldFilter) this.instance).setField((FieldReference) builderForValue.build());
                return this;
            }

            public Builder mergeField(FieldReference value) {
                copyOnWrite();
                ((FieldFilter) this.instance).mergeField(value);
                return this;
            }

            public Builder clearField() {
                copyOnWrite();
                ((FieldFilter) this.instance).clearField();
                return this;
            }

            public int getOpValue() {
                return ((FieldFilter) this.instance).getOpValue();
            }

            public Builder setOpValue(int value) {
                copyOnWrite();
                ((FieldFilter) this.instance).setOpValue(value);
                return this;
            }

            public Operator getOp() {
                return ((FieldFilter) this.instance).getOp();
            }

            public Builder setOp(Operator value) {
                copyOnWrite();
                ((FieldFilter) this.instance).setOp(value);
                return this;
            }

            public Builder clearOp() {
                copyOnWrite();
                ((FieldFilter) this.instance).clearOp();
                return this;
            }

            public boolean hasValue() {
                return ((FieldFilter) this.instance).hasValue();
            }

            public Value getValue() {
                return ((FieldFilter) this.instance).getValue();
            }

            public Builder setValue(Value value) {
                copyOnWrite();
                ((FieldFilter) this.instance).setValue(value);
                return this;
            }

            public Builder setValue(Value.Builder builderForValue) {
                copyOnWrite();
                ((FieldFilter) this.instance).setValue((Value) builderForValue.build());
                return this;
            }

            public Builder mergeValue(Value value) {
                copyOnWrite();
                ((FieldFilter) this.instance).mergeValue(value);
                return this;
            }

            public Builder clearValue() {
                copyOnWrite();
                ((FieldFilter) this.instance).clearValue();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C08571.f230xa1df5c61[method.ordinal()]) {
                case 1:
                    return new FieldFilter();
                case 2:
                    return new Builder((C08571) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\f\u0003\t", new Object[]{"field_", "op_", "value_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<FieldFilter> parser = PARSER;
                    if (parser == null) {
                        synchronized (FieldFilter.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            FieldFilter defaultInstance = new FieldFilter();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(FieldFilter.class, defaultInstance);
        }

        public static FieldFilter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FieldFilter> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$UnaryFilter */
    public static final class UnaryFilter extends GeneratedMessageLite<UnaryFilter, Builder> implements UnaryFilterOrBuilder {
        /* access modifiers changed from: private */
        public static final UnaryFilter DEFAULT_INSTANCE;
        public static final int FIELD_FIELD_NUMBER = 2;
        public static final int OP_FIELD_NUMBER = 1;
        private static volatile Parser<UnaryFilter> PARSER;
        private int op_;
        private int operandTypeCase_ = 0;
        private Object operandType_;

        private UnaryFilter() {
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$UnaryFilter$Operator */
        public enum Operator implements Internal.EnumLite {
            OPERATOR_UNSPECIFIED(0),
            IS_NAN(2),
            IS_NULL(3),
            IS_NOT_NAN(4),
            IS_NOT_NULL(5),
            UNRECOGNIZED(-1);
            
            public static final int IS_NAN_VALUE = 2;
            public static final int IS_NOT_NAN_VALUE = 4;
            public static final int IS_NOT_NULL_VALUE = 5;
            public static final int IS_NULL_VALUE = 3;
            public static final int OPERATOR_UNSPECIFIED_VALUE = 0;
            private static final Internal.EnumLiteMap<Operator> internalValueMap = null;
            private final int value;

            static {
                internalValueMap = new Internal.EnumLiteMap<Operator>() {
                    public Operator findValueByNumber(int number) {
                        return Operator.forNumber(number);
                    }
                };
            }

            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Deprecated
            public static Operator valueOf(int value2) {
                return forNumber(value2);
            }

            public static Operator forNumber(int value2) {
                switch (value2) {
                    case 0:
                        return OPERATOR_UNSPECIFIED;
                    case 2:
                        return IS_NAN;
                    case 3:
                        return IS_NULL;
                    case 4:
                        return IS_NOT_NAN;
                    case 5:
                        return IS_NOT_NULL;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<Operator> internalGetValueMap() {
                return internalValueMap;
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return OperatorVerifier.INSTANCE;
            }

            /* renamed from: com.google.firestore.v1.StructuredQuery$UnaryFilter$Operator$OperatorVerifier */
            private static final class OperatorVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = null;

                private OperatorVerifier() {
                }

                static {
                    INSTANCE = new OperatorVerifier();
                }

                public boolean isInRange(int number) {
                    return Operator.forNumber(number) != null;
                }
            }

            private Operator(int value2) {
                this.value = value2;
            }
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$UnaryFilter$OperandTypeCase */
        public enum OperandTypeCase {
            FIELD(2),
            OPERANDTYPE_NOT_SET(0);
            
            private final int value;

            private OperandTypeCase(int value2) {
                this.value = value2;
            }

            @Deprecated
            public static OperandTypeCase valueOf(int value2) {
                return forNumber(value2);
            }

            public static OperandTypeCase forNumber(int value2) {
                switch (value2) {
                    case 0:
                        return OPERANDTYPE_NOT_SET;
                    case 2:
                        return FIELD;
                    default:
                        return null;
                }
            }

            public int getNumber() {
                return this.value;
            }
        }

        public OperandTypeCase getOperandTypeCase() {
            return OperandTypeCase.forNumber(this.operandTypeCase_);
        }

        /* access modifiers changed from: private */
        public void clearOperandType() {
            this.operandTypeCase_ = 0;
            this.operandType_ = null;
        }

        public int getOpValue() {
            return this.op_;
        }

        public Operator getOp() {
            Operator result = Operator.forNumber(this.op_);
            return result == null ? Operator.UNRECOGNIZED : result;
        }

        /* access modifiers changed from: private */
        public void setOpValue(int value) {
            this.op_ = value;
        }

        /* access modifiers changed from: private */
        public void setOp(Operator value) {
            this.op_ = value.getNumber();
        }

        /* access modifiers changed from: private */
        public void clearOp() {
            this.op_ = 0;
        }

        public boolean hasField() {
            return this.operandTypeCase_ == 2;
        }

        public FieldReference getField() {
            if (this.operandTypeCase_ == 2) {
                return (FieldReference) this.operandType_;
            }
            return FieldReference.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setField(FieldReference value) {
            value.getClass();
            this.operandType_ = value;
            this.operandTypeCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void mergeField(FieldReference value) {
            value.getClass();
            if (this.operandTypeCase_ != 2 || this.operandType_ == FieldReference.getDefaultInstance()) {
                this.operandType_ = value;
            } else {
                this.operandType_ = ((FieldReference.Builder) FieldReference.newBuilder((FieldReference) this.operandType_).mergeFrom(value)).buildPartial();
            }
            this.operandTypeCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void clearField() {
            if (this.operandTypeCase_ == 2) {
                this.operandTypeCase_ = 0;
                this.operandType_ = null;
            }
        }

        public static UnaryFilter parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static UnaryFilter parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static UnaryFilter parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static UnaryFilter parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static UnaryFilter parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static UnaryFilter parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static UnaryFilter parseFrom(InputStream input) throws IOException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static UnaryFilter parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static UnaryFilter parseDelimitedFrom(InputStream input) throws IOException {
            return (UnaryFilter) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static UnaryFilter parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UnaryFilter) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static UnaryFilter parseFrom(CodedInputStream input) throws IOException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static UnaryFilter parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (UnaryFilter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(UnaryFilter prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$UnaryFilter$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<UnaryFilter, Builder> implements UnaryFilterOrBuilder {
            /* synthetic */ Builder(C08571 x0) {
                this();
            }

            private Builder() {
                super(UnaryFilter.DEFAULT_INSTANCE);
            }

            public OperandTypeCase getOperandTypeCase() {
                return ((UnaryFilter) this.instance).getOperandTypeCase();
            }

            public Builder clearOperandType() {
                copyOnWrite();
                ((UnaryFilter) this.instance).clearOperandType();
                return this;
            }

            public int getOpValue() {
                return ((UnaryFilter) this.instance).getOpValue();
            }

            public Builder setOpValue(int value) {
                copyOnWrite();
                ((UnaryFilter) this.instance).setOpValue(value);
                return this;
            }

            public Operator getOp() {
                return ((UnaryFilter) this.instance).getOp();
            }

            public Builder setOp(Operator value) {
                copyOnWrite();
                ((UnaryFilter) this.instance).setOp(value);
                return this;
            }

            public Builder clearOp() {
                copyOnWrite();
                ((UnaryFilter) this.instance).clearOp();
                return this;
            }

            public boolean hasField() {
                return ((UnaryFilter) this.instance).hasField();
            }

            public FieldReference getField() {
                return ((UnaryFilter) this.instance).getField();
            }

            public Builder setField(FieldReference value) {
                copyOnWrite();
                ((UnaryFilter) this.instance).setField(value);
                return this;
            }

            public Builder setField(FieldReference.Builder builderForValue) {
                copyOnWrite();
                ((UnaryFilter) this.instance).setField((FieldReference) builderForValue.build());
                return this;
            }

            public Builder mergeField(FieldReference value) {
                copyOnWrite();
                ((UnaryFilter) this.instance).mergeField(value);
                return this;
            }

            public Builder clearField() {
                copyOnWrite();
                ((UnaryFilter) this.instance).clearField();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C08571.f230xa1df5c61[method.ordinal()]) {
                case 1:
                    return new UnaryFilter();
                case 2:
                    return new Builder((C08571) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002<\u0000", new Object[]{"operandType_", "operandTypeCase_", "op_", FieldReference.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<UnaryFilter> parser = PARSER;
                    if (parser == null) {
                        synchronized (UnaryFilter.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            UnaryFilter defaultInstance = new UnaryFilter();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(UnaryFilter.class, defaultInstance);
        }

        public static UnaryFilter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UnaryFilter> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$Order */
    public static final class Order extends GeneratedMessageLite<Order, Builder> implements OrderOrBuilder {
        /* access modifiers changed from: private */
        public static final Order DEFAULT_INSTANCE;
        public static final int DIRECTION_FIELD_NUMBER = 2;
        public static final int FIELD_FIELD_NUMBER = 1;
        private static volatile Parser<Order> PARSER;
        private int direction_;
        private FieldReference field_;

        private Order() {
        }

        public boolean hasField() {
            return this.field_ != null;
        }

        public FieldReference getField() {
            FieldReference fieldReference = this.field_;
            return fieldReference == null ? FieldReference.getDefaultInstance() : fieldReference;
        }

        /* access modifiers changed from: private */
        public void setField(FieldReference value) {
            value.getClass();
            this.field_ = value;
        }

        /* access modifiers changed from: private */
        public void mergeField(FieldReference value) {
            value.getClass();
            FieldReference fieldReference = this.field_;
            if (fieldReference == null || fieldReference == FieldReference.getDefaultInstance()) {
                this.field_ = value;
            } else {
                this.field_ = (FieldReference) ((FieldReference.Builder) FieldReference.newBuilder(this.field_).mergeFrom(value)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearField() {
            this.field_ = null;
        }

        public int getDirectionValue() {
            return this.direction_;
        }

        public Direction getDirection() {
            Direction result = Direction.forNumber(this.direction_);
            return result == null ? Direction.UNRECOGNIZED : result;
        }

        /* access modifiers changed from: private */
        public void setDirectionValue(int value) {
            this.direction_ = value;
        }

        /* access modifiers changed from: private */
        public void setDirection(Direction value) {
            this.direction_ = value.getNumber();
        }

        /* access modifiers changed from: private */
        public void clearDirection() {
            this.direction_ = 0;
        }

        public static Order parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Order parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Order parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Order parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Order parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Order parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Order parseFrom(InputStream input) throws IOException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Order parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Order parseDelimitedFrom(InputStream input) throws IOException {
            return (Order) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Order parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Order) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Order parseFrom(CodedInputStream input) throws IOException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Order parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Order) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Order prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$Order$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<Order, Builder> implements OrderOrBuilder {
            /* synthetic */ Builder(C08571 x0) {
                this();
            }

            private Builder() {
                super(Order.DEFAULT_INSTANCE);
            }

            public boolean hasField() {
                return ((Order) this.instance).hasField();
            }

            public FieldReference getField() {
                return ((Order) this.instance).getField();
            }

            public Builder setField(FieldReference value) {
                copyOnWrite();
                ((Order) this.instance).setField(value);
                return this;
            }

            public Builder setField(FieldReference.Builder builderForValue) {
                copyOnWrite();
                ((Order) this.instance).setField((FieldReference) builderForValue.build());
                return this;
            }

            public Builder mergeField(FieldReference value) {
                copyOnWrite();
                ((Order) this.instance).mergeField(value);
                return this;
            }

            public Builder clearField() {
                copyOnWrite();
                ((Order) this.instance).clearField();
                return this;
            }

            public int getDirectionValue() {
                return ((Order) this.instance).getDirectionValue();
            }

            public Builder setDirectionValue(int value) {
                copyOnWrite();
                ((Order) this.instance).setDirectionValue(value);
                return this;
            }

            public Direction getDirection() {
                return ((Order) this.instance).getDirection();
            }

            public Builder setDirection(Direction value) {
                copyOnWrite();
                ((Order) this.instance).setDirection(value);
                return this;
            }

            public Builder clearDirection() {
                copyOnWrite();
                ((Order) this.instance).clearDirection();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C08571.f230xa1df5c61[method.ordinal()]) {
                case 1:
                    return new Order();
                case 2:
                    return new Builder((C08571) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\f", new Object[]{"field_", "direction_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Order> parser = PARSER;
                    if (parser == null) {
                        synchronized (Order.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            Order defaultInstance = new Order();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Order.class, defaultInstance);
        }

        public static Order getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Order> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$FieldReference */
    public static final class FieldReference extends GeneratedMessageLite<FieldReference, Builder> implements FieldReferenceOrBuilder {
        /* access modifiers changed from: private */
        public static final FieldReference DEFAULT_INSTANCE;
        public static final int FIELD_PATH_FIELD_NUMBER = 2;
        private static volatile Parser<FieldReference> PARSER;
        private String fieldPath_ = "";

        private FieldReference() {
        }

        public String getFieldPath() {
            return this.fieldPath_;
        }

        public ByteString getFieldPathBytes() {
            return ByteString.copyFromUtf8(this.fieldPath_);
        }

        /* access modifiers changed from: private */
        public void setFieldPath(String value) {
            value.getClass();
            this.fieldPath_ = value;
        }

        /* access modifiers changed from: private */
        public void clearFieldPath() {
            this.fieldPath_ = getDefaultInstance().getFieldPath();
        }

        /* access modifiers changed from: private */
        public void setFieldPathBytes(ByteString value) {
            checkByteStringIsUtf8(value);
            this.fieldPath_ = value.toStringUtf8();
        }

        public static FieldReference parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FieldReference parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FieldReference parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FieldReference parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FieldReference parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static FieldReference parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static FieldReference parseFrom(InputStream input) throws IOException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FieldReference parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FieldReference parseDelimitedFrom(InputStream input) throws IOException {
            return (FieldReference) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static FieldReference parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FieldReference) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static FieldReference parseFrom(CodedInputStream input) throws IOException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static FieldReference parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (FieldReference) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(FieldReference prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$FieldReference$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<FieldReference, Builder> implements FieldReferenceOrBuilder {
            /* synthetic */ Builder(C08571 x0) {
                this();
            }

            private Builder() {
                super(FieldReference.DEFAULT_INSTANCE);
            }

            public String getFieldPath() {
                return ((FieldReference) this.instance).getFieldPath();
            }

            public ByteString getFieldPathBytes() {
                return ((FieldReference) this.instance).getFieldPathBytes();
            }

            public Builder setFieldPath(String value) {
                copyOnWrite();
                ((FieldReference) this.instance).setFieldPath(value);
                return this;
            }

            public Builder clearFieldPath() {
                copyOnWrite();
                ((FieldReference) this.instance).clearFieldPath();
                return this;
            }

            public Builder setFieldPathBytes(ByteString value) {
                copyOnWrite();
                ((FieldReference) this.instance).setFieldPathBytes(value);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C08571.f230xa1df5c61[method.ordinal()]) {
                case 1:
                    return new FieldReference();
                case 2:
                    return new Builder((C08571) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0000\u0000\u0002Ȉ", new Object[]{"fieldPath_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<FieldReference> parser = PARSER;
                    if (parser == null) {
                        synchronized (FieldReference.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            FieldReference defaultInstance = new FieldReference();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(FieldReference.class, defaultInstance);
        }

        public static FieldReference getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FieldReference> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$Projection */
    public static final class Projection extends GeneratedMessageLite<Projection, Builder> implements ProjectionOrBuilder {
        /* access modifiers changed from: private */
        public static final Projection DEFAULT_INSTANCE;
        public static final int FIELDS_FIELD_NUMBER = 2;
        private static volatile Parser<Projection> PARSER;
        private Internal.ProtobufList<FieldReference> fields_ = emptyProtobufList();

        private Projection() {
        }

        public List<FieldReference> getFieldsList() {
            return this.fields_;
        }

        public List<? extends FieldReferenceOrBuilder> getFieldsOrBuilderList() {
            return this.fields_;
        }

        public int getFieldsCount() {
            return this.fields_.size();
        }

        public FieldReference getFields(int index) {
            return (FieldReference) this.fields_.get(index);
        }

        public FieldReferenceOrBuilder getFieldsOrBuilder(int index) {
            return (FieldReferenceOrBuilder) this.fields_.get(index);
        }

        private void ensureFieldsIsMutable() {
            Internal.ProtobufList<FieldReference> tmp = this.fields_;
            if (!tmp.isModifiable()) {
                this.fields_ = GeneratedMessageLite.mutableCopy(tmp);
            }
        }

        /* access modifiers changed from: private */
        public void setFields(int index, FieldReference value) {
            value.getClass();
            ensureFieldsIsMutable();
            this.fields_.set(index, value);
        }

        /* access modifiers changed from: private */
        public void addFields(FieldReference value) {
            value.getClass();
            ensureFieldsIsMutable();
            this.fields_.add(value);
        }

        /* access modifiers changed from: private */
        public void addFields(int index, FieldReference value) {
            value.getClass();
            ensureFieldsIsMutable();
            this.fields_.add(index, value);
        }

        /* access modifiers changed from: private */
        public void addAllFields(Iterable<? extends FieldReference> values) {
            ensureFieldsIsMutable();
            AbstractMessageLite.addAll(values, this.fields_);
        }

        /* access modifiers changed from: private */
        public void clearFields() {
            this.fields_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeFields(int index) {
            ensureFieldsIsMutable();
            this.fields_.remove(index);
        }

        public static Projection parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Projection parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Projection parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Projection parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Projection parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static Projection parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static Projection parseFrom(InputStream input) throws IOException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Projection parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Projection parseDelimitedFrom(InputStream input) throws IOException {
            return (Projection) parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static Projection parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Projection) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Projection parseFrom(CodedInputStream input) throws IOException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static Projection parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return (Projection) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Projection prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /* renamed from: com.google.firestore.v1.StructuredQuery$Projection$Builder */
        public static final class Builder extends GeneratedMessageLite.Builder<Projection, Builder> implements ProjectionOrBuilder {
            /* synthetic */ Builder(C08571 x0) {
                this();
            }

            private Builder() {
                super(Projection.DEFAULT_INSTANCE);
            }

            public List<FieldReference> getFieldsList() {
                return Collections.unmodifiableList(((Projection) this.instance).getFieldsList());
            }

            public int getFieldsCount() {
                return ((Projection) this.instance).getFieldsCount();
            }

            public FieldReference getFields(int index) {
                return ((Projection) this.instance).getFields(index);
            }

            public Builder setFields(int index, FieldReference value) {
                copyOnWrite();
                ((Projection) this.instance).setFields(index, value);
                return this;
            }

            public Builder setFields(int index, FieldReference.Builder builderForValue) {
                copyOnWrite();
                ((Projection) this.instance).setFields(index, (FieldReference) builderForValue.build());
                return this;
            }

            public Builder addFields(FieldReference value) {
                copyOnWrite();
                ((Projection) this.instance).addFields(value);
                return this;
            }

            public Builder addFields(int index, FieldReference value) {
                copyOnWrite();
                ((Projection) this.instance).addFields(index, value);
                return this;
            }

            public Builder addFields(FieldReference.Builder builderForValue) {
                copyOnWrite();
                ((Projection) this.instance).addFields((FieldReference) builderForValue.build());
                return this;
            }

            public Builder addFields(int index, FieldReference.Builder builderForValue) {
                copyOnWrite();
                ((Projection) this.instance).addFields(index, (FieldReference) builderForValue.build());
                return this;
            }

            public Builder addAllFields(Iterable<? extends FieldReference> values) {
                copyOnWrite();
                ((Projection) this.instance).addAllFields(values);
                return this;
            }

            public Builder clearFields() {
                copyOnWrite();
                ((Projection) this.instance).clearFields();
                return this;
            }

            public Builder removeFields(int index) {
                copyOnWrite();
                ((Projection) this.instance).removeFields(index);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
            switch (C08571.f230xa1df5c61[method.ordinal()]) {
                case 1:
                    return new Projection();
                case 2:
                    return new Builder((C08571) null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0000\u0001\u0000\u0002\u001b", new Object[]{"fields_", FieldReference.class});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Projection> parser = PARSER;
                    if (parser == null) {
                        synchronized (Projection.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            Projection defaultInstance = new Projection();
            DEFAULT_INSTANCE = defaultInstance;
            GeneratedMessageLite.registerDefaultInstance(Projection.class, defaultInstance);
        }

        public static Projection getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Projection> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public boolean hasSelect() {
        return this.select_ != null;
    }

    public Projection getSelect() {
        Projection projection = this.select_;
        return projection == null ? Projection.getDefaultInstance() : projection;
    }

    /* access modifiers changed from: private */
    public void setSelect(Projection value) {
        value.getClass();
        this.select_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeSelect(Projection value) {
        value.getClass();
        Projection projection = this.select_;
        if (projection == null || projection == Projection.getDefaultInstance()) {
            this.select_ = value;
        } else {
            this.select_ = (Projection) ((Projection.Builder) Projection.newBuilder(this.select_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearSelect() {
        this.select_ = null;
    }

    public List<CollectionSelector> getFromList() {
        return this.from_;
    }

    public List<? extends CollectionSelectorOrBuilder> getFromOrBuilderList() {
        return this.from_;
    }

    public int getFromCount() {
        return this.from_.size();
    }

    public CollectionSelector getFrom(int index) {
        return (CollectionSelector) this.from_.get(index);
    }

    public CollectionSelectorOrBuilder getFromOrBuilder(int index) {
        return (CollectionSelectorOrBuilder) this.from_.get(index);
    }

    private void ensureFromIsMutable() {
        Internal.ProtobufList<CollectionSelector> tmp = this.from_;
        if (!tmp.isModifiable()) {
            this.from_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setFrom(int index, CollectionSelector value) {
        value.getClass();
        ensureFromIsMutable();
        this.from_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addFrom(CollectionSelector value) {
        value.getClass();
        ensureFromIsMutable();
        this.from_.add(value);
    }

    /* access modifiers changed from: private */
    public void addFrom(int index, CollectionSelector value) {
        value.getClass();
        ensureFromIsMutable();
        this.from_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllFrom(Iterable<? extends CollectionSelector> values) {
        ensureFromIsMutable();
        AbstractMessageLite.addAll(values, this.from_);
    }

    /* access modifiers changed from: private */
    public void clearFrom() {
        this.from_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeFrom(int index) {
        ensureFromIsMutable();
        this.from_.remove(index);
    }

    public boolean hasWhere() {
        return this.where_ != null;
    }

    public Filter getWhere() {
        Filter filter = this.where_;
        return filter == null ? Filter.getDefaultInstance() : filter;
    }

    /* access modifiers changed from: private */
    public void setWhere(Filter value) {
        value.getClass();
        this.where_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeWhere(Filter value) {
        value.getClass();
        Filter filter = this.where_;
        if (filter == null || filter == Filter.getDefaultInstance()) {
            this.where_ = value;
        } else {
            this.where_ = (Filter) ((Filter.Builder) Filter.newBuilder(this.where_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearWhere() {
        this.where_ = null;
    }

    public List<Order> getOrderByList() {
        return this.orderBy_;
    }

    public List<? extends OrderOrBuilder> getOrderByOrBuilderList() {
        return this.orderBy_;
    }

    public int getOrderByCount() {
        return this.orderBy_.size();
    }

    public Order getOrderBy(int index) {
        return (Order) this.orderBy_.get(index);
    }

    public OrderOrBuilder getOrderByOrBuilder(int index) {
        return (OrderOrBuilder) this.orderBy_.get(index);
    }

    private void ensureOrderByIsMutable() {
        Internal.ProtobufList<Order> tmp = this.orderBy_;
        if (!tmp.isModifiable()) {
            this.orderBy_ = GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /* access modifiers changed from: private */
    public void setOrderBy(int index, Order value) {
        value.getClass();
        ensureOrderByIsMutable();
        this.orderBy_.set(index, value);
    }

    /* access modifiers changed from: private */
    public void addOrderBy(Order value) {
        value.getClass();
        ensureOrderByIsMutable();
        this.orderBy_.add(value);
    }

    /* access modifiers changed from: private */
    public void addOrderBy(int index, Order value) {
        value.getClass();
        ensureOrderByIsMutable();
        this.orderBy_.add(index, value);
    }

    /* access modifiers changed from: private */
    public void addAllOrderBy(Iterable<? extends Order> values) {
        ensureOrderByIsMutable();
        AbstractMessageLite.addAll(values, this.orderBy_);
    }

    /* access modifiers changed from: private */
    public void clearOrderBy() {
        this.orderBy_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeOrderBy(int index) {
        ensureOrderByIsMutable();
        this.orderBy_.remove(index);
    }

    public boolean hasStartAt() {
        return this.startAt_ != null;
    }

    public Cursor getStartAt() {
        Cursor cursor = this.startAt_;
        return cursor == null ? Cursor.getDefaultInstance() : cursor;
    }

    /* access modifiers changed from: private */
    public void setStartAt(Cursor value) {
        value.getClass();
        this.startAt_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeStartAt(Cursor value) {
        value.getClass();
        Cursor cursor = this.startAt_;
        if (cursor == null || cursor == Cursor.getDefaultInstance()) {
            this.startAt_ = value;
        } else {
            this.startAt_ = (Cursor) ((Cursor.Builder) Cursor.newBuilder(this.startAt_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearStartAt() {
        this.startAt_ = null;
    }

    public boolean hasEndAt() {
        return this.endAt_ != null;
    }

    public Cursor getEndAt() {
        Cursor cursor = this.endAt_;
        return cursor == null ? Cursor.getDefaultInstance() : cursor;
    }

    /* access modifiers changed from: private */
    public void setEndAt(Cursor value) {
        value.getClass();
        this.endAt_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeEndAt(Cursor value) {
        value.getClass();
        Cursor cursor = this.endAt_;
        if (cursor == null || cursor == Cursor.getDefaultInstance()) {
            this.endAt_ = value;
        } else {
            this.endAt_ = (Cursor) ((Cursor.Builder) Cursor.newBuilder(this.endAt_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearEndAt() {
        this.endAt_ = null;
    }

    public int getOffset() {
        return this.offset_;
    }

    /* access modifiers changed from: private */
    public void setOffset(int value) {
        this.offset_ = value;
    }

    /* access modifiers changed from: private */
    public void clearOffset() {
        this.offset_ = 0;
    }

    public boolean hasLimit() {
        return this.limit_ != null;
    }

    public Int32Value getLimit() {
        Int32Value int32Value = this.limit_;
        return int32Value == null ? Int32Value.getDefaultInstance() : int32Value;
    }

    /* access modifiers changed from: private */
    public void setLimit(Int32Value value) {
        value.getClass();
        this.limit_ = value;
    }

    /* access modifiers changed from: private */
    public void mergeLimit(Int32Value value) {
        value.getClass();
        Int32Value int32Value = this.limit_;
        if (int32Value == null || int32Value == Int32Value.getDefaultInstance()) {
            this.limit_ = value;
        } else {
            this.limit_ = (Int32Value) ((Int32Value.Builder) Int32Value.newBuilder(this.limit_).mergeFrom(value)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearLimit() {
        this.limit_ = null;
    }

    public static StructuredQuery parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StructuredQuery parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StructuredQuery parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StructuredQuery parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StructuredQuery parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static StructuredQuery parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static StructuredQuery parseFrom(InputStream input) throws IOException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static StructuredQuery parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static StructuredQuery parseDelimitedFrom(InputStream input) throws IOException {
        return (StructuredQuery) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static StructuredQuery parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StructuredQuery) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static StructuredQuery parseFrom(CodedInputStream input) throws IOException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static StructuredQuery parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (StructuredQuery) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(StructuredQuery prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /* renamed from: com.google.firestore.v1.StructuredQuery$Builder */
    public static final class Builder extends GeneratedMessageLite.Builder<StructuredQuery, Builder> implements StructuredQueryOrBuilder {
        /* synthetic */ Builder(C08571 x0) {
            this();
        }

        private Builder() {
            super(StructuredQuery.DEFAULT_INSTANCE);
        }

        public boolean hasSelect() {
            return ((StructuredQuery) this.instance).hasSelect();
        }

        public Projection getSelect() {
            return ((StructuredQuery) this.instance).getSelect();
        }

        public Builder setSelect(Projection value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setSelect(value);
            return this;
        }

        public Builder setSelect(Projection.Builder builderForValue) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setSelect((Projection) builderForValue.build());
            return this;
        }

        public Builder mergeSelect(Projection value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeSelect(value);
            return this;
        }

        public Builder clearSelect() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearSelect();
            return this;
        }

        public List<CollectionSelector> getFromList() {
            return Collections.unmodifiableList(((StructuredQuery) this.instance).getFromList());
        }

        public int getFromCount() {
            return ((StructuredQuery) this.instance).getFromCount();
        }

        public CollectionSelector getFrom(int index) {
            return ((StructuredQuery) this.instance).getFrom(index);
        }

        public Builder setFrom(int index, CollectionSelector value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setFrom(index, value);
            return this;
        }

        public Builder setFrom(int index, CollectionSelector.Builder builderForValue) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setFrom(index, (CollectionSelector) builderForValue.build());
            return this;
        }

        public Builder addFrom(CollectionSelector value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addFrom(value);
            return this;
        }

        public Builder addFrom(int index, CollectionSelector value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addFrom(index, value);
            return this;
        }

        public Builder addFrom(CollectionSelector.Builder builderForValue) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addFrom((CollectionSelector) builderForValue.build());
            return this;
        }

        public Builder addFrom(int index, CollectionSelector.Builder builderForValue) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addFrom(index, (CollectionSelector) builderForValue.build());
            return this;
        }

        public Builder addAllFrom(Iterable<? extends CollectionSelector> values) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addAllFrom(values);
            return this;
        }

        public Builder clearFrom() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearFrom();
            return this;
        }

        public Builder removeFrom(int index) {
            copyOnWrite();
            ((StructuredQuery) this.instance).removeFrom(index);
            return this;
        }

        public boolean hasWhere() {
            return ((StructuredQuery) this.instance).hasWhere();
        }

        public Filter getWhere() {
            return ((StructuredQuery) this.instance).getWhere();
        }

        public Builder setWhere(Filter value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setWhere(value);
            return this;
        }

        public Builder setWhere(Filter.Builder builderForValue) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setWhere((Filter) builderForValue.build());
            return this;
        }

        public Builder mergeWhere(Filter value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeWhere(value);
            return this;
        }

        public Builder clearWhere() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearWhere();
            return this;
        }

        public List<Order> getOrderByList() {
            return Collections.unmodifiableList(((StructuredQuery) this.instance).getOrderByList());
        }

        public int getOrderByCount() {
            return ((StructuredQuery) this.instance).getOrderByCount();
        }

        public Order getOrderBy(int index) {
            return ((StructuredQuery) this.instance).getOrderBy(index);
        }

        public Builder setOrderBy(int index, Order value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setOrderBy(index, value);
            return this;
        }

        public Builder setOrderBy(int index, Order.Builder builderForValue) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setOrderBy(index, (Order) builderForValue.build());
            return this;
        }

        public Builder addOrderBy(Order value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addOrderBy(value);
            return this;
        }

        public Builder addOrderBy(int index, Order value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addOrderBy(index, value);
            return this;
        }

        public Builder addOrderBy(Order.Builder builderForValue) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addOrderBy((Order) builderForValue.build());
            return this;
        }

        public Builder addOrderBy(int index, Order.Builder builderForValue) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addOrderBy(index, (Order) builderForValue.build());
            return this;
        }

        public Builder addAllOrderBy(Iterable<? extends Order> values) {
            copyOnWrite();
            ((StructuredQuery) this.instance).addAllOrderBy(values);
            return this;
        }

        public Builder clearOrderBy() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearOrderBy();
            return this;
        }

        public Builder removeOrderBy(int index) {
            copyOnWrite();
            ((StructuredQuery) this.instance).removeOrderBy(index);
            return this;
        }

        public boolean hasStartAt() {
            return ((StructuredQuery) this.instance).hasStartAt();
        }

        public Cursor getStartAt() {
            return ((StructuredQuery) this.instance).getStartAt();
        }

        public Builder setStartAt(Cursor value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setStartAt(value);
            return this;
        }

        public Builder setStartAt(Cursor.Builder builderForValue) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setStartAt((Cursor) builderForValue.build());
            return this;
        }

        public Builder mergeStartAt(Cursor value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeStartAt(value);
            return this;
        }

        public Builder clearStartAt() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearStartAt();
            return this;
        }

        public boolean hasEndAt() {
            return ((StructuredQuery) this.instance).hasEndAt();
        }

        public Cursor getEndAt() {
            return ((StructuredQuery) this.instance).getEndAt();
        }

        public Builder setEndAt(Cursor value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setEndAt(value);
            return this;
        }

        public Builder setEndAt(Cursor.Builder builderForValue) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setEndAt((Cursor) builderForValue.build());
            return this;
        }

        public Builder mergeEndAt(Cursor value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeEndAt(value);
            return this;
        }

        public Builder clearEndAt() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearEndAt();
            return this;
        }

        public int getOffset() {
            return ((StructuredQuery) this.instance).getOffset();
        }

        public Builder setOffset(int value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setOffset(value);
            return this;
        }

        public Builder clearOffset() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearOffset();
            return this;
        }

        public boolean hasLimit() {
            return ((StructuredQuery) this.instance).hasLimit();
        }

        public Int32Value getLimit() {
            return ((StructuredQuery) this.instance).getLimit();
        }

        public Builder setLimit(Int32Value value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setLimit(value);
            return this;
        }

        public Builder setLimit(Int32Value.Builder builderForValue) {
            copyOnWrite();
            ((StructuredQuery) this.instance).setLimit((Int32Value) builderForValue.build());
            return this;
        }

        public Builder mergeLimit(Int32Value value) {
            copyOnWrite();
            ((StructuredQuery) this.instance).mergeLimit(value);
            return this;
        }

        public Builder clearLimit() {
            copyOnWrite();
            ((StructuredQuery) this.instance).clearLimit();
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        switch (C08571.f230xa1df5c61[method.ordinal()]) {
            case 1:
                return new StructuredQuery();
            case 2:
                return new Builder((C08571) null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\b\u0000\u0000\u0001\b\b\u0000\u0002\u0000\u0001\t\u0002\u001b\u0003\t\u0004\u001b\u0005\t\u0006\u0004\u0007\t\b\t", new Object[]{"select_", "from_", CollectionSelector.class, "where_", "orderBy_", Order.class, "limit_", "offset_", "startAt_", "endAt_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<StructuredQuery> parser = PARSER;
                if (parser == null) {
                    synchronized (StructuredQuery.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        StructuredQuery defaultInstance = new StructuredQuery();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(StructuredQuery.class, defaultInstance);
    }

    public static StructuredQuery getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StructuredQuery> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
