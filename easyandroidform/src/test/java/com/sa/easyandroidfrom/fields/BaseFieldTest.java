package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;

import com.sa.easyandroidfrom.ObservablesCall;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class BaseFieldTest<F> {


    protected final BaseField<F> field;

    protected final BaseField<F> mandatoryField;

    protected final BaseField<F> mandatoryOgField;

    protected final BaseField<F> nonMandatoryOgField;

    protected static final String NETWORK_ERROR = "error";

    protected abstract @NonNull
    F getNewValidFieldValue();

    protected abstract F getInValidFieldValue();

    protected abstract @NonNull
    String fieldName();

    BaseFieldTest(BaseField<F> field, BaseField<F> mandatoryField, BaseField<F> mandatoryOgField, BaseField<F> nonMandatoryOgField) {
        this.field = Mockito.spy(field);
        this.mandatoryField = Mockito.spy(mandatoryField);
        this.mandatoryOgField = Mockito.spy(mandatoryOgField);
        this.nonMandatoryOgField = Mockito.spy(nonMandatoryOgField);
    }

    @Test
    void fieldid_same() {
        assertEquals(fieldName(), field.getFieldId());
    }

    @Test
    void isMandatory_false() {
        assertFalse(field.isMandatory());
    }

    @Test
    void isMandatory_true() {
        assertTrue(mandatoryField.isMandatory());
    }

    @Test
    void ogfield_same() {
        assertEquals(nonMandatoryOgField.getOgField(), nonMandatoryOgField.getOgField());
    }

    @Test
    void ogfield_not_same() {
        assertNotEquals(getNewValidFieldValue(), mandatoryOgField.getOgField());
    }

    @Test
    void isModified_false() {
        assertFalse(field.isModified());
    }

    @Test
    void isModified_true() {
        field.setField(getNewValidFieldValue());
        assertTrue(field.isModified());
    }

    @Test
    void field_value_not_changed__with_og_field() {
        assertFalse(nonMandatoryOgField.hasValueChanged());
    }

    @Test
    void field_hasValueChanged() {
        field.setField(getNewValidFieldValue());
        assertTrue(field.hasValueChanged());
    }

    @Test
    void field_throw_validation() throws Exception {
        Mockito.doThrow(new Exception("Testing is field valid by throwing validate"))
                .when(field).validate();
        field.setField(getNewValidFieldValue());
        assertFalse(field.isFieldValid());
    }

    @Test
    void mandatory_field_is_in_valid() {
        mandatoryField.setField(getInValidFieldValue());
        assertFalse(mandatoryField.isFieldValid());
    }

    @Test
    void isFieldValid_false() {
        field.setField(getInValidFieldValue());
        assertFalse(field.isFieldValid());
    }

    @Test
    void isFieldValid_true() {
        field.setField(getNewValidFieldValue());
        assertTrue(field.isFieldValid());
    }

    @Test
    void validate_called_once() throws Exception {
        field.setField(getNewValidFieldValue());
        Mockito.verify(field, Mockito.times(1)).validate();
    }

    @Test
    void null_filed_with_mandatory_field() {
        field.setIsMandatory(true);
        ObservablesCall.isFieldValid(field);
    }

    @Test
    void observable_called() {
        ObservablesCall.observable(field, 1);
    }

    @Test
    void setObservable_called() {
        field.setField(getNewValidFieldValue());
        ObservablesCall.setObservable(field, 1);
    }

    @Test
    void setObservable_not_called() {
        ObservablesCall.setObservable(field, 0);
    }

    @Test
    void validObservable_true() {
        field.setField(getNewValidFieldValue());
        ObservablesCall.validObservable(field, true);
    }

    @Test
    void validObservable_false() {
        field.setIsMandatory(true);
        ObservablesCall.validObservable(field, false);
    }

    @Test
    void invalidObservable_called() {
        field.setIsMandatory(true);
        ObservablesCall.invalidObservable(field, 1);
    }

    @Test
    void invalidObservable_not_called() {
        ObservablesCall.invalidObservable(field, 0);
    }

    @Test
    void nonEmptyInvalidObservable_not_called() {
        field.setIsMandatory(true);
        ObservablesCall.nonEmptyInvalidObservable(field, 0);
    }

    @Test
    void notEmptyValidObservable_called() {
        field.setField(getNewValidFieldValue());
        ObservablesCall.notEmptyValidObservable(field, 1);
    }

    @Test
    void notEmptyValidObservable_not_called() {
        field.setIsMandatory(true);
        ObservablesCall.notEmptyValidObservable(field, 0);
    }

    @Test
    void fieldUnsetObservable_called() {
        ObservablesCall.fieldUnsetObservable(field, 1);
    }

    @Test
    void fieldUnsetObservable_not_called() {
        field.setField(getNewValidFieldValue());
        ObservablesCall.fieldUnsetObservable(field, 0);
    }

    @Test
    void networkError_called() {
        field.networkErrorPublish(NETWORK_ERROR);
        ObservablesCall.networkError(field, 1);
    }

    @Test
    void networkError_not_called() {
        field.networkErrorPublish(NETWORK_ERROR);
        field.setField(getNewValidFieldValue());
        ObservablesCall.networkError(field, 0);
    }

    @Test
    void isValueModifiedObservable_true() {
        field.setField(getNewValidFieldValue());
        ObservablesCall.isValueModifiedObservable(field, true);
    }

    @Test
    void isValueModifiedObservable_false() {
        ObservablesCall.isValueModifiedObservable(field, false);
    }

    @Test
    void isModifiedObservable_true() {
        nonMandatoryOgField.setField(getNewValidFieldValue());
        ObservablesCall.isModifiedObservable(nonMandatoryOgField, true);
    }

    @Test
    void isModifiedObservable_false() {
        ObservablesCall.isModifiedObservable(nonMandatoryOgField, false);
    }

    @Test
    void errorState_true() {
        ObservablesCall.errorState(field, true);
    }

    @Test
    void errorState_false() {
        field.setIsMandatory(true);
        ObservablesCall.errorState(field, false);
    }
}