package com.droid.contactdata.model.contact;

import com.google.gson.annotations.SerializedName;

/**
 * Class defines the contact list item model.
 */
public class ContactListItemModel {

    @SerializedName("name")
    private String mName;

    @SerializedName("uid")
    private String mUid;

    private Long mId;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String uid) {
        this.mUid = uid;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        this.mId = id;
    }
}
