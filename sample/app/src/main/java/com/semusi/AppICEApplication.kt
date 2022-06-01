package com.semusi

import android.app.Application
import android.content.Context
import com.bugfender.android.BuildConfig
import com.bugfender.sdk.Bugfender
import com.semusi.AppICEApplication.AppICE.startAppICESdk
import com.semusi.PlacesModule2Test.R
import semusi.activitysdk.Api
import semusi.activitysdk.AppICEProcessLIfeCycle
import semusi.activitysdk.ContextApplication
import semusi.activitysdk.SdkConfig
import semusi.geofencing.AIGeofenceController

class AppICEApplication : AppICEProcessLIfeCycle() {
    override fun onCreate() {
        super.onCreate()

        Bugfender.init(this, getString(R.string.bugfender_key), BuildConfig.DEBUG);
        Bugfender.enableCrashReporting();
        Bugfender.enableUIEventLogging(this);
        Bugfender.enableLogcatLogging();

        AppICE.mInstance = this
        AIGeofenceController.getInstance()
            .setClickedCallback { data, _ -> println("test : someValue $data") }
        startAppICESdk(applicationContext)
    }

    object AppICE{
        lateinit var mInstance: Application
        fun startAppICESdk(context:Context){
            ContextApplication.initSdk(context, mInstance)

            // creating config for appICE sdk
            val config = SdkConfig()
            config.setAnalyticsTrackingAllowedState(true)
            // Init sdk with your config
            Api.startContext(context, config)

        }
    }
}