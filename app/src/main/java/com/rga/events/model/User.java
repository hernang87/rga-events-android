package com.rga.events.model;

import com.google.gson.annotations.Expose;

/**
 * Created by pablo on 13/11/16.
 */

public class User extends FBObject {

    @Expose()
    private String name;

    @Expose(serialize = false)
    private String position;

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
