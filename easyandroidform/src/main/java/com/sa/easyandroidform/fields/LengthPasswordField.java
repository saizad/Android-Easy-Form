package com.sa.easyandroidform.fields;

import androidx.annotation.NonNull;

import com.sa.easyandroidform.ObjectUtils;

import io.reactivex.exceptions.CompositeException;


public class LengthPasswordField extends PasswordField {

    private final int length;

    public LengthPasswordField(@NonNull String fieldId, int length) {
        super(fieldId);
        this.length = length;
    }

    private boolean isPasswordValid() {
        return (ObjectUtils.isNotNull(getField()) && getField().length() >= length) || !isSet() && !isMandatory();
    }

    @Override
    public void validate() throws CompositeException {
        super.validate();
        if (!isPasswordValid()) {
            throw new CompositeException(new Exception("Password min length should be " + length + " characters and can be used in combination of alphanumeric"));
        }
    }
}
