package taras.adminPanel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;

public class ThemeSettings_ShowMore extends AbstractPage {
    public ThemeSettings_ShowMore() {super();}

    //Настройки темы, вкладка "Показать ещё"
    @FindBy(id = "load_more")
    WebElement tab_ShowMore;

    @FindBy(id = "settings.abt__ut2.load_more.product_list")
    public WebElement setting_AllowForProductLists;


    public void openTab_ShowMore() {
        UtilsAdm.closeAllNotifications();
        UtilsAdm.hoverNavigateAndClick(tab_ShowMore);
    }
}