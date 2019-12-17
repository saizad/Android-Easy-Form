package com.sa.easyandroidfrom.fields;

abstract class BaseMandatoryFieldTest<F> extends BaseFieldTest<F> {

    public BaseMandatoryFieldTest(MandatoryField<F> mandatoryField, MandatoryField<F> nonEmptyMandatoryField) {
        super(mandatoryField, mandatoryField, nonEmptyMandatoryField, nonEmptyMandatoryField);
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