package com.sa.easyandroidform.form

import com.sa.easyandroidform.fields.BaseField
import com.sa.easyandroidform.fields.MandatoryStringField
import com.sa.easyandroidform.fields.StringField
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

abstract class BaseFormTest<F : FormModel<*>> {

    lateinit var form: F

    @BeforeEach
    fun setUp() {
        form = initForm()
    }

    abstract fun initForm(): F

    open fun invalidValue(field: BaseField<*>): Any? = null

    open fun addField(): BaseField<*> = StringField("temp")

    open fun mandatoryAddField(): BaseField<*> = MandatoryStringField("temp")

    @Test
    open fun buildFormFail() {
        val formField = form.fields.firstOrNull()!!
        formField.field =
            invalidValue(formField)
        val build = form.build()
        assertNull(build)
    }

    @Test
    open fun buildFormSuccess() {
        val build = form.build()
        assertNotNull(build)
    }

    @Test
    open fun requiredBuild_Fail() {
        val formField = form.fields.firstOrNull()!!
        formField.field = invalidValue(formField)
        assertThrows(IllegalStateException::class.java) {
            form.requiredBuild()
        }
    }

    @Test
    open fun requiredBuild_Success() {
        assertDoesNotThrow {
            form.requiredBuild()
        }
    }

    @Test
    open fun errorCount_mandatory() {
        val mandatory = form.fields.filter { it.isMandatory }
        mandatory.forEach {
            it.field = invalidValue(it)
        }
        val errors = form.errors()
        assertEquals(errors.size, mandatory.size)
    }

    @Test
    open fun errorCount_non_mandatory() {
        val nonMandatory = form.fields.filter { !it.isMandatory }
        nonMandatory.forEach {
            it.field = invalidValue(it)
        }
        val errors = form.errors()
        assertEquals(errors.size, 0)
    }

    @Test
    open fun addFieldCountMatch() {
        val prevSize = form.fields.size
        form.add(addField())
        val newSize = form.fields.size
        assertEquals(prevSize, newSize - 1)
    }

    @Test
    open fun addFieldValidForm() {
        form.add(addField())
        assertTrue(form.isFormValid)
    }

    @Test
    open fun addFieldInValidForm() {
        form.add(mandatoryAddField())
        assertFalse(form.isFormValid)
    }

    @Test
    open fun getField_Failed() {
        form.add(mandatoryAddField())
        assertNull(form.getField<BaseField<*>>("akaldsj"))
    }

    @Test
    open fun getField_Success() {
        form.add(mandatoryAddField())
        assertNotNull(form.getField<BaseField<*>>("temp"))
    }

    @Test
    open fun requiredField_Failed() {
        form.add(mandatoryAddField())
        assertThrows(IllegalStateException::class.java) {
            form.requiredField<BaseField<*>>("akaldsj")
        }
    }

    @Test
    open fun requiredField_Success() {
        form.add(mandatoryAddField())
        assertDoesNotThrow {
            form.requiredField<BaseField<*>>("temp")
        }
    }

    @Test
    open fun isSet_False() {
        form.fields.forEach {
            it.field = null
        }
        assertFalse(form.isSet)
    }

    @Test
    open fun isSet_True() {
        assertTrue(form.isSet)
    }

    @Test
    open fun requiredField__throws() {
        assertThrows(UnsupportedOperationException::class.java) {
            form.requiredField()
        }
    }

    @Test
    open fun getField__throws() {
        assertThrows(UnsupportedOperationException::class.java) {
            form.getField()
        }
    }

    @Test
    open fun isFormModified__false() {
        val formField = form.fields.random()
        formField.field = invalidValue(formField)
        assertFalse(form.isFormModified)
    }

    @Test
    open fun isFormModified__true() {
        assertTrue(form.isFormModified)
    }

    @Test
    open fun isAllFieldsValid__false() {
        val formField = form.fields.random()
        formField.field = invalidValue(formField)
        assertFalse(form.isAllFieldsValid)
    }

    @Test
    open fun isAllFieldsValid__true() {
        assertTrue(form.isAllFieldsValid)
    }

    @Test
    open fun isAllMandatoryFieldsProvided__false() {
        val formField = form.fields.filter { it.isMandatory }.random()
        formField.field = invalidValue(formField)
        assertFalse(form.isAllMandatoryFieldsProvided)
    }

    @Test
    open fun isAllMandatoryFieldsProvided__true() {
        assertTrue(form.isAllMandatoryFieldsProvided)
    }
}