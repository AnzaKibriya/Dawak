import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

public class TestDawakAppPaymentFlow  extends BaseClass {
    @Test(priority = 2)
    public void payment() throws InterruptedException {
        test = extent.createTest("Login to Dawak App 3");
        Pages.DawakAppLandingPage().openActivePrescription();
        Pages.DawakAppLandingPage().paymentProceed();
    }
}
