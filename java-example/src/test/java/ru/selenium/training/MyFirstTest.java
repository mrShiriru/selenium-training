package ru.selenium.training;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;



public class MyFirstTest extends TestBase {

//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    @Before
//    public void start(){
//        driver = new FirefoxDriver();
//        wait = new WebDriverWait(driver, 10);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    }

    @Test
    public void MyFirstTest() throws InterruptedException {

        driver.navigate().to("http://www.google.com/");
        WebElement q = driver.findElement(By.name("query"));
        //driver.navigate().refresh();
        q.sendKeys("webdriver");





        driver.findElement(By.name("query")).findElement(By.name("query")).findElement(By.name("query")).sendKeys();


        List<WebElement> alist = driver.findElements(By.xpath("//span[@class='label' and contains(.,'Atherosklerotische')]"));

        String a = alist.get(0).getAttribute("innerText");


        driver.findElement(By.name("btnK")).click();
        System.out.println(driver.getTitle());
        String text = "webdriver - Поиск в Google";
        String text2 = "webdriver - Google Search";
        wait.until(titleIs(text));
        wait(10);
    }








    @Test
    public void MySecondTest(){
        driver.get("http://www.google.com/");
        driver.findElement(By.id("gs_ok0")).click();
        driver.findElement(By.id("k32")).click();
        driver.findElement(By.id("gs_ok0")).click();
        driver.findElement(By.id("k32")).click();
        //-Dfile.encoding=UTF-8
    }

    @Test
    public void MyThirdTest(){
        driver.navigate().to("http://localhost:8090/litecart/");
        WebElement q = driver.findElement(By.name("query"));
        List<WebElement> list = driver.findElements(By.tagName("input"));
        String box = driver.findElement(By.id("box-cookie-notice")).getAttribute("innerText");
        WebElement q3 = driver.findElement(By.linkText("Change"));

    }


    @Test
    public void MyTest(){
        driver.get("http://localhost:8090/litecart/admin/");
        login("admin","admin");
        assertTrue(isElementPresent(By.xpath("//li[@class='app']")));

        List<WebElement> array = driver.findElements(By.xpath("//li[@class='app']"));
        for(WebElement ar: array ){
            ar.click();
        }


    }


    private void login(String name, String password){
        driver.findElement(By.name("username")).sendKeys(name);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).submit();
    }



//
//    @After
//    public void stop(){
//        driver.quit();
//        driver = null;
//    }
}
