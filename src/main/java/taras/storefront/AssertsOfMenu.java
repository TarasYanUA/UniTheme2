package taras.storefront;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;
import java.util.List;

public class AssertsOfMenu extends AbstractPage {
    public AssertsOfMenu(){super();}

    @FindBy(css = ".ty-menu__submenu .row-filling")
    public List<WebElement> rowFilling; //Настройка "Способ заполнения"

    //data-settings-cols="3" -- это 3 колонки
    @FindBy(css = "div[data-cols-count='6']")
    public List<WebElement> sixColumns;
    @FindBy(css = "div[data-cols-count='5']")
    public List<WebElement> fiveColumns;
    @FindBy(css = "div[data-cols-count='4']") //эта проверка не колонки считает, а колич. элементов третьего уровня http://i.abt.team/trs/2023-10-04_175450.jpg
    public List<WebElement> fourColumns;
    @FindBy(css = "div[data-cols-count='2']")   //Неправильно! Удалить!
    public List<WebElement> twoColumns;
    @FindBy(css = "div[data-cols-count='1']")   //Неправильно! Удалить!
    public List<WebElement> oneColumn;
    @FindBy(css = ".second-lvl .ut2-mwi-icon-wrap .ut2-mwi-icon")
    public List<WebElement> iconsOfSecondLevel;
    @FindBy(css = "div[style='--menu-items:5;']")
    public List<WebElement> numberOElementsIn2levelMenu_isFive;
    @FindBy(css = "div[style='--menu-items:2;']")
    public List<WebElement> numberOElementsIn2levelMenu_isTwo;
    @FindBy(css = ".ut2-more")
    public List<WebElement> button_MoreInElementsOf2levelMenu;
    @FindBy(css = ".ty-menu-item__electronics div[data-elem-index]")
    public List<WebElement> numberOfElements_SecondLevel;   //Настройка "Элементы второго уровня"
    @FindBy(xpath = "//li[contains(@class, 'ty-menu-item__electronics')]//div[@data-elem-index='0']//div[@class='ty-menu__submenu-item']")
    public List<WebElement> numberOfElements_ThirdLevel;    //Настройка "Элементы третьего уровня"
    @FindBy(css = ".ty-menu__submenu-alt-link")
    public List<WebElement> button_MoreCategoryInTheSecondLevel;
}
