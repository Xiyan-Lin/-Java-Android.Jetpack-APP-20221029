package com.example.app_fcm;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFCMService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        String title, body;
        title = message.getNotification().getTitle();
        body = message.getNotification().getBody();
        Log.i("fcm", "title: " + title);
        Log.i("fcm", "body: " + body);
        super.onMessageReceived(message);
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.i("fcm", "onNewToken: " + token);
    }
}
