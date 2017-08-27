package com.rnazurebtctest2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.react.bridge.ReactContext;

public class AuthStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_start);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Context context = getApplicationContext();
        Toast.makeText(context, "Testing Native Method", Toast.LENGTH_LONG).show();
    }

}
