package taras.adminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import java.time.Duration;
import java.util.List;

import static taras.constants.DriverProvider.getDriver;

public class UtilsAdm extends AbstractPage {

    public static void makePause(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void checkPageOnEngLang() {
        if (DriverProvider.getDriver().findElements(By.cssSelector("#sw_select_en_wrap_content")).isEmpty()) {
            DriverProvider.getDriver().findElement(By.cssSelector("a[id^='sw_select_'][id$='_wrap_content']")).click();
            DriverProvider.getDriver().findElement(By.cssSelector("#content_top_navigation .popup-icons a[name='en']")).click();
        }
    }

    public static void clickAndType(WebElement webElement, String value) {
        webElement.click();
        webElement.clear();
        webElement.sendKeys(value);
    }

    public static void setCheckboxState(WebElement checkbox, boolean value) {
        boolean isSelected = checkbox.isSelected();

        if (value != isSelected)
            checkbox.click();
    }

    public static void closeAllNotifications() {
        while (!DriverProvider.getDriver().findElements(By.cssSelector(".cm-notification-close")).isEmpty()) {
            DriverProvider.getDriver().findElements(By.cssSelector(".cm-notification-close")).getFirst().click();
            makePause(500);
        }
    }

    public static void waitForPopUpWindow() {
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(5)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));
    }

    public static void waitForTitleBarWindow() {
        WebElement lastElement = new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(5))
                .until(d -> {
                    List<WebElement> elems = d.findElements(By.cssSelector(".ui-dialog-titlebar"));
                    return elems.isEmpty() ? null : elems.getLast();
                });
    }


    public static void hoverOverElement(WebElement webElement) {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(webElement).perform();
    }

    public static void scrollToElementAndScrollBelow(WebElement webElement, int below) {
        Actions scroll = new Actions(DriverProvider.getDriver());
        scroll.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(webElement), 0, below).perform();
    }

    public static void scrollIntoCenter(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverProvider.getDriver();
        js.executeScript("arguments[0].scrollIntoView({block: 'center'})", element);
    }

    public static void hoverNavigateAndClick(WebElement webElement) {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(webElement).perform();
        webElement.click();
    }

    public static void closeNotificationOnStorefront() {    //Пришлось это добавить из-за ошибки CS-Cart в 4.18.4
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
        taras.adminPanel.UtilsAdm.makePause(2000);
    }
}