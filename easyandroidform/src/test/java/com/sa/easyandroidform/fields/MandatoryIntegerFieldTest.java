package com.sa.easyandroidform.fields;

import org.jetbrains.annotations.NotNull;

class MandatoryIntegerFieldTest extends BaseMandatoryFieldTest<Integer> {

    private static final String FIELD_NAME = "random";
    private static final Integer VALUE = 1;
    private static final Integer NEW_VALUE = -1;

    public MandatoryIntegerFieldTest() {
        super(new MandatoryIntegerField(FIELD_NAME), new MandatoryIntegerField(FIELD_NAME, VALUE));
    }

    @NotNull
    @Override
    protected String fieldName() {
        return FIELD_NAME;
    }

    @NotNull
    @Override
    protected Integer getNewValidFieldValue() {
        return NEW_VALUE;
    }

    @Override
    protected Integer getInvalidFieldValue() {
        return null;
    }
}