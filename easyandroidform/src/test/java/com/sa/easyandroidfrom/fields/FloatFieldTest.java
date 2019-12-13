package com.sa.easyandroidfrom.fields;

import org.jetbrains.annotations.NotNull;


public class FloatFieldTest extends AllValueValidFieldTest<Float> {

    private static final String FIELD_NAME = "random";
    private static final Float VALUE = 1.3F;
    private static final Float NEW_VALUE = 2.3F;

    public FloatFieldTest() {
        super(new FloatField(FIELD_NAME), new FloatField(FIELD_NAME, true), new FloatField(FIELD_NAME, VALUE, true), new FloatField(FIELD_NAME, VALUE));
    }

    @NotNull
    @Override
    protected String fieldName() {
        return FIELD_NAME;
    }

    @NotNull
    @Override
    protected Float getNewValidFieldValue() {
        return NEW_VALUE;
    }
}