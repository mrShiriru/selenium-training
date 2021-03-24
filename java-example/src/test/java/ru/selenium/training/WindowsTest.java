package ru.selenium.training;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;

public class WindowsTest extends TestBase {



    @Test
    public void test1(){

        driver.get("https://www.selenium.dev/documentation/en/webdriver/");

        //получить дескриптор (идентификатор) текущего открытого окна
        String mainWindow = driver.getWindowHandle();


        //Получить набор дескрипторов текущих открытых окон
        Set<String> oldWindowsSet = driver.getWindowHandles();


        //Для того, чтобы открыть новое окно нужно выполнить JavaScript код:
        //Примечание: при открытии нового окна Селениум в него не переключается!
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open()");


        //Получить набор дескрипторов текущих открытых окон
        Set<String> newWindowsSet = driver.getWindowHandles();


        // получаем дескриптор нового окна
        newWindowsSet.removeAll(oldWindowsSet);
        String newWindowHandle = newWindowsSet.iterator().next();


        //переключаем фокус для будущих команд драйвера в окно с заданным дескриптором
        driver.switchTo().window(newWindowHandle);


        driver.get("https://jqueryui.com/resources/demos/sortable/connect-lists.html");


        //закрываем текущее активное окно
        driver.close();

        //Selenium будет считать окно активным даже после его закрытия
        try {
            driver.getWindowHandle();
        } catch (NoSuchWindowException e){
            System.out.println("Window has been closed");
        }

        //поэтому необходимо снова переключится на окно, в котором продожим выполнять действия
        driver.switchTo().window(mainWindow);
    }


    @Test
    public void test2(){

        driver.get("https://demoqa.com/browser-windows");

        //получить дескриптор (идентификатор) текущего открытого окна
        String mainWindow = driver.getWindowHandle();

        //получить список окон
        Set<String> oldWindowsSet = driver.getWindowHandles();

        By tabLocator = By.xpath("//button[@id='tabButton1']");

        WebElement tabButton = driver.findElement(tabLocator);
        tabButton.click();

        // ожидаем открытия и получаем дескриптор нового окна
        String newTab = wait.until( d -> getOpenedWindow(oldWindowsSet));

        //или используем ожидание из класса ExpectedConditions
//        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
//        String newTab = getOpenedWindow(oldWindowsSet);


       driver.switchTo().window(newTab);

        By headerLocator = By.xpath("//h1[@id='sampleHeading']");
        System.out.println(driver.findElement(headerLocator).getText());

        driver.close();
        driver.switchTo().window(mainWindow);
    }


    public String getOpenedWindow(Set<String> oldWindowsSet){
        Set<String> newWindowsSet = driver.getWindowHandles();
        newWindowsSet.removeAll(oldWindowsSet);

        return newWindowsSet.size() > 0 ? newWindowsSet.iterator().next() : null;
    }


}
