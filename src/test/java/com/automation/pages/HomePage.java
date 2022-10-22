package com.automation.pages;


import com.automation.pageElements.HomePageElements;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    public HomePageElements homePageElements = new HomePageElements();

    public HomePage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), homePageElements);
    }

    public void waitForHomePageDisplayed() {
        waitForLoadingComplete();
        waitVar.until(ExpectedConditions.visibilityOf(homePageElements.bottomNavigation));
        Assert.assertTrue(homePageElements.bottomNavigation.isDisplayed());
    }

    public void inputPassword(String arg0) {
        waitForLoadingComplete();
        waitVar.until(ExpectedConditions.visibilityOf(homePageElements.signInPasswordInput));
        homePageElements.signInPasswordInput.sendKeys(arg0);
    }

}

