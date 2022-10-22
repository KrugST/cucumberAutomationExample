package com.automation.steps;
import com.automation.mobile.appium.AppiumDeviceManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class tempSteps {
    public tempSteps() {}
    @Given("^test hook service$")
    public void testHookService() {
        long threadId = Thread.currentThread().getId();
        System.out.println(AppiumDeviceManager.getDevice() + "................." + threadId);
    }


}
