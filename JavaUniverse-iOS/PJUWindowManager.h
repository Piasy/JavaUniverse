//
//  PJUWindowManager.h
//  JavaUniverse-iOS
//
//  Created by Piasy on 09/11/2017.
//  Copyright © 2017 Piasy. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

#import "WindowManagerCore.h"

@interface PJUWindowManager : PJUWindowManagerCore
- (instancetype)initWithContainer:(UIView*)container
                      andCallback:(id<PJUWindowManagerCore_Callback>)callback;
@end
