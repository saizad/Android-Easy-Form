package com.sa.easyandroidform.fields.time;

import com.sa.easyandroidform.fields.BaseField;

import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;

public class EndDateFieldTest extends BaseDateFieldTest {

    protected static final DateTime OG_END_TIME = DateTime.now().plusDays(1);
    protected static final DateTime START_DATE = OG_END_TIME.minusDays(1);
    protected static final DateTime INVALID_DATE = OG_END_TIME.plusDays(1);
    protected static final DateTime NEW_VALID_DATE = START_DATE.minusDays(1);

    private final DateField startDateField = new DateField(FIELD_NAME);

    private final DateField mandatoryStartDateField = new DateField(FIELD_NAME, true);

    private final DateField mandatoryOGStartDateField = new DateField(FIELD_NAME, START_DATE.toString(), true);

    private final DateField nonMandatoryStartDateField = new DateField(FIELD_NAME, START_DATE.toString());

    public EndDateFieldTest() {
        super(new EndDateField(FIELD_NAME), new EndDateField(FIELD_NAME, true), new EndDateField(FIELD_NAME, OG_END_TIME.toString(), true), new EndDateField(FIELD_NAME, OG_END_TIME.toString()));

        setRelated(field, startDateField);
        setRelated(mandatoryField, mandatoryStartDateField);
        setRelated(mandatoryOgField, mandatoryOGStartDateField);
        setRelated(nonMandatoryOgField, nonMandatoryStartDateField);
    }

    private void setRelated(BaseField<?> field, DateField startDateField) {
        ((EndDateField) field).setStartDateField(startDateField);
    }

    @Override
    protected String getInvalidFieldValue() {
        return INVALID_DATE.toString();
    }

    @NotNull
    @Override
    protected String getNewValidFieldValue() {
        return NEW_VALID_DATE.toString();
    }
}
