package com.sa.easyandroidfrom.fields.test;

import com.sa.easyandroidfrom.fields.GenderField;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

class GenderFieldTest  extends BaseFieldTest<String> {

    private static final String FIELD_NAME = "random";
    private static final List<String> GENDERS = Arrays.asList("male","female","other");
    private static final String VALUE = GENDERS.get(0);
    private static final String NEW_VALUE = GENDERS.get(1);

    public GenderFieldTest() {
        super(new GenderField(FIELD_NAME, GENDERS), new GenderField(FIELD_NAME, true, GENDERS), new GenderField(FIELD_NAME, VALUE, true, GENDERS), new GenderField(FIELD_NAME, VALUE, GENDERS));
    }

    @Override
    protected String getInValidFieldValue() {
        return "no_gender";
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