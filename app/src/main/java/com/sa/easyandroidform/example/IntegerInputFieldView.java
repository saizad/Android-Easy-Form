package com.sa.easyandroidform.example;

import android.content.Context;
import android.util.AttributeSet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class IntegerInputFieldView extends InputFieldView<String> {

    public IntegerInputFieldView(@NotNull Context context) {
        super(context);
    }

    public IntegerInputFieldView(@NotNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IntegerInputFieldView(@NotNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public String resolveTo(@NotNull CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public CharSequence setText(@Nullable String value) {
        return value;
    }

    @Override
    public boolean compare(String value, String prevValue) {
        return value.equals(prevValue);
    }
}
