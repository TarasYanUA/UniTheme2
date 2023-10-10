package taras.storefront;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;
import java.util.List;

public class AssertsOfMenu extends AbstractPage {
    public AssertsOfMenu(){super();}

    @FindBy(css = ".ty-menu__submenu .row-filling")
    public List<WebElement> rowFilling;                         //Настройка "Способ заполнения"
    @FindBy(css = "li[data-settings-cols='6']")                 //Настройка "Максимальное количество колонок в строке"
    public List<WebElement> sixColumns;
    @FindBy(css = "li[data-settings-cols='5']")
    public List<WebElement> fiveColumns;
    @FindBy(css = "li[data-settings-cols='4']")
    public List<WebElement> fourColumns;
    @FindBy(css = "li[data-settings-cols='2']")
    public List<WebElement> twoColumns;
    @FindBy(css = "li[data-settings-cols='1']")
    public List<WebElement> oneColumn;
    @FindBy(css = ".second-lvl .ut2-mwi-icon-wrap .ut2-mwi-icon")
    public List<WebElement> iconsOfSecondLevel;                 //Настройка "Показывать иконки для пунктов меню второго уровня"
    @FindBy(css = "div[style='--menu-items:5;']")
    public List<WebElement> numberOfElementsIn3levelMenu_Five;  //Настройка "Кол-во отображаемых элементов в 3-м уровне меню" Оформлена ошибка https://abteam.planfix.com/task/41190
    @FindBy(css = "div[style='--menu-items:2;']")
    public List<WebElement> numberOfElementsIn3levelMenu_Two;
    @FindBy(css = "div[style='--menu-items:0;']")
    public List<WebElement> numberOfElementsIn3levelMenu_Zero;
    @FindBy(css = ".ty-menu-item__electronics div[data-elem-index]")
    public List<WebElement> numberOfElements_SecondLevel;       //Настройка "Элементы второго уровня"
    @FindBy(xpath = "//li[contains(@class, 'ty-menu-item__electronics')]//div[@data-elem-index='0']//div[@class='ty-menu__submenu-item']")
    public List<WebElement> numberOfElements_ThirdLevel;        //Настройка "Элементы третьего уровня"
    @FindBy(css = ".ut2-more")
    public List<WebElement> button_MoreInElementsOf2levelMenu;
    @FindBy(css = ".ty-menu__submenu-alt-link")
    public List<WebElement> button_MoreCategoryInTheSecondLevel;
}
