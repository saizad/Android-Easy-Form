package com.sa.easyandroidform.example;

import android.content.Context;
import android.util.AttributeSet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class IntegerInputFieldView extends InputFieldView<Integer> {

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
    public void notSetError(@NotNull String error) {

    }

    @Override
    public boolean isSame(Integer value, Integer prevValue) {
        return value.equals(prevValue);
    }
}
