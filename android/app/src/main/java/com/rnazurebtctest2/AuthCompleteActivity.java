package com.rnazurebtctest2;

import android.app.Activity;
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
            final Activity activity = this;
            Toast.makeText(this, "Auth Succeeded", Toast.LENGTH_LONG).show();
            MainApplication app = (MainApplication) getApplication();
            AuthorizationService authService = app.authService;
            authService.performTokenRequest(
                    resp.createTokenExchangeRequest(),
                    new AuthorizationService.TokenResponseCallback() {
                        @Override public void onTokenRequestCompleted(TokenResponse resp, AuthorizationException ex) {
                            if (resp != null) {
                                Toast.makeText(activity, "Token request succeeded", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(activity, "Token request failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        } else {
            Toast.makeText(this, "Auth Failed", Toast.LENGTH_LONG).show();
        }
    }


}
