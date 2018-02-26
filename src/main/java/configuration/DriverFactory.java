package configuration;

import io.appium.java_client.android.AndroidDriver;
import utility.ConfigReader;
import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class DriverFactory {


    private final static Logger logger = Logger.getLogger(DriverFactory.class);

    public static WebDriver createInstance(String deviceName, String platform) {
        WebDriver driver = null;
        if (platform.equalsIgnoreCase("IOS")) {
            try {
                File appDir = new File(System.getProperty(ConfigReader.getProperty("classpath")));
                File app = new File(appDir, ConfigReader.getProperty("applicationDir"));
                File iOSApp = new File(app, "HitList.app");

                DesiredCapabilities capabilities = new DesiredCapabilities();
                //mandatory capabilities
                capabilities.setCapability("deviceName", deviceName);
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("automationName", "XCUITest");
                capabilities.setCapability("app", iOSApp.getAbsolutePath());
                capabilities.setCapability("newCommandTimeout", 60000);
                driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

            } catch (Exception e) {
                logger.error(e + " occurs while accessing url....");
                return null;
            }
        }
        return driver;
    }
}
