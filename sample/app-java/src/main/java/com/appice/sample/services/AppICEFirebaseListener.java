package com.appice.sample.services;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import semusi.activitysdk.ContextSdk;

public class AppICEFirebaseListener extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        System.out.println("onMessage : "+remoteMessage.getData());
        boolean isAppICEPush=  ContextSdk.isAppICENotification(remoteMessage);
        if (isAppICEPush){
            ContextSdk.handleAppICEPush(remoteMessage, getApplicationContext());
        }
    }
}
