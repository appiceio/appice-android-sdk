# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-dontwarn com.clevertap.android.sdk.**

-keep class com.urbanairship.** { *; }
-dontwarn com.urbanairship.**

-keep public class com.google.android.gms.**
-dontwarn com.google.android.gms.**

-keep public class android.support.**
-dontwarn android.support.**

-dontwarn com.google.firebase.messaging.**

-keep class me.pushy.sdk.** { *; }
-dontwarn me.pushy.sdk.**

-keep class org.eclipse.paho.client.mqttv3.** { *; }
-dontwarn org.eclipse.paho.client.mqttv3.**

-keep class com.fasterxml.jackson.** { *; }
-dontwarn com.fasterxml.jackson.**

-keep class semusi.** { *; }
-dontwarn semusi.**

-keep public class com.google.** {*;}

-dontwarn okio.**
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *; }
-keep interface com.squareup.okhttp3.* { *; }

-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.* { *; }

-dontwarn javax.annotation.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-dontwarn org.codehaus.mojo.animal_sniffer.*
-dontwarn okhttp3.internal.platform.ConscryptPlatform

-keepattributes Signature
-keepattributes Annotation
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-keep class com.example.datamodel.** { *; }

-dontwarn rx.**

-dontwarn okio.**

-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }

-dontwarn retrofit.**
-dontwarn retrofit.appengine.UrlFetchClient
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

-keepattributes Signature
-keepattributes *Annotation*

-dontwarn org.xmlpull.v1.**
-dontwarn com.squareup.**
-keep class com.squareup.** { *; }

-dontwarn io.branch.**
-keep class io.branch.** { *; }
-ignorewarnings


