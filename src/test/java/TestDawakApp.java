import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

public class TestDawakApp extends BaseClass {
    @Test(priority = 1)
    public void loginApp() {
        test = extent.createTest("Login to Dawak App");
        Pages.AndroidAppLogin().handleSplashScreens();
        Pages.AndroidAppLogin().loginToDawakApp();
    }

    @Test(priority = 2)
    public void verifyPrescription() throws InterruptedException {
        test = extent.createTest("Login to Dawak App 2");
        Pages.DawakAppLandingPage().openActivePrescription();
        Pages.DawakAppLandingPage().verifyPrescriptionID();
    }

    @Test(priority = 3)
    public void sendPrescriptionForDelivery() {
        test = extent.createTest("Login to Dawak App 3");
        Pages.DawakAppLandingPage().deliverMedicine();
    }
}
