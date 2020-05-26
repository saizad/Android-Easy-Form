package com.sa.easyandroidform.field_view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.EditText
import com.jakewharton.rxbinding2.widget.RxTextView
import com.sa.easyandroidform.ObjectUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

abstract class BaseInputFieldView<F> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    BaseFieldView<F>(
        context,
        attrs,
        defStyleAttr
    ) {

    private lateinit var disposable: Disposable
    private lateinit var editText: EditText

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (!isInEditMode) {
            editText = getEditText()
            disposable = RxTextView.textChanges(getEditText())
                .skipInitialValue()
                .debounce(100, TimeUnit.MILLISECONDS)
                .filter { ObjectUtils.coalesce(fieldItem.field, "") != it.toString() }
                .filter { !compareValue(resolveFrom(it), fieldItem.field) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { s ->
                    Log.d("fcm", s.toString())
                    updateValue(resolveFrom(s))
                }
        }
    }

    override fun onDetachedFromWindow() {
        disposable.dispose()
        super.onDetachedFromWindow()
    }

    final override fun showValue(value: F?) {
        val editText = getEditText()
        if (!editText.isFocused) {
            editText.setText(resolve(value))
        }
    }

    private fun compareValue(value: F?, prevValue: F?): Boolean {
        if (ObjectUtils.isNull(value) && ObjectUtils.isNull(prevValue)) {
            return true
        } else if (ObjectUtils.isNotNull(value) && ObjectUtils.isNull(prevValue)) {
            return false
        } else if (value != null && prevValue != null) {
            return isSame(value, prevValue)
        }
        return false
    }

    abstract fun getEditText(): EditText
    abstract fun resolveFrom(charSequence: CharSequence): F
    abstract fun resolve(value: F?): CharSequence?
    abstract fun isSame(value: F, prevValue: F): Boolean
}
