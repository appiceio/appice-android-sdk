package com.semusi.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import semusi.activitysdk.ContextSdk


class CampaignCampReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent) {
        val extras = intent.extras
        if (extras != null) {
            System.out.println("appICE : from appice " + extras.getString("ai_content"))
            ContextSdk.pushNotificationClicked(extras, context)
        }
    }
}