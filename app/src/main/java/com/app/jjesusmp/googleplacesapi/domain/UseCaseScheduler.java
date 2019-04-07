package com.app.jjesusmp.googleplacesapi.domain;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UseCaseScheduler {

    private static UseCaseScheduler INSTANCE;

    private final Handler mHandler;

    private static ThreadPoolExecutor mThreadPoolExecutor;

    private UseCaseScheduler() {
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public <Request extends UseCase.RequestValues, Response extends UseCase.ResponseValue> void execute(
            final UseCase<Request, Response> useCase, Request values, UseCase.UseCaseCallback<Response> callback) {
        useCase.setRequestValues(values);
        useCase.setUseCaseCallback(new UiCallbackWrapper(callback, mHandler));

        getExecutor().execute(() -> useCase.run());
    }

    private static final class UiCallbackWrapper<Response extends UseCase.ResponseValue> implements
            UseCase.UseCaseCallback<Response> {
        private final UseCase.UseCaseCallback<Response> mCallback;
        private final Handler mHandler;

        public UiCallbackWrapper(UseCase.UseCaseCallback<Response> callback,
                                 Handler handler) {
            mCallback = callback;
            mHandler = handler;
        }

        @Override
        public void onSuccess(Response response) {
            mHandler.post(() -> mCallback.onSuccess(response));
        }

        @Override
        public void onError() {
            mHandler.post(() -> mCallback.onError());
        }
    }

    private static ThreadPoolExecutor getExecutor() {
        if (mThreadPoolExecutor == null) {
            final int SIZE = 2;
            final int MAX = 2;
            final int TIMEOUT = 30;
            mThreadPoolExecutor = new ThreadPoolExecutor(SIZE, MAX, TIMEOUT, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(MAX));
        }
        return mThreadPoolExecutor;
    }

    public static UseCaseScheduler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UseCaseScheduler();
        }
        return INSTANCE;
    }
}
