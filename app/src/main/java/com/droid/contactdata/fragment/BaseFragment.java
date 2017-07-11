package com.droid.contactdata.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A Base fragment doing some common operation which can be used by every fragment.
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        int id = getFragmentLayout();
        View view = null;
        if (id > 0) {
            view = inflater.inflate(id, container, false);
        }
        return view;
    }

    /**
     * Return the fragment view id else -1.
     * @return
     */
    protected abstract int getFragmentLayout();
}
