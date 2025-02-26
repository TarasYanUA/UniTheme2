package taras.storefront;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import java.util.List;

public class AssertsOfMenu extends AbstractPage {
    public AssertsOfMenu() {
        super();
    }

    @FindBy(css = ".ut2-menu__submenu__carrier.row-filling")
    public List<WebElement> rowFilling;                         //Настройка "Способ заполнения -- Строчное"

    @FindBy(css = ".ut2-menu__2nd-list .ut2-mwi-icon-wrap .ut2-mwi-icon")
    public List<WebElement> iconsOfSecondLevel;                 //Настройка "Показывать иконки для пунктов меню второго уровня"

    //Настройка "Столбцов в строке"
    public List<WebElement> columnsPerRow(String number) {
        return DriverProvider.getDriver().findElements(By.cssSelector("ul[style='--menu-columns: " + number + "']"));
    }

    //Настройка "Количество видимых элементов в третьем уровне меню"
    public List<WebElement> numberOfVisibleElementsIn_3levelMenu(String number) {
        return DriverProvider.getDriver().findElements(By.cssSelector("div[style='--menu-items:" + number + ";']"));
    }

    @FindBy(css = ".ty-menu-item__electronics div[data-elem-index]")
    public List<WebElement> numberOfElements_SecondLevel;       //Настройка "Элементы второго уровня" в категории "Электроника"

    @FindBy(css = ".ty-menu-item__electronics div[data-elem-index='0'] .ut2-menu__3rd-item")
    public List<WebElement> numberOfElements_ThirdLevel;        //Настройка "Элементы третьего уровня"

    @FindBy(xpath = "//span[@class='ut2-menu__2nd-link__name'][text()='Электроника']/../../../..//div[@class='ut2-menu__3rd-item']")
    public List<WebElement> numberOfElements_ThirdLevel_AllProducts; //Настройка "Элементы третьего уровня" в категории "Все товары -- Электроника"

    @FindBy(css = ".ut2-menu .ut2-more-btn")
    public List<WebElement> button_MoreInElementsOf2levelMenu;

    @FindBy(xpath = "//span[@class='ut2-menu__more-cat-link__in'][contains(text(), 'Больше Электроника')]")
    public List<WebElement> button_MoreCategoryInTheSecondLevel_MoreElectronics;

    //Настройка "Кол-во отображаемых элементов в 3-м уровне меню"
    @FindBy(css = "li.ty-menu-item__electronics div[data-elem-index=\"1\"] .ut2-menu__3rd-item")
    public List<WebElement> threeLevelMenu_elementsInThirdLevel;

    @FindBy(css = ".ut2-menu__2nd-submenu__wrapper .ut2-menu__more-cat-link__in")
    public List<WebElement> threeLevelMenu_button_MoreCategory;

    @FindBy(css = ".ut2-menu__2nd-submenu img[src$='sports-bg-menu.jpg']")
    public List<WebElement> threeLevelMenu_banner;


    //FLY menu
    @FindBy(xpath = "//span[text()='Меню']")
    public List<WebElement> flyMenu_title;

    @FindBy(css = ".img .ut2-lfl-icon")
    public List<WebElement> flyMenu_iconsOfSecondLevel;

    @FindBy(css = ".ty-menu-item__electronics div[class*='ut2-lsl with-pic']:not(.ut2-lsl__show_more)")
    public List<WebElement> flyMenu_NumberOfElements_SecondLevel;

    @FindBy(css = ".ty-menu-item__electronics div[class='ut2-lsl with-pic ut2-lsl__more']")
    public List<WebElement> flyMenu_NumberOfElements_SecondLevelWithButtonMore;

    @FindBy(css = ".ty-menu-item__electronics .ut2-tlw a[href*='kompyutery']:not(.hidden)")
    public List<WebElement> flyMenu_NumberOfElements_ThirdLevel;

    @FindBy(css = ".ut2-lsl__more-link")
    public List<WebElement> flyMenu_ButtonMore;

    @FindBy(css = ".ut2-lsl__show_more")
    public List<WebElement> flyMenu_ButtonMoreCategories;
}