package com.sa.easyandroidfrom.fields;

abstract class BaseNonEmptyMandatoryStringFieldTest extends BaseFieldTest<String> {

    public BaseNonEmptyMandatoryStringFieldTest(NonEmptyMandatoryStringField mandatoryField, NonEmptyMandatoryStringField nonEmptyMandatoryField) {
        super(mandatoryField, mandatoryField, nonEmptyMandatoryField, nonEmptyMandatoryField);
    }

    @Override
    protected String getInValidFieldValue() {
        return "";
    }

    @Override
    final void isMandatory_false() {

    }

    @Override
    final void invalidObservable_not_called() {

    }

    @Override
    final void errorState_true() {

    }
}