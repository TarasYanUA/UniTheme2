package taras.adminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import taras.constants.DriverProvider;

public interface CheckMenuToBeActive {

    default void checkMenuToBeActive(WebElement menu) {
        if(DriverProvider.getDriver().findElements(By.xpath("//span[text()='_ab__addons']/../..//a[contains(@class, ' collapsed')]")).isEmpty()) {
            DriverProvider.getDriver().findElement(By.xpath("//span[text()='_ab__addons']")).click();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if(DriverProvider.getDriver().findElements(By.xpath(String.valueOf(menu))
                .xpath("/../..//a[contains(@class, ' collapsed')]")).isEmpty()) {
            menu.click();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
