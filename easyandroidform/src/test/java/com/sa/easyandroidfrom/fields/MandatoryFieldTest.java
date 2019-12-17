package com.sa.easyandroidfrom.fields;

import org.jetbrains.annotations.NotNull;


public class MandatoryFieldTest extends BaseMandatoryFieldTest<Double> {

    private static final String FIELD_NAME = "random";
    private static final Double VALUE = 1.3D;
    private static final Double NEW_VALUE = 2.3D;

    public MandatoryFieldTest() {
        super(new MandatoryField<>(FIELD_NAME), new MandatoryField<>(FIELD_NAME, VALUE));
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

    @Override
    protected Double getInValidFieldValue() {
        return null;
    }
}