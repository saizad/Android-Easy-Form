package com.sa.easyandroidfrom.form.test

import com.sa.easyandroidfrom.fields.BaseField
import com.sa.easyandroidfrom.fields.MandatoryStringField
import com.sa.easyandroidfrom.fields.StringField
import com.sa.easyandroidfrom.form.FormModel
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
    internal fun allFieldsValid_True() {

        assertTrue(form.isAllFieldsValid, "allFieldValidMandatoryForm should be set all field with valid value and be mandatory")
    }

    @Test
    internal fun buildFormFail() {
        val formField = form.fields.firstOrNull()!!
        formField.field =
            invalidValue(formField)
        val build = form.build()
        assertNull(build)
    }

    @Test
    internal fun buildFormSuccess() {
        val build = form.build()
        assertNotNull(build)
    }

    @Test
    internal fun requiredBuild_Fail() {
        val formField = form.fields.firstOrNull()!!
        formField.field =
            invalidValue(formField)
        assertThrows(IllegalStateException::class.java) {
            form.requiredBuild()
        }
    }

    @Test
    internal fun requiredBuild_Success() {
        assertDoesNotThrow {
            form.requiredBuild()
        }
    }

    @Test
    internal fun errorCount_mandatory() {
        val mandatory = form.fields.filter { it.isMandatory }
        mandatory.forEach {
            it.field = invalidValue(it)
        }
        val errors = form.errors()
        assertEquals(errors.size, mandatory.size)
    }

    @Test
    internal fun errorCount_non_mandatory() {
        val nonMandatory = form.fields.filter { !it.isMandatory }
        nonMandatory.forEach {
            it.field = invalidValue(it)
        }
        val errors = form.errors()
        assertEquals(errors.size, 0)
    }

    @Test
    internal fun addFieldCountMatch() {
        val prevSize = form.fields.size
        form.add(addField())
        val newSize = form.fields.size
        assertEquals(prevSize, newSize - 1)
    }

    @Test
    internal fun addFieldValidForm() {
        form.add(addField())
        assertTrue(form.isFormValid)
    }

    @Test
    internal fun addFieldInValidForm() {
        form.add(mandatoryAddField())
        assertFalse(form.isFormValid)
    }

    @Test
    internal fun getField_Failed() {
        form.add(mandatoryAddField())
        assertNull(form.getField<BaseField<*>>("akaldsj"))
    }

    @Test
    internal fun getField_Success() {
        form.add(mandatoryAddField())
        assertNotNull(form.getField<BaseField<*>>("temp"))
    }

    @Test
    internal fun requiredField_Failed() {
        form.add(mandatoryAddField())
        assertThrows(IllegalStateException::class.java) {
            form.requiredField<BaseField<*>>("akaldsj")
        }
    }

    @Test
    internal fun requiredField_Success() {
        form.add(mandatoryAddField())
        assertDoesNotThrow {
            form.requiredField<BaseField<*>>("temp")
        }
    }

    @Test
    open fun isSet_False(){
        form.fields.forEach {
            it.field = null
        }
        assertFalse(form.isSet)
    }

    @Test
    internal fun isSet_True(){
        assertTrue(form.isSet)
    }
}