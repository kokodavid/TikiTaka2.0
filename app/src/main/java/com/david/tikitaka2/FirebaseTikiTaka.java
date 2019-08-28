package com.david.tikitaka2;

import android.app.Application;
import com.firebase.client.Firebase;



public class FirebaseTikiTaka extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    Firebase.setAndroidContext(this);
    }
}
