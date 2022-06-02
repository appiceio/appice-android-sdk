package com.appice.sample.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import semusi.activitysdk.ContextSdk;

public class CampaignCampReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            System.out.println("appICE : from appice " + extras.getString("ai_content"));
            ContextSdk.pushNotificationClicked(extras, context);
        }
    }
}
