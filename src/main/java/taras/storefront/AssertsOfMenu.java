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
    @FindBy(css = "div[data-cols-count='5']")
    public List<WebElement> fiveColumns;
    @FindBy(css = "div[data-cols-count='4']")
    public List<WebElement> fourColumns;
    @FindBy(css = "div[data-cols-count='2']")
    public List<WebElement> twoColumns;
    @FindBy(css = "div[data-cols-count='1']")
    public List<WebElement> oneColumn;
    @FindBy(css = ".second-lvl .ut2-mwi-icon-wrap .ut2-mwi-icon")
    public List<WebElement> iconsOfSecondLevel;
    @FindBy(css = "div[style='--menu-items:5;']")
    public List<WebElement> numberOElementsIn2levelMenu_isFive;
    @FindBy(css = "div[style='--menu-items:2;']")
    public List<WebElement> numberOElementsIn2levelMenu_isTwo;
    @FindBy(xpath = "//li[contains(@class, 'ty-menu-item__electronics')]//div[@data-elem-index='0']//div[@class='ty-menu__submenu-item']")
    public List<WebElement> numberOElementsIn2levelMenu_isAnyNumber;
    @FindBy(css = ".ut2-more")
    public List<WebElement> button_MoreInElementsOf2levelMenu;
    @FindBy(css = "div[class='second-lvl'][data-elem-index='6']")
    public List<WebElement> numberOfElementsOfSecondLevel_isSeven;
    @FindBy(css = "div[class='second-lvl'][data-elem-index='2']")
    public List<WebElement> numberOfElementsOfSecondLevel_isThree;
    @FindBy(css = ".ty-menu__submenu-alt-link")
    public List<WebElement> button_MoreCategoryInTheSecondLevel;
}
