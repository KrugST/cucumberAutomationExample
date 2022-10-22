package com.automation.mobile.entities;

public interface MobileConfType {

    /**
     * standard device configuration.
     */
    String UDID = "udid";
    String APPLICATION_NAME = "applicationName";
    String DEVICE_NAME = "devicename";
    String PLATFORM_VERSION = "platformversion";
    String PLATFORM_NAME = "platformname";
    String DEVICE_TYPE = "device";
    String APPIUM_SERVER = "AppiumServer";
    /**
     * ios device configuration
     */
    String IOS_XCODE_ORGID = "xcodeOrgId";
    String IOS_XCODE_SIGNINGID = "xcodeSigningId";

    /**
     * emulator configuration.
     */
    String AVD = "avd";

    /**
     * cloud device configuration - perfecto
     */
    String CLOUD_USER = "user";
    String CLOUD_PASSWORD = "password";
    String CLOUD_SERVER = "CloudServer";
    String CLOUD_TOKEN = "token";
}
