package com.droid.contactdata.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Base model which should be inherited by every parent model which using retrofit to load the data.
 */
public class BaseModel implements Serializable {


    @SerializedName("message")
    protected String mMessage;

    @SerializedName("status")
    protected String mStatus;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        this.mStatus = status;
    }
}


