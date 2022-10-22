package com.automation.mobile.appium;


import com.automation.mobile.entities.MobileConfType;
import com.automation.mobile.util.GlobalVar;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.util.HashMap;

public class AppiumServerManager {

    private AppiumDriverLocalService createAppiumLocalServer() {
        System.out.println("Starting Appium Server on Localhost");
        AppiumDriverLocalService appiumDriverLocalService;
        HashMap<String, String > environment = new HashMap<>();
        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
        AppiumServiceBuilder builder =
                new AppiumServiceBuilder()
                        .withArgument(GeneralServerFlag.RELAXED_SECURITY)
                        .usingAnyFreePort()
                        .withEnvironment(environment);
        appiumDriverLocalService = AppiumDriverLocalService.buildService(builder);
        appiumDriverLocalService.start();
        System.out.println( "Appium Server Started at: "
                + appiumDriverLocalService.getUrl());
        return appiumDriverLocalService;
    }



    public void startAppiumServer()  {
        for (String mobile : GlobalVar.DEVICE_LIST.keySet()) {
            String deviceType = GlobalVar.DEVICE_LIST.get(mobile).get(MobileConfType.DEVICE_TYPE);
            if (deviceType.equalsIgnoreCase("local")) {
                AppiumDriverLocalService server = createAppiumLocalServer();
                GlobalVar.APPIUM_SERVER_LIST.put(server , "AVAILABLE");
                GlobalVar.DEVICE_LIST.get(mobile).put("AppiumServer", server.getUrl().toString());
            } else {
                GlobalVar.DEVICE_LIST.get(mobile).put("AppiumServer",
                        GlobalVar.DEVICE_LIST.get(mobile).get(MobileConfType.CLOUD_SERVER));
            }
        }
    }

    public void stopAppiumServer()  {
        for ( AppiumDriverLocalService server: GlobalVar.APPIUM_SERVER_LIST.keySet()) {
            server.stop();
        }
    }
}
