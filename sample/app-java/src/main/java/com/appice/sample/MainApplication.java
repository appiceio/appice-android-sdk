package com.appice.sample;
import android.app.Application;

import semusi.activitysdk.Api;
import semusi.activitysdk.ContextApplication;
import semusi.activitysdk.SdkConfig;


public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextApplication.initSdk(getApplicationContext(), this);

        // creating config for appice sdk
        SdkConfig config = new SdkConfig();
        config.setAnalyticsTrackingAllowedState(true);

        // Init sdk with your config
        Api.startContext(getApplicationContext(), config);
    }
}

