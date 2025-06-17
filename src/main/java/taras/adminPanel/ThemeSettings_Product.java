package taras.adminPanel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import taras.constants.AbstractPage;

public class ThemeSettings_Product extends AbstractPage {
    public ThemeSettings_Product(){super();}

    @FindBy(css = ".nav-tabs #products")
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

    @FindBy(id = "settings.abt__ut2.products.view.show_you_save.desktop")
    private WebElement setting_ShowYouSave;

    @FindBy(id = "settings.abt__ut2.products.view.show_brand_format.desktop")
    private WebElement setting_ShowProductBrand;

    @FindBy(id = "settings.abt__ut2.products.default_template.multiple_product_images.desktop")
    private WebElement setting_NumberOfDisplayedImages_DefaultTemplate;

    @FindBy(id = "settings.abt__ut2.products.bigpicture_template.multiple_product_images.desktop")
    private WebElement setting_NumberOfDisplayedImages_BigPictureTemplate;

    @FindBy(id = "settings.abt__ut2.products.abt__ut2_bigpicture_flat_template.multiple_product_images.desktop")
    private WebElement setting_NumberOfDisplayedImages_BigPictureFlatTemplate;

    @FindBy(id = "settings.abt__ut2.products.abt__ut2_bigpicture_gallery_template.multiple_product_images.desktop")
    private WebElement setting_NumberOfDisplayedImages_GalleryTemplate;

    @FindBy(id = "settings.abt__ut2.products.abt__ut2_three_columns_template.multiple_product_images.desktop")
    private WebElement setting_NumberOfDisplayedImages_ThreeColumnsTemplate;

    @FindBy (id = "settings.abt__ut2.products.abt__ut2_cascade_gallery_template.formation_multiple_product_images.desktop")
    private WebElement setting_CombinationsOfProductGalleryImageFormations;


    public void clickAndTypeSetting_CustomBlockID(String value){
        setting_CustomBlockID.click();
        setting_CustomBlockID.clear();
        setting_CustomBlockID.sendKeys(value);
    }

    public void selectSetting_ShowProductBrand(String value){
        new Select(setting_ShowProductBrand).selectByValue(value);
    }

    public void selectSetting_ShowYouSave(String value){
        new Select(setting_ShowYouSave).selectByValue(value);
    }

    public void selectSetting_NumberOfDisplayedImages_DefaultTemplate(String value){
        new Select(setting_NumberOfDisplayedImages_DefaultTemplate).selectByValue(value);
    }

    public void selectSetting_NumberOfDisplayedImages_BigPictureTemplate(String value){
        new Select(setting_NumberOfDisplayedImages_BigPictureTemplate).selectByValue(value);
    }

    public void selectSetting_NumberOfDisplayedImages_BigPictureFlatTemplate(String value){
        new Select(setting_NumberOfDisplayedImages_BigPictureFlatTemplate).selectByValue(value);
    }

    public void selectSetting_NumberOfDisplayedImages_GalleryTemplate(String value){
        new Select(setting_NumberOfDisplayedImages_GalleryTemplate).selectByValue(value);
    }

    public void selectSetting_NumberOfDisplayedImages_ThreeColumnsTemplate(String value){
        new Select(setting_NumberOfDisplayedImages_ThreeColumnsTemplate).selectByValue(value);
    }

    public void selectSetting_CombinationsOfProductGalleryImageFormations(String value){
        new Select(setting_CombinationsOfProductGalleryImageFormations).selectByValue(value);
    }
}