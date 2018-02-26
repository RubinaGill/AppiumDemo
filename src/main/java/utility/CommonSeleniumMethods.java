package utility;

import configuration.DriverFactory;
import configuration.DriverManager;
import io.appium.java_client.*;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.Response;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CommonSeleniumMethods {

    final static Logger logger = Logger.getLogger(CommonSeleniumMethods.class);

    // This method will initiate device instance
    public boolean startDriver(String deviceName, String platform) {
       WebDriver driver = DriverFactory.createInstance(deviceName,platform);
        try {
            DriverManager.setWebDriver(driver);
            return true;
        } catch (Exception e) {
            logger.error(e + " occurs while launching app....");
            return false;
        }
    }

    public String setPlatform(String deviceName) {
        String platform = null;
        if(deviceName.startsWith("i")) {
            platform = "IOS";
        }else{
            platform = "Android";
        }
        return platform;
    }


    // This method will close the driver instance
    public boolean stopDevice() {
        WebDriver driver=DriverManager.getDriver();
        try {
            driver.quit();
            return true;
        } catch (Exception e) {
            logger.error(e + " occurs while stopping browser....");
            return false;
        }
    }

    //This method will enter text in textbox
    public boolean enterText(String text, By locator) {
        WebDriver driver=DriverManager.getDriver();
        try {
            driver.manage().timeouts().implicitlyWait(60000,TimeUnit.MILLISECONDS);
            WebElement textBox = driver.findElement(locator);
            textBox.clear();
            textBox.sendKeys(text);
            return true;
        } catch (Exception e) {
            logger.error("Exception occurred while entering text" + e);
            return false;
        }
    }

    //This method will click Button
    public boolean clickButton(By locator) {
        WebDriver driver=DriverManager.getDriver();
        try {
            driver.manage().timeouts().implicitlyWait(60000,TimeUnit.MILLISECONDS);
            driver.findElement(locator).click();
            return true;
        } catch (Exception e) {
            logger.error("Exception occurred while clicking button");
            e.printStackTrace();
            return false;
        }
    }

    //This method will wait until the page is completely loaded

    public boolean waitIfLoaderIsRunning(By locatorPath) throws InterruptedException {
        WebDriver driver=DriverManager.getDriver();
        int loaderTimeOut = 0;
        while (true) {
            try {
               // driver.manage().timeouts().implicitlyWait(60000,TimeUnit.MILLISECONDS);
                driver.findElement(locatorPath);
                break;
            } catch (NoSuchElementException exception) {
                Thread.sleep(2000);
                loaderTimeOut++;
                if (loaderTimeOut > 30) {
                    logger.error("***********************timeout for accessing locator "+locatorPath);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean compareText(String expectedText, By locatorPath) throws InterruptedException {
        WebDriver driver=DriverManager.getDriver();
        try {
            driver.manage().timeouts().implicitlyWait(2000,TimeUnit.MILLISECONDS);
           // Assert.assertEquals(expectedText, driver.findElement(locatorPath).getText(), "Text is different");
            return true;
        } catch (Exception e) {
            logger.error("Exception occurred while not getting text" + e);
            e.printStackTrace();
            return false;
        }

    }

    //This method will check whether the button is enabled or disabled

    public boolean isButtonEnabled(By locatorPath) throws InterruptedException {
        WebDriver driver=DriverManager.getDriver();
        try {
            //wait.until(ExpectedConditions.presenceOfElementLocated(locatorPath));
            driver.manage().timeouts().implicitlyWait(60000,TimeUnit.MILLISECONDS);
            return driver.findElement(locatorPath).isEnabled();
        } catch (Exception e) {
            logger.error("Exception occurred while button is not enabled" + e);
            e.printStackTrace();
            return false;
        }

    }

    //This method will wait until the page is completely loaded

    public boolean waitIfButtonISNotEnabledOrVisible(By locatorPath) throws InterruptedException {
        WebDriver driver=DriverManager.getDriver();
        int loaderTimeOut = 0;
        while (true) {
            try {
                boolean b = driver.findElement(locatorPath).isDisplayed();
                if (b == true) {
                    break;
                }
            } catch (NoSuchElementException exception) {
                Thread.sleep(2000);
                loaderTimeOut++;
                if (loaderTimeOut > 30) {
                    logger.error("Time out while waiting");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isElementPresent(By locatorPath) {
        WebDriver driver=DriverManager.getDriver();
        try {
            driver.manage().timeouts().implicitlyWait(6000,TimeUnit.MILLISECONDS);
            return driver.findElement(locatorPath).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Element not found");
            return false;
        }
    }

    public boolean isElementPresentByName(String name) {
        AppiumDriver driver= (AppiumDriver) DriverManager.getDriver();
        int count=0;
     //   WebDriver driver=DriverManager.getDriver();
        try {
            driver.manage().timeouts().implicitlyWait(6000,TimeUnit.MILLISECONDS);
            while(driver.findElement(By.name(name)).isDisplayed()==false&&count<=2) {
                Dimension screenSize = driver.manage().window().getSize();
                int screenHeight = (screenSize.getHeight() / 2) + 100;
                int screenWidth = screenSize.getWidth() / 2;

                TouchAction ta = new TouchAction(driver);
                MultiTouchAction ma = new MultiTouchAction(driver);

                ta.press(screenWidth, screenHeight)
                        .waitAction(Duration.ofNanos(1000))
                        .moveTo(screenWidth, 100)
                        .release();
                ma.add(ta).perform();
                count++;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Element not found "+name);
            return false;
        }
    }

    public boolean isElementPresentByContent(String name) {
        WebDriver driver=DriverManager.getDriver();
        try {
            driver.manage().timeouts().implicitlyWait(6000,TimeUnit.MILLISECONDS);
            By element = MobileBy.AndroidUIAutomator(String.format("new UiSelector().description(\"%s\")", name));
            return driver.findElement(element).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Element not found");
            return false;
        }
    }

    public String getText(By locatorPath) {
        WebDriver driver=DriverManager.getDriver();
        try {
            driver.manage().timeouts().implicitlyWait(60000,TimeUnit.MILLISECONDS);
            String text = driver.findElement(locatorPath).getAttribute("name");
            return text;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("unable to find element");
            return "";
        }
    }

    public boolean scrollPage(){
        WebDriver driver=DriverManager.getDriver();
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, Math.max(document.documentElement.scrollHeight, document.body.scrollHeight, document.documentElement.clientHeight));");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("unable to scroll page");
            return false;
        }
    }

    public boolean isTextPresent(String textToBeSearched) {
        WebDriver driver=DriverManager.getDriver();
        String xpath = "//*[contains(text(),'"+ textToBeSearched+"')]";
        try{
            driver.manage().timeouts().implicitlyWait(60000,TimeUnit.MILLISECONDS);
            driver.findElement(By.xpath(xpath));
            return true;
        }catch (Exception e){
           logger.error("text is not present");
            e.printStackTrace();
            return false;
        }
    }

    public void tapElement(int x, int y){
        AppiumDriver driver= (AppiumDriver) DriverManager.getDriver();
        try{
            new TouchAction(driver).tap(x,y).perform();
        }catch(Exception e){
            logger.error("unable to tap on element");
            e.printStackTrace();
        }
    }

    public void tapElement(By locator){
        AppiumDriver driver= (AppiumDriver) DriverManager.getDriver();
        try{
            new TouchAction(driver).tap(driver.findElement(locator)).perform();
        }catch(Exception e){
            logger.error("unable to tap on element");
            e.printStackTrace();
        }
    }
    public List<String> getAllItemsOfList(By locator){
        WebDriver driver=DriverManager.getDriver();
        List<WebElement> elementList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        elementList = driver.findElements(locator);
        for(WebElement elementName:elementList){
            nameList.add(elementName.getText());
        }
        return nameList;
    }

}