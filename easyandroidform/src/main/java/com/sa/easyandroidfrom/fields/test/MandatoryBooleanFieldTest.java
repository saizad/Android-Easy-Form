package com.sa.easyandroidfrom.fields.test;

import com.sa.easyandroidfrom.fields.MandatoryBooleanField;

import org.jetbrains.annotations.NotNull;

class MandatoryBooleanFieldTest extends BaseMandatoryFieldTest<Boolean> {

    private static final String FIELD_NAME = "random";
    private static final Boolean VALUE = true;
    private static final Boolean NEW_VALUE = false;

    public MandatoryBooleanFieldTest() {
        super(new MandatoryBooleanField(FIELD_NAME), new MandatoryBooleanField(FIELD_NAME, VALUE));
    }

    @NotNull
    @Override
    protected String fieldName() {
        return FIELD_NAME;
    }

    @NotNull
    @Override
    protected Boolean getNewValidFieldValue() {
        return NEW_VALUE;
    }

    @Override
    protected Boolean getInValidFieldValue() {
        return null;
    }
}