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
    public StHomePage(){
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

    public WebElement hoverToAccountOnTop(){return accountOnTop;}
    public void logOutOnStorefront(){
        WebElement element = hoverToAccountOnTop();
        Actions scrollToBlock = new Actions(DriverProvider.getDriver());
        scrollToBlock.moveToElement(element);
        scrollToBlock.perform();
        accountOnTop.click();
        if(!DriverProvider.getDriver().findElements(By.cssSelector(".ty-account-info__buttons a[href*='auth.logout']")).isEmpty()) {
        button_LogOut.click();  }
    }

    public void selectLanguage_RTL(){
        languageButton.click();
        languageRTL.click();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(languageButton).perform();
    }
    public void selectLanguage_RU(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(languageButton));
        languageButton.click();
        languageRU.click();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(languageButton).perform();
    }

    public void scrollToBlockWithProducts(){
        Actions scrollToBlock = new Actions(DriverProvider.getDriver());
        scrollToBlock.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(blockWithProducts), 0, 800);
        scrollToBlock.perform();
    }


    //Разделы меню на витрине
    @FindBy(css = ".top-menu-grid-vetrtical .ty-dropdown-box__title")
    public WebElement verticalMenu_menuButton_Categories;

    @FindBy(css = ".ut2-menu-vetrtical .ty-menu-item__products")
    private WebElement verticalMenu_menuAllProducts;

    @FindBy(css = ".ut2-menu-vetrtical .ty-menu-item__electronics .menu-lvl-ctn")
    private WebElement verticalMenu_menuElectronic;

    @FindBy(css = ".ut2-menu-vetrtical .ty-menu-item__apparel .menu-lvl-ctn")
    private WebElement verticalMenu_menuApparel;

    @FindBy(css = ".ut2-menu-vetrtical .ty-menu-item__sport .menu-lvl-ctn")
    private WebElement verticalMenu_menuSportsAndOutdoors;

    @FindBy(css = ".ut2-menu-vetrtical .ty-menu-item__media  .menu-lvl-ctn")
    private WebElement verticalMenu_menuVideoGames;

    @FindBy(css = ".ty-menu-item__electronics div[data-elem-index='0']")
    private WebElement threeLevelMenu_Computers;

    @FindBy(css = ".ty-menu-item__electronics div[data-elem-index='1']")
    private WebElement threeLevelMenu_CarElectronics;

    public void navigateToVerticalMenu_AllProducts(){
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(verticalMenu_menuAllProducts);
        hover.perform();
    }

    public void navigateToVerticalMenu_Electronic(){
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(verticalMenu_menuElectronic);
        hover.perform();
    }

    public void navigateToVerticalMenu_Apparel(){
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(verticalMenu_menuApparel);
        hover.perform();
    }

    public void navigateToVerticalMenu_SportsAndOutdoors(){
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(verticalMenu_menuSportsAndOutdoors);
        hover.perform();
    }

    public void navigateToVerticalMenu_VideoGames(){
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(verticalMenu_menuVideoGames);
        hover.perform();
    }

    public void navigateToMenu_ThreeLevelMenu_Computers(){
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(threeLevelMenu_Computers);
        hover.perform();
    }

    public void navigateToMenu_ThreeLevelMenu_CarElectronics(){
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
    @FindBy(css = ".ty-menu__wrapper .ty-menu-item__products")
    private WebElement horizontalMenu_menuAllProducts;
    @FindBy(css = ".ty-menu__wrapper .ty-menu-item__apparel")
    private WebElement horizontalMenu_menuApparel;
    @FindBy(css = ".ty-menu__wrapper .ty-menu-item__electronics")
    private WebElement horizontalMenu_menuElectronic;
    @FindBy(css = ".ty-menu__wrapper .ty-menu-item__sport")
    private WebElement horizontalMenu_menuSportsAndOutdoors;
    @FindBy(css = ".ty-menu__wrapper .ty-menu-item__media")
    private WebElement horizontalMenu_menuVideoGames;


    private WebElement hoverHorizontalMenu_AllProducts(){return horizontalMenu_menuAllProducts;}
    public void navigateToHorizontalMenu_AllProducts(){
        WebElement element = hoverHorizontalMenu_AllProducts();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }
    private WebElement hoverHorizontalMenu_Electronic(){
        return horizontalMenu_menuElectronic;
    }
    public void navigateToHorizontalMenu_Electronic(){
        WebElement element = hoverHorizontalMenu_Electronic();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }
    public void navigateToHorizontalMenu_Phones(){
        navigateToHorizontalMenu_Electronic();
        horizontalMenu_menuPhones.click();
    }
    public void navigateToHorizontalMenu_GameConsoles(){
        navigateToHorizontalMenu_Electronic();
        horizontalMenu_menuGameConsoles.click();
    }

    public WebElement hoverHorizontalMenu_Apparel(){
        return horizontalMenu_menuApparel;
    }
    public void navigateToHorizontalMenu_Apparel(){
        WebElement element = hoverHorizontalMenu_Apparel();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }
    public void navigateToHorizontalMenu_MenCloth(){
        navigateToHorizontalMenu_Apparel();
        horizontalMenu_menuMenCloth.click();
    }
    public void navigateToHorizontalMenu_WomanCloth(){
        navigateToHorizontalMenu_Apparel();
        horizontalMenu_menuWomanCloth.click();
    }
    private WebElement hoverHorizontalMenu_SportsAndOutdoors(){return horizontalMenu_menuSportsAndOutdoors;}
    public void navigateToHorizontalMenu_SportsAndOutdoors(){
        WebElement element = hoverHorizontalMenu_SportsAndOutdoors();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }
    private WebElement hoverHorizontalMenu_VideoGames(){return horizontalMenu_menuVideoGames;}
    public void navigateToHorizontalMenu_VideoGames(){
        WebElement element = hoverHorizontalMenu_VideoGames();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }


    //Fly меню на витрине
    @FindBy(css = ".ut2-icon-outline-menu")
    public WebElement button_FlyMenu;
    @FindBy(css = ".ut2-sw-w .ut2-icon-baseline-close")
    public WebElement button_CloseFlyMenu;
    @FindBy(css = ".ut2-lfl.ty-menu-item__products p")
    private WebElement flyMenu_AllProducts;
    private WebElement hoverFlyMenu_AllProducts(){return flyMenu_AllProducts;}
    public void navigateToFlyMenu_AllProducts(){
        WebElement element = hoverFlyMenu_AllProducts();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element).build().perform();
    }

    @FindBy(css = ".ut2-lfl.ty-menu-item__electronics p")
    private WebElement flyMenu_Electronics;
    private WebElement hoverFlyMenu_Electronics(){return flyMenu_Electronics;}
    public void navigateToFlyMenu_Electronics(){
        WebElement element = hoverFlyMenu_Electronics();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }
    @FindBy(css = ".ut2-lfl.ty-menu-item__apparel p")
    private WebElement flyMenu_Apparel;
    private WebElement hoverFlyMenu_Apparel(){return flyMenu_Apparel;}
    public void navigateToFlyMenu_Apparel(){
        WebElement element = hoverFlyMenu_Apparel();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }
    @FindBy(css = ".ut2-lfl.ty-menu-item__sport p")
    private WebElement flyMenu_SportsAndOutdoors;
    private WebElement hoverFlyMenu_SportsAndOutdoors(){return flyMenu_SportsAndOutdoors;}
    public void navigateToFlyMenu_SportsAndOutdoors(){
        WebElement element = hoverFlyMenu_SportsAndOutdoors();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }
    @FindBy(css = ".ut2-lfl.ty-menu-item__media p")
    private WebElement flyMenu_VideoGames;
    private WebElement hoverFlyMenu_VideoGames(){return flyMenu_VideoGames;}
    public void navigateToFlyMenu_VideoGames(){
        WebElement element = hoverFlyMenu_VideoGames();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }
}