package com.sa.easyandroidfrom.fields;

import org.jetbrains.annotations.NotNull;

class MandatoryStringFieldTest extends BaseMandatoryStringFieldTest {

    private static final String FIELD_NAME = "random";
    private static final String VALUE = "any";
    private static final String NEW_VALUE = "new_value";

    public MandatoryStringFieldTest() {
        super(new MandatoryStringField(FIELD_NAME), new MandatoryStringField(FIELD_NAME, VALUE));
    }

    @NotNull
    @Override
    protected String fieldName() {
        return FIELD_NAME;
    }

    @NotNull
    @Override
    protected String getNewValidFieldValue() {
        return NEW_VALUE;
    }
}