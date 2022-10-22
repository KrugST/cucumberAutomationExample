package com.automation.mobile.manager;

import com.automation.mobile.entities.FileLocations;

import java.io.IOException;
import java.util.Map;

public class ConfigFileManager {

    public static Map<String, String> getAppPropertyMap(String app, String environment) throws IOException {
        String appFilePath = new FileLocations().getAppConfigPath(app, environment);
        return new FileManager(appFilePath).getPropertyInMap();
    }

    public static Map<String, String> getMobilePropertyMap(String mobileDevice) throws IOException {
        String mobileFilePath = new FileLocations().getMobileConfigPath(mobileDevice);
        return new FileManager(mobileFilePath).getPropertyInMap();
    }
}
