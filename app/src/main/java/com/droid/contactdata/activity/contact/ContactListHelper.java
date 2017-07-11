package com.droid.contactdata.activity.contact;

import android.content.Context;

import com.droid.contactdata.R;
import com.droid.contactdata.model.contact.ContactListItemModel;
import com.droid.contactdata.model.contact.ContactListModel;
import com.droid.contactdata.model.contact.ContactListRequestModel;
import com.droid.contactdata.model.contact.dao.Contact;
import com.droid.contactdata.model.contact.dao.ContactDao;
import com.droid.contactdata.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A helper class to do some operations for ContactListActivity
 */

class ContactListHelper {

    /**
     * Convert List<Contact> to List<ContactListItemModel> then Filter the visible items.
     *
     * @param contactList the DB Contact list
     * @return return ContactListItemModel item list
     */
    static List<ContactListItemModel> getVisibleContactList(List<Contact> contactList) {
        List<ContactListItemModel> contactListItemModels = new ArrayList<>();

        if (!CommonUtils.isCollectionNullOrEmpty(contactList)) {
            for (Contact contact : contactList) {
                if (!contact.getIsDeleted()) {
                    ContactListItemModel contactListItemModel = new ContactListItemModel();
                    contactListItemModel.setName(contact.getName());
                    contactListItemModel.setUid(contact.getUid());
                    contactListItemModel.setId(contact.getId());
                    contactListItemModels.add(contactListItemModel);
                }
            }
        }
        return contactListItemModels;
    }

    /**
     * Create Contact object from ContactListItemModel with some property as default.
     *
     * @param contactListItemModel
     * @return
     */
    static Contact createFreshContact(ContactListItemModel contactListItemModel) {
        Contact contact = new Contact();
        contact.setName(contactListItemModel.getName());
        contact.setUid(contactListItemModel.getUid());
        contact.setIsDeleted(false);
        return contact;
    }

    /**
     * Create a Contact object similar to the ContactListItemModel.
     *
     * @param contactListItemModel
     * @return
     */
    static Contact updatedContact(ContactListItemModel contactListItemModel) {
        Contact contact = new Contact();
        contact.setUid(contactListItemModel.getUid());
        contact.setName(contactListItemModel.getName());
        contact.setId(contactListItemModel.getId());
        contact.setIsDeleted(true);
        return contact;
    }

    /**
     * Method used to get request model for Contact list.
     *
     * @param context
     * @return
     */
    static ContactListRequestModel getContactRequestModel(Context context) {
        ContactListRequestModel contactListRequestModel = new ContactListRequestModel();
        contactListRequestModel.setServiceName(context.getResources().getString(R.string.contact_service_name));
        return contactListRequestModel;
    }

    /**
     * Method used to perform insertion operation in database.
     *
     * @param contactListModel
     * @param contactDao
     */
    static void insertContactInDb(ContactListModel contactListModel, ContactDao contactDao) {
        if (contactDao != null) {
            contactDao.deleteAll();
            if (contactListModel != null && !CommonUtils.isCollectionNullOrEmpty(contactListModel.getContactList())) {
                List<ContactListItemModel> contactList = contactListModel.getContactList();
                for (ContactListItemModel contactListItemModel : contactList) {
                    Contact contact = ContactListHelper.createFreshContact(contactListItemModel);
                    contactDao.insert(contact);
                }
            }
        }
    }
}
