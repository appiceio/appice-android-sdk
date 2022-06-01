package com.semusi.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.semusi.PlacesModule2Test.R
import org.json.JSONArray
import org.json.JSONObject
import semusi.activitysdk.ContextSdk
import java.util.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)
        setupData()
    }

    private fun setupData() {
        setupButtons()
    }

    @SuppressLint("SetTextI18n", "CutPasteId")
    private fun setupButtons() {
        val e1 = findViewById<View>(R.id.event1) as Button
        e1.setOnClickListener {
            val arr = arrayOf(
                "38433079e-d371-4c25-a8d0-8fd2c65190b8"
            )
            ContextSdk.setUser(arr, applicationContext)
            val map = HashMap<String, Any>()
            map["state"] = true
            map["id"] = "aman"
            ContextSdk.tagEventObj(
                "Login",
                map,
                this@MainActivity.applicationContext
            )
        }

        // Product-list
        val e2 = findViewById<View>(R.id.event2) as Button
        e2.setOnClickListener {
            ContextSdk.setCustomVariable(
                "language",
                "En",
                this@MainActivity.applicationContext
            )
        }

        // Product-view
        val e3 = findViewById<View>(R.id.event3) as Button
        e3.setOnClickListener {
            ContextSdk.setUser(
                arrayOf("abccdef=="),
                this@MainActivity.applicationContext
            )
        }

        // Add-to-cart
        val e4 = findViewById<View>(R.id.event4) as Button
        e4.setOnClickListener {
            try {
                val intent = Intent(this@MainActivity, InboxActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }

        // Proceed-success
        val e5 = findViewById<View>(R.id.event5) as Button
        e5.setOnClickListener {
            try {
                val ob1 = JSONObject()
                ob1.put("id", 102)
                ob1.put("category", "fashion")
                ob1.put("price", "1452")
                val ob2 = JSONObject()
                ob2.put("id", 217)
                ob2.put("category", "cosmetic")
                ob2.put("price", "1233")
                val arr = JSONArray()
                arr.put(ob1)
                arr.put(ob2)
                val cart = JSONObject()
                cart.put("total", 3726)
                cart.put("list", arr)
                val map = HashMap<String, Any>()
                map["state"] = true
                map["cart"] = cart
                ContextSdk.tagEventObj(
                    "FD_Cancel",
                    map,
                    this@MainActivity.applicationContext
                )
            } catch (e: Exception) {
            }
        }
    }
}