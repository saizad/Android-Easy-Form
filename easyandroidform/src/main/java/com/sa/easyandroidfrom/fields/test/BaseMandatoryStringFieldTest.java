package com.sa.easyandroidfrom.fields.test;

import com.sa.easyandroidfrom.fields.MandatoryStringField;

public abstract class BaseMandatoryStringFieldTest extends BaseMandatoryFieldTest<String> {

    public BaseMandatoryStringFieldTest(MandatoryStringField mandatoryField, MandatoryStringField nonEmptyMandatoryField) {
        super(mandatoryField, nonEmptyMandatoryField);
    }

    @Override
    protected String getInValidFieldValue() {
        return "";
    }

}