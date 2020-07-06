package com.sa.easyandroidform.form;

import android.annotation.SuppressLint;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidform.ErrorField;
import com.sa.easyandroidform.KotlinUtilsKt;
import com.sa.easyandroidform.fields.BaseField;
import com.sa.easyandroidform.fields.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.exceptions.CompositeException;

public abstract class FormModel<T> extends Field<T> {

    public final transient List<BaseField<?>> fields;
    private final transient Observable<BaseField<?>> allFieldObservable;

    private static boolean isMandatory(List<BaseField<?>> fields) {
        for (BaseField field : fields) {
            if (field.isMandatory()) {
                return true;
            }
        }
        return false;
    }

    public FormModel(ArrayList<BaseField<?>> fields) {
        this("form", fields);
    }

    public FormModel(String fieldId, ArrayList<BaseField<?>> fields) {
        super(fieldId, isMandatory(KotlinUtilsKt.checkForDuplicateFieldName(fields)));
        this.fields = fields;
        List<Observable<BaseField<?>>> list = new ArrayList<>();
        for (BaseField<?> field : fields) {
            list.add(buildFieldObservable(field));
        }
        allFieldObservable = Observable.merge(list);
    }


    public void add(BaseField<?>... addFields) {
        KotlinUtilsKt.checkForDuplicateFieldName(new ArrayList<BaseField<?>>() {
            {
                addAll(fields);
                addAll(Arrays.asList(addFields));
            }
        });

        fields.addAll(Arrays.asList(addFields));
        for (BaseField<?> addField : addFields) {
            allFieldObservable.mergeWith(buildFieldObservable(addField));
        }
    }

    private Observable<BaseField<?>> buildFieldObservable(BaseField<?> field) {
        return field.observable().map(__ -> field);
    }

    @Override
    public Observable<Object> observable() {
        return allFieldObservable.map(__ -> new Object());
    }

    @NonNull
    public final <F extends BaseField<?>> F requiredFindField(String fieldName) {
        for (BaseField<?> field : fields) {
            if (field.getFieldId().equalsIgnoreCase(fieldName)) {
                return (F) field;
            }
        }
        throw new IllegalStateException(fieldName + " form field not found");
    }

    @Nullable
    public final <F extends BaseField<?>> F findField(String fieldName) {
        try {
            return requiredFindField(fieldName);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public final void publish() {
        for (BaseField field : fields) {
            field.publish();
        }
        super.publish();
    }

    @Override
    public void networkErrorPublish(String error) {
        super.networkErrorPublish(error);
    }

    @CallSuper
    @Override
    public void validate() throws CompositeException {
        super.validate();
        List<CompositeException> exceptions = new ArrayList<>();
        for (BaseField<?> field : fields) {
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

    @Override
    public boolean isModified() {
        for (BaseField<?> field : fields) {
            if (field.isModified()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isValid() {
        for (BaseField<?> field : fields) {
            if (!field.isValid()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasValueChanged() {
        for (BaseField<?> field : fields) {
            if (field.hasValueChanged()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isAllMandatoryFieldsProvided() {
        for (BaseField<?> field : fields) {
            if (field.isMandatory() && !field.isValid()) {
                return false;
            }
        }
        return true;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void clear() {
        for (BaseField<?> field : fields) {
            field.clear();
        }
    }

    public final Observable<BaseField<?>> formEdited() {
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
    public final T getField() {
        throw new UnsupportedOperationException("Call getField is supported on Form Field");
    }

    @NonNull
    @Override
    public final T requiredField() {
        throw new UnsupportedOperationException("Call requiredField is supported on Form Field");
    }

    @Override
    public final void setIsMandatory(boolean mIsMandatory) {
        throw new UnsupportedOperationException("Call setIsMandatory is supported on Form Field");
    }

    public List<ErrorField> errors() {
        List<ErrorField> errorFieldList = new ArrayList<>();
        for (BaseField<?> field : fields) {
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
        for (BaseField<?> field : fields) {
            if (field.isSet()) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        int size = 0;
        for (BaseField<?> field : fields) {
            if (field instanceof FormModel) {
                size += ((FormModel<?>) field).size();
            } else {
                size += 1;
            }
        }
        return size;
    }

    public int setCount() {
        int size = 0;
        for (BaseField<?> field : fields) {
            if (field instanceof FormModel) {
                size += ((FormModel<?>) field).setCount();
            } else {
                if(field.isSet()) {
                    size += 1;
                }
            }
        }
        return size;
    }

    @Override
    public void setField(@Nullable T value) {
        if(value == null){
            for (BaseField<?> field : fields) {
                field.setField(null);
            }
        }
        super.setField(value);
    }
}
