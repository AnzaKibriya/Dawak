package Helper;

import io.appium.java_client.android.options.UiAutomator2Options;
import java.nio.file.Path;

public class AndroidDriverCapabilities {
    public static UiAutomator2Options getAPKOptions() {
        String apkPath = Path.of(System.getProperty("user.dir"), "/src/main/resources/app-uat-debug.apk").toString();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android")
                .setAvd("Pixel_6_API_34")
                .setAutomationName("UiAutomator2")
                .setDeviceName("Pixel 6 API 34")
                .setAppPackage("ae.purehealth.dawak")
                .setAppActivity("ae.purehealth.dawak.ui.splash.SplashActivity")
                .setApp(apkPath)
                .autoGrantPermissions()
                .setNoReset(false);
        options.setCapability("appium:settings[ignoreUnimportantViews]", true);
        return options;
    }
}
