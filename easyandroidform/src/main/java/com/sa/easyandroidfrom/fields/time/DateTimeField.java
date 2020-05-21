package com.sa.easyandroidfrom.fields.time;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.fields.StringField;

import org.joda.time.DateTime;

import io.reactivex.exceptions.CompositeException;


public class DateTimeField extends StringField {


    public DateTimeField(@NonNull String fieldId) {
        super(fieldId);
    }

    public DateTimeField(@NonNull String fieldId, boolean isMandatory) {
        super(fieldId, isMandatory);
    }

    public DateTimeField(@NonNull String fieldId, @Nullable String ogField) {
        super(fieldId, ogField);
    }

    public DateTimeField(@NonNull String fieldId, @Nullable String ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
    }

    @Override
    public void validate() throws CompositeException {
        super.validate();
        new DateTime(super.getField());
    }

    @Nullable
    public DateTime dateTime() {
        if (isSet()) {
            return new DateTime(super.getField());
        }
        return null;
    }
}
