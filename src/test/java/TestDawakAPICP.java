import Helper.BaseClass;
import model.CreateOtpCpApiCall;
import model.GetTaskAPICall;
import model.LogincpApiCall;
import model.PutOtpCpApiCall;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestDawakAPICP extends BaseClass {

    @Test(priority = 1)
    public void login() throws IOException {
     test = extent.createTest("Login API");
     LogincpApiCall.makeLoginApiCall();



 }
    @Test(priority = 2)
    public void createOtp()
 {
     test = extent.createTest("create otp API");
     CreateOtpCpApiCall.createOtpApiCall();

 }

    @Test(priority = 3)
    public void verifyOtp()
 {
     test = extent.createTest("verify otp API");
     PutOtpCpApiCall.OTPApiCall();

 }
    @Test(priority = 4)
    public void getTaskApiData() throws IOException {
        test = extent.createTest("Get Task Api");
     GetTaskAPICall.getTaskApicall();

    }


}
