package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Helper.BaseClass.generateRandomNumericString;
import static Helper.BaseClass.mobileWait;

public class DawakAppPatientModule {
    AndroidDriver androidDriver;
    By addFamilyBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/add_patient_btn");
    By relationDropdown = AppiumBy.id("ae.purehealth.dawak.qa:id/relation_tv");
    By relationOtherCheckBox = AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"ae.purehealth.dawak.qa:id/rvRelation\"]/android.view.ViewGroup[5]");
    By confirmRelationBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/button3");

    By emiratesIdField = AppiumBy.id("ae.purehealth.dawak.qa:id/emirates_id");
    By registerBtn = AppiumBy.id("ae.purehealth.dawak.qa:id/register_btn");

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
        mobileWait.until(ExpectedConditions.elementToBeClickable(emiratesIdField)).sendKeys("7841994" + generateRandomNumericString());
        mobileWait.until(ExpectedConditions.elementToBeClickable(registerBtn)).click();
        Pages.MobileCommon().waitForLoaderInvisibility();
    }
}
