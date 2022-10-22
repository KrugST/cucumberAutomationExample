package com.automation.mobile.appium;

public interface IAppiumServer {

    void closeAppiumNode(String type) throws Exception;

    void startAppiumNode(String type) throws Exception;
}
