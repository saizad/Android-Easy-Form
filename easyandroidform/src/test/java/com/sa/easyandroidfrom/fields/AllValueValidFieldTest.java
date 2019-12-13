package com.sa.easyandroidfrom.fields;

public abstract class AllValueValidFieldTest<F> extends BaseFieldTest<F> {

    AllValueValidFieldTest(Field<F> field, Field<F> mandatoryField, Field<F> mandatoryOgField, Field<F> nonMandatoryOgField) {
        super(field, mandatoryField, mandatoryOgField, nonMandatoryOgField);
    }

    @Override
    protected final F getInValidFieldValue() {
        return null;
    }

    @Override
    final void field_is_in_valid() {

    }
}