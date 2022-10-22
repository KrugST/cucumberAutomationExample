package com.automation.mobile.appium;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class AppiumDriverManager {

        private static ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<>();

        public static AppiumDriver getDriver() {
                return appiumDriver.get();
        }

        public AppiumDriver initializeDriver(URL appiumServer, DesiredCapabilities ds) {
                return new AppiumDriver(appiumServer, ds);
        }

        public static void setDriver(AppiumDriver driver) {
                appiumDriver.set(driver);
        }
}
