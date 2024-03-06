import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

public class TestDawakAppCODPaymentFlow extends BaseClass {
    @Test(priority = 1)
    public void paymentCashOnDelivery() throws InterruptedException {
        test = extent.createTest("Login to Dawak App 3");
        Pages.AndroidAppLogin().handleSplashScreens();
        Pages.AndroidAppLogin().loginToDawakApp();
        Pages.DawakAppLandingPage().openActivePrescription();
        Pages.DawakAppPrescriptionPage().clickOnProceedBtn();
        Pages.DawakAppPaymentPage().selectTimeSlotForDelivery();
        Pages.DawakAppPaymentPage().placeOrderSuccessfully();
    }
}
