package com.sa.easyandroidform.form;

import android.annotation.SuppressLint;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidform.ErrorField;
import com.sa.easyandroidform.fields.BaseField;
import com.sa.easyandroidform.fields.Field;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.exceptions.CompositeException;

public abstract class FormModel<T> extends Field<T> {

    public final transient List<BaseField> fields;
    private final transient Observable<Object> allFieldObservable;

    public FormModel(ArrayList<BaseField> fields) {
        this("form", fields);
    }

    public FormModel(@NonNull String fieldId, ArrayList<BaseField> fields) {
        this(fieldId, false, fields);
    }

    public FormModel(String fieldId, boolean isMandatory, ArrayList<BaseField> fields) {
        super(fieldId, isMandatory);
        this.fields = fields;
        List<Observable<Object>> list = new ArrayList<>();
        for (BaseField field : fields) {
            list.add(field.observable());
        }
        allFieldObservable = Observable.merge(list);
        super.setField(null);
    }

    public void add(BaseField field) {
        fields.add(field);
        allFieldObservable.mergeWith(field.observable());
    }

    @NonNull
    public final <F extends BaseField> F requiredField(String fieldName) {
        for (BaseField field : fields) {
            if (field.getFieldId().equalsIgnoreCase(fieldName)) {
                return (F) field;
            }
        }
        throw new IllegalStateException(fieldName + " form field not found");
    }

    @Nullable
    public final <F extends BaseField> F getField(String fieldName) {
        try {
            return requiredField(fieldName);
        } catch (Exception e) {
            return null;
        }
    }

    public final void publish() {
        super.publish();
        for (BaseField field : fields) {
            field.publish();
        }
    }

    @Override
    protected boolean isFieldValueModified(@NonNull T field, @NonNull T ogField) {
        return isFormModified();
    }

    @CallSuper
    @Override
    public void validate() throws CompositeException {
        super.validate();
        List<CompositeException> exceptions = new ArrayList<>();
        for (BaseField field : fields) {
            try {
                field.validate();
            } catch (CompositeException e) {
                exceptions.add(e);
            }
        }
        if (!exceptions.isEmpty()) {
            throw new CompositeException(exceptions);
        }
    }

    public final boolean isFormModified() {
        for (BaseField field : fields) {
            if (field.isModified()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isAllFieldsValid() {
        for (BaseField field : fields) {
            if (!field.isFieldValid()) {
                return false;
            }
        }
        return true;
    }

    public final boolean isAllMandatoryFieldsProvided() {
        for (BaseField field : fields) {
            if (field.isMandatory() && !field.isFieldValid()) {
                return false;
            }
        }
        return true;
    }

    public final boolean isFormValid() {
        for (BaseField field : fields) {
            if (!field.isFieldValid()) {
                return false;
            }
        }
        return true;
    }

    public int getMissingMandatoryCount() {
        int count = 0;
        for (BaseField field : fields) {
            if (field.isMandatory() && !field.isFieldValid()) {
                count += 1;
            }
        }
        return count;
    }

    public int getMandatoryCompletedCount() {
        int count = 0;
        for (BaseField field : fields) {
            if (field.isMandatory() && field.isFieldValid()) {
                count += 1;
            }
        }
        return count;
    }

    public final void clearForm() {
        for (BaseField field : fields) {
            field.setField(null);
        }
    }

    public final Observable<Boolean> formModified() {
        return allFieldObservable.map(o -> isModified());
    }

    public final Observable<Boolean> formModifiedValid() {
        return allFieldObservable.map(o -> isModified() && isAllFieldsValid());
    }

    public final Observable<Boolean> isAllFieldValidObservable() {
        return allFieldObservable.map(o -> isAllFieldsValid());
    }

    public final Observable<Boolean> validFormObservable() {
        return isAllFieldValidObservable().filter(__ -> __);
    }

    public final Observable<Object> formEdited() {
        return allFieldObservable;
    }

    @NonNull
    public final T requiredBuild() {
        if (isAllMandatoryFieldsProvided()) {
            return buildForm();
        }
        throw new IllegalStateException("All mandatory fields provided are not valid");
    }

    @Nullable
    public final T build() {
        try {
            return requiredBuild();
        } catch (Exception ignored) {
            return null;
        }
    }

    @NonNull
    protected abstract T buildForm();

    @Nullable
    @Override
    public T getField() {
        throw new UnsupportedOperationException("Call getField is supported on Form Field");
    }

    @NonNull
    @Override
    public T requiredField() {
        throw new UnsupportedOperationException("Call requiredField is supported on Form Field");
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void setField(@Nullable T value) {

    }

    public List<ErrorField> errors() {
        List<ErrorField> errorFieldList = new ArrayList<>();
        for (BaseField field : fields) {
            try {
                field.validate();
            } catch (CompositeException e) {
                errorFieldList.add(new ErrorField(field.getFieldId(), e));
            }
        }
        return errorFieldList;
    }

    @Override
    public boolean isSet() {
        for (BaseField field : fields) {
            if(field.isSet()){
                return true;
            }
        }
        return false;
    }
}
