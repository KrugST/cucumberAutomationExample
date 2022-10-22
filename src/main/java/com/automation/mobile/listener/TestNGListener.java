package com.automation.mobile.listener;

import com.automation.mobile.appium.AppiumServerManager;
import org.testng.ISuite;
import org.testng.ISuiteListener;


public class TestNGListener implements ISuiteListener {

    public void onStart(ISuite iSuite) {
        new AppiumServerManager().startAppiumServer();
        System.out.println("*************start server*************");
    }

    @Override
    public void onFinish(ISuite iSuite) {
        new AppiumServerManager().stopAppiumServer();
        System.out.println("*************stop server*************");
    }
}
