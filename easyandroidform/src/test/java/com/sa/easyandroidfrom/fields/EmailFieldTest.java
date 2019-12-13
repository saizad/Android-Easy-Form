package com.sa.easyandroidfrom.fields;

import org.jetbrains.annotations.NotNull;


public class EmailFieldTest extends BaseFieldTest<String> {

    private static final String FIELD_NAME = "random";
    private static final String VALUE = "any@gmail.com";
    private static final String NEW_VALUE = "new_value@gmail.com";

    public EmailFieldTest() {
        super(new EmailField(FIELD_NAME), new EmailField(FIELD_NAME, true), new EmailField(FIELD_NAME, VALUE, true), new EmailField(FIELD_NAME, VALUE));
    }

    @Override
    protected String getInValidFieldValue() {
        return "any@gmail";
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