package taras.adminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import taras.constants.DriverProvider;

public interface DisableLazyLoadFromSection {
    default void disableLazyLoadFromSection (String blockName) {
        LayoutPage layoutPage = new LayoutPage();
        layoutPage.layout_TabHomePage.click();

        String block = "div[data-ca-block-name='" + blockName + "'] ~ div[class*='grid-control-menu'] div[class*='bm-action-properties']";
        WebElement specifiedBlock = DriverProvider.getDriver().findElement(By.cssSelector(block));
        UtilsAdm.hoverNavigateAndClick(specifiedBlock);

        UtilsAdm.waitForPopUpWindow();
        WebElement lazyloadInSection = DriverProvider.getDriver().findElement(By.cssSelector("input[id^='elm_grid_abt__ut2_use_lazy_load']"));
        UtilsAdm.setCheckboxState(lazyloadInSection, false);
        DriverProvider.getDriver().findElement(By.cssSelector("input[name='dispatch[block_manager.grid.update]']")).click();

        UtilsAdm.closeAllNotifications();
        UtilsAdm.makePause(2000);
    }
}