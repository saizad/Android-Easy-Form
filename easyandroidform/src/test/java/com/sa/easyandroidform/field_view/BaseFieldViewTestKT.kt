package com.sa.easyandroidform.field_view

import com.sa.easyandroidform.StringUtils
import com.sa.easyandroidform.fields.Field
import com.sa.easyandroidform.fields.PhoneNumberField
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.concurrent.Callable

internal class BaseFieldViewTestKT {
    private val baseFieldView: BaseFieldView<String>
    @Test
    fun fieldMandatory__not_called() {
        val field =
            Field<String>("field")
        baseFieldView.setField(field)
        Mockito.verify(baseFieldView, Mockito.never())
            .fieldMandatory()
    }

    @Test
    fun fieldMandatory__called_1() {
        val field =
            Field<String>("field")
        field.setIsMandatory(true)
        baseFieldView.setField(field)
        Mockito.verify(baseFieldView, Mockito.times(1))
            .fieldMandatory()
    }

    @Test
    fun edited__not_called() {
        val field =
            Field<String>("field")
        baseFieldView.setField(field)
        Mockito.verify(baseFieldView, Mockito.never()).edited()
    }

    @Test
    fun edited__called_1() {
        val field =
            Field<String>("field")
        field.field = StringUtils.random(10)
        baseFieldView.setField(field)
        Mockito.verify(baseFieldView, Mockito.times(1)).edited()
    }

    @Test
    fun error_null_value__not_called() {
        val field = PhoneNumberField("field", true, 10)
        baseFieldView.setField(field)
        Mockito.verify(baseFieldView, Mockito.never()).error()
    }

    @Test
    fun error_null_value_valid__not_called() {
        val field = PhoneNumberField("field", 10)
        baseFieldView.setField(field)
        Mockito.verify(baseFieldView, Mockito.never()).error()
    }

    @Test
    fun error__not_called() {
        val field = PhoneNumberField("field", 10)
        field.field = StringUtils.random(10)
        baseFieldView.setField(field)
        Mockito.verify(baseFieldView, Mockito.never()).error()
    }

    @Test
    fun error__called_1() {
        val field = PhoneNumberField("field", 1)
        field.field = "12"
        baseFieldView.setField(field)
        Mockito.verify(baseFieldView, Mockito.times(1)).error()
    }

    @Test
    fun neutral__default_called() {
        val field =
            Field<String>("field")
        baseFieldView.setField(field)
        Mockito.verify(baseFieldView, Mockito.times(1)).neutral()
    }

    @Test
    fun neutral__modified_called() {
        val field =
            Field<String>("field")
        field.field = "dasfasd"
        baseFieldView.setField(field)
        Mockito.verify(baseFieldView, Mockito.times(1)).neutral()
    }

    @Test
    fun validate_calls__init() {
        val field = PhoneNumberField("field", 1)
        baseFieldView.setField(field)
    }

    @Test
    fun validate_calls__init_invalid() {
        val field = PhoneNumberField("field", 1)
    }

    @Test
    fun validate_calls__init_valid() {
        val field = PhoneNumberField("field", 1)
    }

    private fun verify(
        error: Boolean,
        neutral: Boolean,
        edited: Boolean
    ) {
        Mockito.verify(baseFieldView, Mockito.times(1))
            .fieldMandatory()
        Mockito.verify(
            baseFieldView,
            Mockito.times(booleanToBit(error))
        ).error()
        Mockito.verify(
            baseFieldView,
            Mockito.times(booleanToBit(neutral))
        ).neutral()
        Mockito.verify(
            baseFieldView,
            Mockito.times(booleanToBit(edited))
        ).edited()
    }

    companion object {
        private fun booleanToBit(bool: Boolean): Int {
            return if (bool) {
                1
            } else 0
        }
    }

    init {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> Schedulers.trampoline() }
        baseFieldView = Mockito.mock<BaseFieldView<*>>(BaseFieldView::class.java)
    }
}