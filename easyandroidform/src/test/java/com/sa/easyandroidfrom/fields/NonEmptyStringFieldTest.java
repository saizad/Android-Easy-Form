package com.sa.easyandroidfrom.fields;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class NonEmptyStringFieldTest extends AllValueValidFieldTest<String> {
    
    private static final String FIELD_NAME = "random";
    private static final String VALUE = "any";
    private static final String NEW_VALUE = "new_value";

    public NonEmptyStringFieldTest() {
        super(new NonEmptyStringField(FIELD_NAME), new NonEmptyStringField(FIELD_NAME, true), new NonEmptyStringField(FIELD_NAME, VALUE, true), new NonEmptyStringField(FIELD_NAME, VALUE));
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

    @Test
    void empty_value_null(){
        field.setField("");
        assertNull(field.getField());
    }

    @Test
    void non_empty_value_not_null(){
        field.setField(NEW_VALUE);
        assertNotNull(field.getField());
    }
}