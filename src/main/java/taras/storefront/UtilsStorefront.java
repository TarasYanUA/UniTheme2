package taras.storefront;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import java.time.Duration;
import java.util.List;

public class UtilsStorefront extends AbstractPage {
    public UtilsStorefront() {
        super();
    }


    public static void closeNotificationIfExists() {    //Пришлось это добавить из-за ошибки CS-Cart в 4.18.4
        List<WebElement> notification = DriverProvider.getDriver().findElements(By.cssSelector(".notification-body-extended"));

        if (!notification.isEmpty()) {
            DriverProvider.getDriver().findElement(By.cssSelector(".cm-notification-close bdi")).click();

            new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(2)).until(driver -> true);

            List<WebElement> closeNotification_AlertSuccess = DriverProvider.getDriver()
                    .findElements(By.cssSelector(".close.cm-notification-close"));

            if (!closeNotification_AlertSuccess.isEmpty())
                closeNotification_AlertSuccess.getFirst().click();
        }
    }
}