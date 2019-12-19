package com.sa.easyandroidfrom.fields;

import org.jetbrains.annotations.NotNull;

class MandatoryFloatFieldTest extends BaseMandatoryFieldTest<Float> {

    private static final String FIELD_NAME = "random";
    private static final Float VALUE = 323.3f;
    private static final Float NEW_VALUE = 32.3321f;

    public MandatoryFloatFieldTest() {
        super(new MandatoryFloatField(FIELD_NAME), new MandatoryFloatField(FIELD_NAME, VALUE));
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

    @Override
    protected Float getInValidFieldValue() {
        return null;
    }
}