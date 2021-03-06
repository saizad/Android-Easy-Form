package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.ObjectUtils;


public class PhoneNumberField extends NonEmptyStringField {

    private final int length;

    public PhoneNumberField(@NonNull String fieldId, int length) {
        this(fieldId, false, length);
    }

    public PhoneNumberField(@NonNull String fieldId, boolean isMandatory, int length) {
        this(fieldId, null, isMandatory, length);
    }

    public PhoneNumberField(@NonNull String fieldId, @Nullable String ogField, int length) {
        this(fieldId, ogField, false, length);
    }

    public PhoneNumberField(@NonNull String fieldId, @Nullable String ogField, boolean isMandatory, int length) {
        super(fieldId, ogField, isMandatory);
        this.length = length;
    }

    private boolean isValid() {
        return ObjectUtils.isNotNull(getField()) && getField().length() == length;
    }

    @Override
    public void validate() throws Exception {
        if (!isValid()) {
            throw new Exception("Mobile number should be " + length + " digits longer");
        }
    }
}
