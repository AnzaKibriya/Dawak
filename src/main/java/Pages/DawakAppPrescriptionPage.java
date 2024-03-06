package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


import static Helper.BaseClass.*;

public class DawakAppPrescriptionPage {
    AndroidDriver androidDriver;

    By prescriptionNumber = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/order_number_tv\")");
    By deliverMedicationBtn = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/main_button\")");
    By confirmLocationBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/button3");
    By goToHomeBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/goto_home_btn");
    By proceedBtn = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/main_button\")");

    public DawakAppPrescriptionPage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
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

    public void clickOnProceedBtn(){
        mobileWait.until(ExpectedConditions.elementToBeClickable(proceedBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
    }


//    public void paymentProceed() throws InterruptedException {
//        mobileWait.until(ExpectedConditions.elementToBeClickable(proceedBtn)).click();
//        Pages.MobileCommon().waitForLoaderInvisibility();
//        mobileWait.until(ExpectedConditions.elementToBeClickable(timeSlotDropDown)).click();
//        Pages.MobileCommon().scrollInMobile(dateSlotScroll, "down", "80");
////        HashMap<String, String> scrollObject = new HashMap<String, String>();
////        scrollObject.put("direction", "down");
////        scrollObject.put("element", ((RemoteWebElement) element).getId());
////        scrollObject.put("percent", "80");
////        mobileJavascriptExecutor().executeScript("mobile: scrollGesture", scrollObject);
//        mobileWait.until(ExpectedConditions.visibilityOfElementLocated(timeSlotCheckBox));
//        mobileWait.until(ExpectedConditions.elementToBeClickable(timeSlotCheckBox)).click();
//        mobileWait.until(ExpectedConditions.elementToBeClickable(confirmTimeSlotBtn)).click();
//        Pages.MobileCommon().waitForLoaderInvisibility();
//        Pages.MobileCommon().scrollInMobile(pageScroll, "down", "80");
////        HashMap<String, String> scrollObject1 = new HashMap<String, String>();
////        scrollObject1.put("direction", "down");
////        scrollObject1.put("element", ((RemoteWebElement) element1).getId());
////        scrollObject1.put("percent", "80");
////        mobileJavascriptExecutor().executeScript("mobile: scrollGesture", scrollObject1);
//        Pages.MobileCommon().waitForElementsInteractions();
//        mobileWait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
//        Pages.MobileCommon().waitForElementsInteractions();
//        mobileWait.until(ExpectedConditions.visibilityOfElementLocated(goToHomeAfterPayment));
//        mobileWait.until(ExpectedConditions.elementToBeClickable(goToHomeAfterPayment)).click();
//    }
}
