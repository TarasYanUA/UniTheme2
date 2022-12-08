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

    @FindBy(css = "li[class$='ty-menu-item__electronics']")
    private WebElement menuElectronic;
    @FindBy(xpath = "//bdi[text()='Телефоны']")
    private WebElement menuPhones;
    @FindBy(css = "li[class$='ty-menu-item__apparel']")
    private WebElement menuApparel;
    @FindBy(xpath = "//bdi[text()='Женская одежда']")
    private WebElement menuWomanCloth;
    @FindBy(css = "a[id*='wrap_language']")
    private WebElement languageButton;
    @FindBy(css = "ul[class^='cm-select-list'] li")
    private List<WebElement> languageSelection;


    public WebElement hoverMenuElectronic(){
        return menuElectronic;
    }
    public WebElement hoverMenuApparel(){
        return menuApparel;
    }
    public void navigateToMenuPhones(){
        WebElement elementOfMenu = hoverMenuElectronic();
        Actions hoverMenuApparel = new Actions(DriverProvider.getDriver());
        hoverMenuApparel.moveToElement(elementOfMenu);
        hoverMenuApparel.perform();
        menuPhones.click();
    }
    public void navigateToMenuWomanCloth(){
        WebElement elementOfMenu = hoverMenuApparel();
        Actions hoverMenuApparel = new Actions(DriverProvider.getDriver());
        hoverMenuApparel.moveToElement(elementOfMenu);
        hoverMenuApparel.perform();
        menuWomanCloth.click();
    }
    public List<WebElement> getLanguageSelection(){
        return languageSelection;
    }
    public void selectLanguageByIndex (int langCode){
        languageButton.click();
        WebElement listOfLanguages = getLanguageSelection().get(langCode);
        listOfLanguages.click();
    }
}