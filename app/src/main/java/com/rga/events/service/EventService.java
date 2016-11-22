package com.rga.events.service;

import android.accounts.NetworkErrorException;

import com.google.gson.GsonBuilder;
import com.rga.events.model.Event;
import com.rga.events.model.FBObject;
import com.rga.events.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pablo on 14/11/16.
 */

public class EventService {

    private static final String ENDPOINT = "https://rga-events.firebaseio.com/";

    private static EventService mInstance;
    private EventApi mApi;

    public static EventService getInstance(){
        if (mInstance == null)
            mInstance = new EventService();

        return mInstance;
    }

    private EventService(){
        GsonConverterFactory gsonFactory = GsonConverterFactory.create(
                new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .registerTypeAdapter(User.class, new UserSerializer())
                        .create());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(gsonFactory)
                .build();

        mApi = retrofit.create(EventApi.class);
    }

    public void listEvents(final ServiceResponse<List<Event>> listener){
        mApi.fetchEvents().enqueue(new Callback<Map<String, Event>>() {
            @Override
            public void onResponse(Call<Map<String, Event>> call, Response<Map<String, Event>> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(mapToList(response.body()));
                } else {
                    listener.onError(createNetworkError(response));
                }
            }

            @Override
            public void onFailure(Call<Map<String, Event>> call, Throwable t) {
                listener.onError(t);
            }
        });
    }

    public void getEvent(final String eventId, final ServiceResponse<Event> listener){
        mApi.fetchEvent(eventId).enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if (response.isSuccessful()) {
                    Event event = response.body();
                    event.setId(eventId);
                    listener.onSuccess(event);
                } else {
                    listener.onError(createNetworkError(response));
                }
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                listener.onError(t);
            }
        });
    }

    public void listEventUsers(Event event, final ServiceResponse<List<User>> listener){
        mApi.fetchEventUsers(event.getId()).enqueue(new Callback<Map<String, User>>() {
            @Override
            public void onResponse(Call<Map<String, User>> call, Response<Map<String, User>> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(mapToList(response.body()));
                } else {
                    listener.onError(createNetworkError(response));
                }
            }

            @Override
            public void onFailure(Call<Map<String, User>> call, Throwable t) {
                listener.onError(t);
            }
        });
    }

    public void subscribeUserToEvent(Event event, User user, final ServiceResponse<User> listener){
        mApi.addUserToEvent(event.getId(), user.getId(), user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onError(createNetworkError(response));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onError(t);
            }
        });
    }

    public void unsubscribeUserFromEvent(Event event, User user, final ServiceResponse<Void> listener){
        mApi.deleteUserFromEvent(event.getId(), user.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onError(createNetworkError(response));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onError(t);
            }
        });
    }

    private <T extends FBObject> List<T> mapToList(Map<String, T> map){
        List<T> objects = new ArrayList<>();

        if (map != null) {
            for (Map.Entry<String, T> entry : map.entrySet()) {
                T object = entry.getValue();
                object.setId(entry.getKey());

                objects.add(object);
            }
        }

        return objects;
    }

    private static NetworkErrorException createNetworkError(Response response){
        return new NetworkErrorException(response.errorBody().toString());
    }

    public interface ServiceResponse<T> {
        void onSuccess(T response);
        void onError(Throwable t);
    }
}
