package com.sa.easyandroidform.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidform.ObjectUtils;

import io.reactivex.exceptions.CompositeException;


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
        publish();
    }

    private boolean isPhoneNumberValid() {
        return (ObjectUtils.isNotNull(getField()) && getField().length() == length) || !isSet() && !isMandatory();
    }

    @Override
    public void validate() throws CompositeException {
        super.validate();
        if (!isPhoneNumberValid()) {
            throw new CompositeException(new Exception("Mobile number should be " + length + " digits long"));
        }
    }
}
