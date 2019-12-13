package com.sa.easyandroidfrom.fields;


import org.jetbrains.annotations.NotNull;

public class PasswordFieldTest extends BasePasswordFieldTest {

    private static final String FIELD_NAME = "random";
    private static final String NEW_VALUE = "new_value";

    public PasswordFieldTest() {
        super(new PasswordField(FIELD_NAME));
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