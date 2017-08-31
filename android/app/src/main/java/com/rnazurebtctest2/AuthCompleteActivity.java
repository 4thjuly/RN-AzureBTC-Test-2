package com.rnazurebtctest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.openid.appauth.*;

import android.widget.Toast;

public class AuthCompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_complete);

        AuthorizationResponse resp = AuthorizationResponse.fromIntent(getIntent());
        AuthorizationException ex = AuthorizationException.fromIntent(getIntent());
        if (resp != null) {
            Toast.makeText(this, "Auth Succeeded", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Auth Failed", Toast.LENGTH_LONG).show();
        }
    }


}
