# JavaUniverse

A demo project that showcase how to use Java to conquer the universe, with the help of
[J2ObjC](https://developers.google.com/j2objc/) and [GWT](http://www.gwtproject.org/) :)

This demo is a window management app, it download window configuration (size and position)
from the Internet, then create TextView/UILabel from it, and user can switch fullscreen
window.

The window management logic and data structure is written in Java, but GUI operation and
third party library call are written "natively", which is separated through interface/protocol.

Web app is still working in progress...

## J2Objc Caveat

+ When modify build settings, don't forget add `$(inherited)` at first, otherwise CocoaPods
will break.

## Xcode project file management

+ Use [xcake](https://github.com/jcampbell05/xcake) to generate Xcode project files, but
J2ObjC need to use custom build rule, which is not supported by xcake yet, currently I
use [my fork](https://github.com/Piasy/xcake), please track
[this pr](https://github.com/jcampbell05/xcake/pull/181).
+ xcake can work with [CocoaPods](https://github.com/CocoaPods/CocoaPods), use
`setup_xcode_project.sh` to setup Xcode project.
