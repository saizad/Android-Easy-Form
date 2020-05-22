package com.sa.easyandroidform.fields.time;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;

public class DobFieldTest extends BaseDateFieldTest {

    private static final int MIN_AGE = 18;
    private final @NonNull DateTime minDate = new DateTime().minusYears(MIN_AGE);

    public DobFieldTest() {
        super(new DobField(FIELD_NAME, MIN_AGE), new DobField(FIELD_NAME, true, MIN_AGE), new DobField(FIELD_NAME, NOW, true, MIN_AGE), new DobField(FIELD_NAME, NOW, MIN_AGE));
    }

    @NotNull
    @Override
    protected String getNewValidFieldValue() {
        return minDate.minusDays(10).toString();
    }

    @Override
    protected String getInvalidFieldValue() {
        return minDate.plusDays(1).toString();
    }
}
