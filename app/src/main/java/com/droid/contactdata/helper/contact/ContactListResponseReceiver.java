package com.droid.contactdata.helper.contact;

import com.droid.contactdata.model.BaseModel;

/**
 * Interface handle the operation in activity to pass the webservice data from other place.
 */
public interface ContactListResponseReceiver {
    /**
     * Method called when webservice return some positive data.
     *
     * @param resultData
     */
    void onReceiveContactData(BaseModel resultData);

    /**
     * Method called when webservice return some error.
     *
     * @param errorMessage
     */
    void onReceiveContactError(String errorMessage);
}
