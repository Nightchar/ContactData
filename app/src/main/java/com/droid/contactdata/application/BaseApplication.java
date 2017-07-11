package com.droid.contactdata.application;

import android.app.Application;
import android.content.Context;

/**
 * A Base application class.
 */

public class BaseApplication extends Application {

    private static Context mContext = null;

    public static Context getAppContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
