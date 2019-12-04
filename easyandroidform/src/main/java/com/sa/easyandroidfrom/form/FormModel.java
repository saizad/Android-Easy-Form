package com.sa.easyandroidfrom.form;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.fields.BaseField;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public abstract class FormModel<T> {

    private final transient List<BaseField> fields;
    private final transient Observable<Object> allFieldObservable;
    private transient int mandatory = 0;

    public FormModel(List<BaseField> fields) {
        this.fields = fields;
        ArrayList<Observable<Object>> list = new ArrayList<>();
        for (BaseField field : fields) {
            list.add(field.observable());
            incrementMandatory(field);
        }
        allFieldObservable = Observable.merge(list);
    }

    private void incrementMandatory(BaseField field) {
        if (field.isMandatory()) {
            mandatory += 1;
        }
    }

    public void add(BaseField field) {
        incrementMandatory(field);
        fields.add(field);
        allFieldObservable.mergeWith(field.observable());
    }

    @Nullable
    public final BaseField getField(String fieldName) {
        for (BaseField field : fields) {
            if (field.getFieldId().equalsIgnoreCase(fieldName)) {
                return field;
            }
        }
        return null;
    }

    public final void publish() {
        for (BaseField field : fields) {
            field.publish();
        }
    }

    public final boolean isModified() {
        for (BaseField field : fields) {
            if (field.isModified()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isAllFieldsValid() {
        for (BaseField field : fields) {
            if (!field.isValidCache) {
                return false;
            }
        }
        return true;
    }

    public final boolean isAllMandatoryFieldsProvided() {
        for (BaseField field : fields) {
            if (field.isMandatory() && !field.isValidCache) {
                return false;
            }
        }
        return true;
    }

    public final boolean isFormValid() {
        for (BaseField field : fields) {
            if (!field.isValidCache) {
                return false;
            }
        }
        return true;
    }

    public int getCount() {
        return fields.size();
    }

    public int getMandatoryCount() {
        return mandatory;
    }

    public int getMissingMandatoryCount() {
        int count = 0;
        for (BaseField field : fields) {
            if (field.isMandatory() && !field.isValidCache) {
                count += 1;
            }
        }
        return count;
    }

    public int getMandatoryCompletedCount() {
        int count = 0;
        for (BaseField field : fields) {
            if (field.isMandatory() && field.isValidCache) {
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
        return isAllFieldValidObservable().filter(isValid -> isValid);
    }

    public final Observable<Object> formEdited() {
        return allFieldObservable;
    }

    @Nullable
    public final T build() {
        if (isAllMandatoryFieldsProvided()) {
            return buildForm();
        }
        return null;
    }

    @NonNull
    protected abstract T buildForm();

    protected <M> Observable<M> submit(Observable<M> observable) {
        return observable;
    }
}
