package com.sa.easyandroidfrom.fields.test;

import androidx.annotation.NonNull;

import com.sa.easyandroidfrom.fields.IntegerListField;
import com.sa.easyandroidfrom.fields.test.BaseListFieldTest;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;


public class IntegerListFieldTest extends BaseListFieldTest<Integer> {

    private static final String FIELD_NAME = "random";
    private static final List<Integer> VALUE = Arrays.asList(3, 2, 9);

    public IntegerListFieldTest() {
        super(new IntegerListField(FIELD_NAME), new IntegerListField(FIELD_NAME, true), new IntegerListField(FIELD_NAME, VALUE, true), new IntegerListField(FIELD_NAME, VALUE));
    }

    @NotNull
    @Override
    protected String fieldName() {
        return FIELD_NAME;
    }


    @NonNull
    @Override
    protected List<Integer> getListSizeMoreThanOGField() {
        return Arrays.asList(3, 2, 5, 10);
    }

    @NonNull
    @Override
    protected List<Integer> getListSizeLessThanOGField() {
        return Arrays.asList(1, 3);
    }

    @NonNull
    @Override
    protected List<Integer> getListSizeEqualToOGField() {
        return Arrays.asList(2, 3, 4);
    }
}