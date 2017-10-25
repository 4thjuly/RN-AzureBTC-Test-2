#import "AppAuthModule.h"
#import <React/RCTLog.h>
#import <OIDServiceConfiguration.h>
#import <OIDAuthorizationRequest.h>
#import "AppDelegate.h"
#import <OIDTokenRequest.h>

@implementation AppAuthModule

  @synthesize authState;

  RCT_EXPORT_MODULE(AppAuthIos);

  RCT_EXPORT_METHOD(test:(NSString *)msg) {
    RCTLogInfo(@"Test: %@", msg);
  }

  - (dispatch_queue_t)methodQueue {
    return dispatch_get_main_queue();
  }

  RCT_EXPORT_METHOD(login) {
    NSURL *authorizationEndpoint = [NSURL URLWithString:@"https://login.microsoftonline.com/IanETB2CTenant.onmicrosoft.com/oauth2/v2.0/authorize?p=B2C_1_SUSI"];
    NSURL *tokenEndpoint = [NSURL URLWithString:@"https://login.microsoftonline.com/IanETB2CTenant.onmicrosoft.com/oauth2/v2.0/token?p=B2C_1_SUSI"];
    NSString *clientId = @"62386987-856b-4e6e-89db-59eef6d603b6";
    NSURL *redirectURI = [NSURL URLWithString:@"com.onmicrosoft.ianetb2ctenant.rnazureb2ctest2://oauth/redirect"];

    OIDServiceConfiguration *configuration = [[OIDServiceConfiguration alloc] initWithAuthorizationEndpoint:authorizationEndpoint tokenEndpoint:tokenEndpoint];
    
    OIDAuthorizationRequest *request = [[OIDAuthorizationRequest alloc] initWithConfiguration:configuration
        clientId:clientId
  //      scopes:@[@"openid", @"profile", @"offline_access"]
        scopes:@[@"openid", @"profile", @"offline_access", @"62386987-856b-4e6e-89db-59eef6d603b6"]
  //      scopes:@[@"62386987-856b-4e6e-89db-59eef6d603b6"]
        redirectURL:redirectURI
        responseType:OIDResponseTypeCode
        additionalParameters:nil];
    
    AppDelegate *appDelegate = (AppDelegate *)[UIApplication sharedApplication].delegate;
    
    UIViewController *viewController = [UIApplication sharedApplication].keyWindow.rootViewController;
    
    appDelegate.currentAuthorizationFlow = [OIDAuthState authStateByPresentingAuthorizationRequest:request presentingViewController:viewController callback:^(OIDAuthState *authState, NSError *error)  {
        if (authState) {
            // NSLog(@"Got authorization tokens. Access token: %@", authState.lastTokenResponse.accessToken);
            [self setAuthState:authState];
        } else {
            NSLog(@"Authorization error: %@", [error localizedDescription]);
            [self setAuthState:nil];
        }
    }];
}


//String authEndpoint = "https://login.microsoftonline.com/IanETB2CTenant.onmicrosoft.com/oauth2/v2.0/authorize?p=B2C_1_SUSI";
//String tokenEndpoint = "https://login.microsoftonline.com/IanETB2CTenant.onmicrosoft.com/oauth2/v2.0/token?p=B2C_1_SUSI";
//String clientId = "62386987-856b-4e6e-89db-59eef6d603b6";
//String scope = "openid profile offline_access 62386987-856b-4e6e-89db-59eef6d603b6";
//Uri redirectUri = Uri.parse("com.onmicrosoft.ianetb2ctenant.rnazureb2ctest2://oauth/redirect");
//
//AuthorizationServiceConfiguration config = new AuthorizationServiceConfiguration(Uri.parse(authEndpoint), Uri.parse(tokenEndpoint));
//AuthorizationRequest req = new AuthorizationRequest.Builder(config, clientId, "code", redirectUri).setScope(scope).build();
//AuthorizationService authService = new AuthorizationService(this);
//MainApplication app = (MainApplication) getApplication();
//app.authService = authService;
//app.authConfig = config;
//
//Intent completeIntent = new Intent(this, AuthCompleteActivity.class);
//authService.performAuthorizationRequest(req, PendingIntent.getActivity(this, 0, completeIntent, 0));


@end
