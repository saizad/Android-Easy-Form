package com.sa.easyandroidfrom.fields;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sa.easyandroidfrom.ListUtils;
import com.sa.easyandroidfrom.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


public class ListField<T> extends Field<List<T>> {

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
    public boolean isModified() {
        if (ObjectUtils.isNull(getOgField()))
            return !getField().isEmpty();

        for (T ogListValue : getOgField()) {
            if (!ListUtils.contains(getField(), ogListValue)) {
                return true;
            }
        }

        return super.isModified();
    }

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
