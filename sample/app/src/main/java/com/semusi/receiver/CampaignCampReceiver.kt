package com.semusi.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import org.json.JSONException
import org.json.JSONObject
import semusi.activitysdk.ContextSdk
import java.lang.Exception
import java.net.URLDecoder

class CampaignCampReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent) {
        val extras = intent.extras
        if (extras != null) {
            ContextSdk.pushNotificationClicked(extras, context)
            val appICENotification = ContextSdk.appICEPushContent(extras)
        }
    }

    fun sendCallback(extra: Bundle, context: Context?) {
        try {
            val tap = extra.getString("tap")
            val url = extra.getString("url")
            val cdata = extra.getString("cdata")
            val json = JSONObject()
            try {
                if (url != null && url.isNotEmpty()) {
                    val uri = Uri.parse(url)
                    val deeplinkData = ContextSdk.gatherDeepLinkData(uri)
                    val keys: Set<String> = deeplinkData.keys
                    for (key: String in keys) {
                        try {
                            val data = deeplinkData[key]
                            json.put(key, data)
                        } catch (e: JSONException) {
                        }
                    }
                }
            } catch (e: Exception) {
            }

            // Gather extra data from json object root
            try {
                if (cdata != null && cdata.isNotEmpty()) {
                    val root: JSONObject = JSONObject(cdata)
                    if (root.length() > 0) {
                        val it = root.keys()
                        while (it.hasNext()) {
                            val key = it.next()
                            val obj = root[key]
                            if ((obj.javaClass == String::class.java)) json.put(
                                key,
                                URLDecoder.decode(obj as String, "UTF-8")
                            ) else json.put(key, obj)
                        }
                    }
                }
            } catch (e: Exception) {
            }
            json.put("tap", tap)
            try {
                if (url != null && url.isNotEmpty()) {
                    val path = Uri.parse(url)
                    json.put("host", path.host)
                    json.put("path", path.path)
                }
            } catch (e: Exception) {
            }
            println("notification json data : $json")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}