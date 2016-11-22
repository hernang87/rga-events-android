package com.rga.events.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.rga.events.R;
import com.rga.events.model.Event;
import com.rga.events.model.User;

import java.util.Date;

public class EventActivity extends AppCompatActivity {

    private Event mEvent;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // TODO: Get the event from the backend
        mEvent = new Event();
        mEvent.setId("-KUwGD8RtJtnaHNvaUGQ");
        mEvent.setTitle("Voley");
        mEvent.setDescription("Todos son bienvenidos");
        mEvent.setComments("");
        mEvent.setDate(new Date());
        mEvent.setDuration(120);
        mEvent.setCost("600");
        mEvent.setPlace("Club Eros");
        mEvent.setMaxUsers(12);
        mEvent.setRecurrent(true);

        // TODO: use the user that corresponds to you and is saved in the App
        mUser = new User();
        mUser.setId("qxy0eMSUcrWjdCloVQZJTWwycAF3");
        mUser.setName("Pablo Roulet");

        getSupportActionBar().setTitle(mEvent.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();

        /*
        // Example of how to use the EventService to make calls
        EventService.getInstance().getEvent("-KUwGD8RtJtnaHNvaUGQ", new EventService.ServiceResponse<Event>() {
            @Override
            public void onSuccess(Event event) {
                Log.d("EventActivity", event.getTitle());
            }

            @Override
            public void onError(Throwable t) {

            }
        });
        */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
