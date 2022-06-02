package com.semusi.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.BuildConfig
import com.semusi.AppICEApplication
import com.semusi.PlacesModule2Test.R
import org.json.JSONArray
import org.json.JSONObject
import semusi.activitysdk.Api
import semusi.activitysdk.ContextSdk
import semusi.geofencing.AIGeofenceController
import semusi.geofencing.GeoCallback
import semusi.geofencing.GeofenceClickedCallback
import java.lang.Exception
import java.util.*

class GeofenceActivity : AppCompatActivity() , GeofenceClickedCallback {

    private val tag = GeofenceActivity::class.java.simpleName
    private val requestCode = 34

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)
        AIGeofenceController.getInstance().clickedCallback = this
    }

    override fun onStart() {
        super.onStart()
        checkRunTimePermission()
    }

    private fun checkRunTimePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(
                    this.applicationContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                    this.applicationContext,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                    this.applicationContext,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                setUpAppICEGeofence()
            } else {
                requestPermissions(
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ),
                    10
                )
            }
        } else {
            setUpAppICEGeofence()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setUpAppICEGeofence()
            } else {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(
                        this@GeofenceActivity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                ) {
                    // If User Checked 'Don't Show Again' checkbox for runtime permission, then navigate user to Settings
                    val dialog: AlertDialog.Builder = AlertDialog.Builder(applicationContext)
                    dialog.setTitle("Permission Required")
                    dialog.setCancelable(false)
                    dialog.setMessage("You have to Allow permission to access user location")
                    dialog.setPositiveButton("setting", DialogInterface.OnClickListener { _, _ ->
                        run {
                            val i = Intent(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts(
                                    "package",
                                    applicationContext.packageName, null
                                )
                            )
                            //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivityForResult(i, 1001)
                        }
                    })
                    val alertDialog: AlertDialog = dialog.create()
                    alertDialog.show()
                }
                //code for deny
            }
        }
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        when (requestCode) {
            1001 -> {
                if (ActivityCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    setUpAppICEGeofence()
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        requestPermissions(
                            arrayOf(
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_BACKGROUND_LOCATION
                            ), 10
                        )
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(
                                arrayOf(
                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                ), 10
                            )
                        }
                    }
                }

            }
        }
    }

    private fun setUpAppICEGeofence() {
        println("appICEGeofence : setUpAppICEGeofence")
        AIGeofenceController.getInstance().setGeoCallbackListener(object : GeoCallback {
            override fun onGeoEnter(data: JSONObject, context: Context) {
                println("AIGeofence test : $data")
                try {
                    val jsonObject = data.optJSONObject("data")
                    println("geoEnter : message : $jsonObject")
                    if (jsonObject != null) {
                        val message = jsonObject.optJSONObject("message")
                        if (message != null) {
                            ContextSdk.handleAppICEPush(message.toString(), null, null, context)
                        }
                    }
                } catch (e: Exception) {
                }
            }

            override fun onGeoExit(data: JSONObject, context: Context) {
                try {
                    val jsonObject = data.optJSONObject("data")
                    if (jsonObject != null) {
                        val message = jsonObject.optJSONObject("message")
                        if (message != null) {
                            ContextSdk.handleAppICEPush(message.toString(), null, null, context)
                        }
                    }
                } catch (e: Exception) {
                }
            }
        }).setUpGeoCallback(applicationContext)
    }

    //====================================
    // permission request and result
    //====================================
    private fun checkPermissions(context: Context?): Boolean {
        val permissionState = ActivityCompat.checkSelfPermission(context!!,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        val shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (shouldProvideRationale) {
            permissionEntry()
        } else {
            permissionEntry()
        }
    }

    private fun permissionEntry() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(
                this@GeofenceActivity, arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ),
                requestCode
            )
        } else {
            Log.i(tag, "Displaying permission rationale to provide additional context.")
            ActivityCompat.requestPermissions(
                this@GeofenceActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                requestCode
            )
        }
    }

    override fun onNotificationClickedPayloadReceived(`object`: JSONObject, context: Context?) {
        println("test : someValue $`object`")
    }
}