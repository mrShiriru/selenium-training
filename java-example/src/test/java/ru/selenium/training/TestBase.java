package ru.selenium.training;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class TestBase {

    public EventFiringWebDriver driver;
    //public WebDriver driver;
    public WebDriverWait wait;
    public Alert alert;

    @Before
    public void start(){

//        DesiredCapabilities ff = DesiredCapabilities.firefox();
//        ff.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
////        ff.setCapability("unexpectedAlertBehaviour", "dismiss");
//        WebDriver driver = new FirefoxDriver(ff);


//        driver = new ChromeDriver();
//        driver = new FirefoxDriver();

        driver = new EventFiringWebDriver(new FirefoxDriver());
        MyListener myListener = new MyListener();
        driver.register(myListener);

        //driver = new InternetExplorerDriver();
        wait = new WebDriverWait(driver, 10);

//        alert = (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.alertIsPresent());


        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void loginAsAdmin() {
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).submit();
    }

    public void loginAsAdmin(String username, String password) {
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).submit();
    }

    boolean areElementsPresent(By locator, int time) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        boolean a = driver.findElements(locator).size() > 0;
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        return a;
    }




    public boolean isElementPresent (By locator){
        try {
            wait.until( d-> d.findElement(locator) );
            //driver.findElement(locator);
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    boolean areElementsPresent(By locator) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        return driver.findElements(locator).size() > 0;
    }


    public void waitForPageToLoad() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int timeWaitedInMilliseconds = 0;
        int maxWaitTimeInMilliseconds = 2000;

        while (timeWaitedInMilliseconds < maxWaitTimeInMilliseconds) {
            if (js.executeScript("return document.readyState").equals("interactive")) {
                System.out.println("Waited interactive: " + timeWaitedInMilliseconds);
                break;
            }
            waitElementsReload(100);
            timeWaitedInMilliseconds += 100;
        }

        timeWaitedInMilliseconds = 0;
        while (!js.executeScript("return document.readyState").equals("complete")) {
            //System.out.println("waiting !!!!");
            waitElementsReload(500);
            timeWaitedInMilliseconds += 500;
            if (timeWaitedInMilliseconds == 10000) {
                break;
            }
        }
    }


    public void waitForAjaxRequests() {
        final By uiPageBlockerJSLocator = By.xpath("//div[@class='uiPageBlockerJS']");

        wait.until((ExpectedCondition<Boolean>) d -> {
            JavascriptExecutor js = (JavascriptExecutor) d;
            Boolean readyState = (Boolean) js.executeScript("return document.readyState == 'complete'");
            Boolean requestsAreFinished = (Boolean) js.executeScript("return !!window.jQuery && window.jQuery.active == 0");
            Boolean blockedIsHidden = (Boolean) js.executeScript("return document.getElementsByClassName('uiPageBlockerJS').length < 1 || document.getElementsByClassName('uiPageBlockerJS')[0].getAttribute('style') == undefined || (document.getElementsByClassName('uiPageBlockerJS')[0].getAttribute('style') != undefined && !document.getElementsByClassName('uiPageBlockerJS')[0].getAttribute('style').includes('block'))");
            return requestsAreFinished && readyState && blockedIsHidden;
        });
        ExpectedConditions.visibilityOfAllElementsLocatedBy(uiPageBlockerJSLocator);
    }


    /**
     * thread sleep
     *
     * @param ms time in milliseconds
     */
    protected void waitElementsReload(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            throw new IllegalArgumentException("error"+ e);
        }
    }





    @After
    public void Stop(){
        driver.quit();
        //WebDriverUtils.killDrivers();
        driver = null;
    }

}
