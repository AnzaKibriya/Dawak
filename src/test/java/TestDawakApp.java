import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

public class TestDawakApp extends BaseClass {
    @Test
    public void loginApp(){
        test = extent.createTest("Login to Dawak App");
        Pages.AndroidAppLogin().loginDawakApp();
    }
}
