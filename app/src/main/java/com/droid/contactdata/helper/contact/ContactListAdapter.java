package com.droid.contactdata.helper.contact;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.droid.contactdata.R;
import com.droid.contactdata.model.contact.ContactListItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Recycle view adapter class for contact list.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {


    private List<ContactListItemModel> mContactList;
    private ContactClickListener mClickListener;


    public ContactListAdapter(ContactClickListener clickListener) {
        mClickListener = clickListener;
        mContactList = new ArrayList<>();
    }

    public void setContacts(List<ContactListItemModel> contactList) {
        mContactList = contactList;
        notifyDataSetChanged();
    }

    public ContactListItemModel getContact(int position) {
        return mContactList.get(position);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        ContactListItemModel contactListItemModel = mContactList.get(position);
        holder.mTvContactName.setText(contactListItemModel.getName());
        holder.mTvContactUid.setText(contactListItemModel.getUid());
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public interface ContactClickListener {
        void onContactClick(int position);
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView mTvContactName;
        TextView mTvContactUid;

        ContactViewHolder(View itemView, final ContactClickListener clickListener) {
            super(itemView);
            mTvContactName = (TextView) itemView.findViewById(R.id.tv_contact_name);
            mTvContactUid = (TextView) itemView.findViewById(R.id.tv_contact_uid);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onContactClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
