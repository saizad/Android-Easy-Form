package com.sa.easyandroidfrom.fields;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;


public class IntegerListFieldTest extends AllValueValidFieldTest<List<Integer>> {

    private static final String FIELD_NAME = "random";
    private static final List<Integer> VALUE = Arrays.asList(3,2);
    private static final List<Integer> NEW_VALUE = Arrays.asList(1,3,2);

    public IntegerListFieldTest() {
        super(new IntegerListField(FIELD_NAME), new IntegerListField(FIELD_NAME, true), new IntegerListField(FIELD_NAME, VALUE, true), new IntegerListField(FIELD_NAME, VALUE));
    }

    @NotNull
    @Override
    protected String fieldName() {
        return FIELD_NAME;
    }

    @NotNull
    @Override
    protected List<Integer> getNewValidFieldValue() {
        return NEW_VALUE;
    }
}