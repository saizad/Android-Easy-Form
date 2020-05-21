package com.sa.easyandroidfrom.fields.test;

import com.sa.easyandroidfrom.fields.Field;
import com.sa.easyandroidfrom.fields.test.AllValueValidFieldTest;

import org.jetbrains.annotations.NotNull;

public class FieldTest extends AllValueValidFieldTest<String> {

    private static final String FIELD_NAME = "random";
    private static final String VALUE = "any";
    private static final String NEW_VALUE = "new_value";

    public FieldTest() {
        super(new Field<>(FIELD_NAME), new Field<>(FIELD_NAME, true), new Field<>(FIELD_NAME, VALUE, true), new Field<>(FIELD_NAME, VALUE));
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