package taras.adminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

import java.time.Duration;

public class CsCartSettings extends AbstractPage {
    public CsCartSettings() {super();}

    //Меню "Настройки -- Общие настройки -- Внешний вид"
    @FindBy(id = "field___show_prices_taxed_clean_116")
    public WebElement setting_DisplayPricesWithTaxesOnCategoryAndProductPages;

    @FindBy(css = "input[id*='field___thumbnails_gallery']")
    public WebElement setting_ThumbnailsGallery;

    @FindBy(id = "field___in_stock_field_146")
    public WebElement setting_NumberOfAvailableProducts;

    @FindBy(id = "field___product_details_in_tab_288")
    public WebElement setting_ProductDetailsInTab;

    @FindBy(id = "field___default_product_details_view_180")
    public WebElement setting_ProductPageView;

    @FindBy(css = "input[id*='field___enable_quick_view']")
    public WebElement setting_QuickView;


    //Меню "Настройки -- Налоги"
    @FindBy(xpath = "//input[@type='checkbox'][@name='tax_data[7][price_includes_tax]']")
    public WebElement setting_priceIncludesTax;

    @FindBy(css = "tr[data-ct-tax-id='7'] td")
    public WebElement vat20;

    @FindBy(css = "a[data-ca-dispatch='dispatch[taxes.m_update]']")
    public WebElement button_saveTaxes;

    @FindBy(css = ".bulk-edit__btn.bulk-edit__btn--actions span")
    public WebElement button_Actions;

    @FindBy(css = "a[data-ca-dispatch=\"dispatch[taxes.apply_selected_taxes]\"]")
    public WebElement button_ApplySelectedTaxesToAllProducts;

    @FindBy(id = "field___tax_calculation_179")
    public WebElement setting_TaxCalculationMethodBasedOn;


    public void setTaxesForAllProducts() {
        BasicPage basicPage = new BasicPage();
        basicPage.navigateToCheckoutSettings();
        new Select(setting_TaxCalculationMethodBasedOn).selectByValue("unit_price");
        basicPage.clickSaveButtonOfSettings();
        basicPage.navigateToTaxes();
        WebElement checkboxPriceIncludesTax = setting_priceIncludesTax;
        if (checkboxPriceIncludesTax.isSelected()) {
            checkboxPriceIncludesTax.click();
            button_saveTaxes.click();
            UtilsAdm.makePause(1500);
            vat20.click();
            (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(4)))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn-group.bulk-edit__wrapper")));
            button_Actions.click();
            button_ApplySelectedTaxesToAllProducts.click();
        }

        //Настраиваем CS-Cart настройку
        basicPage.navigateToAppearanceSettings();
        UtilsAdm.setCheckboxState(setting_DisplayPricesWithTaxesOnCategoryAndProductPages, true);
        basicPage.clickSaveButtonOfSettings();
    }
}