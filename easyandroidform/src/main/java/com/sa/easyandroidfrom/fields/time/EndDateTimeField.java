package com.sa.easyandroidfrom.fields.time;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.joda.time.DateTime;

import io.reactivex.exceptions.CompositeException;

public class EndDateTimeField extends DateTimeField {

    private transient DateTimeField startDateTimeField;

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

    @Override
    public void validate() throws CompositeException {
        final DateTime startDateTimeField = this.startDateTimeField.dateTime();
        final DateTime dateTime = dateTime();
        if (startDateTimeField != null && dateTime != null && dateTime.isBefore(startDateTimeField)) {
            throw new CompositeException(new Exception("End date time can't start before start date time"));
        }
    }

    public void setStartDateTimeField(@NonNull DateTimeField startDateTimeField) {
        this.startDateTimeField = startDateTimeField;
        startDateTimeField.observable().subscribe(__ -> publish());
    }
}
