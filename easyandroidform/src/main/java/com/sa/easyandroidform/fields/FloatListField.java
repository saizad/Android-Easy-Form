package com.sa.easyandroidform.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FloatListField extends ListField<Float> {

    public FloatListField(@NonNull String fieldId) {
        super(fieldId);
    }

    public FloatListField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public FloatListField(@NonNull String fieldId, @Nullable List<Float> ogField) {
        super(fieldId, ogField);
    }

    public FloatListField(@NonNull String fieldId, @Nullable List<Float> ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    protected boolean compare(Float item1, Float item2) {
        return item1.compareTo(item2) == 0;
    }
}
