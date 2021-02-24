package ru.selenium.training;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;

public class MyDisplayedTest extends TestBase {


    @Test
    public void getElementDisplayedTest(){

        final By hideMessageLocator = By.xpath("//div[@id='notices']");

        driver.get("http://localhost:8090/litecart/admin");

        loginAsAdmin("admin123", "admin");

        waitForPageToLoad();

        driver.findElement(hideMessageLocator).isDisplayed();
        System.out.println("1 ="+ new SimpleDateFormat("dd.MM.yyyy HH:mm::ss").format(new Date()));
        driver.findElement(hideMessageLocator).isDisplayed();
        waitToBeInvisible(hideMessageLocator);
        System.out.println("2 ="+ new SimpleDateFormat("dd.MM.yyyy HH:mm::ss").format(new Date()));

    }


    public void waitToBeInvisible(By locator) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }





}
