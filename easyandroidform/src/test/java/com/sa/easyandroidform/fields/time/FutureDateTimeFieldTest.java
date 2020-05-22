package com.sa.easyandroidform.fields.time;

import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;

public class FutureDateTimeFieldTest extends BaseDateTimeFieldTest {

    public FutureDateTimeFieldTest() {
        super(new FutureDateTimeField(FIELD_NAME), new FutureDateTimeField(FIELD_NAME, true), new FutureDateTimeField(FIELD_NAME, NOW, true), new FutureDateTimeField(FIELD_NAME, NOW));
    }

    @NotNull
    @Override
    protected String getNewValidFieldValue() {
        return new DateTime().plusMinutes(1).toString();
    }

    @Override
    protected String getInvalidFieldValue() {
        return new DateTime().minusMinutes(1).toString();
    }
}
