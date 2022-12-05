package taras.workPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import taras.AbstractPage;
import taras.DriverProvider;

import java.util.ArrayList;

public class AdminPanel extends AbstractPage {
    public AdminPanel(){
        super();
    }

    public void navigateToAddonsPage(AdminPanel adminPanel) {
        WebElement elementOfAddonsDropDown = hoverAddonsDropDown();
        Actions hoverAddonsDropDown = new Actions(DriverProvider.getDriver());
        hoverAddonsDropDown.moveToElement(elementOfAddonsDropDown);
        hoverAddonsDropDown.perform();
        navigateToAddonsManagementPage();
    }
    public void hoverToProductPage(){
        WebElement elementOfMenuProducts = hoverMenuProducts();
        Actions hoverMenuProducts = new Actions(DriverProvider.getDriver());
        hoverMenuProducts.moveToElement(elementOfMenuProducts);
        hoverMenuProducts.perform();
    }
    public ProductPage navigateToProductPage(){
        productPage.click();
        return new ProductPage();
    }
    public void focusBrowserTab() {
        ArrayList tabs = new ArrayList<String> (DriverProvider.getDriver().getWindowHandles());
        for(int ii = 0; ii <= 1; ii++) {
            DriverProvider.getDriver().switchTo().window(tabs.get(ii).toString());
        }
    }

    @FindBy(css = ".btn.btn-primary")
    private WebElement buttonAuthorization;
    @FindBy(xpath = "(//a[@class=\"dropdown-toggle addons\"])[1]")
    private WebElement addonsDropDown;
    @FindBy(id = "elm_menu_addons_manage_addons")
    private WebElement addonsManagementPage;
    @FindBy(xpath = "//button[@class=\"close cm-notification-close cm-notification-close-ajax\"]")
    private WebElement closeWarning;
    @FindBy(xpath = "//li[@class='dropdown nav__header-main-menu-item ']//a[@href='#products']")
    private WebElement menuProducts;
    @FindBy(xpath = "//span[text()='Товары']")
    private WebElement productPage;

    
    public void clickButtonAuthorization(){
        buttonAuthorization.click();
    }
    public WebElement hoverAddonsDropDown(){
        return addonsDropDown;
    }
    public void navigateToAddonsManagementPage(){
        addonsManagementPage.click();
    }
    public void clickCloseWarning(){
        closeWarning.click();
    }
    public WebElement hoverMenuProducts(){
        return menuProducts;
    }
}