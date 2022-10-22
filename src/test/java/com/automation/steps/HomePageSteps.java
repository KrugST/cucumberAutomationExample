package com.automation.steps;

import com.automation.mobile.appium.AppiumDriverManager;
import com.automation.pages.HomePage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import org.junit.Assert;

public class HomePageSteps {
    AppiumDriver driver = AppiumDriverManager.getDriver();
    public HomePage homePage = new HomePage(driver);

    @When("^home page is displayed$")
    public void home_page_is_displayed() throws Throwable {
        homePage.waitForHomePageDisplayed();
    }

    @Then("^user input \"([^\"]*)\" password in password input field$")
    public void userInputPasswordInPasswordInputField(String arg0) throws Throwable {
        homePage.inputPassword(arg0);
    }
}
