package com.app.jjesusmp.googleplacesapi.domain;

public abstract class UseCase<Request extends UseCase.RequestValues, Response extends UseCase.ResponseValue> {

    private Request mRequestValues;

    private UseCaseCallback<Response> mUseCaseCallback;

    public void setRequestValues(Request requestValues) {
        mRequestValues = requestValues;
    }

    public Request getRequestValues() {
        return mRequestValues;
    }

    public UseCaseCallback<Response> getUseCaseCallback() {
        return mUseCaseCallback;
    }

    public void setUseCaseCallback(UseCaseCallback<Response> useCaseCallback) {
        mUseCaseCallback = useCaseCallback;
    }

    void run() {
        executeUseCase(mRequestValues);
    }

    protected abstract void executeUseCase(Request requestValues);

    public interface RequestValues {
    }

    public interface ResponseValue {
    }

    public interface UseCaseCallback<Response> {
        void onSuccess(Response response);
        void onError();
    }
}
