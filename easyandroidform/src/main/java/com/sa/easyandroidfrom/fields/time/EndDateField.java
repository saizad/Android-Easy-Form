package com.sa.easyandroidfrom.fields.time;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.joda.time.DateTime;

public class EndDateField extends DateField {

    private DateTimeField startDateField;

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

    @Override
    public void validate() throws Exception {
        final DateTime startDatetime = startDateField.dateTime();
        final DateTime dateTime = dateTime();
        if (startDatetime != null && dateTime != null && dateTime.isBefore(startDatetime)) {
            throw new Exception("End date can't start before start date");
        }
    }

    public void setStartDateField(@NonNull DateField startDateField) {
        this.startDateField = startDateField;
        startDateField.observable().subscribe(__ -> publish());
    }
}
