## Android SDK Integration Sample

This repository contains integration reference for appICE Android SDK. The sample folder contains sample project for appICE Android SDK integration.

### Sample App Usage

1. Add appICE attributes (APP ID, API KEY, APP KEY) in the Application class.

    ```<manifest package="com.example.gcm" ...>```
   
    ```<application ...>```
   
    ```<meta-data android:name="com.semusi.analytics.appid" android:value="Your_AppID"/>```
   
    ```<meta-data android:name="com.semusi.analytics.appkey" android:value="Your_AppKey"/>```
   
    ```<meta-data android:name="com.semusi.analytics.apikey" android:value="Your_ApiKey"/>```
 
    **// for region value**

    ```<meta-data  android:name="io.appice.analytics.region"  android:value="US" />```
 
    ```</application>```

    ```</manifest>```
 
   NOTE: Login to your appICE account, go to Settings in the left panel of the dashboard. Under App Settings, you will find your APP ID, APP KEY, API KEY.

2. Initialize appICE SDK.

    ```ContextApplication.initSdk(context, mInstance)```
    
    **// creating config for appICE SDK**
    
    ```val config = SdkConfig()```
    
    ```config.setAnalyticsTrackingAllowedState(true)```
    
    **// Init SDK with your config**
    
    ```Api.startContext(context, config)```
      
3. Replace the dummy **google-services.json** file with your actual file.

Thats it!! appICE SDK is successfully integrated in the project and is ready to use.
