package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.exceptions.CompositeException;


public class ListField<T> extends BaseField<List<T>>{

    public ListField(@NonNull String fieldId) {
        this(fieldId, null);
    }

    public ListField(@NonNull String fieldId, boolean isMandatory) {
        this(fieldId, null, isMandatory);
    }

    public ListField(@NonNull String fieldId, @Nullable List<T> ogField) {
        this(fieldId, ogField, false);
    }

    public ListField(@NonNull String fieldId, @Nullable List<T> ogField, boolean isMandatory) {
        super(fieldId, ogField, isMandatory);
        setField(ogField);
    }

    @Override
    protected boolean isFieldValueModified(@NonNull List<T> field, @NonNull List<T> ogField) {
        if(field.size() != ogField.size()){
            return true;
        }
        for (T currentFieldItem : field) {
            boolean isModified = false;
            for (T ogFieldItem : ogField) {
                boolean found = compare(ogFieldItem, currentFieldItem);
                isModified = !found;
                if(found){
                    break;
                }
            }
            if(isModified){
                return true;
            }
        }
        return false;
    }

    protected boolean compare(T item1, T item2){
        return item1.equals(item2);
    }

    @Override
    public void validate() throws CompositeException {
        super.validate();
        if (isMandatory() && isSet() && getField().isEmpty()) {
            throw new CompositeException(new Throwable("Mandatory list can't be empty"));
        }
    }

    public Observable<List<T>> emptyListObservable() {
        return notEmptyValidObservable()
                .filter(List::isEmpty);
    }

    @Override
    public Observable<Object> fieldUnsetObservable() {
        return Observable.merge(emptyListObservable(), super.fieldUnsetObservable()).map(o -> emptyObject);
    }
}
