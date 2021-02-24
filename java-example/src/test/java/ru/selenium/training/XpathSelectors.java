package ru.selenium.training;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class XpathSelectors extends TestBase {


    /*
    * Правила построения локаторов:
    *
    * 1. использовать максимально точные критерии выбора
    * 2. избегать использование порядковых номеров
    * 3. Привязка к ближайшему уникальному элементу
    * 4. минимум прыжков по DOM
    *
    */


    //"Пункт 1"

    //плохо
    String xpath = "//body/div";

    // не очень хорошо:
    String xpath1 = "//*[@id='_025a50318d--mainpage--3vyA6']/div";




    //Пункт 2
    //плохо
    String xpath2 = "//*[@id='_025a50318d--mainpage--3vyA6']/div/div[1]/div[2]/div[1]/div[1]/div/div/div[3]/span/span[2]";

    //Не очень хорошо:
    public void badReadTable(){

        WebElement table = driver.findElement(By.xpath("//table[@name='users']"));
                List<WebElement> rows = table.findElements(By.xpath("./tr"));

        //for row in rows:
        for (int i = 0; i < rows.size(); i++){
            String name = rows.get(i).findElement(By.xpath("./td[1]")).getText();
            String email = rows.get(i).findElement(By.xpath("./td[2]")).getText();
        }
    }

    //Хорошо
    public void readTable (){
        WebElement table = driver.findElement(By.xpath("//table[@name='users']")) ;
        List<WebElement> rows = table.findElements(By.xpath("./tr")) ;
        for (int i = 0; i < rows.size(); i++){
            List<WebElement> cells = rows.get(i).findElements(By.xpath("./td"));
            String name = cells.get(0).getText();
            String email = cells.get(1).getText();
        }
    }

    //отлично
    public void readTable2 (){
        WebElement table = driver.findElement(By.xpath("//table[@name='users']")) ;
        List<WebElement> rows = table.findElements(By.xpath("./tr")) ;
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.xpath("./td"));
            String name = cells.get(0).getText();
            String email = cells.get(1).getText();
        }
    }

    //  - /following-sibling::, двойные кавычки, * и так далее
    // - //following-sibling::





}
