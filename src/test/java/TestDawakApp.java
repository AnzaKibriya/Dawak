import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

public class TestDawakApp extends BaseClass {
    @Test
    public void loginApp(){
        test = extent.createTest("Login to Dawak App");
        Pages.AndroidAppLogin().handleSplashScreens();
        Pages.AndroidAppLogin().loginToDawakApp();
        Pages.DawakAppLandingPage().openActivePrescription();
        Pages.DawakAppLandingPage().verifyPrescriptionID();
        Pages.DawakAppLandingPage().deliverMedicine();
    }
}
