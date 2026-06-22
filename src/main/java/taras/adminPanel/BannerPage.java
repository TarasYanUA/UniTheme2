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
import java.util.List;

public class BannerPage extends AbstractPage {
    public BannerPage() {
        super();
    }

    @FindBy(css = ".nav__actions-adv-buttons a")
    WebElement button_PlusBanner;

    @FindBy(css = "a[href*='dispatch=banners.add&type=abt__ut2']")
    WebElement button_AddAdvancedBanner;

    @FindBy(css = "a[data-ca-dispatch=\"dispatch[banners.update]\"]")
    WebElement button_CreateBanner;

    @FindBy(id = "elm_banner_name")
    WebElement field_BannerName;

    @FindBy(id = "elm_banner_abt__ut2_content_valign")
    WebElement setting_BannerBlockSettings_VerticalAlignment;

    @FindBy(id = "elm_banner_abt__ut2_content_align")
    WebElement setting_BannerBlockSettings_HorizontalAlignment;

    @FindBy(id = "elm_banner_abt__ut2_content_full_width")
    WebElement setting_ContentOnFullWidth;

    @FindBy(id = "elm_banner_abt__ut2_title")
    WebElement field_BannerTitle;

    @FindBy(id = "elm_banner_abt__ut2_object")
    WebElement setting_ObjectInside_DisplayedObject;

    @FindBy(id = "elm_banner_abt__ut2_products_template")
    WebElement setting_ObjectInside_Template;

    @FindBy(id = "elm_banner_abt__ut2_products_grid_columns")
    WebElement setting_ObjectInside_ColumnsForGrid;

    @FindBy(id = "elm_banner_abt__ut2_products_small_items_columns")
    WebElement setting_ObjectInside_ColumnsForSmallItems;

    @FindBy(id = "elm_banner_abt__ut2_products_links_thumb_columns")
    WebElement setting_ObjectInside_ColumnsForThumbnails;

    private final By setting_ObjectInside_Rows = By.id("elm_banner_abt__ut2_products_small_items_rows");

    @FindBy(css = "a[data-ca-external-click-id='opener_picker_object_picker_advanced_elm_banner_abt__ut2_products_list']")
    WebElement setting_ObjectInside_ProductPicker;

    @FindBy(css = ".sidebar-field input[name='q']")
    WebElement field_searchProduct;

    @FindBy(css = "input[value='Найти']")
    WebElement button_SearchProduct;

    @FindBy(css = "input[name='check_all']")
    List<WebElement> checkbox_CheckAllProducts;

    @FindBy(css = "input[value='Добавить товары']")
    WebElement button_AddSelectedProducts;

    @FindBy(css = "input[value='Добавить товары и закрыть']")
    WebElement button_AddSelectedProductsAndClose;

    @FindBy(id = "elm_banner_abt__ut2_background_color_use")
    WebElement checkbox_BannerBackground_BackgroundColor;

    @FindBy(css = "#overlay_abt__ut2_background_color .sp-replacer")
    WebElement colorPicker_BannerBackground_BackgroundColor;

    @FindBy(css = "#overlay_abt__ut2_background_color .sp-input")
    WebElement printColorCode_BannerBackground;

    @FindBy(css = "#overlay_abt__ut2_background_color .sp-choose")
    WebElement button_BannerBackground_ChooseColor;


    public void createNewAdvancedBannerWithProducts(String bannerName,
                                                    String template,
                                                    String columns,
                                                    String rows,
                                                    String backgroundColor) {
        if (DriverProvider.getDriver().findElements(By.xpath("//a[text()='" + bannerName + "']")).isEmpty()) {
            UtilsAdm.closeAllNotifications();
            button_PlusBanner.click();
            button_AddAdvancedBanner.click();
            field_BannerName.sendKeys(bannerName);
            new Select(setting_BannerBlockSettings_VerticalAlignment).selectByValue("center");
            new Select(setting_BannerBlockSettings_HorizontalAlignment).selectByValue("center");
            UtilsAdm.setCheckboxState(setting_ContentOnFullWidth, true);

            field_BannerTitle.sendKeys(bannerName);
            new Select(setting_ObjectInside_DisplayedObject).selectByValue("products");
            new Select(setting_ObjectInside_Template).selectByValue(template);
            UtilsAdm.makePause(500);

            WebElement select = switch (template) {
                case "grid_items" -> setting_ObjectInside_ColumnsForGrid;
                case "small_items" -> setting_ObjectInside_ColumnsForSmallItems;
                case "links_thumb" -> setting_ObjectInside_ColumnsForThumbnails;
                default -> throw new IllegalArgumentException("Unknown banner template: " + template);
            };
            new Select(select).selectByValue(columns);

            if (!DriverProvider.getDriver().findElements(setting_ObjectInside_Rows).isEmpty())
                new Select(DriverProvider.getDriver().findElement(setting_ObjectInside_Rows)).selectByValue(rows);

            selectProductsForBanner();
            UtilsAdm.scrollIntoCenter(checkbox_BannerBackground_BackgroundColor);
            UtilsAdm.setCheckboxState(checkbox_BannerBackground_BackgroundColor, true);
            colorPicker_BannerBackground_BackgroundColor.click();
            UtilsAdm.clickAndType(printColorCode_BannerBackground, backgroundColor);
            button_BannerBackground_ChooseColor.click();
            createBanner();
        }
    }

    private void selectProductsForBanner() {
        UtilsAdm.scrollIntoCenter(setting_ObjectInside_ProductPicker);
        setting_ObjectInside_ProductPicker.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(8)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-dialog-title")));

        String[] products = {
                "adizero Rush Shoes",
                "Apple - iPhone 5c 32GB Cell Phone",
                "Apple iPad 2",
                "X-Box 360",
                "GoPro - Hero3",
                "Samsung NX200"
        };
        for (String product : products) {
            UtilsAdm.clickAndType(field_searchProduct, product);
            button_SearchProduct.click();
            UtilsAdm.makePause(500);
            if (!checkbox_CheckAllProducts.isEmpty()) {
                UtilsAdm.setCheckboxState(checkbox_CheckAllProducts.getFirst(), true);
                button_AddSelectedProducts.click();
            }
        }
        button_AddSelectedProductsAndClose.click();
        (new WebDriverWait((DriverProvider.getDriver()), Duration.ofSeconds(8)))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.className("ui-dialog-title")));
    }

    private void createBanner() {
        button_CreateBanner.click();
        UtilsAdm.makePause(2000);
    }
}