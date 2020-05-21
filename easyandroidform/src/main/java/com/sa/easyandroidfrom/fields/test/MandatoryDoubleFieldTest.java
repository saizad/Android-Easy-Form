package com.sa.easyandroidfrom.fields.test;

import com.sa.easyandroidfrom.fields.MandatoryDoubleField;
import com.sa.easyandroidfrom.fields.test.BaseMandatoryFieldTest;

import org.jetbrains.annotations.NotNull;

class MandatoryDoubleFieldTest extends BaseMandatoryFieldTest<Double> {

    private static final String FIELD_NAME = "random";
    private static final Double VALUE = 323.3d;
    private static final Double NEW_VALUE = -32.3321d;

    public MandatoryDoubleFieldTest() {
        super(new MandatoryDoubleField(FIELD_NAME), new MandatoryDoubleField(FIELD_NAME, VALUE));
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