package com.sa.easyandroidform.example

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import com.sa.easyandroidform.field_view.BaseInputFieldView
import kotlinx.android.synthetic.main.input_field_view.view.*


abstract class InputFieldView<F> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) :
    BaseInputFieldView<F>(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.input_field_view, this)
        val handler = Handler()
        handler.postDelayed( {
            editTextLayout.isEndIconVisible = false
        }, 1000)
        editText.setOnFocusChangeListener { v, hasFocus ->
            editTextLayout.isEndIconVisible = hasFocus
        }
    }


    final override fun getEditText(): EditText {
        return editText
    }

    override fun fieldMandatory() {
    }

    override fun displayError(show: Boolean, error: String?) {
        editTextLayout.error = error
        editTextLayout.isErrorEnabled = show
    }
}
