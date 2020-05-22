package com.sa.easyandroidform.fields.time;

import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;

public class FutureDateFieldTest extends BaseDateFieldTest {

    protected static final String VALUE = DateTime.now().plusDays(1).toString();

    public FutureDateFieldTest() {
        super(new FutureDateField(FIELD_NAME), new FutureDateField(FIELD_NAME, true), new FutureDateField(FIELD_NAME, VALUE, true), new FutureDateField(FIELD_NAME, VALUE));
    }

    @Override
    protected String getInvalidFieldValue() {
        return new DateTime().minusDays(1).toString();
    }

    @NotNull
    @Override
    protected String getNewValidFieldValue() {
        return new DateTime().plusDays(1).toString();
    }
}
