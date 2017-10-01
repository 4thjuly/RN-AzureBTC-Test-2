package com.rnazurebtctest2;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.lwansbrough.RCTCamera.RCTCameraPackage;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

  AuthorizationService authService;
  AuthorizationServiceConfiguration authConfig;

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
            new RCTCameraPackage(), new AppAuthPackage()
      );
    }
  };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  public AuthorizationService getAuthService() { return authService; }
  public void setAuthService(AuthorizationService authService) { this.authService = authService; }
  public AuthorizationServiceConfiguration getAuthConfig() { return authConfig; }
  public void setAuthConfig(AuthorizationServiceConfiguration authConfig) { this.authConfig = authConfig; }

    @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, false);
  }
}
