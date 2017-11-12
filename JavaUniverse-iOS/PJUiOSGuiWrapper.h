//
//  PJUiOSGuiWrapper.h
//  JavaUniverse-iOS
//
//  Created by Piasy on 09/11/2017.
//  Copyright © 2017 Piasy. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

#import "GuiWrapper.h"

@interface PJUiOSGuiWrapper : NSObject<PJUGuiWrapper>
- (instancetype)initWithContainer:(UIView*)container;
@end
