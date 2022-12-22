package taras.workPages;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import taras.AbstractPage;
import taras.DriverProvider;
import java.util.List;

public class MainPage extends AbstractPage {
    public MainPage(){
        super();
    }

    @FindBy(css = ".cm-btn.cm-btn-success")
    private WebElement cookieNoticeOnStorefront;
    @FindBy(css = "div.ty-mainbox-container.clearfix")
    private WebElement blockWithProducts;
    @FindBy(css = "li[class$='ty-menu-item__electronics']")
    private WebElement menuElectronic;
    @FindBy(xpath = "//bdi[text()='Телефоны']")
    private WebElement menuPhones;
    @FindBy(css = "li[class$='ty-menu-item__apparel']")
    private WebElement menuApparel;
    @FindBy(xpath = "//bdi[text()='Женская одежда']")
    private WebElement menuWomanCloth;
    @FindBy(xpath = "//bdi[text()='Мужская одежда']")
    private WebElement menuMenCloth;
    @FindBy(css = "a[id*='wrap_language']")
    private WebElement languageButton;
    @FindBy(css = "ul[class^='cm-select-list'] li")
    private List<WebElement> languageSelection;


    public void closeCookieNoticeOnStorefront(){
        cookieNoticeOnStorefront.click();
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
    public WebElement hoverMenuApparel(){
        return menuApparel;
    }
    public void navigateToMenuPhones(){
        WebElement elementOfMenu = hoverMenuElectronic();
        Actions hoverMenuPhones = new Actions(DriverProvider.getDriver());
        hoverMenuPhones.moveToElement(elementOfMenu);
        hoverMenuPhones.perform();
        menuPhones.click();
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

    public void scrollToMenuApparel(){
        WebElement elementOfMenu = hoverMenuApparel();
        Actions scrollToMenuApparel = new Actions(DriverProvider.getDriver());
        scrollToMenuApparel.scrollToElement(elementOfMenu);
        scrollToMenuApparel.perform();
    }
    public List<WebElement> getLanguageSelection(){
        return languageSelection;
    }
    public void changeLanguageByIndex (int langCode){
        languageButton.click();
        WebElement listOfLanguages = getLanguageSelection().get(langCode);
        listOfLanguages.click();
    }
}