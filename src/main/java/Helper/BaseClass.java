package Helper;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;



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




    public static String screenshot(String filename) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File obj = ts.getScreenshotAs(OutputType.FILE);
        String distination=new File("target//" + filename + ".PNG").getAbsolutePath();
        Files.copy(obj, new File("./target//" + filename + ".PNG"));

        return distination;




    }

    @AfterMethod
    public void getResult(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() +"Test case failed", ExtentColor.RED));
            test.fail(result.getThrowable());
            String distination=screenshot("faileds screenshot");

          //  System.out.println(path);
            test.fail(result.getThrowable()).addScreenCaptureFromPath(distination);
            //screenshot("failed screenshot");

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "Test case passed", ExtentColor.GREEN));
        } else {
            test.log(Status.SKIP,
                    MarkupHelper.createLabel(result.getName() + "Test case skipped", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }

    @AfterSuite
    public void tearDown() {
        extent.flush();
        driver.quit();
    }
}
