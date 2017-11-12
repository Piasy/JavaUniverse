//
//  ViewController.m
//  JavaUniverse-iOS
//
//  Created by Piasy on 09/11/2017.
//  Copyright © 2017 Piasy. All rights reserved.
//

#import "Window.h"

#import "ViewController.h"
#import "PJUWindowManager.h"

@interface ViewController ()

@end

@implementation ViewController {
    PJUWindowManager* _windowManager;
    NSMutableArray* _users;
    int _currentFcIndex;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    _windowManager = [[PJUWindowManager alloc] initWithContainer:self.container
                                                     andCallback:self];
    _users = [[NSMutableArray alloc] init];
    _currentFcIndex = 0;

    [_windowManager loadWindows];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)onWindowAdded:(PJUWindow*)window {
    [_users addObject:[window getUid]];
}

- (void)onError:(jint)error {
    NSLog(@"onError %d", error);
}

- (IBAction)onShuffle:(UIButton*)sender {
    _currentFcIndex = (_currentFcIndex + 1) % _users.count;
    NSLog(@"toggleFc %@", _users[_currentFcIndex]);
    [_windowManager toggleFullscreenWithNSString:_users[_currentFcIndex]];
}
@end
