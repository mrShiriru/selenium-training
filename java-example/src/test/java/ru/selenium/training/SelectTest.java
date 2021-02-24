package ru.selenium.training;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.ui.Select;
import ru.selenium.training.TestBase;

import java.util.List;

public class SelectTest extends TestBase {



    @Test
    public void selectElement() {

        driver.get("https://www.testandquiz.com/selenium/testing.html");

        By locator = By.xpath("//select");

        WebElement selectElem = driver.findElement(locator);
        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(selectElem);

        WebElement selectedOption = select.getFirstSelectedOption();
        System.out.println("selectedOption1 :"+ selectedOption.getAttribute("value"));

        List<WebElement> options = select.getOptions();
        System.out.println("sise options:"+ options.size());
        List<WebElement> selectedAllOptions = select.getAllSelectedOptions();
        boolean isSelectMultiple = select.isMultiple();


        select.selectByIndex(1);
        selectedOption = select.getFirstSelectedOption();
        System.out.println("selectedOption2 :"+selectedOption.getText());
        //System.out.println("1 element"+selectedOption.getAttribute("value"));


        select.selectByVisibleText("Database Testing");
        selectedOption = select.getFirstSelectedOption();
        System.out.println("selectedOption3 :"+selectedOption.getText());


        select.selectByValue("Automation");
        selectedOption = select.getFirstSelectedOption();
        System.out.println("selectedOption4 :"+selectedOption.getText());
    }

    @Test
    public void selectMultipleElement() {

        driver.get("https://demoqa.com/select-menu");

        By locator = By.xpath("//select[@id='cars']");

        WebElement selectElem = driver.findElement(locator);
        ((Locatable) selectElem).getCoordinates().inViewPort();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollTo({top: 200, behavior: \"smooth\"})");
        js.executeScript("javascript:window.scrollTo(0, 200)");

        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(selectElem);

        if(select.isMultiple()) {
            select.selectByIndex(1);
            select.selectByIndex(2);
        }

        List<WebElement> selectedAllOptions = select.getAllSelectedOptions();
        System.out.println("size ="+ selectedAllOptions.size());

        if(select.isMultiple()) {
            select.deselectAll();
        }
        selectedAllOptions = select.getAllSelectedOptions();
        System.out.println("size ="+ selectedAllOptions.size());

    }


    public void datePicker(By calendarLocator,By datePickerLocator, String date) {

        wait.until(d -> isElementPresent(calendarLocator));
        WebElement calendarButton = driver.findElement(calendarLocator);
        calendarButton.click();

        WebElement datePicker = driver.findElement(datePickerLocator);
        datePicker.sendKeys(Keys.HOME + date);
        waitForAjaxRequests();

        By selectedDateLocator = By.xpath("");
        WebElement preselectedDate = driver.findElement(selectedDateLocator);
        preselectedDate.click();
    }


}
