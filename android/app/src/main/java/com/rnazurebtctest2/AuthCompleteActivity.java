package com.rnazurebtctest2;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.openid.appauth.*;

import android.widget.Toast;
import com.microsoft.windowsazure.mobileservices.*;
import com.google.common.util.concurrent.*;
import com.google.gson.*;
import com.microsoft.windowsazure.mobileservices.authentication.MobileServiceUser;

import java.net.MalformedURLException;
import java.util.Collections;

public class AuthCompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_complete);
        MainApplication app = (MainApplication) getApplication();
        final AuthState authState = new AuthState(app.getAuthConfig());
        AuthorizationResponse resp = AuthorizationResponse.fromIntent(getIntent());
        AuthorizationException ex = AuthorizationException.fromIntent(getIntent());
        Uri redirectUri = getIntent().getData();

        if (resp != null) {
            final Activity activity = this;
            Toast.makeText(this, "Auth Succeeded", Toast.LENGTH_SHORT).show();
            final AuthorizationService authService = app.authService;
            authState.update(resp, ex);
            authService.performTokenRequest(
                resp.createTokenExchangeRequest(),
                new AuthorizationService.TokenResponseCallback() {
                    @Override public void onTokenRequestCompleted(TokenResponse resp, AuthorizationException ex) {
                        if (resp != null) {
                            authState.update(resp, ex);
                            Toast.makeText(activity, "Token request succeeded", Toast.LENGTH_SHORT).show();
                            authState.performActionWithFreshTokens(authService, new AuthState.AuthStateAction() {
                                @Override public void execute(String accessToken, String idToken, AuthorizationException ex) {
                                    if (ex != null) {
                                        Toast.makeText(activity, "Token refresh failed", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                    // Use the access token to do something ...
                                    Toast.makeText(activity, "Token refresh succeeded", Toast.LENGTH_SHORT).show();
                                    try {
                                        MobileServiceClient client = new MobileServiceClient("https://api-app-test-1.azurewebsites.net", activity);
                                        MobileServiceUser user = new MobileServiceUser(idToken);
                                        user.setAuthenticationToken(accessToken);
                                        client.setCurrentUser(user);
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
                                }
                            });
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
