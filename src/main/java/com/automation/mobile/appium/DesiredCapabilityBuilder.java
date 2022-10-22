package com.automation.mobile.appium;

import com.automation.mobile.entities.AppConfType;
import com.automation.mobile.entities.ConfigType;
import com.automation.mobile.entities.FileLocations;
import com.automation.mobile.entities.MobileConfType;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;

public class DesiredCapabilityBuilder {
    private static ThreadLocal<DesiredCapabilities> desiredCapabilitiesThreadLocal = new ThreadLocal<>();

    public static DesiredCapabilities getDesiredCapability(){
        return desiredCapabilitiesThreadLocal.get();
    }

    private static String getAppPath(String platform, String environment) {
        String appPath = FileLocations.MOBILE_APP_LOCATION;
        String appName = null;
        if (platform.equalsIgnoreCase("android")) {
           if (environment.equalsIgnoreCase("prod")) {appName = "release.apk";}
           else {appName = "debug.apk";}
           appPath += "Android/" + environment + "/" + appName;
        }else if (platform.equalsIgnoreCase("ios")) {
            appPath += "IOS/" + environment + "/" + "release.ipa";
        }
        return appPath;
    }

    private static String getAppName(String platform, String environment) {
        String appName = null;
        if (platform.equalsIgnoreCase("android")) {
            if (environment.equalsIgnoreCase("prod")) {appName = "release.apk";}
            else {appName = "debug.apk";}
        }else if (platform.equalsIgnoreCase("ios")) {
            appName = "release.ipa";
        }
        return appName;
    }

    public static void buildDesiredCapability(AppiumDevice appiumDevice) throws IOException {
        AppiumDevice ad = appiumDevice;
        String platform = ad.getConfigureData(MobileConfType.PLATFORM_NAME);
        String env = ad.getConfigureData(ConfigType.APP_ENVIRONMENT);
        String appPath = getAppPath(platform, env);
        String appName = getAppName(platform, env);
        DesiredCapabilities dc = new DesiredCapabilities();

        dc.setCapability(MobileCapabilityType.UDID, ad.getConfigureData(MobileConfType.UDID));
        dc.setCapability(MobileCapabilityType.APPLICATION_NAME, ad.getConfigureData(MobileConfType.APPLICATION_NAME));
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, ad.getConfigureData(MobileConfType.DEVICE_NAME));
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, ad.getConfigureData(MobileConfType.PLATFORM_NAME));
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, ad.getConfigureData(MobileConfType.PLATFORM_VERSION));
        dc.setCapability(MobileCapabilityType.NO_RESET, true);

        if (platform.equalsIgnoreCase("android")) {
            dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, ad.getConfigureData(AppConfType.PACKAGE_NAME));
            dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ad.getConfigureData(AppConfType.ACTIVITY_NAME));
            if (ad.getConfigureData(MobileConfType.AVD) != null) {
                dc.setCapability(AndroidMobileCapabilityType.AVD, ad.getConfigureData(MobileConfType.AVD));
            }
        } else if (platform.equalsIgnoreCase("ios")) {
            dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, ad.getConfigureData(AppConfType.BUNDLE_ID));
        }
        //ios sign capability
        if (platform.equalsIgnoreCase("ios")) {
            dc.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, ad.getConfigureData(MobileConfType.IOS_XCODE_ORGID));
            dc.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID,ad.getConfigureData(MobileConfType.IOS_XCODE_SIGNINGID));
            dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            dc.setCapability(MobileCapabilityType.NO_RESET, true);
        }

        //local device install app
        dc.setCapability(MobileCapabilityType.APP, appPath);
        desiredCapabilitiesThreadLocal.set(dc);

        System.out.println(desiredCapabilitiesThreadLocal.get().getCapabilityNames());
    }
}
