package Helper;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
    public static ChromeDriver driver;
    public static ExtentTest test;
    public static ExtentReports extent;

    @BeforeSuite
    public void setUp(){
        driver = new ChromeDriver();
        extent = new ExtentReports();
        driver.manage().window().maximize();
        driver.get("https://dawakportaluat.z1.web.core.windows.net");
        ExtentSparkReporter extentSparkReporter= new ExtentSparkReporter("target/Dawak.html");
        extent.attachReporter(extentSparkReporter);
    }

    @AfterSuite
    public void tearDown() {
        extent.flush();
        driver.quit();
    }
}
