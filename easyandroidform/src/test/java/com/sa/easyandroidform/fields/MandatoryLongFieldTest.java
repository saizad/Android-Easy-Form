package com.sa.easyandroidform.fields;

import org.jetbrains.annotations.NotNull;

class MandatoryLongFieldTest extends BaseMandatoryFieldTest<Long> {

    private static final String FIELD_NAME = "random";
    private static final Long VALUE = 323L;
    private static final Long NEW_VALUE = 323321L;

    public MandatoryLongFieldTest() {
        super(new MandatoryLongField(FIELD_NAME), new MandatoryLongField(FIELD_NAME, VALUE));
    }

    @NotNull
    @Override
    protected String fieldName() {
        return FIELD_NAME;
    }

    @NotNull
    @Override
    protected Long getNewValidFieldValue() {
        return NEW_VALUE;
    }

    @Override
    protected Long getInvalidFieldValue() {
        return null;
    }
}