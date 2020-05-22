package com.sa.easyandroidform.fields.time;

import org.jetbrains.annotations.NotNull;

public class TimeFieldTest extends BaseDateTimeFieldTest {

    public TimeFieldTest() {
        super(new TimeField(FIELD_NAME), new TimeField(FIELD_NAME, true), new TimeField(FIELD_NAME, NOW, true), new TimeField(FIELD_NAME, NOW));
    }

    @NotNull
    @Override
    protected String getNewValidFieldValue() {
        return "13:44:59";
    }

    @Override
    protected String getInvalidFieldValue() {
        return "13:44";
    }
}
