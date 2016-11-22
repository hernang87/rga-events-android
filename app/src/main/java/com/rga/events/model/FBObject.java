package com.rga.events.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by pablo on 14/11/16.
 */

public class FBObject  implements Serializable {

    @Expose(serialize = false, deserialize = false)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (!(obj instanceof FBObject)) return false;
        if (obj == this) return true;

        FBObject fbObject = (FBObject)obj;
        return (fbObject.id.equals(this.id));
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
