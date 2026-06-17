package taras.storefront;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import taras.adminPanel.UtilsAdm;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class StHomePage extends AbstractPage {
    public StHomePage() {
        super();
    }

    @FindBy(css = ".cm-btn-success")
    public WebElement cookie;

    @FindBy(css = "a[id*='wrap_language']")
    public WebElement languageButton;

    @FindBy(css = "div[id^='account_info_']")
    WebElement accountOnTop;

    @FindBy(css = ".ty-account-info__buttons a[href*='auth.logout']")
    WebElement button_LogOut;

    @FindBy(css = "div[id*='simple_products_scroller']")
    public WebElement blockWithProducts;

    @FindBy(css = ".close.cm-notification-close")
    List<WebElement> closeNotification_AlertSuccess;


    public void closeAllNotifications() {
        if (!closeNotification_AlertSuccess.isEmpty()) {
            for (int i = 0; i < closeNotification_AlertSuccess.size(); i++) {
                closeNotification_AlertSuccess.getFirst().click();
            }
        }
    }

    public void logOutOnStorefront() {
        taras.adminPanel.UtilsAdm.hoverNavigateAndClick(accountOnTop);
        if (!DriverProvider.getDriver().findElements(By.cssSelector(".ty-account-info__buttons a[href*='auth.logout']")).isEmpty()) {
            button_LogOut.click();
        }
    }

    public void selectLanguage(String ruEnAr) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(languageButton));
        languageButton.click();
        DriverProvider.getDriver().findElement(By.cssSelector(".ty-select-block__list-item a[data-ca-name='" + ruEnAr + "']")).click();
        UtilsAdm.hoverOverElement(languageButton);
    }

    public void scrollToBlockWithProducts() {
        UtilsAdm.scrollToElementAndScrollBelow(blockWithProducts, 800);
    }

    public void openProductBlock(String blockName) {
        List<WebElement> block = DriverProvider.getDriver().findElements(By
                .xpath("//span[@class='ty-tabs__span'][text()='" + blockName + "']"));

        UtilsAdm.hoverNavigateAndClick(!block.isEmpty()
                ? block.getFirst()
                : DriverProvider.getDriver().findElement(By
                .xpath("//span[@class='ty-tabs__span'][text()='Распродажа']")));
        UtilsAdm.makePause(2000);
    }


    //Разделы меню на витрине
    @FindBy(css = ".top-menu-grid-vertical .ty-dropdown-box__title")
    public WebElement verticalMenu_menuButton_Categories;

    @FindBy(css = ".top-menu-grid-vertical .ty-menu-item__products")
    public WebElement verticalMenu_AllProducts;

    @FindBy(css = ".top-menu-grid-vertical .ty-menu-item__electronics")
    public WebElement verticalMenu_Electronic;

    @FindBy(css = ".top-menu-grid-vertical .ty-menu-item__apparel")
    public WebElement verticalMenu_Apparel;

    @FindBy(css = ".top-menu-grid-vertical .ty-menu-item__sport")
    public WebElement verticalMenu_SportsAndOutdoors;

    @FindBy(css = ".top-menu-grid-vertical .ty-menu-item__media ")
    public WebElement verticalMenu_VideoGames;

    @FindBy(css = ".ty-menu-item__electronics div[data-elem-index='0']")
    public WebElement threeLevelMenu_Computers;

    @FindBy(css = ".ty-menu-item__electronics div[data-elem-index='1']")
    public WebElement threeLevelMenu_CarElectronics;

    @FindBy(xpath = "//li[contains(@class, 'ty-menu-item__electronics')]//div[@data-elem-index='3']//span")
    WebElement horizontalMenu_Phones;

    @FindBy(xpath = "//li[contains(@class, 'ty-menu-item__electronics')]//div[@data-elem-index='6']//span")
    WebElement horizontalMenu_GameConsoles;

    @FindBy(xpath = "//li[contains(@class, 'ty-menu-item__apparel')]//div[@data-elem-index='0']//span")
    WebElement horizontalMenu_MenCloth;

    @FindBy(xpath = "//li[contains(@class, 'ty-menu-item__apparel')]//div[@data-elem-index='1']//span")
    WebElement horizontalMenu_WomanCloth;

    @FindBy(css = ".ut2-menu__list .ty-menu-item__products")
    public WebElement horizontalMenu_AllProducts;

    @FindBy(css = ".ut2-menu__list .ty-menu-item__apparel")
    public WebElement horizontalMenu_Apparel;

    @FindBy(css = ".ut2-menu__list .ty-menu-item__electronics")
    public WebElement horizontalMenu_Electronic;

    @FindBy(css = ".ut2-menu__list .ty-menu-item__sport")
    public WebElement horizontalMenu_SportsAndOutdoors;

    @FindBy(css = ".ut2-menu__list .ty-menu-item__media")
    public WebElement horizontalMenu_VideoGames;


    public void navigateToHorizontalMenu_Phones() {
        UtilsAdm.hoverOverElement(horizontalMenu_Electronic);
        horizontalMenu_Phones.click();
    }

    public void navigateToHorizontalMenu_GameConsoles() {
        UtilsAdm.hoverOverElement(horizontalMenu_Electronic);
        horizontalMenu_GameConsoles.click();
    }

    public void navigateToHorizontalMenu_MenCloth() {
        UtilsAdm.hoverOverElement(horizontalMenu_Apparel);
        horizontalMenu_MenCloth.click();
    }

    public void navigateToHorizontalMenu_WomanCloth() {
        UtilsAdm.hoverOverElement(horizontalMenu_Apparel);
        horizontalMenu_WomanCloth.click();
    }


    //Fly меню на витрине
    @FindBy(css = ".ut2-icon-outline-menu")
    WebElement button_FlyMenu;

    @FindBy(css = ".ut2-sw-w .ut2-icon-baseline-close")
    public WebElement button_CloseFlyMenu;

    @FindBy(css = ".ut2-lfl.ty-menu-item__products p")
    public WebElement flyMenu_AllProducts;

    @FindBy(css = ".ut2-lfl.ty-menu-item__electronics p")
    public WebElement flyMenu_Electronics;

    @FindBy(css = ".ut2-lfl.ty-menu-item__apparel p")
    public WebElement flyMenu_Apparel;

    @FindBy(css = ".ut2-lfl.ty-menu-item__sport p")
    public WebElement flyMenu_SportsAndOutdoors;

    @FindBy(css = ".ut2-lfl.ty-menu-item__media p")
    public WebElement flyMenu_VideoGames;


    public void openFlyMenu() {
        button_FlyMenu.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ut2-lfl.ty-menu-item__products p")));
    }
}