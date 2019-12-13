package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;


public class PasswordField extends NonEmptyMandatoryStringField {

    public PasswordField(@NonNull String fieldId) {
        super(fieldId, null);
    }

}
