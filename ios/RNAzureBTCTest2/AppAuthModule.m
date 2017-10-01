#import "AppAuthModule.h"
#import <React/RCTLog.h>

@implementation AppAuthModule
  
  RCT_EXPORT_MODULE(AppAuthIos);

  RCT_EXPORT_METHOD(test:(NSString *)msg) {
    RCTLogInfo(@"Test: %@", msg);
  }
  
@end
