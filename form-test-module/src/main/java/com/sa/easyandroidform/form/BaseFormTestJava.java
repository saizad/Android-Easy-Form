package com.sa.easyandroidform.form;

import androidx.annotation.Nullable;

import com.sa.easyandroidform.ErrorField;
import com.sa.easyandroidform.fields.BaseField;
import com.sa.easyandroidform.fields.MandatoryStringField;
import com.sa.easyandroidform.fields.StringField;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


abstract public class BaseFormTestJava<F extends FormModel<?>> {

    protected F form;

    @BeforeEach
    void setUp() {
        form = initForm();
    }

    abstract F initForm();

    @Nullable
    Object invalidValue(BaseField<?> baseField) {
        return null;
    }

    BaseField<?> addField() {
        return new StringField("temp");
    }

    BaseField<?> mandatoryAddField() {
        return new MandatoryStringField("temp");
    }

    @Test
    void buildFormFail() {
        BaseField formField = form.fields.get(0);
        formField.setField(invalidValue(formField));
        final Object build = form.build();
        assertNull(build);
    }

    @Test
    public void buildFormSuccess() {
        Object build = form.build();
        Assertions.assertNotNull(build);
    }

    @Test
    public void requiredBuild_Fail() {
        BaseField formField = form.fields.get(0);
        formField.setField(this.invalidValue(formField));
        assertThrows(IllegalStateException.class, () -> form.requiredBuild());
    }

    @Test
    public void requiredBuild_Success() {
        Assertions.assertDoesNotThrow(() -> form.requiredBuild());
    }

    @Test
    void isSet_True() {
        assertTrue(form.isSet());
    }

    @Test
    void requiredField__throws() {
        assertThrows(UnsupportedOperationException.class, form::requiredField);
    }

    @Test
    void getField__throws() {
        assertThrows(UnsupportedOperationException.class, form::getField);
    }

    @Test
    void isFormModified__false() {
        BaseField formField = form.fields.get(0);
        formField.setField(invalidValue(formField));
        assertFalse(form.isFormModified());
    }

    @Test
    void isFormModified__true() {
        assertTrue(form.isFormModified());
    }

    @Test
    void isAllFieldsValid__false() {
        BaseField formField = form.fields.get(0);
        formField.setField(invalidValue(formField));
        assertFalse(form.isAllFieldsValid());
    }

    @Test
    void isAllFieldsValid__true() {
        assertTrue(form.isAllFieldsValid());
    }

    @Test
    void isAllMandatoryFieldsProvided__false() {
        BaseField formField = form.fields.get(0);
        formField.setField(invalidValue(formField));
        assertFalse(form.isAllMandatoryFieldsProvided());
    }

    @Test
    void isAllMandatoryFieldsProvided__true() {
        assertTrue(form.isAllMandatoryFieldsProvided());
    }

    @Test
    void addFieldValidForm() {
        form.add(addField());
        assertTrue(form.isFormValid());
    }

    @Test
    void addFieldInValidForm() {
        form.add(mandatoryAddField());
        assertFalse(form.isFormValid());
    }

    @Test
    void getField_Failed() {
        form.add(mandatoryAddField());
        assertNull(form.getField("akaldsj"));
    }

    @Test
    void getField_Success() {
        form.add(mandatoryAddField());
        assertNotNull(form.getField("temp"));
    }

    @Test
    void requiredField_Failed() {
        form.add(mandatoryAddField());
        assertThrows(IllegalStateException.class,() -> form.requiredField("dasfaf"));
    }

    @Test
    void requiredField_Success() {
        form.add(mandatoryAddField());
        assertDoesNotThrow(() -> form.requiredField("dasfaf"));
    }

    @Test
    void isSet_False() {
        for (BaseField field : form.fields) {
            field.setField(null);
        }
        assertFalse(form.isSet());
    }

    @Test
    void errorCount_mandatory() {

        for (BaseField field : form.fields) {
            field.setField(invalidValue(field));
        }
        int mandatorySize = 1;
        final List<ErrorField> errors = form.errors();
        assertEquals(errors.size(), mandatorySize);
    }

    @Test
    void errorCount_non_mandatory() {
        for (BaseField field : form.fields) {
            field.setField(invalidValue(field));
        }
        final List<ErrorField> errors = form.errors();
        assertEquals(errors.size(), 0);
    }

    @Test
    void addFieldCountMatch() {
        int prevSize = form.fields.size();
        form.add(addField());
        int newSize = form.fields.size();
        assertEquals(prevSize, newSize - 1);
    }
}
