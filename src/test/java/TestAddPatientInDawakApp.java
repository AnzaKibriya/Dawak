import Helper.BaseClass;
import Pages.Pages;
import model.LoginApiCall;
import model.PrescriptionApiCall;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestAddPatientInDawakApp extends BaseClass {
    @BeforeClass
    public void createANewPrescription(){
        accessToken = LoginApiCall.makeLoginApiCall();
        prescriptionOrderID = generateRandomNumericString();
        System.out.println(prescriptionOrderID);
        PrescriptionApiCall.makePrescriptionApiCall(accessToken, prescriptionOrderID);
    }
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
        Pages.DawakAppPatientModule().verifyPatientDetails();
    }

}
