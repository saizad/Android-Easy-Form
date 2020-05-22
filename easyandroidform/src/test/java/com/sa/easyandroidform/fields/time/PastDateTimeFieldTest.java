package com.sa.easyandroidform.fields.time;

import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;

public class PastDateTimeFieldTest extends BaseDateTimeFieldTest {

    public PastDateTimeFieldTest() {
        super(new PastDateTimeField(FIELD_NAME), new PastDateTimeField(FIELD_NAME, true), new PastDateTimeField(FIELD_NAME, NOW, true), new PastDateTimeField(FIELD_NAME, NOW));
    }

    @NotNull
    @Override
    protected String getNewValidFieldValue() {
        return new DateTime().minusMinutes(1).toString();
    }

    @Override
    protected String getInvalidFieldValue() {
        return new DateTime().plusMinutes(1).toString();
    }
}
