package ru.selenium.training;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;



public class MyFirstTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        //System.setProperty("webdriver.chrome.driver", "C:\\workspace\\chromedriver.exe");
        //System.setProperty("webdriver.gecko.driver", "C:\\workspace\\geckodriver.exe");
        //FirefoxOptions firefoxOptions = new FirefoxOptions().setBinary("C:\\Program Files\\Mozilla Firefox68\\firefox.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void MyFirstTest(){

        driver.get("http://www.google.com/");
        //WebElement q2 = driver.findElement(By.xpath("//inptut[@name='q'"));
        WebElement q = driver.findElement(By.name("q"));
        driver.navigate().refresh();
        q.sendKeys("webdriver");
        //List<WebElement> alist = driver.findElements(By.name("q"));
        driver.findElement(By.name("btnK")).click();
        System.out.println(driver.getTitle());
        String text2 = "webdriver - Поиск в Google";
        wait.until(titleIs(text2));
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

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
