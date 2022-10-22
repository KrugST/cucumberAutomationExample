package com.automation.pages;

import com.automation.mobile.appium.AppiumDevice;
import com.automation.mobile.appium.AppiumDeviceManager;
import com.google.common.collect.ImmutableMap;
import freemarker.template.utility.StringUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class BasePage {
    public AppiumDriver driver;
    public WebDriverWait waitVar;
    public String platformName;

    public BasePage(AppiumDriver driver){
        this.driver = driver;
        this.waitVar = new WebDriverWait(driver, 15);
        this.platformName = driver.getPlatformName().toLowerCase();
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public boolean textDisplayed(String text) {
        if (driver.getPlatformName().equalsIgnoreCase("ios")) {
            waitVar.until(ExpectedConditions.presenceOfElementLocated(MobileBy.name(text)));
            return true;
        } else if (driver.getPlatformName().equalsIgnoreCase("android")) {
            String textNew = text.substring(0, 1).toUpperCase() + text.substring(1);
            String selector = "new UiSelector().textContains(" + "\"" + textNew + "\"" + ")" ;
            waitVar.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AndroidUIAutomator(selector)));
            return true;
        }
        return false;
    }

    public boolean containTextDisplayed(String text) {
        waitForLoadingComplete();
        WebElement element;
        if (driver.getPlatformName().equalsIgnoreCase("ios")) {
            String iosText = "type == 'XCUIElementTypeStaticText' AND name CONTAINS "
                    + "'"
                    + text
                    + "'";
            element = waitVar.until(ExpectedConditions.presenceOfElementLocated(MobileBy.iOSNsPredicateString(iosText)));
            return true;
        } else  {
            String selector = "new UiSelector().textContains(" + "\"" + text + "\"" + ")" ;
            element = waitVar.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AndroidUIAutomator(selector)));
            return true;
        }
    }

    public void enterKey(MobileElement element, String keyValue){
        element.click();
        element.sendKeys(keyValue);
        driver.hideKeyboard();
    }

    public void waitForDisplayed(MobileElement element){
        waitForLoadingComplete();
        waitVar.until(ExpectedConditions.visibilityOf(element));
    }

    public void scrollDown() {
        int pressX = driver.manage().window().getSize().width / 2;
        int bottomY = driver.manage().window().getSize().height * 4/5;
        int topY = driver.manage().window().getSize().height / 8;
        scroll(pressX, bottomY, pressX, topY);
    }

    public void scrollTop() {
        int pressX = driver.manage().window().getSize().width / 2;
        int bottomY = driver.manage().window().getSize().height * 4/5;
        int topY = driver.manage().window().getSize().height / 8;
        scroll(pressX, topY, pressX, bottomY);
    }

    public void swipeLeft() {

    }

    public void swipeLeftOnElement(MobileElement element) {
        scroll(element.getCenter().getX(), element.getCenter().getY(), 0, element.getCenter().getY());
    }

    public boolean scrollDownForElement(WebElement element){
      for(int i = 0; i < 5; i ++ ) {
          try{
            if(element.isDisplayed()){
                return true;
            }
          }catch (Exception e){}
          scrollDown();
      }
      return false;
    }

    public boolean scrollUpForElement(WebElement element){
        for(int i = 0; i < 5; i ++ ) {
            try{
                if(element.isDisplayed()){
                    return true;
                }
            }catch (Exception e){}
            scrollDown();
        }
        return false;
    }

    public void clickElement(WebElement element){
        element.click();
    }

    public MobileElement findElementByName(String name) {
        AppiumDevice device = AppiumDeviceManager.getDevice();
        if (device.getPlatform().equalsIgnoreCase("ios")) {
            return (MobileElement) driver.findElementByName(name);
        } else {
            ArrayList<MobileElement> elementList = (ArrayList<MobileElement>) driver.findElementsByClassName("android.widget.TextView");
            for (MobileElement element: elementList) {
                if (element.getText().equalsIgnoreCase(name)) {
                    return element;
                }
            }
        }
        return null;
    }

    public void scroll(int fromX, int fromY, int toX, int toY) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.longPress(PointOption.point(fromX, fromY)).moveTo(PointOption.point(toX, toY)).release().perform();
    }

    public void enterFromKeyboard(MobileElement element, String keyValue){
        element.click();
        driver.getKeyboard().pressKey(keyValue);
        driver.hideKeyboard();
    }

    public  void doubleClick(MobileElement element){
       clickElement(element);
        clickElement(element);
    }

    public void deleteCurrentTextInEditText(MobileElement element){
        clickElement(element);
        String temp = "";
        int stringLength = element.getText().length();
        for (int i = 0; i < stringLength; i++) {
            temp = temp + "\b";
        }
        element.sendKeys(temp);
    }

    public void tapCoordinate(int x, int y){
        TouchAction touch = new TouchAction(driver);
        touch.tap( new PointOption().withCoordinates(x, y)).perform();
    }

    public void tapElementCoordinate(MobileElement element){
        TouchAction touch = new TouchAction(driver);
        Point point = element.getCenter();
        touch.tap( new PointOption().withCoordinates(point)).perform();
    }

    public void tapSearchAndroidKeyboard(){
        Dimension dimension = driver.manage().window().getSize();
        int height = (int) (dimension.getHeight() * .90);
        int width = (int) (dimension.getWidth() * .92);
        tapCoordinate(width, height);
    }

    public void tapEnterAndroidKeyboard(){
        Dimension dimension = driver.manage().window().getSize();
        int height = (int) (dimension.getHeight() * .97);
        int width = (int) (dimension.getWidth() * .90);
        tapCoordinate(width, height);
    }

    public void tapElement(MobileElement element){
        TouchAction touch = new TouchAction(driver);
        touch.tap(
                new TapOptions().withElement(
                        ElementOption.element(
                                element)))
                .perform();

    }

    public void waitForLoadingComplete(){
        waitVar.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath("//AndroidLoading | //iOSLoading"))));
    }

    public void androidKeyboardClickDone(){
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "Done"));
    }

    public String getCurrentApp(){
        return driver.getCapabilities().getCapability("app").toString();
    }

    public String getCurrentEnv(){
        return driver.getCapabilities().getCapability("env").toString();
    }

    public String getPlatformName(){
        return driver.getPlatformName();
    }

    public void closeAndRelaunch() throws InterruptedException {
        driver.closeApp();
        Thread.sleep(10000);
        driver.launchApp();
    }

    public void waitForClickableAndClick(MobileElement element){
        waitVar.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void waitForTextPresentInElement(MobileElement element, String text){
        waitVar.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void hideKeyBoard(){
        driver.hideKeyboard();
    }

    public void showKeyBoard(){
        driver.getKeyboard();
    }

    public void androidNavigateBack() {
        if (driver.getPlatformName().equalsIgnoreCase("android")) {
            navigateBack();
        }
    }

    public void clickTextOnScreen(String arg0) {
        if (driver.getPlatformName().equalsIgnoreCase("android")) {
            String text = "new UiSelector().text(\"" + arg0 + "\")";
            scrollDownForElement(driver.findElement(MobileBy.AndroidUIAutomator(text)));
            clickElement(driver.findElement(MobileBy.AndroidUIAutomator(text)));
        } else {
            scrollDownForElement(driver.findElementByName(arg0));
            clickElement(driver.findElementByName(arg0));
        }
    }

    public void clickButtonText(String button) {
        MobileElement element;
        if (driver.getPlatformName().equalsIgnoreCase("ios")) {
            element = (MobileElement) driver.findElementByName(button);
        } else {
            String selector = "new UiSelector().textContains(" + "\"" + button + "\"" + ")" ;
            element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(selector));
        }
        element.click();
    }
}
