//
//  PJUWindowManager.m
//  JavaUniverse-iOS
//
//  Created by Piasy on 09/11/2017.
//  Copyright © 2017 Piasy. All rights reserved.
//

#import "PJUWindowManager.h"
#import "PJUAFDataSource.h"
#import "PJUiOSGuiWrapper.h"

@implementation PJUWindowManager
- (instancetype)initWithContainer:(UIView*)container
                      andCallback:(id<PJUWindowManagerCore_Callback>)callback {
    self = [super initWithPJUDataSource:[[PJUAFDataSource alloc] init]
                        withPJUGuiWrapper:[[PJUiOSGuiWrapper alloc]
                                              initWithContainer:container]
        withPJUWindowManagerCore_Callback:callback];
    return self;
}
@end

