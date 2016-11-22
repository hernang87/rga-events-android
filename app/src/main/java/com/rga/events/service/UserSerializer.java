package com.rga.events.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rga.events.model.User;

import java.lang.reflect.Type;

/**
 * Created by pablo on 15/11/16.
 */

public class UserSerializer implements JsonSerializer<User> {
    @Override
    public JsonElement serialize(User src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject serverTimestamp = new JsonObject();
        serverTimestamp.addProperty(".sv", "timestamp");

        JsonObject object = new JsonObject();
        object.addProperty("name", src.getName());
        object.add("position", serverTimestamp);

        return object;
    }
}
