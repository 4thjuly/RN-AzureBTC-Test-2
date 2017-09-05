package com.rnazurebtctest2;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;

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
    public void login() {
        ReactContext context = getReactApplicationContext();
        Intent authStartIntent = new Intent(context, AuthStartActivity.class);
        authStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(authStartIntent);
    }

    @ReactMethod
    public void logout() {
        Activity activity = getCurrentActivity();
        try {
            MobileServiceClient client = new MobileServiceClient("https://api-app-test-1.azurewebsites.net", activity);
            client.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
