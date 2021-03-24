package ru.selenium.training;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.io.File;
import java.io.IOException;


public class MyListener extends AbstractWebDriverEventListener  {



    @Override
    public void onException (Throwable throwable, WebDriver driver){
        System.out.println(throwable);

        //Obtain the screenshot into a temporary file that will be deleted once the JVM exits.
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        System.out.println("tmp: "+tmp);

        File screen = new File("screenshots/screen"+ System.currentTimeMillis() +".png");
        System.out.println("screen: "+screen);

        try {
            Files.copy(tmp, screen);
            System.err.println("Screenshot saved to: " + screen.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Obtain the screenshot as raw bytes.
//        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//        System.out.println("Byte screen" +screenshot);

    }

}
