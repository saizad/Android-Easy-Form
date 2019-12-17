package com.sa.easyandroidfrom.fields;

abstract class BaseMandatoryStringFieldTest extends BaseMandatoryFieldTest<String> {

    public BaseMandatoryStringFieldTest(MandatoryStringField mandatoryField, MandatoryStringField nonEmptyMandatoryField) {
        super(mandatoryField, nonEmptyMandatoryField);
    }

    @Override
    protected String getInValidFieldValue() {
        return "";
    }

}