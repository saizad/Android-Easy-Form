package com.sa.easyandroidform.fields.time;

public class DateFieldTest extends BaseDateFieldTest {

    public DateFieldTest() {
        super(new DateField(FIELD_NAME), new DateField(FIELD_NAME, true), new DateField(FIELD_NAME, NOW, true), new DateField(FIELD_NAME, NOW));
    }

    @Override
    protected String getInvalidFieldValue() {
        return "ASFASDF";
    }
}
