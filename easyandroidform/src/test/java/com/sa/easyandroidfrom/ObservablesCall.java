package com.sa.easyandroidfrom;

import com.sa.easyandroidfrom.fields.BaseField;

import io.reactivex.observers.TestObserver;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ObservablesCall {

    public static void isFieldValid(BaseField<?> field) {
        assertFalse(field.isFieldValid());
    }

    public static void errorState(BaseField<?> field, boolean state) {
        final TestObserver<?> testObserver = field.errorStateObservable()
                .map(__ -> __.first)
                .test()
                .assertValue(state);
        testObserver.dispose();
    }

    public static void isModifiedObservable(BaseField<?> field, boolean state) {
        final TestObserver<?> testObserver = field.modifiedObservable()
                .test()
                .assertValue(state);
        testObserver.dispose();
    }

    public static void validObservable(BaseField<?> field, boolean state) {
        final TestObserver<?> testObserver = field.validObservable()
                .test()
                .assertValue(state);
        testObserver.dispose();
    }

    public static void networkError(BaseField<?> field, int callCount) {
        final TestObserver<?> testObserver = field.networkError()
                .test()
                .assertValueCount(callCount);
        testObserver.dispose();
    }

    public static void fieldUnsetObservable(BaseField<?> field, int callCount) {
        final TestObserver<?> testObserver = field.fieldUnsetObservable()
                .test()
                .assertValueCount(callCount);
        testObserver.dispose();
    }

    public static void notEmptyValidObservable(BaseField<?> field, int callCount) {
        final TestObserver<?> testObserver =
                field
                        .notEmptyValidObservable()
                        .test()
                        .assertValueCount(callCount);
        testObserver.dispose();
    }

    public static void nonEmptyInvalidObservable(BaseField<?> field, int callCount) {
        final TestObserver<?> testObserver =
                field
                        .nonEmptyInvalidObservable()
                        .test()
                        .assertValueCount(callCount);
        testObserver.dispose();
    }

    public static void invalidObservable(BaseField<?> field, int callCount) {
        final TestObserver<?> testObserver =
                field
                        .invalidObservable()
                        .test()
                        .assertValueCount(callCount);
        testObserver.dispose();
    }

    public static void setObservable(BaseField<?> field, int callCount) {
        final TestObserver<?> testObserver =
                field
                        .setObservable()
                        .test()
                        .assertValueCount(callCount);
        testObserver.dispose();
    }

    public static void observable(BaseField<?> field, int callCount) {
        final TestObserver<Object> objectTestObserver =
                field
                        .observable()
                        .test()
                        .assertValueCount(callCount);
        objectTestObserver.dispose();
    }
}
