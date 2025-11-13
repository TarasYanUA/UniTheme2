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

    public static void scrollToElementAndScrollBelow(WebElement webElement, int below) {
        Actions scroll = new Actions(DriverProvider.getDriver());
        scroll.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(webElement), 0, below).perform();
    }

    public static void scrollIntoCenter(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverProvider.getDriver();
        js.executeScript("arguments[0].scrollIntoView({block: 'center'})", element);
    }

    public static void hoverAndNavigateAndClick(WebElement webElement) {
        Actions actions = new Actions(DriverProvider.getDriver());
        actions.moveToElement(webElement).perform();
        webElement.click();
    }
}