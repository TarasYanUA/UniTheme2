package taras.adminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import taras.constants.DriverProvider;
import java.util.List;

public interface CheckMenuToBeActive {
    static void checkMenu_Addons_ToBeActive() {

        // Проверяем наличие хотя бы одного из элементов
        List<WebElement> firstElement = DriverProvider.getDriver().findElements(By.xpath("//span[text()='_ab__addons']/../../..//div[contains(@class, 'in collapse')]"));
        List<WebElement> secondElement = DriverProvider.getDriver().findElements(By.xpath("//span[text()='_ab__addons']/../..//a[contains(@class, 'main-menu-1__toggle--active')]"));

        if (!firstElement.isEmpty() || !secondElement.isEmpty()) {
            // Если хотя бы один элемент присутствует, выполняем действие
            DriverProvider.getDriver().findElement(By.xpath("//span[text()='_ab__addons']")).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

        /*if(!DriverProvider.getDriver().findElements(By.xpath("//span[text()='_ab__addons']/../../..//div[contains(@class, 'in collapse')]")).isEmpty() ||
                !DriverProvider.getDriver().findElements(By.xpath("//span[text()='_ab__addons']/../..//a[contains(@class, 'main-menu-1__toggle--active')]")).isEmpty()) {
            DriverProvider.getDriver().findElement(By.xpath("//span[text()='_ab__addons']")).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }*/


    default void checkMenuToBeActive(WebElement menu) {
        checkMenu_Addons_ToBeActive();
        if (DriverProvider.getDriver().findElements(By.xpath(String.valueOf(menu))
                .xpath("/../..//a[contains(@class, 'active')]")).isEmpty()) {
            menu.click();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    default void checkMenu_Products_ToBeActive() {
        checkMenu_Addons_ToBeActive();
        if (DriverProvider.getDriver().findElements(By.cssSelector("a[href$='products.manage'].main-menu-1__link ~ a[class*='main-menu-1__toggle--active']")).isEmpty())
            DriverProvider.getDriver().findElement(By.cssSelector("a[href$='products.manage'].main-menu-1__link")).click();
    }
}