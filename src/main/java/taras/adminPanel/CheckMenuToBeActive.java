package taras.adminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import taras.constants.DriverProvider;

public interface CheckMenuToBeActive {

    default void checkMenuToBeActive(WebElement menu) {
        if(DriverProvider.getDriver().findElements(By.xpath(String.valueOf(menu))
                .xpath("/../..//a[contains(@class, 'main-menu-1__toggle--active')]")).isEmpty()) {
            menu.click();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
