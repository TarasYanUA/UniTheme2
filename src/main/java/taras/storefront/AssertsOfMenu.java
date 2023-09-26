package taras.storefront;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;
import java.util.List;

public class AssertsOfMenu extends AbstractPage {
    public AssertsOfMenu(){super();}

    @FindBy(css = ".ty-menu__submenu .row-filling")
    public List<WebElement> rowFilling;
    @FindBy(css = "div[data-cols-count='6']")
    public List<WebElement> sixColumns;
}
