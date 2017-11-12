//
//  AFNetworkingDataSource.m
//  JavaUniverse-iOS
//
//  Created by Piasy on 09/11/2017.
//  Copyright © 2017 Piasy. All rights reserved.
//

#import <AFNetworking/AFNetworking.h>

#import "java/util/ArrayList.h"

#import "Window.h"

#import "PJUAFDataSource.h"

@implementation PJUAFDataSource {
    NSURLSessionConfiguration* _conf;
    AFURLSessionManager* _manager;
}

- (instancetype)init {
    self = [super init];
    if (self) {
        _conf = [NSURLSessionConfiguration defaultSessionConfiguration];
        _manager =
            [[AFURLSessionManager alloc] initWithSessionConfiguration:_conf];
    }
    return self;
}

- (void)getWindowsWithPage:(jint)page
                       num:(jint)num
                  callback:(id<PJUDataSource_Callback>)callback {
    NSURL* url =
        [NSURL URLWithString:@"https://imgs.babits.top/windows_4.json"];
    NSURLRequest* request = [[NSURLRequest alloc] initWithURL:url];
    NSURLSessionDataTask* task = [_manager
        dataTaskWithRequest:request
          completionHandler:^(NSURLResponse* _Nonnull response,
                              id _Nullable responseObject,
                              NSError* _Nullable error) {
              JavaUtilArrayList* list = [[JavaUtilArrayList alloc] init];
              for (NSDictionary* obj in responseObject) {
                  PJUWindow* window = [[PJUWindow alloc]
                      initWithWidth:[obj[@"width"] integerValue]
                             height:[obj[@"height"] integerValue]
                                top:[obj[@"top"] integerValue]
                               left:[obj[@"left"] integerValue]
                             zIndex:[obj[@"z_index"] integerValue]
                                uid:obj[@"uid"]];
                  [list addWithId:window];
              }
              [callback onSuccess:list];
          }];
    [task resume];
}

@end

