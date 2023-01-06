package taras.constants;

import org.openqa.selenium.WebDriver;

public class DriverProvider {

    private static WebDriver driver;

    public static WebDriver getDriver(){
        if(driver == null){
            driver = getBrowserType();
        }
        return driver;
    }

    private static WebDriver getBrowserType(){
        BrowserFactory browserFactory = new BrowserFactory();
        return browserFactory.createDriver();
    }

    private DriverProvider(){}

    public static void destroyDriver(){
        driver = null;
    }
}
