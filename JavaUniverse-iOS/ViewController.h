//
//  ViewController.h
//  JavaUniverse-iOS
//
//  Created by Piasy on 09/11/2017.
//  Copyright © 2017 Piasy. All rights reserved.
//

#import <UIKit/UIKit.h>

#import "WindowManagerCore.h"

@interface ViewController : UIViewController<PJUWindowManagerCore_Callback>
// MARK: Properties
@property(weak, nonatomic) IBOutlet UIView* container;

// MARK: Actions
- (IBAction)onShuffle:(UIButton*)sender;
@end
