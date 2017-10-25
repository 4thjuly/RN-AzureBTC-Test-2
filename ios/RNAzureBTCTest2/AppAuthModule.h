#import <React/RCTBridgeModule.h>
#import <OIDAuthState+IOS.h>

@interface AppAuthModule : NSObject <RCTBridgeModule>

@property(nonatomic, strong, nullable) OIDAuthState *authState;

@end

