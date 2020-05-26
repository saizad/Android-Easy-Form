package com.sa.easyandroidform.field_view;

import android.widget.EditText;

import com.sa.easyandroidform.fields.NonZeroIntField;

import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class IntegerInputFieldViewTest extends BaseFieldViewTestKT<Integer, InputFieldView> {

    private static final String FIELD_NAME = "random";
    private static final Integer VALUE = 1;
    final EditText editText = Mockito.mock(EditText.class);

    public IntegerInputFieldViewTest() {
        super(InputFieldView.class, new NonZeroIntField(FIELD_NAME), new NonZeroIntField(FIELD_NAME, true), new NonZeroIntField(FIELD_NAME, VALUE, true), new NonZeroIntField(FIELD_NAME, VALUE));
        Mockito.when(getBaseFieldView().getEditText()).thenReturn(editText);
    }

    @Nullable
    @Override
    public Integer invalidValue() {
        return 0;
    }

    @Nullable
    @Override
    public Integer validValue() {
        return 1;
    }

    @Test
    public void resolve__not_called(){
        Mockito.when(editText.isFocused()).thenReturn(true);
        getBaseFieldView().setField(getField());
        Mockito.verify(getBaseFieldView(), Mockito.never()).resolve(Mockito.any());
    }

    @Test
    public void resolve__called(){
        getBaseFieldView().setField(getField());
        Mockito.verify(getBaseFieldView(), Mockito.times(1)).resolve(Mockito.any());
    }

    @Test
    public void getEditText__not_called(){
        Mockito.verify(getBaseFieldView(), Mockito.never()).getEditText();
    }

    @Test
    public void fieldMandatory__not_called() {
//        System.out.println(getBaseFieldView().getEditText());
//        System.out.println(getBaseFieldView().getEditText());
//        System.out.println(getBaseFieldView().getEditText());
//        System.out.println(getBaseFieldView().getEditText());
        final InputFieldView mock = Mockito.mock(InputFieldView.class);
        Mockito.when(mock.getEditText()).thenReturn(editText);
        getBaseFieldView().setField(getField());
        Mockito.verify(mock, Mockito.times(1))
                .showValue(getField().getField());
//        verify(1,1,false);
    }
}
