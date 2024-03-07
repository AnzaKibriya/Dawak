import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

public class TestDawakAppCODPaymentFlow extends BaseClass {
    @Test(priority = 1)
    public void paymentCashOnDelivery() throws InterruptedException {
        test = extent.createTest("Login to Dawak App 3");
        Pages.DawakAppLandingPage().openActivePrescription();
        Pages.DawakAppPrescriptionPage().clickOnProceedBtn();
        Pages.DawakAppPaymentModule().selectTimeSlotForDelivery();
        Pages.DawakAppPaymentModule().placeOrderSuccessfully();
    }
}
