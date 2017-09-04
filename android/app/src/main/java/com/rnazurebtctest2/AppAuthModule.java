package com.rnazurebtctest2;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;

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
        ReactContext context = getReactApplicationContext();
        //Activity currentActivity = getCurrentActivity();
        Intent authStartIntent = new Intent(context, AuthStartActivity.class);
        authStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(authStartIntent);
    }
}
