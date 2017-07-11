package com.droid.contactdata.service.contact;

import com.droid.contactdata.model.contact.ContactListModel;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Interface for defining Contact list service.
 */
interface ContactServiceInterface {

    @Headers({
            "token: c149c4fac72d3a3678eefab5b0d3a85a",
            "Content-Type:application/x-www-form-urlencoded"
    })
    @POST("{service}")
    Call<ContactListModel> getContacts(@Path("service") String serviceName);
}
