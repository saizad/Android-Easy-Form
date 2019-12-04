package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class IntegerField extends BaseField<Integer> {

    public IntegerField(@NonNull String fieldId) {
        super(fieldId);
    }

    public IntegerField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public IntegerField(@NonNull String fieldId, @Nullable Integer ogField) {
        super(fieldId, ogField);
    }

    public IntegerField(@NonNull String fieldId, @Nullable Integer ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Integer) {
            return obj.equals(getField());
        }
        return super.equals(obj);
    }

    @Override
    public void validate() throws Exception {

    }
}
