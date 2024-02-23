package Pages;

import Helper.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;

import static Helper.BaseClass.*;

public class Mailinator {

    WebDriver driver;

    @FindBy(xpath = "//td[contains(text(),'just now')]//preceding-sibling::td[contains(text(),'Dawak')]")
    WebElement justNow;

    @FindBy(xpath = "//p[contains(text(),'Dear Dawak User,')]")
    WebElement mailinatorText;

    public Mailinator(WebDriver Driver) {
        driver = Driver;
    }

    public void verifyOtp() {
        WebDriver str = driver.switchTo().newWindow(WindowType.TAB);
        str.get(BaseClass.propertyFile("config", "mailinatorurl"));
        String window = str.getWindowHandle();
        driver.switchTo().window(window);
        Pages.WebCommon().waitForJustNowElementVisibility();
        justNow.click();
        driver.switchTo().frame("html_msg_body");
        String emailText = mailinatorText.getText();
        String numberOnly = emailText.replaceAll("[^0-9]", "");
        otpText = numberOnly.substring(0, 4);
        driver.switchTo().window(loginWindow);
    }
    public void verifyDpOtp() {
        WebDriver str = driver.switchTo().newWindow(WindowType.TAB);
        str.get(BaseClass.propertyFile("config", "mailinatorurlDP"));
        String window = str.getWindowHandle();
        driver.switchTo().window(window);
        Pages.WebCommon().waitForJustNowElementVisibility();
        justNow.click();
        driver.switchTo().frame("html_msg_body");
        String emailText = mailinatorText.getText();
        String numberOnly = emailText.replaceAll("[^0-9]", "");
        otpText = numberOnly.substring(0, 4);
        driver.switchTo().window(loginWindow);
    }
}
