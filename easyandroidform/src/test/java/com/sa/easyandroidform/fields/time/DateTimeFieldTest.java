package com.sa.easyandroidform.fields.time;

public class DateTimeFieldTest extends BaseDateTimeFieldTest {

    public DateTimeFieldTest() {
        super(new DateTimeField(FIELD_NAME), new DateTimeField(FIELD_NAME, true), new DateTimeField(FIELD_NAME, NOW, true), new DateTimeField(FIELD_NAME, NOW));
    }

    @Override
    protected String getInvalidFieldValue() {
        return "AFASDFSA";
    }
}
