package ru.selenium.training;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class UploadFile extends TestBase {


    @Test
    public void uploadFileTest(){

        // Открыть страницу (в данном случае файл на локальной машине)
        driver.get("C:\\workspace\\testPage\\index.html");

        waitForPageToLoad();

        // Найти на странице элемент для загрузки файла
        WebElement fileInput = driver.findElement(By.id("file"));

        System.out.println("first result: " +fileInput.getAttribute("value"));


        // Указать элементу путь до файла (на диске)
        fileInput.sendKeys("C:\\workspace\\testPage\\loveis.jpg");

        System.out.println("second result: "+ fileInput.getAttribute("value"));
        // Найти на странице кнопку отправки формы и нажать её
        driver.findElement(By.id("submit")).click();

        System.out.println("third result: "+ driver.findElement(By.id("file")).getAttribute("value"));


    }



}
