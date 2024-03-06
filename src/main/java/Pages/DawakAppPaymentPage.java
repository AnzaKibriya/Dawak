package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Helper.BaseClass.mobileWait;

public class DawakAppPaymentPage {
    AndroidDriver androidDriver;
    WebElement dateSlotScroll = (WebElement) androidDriver.findElement(By.id("ae.purehealth.dawak.qa:id/rvDatesSlots"));
    WebElement pageScroll = (WebElement) androidDriver.findElement(By.id("ae.purehealth.dawak.qa:id/root_view"));
    By timeSlotDropDown = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/choose_time_slot_v\")");
    By timeSlotCheckBox = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"10:00 PM - 10:30 PM\")");
    By confirmTimeSlotBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/button3");
    By goToHomeAfterPayment = AppiumBy.xpath("//android.widget.Button[@text='GO TO HOME']");
    By placeOrderBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/place_order_btn");

    public DawakAppPaymentPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    public void selectTimeSlotForDelivery(){
        mobileWait.until(ExpectedConditions.elementToBeClickable(timeSlotDropDown)).click();
        Pages.MobileCommon().scrollInMobile(dateSlotScroll, "down", "80");
        mobileWait.until(ExpectedConditions.visibilityOfElementLocated(timeSlotCheckBox));
        mobileWait.until(ExpectedConditions.elementToBeClickable(timeSlotCheckBox)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(confirmTimeSlotBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
    }

    public void placeOrderSuccessfully()throws InterruptedException {
        Pages.MobileCommon().scrollInMobile(pageScroll, "down", "80");
        Pages.MobileCommon().waitForElementsInteractions();
        mobileWait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
        Pages.MobileCommon().waitForElementsInteractions();
        mobileWait.until(ExpectedConditions.visibilityOfElementLocated(goToHomeAfterPayment));
        mobileWait.until(ExpectedConditions.elementToBeClickable(goToHomeAfterPayment)).click();
    }
}
