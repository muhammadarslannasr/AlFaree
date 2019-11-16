package com.thexsolution.propertyprojectf11.Util;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage message) {
        if (!message.getData().isEmpty()) {
            sendMyNotification(message);
        }
    }

    private void sendMyNotification(RemoteMessage remoteMessage) {
        Log.d("notification_is_calling", "calling");
    }
}