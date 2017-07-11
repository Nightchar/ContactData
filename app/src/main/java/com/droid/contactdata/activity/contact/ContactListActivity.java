package com.droid.contactdata.activity.contact;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.droid.contactdata.R;
import com.droid.contactdata.activity.BaseActivity;
import com.droid.contactdata.common.CommonConstants;
import com.droid.contactdata.database.GreenDaoClient;
import com.droid.contactdata.fragment.contact.ServiceFragment;
import com.droid.contactdata.helper.contact.ContactListAdapter;
import com.droid.contactdata.helper.contact.ContactListResponseReceiver;
import com.droid.contactdata.model.BaseModel;
import com.droid.contactdata.model.contact.ContactListItemModel;
import com.droid.contactdata.model.contact.ContactListModel;
import com.droid.contactdata.model.contact.dao.Contact;
import com.droid.contactdata.model.contact.dao.ContactDao;
import com.droid.contactdata.utils.CommonUtils;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import static com.droid.contactdata.activity.contact.ContactListHelper.getContactRequestModel;


public class ContactListActivity extends BaseActivity implements ContactListResponseReceiver, ContactListAdapter
        .ContactClickListener {

    // Filtered contact list which needs to show in UI.
    private List<ContactListItemModel> mContactListItemModels = null;
    private View mProgressView;
    private RecyclerView mRecyclerView;
    private ContactListAdapter contactListAdapter;
    private ContactDao mContactDao;
    private Query<Contact> mContactListQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        mProgressView = findViewById(R.id.view_loading_progress);

        // set up recycle view
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view_contacts);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactListAdapter = new ContactListAdapter(this);
        mRecyclerView.setAdapter(contactListAdapter);

        // Initiate Contact Database
        mContactDao = GreenDaoClient.getDaoSession().getContactDao();
        mContactListQuery = mContactDao.queryBuilder().build();

        loadContactsList();
    }

    // check data in db very first time, filter the visible data and then display it. if not available available load
    // it.
    private void loadContactsList() {
        boolean hitRequired = false;
        List<Contact> contactList = mContactListQuery.list();
        if (!CommonUtils.isCollectionNullOrEmpty(contactList)) {
            mContactListItemModels = ContactListHelper.getVisibleContactList(contactList);
            if (!CommonUtils.isCollectionNullOrEmpty(mContactListItemModels)) {
                loadDataInUI(mContactListItemModels);
            } else {
                hitRequired = true;
            }
        } else {
            hitRequired = true;
        }
        if (hitRequired) {
            Bundle bundle = new Bundle();

            bundle.putSerializable(CommonConstants.CONTACT_LIST_REQUEST_MODEL, getContactRequestModel(this));
            ServiceFragment serviceFragment = new ServiceFragment();
            serviceFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().add(serviceFragment,
                    ServiceFragment.SERVICE_FRAGMENT).commit();
        }
    }

    @Override
    public void onReceiveContactData(BaseModel resultData) {
        if (resultData instanceof ContactListModel) {
            // save data in db
            ContactListHelper.insertContactInDb((ContactListModel) resultData, mContactDao);
            refreshContactList();
        }
        removeServiceFragment();
    }

    @Override
    public void onReceiveContactError(String errorMessage) {
        showError(errorMessage);
        removeServiceFragment();
    }

    @Override
    public void onContactClick(int position) {
        ContactListItemModel contactListItemModel = contactListAdapter.getContact(position);
        mContactDao.update(ContactListHelper.updatedContact(contactListItemModel));
        refreshContactList();
    }

    private void loadDataInUI(List<ContactListItemModel> contactListItemModels) {
        if (CommonUtils.isCollectionNullOrEmpty(contactListItemModels)) {
            showNoDataAvailable(getResources().getString(R.string.no_data_available));
        } else {
            mProgressView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            contactListAdapter.setContacts(contactListItemModels);
        }
    }

    // Show
    private void showNoDataAvailable(String message) {
        showError(message);
    }

    // Show error in UI.
    private void showError(String message) {
        mRecyclerView.setVisibility(View.GONE);
        mProgressView.setVisibility(View.VISIBLE);
        mProgressView.findViewById(R.id.progressBar).setVisibility(View.GONE);
        ((TextView) mProgressView.findViewById(R.id.tv_loading)).setText(message);
    }

    private void removeServiceFragment() {
        Fragment serviceFragment = getSupportFragmentManager().findFragmentByTag(ServiceFragment.SERVICE_FRAGMENT);
        if (serviceFragment != null) {
            getSupportFragmentManager().beginTransaction().remove(serviceFragment).commit();
        }
    }

    // fetch the data from db and filter it with visible item
    private void refreshContactList() {
        List<Contact> contactList = mContactListQuery.list();
        if (!CommonUtils.isCollectionNullOrEmpty(contactList)) {
            mContactListItemModels = ContactListHelper.getVisibleContactList(contactList);
            if (!CommonUtils.isCollectionNullOrEmpty(mContactListItemModels)) {
                loadDataInUI(mContactListItemModels);
            } else {
                showNoDataAvailable(getResources().getString(R.string.no_data_available));
            }
        } else {
            showNoDataAvailable(getResources().getString(R.string.no_data_available));
        }
    }
}
