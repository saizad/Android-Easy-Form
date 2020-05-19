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
    transient Exception validatedException;
    private transient boolean mIsMandatory;
    private transient boolean isValueModified;

    @NonNull
    private transient final String fieldId;
    private transient final @Nullable
    T ogField;
    protected transient final Object emptyObject = new Object();
    private transient BehaviorSubject<Object> subject = BehaviorSubject.create();
    private transient BehaviorSubject<String> networkErrorSubject = BehaviorSubject.create();
    private @Nullable
    T field;
    private final static String EMPTY_NETWORK_ERROR_MESSAGE = "--empty---";
    private transient boolean isValid = false;

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

    public Observable<Pair<Boolean, Exception>> errorStateObservable() {
        return observable().map(o -> new Pair<>(isValid, validatedException));
    }

    public Observable<Boolean> modifiedObservable() {
        return observable().map(o -> isModified());
    }

    public Observable<Boolean> isValueModifiedObservable() {
        return observable().map(o -> isValueModified);
    }

    public Observable<String> networkError() {
        return networkErrorSubject.filter(s -> !s.equals(EMPTY_NETWORK_ERROR_MESSAGE));
    }

    public final boolean isModified() {
        //TODO BUG if ogField was null then it shouldn't equate to modifed
        return ogField != field;
    }

    public boolean isSet() {
        return isNotNull(field);
    }

    @Nullable
    public T getField() {
        try {
            return requiredField();
        }catch (Exception e){
            return null;
        }
    }

    @NonNull
    public T requiredField() {
        if(field == null){
            throw new IllegalStateException(fieldId + " has a null value");
        }
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
                if(mIsMandatory && !isSet()){
                    throw new Exception("Field is mandatory and value is not provided.");
                }else {
                    validate();
                    retValue = true;
                }
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
