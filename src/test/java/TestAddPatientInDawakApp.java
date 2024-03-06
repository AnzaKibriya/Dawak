import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

public class TestAddPatientInDawakApp extends BaseClass {
    @Test(priority = 1)
    public void loginApp() {
        test = extent.createTest("Login to Dawak App");
        Pages.AndroidAppLogin().handleSplashScreens();
        Pages.AndroidAppLogin().loginToDawakApp();
    }
    @Test(priority = 2)
    public void navigateToPatientPage(){
        Pages.DawakAppLandingPage().navigateToPatientPage();
        Pages.DawakAppPatientModule().clickOnAddFamilyBtn();
        Pages.DawakAppPatientModule().addFamilyMember();
    }

}
