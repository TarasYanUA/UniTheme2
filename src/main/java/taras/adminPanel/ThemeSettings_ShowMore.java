package taras.adminPanel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

public class ThemeSettings_ShowMore extends AbstractPage {
    public ThemeSettings_ShowMore() {super();}

    //Настройки темы, вкладка "Показать ещё"
    @FindBy(id = "load_more")
    public WebElement tab_ShowMore;
    @FindBy(id = "settings.abt__ut2.load_more.product_list")
    public WebElement setting_AllowForProductLists;

    public WebElement scrollToTab_ShowMore(){return tab_ShowMore;}
    public void navigateTo_ThemeSettings_tabShowMore() {
        WebElement element = scrollToTab_ShowMore();
        Actions scrollToElement = new Actions(DriverProvider.getDriver());
        scrollToElement.moveToElement(element);
        scrollToElement.perform();
        tab_ShowMore.click();
    }
}