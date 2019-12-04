package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.StringUtils;


public class EmailField extends NonEmptyStringField {

    public EmailField(@NonNull String fieldId) {
        super(fieldId);
    }

    public EmailField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public EmailField(@NonNull String fieldId, @Nullable String ogField) {
        super(fieldId, ogField);
    }

    public EmailField(@NonNull String fieldId, @Nullable String ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    public void validate() throws Exception {
        if(!StringUtils.isValidEmail(getField())){
            throw new Exception("Invalid email");
        }
    }
}
