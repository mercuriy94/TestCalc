package com.mercuriy94.calculator.operation;

public class MinusOperation implements Operation {
    @Override
    public float doOperation(float value1, float value2) {
        return value1 - value2;
    }
}
