package ru.selenium.training;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class MySecondTest {


    @Test
    public void mySecondTest(){
        WebDriver d = new FirefoxDriver();
        WebDriver d2 = new ChromeDriver();
        d.navigate().to("http://www.google.com/");
        d2.get("http://www.google.com/");
    }
}
