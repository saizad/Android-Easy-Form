package com.sa.easyandroidform.field_view

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.times
import com.sa.easyandroidform.fields.BaseField
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
        field: BaseField<V> = this.field,
        invocations: Int = 1, isError: Boolean = false
    ) {
        Mockito
            .verify(baseFieldView, Mockito.times(booleanToBit(baseFieldView.fieldItem.isMandatory)))
            .fieldMandatory()

        Mockito
            .verify(baseFieldView, Mockito.times(invocations))
            .showValue(field.field)

        if(field.isSet) {
            Mockito
                .verify(baseFieldView, Mockito.times(invocations))
                .displayError(eq(isError), Mockito.any())
        }else if(!field.isSet && isError){
//            Mockito
//                .verify(baseFieldView, Mockito.times(showsValueNumberOfInvocations)).notSetError("")
        }
    }

    @Test
    open fun fieldMandatory__not_called() {
        baseFieldView.setField(field)
        verify()
    }

    @Test
    fun fieldMandatory__called_1() {
        baseFieldView.setField(mandatoryOgField)
        verify(mandatoryOgField)
    }

    @Test
    fun change_field__invalid() {
        baseFieldView.setField(mandatoryField)
        mandatoryField.field = null
        verify(mandatoryField, isError = true, invocations = 2)
    }

    @Test
    fun change_field__valid() {
        baseFieldView.setField(mandatoryOgField)
        val validValue = validValue()
        mandatoryOgField.field = validValue
        Mockito.verify(baseFieldView, times(1)).showValue(validValue)
        Mockito.verify(baseFieldView, times(2)).displayError(eq(false), Mockito.any())
    }

    @Test
    fun showValue__init() {
        baseFieldView.setField(field)
        verify()
    }

    @Test
    fun showValue__change_field_invalid() {
        baseFieldView.setField(mandatoryField)
        mandatoryField.field = null
        Mockito.verify(baseFieldView, times(2)).showValue(null)
        Mockito.verify(baseFieldView, times(1)).displayError(eq(true), Mockito.any())
    }

}