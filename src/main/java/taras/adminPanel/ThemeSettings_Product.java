package taras.adminPanel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;

public class ThemeSettings_Product extends AbstractPage {
    public ThemeSettings_Product(){super();}
    @FindBy(id = "products")
    public WebElement tab_Product;
    @FindBy(id = "settings.abt__ut2.products.custom_block_id")
    private WebElement setting_CustomBlockID;
    @FindBy(id = "settings.abt__ut2.products.view.show_qty.desktop")
    public WebElement setting_ShowQuantityChanger;
    @FindBy(id = "settings.abt__ut2.products.view.show_sku.desktop")
    public WebElement setting_ShowProductCode;
    @FindBy(id = "settings.abt__ut2.products.view.show_features.desktop")
    public WebElement setting_ShowProductFeatures;
    @FindBy(id = "settings.abt__ut2.products.view.show_features_in_two_col.desktop")
    public WebElement setting_FeaturesInTwoColumns;
    @FindBy(id = "settings.abt__ut2.products.view.show_short_description.desktop")
    public WebElement setting_ShowShortDescription;
    @FindBy(id = "settings.abt__ut2.products.view.show_brand_format.desktop")
    private WebElement setting_ShowProductBrand;
}