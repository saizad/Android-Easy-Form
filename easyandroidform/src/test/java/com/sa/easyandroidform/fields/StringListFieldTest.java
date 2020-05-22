package com.sa.easyandroidform.fields;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;


public class StringListFieldTest extends BaseListFieldTest<String> {

    private static final String FIELD_NAME = "random";
    private static final List<String> VALUE = Arrays.asList("a", "b", "c");

    public StringListFieldTest() {
        super(new StringListField(FIELD_NAME), new StringListField(FIELD_NAME, true), new StringListField(FIELD_NAME, VALUE, true), new StringListField(FIELD_NAME, VALUE));
    }

    @NotNull
    @Override
    protected String fieldName() {
        return FIELD_NAME;
    }


    @NonNull
    @Override
    protected List<String> getListSizeMoreThanOGField() {
        return Arrays.asList("a", "b", "c", "d");
    }

    @NonNull
    @Override
    protected List<String> getListSizeLessThanOGField() {
        return Arrays.asList("a", "b");
    }

    @NonNull
    @Override
    protected List<String> getListSizeEqualToOGField() {
        return Arrays.asList("a", "c", "d");
    }
}