package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static Helper.BaseClass.mobileWait;


public class AndroidAppLogin {
    AndroidDriver androidDriver;

    By eng = AppiumBy.id("ae.purehealth.dawak:id/english_langauge_btn");
    public AndroidAppLogin(AndroidDriver AndroidDriver){
        androidDriver = AndroidDriver;
    }
    public void loginDawakApp(){

        mobileWait.until(ExpectedConditions.elementToBeClickable(eng)).click();

    }
}
