package com.sa.easyandroidfrom.fields;

import org.jetbrains.annotations.NotNull;


public class BooleanFieldTest extends AllValueValidFieldTest<Boolean> {

    private static final String FIELD_NAME = "random";
    private static final Boolean VALUE = false;
    private static final Boolean NEW_VALUE = true;

    public BooleanFieldTest() {
        super(new BooleanField(FIELD_NAME), new BooleanField(FIELD_NAME, true), new BooleanField(FIELD_NAME, VALUE, true), new BooleanField(FIELD_NAME, VALUE));
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
}