#import "AppAuthModule.h"
#import <React/RCTLog.h>
#import <OIDServiceConfiguration.h>
#import <OIDAuthorizationRequest.h>
#import "AppDelegate.h"
#import <MicrosoftAzureMobile/MicrosoftAzureMobile.h>
#import <OIDTokenResponse.h>

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
    NSURL *authorizationEndpoint = [NSURL URLWithString:@"https://login.microsoftonline.com/te/IanETB2CTenant.onmicrosoft.com/b2c_1_susi/oauth2/v2.0/authorize"];
    NSURL *tokenEndpoint = [NSURL URLWithString:@"https://login.microsoftonline.com/te/IanETB2CTenant.onmicrosoft.com/b2c_1_susi/oauth2/v2.0/token"];
    NSString *clientId = @"62386987-856b-4e6e-89db-59eef6d603b6";
    NSURL *redirectURI = [NSURL URLWithString:@"com.onmicrosoft.ianetb2ctenant.rnazureb2ctest2://oauth/redirect"];

    OIDServiceConfiguration *configuration = [[OIDServiceConfiguration alloc] initWithAuthorizationEndpoint:authorizationEndpoint tokenEndpoint:tokenEndpoint];
    
    OIDAuthorizationRequest *request = [[OIDAuthorizationRequest alloc] initWithConfiguration:configuration
      clientId:clientId
      scopes:@[@"openid", @"profile", @"offline_access", @"62386987-856b-4e6e-89db-59eef6d603b6"]
      redirectURL:redirectURI
      responseType:OIDResponseTypeCode
      additionalParameters:nil];
        
    UIViewController *viewController = [UIApplication sharedApplication].keyWindow.rootViewController;
    
    AppDelegate *appDelegate = (AppDelegate *)[UIApplication sharedApplication].delegate;
    appDelegate.currentAuthorizationFlow = [OIDAuthState authStateByPresentingAuthorizationRequest:request presentingViewController:viewController callback:^(OIDAuthState *authState, NSError *error)  {
      if (authState) {
          OIDTokenResponse *resp = authState.lastTokenResponse;
          NSString *token = resp.accessToken;
          NSLog(@"Got authorization tokens. Access token: %@", token);
          [self setAuthState:authState];
        
//          UIAlertController* alert = [UIAlertController alertControllerWithTitle:@"Msg" message:@"Got access token" preferredStyle:UIAlertControllerStyleAlert];
//          UIAlertAction* defaultAction = [UIAlertAction actionWithTitle:@"OK" style:UIAlertActionStyleDefault handler:^(UIAlertAction * action) {}];
//          [alert addAction:defaultAction];
//          [viewController presentViewController:alert animated:YES completion:nil];

          MSClient *client = [MSClient clientWithApplicationURLString:@"https://api-app-test-1.azurewebsites.net"];
          MSUser *user = [[MSUser alloc] initWithUserId:nil];
          user.mobileServiceAuthenticationToken = token;
          [client setCurrentUser:user];
          [client invokeAPI:@"Test" body:nil HTTPMethod:@"GET" parameters:nil headers:nil completion:^(NSData *result, NSHTTPURLResponse *response, NSError *error) {
            if (error) {
              NSLog(@"ERROR %@", error);
            } else {
              NSDictionary *resultDictionary = (NSDictionary*)result;
              NSDate *date = resultDictionary[@"date"];
              NSLog(@"Success: %@", date.description);
              UIAlertController* alert = [UIAlertController alertControllerWithTitle:@"Date" message:date.description preferredStyle:UIAlertControllerStyleAlert];
              UIAlertAction* defaultAction = [UIAlertAction actionWithTitle:@"OK" style:UIAlertActionStyleDefault handler:^(UIAlertAction * action) {}];
              [alert addAction:defaultAction];
              [viewController presentViewController:alert animated:YES completion:nil];
            }
          }];
        } else {
          NSLog(@"Authorization error: %@", [error localizedDescription]);
          [self setAuthState:nil];
          UIAlertController* alert = [UIAlertController alertControllerWithTitle:@"Error" message:@"Failed to get access token" preferredStyle:UIAlertControllerStyleAlert];
          UIAlertAction* defaultAction = [UIAlertAction actionWithTitle:@"OK" style:UIAlertActionStyleDefault handler:^(UIAlertAction * action) {}];
          [alert addAction:defaultAction];
          [viewController presentViewController:alert animated:YES completion:nil];
        }
    }];
}

@end
