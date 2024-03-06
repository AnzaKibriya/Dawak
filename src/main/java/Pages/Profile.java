package Pages;

import Helper.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import static Helper.BaseClass.javascriptExecutor;
import static Helper.BaseClass.prescriptionOrderID;

public class Profile {

    WebDriver driver;

    public Profile(WebDriver Driver) {

        driver=Driver;
    }

    @FindBy(xpath ="//img[@class='mat-mdc-menu-trigger user-icon ng-star-inserted']")
    WebElement profileMenu;


    @FindBy(xpath = "//span[text()='Profile']")
    WebElement profile;


    @FindBy(xpath = "//a[@class='edit-profile']")
    WebElement editProfile;


    @FindBy(xpath = "//button[@type='botton']")
    WebElement uploadPicture;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement firstName;

    @FindBy(xpath = "//span[text()=' Save Changes ']")
    WebElement saveChangesButton;


    @FindBy(xpath = "//h1[text()='Anzas  Kibriya']")
    WebElement nameAferEdit;

    @FindBy(xpath = "//i[@class='das das-task-icons']")
    WebElement CentralPharmacistTasks;


    public  void openProfile()  {
        Pages.WebCommon().waitForElementInteractivity(profileMenu);
        javascriptExecutor().executeScript("arguments[0].click();", profileMenu);
        Pages.WebCommon().waitForElementInteractivity(profile);
        profile.click();


    }
    public void editProfile() throws InterruptedException {
        Pages.WebCommon().waitForElementInteractivity(editProfile);
        javascriptExecutor().executeScript("arguments[0].click();", editProfile);

    }

    public void uploadProfilePicture() throws InterruptedException, AWTException {

        javascriptExecutor().executeScript("arguments[0].click();", uploadPicture);
        String picturepath = new File("src/main/resources/PureHealth_Completes_Acquisition.jpg").getAbsolutePath();
        System.out.println(picturepath);
        StringSelection str = new StringSelection(picturepath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        Pages.WebCommon().waitForElementsInteractions();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        Pages.WebCommon().waitForElementsInteractions();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);


    }


    public void editName() throws InterruptedException {
        Pages.WebCommon().waitForLoaderInvisibility();
        firstName.clear();
        Pages.WebCommon().waitForLoaderInvisibility();
        firstName.sendKeys(BaseClass.propertyFile("config", "FirstName"));
        saveChangesButton.click();
        Pages.WebCommon().waitForLoaderInvisibility();
        Assert.assertEquals(nameAferEdit.getText(), BaseClass.propertyFile("config", "FullName"));
        Pages.Profile().editProfile();
        firstName.clear();
        Pages.WebCommon().waitForLoaderInvisibility();
        firstName.sendKeys(BaseClass.propertyFile("config", "FirstNameAfterEdit"));
        saveChangesButton.click();
        Pages.WebCommon().waitForLoaderInvisibility();
        javascriptExecutor().executeScript("arguments[0].click();", CentralPharmacistTasks);


    }


}
