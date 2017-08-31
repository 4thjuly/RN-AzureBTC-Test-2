package com.rnazurebtctest2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactApplicationContext;

import net.openid.appauth.*;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;

public class AuthStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_start);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Testing Native Method", Toast.LENGTH_LONG).show();

        String authEndpoint = "https://login.microsoftonline.com/IanETB2CTenant.onmicrosoft.com/oauth2/v2.0/authorize?p=B2C_1_SUSI";
        String tokenEndpoint = "https://login.microsoftonline.com/IanETB2CTenant.onmicrosoft.com/oauth2/v2.0/token?p=B2C_1_SUSI";
        String clientId = "62386987-856b-4e6e-89db-59eef6d603b6";
        Uri redirectUri = Uri.parse("com.onmicrosoft.ianetb2ctenant.rnazureb2ctest2://oauth/redirect");

        AuthorizationServiceConfiguration config = new AuthorizationServiceConfiguration(Uri.parse(authEndpoint), Uri.parse(tokenEndpoint));
        AuthorizationRequest req = new AuthorizationRequest.Builder(config, clientId, "code", redirectUri).build();
        AuthorizationService authService = new AuthorizationService(this);

        Intent completeIntent = new Intent(this, AuthCompleteActivity.class);
        authService.performAuthorizationRequest(req, PendingIntent.getActivity(this, 0, completeIntent, 0));
    }

}
