package com.sa.easyandroidform.field_view;

import com.sa.easyandroidform.StringUtils;
import com.sa.easyandroidform.fields.PhoneNumberField;

import org.jetbrains.annotations.Nullable;

public class StringFieldViewTest extends BaseFieldViewTestKT<String, StringFieldView> {

    private static final String FIELD_NAME = "random";
    private static final int VALID_LENGTH = 10;
    private static final int INVALID_LENGTH = 5;
    private static final String VALUE = StringUtils.random(VALID_LENGTH);

    public StringFieldViewTest() {
        super(StringFieldView.class, new PhoneNumberField(FIELD_NAME, VALID_LENGTH), new PhoneNumberField(FIELD_NAME, true, VALID_LENGTH), new PhoneNumberField(FIELD_NAME, VALUE, true, VALID_LENGTH), new PhoneNumberField(FIELD_NAME, VALUE, VALID_LENGTH));
    }

    @Nullable
    @Override
    public String invalidValue() {
        return StringUtils.random(INVALID_LENGTH);
    }

    @Nullable
    @Override
    public String validValue() {
        return StringUtils.random(VALID_LENGTH);
    }
}
