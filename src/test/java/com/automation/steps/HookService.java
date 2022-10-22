package com.automation.steps;

import com.automation.mobile.appium.AppiumDevice;
import com.automation.mobile.appium.AppiumDeviceManager;
import com.automation.mobile.appium.AppiumDriverManager;
import com.automation.mobile.appium.DesiredCapabilityBuilder;
import com.automation.mobile.entities.ConfigType;
import com.automation.mobile.entities.MobileConfType;
import com.automation.mobile.util.GlobalVar;
import com.automation.mobile.listener.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.AppiumDriver;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

//import com.vimalselvam.cucumber.listener.Reporter;

public class HookService {

    AppiumDevice appiumDevice = AppiumDeviceManager.getDevice();

    @Before
    public void before(Scenario scenario) throws IOException {
        System.out.println("**************hooks*********************" + Thread.currentThread().getId());
        System.out.println(appiumDevice);

            if (appiumDevice == null) {
                synchronized (GlobalVar.DEVICE_LIST) {
                    for (String mobile : GlobalVar.DEVICE_LIST.keySet()) {
                        Map<String, String> tempMap = GlobalVar.DEVICE_LIST.get(mobile);
                        if (tempMap.get("STATE").equalsIgnoreCase("available")) {
                            tempMap.put("STATE", "BUSY");
                            System.out.println(GlobalVar.DEVICE_LIST);
                            //create appium device
                            appiumDevice = new AppiumDevice(tempMap);
                            AppiumDeviceManager.setDevice(appiumDevice);

                            //create desired capability
                            DesiredCapabilityBuilder.buildDesiredCapability(appiumDevice);
                            System.out.println(appiumDevice);
                            System.out.println(Thread.currentThread().getId());
                            //create appium server

                            AppiumDriver appiumDriver = new AppiumDriverManager().
                                    initializeDriver(new URL(appiumDevice.getConfigureData(MobileConfType.APPIUM_SERVER)),
                                            DesiredCapabilityBuilder.getDesiredCapability());
                            AppiumDriverManager.setDriver(appiumDriver);


                            break;
                        }
                    }
                }
            }


        synchronized (appiumDevice){
        Reporter.assignAuthor(AppiumDeviceManager.getDevice().getConfigureData(MobileConfType.DEVICE_NAME)
        + "( " + AppiumDeviceManager.getDevice().getConfigureData(ConfigType.APP) + " )");
        Reporter.assignAuthor(AppiumDeviceManager.getDevice().getConfigureData(MobileConfType.PLATFORM_NAME).toLowerCase());
        Reporter.assignAuthor(AppiumDeviceManager.getDevice().getConfigureData(ConfigType.APP));
            }
        AppiumDriverManager.getDriver().launchApp();

        //new PreSetupToHomePageStep();
    }

    @After
    public void afterScenario(Scenario scenario) throws IOException {
        AppiumDriverManager.getDriver().closeApp();
    }
}
