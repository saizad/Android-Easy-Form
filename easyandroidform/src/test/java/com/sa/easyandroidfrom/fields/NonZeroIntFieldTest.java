package com.sa.easyandroidfrom.fields;


import org.jetbrains.annotations.NotNull;

public class NonZeroIntFieldTest extends BaseFieldTest<Integer> {

    private static final String FIELD_NAME = "random";
    private static final Integer VALUE = 1;
    private static final Integer NEW_VALUE = 2;

    public NonZeroIntFieldTest() {
        super(new NonZeroIntField(FIELD_NAME), new NonZeroIntField(FIELD_NAME, true), new NonZeroIntField(FIELD_NAME, VALUE, true), new NonZeroIntField(FIELD_NAME, VALUE));
    }

    @Override
    protected Integer getInValidFieldValue() {
        return 0;
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