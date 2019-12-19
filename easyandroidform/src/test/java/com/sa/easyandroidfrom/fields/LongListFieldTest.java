package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;


public class LongListFieldTest extends BaseListFieldTest<Long> {

    private static final String FIELD_NAME = "random";
    private static final List<Long> VALUE = Arrays.asList(3L, 2L, 9L);

    public LongListFieldTest() {
        super(new LongListField(FIELD_NAME), new LongListField(FIELD_NAME, true), new LongListField(FIELD_NAME, VALUE, true), new LongListField(FIELD_NAME, VALUE));
    }

    @NotNull
    @Override
    protected String fieldName() {
        return FIELD_NAME;
    }


    @NonNull
    @Override
    protected List<Long> getListSizeMoreThanOGField() {
        return Arrays.asList(2333L, 0L, 232L, 329L);
    }

    @NonNull
    @Override
    protected List<Long> getListSizeLessThanOGField() {
        return Arrays.asList(312L, 212L);
    }

    @NonNull
    @Override
    protected List<Long> getListSizeEqualToOGField() {
        return Arrays.asList(13L, 122L, 13L);
    }
}