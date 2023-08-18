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

    //Разделы меню
    @FindBy(css = ".top-menu-grid-vetrtical .ty-dropdown-box__title")
    public WebElement menuButton_Catalog;
    @FindBy(css = "li[class$='ty-menu-item__products']")
    private WebElement menuAllProducts;
    @FindBy(css = "li[class$='ty-menu-item__apparel']")
    private WebElement menuApparel;
    @FindBy(css = "li[class$='ty-menu-item__electronics']")
    private WebElement menuElectronic;
    @FindBy(xpath = "(//li[contains(@class, 'ty-menu-item__electronics')]//div[@class='ty-menu__submenu-item-header ut2-mwi-icon-wrap'])[4]")
    private WebElement menuPhones;
    @FindBy(xpath = "//li[contains(@class, 'ty-menu-item__electronics')]//div[@data-elem-index='6']")
    private WebElement menuGameConsoles;
    @FindBy(xpath = "(//li[contains(@class, 'ty-menu-item__apparel')]//div[contains(@class, 'ty-menu__submenu-item')])[3]")
    private WebElement menuWomanCloth;
    @FindBy(xpath = "(//li[contains(@class, 'ty-menu-item__apparel')]//div[contains(@class, 'ty-menu__submenu-item')])[2]")
    private WebElement menuMenCloth;
    @FindBy(css = "li[class$='ty-menu-item__sport']")
    private WebElement menuSportsAndOutdoors;
    @FindBy(css = "li[class$='ty-menu-item__media']")
    private WebElement menuVideoGames;


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

    //Относится к меню на витрине
    private WebElement hoverMenuAllProducts(){return menuAllProducts;}
    public void navigateToMenu_AllProducts(){
        WebElement element = hoverMenuAllProducts();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }
    private WebElement hoverMenuElectronic(){
        return menuElectronic;
    }
    public void navigateToMenu_Electronic(){
        WebElement element = hoverMenuElectronic();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }
    public void navigateToMenu_Phones(){
        WebElement elementOfMenu = hoverMenuElectronic();
        Actions hoverMenuPhones = new Actions(DriverProvider.getDriver());
        hoverMenuPhones.moveToElement(elementOfMenu);
        hoverMenuPhones.perform();
        menuPhones.click();
    }
    public void navigateToMenu_GameConsoles(){
        WebElement elementOfMenu = hoverMenuElectronic();
        Actions hoverMenu = new Actions(DriverProvider.getDriver());
        hoverMenu.moveToElement(elementOfMenu);
        hoverMenu.perform();
        menuGameConsoles.click();
    }
    public WebElement hoverMenuApparel(){
        return menuApparel;
    }
    public void navigateToMenu_Apparel(){
        WebElement element = hoverMenuApparel();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }
    public void navigateToMenu_WomanCloth(){
        WebElement elementOfMenu = hoverMenuApparel();
        Actions hoverMenuApparel = new Actions(DriverProvider.getDriver());
        hoverMenuApparel.moveToElement(elementOfMenu);
        hoverMenuApparel.perform();
        menuWomanCloth.click();
    }

    public void navigateToMenu_MenCloth(){
        WebElement elementOfMenu = hoverMenuApparel();
        Actions hoverMenuApparel = new Actions(DriverProvider.getDriver());
        hoverMenuApparel.moveToElement(elementOfMenu);
        hoverMenuApparel.perform();
        menuMenCloth.click();
    }
    private WebElement hoverMenuSportsAndOutdoors(){return menuSportsAndOutdoors;}
    public void navigateToMenu_SportsAndOutdoors(){
        WebElement element = hoverMenuSportsAndOutdoors();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }
    private WebElement hoverMenuVideoGames(){return menuVideoGames;}
    public void navigateToMenu_VideoGames(){
        WebElement element = hoverMenuVideoGames();
        Actions hover = new Actions(DriverProvider.getDriver());
        hover.moveToElement(element);
        hover.perform();
    }
}