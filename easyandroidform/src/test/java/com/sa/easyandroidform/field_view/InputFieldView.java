package com.sa.easyandroidform.field_view;

import android.content.Context;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InputFieldView extends BaseInputFieldView<Integer> {

    public InputFieldView(@NotNull Context context) {
        super(context);
    }

    @NotNull
    @Override
    public EditText getEditText() {
        return null;
    }

    @Override
    public Integer resolveFrom(@NotNull CharSequence charSequence) {
        return Integer.valueOf(charSequence.toString());
    }

    @Override
    public CharSequence resolve(@Nullable Integer value) {
        if (value != null)
            return String.valueOf(value);
        return null;
    }

    @Override
    public boolean isSame(Integer value, Integer prevValue) {
        return value.equals(prevValue);
    }

    @Override
    public void fieldMandatory() { }

    @Override
    public void displayError(boolean show, @Nullable String error) { }
}
