package com.sa.easyandroidform.field_view

import com.nhaarman.mockitokotlin2.eq
import com.sa.easyandroidform.fields.BaseField
import com.sa.easyandroidform.fields.Field
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.Test
import org.mockito.Mockito

open abstract class BaseFieldViewTestKT<V, T : BaseFieldView<V>>(
    mockClass: Class<T>,
    val field: BaseField<V>,
    val mandatoryField: BaseField<V>,
    val mandatoryOgField: BaseField<V>,
    val nonMandatoryOgField: BaseField<V>
) {

    abstract fun invalidValue(): V?
    abstract fun validValue(): V?

    protected val baseFieldView: T

    companion object {
        private fun booleanToBit(bool: Boolean): Int {
            return if (bool) {
                1
            } else 0
        }
    }

    init {
        baseFieldView = Mockito.mock<T>(mockClass)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    protected fun verify(
        showsValueNumberOfInvocations: Int = 1,
        displayErrorsNumberOfInvocations: Int = 1, isError: Boolean = false
    ) {
        Mockito
            .verify(baseFieldView, Mockito.times(booleanToBit(baseFieldView.fieldItem.isMandatory)))
            .fieldMandatory()

        Mockito
            .verify(baseFieldView, Mockito.times(showsValueNumberOfInvocations))
            .showValue(field.field)

        Mockito
            .verify(baseFieldView, Mockito.times(displayErrorsNumberOfInvocations))
            .displayError(eq(isError), Mockito.any())
    }

    private fun setField(field: BaseField<V>, isValid: Boolean = true) {
        if (isValid) {
            field.field = validValue()
        } else {
            field.field = invalidValue()
        }
        verify(showsValueNumberOfInvocations = 2, isError = !isValid)
    }

    @Test
    open fun fieldMandatory__not_called() {
        baseFieldView.setField(field)
        verify()
    }

    @Test
    fun fieldMandatory__called_1() {
        baseFieldView.setField(mandatoryOgField)
        verify()
    }

    @Test
    fun validate_calls__init_invalid() {
        val field = Field("init", invalidValue(), true)
        baseFieldView.setField(field)
        verify(isError = true)
    }

    @Test
    fun validate_calls__init_valid() {
        val field = Field("init", validValue())
        baseFieldView.setField(field)
        verify(showsValueNumberOfInvocations = 1)
    }

    @Test
    fun validate_calls__change_field_invalid() {
        baseFieldView.setField(field)
        setField(field, false)
    }

    @Test
    fun validate_calls__change_field_valid() {
        baseFieldView.setField(field)
        setField(field)
    }

    @Test
    fun showValue__init() {
        baseFieldView.setField(field)
        verify()
    }

    @Test
    fun showValue__change_field_invalid() {
        baseFieldView.setField(field)
        setField(field, false)
    }

    @Test
    fun showValue__change_field_invalid_multiple() {
        baseFieldView.setField(field)
        field.field = invalidValue()
        field.field = invalidValue()
        verify(isError = true, showsValueNumberOfInvocations = 2)
    }

    @Test
    fun showValue__change_field_valid() {
        baseFieldView.setField(field)
        field.field = validValue()
        verify(isError = false)
    }


}