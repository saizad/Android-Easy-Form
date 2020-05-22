package com.sa.easyandroidform.fields;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;


public class FloatListFieldTest extends BaseListFieldTest<Float> {

    private static final String FIELD_NAME = "random";
    private static final List<Float> VALUE = Arrays.asList(3f, 2f, 9f);

    public FloatListFieldTest() {
        super(new FloatListField(FIELD_NAME), new FloatListField(FIELD_NAME, true), new FloatListField(FIELD_NAME, VALUE, true), new FloatListField(FIELD_NAME, VALUE));
    }

    @NotNull
    @Override
    protected String fieldName() {
        return FIELD_NAME;
    }


    @NonNull
    @Override
    protected List<Float> getListSizeMoreThanOGField() {
        return Arrays.asList(3f, 2f, 5f, 10f);
    }

    @NonNull
    @Override
    protected List<Float> getListSizeLessThanOGField() {
        return Arrays.asList(1f, 3f);
    }

    @NonNull
    @Override
    protected List<Float> getListSizeEqualToOGField() {
        return Arrays.asList(2f, 3f, 4f);
    }
}