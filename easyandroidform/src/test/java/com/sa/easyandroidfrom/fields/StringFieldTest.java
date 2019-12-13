package com.sa.easyandroidfrom.fields;

import org.jetbrains.annotations.NotNull;


public class StringFieldTest extends AllValueValidFieldTest<String> {

    private static final String FIELD_NAME = "random";
    private static final String VALUE = "any";
    private static final String NEW_VALUE = "new_any";

    public StringFieldTest() {
        super(new StringField(FIELD_NAME), new StringField(FIELD_NAME, true), new StringField(FIELD_NAME, VALUE, true), new StringField(FIELD_NAME, VALUE));
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