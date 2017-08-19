package com.rnazurebtctest2;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Map;

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
//        AuthorizationServiceConfiguration serviceConfiguration = new AuthorizationServiceConfiguration(
//                Uri.parse("https://accounts.google.com/o/oauth2/v2/auth") /* auth endpoint */,
//                Uri.parse("https://www.googleapis.com/oauth2/v4/token") /* token endpoint */
//        );
    }
}
