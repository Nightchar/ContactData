package com.droid.contactdata.service.core;

import android.content.Context;
import android.util.Log;

import com.droid.contactdata.R;
import com.droid.contactdata.application.BaseApplication;
import com.droid.contactdata.common.enums.ExceptionEnum;
import com.droid.contactdata.model.BaseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class handle the Retrofit callbacks. Then it sends the result data or send the error.
 */

public class CustomRetrofitCallBack<T extends BaseModel> implements Callback<T> {

    private static final String TAG = "CustomRetrofitCallBack";
    private ApiRequest.ResponseReceiver mResponseReceiver;

    public CustomRetrofitCallBack(ApiRequest.ResponseReceiver responseReceiver) {
        this.mResponseReceiver = responseReceiver;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        BaseModel baseModel = response.body();
        mResponseReceiver.onReceiveResult(baseModel);
        Log.d(TAG, "RESPONSE: " + response.raw());
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        if (call.isCanceled()) {
            Log.e(TAG, "request was cancelled");
        } else {
            Log.e(TAG, "ERROR: " + throwable.getClass().getSimpleName());
            String failSafeMessage = BaseApplication.getAppContext().getResources().getString(R.string
                    .err_loading_data);
            if (throwable instanceof Exception) {
                Exception exception = (Exception) throwable;
                failSafeMessage = getErrorMessage(BaseApplication.getAppContext(), exception, failSafeMessage);
            }
            mResponseReceiver.onReceiveError(failSafeMessage);
        }
    }

    /**
     * Gets the error message according to instance of exception thrown.
     *
     * @param context         the reference of the activity
     * @param exception       the thrown exception
     * @param failSafeMessage the error message to be shown
     * @return the error message
     */
    private String getErrorMessage(Context context, Exception exception, String failSafeMessage) {
        String errorMessage = failSafeMessage;
        ExceptionEnum exceptionEnum = null;
        if (exception != null) {
            try {
                exceptionEnum = ExceptionEnum.valueOf(exception.getClass().getSimpleName());
            } catch (IllegalArgumentException eArgumentException) {
                Log.d(getClass().getName(), eArgumentException.getLocalizedMessage());
            }
            if (exceptionEnum != null) {
                errorMessage = context.getString(exceptionEnum.getMsgId());
            }
        }
        return errorMessage;
    }
}
