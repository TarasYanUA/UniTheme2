package taras.adminPanel;

import org.openqa.selenium.By;
import taras.constants.DriverProvider;

public interface CheckPageOnEngLang {

    default void checkPageOnEngLang(){
        if(DriverProvider.getDriver().findElements(By.cssSelector("#sw_select_en_wrap_content")).isEmpty()){
            DriverProvider.getDriver().findElement(By.cssSelector("a[id^='sw_select_'][id$='_wrap_content']")).click();
            DriverProvider.getDriver().findElement(By.cssSelector(".popup-icons a[name='en']")).click();
        }
    }
}
