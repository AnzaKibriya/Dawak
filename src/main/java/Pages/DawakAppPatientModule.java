package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import model.Patient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static Helper.BaseClass.*;
import static Pages.WebCommon.patient;

public class DawakAppPatientModule {
    AndroidDriver androidDriver;
    String textViews = "//androidx.recyclerview.widget.RecyclerView[@resource-id='ae.purehealth.dawak.qa:id/patient_detail_rv']/android.view.ViewGroup";
    By addFamilyBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/add_patient_btn");
    By relationDropdown = AppiumBy.id("ae.purehealth.dawak.qa:id/relation_tv");
    By relationOtherCheckBox = AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"ae.purehealth.dawak.qa:id/rvRelation\"]/android.view.ViewGroup[5]");
    By confirmRelationBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/button3");
    By otpFields = AppiumBy.id("ae.purehealth.dawak.qa:id/otp_view");

    By emiratesIdField = AppiumBy.id("ae.purehealth.dawak.qa:id/mask_et");
    By registerBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/register_btn");
    By verifyBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/next_btn");
    By proceedBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/register_btn");


    public DawakAppPatientModule(AndroidDriver AndroidDriver) {
        androidDriver = AndroidDriver;
    }
    public void clickOnAddFamilyBtn(){
        mobileWait.until(ExpectedConditions.elementToBeClickable(addFamilyBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
    }

    public void addFamilyMember(){
        mobileWait.until(ExpectedConditions.elementToBeClickable(relationDropdown)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(relationOtherCheckBox)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(confirmRelationBtn)).click();
        mobileWait.until(ExpectedConditions.elementToBeClickable(emiratesIdField)).sendKeys( emiratesID);
        mobileWait.until(ExpectedConditions.elementToBeClickable(registerBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        mobileWait.until(ExpectedConditions.elementToBeClickable(otpFields)).sendKeys("1234");
        mobileWait.until(ExpectedConditions.elementToBeClickable(verifyBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
        Pages.MobileCommon().waitForLoaderInvisibility();
    }

    public void verifyPatientDetails(){
        List<WebElement> listviews = androidDriver.findElements(By.xpath(textViews));
        String a = listviews.get(0).findElement(By.id("ae.purehealth.dawak.qa:id/description_tv")).getText();
        String firstName = patient.getAsJsonPrimitive("eid").getAsString();



    }
}
