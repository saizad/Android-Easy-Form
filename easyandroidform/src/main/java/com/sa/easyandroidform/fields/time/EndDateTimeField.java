package com.sa.easyandroidform.fields.time;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidform.ObjectUtils;

import org.joda.time.DateTime;

public class EndDateTimeField extends DateTimeField {

    private transient @NonNull DateTimeField startDateTimeField;

    public EndDateTimeField(@NonNull String fieldId) {
        super(fieldId);
    }

    public EndDateTimeField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public EndDateTimeField(@NonNull String fieldId, @Nullable String ogField) {
        super(fieldId, ogField);
    }

    public EndDateTimeField(@NonNull String fieldId, @Nullable String ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    public void setStartDateTimeField(@NonNull DateTimeField startDateTimeField) {
        ObjectUtils.requireNonNull(startDateTimeField);
        this.startDateTimeField = startDateTimeField;
        startDateTimeField.observable().subscribe(__ -> publish());
    }

    @Override
    DateTime resolveToDateTime(@NonNull String dateString) throws Exception {
        final DateTime dateTime = super.resolveToDateTime(dateString);
        final DateTime startDateTime = startDateTimeField.dateTime();
        if (startDateTime != null && dateTime.isBefore(startDateTime)) {
            throw new Exception("End date time can't start before start date time");
        }
        return dateTime;
    }
}
