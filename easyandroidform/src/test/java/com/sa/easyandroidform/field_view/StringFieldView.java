package com.sa.easyandroidform.field_view;

import android.content.Context;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StringFieldView extends BaseFieldView<String> {

    public StringFieldView(@NotNull Context context) {
        super(context);
    }

    @Override
    public void fieldMandatory() {

    }

    @Override
    public void showValue(@Nullable String field) {

    }

    @Override
    public void displayError(boolean show, @Nullable String error) {

    }
}
