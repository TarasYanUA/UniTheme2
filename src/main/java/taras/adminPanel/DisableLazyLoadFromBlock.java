package taras.adminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import taras.constants.DriverProvider;

import java.time.Duration;

public interface DisableLazyLoadFromBlock {
    default void disableLazyLoadFromBlock(String blockName) {
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToSection_WebsiteLayouts();
        csCartSettings.layout_TabHomePage.click();

        String block = "div[data-ca-block-name='" + blockName + "'] ~ div[class*='grid-control-menu'] div[class*='bm-action-properties']";
        WebElement specifiedBlock = DriverProvider.getDriver().findElement(By.cssSelector(block));
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(specifiedBlock);
        hover.perform();
        specifiedBlock.click();

        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-dialog-title")));
        if (DriverProvider.getDriver().findElement(By.cssSelector("input[id^='elm_grid_abt__ut2_use_lazy_load']")).isSelected())
            DriverProvider.getDriver().findElement(By.cssSelector("input[id^='elm_grid_abt__ut2_use_lazy_load']")).click();
        DriverProvider.getDriver().findElement(By.cssSelector("input[name='dispatch[block_manager.grid.update]']")).click();
    }
}