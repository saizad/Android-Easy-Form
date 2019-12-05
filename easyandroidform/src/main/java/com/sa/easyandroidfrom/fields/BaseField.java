package com.sa.easyandroidfrom.fields;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;

import com.sa.easyandroidfrom.ObjectUtils;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

import static com.sa.easyandroidfrom.ObjectUtils.isNotNull;


public abstract class BaseField<T> {

    private @Nullable
    Exception validatedException;
    private boolean mIsMandatory;
    private boolean isValueModified;

    @NonNull
    private final String fieldId;
    private final @Nullable
    T ogField;
    protected transient final Object emptyObject = new Object();
    private transient BehaviorSubject<Object> subject = BehaviorSubject.create();
    private transient BehaviorSubject<String> networkErrorSubject = BehaviorSubject.create();
    private @Nullable
    T field;
    private final static String EMPTY_NETWORK_ERROR_MESSAGE = "--empty---";
    private boolean isValid = false;

    public BaseField(@NonNull String fieldId) {
        this(fieldId, null, false);
    }

    public BaseField(@NonNull String fieldId, boolean isMandatory) {
        this(fieldId, null, isMandatory);
    }

    public BaseField(@NonNull String fieldId, @Nullable T ogField) {
        this(fieldId, ogField, false);
    }

    public BaseField(@NonNull String fieldId, @Nullable T ogField, boolean isMandatory) {
        mIsMandatory = isMandatory;
        this.fieldId = fieldId;
        this.ogField = ogField;
        setField(ogField);
    }

    public Observable<Object> observable() {
        return subject;
    }

    public Observable<T> setObservable() {
        return observable().filter(o -> isNotNull(field)).map(o -> field);
    }

    public Observable<Boolean> validObservable() {
        return observable().map(o -> isValid);
    }

    public Observable<Pair<String, T>> nonEmptyInvalidObservable() {
        return setObservable()
                .filter(o -> ObjectUtils.isNotNull(validatedException))
                .map(__ -> new Pair<>(validatedException.getMessage(), getField()));
    }

    public Observable<Pair<String, T>> invalidObservable() {
        return observable()
                .filter(o -> ObjectUtils.isNotNull(validatedException))
                .map(__ -> new Pair<>(validatedException.getMessage(), getField()));
    }

    public Observable<T> notEmptyValidObservable() {
        return setObservable().filter(o -> isValid).map(o -> field);
    }

    public Observable<Object> fieldUnsetObservable() {
        return observable().filter(o -> ObjectUtils.isNull(field));
    }

    public Observable<Pair<Boolean, String>> runTimeErrorState() {
        return observable().map(o -> new Pair<>(isValid || isRuntimeValid(), getFieldId()));
    }

    public Observable<Pair<Boolean, String>> errorState() {
        return observable().map(o -> new Pair<>(isValid, getFieldId()));
    }

    public Observable<Boolean> modified() {
        return observable().map(o -> isModified());
    }

    public Observable<String> networkError() {
        return networkErrorSubject.filter(s -> !s.equals(EMPTY_NETWORK_ERROR_MESSAGE));
    }

    public boolean isModified() {
        return ogField != field;
    }

    //Todo test
    public boolean isRuntimeValid() {
        return ObjectUtils.isNull(field);
    }

    public boolean isSet() {
        return isNotNull(field);
    }

    @Nullable
    public T getField() {
        return field;
    }

    @CallSuper
    public void setField(@Nullable T value) {
        field = value;
        publish();
    }

    @Nullable
    public T getOgField() {
        return ogField;
    }

    public void publish() {
        isValid = __isValid();
        subject.onNext(ObjectUtils.coalesce(field, emptyObject));
        networkErrorSubject.onNext(EMPTY_NETWORK_ERROR_MESSAGE);
    }

    //Todo test
    public void networkErrorPublish(String error) {
        networkErrorSubject.onNext(error);
    }

    @NonNull
    public final String getFieldId() {
        return fieldId;
    }

    public boolean isMandatory() {
        return mIsMandatory;
    }

    //Todo test
    public void setIsMandatory(boolean mIsMandatory) {
        this.mIsMandatory = mIsMandatory;
        publish();
    }

    private boolean __isValid() {
        validatedException = null;
        boolean retValue = false;

        if (!mIsMandatory && ObjectUtils.isNull(field)) {
            retValue = true;
        } else {
            try {
                validate();
                retValue = true;
            } catch (Exception e) {
                validatedException = e;
            }
        }

        if(field != null && ogField != null) {
            isValueModified = isFieldValueModified(field, ogField);
        }else if(field == null && ogField == null){
            isValueModified = false;
        }else if(ogField == null){
            isValueModified = true;
        }

        return retValue;
    }

    public final boolean isFieldValid() {
        return isValid;
    }

    public final boolean hasValueChanged() {
        return isValueModified;
    }

    protected abstract boolean isFieldValueModified(@NonNull T field, @NonNull T ogField);

    protected abstract void validate() throws Exception;
}
