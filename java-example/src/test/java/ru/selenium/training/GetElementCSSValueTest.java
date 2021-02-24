package ru.selenium.training;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Locatable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GetElementCSSValueTest extends TestBase {


    @Test
    public void leftPanel(){

        driver.get("http://localhost:8090/litecart/admin");
        loginAsAdmin();

        waitForPageToLoad();

        By locator = By.xpath("//div[@id='sidebar']//ul[@id='box-apps-menu']/li/");
        By locator3 = By.xpath("//li//span");
        List<WebElement> list = driver.findElements(locator);

        for(int i=0; i< list.size(); i++){

            WebElement panel = getElementWithIndex(locator3, i);

            System.out.println(panel.getText());
            panel.click();


        }
    }

    private WebElement getElementWithIndex(By by, int indx) {
        return driver.findElements(by).get(indx);
    }



        @Test
    public void getCssValueTest(){

        final By ordersPanelLocator = By.xpath("//div[@id='widget-orders']//div[@class='panel-heading']");

        driver.get("http://localhost:8090/litecart/admin");
        loginAsAdmin();

        waitForPageToLoad();

        WebElement orderPanel = driver.findElement(ordersPanelLocator);

        String color = orderPanel.getCssValue("color");
        System.out.println("color is "+color);

        color = orderPanel.getCssValue("background-color");
        System.out.println("background-color is "+color);

        color = orderPanel.getCssValue("border-color");
        System.out.println("border-color is "+color);

//        color = a.getCssValue("border-top-color");
//        System.out.println("border-top-color "+color);


    }



    @Test
    public void getSizeAndLocationElementTest() {

        final By ordersPanelLocator = By.xpath("//div[@id='widget-orders']//div[@class='panel-heading']");

        driver.get("http://localhost:8090/litecart/admin");
        loginAsAdmin();

        waitForPageToLoad();

        WebElement orderPanel = driver.findElement(ordersPanelLocator);
        //size of element in px
        Dimension size = orderPanel.getSize();

        //Returns: A point, containing the location of the top left-hand corner of the element
        Point location = orderPanel.getLocation();

        System.out.println("size: " + size);
        System.out.println("location: " + location);

        //secret function that work in Java:
        //finds out the coordinates of an element relative to the top left corner of the monitor
        //scrolling to the element
        ((Locatable) orderPanel).getCoordinates().inViewPort();

    }

    public void scrollIntoView(WebElement element) {
        ((Locatable) element).getCoordinates().inViewPort();

    }


}
