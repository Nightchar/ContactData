package com.droid.contactdata.model.contact;

import com.droid.contactdata.model.BaseModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Class the Contact list model.
 */
public class ContactListModel extends BaseModel {

    @SerializedName("result")
    private List<ContactListItemModel> mContactList;

    public List<ContactListItemModel> getContactList() {
        return mContactList;
    }

    public void setContactList(List<ContactListItemModel> contactList) {
        this.mContactList = contactList;
    }
}
