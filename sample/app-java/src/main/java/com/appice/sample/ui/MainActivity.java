package com.appice.sample.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.appice.sample.R;

import java.util.HashMap;
import semusi.activitysdk.ContextSdk;


public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        setupButtons();
    }

    private void setupButtons() {
        Button e1 = findViewById(R.id.event1);
        e1.setOnClickListener(view -> {
                HashMap<String, Object> map = new HashMap<>();
                map.put("state", true);
                map.put("id", "aman");
                ContextSdk.tagEventObj(
                        "Login",
                        map,
                        getApplicationContext()
                );
            });
        // Product-list
        Button e2 = findViewById(R.id.event2);
        e2.setOnClickListener(view -> ContextSdk.setCustomVariable(
                "language",
                "En",
                getApplicationContext()
        ));

        // Product-view
        Button e3 = findViewById(R.id.event3);
        e3.setOnClickListener(view -> ContextSdk.setUser(
                new String[]{"abccdef=="},
                getApplicationContext()
        ));

        // Add-to-cart
        Button e4 = findViewById(R.id.event4);
        e4.setOnClickListener(view -> {
            try {
                Intent intent = new Intent(MainActivity.this, InboxActivity.class);
                startActivity(intent);
            } catch (Exception e) {
            }
        });

        // Proceed-success
        Button e5 = findViewById(R.id.event5);
        e5.setOnClickListener(view -> {

        });
    }

}
