package com.sa.easyandroidform.form;

import androidx.annotation.NonNull;

import com.sa.easyandroidform.Func1;
import com.sa.easyandroidform.fields.BaseField;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.exceptions.CompositeException;

public class ListForm<T> extends FormModel<List<T>> {


    public ListForm(String formName, List<T> bodies, Func1<Integer, FormModel<T>> func1) {
        super(formName, new ArrayList<>(forms(bodies, func1)));
    }

    private static <T> List<FormModel<T>> forms(List<T> bodies, Func1<Integer, FormModel<T>> func1) {
        List<FormModel<T>> list = new ArrayList<>();
        for (int i = 0; i < bodies.size(); i++) {
            list.add(func1.call(i));
        }
        return list;
    }


    @NonNull
    @Override
    protected List<T> buildForm() {
        List<T> list = new ArrayList<>();
        for (BaseField field : fields) {
            final FormModel<T> form = (FormModel<T>) field;
            final T build = form.requiredBuild();
            list.add(build);
        }
        return list;
    }

    @Override
    public void validate() throws CompositeException {
        super.validate();
        if (isMandatory() && fields.isEmpty()) {
            throw new CompositeException(new Exception("Mandatory list form filed is empty"));
        }
    }
}
