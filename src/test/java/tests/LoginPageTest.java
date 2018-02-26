package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.CommonSeleniumMethods;


public class LoginPageTest {

    //Creating objects
    CommonSeleniumMethods commonSeleniumMethods = new CommonSeleniumMethods();
    String platform = null;


    @BeforeMethod
    public void initiateBrowser(ITestContext context) {
        String deviceName = context.getCurrentXmlTest().getName();
        platform = commonSeleniumMethods.setPlatform(deviceName);
        commonSeleniumMethods.startDriver(deviceName, platform);
    }


    @Test
    public void loginWithValidCredentialsTest() throws Exception {
        Assert.assertTrue(true);
    }

    @Test
    public void loginWithInValidCredentialsTest() throws Exception {
        Assert.assertTrue(false, "test case fails");
    }

    @AfterMethod
    public void quitBrowser() {
        commonSeleniumMethods.stopDevice();
    }
}
