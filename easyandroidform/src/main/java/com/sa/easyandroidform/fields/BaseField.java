package com.sa.easyandroidform.fields;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.util.Pair;

import com.sa.easyandroidform.ObjectUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

import static com.sa.easyandroidform.ObjectUtils.isNotNull;
import static com.sa.easyandroidform.Utils.compositeExceptionMessage;


public abstract class BaseField<T> {

    private @Nullable
    transient CompositeException validatedException;
    private transient boolean isMandatory;
    private transient Boolean isValueModified;

    @NonNull
    private transient final String fieldId;
    private transient final @Nullable
    T ogField;
    protected transient final Object emptyObject = new Object();
    private transient BehaviorSubject<Object> publishQ = BehaviorSubject.create();
    private transient BehaviorSubject<Object> subject = BehaviorSubject.create();
    private transient BehaviorSubject<String> networkErrorSubject = BehaviorSubject.create();
    private final transient BehaviorSubject<Boolean> validatingSubject = BehaviorSubject.create();
    private @Nullable
    T field;
    private final static String EMPTY_NETWORK_ERROR_MESSAGE = "--empty---";
    private transient Boolean isValid;

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
        this.isMandatory = isMandatory;
        this.fieldId = fieldId;
        this.ogField = ogField;
        this.field = ogField;
        __publish();
        publishQ.subscribeOn(Schedulers.io())
                .switchMap(o -> Observable.fromCallable(() -> {
                    publish();
                    return isValid;
                }).subscribeOn(Schedulers.io()))
                .subscribe();
    }

    public Observable<Object> observable() {
        return subject;
    }

    public Observable<T> setObservable() {
        return observable().filter(o -> isNotNull(field)).map(o -> field);
    }

    public Observable<Boolean> validObservable() {
        return observable()
                .subscribeOn(Schedulers.io())
                .map(o -> isValid())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Pair<String, T>> nonEmptyInvalidObservable() {
        return setObservable()
                .filter(o -> ObjectUtils.isNotNull(validatedException))
                .map(__ -> new Pair<>(compositeExceptionMessage(validatedException), getField()));
    }

    public Observable<Pair<String, T>> invalidObservable() {
        return observable()
                .filter(o -> ObjectUtils.isNotNull(validatedException))
                .map(__ -> new Pair<>(compositeExceptionMessage(validatedException), getField()));
    }

    public Observable<T> notEmptyValidObservable() {
        return setObservable()
                .subscribeOn(Schedulers.io())
                .filter(o -> isValid())
                .observeOn(AndroidSchedulers.mainThread())
                .map(o -> field);
    }

    public Observable<Object> fieldUnsetObservable() {
        return observable().filter(o -> ObjectUtils.isNull(field));
    }

    public Observable<Pair<Boolean, CompositeException>> errorStateObservable() {
        return observable()
                .subscribeOn(Schedulers.io())
                .map(o -> new Pair<>(isValid(), validatedException))
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Boolean> modifiedObservable() {
        return observable().map(o -> isModified());
    }

    public Observable<Boolean> isValueModifiedObservable() {
        return observable().map(o -> hasValueChanged());
    }

    public Observable<String> networkError() {
        return networkErrorSubject.filter(s -> !s.equals(EMPTY_NETWORK_ERROR_MESSAGE));
    }

    public Observable<Boolean> validatingObservable() {
        return validatingSubject.observeOn(AndroidSchedulers.mainThread());
    }

    public boolean isModified() {
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
        } catch (Exception e) {
            return null;
        }
    }

    @NonNull
    public T requiredField() {
        if (field == null) {
            throw new IllegalStateException(fieldId + " has a null value");
        }
        return field;
    }

    @CallSuper
    public void clear() {
        setField(null);
    }

    /**
     * This method is called on a background thread
     */
    @WorkerThread
    @CallSuper
    public void setField(@Nullable T value) {
        field = value;
        publishQ.onNext(emptyObject);
    }

    @Nullable
    public T getOgField() {
        return ogField;
    }

    /**
     * This method is called on a background thread
     */
    @WorkerThread
    @CallSuper
    public synchronized void publish() {
        isValid = __isValidValidate();
        isValueModified = __isFieldValueModifiedValidate();
        __publish();
    }

    private void __publish() {
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

    public final boolean isMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(boolean mIsMandatory) {
        this.isMandatory = mIsMandatory;
        publish();
    }

    @WorkerThread
    private synchronized boolean __isValidValidate() {
        T field = this.field;
        validatingSubject.onNext(true);
        validatedException = null;
        boolean retValue = false;

        if (!isMandatory && ObjectUtils.isNull(field)) {
            retValue = true;
        } else {
            try {
                validate();
                retValue = true;
            } catch (CompositeException e) {
                validatedException = e;
            }
        }
        validatingSubject.onNext(false);
        return retValue;
    }

    private boolean __isFieldValueModifiedValidate() {
        if (field != null && ogField != null) {
            return isFieldValueModified(field, ogField);
        } else if (field == null && ogField == null) {
            return false;
        } else return ogField == null;
    }

    /**
     * This method is called on a background thread - you are required to <b>synchronously</b>
     * validate and return if it is valid
     */
    @WorkerThread
    public boolean isValid() {
        if (isValid == null) {
            isValid = __isValidValidate();
        }
        return isValid;
    }

    public boolean hasValueChanged() {
        if (isValueModified == null) {
            isValueModified = __isFieldValueModifiedValidate();
        }
        return isValueModified;
    }

    protected abstract boolean isFieldValueModified(@NonNull T field, @NonNull T ogField);

    /**
     * This method is called on a background thread - you are required to <b>synchronously</b>
     * validate and throw the error.
     */
    @CallSuper
    @WorkerThread
    public void validate() throws CompositeException {
        if (isMandatory && !isSet()) {
            throw new CompositeException(new Exception(fieldId + " field is mandatory and value is not provided."));
        }
    }
}
