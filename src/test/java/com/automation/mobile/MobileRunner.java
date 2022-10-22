package com.automation.mobile;

import com.automation.mobile.entities.CommandArgument;
import com.automation.mobile.manager.ConfigFileManager;
import com.automation.mobile.util.GlobalVar;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

public class MobileRunner {

    @Test
    public void executeRunner() throws Exception {
            String testType = System.getProperty("testType");
            System.out.println(testType);

            Map<String, List<String>> parsedArgument = parseCommandArgument();
            GlobalVar.DEVICE_LIST = generateDeviceList(parsedArgument);

            System.out.println(GlobalVar.DEVICE_LIST);

            int threadCount = GlobalVar.DEVICE_LIST.size();

            TestNGGenerator testng = new TestNGGenerator(testType, threadCount);
            try {
                testng.runTest();
            }catch (Exception e) {
                e.printStackTrace();
            }
    }

    private Map<String, List<String>> parseCommandArgument()  {

        Map<String, List<String>> testParameters = new HashMap<String, List<String>>();

            testParameters.put(CommandArgument.APPS,
                    Arrays.asList(System.getProperty(CommandArgument.APPS)));
            testParameters.put(CommandArgument.MOBILE_DEVICES,
                    Arrays.asList(System.getProperty(CommandArgument.MOBILE_DEVICES)));

        System.out.println(testParameters);
        testParameters.put("app", Arrays.asList("rjhauler"));
        System.out.println(testParameters);
        testParameters.put("environment", Arrays.asList(System.getProperty(CommandArgument.MOBILE_ENVIRONMENT)));

        System.out.println(testParameters);
        return testParameters;
    }

    public Map<String, Map<String, String>> generateDeviceList(Map<String, List<String>> inputParameters) throws IOException {
        Map<String, Map<String, String>> totalParameters = new HashMap<String, Map<String, String>>();
        List<String> apps = inputParameters.get("app");
        List<String> mobileDevices = inputParameters.get("mobileDevice");
        String app = inputParameters.get("app").get(0);
        String env = inputParameters.get("environment").get(0);

        for (int i = 0; i < mobileDevices.size(); i++) {
            String appName = apps.get(i);
            String mobile = mobileDevices.get(i);
            Map<String, String> deviceParam = new HashMap<>();
            System.out.println(mobile);
            deviceParam.putAll(new ConfigFileManager().getMobilePropertyMap(mobile));
            deviceParam.putAll(new ConfigFileManager().getAppPropertyMap(appName, env));
            deviceParam.put("appName", appName);
            deviceParam.put("env", env);
            deviceParam.put("app", app);
            deviceParam.put("STATE", "AVAILABLE");
            deviceParam.put("REGISTERUSER", "");
            totalParameters.put(mobile, deviceParam);
        }
        return totalParameters;
    }
}


