package Helper;

import io.appium.java_client.android.options.UiAutomator2Options;
import java.nio.file.Path;
import java.time.Duration;

public class AndroidDriverCapabilities {
    public static UiAutomator2Options getAPKOptions() {
        String apkPath = Path.of(System.getProperty("user.dir"), "/src/main/resources/app-qa-debug.apk").toString();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(BaseClass.propertyFile("config", "PlatformName"))
                .setAvd(BaseClass.propertyFile("config", "Avd"))
                .setAutomationName(BaseClass.propertyFile("config", "AutomationName"))
                .setDeviceName(BaseClass.propertyFile("config", "DeviceName"))
                .setAppPackage(BaseClass.propertyFile("config", "AppPackage"))
                .setAppActivity(BaseClass.propertyFile("config", "AppActivity"))
                .setApp(apkPath)
                .autoGrantPermissions()
                .setNewCommandTimeout(Duration.ofMinutes(25))
                .setNoReset(false);
        options.setCapability("appium:settings[ignoreUnimportantViews]", true);
        return options;
    }
}
