package com.droid.contactdata.service.core;

import android.support.annotation.NonNull;

import com.droid.contactdata.model.BaseModel;


/**
 * An interface to define some common operations required by every Service using the retrofit.
 */
public interface ApiRequest<M, R> {

    /**
     * Method to initiate the request process.
     *
     * @param requestModel
     * @param responseReceiver
     */
    void makeRequest(@NonNull M requestModel, @NonNull R responseReceiver);

    /**
     * Method to cancel the request process.
     */
    void cancelRequest();

    /**
     * Method to check if the Retrofit service is executed.
     *
     * @return
     */
    boolean isRequestExecuted();

    interface ResponseReceiver {
        void onReceiveResult(BaseModel resultData);

        void onReceiveError(String errorMessage);
    }
}
