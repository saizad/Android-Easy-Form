package com.sa.easyandroidfrom.fields;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.StringUtils;


public class NonEmptyMandatoryStringField extends NonEmptyStringField {

    public NonEmptyMandatoryStringField(@NonNull String fieldId) {
        this(fieldId, null);
    }

    public NonEmptyMandatoryStringField(@NonNull String fieldId, @Nullable String ogField) {
        super(fieldId, ogField, true);
    }


    @Override
    @CallSuper
    public void validate() throws Exception {
        if (StringUtils.isNullOrEmpty(getField())) {
            throw new Exception(getFieldId() + " is required");
        }
    }
}
