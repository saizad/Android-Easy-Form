package com.sa.easyandroidfrom.fields.test;

import androidx.annotation.NonNull;

import com.sa.easyandroidfrom.fields.DoubleListField;
import com.sa.easyandroidfrom.fields.test.BaseListFieldTest;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;


public class DoubleListFieldTest extends BaseListFieldTest<Double> {

    private static final String FIELD_NAME = "random";
    private static final List<Double> VALUE = Arrays.asList(3.3d, 2d, -1.9d);

    public DoubleListFieldTest() {
        super(new DoubleListField(FIELD_NAME), new DoubleListField(FIELD_NAME, true), new DoubleListField(FIELD_NAME, VALUE, true), new DoubleListField(FIELD_NAME, VALUE));
    }

    @NotNull
    @Override
    protected String fieldName() {
        return FIELD_NAME;
    }


    @NonNull
    @Override
    protected List<Double> getListSizeMoreThanOGField() {
        return Arrays.asList(3.3d, 2d, -1.9d, 0d);
    }

    @NonNull
    @Override
    protected List<Double> getListSizeLessThanOGField() {
        return Arrays.asList(3.3d, -1.9d);
    }

    @NonNull
    @Override
    protected List<Double> getListSizeEqualToOGField() {
        return Arrays.asList(3.3d, -1.9d, -0d);
    }
}