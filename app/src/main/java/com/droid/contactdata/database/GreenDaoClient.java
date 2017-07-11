package com.droid.contactdata.database;

import android.content.Context;

import com.droid.contactdata.R;
import com.droid.contactdata.application.BaseApplication;
import com.droid.contactdata.model.contact.dao.DaoMaster;
import com.droid.contactdata.model.contact.dao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * A singleton class to generate GreenDao client with desired configuration.
 */
public final class GreenDaoClient {

    private static DaoSession daoSession = null;

    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            synchronized (DaoSession.class) {
                if (daoSession == null) {
                    Context context = BaseApplication.getAppContext();
                    DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,
                            context.getResources().getString(R.string.database_name));
                    Database db = helper.getWritableDb();
                    daoSession = new DaoMaster(db).newSession();
                }
            }
        }
        return daoSession;
    }
}
