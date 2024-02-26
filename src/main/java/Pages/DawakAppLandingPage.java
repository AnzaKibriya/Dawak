package Pages;

import Helper.BaseClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

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
    By creditCardOption = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/mode_of_payment_card_v\")");
    By paymentBtn = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/place_order_btn\")");


    public DawakAppLandingPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }


    public void openActivePrescription() throws InterruptedException {
        mobileWait.until(ExpectedConditions.elementToBeClickable(activePrescriptionWidget)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        Thread.sleep(2000);
    }

    public void verifyPrescriptionID() {
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
        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"PLACE ORDER\").instance(0))")).click();
    }
}
