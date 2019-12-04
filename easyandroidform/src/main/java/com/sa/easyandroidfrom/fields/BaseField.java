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

    private boolean mIsMandatory;
    @NonNull
    private final String fieldId;
    private final @Nullable
    T ogField;
    protected transient Object emptyObject = new Object();
    private transient BehaviorSubject<Object> subject = BehaviorSubject.create();
    private transient Observable<Object> observable = subject;
    private transient BehaviorSubject<String> networkErrorSubject = BehaviorSubject.create();
    private transient Observable<String> networkError = networkErrorSubject;
    private @Nullable
    T field;
    private final static String EMPTY_NETWORK_ERROR_MESSAGE = "--empty---";
    public boolean isValidCache = false;

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
        return observable;
    }

    public Observable<T> setObservable() {
        return observable().filter(o -> isNotNull(field)).map(o -> field);
    }

    public Observable<Boolean> validObservable() {
        return observable().map(o -> isValidCache);
    }

    public Observable<Pair<String, T>> nonEmptyInvalidObservable() {
        return setObservable().filter(o -> !isValidCache).map(o -> {
            try {
                validate();
                return new Pair<>("--- some thing is broken :( ---", getField());
            } catch (Exception e) {
                return new Pair<>(e.getMessage(), field);
            }
        });
    }

    public Observable<Pair<String, T>> invalidObservable() {
        return observable().filter(o -> !isValidCache).map(o -> {
            try {
                validate();
                return new Pair<>("--- some thing is broken :( ---", getField());
            } catch (Exception e) {
                return new Pair<>(e.getMessage(), field);
            }
        });
    }

    public Observable<T> notEmptyValidObservable() {
        return setObservable().filter(o -> isValidCache).map(o -> field);
    }

    public Observable<Object> fieldUnsetObservable() {
        return observable().filter(o -> ObjectUtils.isNull(field));
    }

    public Observable<Pair<Boolean, String>> runTimeErrorState() {
        return observable().map(o -> new Pair<>(isValidCache || isRuntimeValid(), getFieldId()));
    }

    public Observable<Pair<Boolean, String>> errorState() {
        return observable().map(o -> new Pair<>(isValidCache, getFieldId()));
    }

    public Observable<Boolean> modified() {
        return observable().map(o -> isModified());
    }

    public boolean isModified() {
        return !equals(ogField);
    }

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
        isValidCache = __isValid();
        subject.onNext(emptyObject);
        networkErrorSubject.onNext(EMPTY_NETWORK_ERROR_MESSAGE);
    }

    public void networkErrorPublish(String error) {
        networkErrorSubject.onNext(error);
    }

    public Observable<String> networkError() {
        return networkError.filter(s -> !s.equals(EMPTY_NETWORK_ERROR_MESSAGE));
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
        if (!mIsMandatory && ObjectUtils.isNull(field)) {
            return  true;
        }
        try {
            validate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFieldValid() {
        return isValidCache;
    }

    public abstract void validate() throws Exception;
}
