package com.sa.easyandroidform.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DoubleListField extends ListField<Double> {

    public DoubleListField(@NonNull String fieldId) {
        super(fieldId);
    }

    public DoubleListField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public DoubleListField(@NonNull String fieldId, @Nullable List<Double> ogField) {
        super(fieldId, ogField);
    }

    public DoubleListField(@NonNull String fieldId, @Nullable List<Double> ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    protected boolean compare(Double item1, Double item2) {
        return item1.compareTo(item2) == 0;
    }
}
