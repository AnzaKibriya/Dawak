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
    By prescriptionNumber = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/order_number_tv\")");
    By deliverMedicationBtn = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/main_button\")");
    By confirmLocationBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/button3");
    By goToHomeBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/goto_home_btn");
    By proceedBtn = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/main_button\")");
    By timeSlotDropDown = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/choose_time_slot_v\")");
    By timeSlotCheckBox = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"10:00 PM - 10:30 PM\")");
    By confirmTimeSlotBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/button3");
    By goToHomeAfterPayment = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"10:00 PM - 10:30 PM\")");


    public DawakAppLandingPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }


    public void openActivePrescription() throws InterruptedException {
        mobileWait.until(ExpectedConditions.elementToBeClickable(activePrescriptionWidget)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        Thread.sleep(2000);
    }

    public void verifyPrescriptionID() throws InterruptedException {
        Pages.MobileCommon().waitForAPIResponseToMirrorInAPP();
        String number = mobileWait.until(ExpectedConditions.visibilityOfElementLocated(prescriptionNumber)).getText();
        String[] arrOfStr = number.split("#");
        Assert.assertEquals(arrOfStr[1].replaceAll("\\s", ""), prescriptionOrderID);
    }

    public void deliverMedicine() {
        mobileWait.until(ExpectedConditions.elementToBeClickable(deliverMedicationBtn)).click();
        Pages.MobileCommon().waitForAddress();
        mobileWait.until(ExpectedConditions.elementToBeClickable(confirmLocationBtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(goToHomeBtn)).click();
    }

    public void paymentProceed(){
        mobileWait.until(ExpectedConditions.elementToBeClickable(proceedBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        mobileWait.until(ExpectedConditions.elementToBeClickable(timeSlotDropDown)).click();
        WebElement element = (WebElement) androidDriver.findElement(By.id("ae.purehealth.dawak.qa:id/rvDatesSlots"));
        JavascriptExecutor js = (JavascriptExecutor)androidDriver;
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "down");
        scrollObject.put("element", ((RemoteWebElement) element).getId());
        scrollObject.put("percent", "80");
        js.executeScript("mobile: scrollGesture", scrollObject);
        mobileWait.until(ExpectedConditions.elementToBeClickable(timeSlotCheckBox)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(confirmTimeSlotBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"PLACE ORDER\").instance(0))")).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        mobileWait.until(ExpectedConditions.elementToBeClickable(goToHomeAfterPayment)).click();
    }
}
