package com.norwegian.basetestplatform;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author anna
 */
public class BaseTest {
    public static AppiumDriver<AndroidElement> driver = null;
    private DesiredCapabilities cap;
    public static Logger logger;
    public static String ADB = System.getenv("ANDROID_HOME") + "/platform-tools/adb";

    @Parameters({
            "deviceName", "udid", "platformName", "appPackage",
            "appActivity", "newCommandTimeout", "unicodeKeyboard",
            "resetKeyboard", "fullReset", "noReset", "portNumber"
    })
    @BeforeSuite
    public void launchingAndroidDriver(String deviceNameValue, String uniqueDeviceIdValue,
                                       String platformNameValue, String appPackValue, String appActivityValue,
                                       String newCommandTimeoutValue, String unicodeKeyBoardValue, String resetKeyBoardValue,
                                       String fullResetValue, String noResetValue, String portNumberValue) throws Exception {
        cap = new DesiredCapabilities();
        cap.setCapability("deviceName", deviceNameValue);
        cap.setCapability("udid", uniqueDeviceIdValue);
        cap.setCapability("platformName", platformNameValue);
        cap.setCapability("appPackage", appPackValue);
        cap.setCapability("appActivity", appActivityValue);
        cap.setCapability("newCommandTimeout", newCommandTimeoutValue);
        cap.setCapability("unicodeKeyboard", unicodeKeyBoardValue);
        cap.setCapability("resetKeyboard", resetKeyBoardValue);
        cap.setCapability("fullReset", fullResetValue);
        cap.setCapability("noReset", noResetValue);
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:" + portNumberValue + "/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Runtime.getRuntime().exec(ADB + " -s " + uniqueDeviceIdValue + " logcat -c");
    }

    @BeforeClass
    public void beforeClassBaseSetUp() {
        logger = Logger.getLogger("NORWEGIAN-->  ");
        PropertyConfigurator.configure("Log4j.properties");
        logger.setLevel(Level.DEBUG);
    }

    @AfterSuite
    public void afterSuitePerformed() {
        driver.quit();
        driver = null;
    }
}
