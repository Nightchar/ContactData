package com.droid.contactdata.service.core;

import android.support.annotation.Nullable;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * A Class to define wrapper operation over inbuilt Retrofit operations.
 */
public class RetrofitApiCallManager {

    /**
     * Handle the enque operation.
     *
     * @param apiCall
     * @param callback
     * @param <T>
     */
    public static <T> void enqueue(@Nullable final Call<T> apiCall, @Nullable final Callback<T> callback) {
        if (apiCall != null && callback != null) {
            apiCall.enqueue(callback);
        }
    }

    /**
     * Handle the cancel operation.
     *
     * @param apiCall
     * @param <T>
     */
    public static <T> void cancelApiCall(@Nullable final Call<T> apiCall) {
        if (apiCall != null) {
            apiCall.cancel();
        }
    }

    /**
     * Check that if service is executed or not.
     *
     * @param apiCall
     * @param <T>
     * @return
     */
    public static <T> boolean isExecuted(@Nullable final Call<T> apiCall) {
        return apiCall != null && apiCall.isExecuted();
    }
}
