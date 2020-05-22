package com.sa.easyandroidform.fields.time;

import org.joda.time.DateTime;

public class PastDateFieldTest extends BaseDateFieldTest {

    public PastDateFieldTest() {
        super(new PastDateField(FIELD_NAME), new PastDateField(FIELD_NAME, true), new PastDateField(FIELD_NAME, NOW, true), new PastDateField(FIELD_NAME, NOW));
    }

    @Override
    protected String getInvalidFieldValue() {
        return new DateTime().plusDays(1).toString();
    }
}
