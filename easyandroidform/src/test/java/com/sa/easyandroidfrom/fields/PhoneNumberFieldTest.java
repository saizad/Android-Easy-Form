package com.sa.easyandroidfrom.fields;

import org.jetbrains.annotations.NotNull;


public class PhoneNumberFieldTest extends BaseFieldTest<String> {

    private static final String FIELD_NAME = "random";
    private static final String VALUE = "1234567890";
    private static final String NEW_VALUE = "9934567890";
    private static final int MAX_DIGIT = 10;

    public PhoneNumberFieldTest() {
        super(new PhoneNumberField(FIELD_NAME, MAX_DIGIT), new PhoneNumberField(FIELD_NAME, true, MAX_DIGIT), new PhoneNumberField(FIELD_NAME, VALUE, true, MAX_DIGIT), new PhoneNumberField(FIELD_NAME, VALUE, MAX_DIGIT));
    }

    @Override
    protected String getInValidFieldValue() {
        return "52345432";
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