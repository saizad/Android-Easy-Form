package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


abstract public class ListField<T> extends BaseField<List<T>>{

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
        for (T ogFieldItem : ogField) {
            boolean isModified = false;
            for (T currentFieldItem : field) {
                isModified = compare(ogFieldItem, currentFieldItem);
                if(isModified){
                    break;
                }
            }
            if(!isModified){
                return true;
            }
        }
        return false;
    }

    abstract protected boolean compare(T item1, T item2);

    @Override
    public void validate() throws Exception {

    }

    @Override
    public void setField(@Nullable List<T> value) {
        if (ObjectUtils.isNull(value)) {
            value = new ArrayList<>();
        }
        super.setField(value);
    }

    @NonNull
    @Override
    public List<T> getField() {
        return ObjectUtils.coalesce(super.getField(), new ArrayList<>());
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
