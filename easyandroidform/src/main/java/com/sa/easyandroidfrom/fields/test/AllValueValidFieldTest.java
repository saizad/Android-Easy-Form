package com.sa.easyandroidfrom.fields.test;

import com.sa.easyandroidfrom.fields.BaseField;

public abstract class AllValueValidFieldTest<F> extends BaseFieldTest<F> {

    public AllValueValidFieldTest(BaseField<F> field, BaseField<F> mandatoryField, BaseField<F> mandatoryOgField, BaseField<F> nonMandatoryOgField) {
        super(field, mandatoryField, mandatoryOgField, nonMandatoryOgField);
    }

    @Override
    protected final F getInValidFieldValue() {
        return null;
    }

    @Override
    final void isFieldValid_false() {

    }
}