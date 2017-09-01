package com.rnazurebtctest2;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.openid.appauth.*;

import android.widget.Toast;
import com.microsoft.windowsazure.mobileservices.*;
import com.google.common.util.concurrent.*;
import com.google.gson.*;
import java.net.MalformedURLException;
import java.util.Collections;

public class AuthCompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_complete);

        AuthorizationResponse resp = AuthorizationResponse.fromIntent(getIntent());
        AuthorizationException ex = AuthorizationException.fromIntent(getIntent());
        if (resp != null) {
            final Activity activity = this;
            Toast.makeText(this, "Auth Succeeded", Toast.LENGTH_SHORT).show();
            MainApplication app = (MainApplication) getApplication();
            AuthorizationService authService = app.authService;
            authService.performTokenRequest(
                resp.createTokenExchangeRequest(),
                new AuthorizationService.TokenResponseCallback() {
                    @Override public void onTokenRequestCompleted(TokenResponse resp, AuthorizationException ex) {
                        if (resp != null) {
                            Toast.makeText(activity, "Token request succeeded", Toast.LENGTH_SHORT).show();
                            try {
                                MobileServiceClient client = new MobileServiceClient("https://api-app-test-1.azurewebsites.net", activity);
                                Toast.makeText(activity, "Invoking Test", Toast.LENGTH_SHORT).show();
                                ListenableFuture<JsonElement> result = client.invokeApi("Test", "GET", Collections.EMPTY_LIST);
                                Futures.addCallback(result, new FutureCallback<JsonElement>() {
                                    @Override
                                    public void onFailure(Throwable exc) {
                                        Toast.makeText(activity, "Test Failure", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onSuccess(JsonElement result) {
                                        Toast.makeText(activity, "Test Success: " + result.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(activity, "Token request failed", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            );
        } else {
            Toast.makeText(this, "Auth Failed", Toast.LENGTH_SHORT).show();
        }
    }

}

//        try {
//            MobileServiceClient client = new MobileServiceClient("https://api-app-test-1.azurewebsites.net", activity);
//            ListenableFuture<JsonElement> test = client.invokeApi("Test", "GET", Collections.EMPTY_LIST);
//            Toast.makeText(this, "Invoked Test", Toast.LENGTH_LONG).show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
