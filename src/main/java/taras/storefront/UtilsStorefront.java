package taras.storefront;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import taras.adminPanel.UtilsAdm;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import java.time.Duration;
import java.util.List;

import static taras.constants.DriverProvider.getDriver;

public class UtilsStorefront extends AbstractPage {
    public UtilsStorefront() {
        super();
    }

    public static void closeNotificationIfExists() {    //Пришлось это добавить из-за ошибки CS-Cart в 4.18.4
        List<WebElement> notification = getDriver().findElements(By.cssSelector(".notification-body-extended"));

        if (!notification.isEmpty()) {
            getDriver().findElement(By.cssSelector(".cm-notification-close bdi")).click();

            new WebDriverWait(getDriver(), Duration.ofSeconds(2)).until(driver -> true);

            List<WebElement> closeNotification_AlertSuccess = getDriver()
                    .findElements(By.cssSelector(".close.cm-notification-close"));

            if (!closeNotification_AlertSuccess.isEmpty())
                closeNotification_AlertSuccess.getFirst().click();
        }
    }

    public static void waitForSpinnerDisappear() {
        (new WebDriverWait((getDriver()), Duration.ofSeconds(10)))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajax_loading_box[style = 'display: block;']")));
        UtilsAdm.makePause(2000);
    }

    public static void hoverOverElement(WebElement webElement) {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(webElement).perform();
    }
}