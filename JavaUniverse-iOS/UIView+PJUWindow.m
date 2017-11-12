

//
//  UIView+Tag.m
//  JavaUniverse-iOS
//
//  Created by Piasy on 10/11/2017.
//  Copyright © 2017 Piasy. All rights reserved.
//

#import <objc/runtime.h>

#import "UIView+PJUWindow.h"

static const void* kWindowKey = @"kWindowKey";

@implementation UIView (PJUWindow)
- (PJUWindow*)pjuWindow {
    return objc_getAssociatedObject(self, kWindowKey);
}

- (void)setPjuWindow:(PJUWindow*)pjuWindow {
    objc_setAssociatedObject(self, kWindowKey, pjuWindow,
                             OBJC_ASSOCIATION_ASSIGN);
}
@end
