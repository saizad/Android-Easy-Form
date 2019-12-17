package com.sa.easyandroidfrom.fields;

import org.jetbrains.annotations.NotNull;


public class IntegerFieldTest extends AllValueValidFieldTest<Integer> {

    private static final String FIELD_NAME = "random";
    private static final Integer VALUE = 1;
    private static final Integer NEW_VALUE = 2;

    public IntegerFieldTest() {
        super(new IntegerField(FIELD_NAME), new IntegerField(FIELD_NAME, true), new IntegerField(FIELD_NAME, VALUE, true), new IntegerField(FIELD_NAME, VALUE));
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
}