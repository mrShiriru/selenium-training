package ru.selenium.training;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FrameTest extends TestBase {


    /**
     * Info:
     *
     * IFrame — это веб-страница, встроенная в другую веб-страницу, или документ HTML, встроенный в другой документ HTML.
     * IFrame часто используется для вставки содержимого из другого источника, такого как реклама, в веб-страницу.
     * Тег < iframe > указывает встроенный фрейм.
     *
     * Для того, чтобы начать работать с контентом фрейма нужно указать драйверу с какой DOM-моделью необходимо работать.
     * Для этого используется метод switchTo().
     * В качестве параметров он принимает индекс, имя или сам iframe элемент:
     *
     * driver.switchTo().frame(0);
     * driver.switchTo().frame("frameName");
     * driver.switchTo().frame(webElement);
     *
     * Чтобы вернуться к родительскому фрейму, вы можете использовать switchTo().parentFrame()
     * Если вы хотите вернуться к основному фрейму, вы можете использовать switchTo().defaultContent();
     *
     *
     * Страницы для тестов:
     * https://snipp.ru/jquery/masked-input
     * http://frame.free.nanoquant.ru/
     * http://demo.guru99.com/test/guru99home/
     * https://jsbin.com/kitaqaf/edit?html,output
     */




    @Test
    public void test1(){

        driver.get("http://frame.free.nanoquant.ru/");




        int size = driver.findElements(By.tagName("frame")).size();
        System.out.println(size);

        for (int i=0 ; i< size-1; i++){
            driver.switchTo().frame(i);
            if (driver.findElements(By.xpath("//h1")).size()>0){
                System.out.println(driver.findElement(By.xpath("//h1")).getText());
            }else
                driver.switchTo().parentFrame();
        }


//        WebElement header;
//        try{
//            header = driver.findElement(By.xpath("//h1"));
//        } catch (NoSuchElementException e){
//            System.out.println("Header H1 was not found");
//        }
//
////        driver.switchTo().frame(2);
////        driver.switchTo().frame("content");
//        WebElement frameContent = driver.findElement(By.xpath("//frame[@src='center.html']"));
//        driver.switchTo().frame(frameContent);
//
//        header = driver.findElement(By.xpath("//h1"));
//        System.out.println(header.getText());
//
////        driver.switchTo().parentFrame();
//        driver.switchTo().defaultContent();




    }

    /**
     * Ожидание с проверкой является ли фрейм доступным для переключения, и если доступен, то переключает драйвер во фрейм.
     */
    @Test
    public void test2(){

        driver.get("http://frame.free.nanoquant.ru/");

        By contentLocator = By.xpath("//frame[@src='center.html']");
        WebElement frameContent = driver.findElement(By.xpath("//frame[@src='center.html']"));


        driver.switchTo().frame("<proxy>.JS Bin Output ");

        //driver.switchTo().frame(0);

        (new WebDriverWait(driver, 5)).
                until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(contentLocator));

        try{
            WebElement header = driver.findElement(By.xpath("//h1"));
            System.out.println(header.getText());
        } catch (NoSuchElementException e){
            System.out.println("Header H1 was not found");
        }
    }


}
