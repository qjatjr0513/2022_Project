package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.p002v1.Value;

public class NumericIncrementTransformOperation implements TransformOperation {
    private Value operand;

    public NumericIncrementTransformOperation(Value operand2) {
        Assert.hardAssert(Values.isNumber(operand2), "NumericIncrementTransformOperation expects a NumberValue operand", new Object[0]);
        this.operand = operand2;
    }

    public Value applyToLocalView(Value previousValue, Timestamp localWriteTime) {
        Value baseValue = computeBaseValue(previousValue);
        if (Values.isInteger(baseValue) && Values.isInteger(this.operand)) {
            return (Value) Value.newBuilder().setIntegerValue(safeIncrement(baseValue.getIntegerValue(), operandAsLong())).build();
        } else if (Values.isInteger(baseValue)) {
            return (Value) Value.newBuilder().setDoubleValue(((double) baseValue.getIntegerValue()) + operandAsDouble()).build();
        } else {
            Assert.hardAssert(Values.isDouble(baseValue), "Expected NumberValue to be of type DoubleValue, but was ", previousValue.getClass().getCanonicalName());
            return (Value) Value.newBuilder().setDoubleValue(baseValue.getDoubleValue() + operandAsDouble()).build();
        }
    }

    public Value applyToRemoteDocument(Value previousValue, Value transformResult) {
        return transformResult;
    }

    public Value getOperand() {
        return this.operand;
    }

    public Value computeBaseValue(Value previousValue) {
        if (Values.isNumber(previousValue)) {
            return previousValue;
        }
        return (Value) Value.newBuilder().setIntegerValue(0).build();
    }

    private long safeIncrement(long x, long y) {
        long r = x + y;
        if (((x ^ r) & (y ^ r)) >= 0) {
            return r;
        }
        if (r >= 0) {
            return Long.MIN_VALUE;
        }
        return Long.MAX_VALUE;
    }

    private double operandAsDouble() {
        if (Values.isDouble(this.operand)) {
            return this.operand.getDoubleValue();
        }
        if (Values.isInteger(this.operand)) {
            return (double) this.operand.getIntegerValue();
        }
        throw Assert.fail("Expected 'operand' to be of Number type, but was " + this.operand.getClass().getCanonicalName(), new Object[0]);
    }

    private long operandAsLong() {
        if (Values.isDouble(this.operand)) {
            return (long) this.operand.getDoubleValue();
        }
        if (Values.isInteger(this.operand)) {
            return this.operand.getIntegerValue();
        }
        throw Assert.fail("Expected 'operand' to be of Number type, but was " + this.operand.getClass().getCanonicalName(), new Object[0]);
    }
}
