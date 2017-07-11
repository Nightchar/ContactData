package com.droid.contactdata.service.contact;

import android.support.annotation.NonNull;
import android.util.Log;

import com.droid.contactdata.model.contact.ContactListModel;
import com.droid.contactdata.model.contact.ContactListRequestModel;
import com.droid.contactdata.service.core.ApiRequest;
import com.droid.contactdata.service.core.CustomRetrofitCallBack;
import com.droid.contactdata.service.core.RetrofitApiCallManager;
import com.droid.contactdata.utils.CommonUtils;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Class handle the contact list web service.
 */
public class ContactListApiRequest implements ApiRequest<ContactListRequestModel, ApiRequest.ResponseReceiver> {
    private static final String TAG = "ContactListApiRequest";
    private Call<ContactListModel> mContactListCall = null;

    @Override
    public void makeRequest(@NonNull ContactListRequestModel requestModel, @NonNull ResponseReceiver responseReceiver) {

        final ContactServiceInterface contactServiceInterface = CommonUtils.retroFitCreator(ContactServiceInterface
                .class);
        mContactListCall = contactServiceInterface.getContacts(requestModel.getServiceName());
        Log.d(TAG, "REQUEST_URL: " + mContactListCall.request().url());

        Callback<ContactListModel> contactListModelCallback = new CustomRetrofitCallBack<>(responseReceiver);
        RetrofitApiCallManager.enqueue(mContactListCall, contactListModelCallback);
    }

    @Override
    public void cancelRequest() {
        Log.d(TAG, "REQUEST_CANCEL: " + mContactListCall.request().url());
        RetrofitApiCallManager.cancelApiCall(mContactListCall);
    }

    @Override
    public boolean isRequestExecuted() {
        return RetrofitApiCallManager.isExecuted(mContactListCall);
    }
}
