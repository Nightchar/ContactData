package com.droid.contactdata.model.contact.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * This model work as an entity in db, when using with GreenDao Database.
 */
@Entity
public class Contact {

    @Id
    private Long id;

    @NotNull
    private String uid;
    private String name;
    private boolean isDeleted;

    @Generated(hash = 253097159)
    public Contact(Long id, @NotNull String uid, String name, boolean isDeleted) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.isDeleted = isDeleted;
    }
    @Generated(hash = 672515148)
    public Contact() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean getIsDeleted() {
        return this.isDeleted;
    }
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
