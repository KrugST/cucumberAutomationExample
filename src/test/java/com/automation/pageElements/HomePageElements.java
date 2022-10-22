package com.automation.pageElements;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomePageElements {
    //EXAMPLE
    @AndroidFindBy(id = "imageBack")
    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND name == 'Cancel' AND visible == 1")
    public MobileElement cancelButton;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[1]")
    @AndroidFindBy(id = "app_bar_title")
    public MobileElement optionWindowTitle;

    @iOSXCUITFindBy(accessibility = "Related Products")
    @AndroidFindBy(id = "carousel_title")
    public MobileElement relatedProductText;
    //EXAMPLE


    @AndroidFindBy(id = "bottom_nav")
    public MobileElement bottomNavigation;

    //@AndroidFindBy(id = "password")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View[3]/android.widget.EditText")
    public MobileElement signInPasswordInput;



}
