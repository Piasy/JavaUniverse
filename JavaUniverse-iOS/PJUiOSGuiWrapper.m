//
//  PJUiOSGuiWrapper.m
//  JavaUniverse-iOS
//
//  Created by Piasy on 09/11/2017.
//  Copyright © 2017 Piasy. All rights reserved.
//

#import "Window.h"

#import "PJUiOSGuiWrapper.h"
#import "UIView+PJUWindow.h"

static const int32_t kMatchParentSize = 10000;

CGFloat getSize(CGFloat full, int32_t size) {
    return full * size / kMatchParentSize;
}

@implementation PJUiOSGuiWrapper {
    NSArray<UIColor*>* _colors;
    UIView* _container;
    CGFloat _containerWidth;
    CGFloat _containerHeight;
}

- (instancetype)initWithContainer:(UIView*)container {
    self = [super init];
    if (self) {
        _colors =
            [NSArray arrayWithObjects:[UIColor redColor], [UIColor greenColor],
                                      [UIColor blueColor], nil];
        _container = container;
        _containerWidth = _container.bounds.size.width;
        _containerHeight = _container.bounds.size.height;
    }
    return self;
}

- (void)clearView {
    [[_container subviews]
        makeObjectsPerformSelector:@selector(removeFromSuperview)];
}

- (void)createViewWithWindow:(PJUWindow*)window {
    UILabel* label = [[UILabel alloc]
        initWithFrame:CGRectMake(getSize(_containerWidth, window.left),
                                 getSize(_containerHeight, window.top),
                                 getSize(_containerWidth, window.width),
                                 getSize(_containerHeight, window.height))];
    label.text = window.uid;
    label.textAlignment = NSTextAlignmentCenter;
    label.backgroundColor = _colors[_container.subviews.count % _colors.count];
    label.pjuWindow = window;

    [_container addSubview:label];
}

- (void)swapViewWithWindow:(PJUWindow*)alice andWindow:(PJUWindow*)bob {
    UIView* aliceView = nil;
    UIView* bobView = nil;
    for (UIView* view in _container.subviews) {
        if ([view.pjuWindow.uid isEqualToString:alice.uid]) {
            aliceView = view;
        } else if ([view.pjuWindow.uid isEqualToString:bob.uid]) {
            bobView = view;
        }
    }
    if (aliceView == nil || bobView == nil || aliceView == bobView) {
        return;
    }
    [aliceView.pjuWindow swapWithWindow:bobView.pjuWindow];

    [self applyWindowSize];
}

- (void)applyWindowSize {
    NSArray* children = [_container.subviews
        sortedArrayUsingComparator:^NSComparisonResult(UIView* v1, UIView* v2) {
            int32_t z1 = v1.pjuWindow.z_index;
            int32_t z2 = v2.pjuWindow.z_index;
            return z1 < z2 ? NSOrderedAscending : NSOrderedDescending;
        }];
    for (UIView* child in children) {
        PJUWindow* window = child.pjuWindow;
        child.frame = CGRectMake(getSize(_containerWidth, window.left),
                                 getSize(_containerHeight, window.top),
                                 getSize(_containerWidth, window.width),
                                 getSize(_containerHeight, window.height));
        [_container bringSubviewToFront:child];
    }
}

@end
