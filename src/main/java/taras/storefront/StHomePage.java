package taras.storefront;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

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
    @FindBy(css = ".ut2-icon-outline-account-circle")
    private WebElement accountOnTop;
    @FindBy(css = ".ty-account-info__buttons a[href*='auth.logout']")
    private WebElement button_LogOut;
    @FindBy(css = "div.ty-mainbox-container.clearfix")
    private WebElement blockWithProducts;
    @FindBy(css = "div[id*='content_abt__ut2_grid_tab'] .ut2-w-c-q__buttons")
    private WebElement productInProductBlock;
    public WebElement hoverToAccountOnTop(){return accountOnTop;}
    public void LogOutOnStorefront(){
        WebElement element = hoverToAccountOnTop();
        Actions scrollToBlock = new Actions(DriverProvider.getDriver());
        scrollToBlock.moveToElement(element);
        scrollToBlock.perform();
        accountOnTop.click();
        if(DriverProvider.getDriver().findElements(By.cssSelector(".ty-account-info__buttons a[href*='auth.logout']")).size() > 0) {
        button_LogOut.click();  }
    }
    public void selectLanguage_RTL(){
        languageButton.click();
        languageRTL.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void selectLanguage_RU(){
        languageButton.click();
        languageRU.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement getProductInProductBlock(){return productInProductBlock;}
    public void hoverToProductInProductBlock(){
        WebElement element = getProductInProductBlock();
        Actions hoverProduct = new Actions(DriverProvider.getDriver());
        hoverProduct.moveToElement(element);
        hoverProduct.perform();
    }

    public WebElement hoverBlockWithProducts(){
        return blockWithProducts;
    }
    public void scrollToBlockWithProducts(){
        WebElement elementForScroll = hoverBlockWithProducts();
        Actions scrollToBlock = new Actions(DriverProvider.getDriver());
        scrollToBlock.scrollFromOrigin(WheelInput.ScrollOrigin.fromElement(elementForScroll), 0, 800);
        scrollToBlock.perform();
    }

    //Разделы меню на витрине
    @FindBy(css = ".top-menu-grid-vetrtical .ty-dropdown-box__title")
    public WebElement verticalMenu_menuButton_Catalog;
    @FindBy(css = ".ut2-menu-vetrtical .ty-menu-item__products")
    private WebElement verticalMenu_menuAllProducts;
    @FindBy(css = ".ut2-menu-vetrtical .ty-menu-item__apparel")
    private WebElement verticalMenu_menuApparel;
    @FindBy(css = ".ut2-menu-vetrtical .ty-menu-item__electronics")
    private WebElement verticalMenu_menuElectronic;
    @FindBy(xpath = "(//li[contains(@class, 'ty-menu-item__electronics')]//div[@class='ty-menu__submenu-item-header ut2-mwi-icon-wrap'])[4]")
    private WebElement verticalMenu_menuPhones;
    @FindBy(xpath = "//li[contains(@class, 'ty-menu-item__electronics')]//div[@data-elem-index='6']")
    private WebElement verticalMenu_menuGameConsoles;
    @FindBy(xpath = "(//li[contains(@class, 'ty-menu-item__apparel')]//div[contains(@class, 'ty-menu__submenu-item')])[3]")
    private WebElement verticalMenu_menuWomanCloth;
    @FindBy(xpath = "(//li[contains(@class, 'ty-menu-item__apparel')]//div[contains(@class, 'ty-menu__submenu-item')])[2]")
    private WebElement verticalMenu_menuMenCloth;
    @FindBy(css = ".ut2-menu-vetrtical .ty-menu-item__sport")
    private WebElement verticalMenu_menuSportsAndOutdoors;
    @FindBy(css = ".ut2-menu-vetrtical .ty-menu-item__media")
    private WebElement verticalMenu_menuVideoGames;

    private WebElement hoverVerticalMenu_AllProducts(){return verticalMenu_menuAllProducts;}
    public void navigateToVerticalMenu_AllProducts(){
        WebElement element = hoverVerticalMenu_AllProducts();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }
    private WebElement hoverVerticalMenu_Electronic(){
        return verticalMenu_menuElectronic;
    }
    public void navigateToVerticalMenu_Electronic(){
        WebElement element = hoverVerticalMenu_Electronic();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }
    public void navigateToVerticalMenu_Phones(){
        WebElement elementOfMenu = hoverVerticalMenu_Electronic();
        Actions hoverMenuPhones = new Actions(DriverProvider.getDriver());
        hoverMenuPhones.moveToElement(elementOfMenu);
        hoverMenuPhones.perform();
        verticalMenu_menuPhones.click();
    }
    public void navigateToVerticalMenu_GameConsoles(){
        WebElement elementOfMenu = hoverVerticalMenu_Electronic();
        Actions hoverMenu = new Actions(DriverProvider.getDriver());
        hoverMenu.moveToElement(elementOfMenu);
        hoverMenu.perform();
        verticalMenu_menuGameConsoles.click();
    }
    public WebElement hoverVerticalMenu_Apparel(){
        return verticalMenu_menuApparel;
    }
    public void navigateToVerticalMenu_Apparel(){
        WebElement element = hoverVerticalMenu_Apparel();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }
    public void navigateToVerticalMenu_WomanCloth(){
        WebElement elementOfMenu = hoverVerticalMenu_Apparel();
        Actions hoverMenuApparel = new Actions(DriverProvider.getDriver());
        hoverMenuApparel.moveToElement(elementOfMenu);
        hoverMenuApparel.perform();
        verticalMenu_menuWomanCloth.click();
    }

    public void navigateToVerticalMenu_MenCloth(){
        WebElement elementOfMenu = hoverVerticalMenu_Apparel();
        Actions hoverMenuApparel = new Actions(DriverProvider.getDriver());
        hoverMenuApparel.moveToElement(elementOfMenu);
        hoverMenuApparel.perform();
        verticalMenu_menuMenCloth.click();
    }
    private WebElement hoverVerticalMenu_SportsAndOutdoors(){return verticalMenu_menuSportsAndOutdoors;}
    public void navigateToVerticalMenu_SportsAndOutdoors(){
        WebElement element = hoverVerticalMenu_SportsAndOutdoors();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }
    private WebElement hoverVerticalMenu_VideoGames(){return verticalMenu_menuVideoGames;}
    public void navigateToVerticalMenu_VideoGames(){
        WebElement element = hoverVerticalMenu_VideoGames();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }
    @FindBy(css = ".ty-menu-item__electronics div[data-elem-index='1']")
    private WebElement threeLevelMenu_CarElectronics;
    private WebElement hoverThreeLevelMenu_CarElectronics(){return threeLevelMenu_CarElectronics;}
    public void navigateToMenu_ThreeLevelMenu_CarElectronics(){
        WebElement element = hoverThreeLevelMenu_CarElectronics();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }

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
    public WebElement hoverHorizontalMenu_Apparel(){
        return horizontalMenu_menuApparel;
    }
    public void navigateToHorizontalMenu_Apparel(){
        WebElement element = hoverHorizontalMenu_Apparel();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
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