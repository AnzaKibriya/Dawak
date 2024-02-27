package Pages;

import Helper.BaseClass;
import org.openqa.selenium.support.PageFactory;

public class Pages {
    public static LoginCP LoginCP() {
        LoginCP pg = new LoginCP(BaseClass.driver);
        PageFactory.initElements(BaseClass.driver, pg);
        return pg;
    }

    public static OrderDetails OrderDetails() {
        OrderDetails pg = new OrderDetails(BaseClass.driver);
        PageFactory.initElements(BaseClass.driver, pg);
        return pg;
    }

    public static Home Home() {
        Home pg = new Home(BaseClass.driver);
        PageFactory.initElements(BaseClass.driver, pg);
        return pg;
    }

    public static WebCommon WebCommon() {
        WebCommon pg = new WebCommon(BaseClass.driver);
        PageFactory.initElements(BaseClass.driver, pg);
        return pg;
    }

    public static Mailinator Mailinator() {
        Mailinator pg = new Mailinator(BaseClass.driver);
        PageFactory.initElements(BaseClass.driver, pg);
        return pg;
    }

    public static AndroidAppLogin AndroidAppLogin() {
        AndroidAppLogin pg = new AndroidAppLogin(BaseClass.androidDriver);
        PageFactory.initElements(BaseClass.androidDriver, pg);
        return pg;
    }

    public static DawakAppLandingPage DawakAppLandingPage() {
        DawakAppLandingPage pg = new DawakAppLandingPage(BaseClass.androidDriver);
        PageFactory.initElements(BaseClass.androidDriver, pg);
        return pg;
    }

    public static MobileCommon MobileCommon() {
        MobileCommon pg = new MobileCommon(BaseClass.androidDriver);
        PageFactory.initElements(BaseClass.androidDriver, pg);
        return pg;
    }

    public static LoginDP LoginDP() {
        LoginDP pg = new LoginDP(BaseClass.driver);
        PageFactory.initElements(BaseClass.driver, pg);
        return pg;
    }

    public static HomeDP HomeDP() {
        HomeDP pg = new HomeDP(BaseClass.driver);
        PageFactory.initElements(BaseClass.driver, pg);
        return pg;
    }

    public static OrderDetailsDP OrderDetailsDP() {
        OrderDetailsDP pg = new OrderDetailsDP(BaseClass.driver);
        PageFactory.initElements(BaseClass.driver, pg);
        return pg;
    }

    public static Logout Logout() {
        Logout pg = new Logout(BaseClass.driver);
        PageFactory.initElements(BaseClass.driver, pg);
        return pg;
    }

    public static NavigationsCP NavigationsCP() {
        NavigationsCP pg = new NavigationsCP(BaseClass.driver);
        PageFactory.initElements(BaseClass.driver, pg);
        return pg;
    }

    public static NavigationsDP NavigationsDP() {
        NavigationsDP pg = new NavigationsDP(BaseClass.driver);
        PageFactory.initElements(BaseClass.driver, pg);
        return pg;
    }

}
