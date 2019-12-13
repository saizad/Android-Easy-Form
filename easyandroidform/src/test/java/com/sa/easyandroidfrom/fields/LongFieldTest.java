package com.sa.easyandroidfrom.fields;

import org.jetbrains.annotations.NotNull;


public class LongFieldTest extends AllValueValidFieldTest<Long> {

    private static final String FIELD_NAME = "random";
    private static final Long VALUE = 1332414123412341234L;
    private static final Long NEW_VALUE = 32424L;

    public LongFieldTest() {
        super(new LongField(FIELD_NAME), new LongField(FIELD_NAME, true), new LongField(FIELD_NAME, VALUE, true), new LongField(FIELD_NAME, VALUE));
    }

    @NotNull
    @Override
    protected String fieldName() {
        return FIELD_NAME;
    }

    @NotNull
    @Override
    protected Long getNewValidFieldValue() {
        return NEW_VALUE;
    }
}