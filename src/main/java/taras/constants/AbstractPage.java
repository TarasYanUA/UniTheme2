package taras.constants;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AbstractPage {
    public WebDriver driver;

    public AbstractPage(){
        this.driver = DriverProvider.getDriver();
        PageFactory.initElements(driver, this);
    }
}
