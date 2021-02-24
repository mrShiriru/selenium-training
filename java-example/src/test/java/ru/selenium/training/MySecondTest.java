package ru.selenium.training;

import minaeva.MyWaitHW;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class MySecondTest extends TestBase {



    @Test
    public void FindElementsTest(){

        driver.navigate().to("http://localhost:8090/litecart/");
        //WebElement q = driver.findElement(By.name("query"));
        //Assert.assertFalse(areElementsPresent(By.name("XXX")));
        Assert.assertFalse(isElementPresent(By.xpath("//input[@name ='XXX'")));

    }

    @Test
    public void WaitElementTest(){
        driver.get("http://localhost:8090/litecart/");
        String xpath = "//section[@id='box-campaign-products']//a[@class='link' and @data-name='Yellow Duck']";
        driver.findElement(By.xpath(xpath)).click();
        xpath = "//button[@name='add_cart_product']";
        assertTrue(isElementPresent(By.xpath(xpath)));
    }


    @Test
    public void WaitSecondElementTest(){
        driver.get("https://voronezh.cian.ru/");
        String xpath = "//a[@data-mark='FiltersSearchButton' and .='Найти']";
        //xpath = "//*[@id='_025a50318d--mainpage--3vyA6']/div/div[1]/div[2]/div[1]/div[1]/div/div/div[3]/span/span[2]";
        driver.findElement(By.xpath(xpath)).click();
        xpath = "//article[@data-name='CardComponent']";
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        assertTrue(isElementPresent(By.xpath(xpath)));
    }


    @Test
    public void SearchСontext(){
        driver.get("http://localhost:8090/litecart/create_account");
        driver.findElement(By.xpath("//a[@class='dropdown-toggle' and contains(.,'Sign In')]")).click();
        WebElement form = driver.findElement(By.xpath("//form[@name='login_form']"));
        WebElement pass = form.findElement(By.xpath(".//input[@type='password']"));
        //assertTrue("ERROR:element on the page is not found",isElementPresent(form, By.xpath(".//input[@type='password']")));
        pass.sendKeys("12345");
    }


    //getAttribute

    @Test
    public void getAttributeTest(){
        driver.get("http://localhost:8090/litecart/admin");

        Assert.assertTrue(isElementPresent(By.name("login")));
        WebElement loginButton =driver.findElement(By.xpath("//button[@name='login']"));

        String buttonValue = loginButton.getAttribute("value");
        System.out.println("login button: "+ buttonValue);

        System.out.println("outerHTML ="+ loginButton.getAttribute("outerHTML") );
    }


    @Test
    public void getAttributeValueTest(){
        driver.get("http://localhost:8090/litecart");
        WebElement searchfield = driver.findElement(By.xpath("//input[@class='form-control' and @name='query']"));

        String beforeText = searchfield.getAttribute("value");
        searchfield.sendKeys("BIG SALE!!!");

        String afterText = searchfield.getAttribute("value");
        String text =  searchfield.getText();

        System.out.println("before: '"+beforeText +"', after: '"+ afterText+ "', text: '"+text+"'");
        //assertTrue("ERROR:element on the page is not found",isElementPresent(form, By.xpath(".//input[@type='password']")));

    }

    @Test
    public void getAttributeHrefTest(){
        final By panelLocator = new By.ByXPath("//ul[@id='box-apps-menu']//li[contains(@class,'app')]/a");
        final By dashboardLocator = new By.ByXPath("//ul[@id='top-bar']//a[.='Dashboard']");

        driver.get("http://localhost:8090/litecart/admin");

        loginAsAdmin();
        Assert.assertTrue(isElementPresent(panelLocator));
        WebElement firstPanel = wait.until(ExpectedConditions.elementToBeClickable(panelLocator));
        firstPanel.click();

        Assert.assertTrue(isElementPresent(dashboardLocator));
        WebElement dashboard  = wait.until(ExpectedConditions.elementToBeClickable(dashboardLocator));
        String href = dashboard.getAttribute("href");

        System.out.println("href: '"+ href +"'");
    }

    @FindBy (xpath = "//span[text()='Diagnosen']")
    private WebElement button;


    @Test
    public void getAttributeSelectedTest(){
        final By changeLocator = By.xpath("//div[@id='region']//a[.='Change']");
        final By currencyUSDLocator = By.xpath("//select[@name='currency_code']//option[@value='EUR']");

        driver.get("http://localhost:8090/litecart");

        WebElement changeLink  = wait.until(ExpectedConditions.elementToBeClickable(changeLocator));
        changeLink.click();


        Assert.assertTrue(isElementPresent(currencyUSDLocator));
        System.out.println("result: "+driver.findElement(currencyUSDLocator).getAttribute("selected"));
        System.out.println("result: "+driver.findElement(currencyUSDLocator).getAttribute("selected22322"));
    }

}
