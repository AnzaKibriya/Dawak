package Helper;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;
import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import model.LoginApiCall;
import model.PrescriptionApiCall;
import okhttp3.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import static Helper.AndroidDriverCapabilities.getAPKOptions;

public class BaseClass {
    public static ChromeDriver driver;
    public static ExtentTest test;
    public static ExtentReports extent;
    public static String storestring;
    public static Properties prop;
    public static WebDriverWait mobileWait;
    public static WebDriverWait webWait;
    public static String prescriptionOrderID;
    public static String accessToken = "";
    public static OkHttpClient client;
    public static String loginWindow;
    public static String otpText;
    public static SoftAssert softAssert;
    public static AndroidDriver androidDriver;

    public static String orderId;

    @BeforeSuite
    public void setUp() throws MalformedURLException {
        client = new OkHttpClient();
       accessToken = LoginApiCall.makeLoginApiCall();
        prescriptionOrderID = generateRandomNumericString();
        PrescriptionApiCall.makePrescriptionApiCall(accessToken, prescriptionOrderID);
        androidDriver = new AndroidDriver(new URL("http://localhost:4723"), getAPKOptions());
        androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        driver = new ChromeDriver();
        softAssert = new SoftAssert();
        extent = new ExtentReports();
        prop = new Properties();
        webWait = new WebDriverWait(driver, Duration.ofSeconds(50));
       mobileWait = new WebDriverWait(androidDriver, Duration.ofSeconds(70));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        loginWindow = driver.getWindowHandle();
        driver.get("https://dawakportaluat.z1.web.core.windows.net/#/auth/login");
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("target/Dawak.html");
        extent.attachReporter(extentSparkReporter);
    }

    public static String propertyFile(String PropFileName, String stringName) {
        try {
            FileInputStream fis = new FileInputStream("./src/main/resources/" + PropFileName + ".properties");
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

    public String generateRandomNumericString() {
        int length = 10;
        StringBuilder numericString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            prescriptionOrderID = String.valueOf(numericString.append(digit));
        }
        return prescriptionOrderID;
    }

    @AfterSuite
    public void tearDown() {
        extent.flush();
        androidDriver.quit();
        driver.quit();
    }
}
