package taras.storefront;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class StHomePage extends AbstractPage {
    public StHomePage() {
        super();
    }

    @FindBy(css = ".cm-btn-success")
    public WebElement cookie;

    @FindBy(css = "a[id*='wrap_language']")
    private WebElement languageButton;

    @FindBy(css = ".ty-select-block__list-item a[data-ca-name='ar']")
    private WebElement languageRTL;

    @FindBy(css = ".ty-select-block__list-item a[data-ca-name='ru']")
    private WebElement languageRU;

    @FindBy(css = "div[id^='account_info_']")
    private WebElement accountOnTop;

    @FindBy(css = ".ty-account-info__buttons a[href*='auth.logout']")
    private WebElement button_LogOut;

    @FindBy(css = "div.ty-mainbox-container.clearfix")
    private WebElement blockWithProducts;


    public void logOutOnStorefront() {
        Actions scrollToBlock = new Actions(DriverProvider.getDriver());
        scrollToBlock.moveToElement(accountOnTop);
        scrollToBlock.perform();
        accountOnTop.click();
        if (!DriverProvider.getDriver().findElements(By.cssSelector(".ty-account-info__buttons a[href*='auth.logout']")).isEmpty()) {
            button_LogOut.click();
        }
    }

    public void selectLanguage_RTL() {
        languageButton.click();
        languageRTL.click();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(languageButton).perform();
    }

    public void selectLanguage_RU() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(languageButton));
        languageButton.click();
        languageRU.click();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(languageButton).perform();
    }

    public void scrollToBlockWithProducts() {
        Actions scrollToBlock = new Actions(DriverProvider.getDriver());
        scrollToBlock.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(blockWithProducts), 0, 800);
        scrollToBlock.perform();
    }


    //Разделы меню на витрине
    @FindBy(css = ".top-menu-grid-vetrtical .ty-dropdown-box__title")
    public WebElement verticalMenu_menuButton_Categories;

    @FindBy(css = ".top-menu-grid-vetrtical .ty-menu-item__products")
    private WebElement verticalMenu_menuAllProducts;

    @FindBy(css = ".top-menu-grid-vetrtical .ty-menu-item__electronics")
    private WebElement verticalMenu_menuElectronic;

    @FindBy(css = ".top-menu-grid-vetrtical .ty-menu-item__apparel")
    private WebElement verticalMenu_menuApparel;

    @FindBy(css = ".top-menu-grid-vetrtical .ty-menu-item__sport")
    private WebElement verticalMenu_menuSportsAndOutdoors;

    @FindBy(css = ".top-menu-grid-vetrtical .ty-menu-item__media ")
    private WebElement verticalMenu_menuVideoGames;

    @FindBy(css = ".ty-menu-item__electronics div[data-elem-index='0']")
    private WebElement threeLevelMenu_Computers;

    @FindBy(css = ".ty-menu-item__electronics div[data-elem-index='1']")
    private WebElement threeLevelMenu_CarElectronics;


    public void navigateToVerticalMenu_AllProducts() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(verticalMenu_menuAllProducts);
        hover.perform();
    }

    public void navigateToVerticalMenu_Electronic() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(verticalMenu_menuElectronic);
        hover.perform();
    }

    public void navigateToVerticalMenu_Apparel() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(verticalMenu_menuApparel);
        hover.perform();
    }

    public void navigateToVerticalMenu_SportsAndOutdoors() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(verticalMenu_menuSportsAndOutdoors);
        hover.perform();
    }

    public void navigateToVerticalMenu_VideoGames() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(verticalMenu_menuVideoGames);
        hover.perform();
    }

    public void navigateToMenu_ThreeLevelMenu_Computers() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(threeLevelMenu_Computers);
        hover.perform();
    }

    public void navigateToMenu_ThreeLevelMenu_CarElectronics() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(threeLevelMenu_CarElectronics);
        hover.perform();
    }


    @FindBy(xpath = "//li[contains(@class, 'ty-menu-item__electronics')]//div[@data-elem-index='3']//span")
    private WebElement horizontalMenu_menuPhones;

    @FindBy(xpath = "//li[contains(@class, 'ty-menu-item__electronics')]//div[@data-elem-index='6']//span")
    private WebElement horizontalMenu_menuGameConsoles;

    @FindBy(xpath = "//li[contains(@class, 'ty-menu-item__apparel')]//div[@data-elem-index='0']")
    private WebElement horizontalMenu_menuMenCloth;

    @FindBy(xpath = "//li[contains(@class, 'ty-menu-item__apparel')]//div[@data-elem-index='1']")
    private WebElement horizontalMenu_menuWomanCloth;

    @FindBy(css = ".ut2-menu__list .ty-menu-item__products")
    private WebElement horizontalMenu_menuAllProducts;

    @FindBy(css = ".ut2-menu__list .ty-menu-item__apparel")
    private WebElement horizontalMenu_menuApparel;

    @FindBy(css = ".ut2-menu__list .ty-menu-item__electronics")
    private WebElement horizontalMenu_menuElectronic;

    @FindBy(css = ".ut2-menu__list .ty-menu-item__sport")
    private WebElement horizontalMenu_menuSportsAndOutdoors;

    @FindBy(css = ".ut2-menu__list .ty-menu-item__media")
    private WebElement horizontalMenu_menuVideoGames;


    public void navigateToHorizontalMenu_AllProducts() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(horizontalMenu_menuAllProducts);
        hover.perform();
    }

    public void navigateToHorizontalMenu_Electronic() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(horizontalMenu_menuElectronic);
        hover.perform();
    }

    public void navigateToHorizontalMenu_Phones() {
        navigateToHorizontalMenu_Electronic();
        horizontalMenu_menuPhones.click();
    }

    public void navigateToHorizontalMenu_GameConsoles() {
        navigateToHorizontalMenu_Electronic();
        horizontalMenu_menuGameConsoles.click();
    }

    public void navigateToHorizontalMenu_Apparel() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(horizontalMenu_menuApparel);
        hover.perform();
    }

    public void navigateToHorizontalMenu_MenCloth() {
        navigateToHorizontalMenu_Apparel();
        horizontalMenu_menuMenCloth.click();
    }

    public void navigateToHorizontalMenu_WomanCloth() {
        navigateToHorizontalMenu_Apparel();
        horizontalMenu_menuWomanCloth.click();
    }

    public void navigateToHorizontalMenu_SportsAndOutdoors() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(horizontalMenu_menuSportsAndOutdoors);
        hover.perform();
    }

    public void navigateToHorizontalMenu_VideoGames() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(horizontalMenu_menuVideoGames);
        hover.perform();
    }


    //Fly меню на витрине
    @FindBy(css = ".ut2-icon-outline-menu")
    public WebElement button_FlyMenu;

    @FindBy(css = ".ut2-sw-w .ut2-icon-baseline-close")
    public WebElement button_CloseFlyMenu;

    @FindBy(css = ".ut2-lfl.ty-menu-item__products p")
    private WebElement flyMenu_AllProducts;

    @FindBy(css = ".ut2-lfl.ty-menu-item__electronics p")
    private WebElement flyMenu_Electronics;

    @FindBy(css = ".ut2-lfl.ty-menu-item__apparel p")
    private WebElement flyMenu_Apparel;

    @FindBy(css = ".ut2-lfl.ty-menu-item__sport p")
    private WebElement flyMenu_SportsAndOutdoors;

    @FindBy(css = ".ut2-lfl.ty-menu-item__media p")
    private WebElement flyMenu_VideoGames;


    public void navigateToFlyMenu_AllProducts() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(flyMenu_AllProducts).perform();
    }

    public void navigateToFlyMenu_Electronics() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(flyMenu_Electronics);
        hover.perform();
    }

    public void navigateToFlyMenu_Apparel() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(flyMenu_Apparel);
        hover.perform();
    }

    public void navigateToFlyMenu_SportsAndOutdoors() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(flyMenu_SportsAndOutdoors);
        hover.perform();
    }

    public void navigateToFlyMenu_VideoGames() {
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(flyMenu_VideoGames);
        hover.perform();
    }
}