package Helper;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;


public class BaseClass {
    public static ChromeDriver driver;
    public static ExtentTest test;
    public static ExtentReports extent;
    public static String storestring;
    public static Properties prop = new Properties();
    public static WebDriverWait wait;

    public static String value;



    @BeforeSuite
    public void setUp() {
        driver = new ChromeDriver();
        extent = new ExtentReports();
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://dawakportaluat.z1.web.core.windows.net/#/auth/login");
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("target/Dawak.html");
        extent.attachReporter(extentSparkReporter);
    }

    public static String propertyFile(String PropFileName, String stringName) {
        try {
            FileInputStream fis = new FileInputStream("./target/" + PropFileName + ".properties");
            prop.load(fis);
            storestring = prop.getProperty(stringName);
        } catch (Exception e) {
            System.out.println("File Not Found :" + e.getMessage());
        }
        return storestring;
    }

    public static String screenshot(String filename) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File obj = ts.getScreenshotAs(OutputType.FILE);
        String destination = new File("target//" + filename + ".PNG").getAbsolutePath();
        Files.copy(obj, new File("./target//" + filename + ".PNG"));
        return destination;
    }

    public static void waitForLoaderInvisibility() {
        List<WebElement> loaderElement = driver.findElements(By.xpath("//ngx-spinner//img"));
        if (!loaderElement.isEmpty()) {
            wait.until(ExpectedConditions.invisibilityOfAllElements(loaderElement));
        }
    }

    public static void verifyWebtableData() {
        for (int i = 3; i <= 7; i++) {
            value = driver.findElement(By.xpath("//table//tr[1]//td[" + i + "]")).getText();
            if (value.isEmpty())
                break;
        }
        if (value.isEmpty()) {
            test.log(Status.FAIL, "WebTable  cell contain  data");

        } else {
            test.log(Status.PASS, "WebTable  contains data");
        }
    }


    public static void checkElementIsEmpty(String details) {

        if (details.isEmpty()) {
            test.log(Status.FAIL, "WebTable  Does not contain data");

        } else {
            test.log(Status.PASS, "WebTable cell  contains data");
        }


    }

    @AfterMethod
    public void getResult(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "Test case failed", ExtentColor.RED));
            test.fail(result.getThrowable());
            String destination = screenshot("Failed Scenario Screenshot");
            test.fail(result.getThrowable()).addScreenCaptureFromPath(destination);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "Test case passed", ExtentColor.GREEN));
        } else {
            test.log(Status.SKIP,
                    MarkupHelper.createLabel(result.getName() + "Test case skipped", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }

    public static JavascriptExecutor javascriptExecutor() {
        return (JavascriptExecutor) driver;
    }

    @AfterSuite
    public void tearDown() {
        extent.flush();
        driver.quit();
    }
}
