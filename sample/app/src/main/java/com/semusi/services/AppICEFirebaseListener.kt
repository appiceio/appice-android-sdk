package com.semusi.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import semusi.activitysdk.ContextSdk

class AppICEFirebaseListener : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        println("onMessage : " + remoteMessage.data)
        val isAppICEPush = ContextSdk.isAppICENotification(remoteMessage)
        if (isAppICEPush) {
            ContextSdk.handleAppICEPush(remoteMessage, this.applicationContext)
        }
    }
}