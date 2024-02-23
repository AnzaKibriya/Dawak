package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static Helper.BaseClass.mobileWait;
import static Helper.BaseClass.webWait;
import static java.time.Duration.ofSeconds;

public class MobileCommon {
    AndroidDriver androidDriver;
    By dawakAppLoader = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"ae.purehealth.dawak.qa:id/success_logo_v\")");
    By addressCheckBox = AppiumBy.className("android.view.ViewGroup");

    public MobileCommon(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    public void waitForLoaderInvisibility() {
        List<WebElement> loaderElement = androidDriver.findElements(dawakAppLoader);
        if (!loaderElement.isEmpty()) {
            WebElement topLoaderElement = loaderElement.get(0);
            webWait.until(ExpectedConditions.invisibilityOf(topLoaderElement));
        }
    }

    public void waitForAddress() {
        mobileWait.until(ExpectedConditions.visibilityOfElementLocated(addressCheckBox));
    }
}
