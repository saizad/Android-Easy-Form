package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;


public class BasePasswordField extends NonEmptyMandatoryStringField {

    public BasePasswordField(@NonNull String fieldId) {
        super(fieldId, null);
    }

}
