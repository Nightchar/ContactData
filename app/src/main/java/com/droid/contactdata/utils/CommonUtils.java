package com.droid.contactdata.utils;

import com.droid.contactdata.service.core.RetrofitClient;

import java.util.Collection;

/**
 * Common util class to handle common operations.
 */
public class CommonUtils {

    /**
     * Method create a Executable service object for a given service interface.
     *
     * @param service
     * @param <T>
     * @return
     */
    public static final <T> T retroFitCreator(final Class<T> service) {
        return RetrofitClient.getInstance().create(service);
    }

    /**
     * Checks whether the list is empty or null.
     *
     * @param collection the passed collection object
     * @return true, if the list is empty or null
     */
    public static boolean isCollectionNullOrEmpty(Collection<?> collection) {

        return (collection == null || collection.isEmpty());
    }


}
