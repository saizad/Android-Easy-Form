package com.sa.easyandroidfrom.fields;


import org.jetbrains.annotations.NotNull;

public class ConfirmPasswordFieldTest extends BasePasswordFieldTest {

    private static final String FIELD_NAME = "random";
    private static final String NEW_VALUE = "new_value";

    public ConfirmPasswordFieldTest() {
        super(new ConfirmPasswordField(FIELD_NAME));
        final PasswordField passwordField = new PasswordField(FIELD_NAME);
        ((ConfirmPasswordField) field).setRelated(passwordField);
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