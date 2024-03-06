package Pages;

import Helper.BaseClass;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.util.HashMap;

import static Helper.BaseClass.mobileWait;
import static Helper.BaseClass.prescriptionOrderID;

public class DawakAppLandingPage {
    AndroidDriver androidDriver;
    By activePrescriptionWidget = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/card_v\").instance(1)");

    public DawakAppLandingPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }


    public void openActivePrescription() throws InterruptedException {
        Thread.sleep(7000);
        mobileWait.until(ExpectedConditions.elementToBeClickable(activePrescriptionWidget)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        Thread.sleep(10000);
    }

}
