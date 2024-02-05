import Helper.BaseClass;
import Pages.Pages;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestHome extends BaseClass {
    @Test
    public void homepage()throws InterruptedException, IOException
    {
        test = extent.createTest("Home page");
        Pages.Home().testHome();
    }
}
