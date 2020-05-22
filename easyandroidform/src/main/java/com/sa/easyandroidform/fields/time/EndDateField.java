package com.sa.easyandroidform.fields.time;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidform.ObjectUtils;

import org.joda.time.DateTime;

public class EndDateField extends DateField {

    private transient @NonNull
    DateTimeField startDateField;

    public EndDateField(@NonNull String fieldId) {
        super(fieldId);
    }

    public EndDateField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public EndDateField(@NonNull String fieldId, @Nullable String ogField) {
        super(fieldId, ogField);
    }

    public EndDateField(@NonNull String fieldId, @Nullable String ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    public void setStartDateField(@NonNull DateField startDateField) {
        ObjectUtils.requireNonNull(startDateField);
        this.startDateField = startDateField;
        startDateField.observable().subscribe(__ -> publish());
    }

    @Override
    protected DateTime resolveToDateTime(@NonNull String dateString) throws Exception {
        final DateTime dateTime = super.resolveToDateTime(dateString);
        final DateTime startDatetime = startDateField.dateTime();
        final String s = dateTime.toString();
        if (startDatetime != null && dateTime.isBefore(startDatetime)) {
            throw new Exception("End date can't start before start date");
        } else if (startDatetime == null) {
            throw new Exception("Start time related field not provided");
        }
        return dateTime;
    }
}
