package com.rnazurebtctest2;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import net.openid.appauth.*;
import java.util.Map;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class AppAuthModule extends ReactContextBaseJavaModule {

    public AppAuthModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "AppAuthAndroid";
    }

    @ReactMethod
    public void configureService() {
        String authEndpoint = "https://login.microsoftonline.com/IanETB2CTenant.onmicrosoft.com/oauth2/v2.0/authorize?p=B2C_1_SUSI";
        String tokenEndpoint = "https://login.microsoftonline.com/IanETB2CTenant.onmicrosoft.com/oauth2/v2.0/token?p=B2C_1_SUSI";
        String clientId = "62386987-856b-4e6e-89db-59eef6d603b6";
        Uri redirectUri = Uri.parse("com.onmicrosoft.ianetb2ctenant.rnazureb2ctest2://oauth/redirect");
        ReactContext context = getReactApplicationContext();

        Toast.makeText(context, "Testing Native Method", Toast.LENGTH_LONG).show();
//        AuthorizationServiceConfiguration config = new AuthorizationServiceConfiguration(Uri.parse(authEndpoint), Uri.parse(tokenEndpoint));
//        AuthorizationRequest req = new AuthorizationRequest.Builder(config, clientId, "code", redirectUri).build();
//
//            AuthorizationService authService = new AuthorizationService(context);

//        authService.performAuthorizationRequest(req, PendingIntent.getActivity(context, 0, new Intent(context, AuthCompleteActivity.class), 0));
    }
}
