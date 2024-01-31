import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

public class TestDawakCP extends BaseClass {
    @Test
    public void loginToCP(){
        test = extent.createTest("Login to Central Pharmacist");
        Pages.LoginCP().testCPLogin();

    }
}
