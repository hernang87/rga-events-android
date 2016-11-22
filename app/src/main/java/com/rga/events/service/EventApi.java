package com.rga.events.service;

import com.rga.events.model.Event;
import com.rga.events.model.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by pablo on 13/11/16.
 */

public interface EventApi {

    @GET("events.json")
    Call<Map<String, Event>> fetchEvents();

    @GET("events/{eventId}.json")
    Call<Event> fetchEvent(@Path("eventId") String eventId);

    @GET("events-users/{eventId}.json")
    Call<Map<String, User>> fetchEventUsers(@Path("eventId") String eventId);

    @PUT("events-users/{eventId}/{userId}.json")
    Call<User> addUserToEvent(@Path("eventId") String eventId, @Path("userId") String userId, @Body User user);

    @DELETE("events-users/{eventId}/{userId}.json")
    Call<Void> deleteUserFromEvent(@Path("eventId") String eventId, @Path("userId") String userId);
}
