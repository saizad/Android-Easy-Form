package com.sa.easyandroidfrom.fields;

import org.jetbrains.annotations.NotNull;


public class DoubleFieldTest extends AllValueValidFieldTest<Double> {

    private static final String FIELD_NAME = "random";
    private static final Double VALUE = 1.3D;
    private static final Double NEW_VALUE = 2.3D;

    public DoubleFieldTest() {
        super(new DoubleField(FIELD_NAME), new DoubleField(FIELD_NAME, true), new DoubleField(FIELD_NAME, VALUE, true), new DoubleField(FIELD_NAME, VALUE));
    }

    @NotNull
    @Override
    protected String fieldName() {
        return FIELD_NAME;
    }

    @NotNull
    @Override
    protected Double getNewValidFieldValue() {
        return NEW_VALUE;
    }
}