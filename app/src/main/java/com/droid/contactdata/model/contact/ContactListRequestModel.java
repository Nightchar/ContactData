package com.droid.contactdata.model.contact;

import java.io.Serializable;

/**
 * Class used for creating a Request Model for Contact list webservice.
 */
public class ContactListRequestModel implements Serializable {

    private String mServiceName;

    public String getServiceName() {
        return mServiceName;
    }

    public void setServiceName(String serviceName) {
        this.mServiceName = serviceName;
    }
}
