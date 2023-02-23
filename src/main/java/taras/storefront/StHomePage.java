package taras.storefront;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;
import java.util.List;

public class StHomePage extends AbstractPage {
    public StHomePage(){
        super();
    }
    @FindBy(css = ".cm-btn.cm-btn-success")
    private WebElement cookieNoticeOnStorefront;
    @FindBy(css = ".ut2-icon-outline-account-circle")
    private WebElement accountOnTop;
    @FindBy(css = ".ty-account-info__buttons a[href*='auth.logout']")
    private WebElement button_LogOut;
    @FindBy(css = "div.ty-mainbox-container.clearfix")
    private WebElement blockWithProducts;
    @FindBy(css = "li[class$='ty-menu-item__apparel']")
    private WebElement menuApparel;
    @FindBy(css = "li[class$='ty-menu-item__electronics']")
    private WebElement menuElectronic;
    @FindBy(xpath = "(//li[contains(@class, 'ty-menu-item__electronics')]//div[@class='ty-menu__submenu-item-header ut2-mwi-icon-wrap'])[4]")
    private WebElement menuPhones;
    @FindBy(xpath = "(//li[contains(@class, 'ty-menu-item__apparel')]//div[contains(@class, 'ty-menu__submenu-item')])[3]")
    private WebElement menuWomanCloth;
    @FindBy(xpath = "(//li[contains(@class, 'ty-menu-item__apparel')]//div[contains(@class, 'ty-menu__submenu-item')])[2]")
    private WebElement menuMenCloth;
    @FindBy(xpath = "//li[contains(@class, 'ty-menu-item__electronics')]//div[@data-elem-index='6']//bdi")
    private WebElement menuGameConsoles;
    @FindBy(css = "a[id*='wrap_language']")
    private WebElement languageButton;
    @FindBy(css = ".ty-select-block__list-item a[data-ca-name='ar']")
    private WebElement languageRTL;
    @FindBy(css = ".ty-select-block__list-item a[data-ca-name='ru']")
    private WebElement languageRU;
    @FindBy(css = "div[id*='content_abt__ut2_grid_tab'] .ut2-w-c-q__buttons")
    private WebElement productInProductBlock;


    public void closeCookieNoticeOnStorefront(){
        cookieNoticeOnStorefront.click();
    }
    public WebElement hoverToAccountOnTop(){return accountOnTop;}
    public void LogOutOnStorefront(){
        WebElement element = hoverToAccountOnTop();
        Actions scrollToBlock = new Actions(DriverProvider.getDriver());
        scrollToBlock.moveToElement(element);
        scrollToBlock.perform();
        button_LogOut.click();
    }

    public WebElement hoverBlockWithProducts(){
        return blockWithProducts;
    }
    public void scrollToBlockWithProducts(){
        WebElement elementForScroll = hoverBlockWithProducts();
        Actions scrollToBlock = new Actions(DriverProvider.getDriver());
        scrollToBlock.moveToElement(elementForScroll).scrollByAmount(0,800);
        scrollToBlock.perform();
    }
    public WebElement hoverMenuElectronic(){
        return menuElectronic;
    }
    public void navigateToMenuPhones(){
        WebElement elementOfMenu = hoverMenuElectronic();
        Actions hoverMenuPhones = new Actions(DriverProvider.getDriver());
        hoverMenuPhones.moveToElement(elementOfMenu);
        hoverMenuPhones.perform();
        menuPhones.click();
    }
    public WebElement hoverMenuApparel(){
        return menuApparel;
    }
    public void navigateToMenuWomanCloth(){
        WebElement elementOfMenu = hoverMenuApparel();
        Actions hoverMenuApparel = new Actions(DriverProvider.getDriver());
        hoverMenuApparel.moveToElement(elementOfMenu);
        hoverMenuApparel.perform();
        menuWomanCloth.click();
    }

    public void navigateToMenuMenCloth(){
        WebElement elementOfMenu = hoverMenuApparel();
        Actions hoverMenuApparel = new Actions(DriverProvider.getDriver());
        hoverMenuApparel.moveToElement(elementOfMenu);
        hoverMenuApparel.perform();
        menuMenCloth.click();
    }

    public void navigateToMenuApparel(){
        WebElement elementOfMenu = hoverMenuApparel();
        Actions scrollToMenuApparel = new Actions(DriverProvider.getDriver());
        scrollToMenuApparel.scrollToElement(elementOfMenu).scrollByAmount(0, -100);
        scrollToMenuApparel.perform();
        menuApparel.click();
    }

    public void navigateToMenuGameConsoles(){
        WebElement elementOfMenu = hoverMenuElectronic();
        Actions hoverMenu = new Actions(DriverProvider.getDriver());
        hoverMenu.moveToElement(elementOfMenu);
        hoverMenu.perform();
        menuGameConsoles.click();
    }
    public void selectLanguage_RTL(){
        languageButton.click();
        languageRTL.click();
    }
    public void selectLanguage_RU(){
        languageButton.click();
        languageRU.click();
    }
    public WebElement getProductInProductBlock(){return productInProductBlock;}
    public void hoverToProductInProductBlock(){
        WebElement element = getProductInProductBlock();
        Actions hoverProduct = new Actions(DriverProvider.getDriver());
        hoverProduct.moveToElement(element);
        hoverProduct.perform();
    }
}
