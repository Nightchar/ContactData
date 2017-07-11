package com.droid.contactdata.fragment.contact;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.droid.contactdata.common.CommonConstants;
import com.droid.contactdata.fragment.BaseFragment;
import com.droid.contactdata.helper.contact.ContactListResponseReceiver;
import com.droid.contactdata.model.BaseModel;
import com.droid.contactdata.model.contact.ContactListRequestModel;
import com.droid.contactdata.service.contact.ContactListApiRequest;
import com.droid.contactdata.service.core.ApiRequest;


/**
 * Fragment class to load the data from web service.
 */
public class ServiceFragment extends BaseFragment implements ApiRequest.ResponseReceiver {

    public static final String SERVICE_FRAGMENT = "SERVICE_FRAGMENT";

    private ContactListApiRequest mContactListApiRequest;
    private ContactListResponseReceiver mContactListResponseReceiver;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ContactListResponseReceiver) {
            mContactListResponseReceiver = (ContactListResponseReceiver) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    protected int getFragmentLayout() {
        return -1;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (mContactListApiRequest == null && bundle != null) {
            ContactListRequestModel contactListRequestModel = (ContactListRequestModel) bundle.getSerializable(CommonConstants
                    .CONTACT_LIST_REQUEST_MODEL);
            startDataLoading(contactListRequestModel);
        }
    }

    private void startDataLoading(ContactListRequestModel contactListRequestModel) {
        if (mContactListApiRequest == null) {
            mContactListApiRequest = new ContactListApiRequest();
            mContactListApiRequest.makeRequest(contactListRequestModel, this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mContactListApiRequest != null) {
            mContactListApiRequest.cancelRequest();
        }
    }

    @Override
    public void onReceiveResult(BaseModel resultData) {
        if (mContactListResponseReceiver != null) {
            mContactListResponseReceiver.onReceiveContactData(resultData);
        }
    }

    @Override
    public void onReceiveError(String errorMessage) {
        if (mContactListResponseReceiver != null) {
            mContactListResponseReceiver.onReceiveContactError(errorMessage);
        }
    }
}