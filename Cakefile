# Change this to set a different Project file name
project.name = "JavaUniverse-iOS"

# Replace this with your class prefix for Objective-C files.
project.organization = "com.github.piasy"
project.class_prefix = "PJU"

# By default Xcake defaults to creating the standard Debug and Release
# configurations, uncomment these lines to add your own.
#
#debug_configuration :Staging
#debug_configuration :Debug
#release_configuration :Release

# Change these to the platform you wish to support (ios, osx) and the
# version of that platform (8.0, 9.0, 10.10, 10.11)
#
application_for :ios, 11.1 do |target|

    #Update these with the details of your app
    target.name = "JavaUniverse-iOS"
    target.all_configurations.each { |c| c.product_bundle_identifier = "com.github.piasy.JavaUniverse-iOS"}

    # Uncomment to target iPhone devices only
    #
    # File patterns can be seen here https://guides.cocoapods.org/syntax/podspec.html#group_file_patterns
    #
    target.all_configurations.each { |c| c.supported_devices = :iphone_only}

    # Uncomment this to include additional files
    #
    target.include_files << "./JavaUniverse/src/main/java/**/*.java"

    # Uncomment this to exclude additional files
    #
    # File patterns can be seen here https://guides.cocoapods.org/syntax/podspec.html#group_file_patterns
    #
    #target.exclude_files << "ExcludeToInclude/*.*"

    # Uncomment to set your own build settings
    target.all_configurations.each do |c|
      c.settings["INFOPLIST_FILE"] = "JavaUniverse-iOS/Info.plist"
      c.settings["J2OBJC_HOME"] = "/usr/local/j2objc"
      c.settings["OTHER_LDFLAGS"] = "$(inherited) -ljre_emul -liconv -lz"
      c.settings["FRAMEWORK_SEARCH_PATHS"] = "$(inherited) ${J2OBJC_HOME}/frameworks"
      c.settings["LIBRARY_SEARCH_PATHS"] = "$(inherited) ${J2OBJC_HOME}/lib"
      c.settings["USER_HEADER_SEARCH_PATHS"] = "$(inherited) ${J2OBJC_HOME}/include"
    end

    target.build_rule "Compile Java to ObjC", "sourcecode.java", ["${DERIVED_FILES_DIR}/${INPUT_FILE_BASE}.h", "${DERIVED_FILES_DIR}/${INPUT_FILE_BASE}.m"], [], <<-SCRIPT
      if [ ! -f "${J2OBJC_HOME}/j2objc" ]; then \
      echo "J2OBJC_HOME not correctly defined in Settings.xcconfig, currently set to '${J2OBJC_HOME}'"; \
      exit 1; fi;
      "${J2OBJC_HOME}/j2objc" -d ${DERIVED_FILES_DIR} \
      -sourcepath "${PROJECT_DIR}/JavaUniverse/src/main/java" \
      --no-package-directories -use-arc \
      --prefix com.github.piasy.java_universe=PJU \
      -g ${INPUT_FILE_PATH};
    SCRIPT
    #
    #target.all_configurations.each { |c| c.settings["ENABLE_BITCODE"] = "NO"}
    #target.all_configurations.each { |c| c.settings["GCC_PREFIX_HEADER"] = "APP-Prefix-Header.pch"}
    #target.all_configurations.each { |c| c.settings["SWIFT_OBJC_BRIDGING_HEADER"] = "APP-Bridging-Header.h"}

    # Uncomment to define your own preprocessor macros
    #
    #target.all_configurations.each { |c| c.preprocessor_definitions["API_ENDPOINT"] = "https://example.org".to_obj_c}

    # Comment to remove Unit Tests for your app
    #
    # unit_tests_for target

    # Uncomment to create a Watch App for your application.
    #
    #watch_app_for target, 2.0
end
