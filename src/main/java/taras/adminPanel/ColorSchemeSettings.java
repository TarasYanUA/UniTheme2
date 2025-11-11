package taras.adminPanel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;

public class ColorSchemeSettings extends AbstractPage {
    public ColorSchemeSettings() {
        super();
    }

    @FindBy(css = "a[id^='sw_select_'][id$='_wrap_currency']")
    public WebElement fieldOfActiveColorScheme;

    @FindBy(xpath = "//div[@class=\"language-wrap\"]//a[contains(.,\"CS-Cart\")]")
    public WebElement activeColorScheme;


    //Вкладка "Общее"
    @FindBy(id = "settings.abt__ut2.general.use_rounding")
    public WebElement setting_General_RoundCornersForElements;

    @FindBy(id = "settings.abt__ut2.general.use_rounding_blocks")
    public WebElement setting_General_RoundCornersOfBlocks;

    @FindBy(id = "settings.abt__ut2.general.use_titles_uppercase")
    public WebElement setting_General_DisplayHeadersInCapitalLetters;

    @FindBy(id = "settings.abt__ut2.general.buttons.style")
    public WebElement setting_General_ButtonsStyle;

    @FindBy(id = "settings.abt__ut2.general.buttons.use_text_uppercase")
    public WebElement setting_General_DisplayTextInCapitalLetters;

    @FindBy(id = "settings.abt__ut2.general.buttons.use_shadow")
    public WebElement setting_General_AddShadow;

    @FindBy(id = "settings.abt__ut2.general.buttons.use_gradient")
    public WebElement setting_General_AddBulk;

    @FindBy(id = "settings.abt__ut2.general.buttons.use_icon_cart")
    public WebElement setting_General_CartIcon;


    //Вкладка "Списки товаров"
    @FindBy(css = ".nav-tabs #product_list")
    public WebElement tab_ProductLists;

    @FindBy(id = "settings.abt__ut2.product_list.show_grid_border")
    public WebElement setting_FrameType;

    @FindBy(id = "settings.abt__ut2.product_list.mask_images_gallery")
    public WebElement setting_ProductLists_MaskForProductImages;

    @FindBy(id = "settings.abt__ut2.product_list.use_elements_alignment")
    public WebElement setting_ProductLists_ElementsAlignment;

    @FindBy(id = "settings.abt__ut2.product_list.extend_grid_item_on_hover")
    public WebElement setting_ProductLists_ExpandGridItemOnHover;

    @FindBy(id = "settings.abt__ut2.product_list.grid-list.product_name_font_weight")
    public WebElement setting_ProductLists_FontWeightForProductName;


    //Вкладка "Товар"
    @FindBy(css = ".nav-tabs #products")
    public WebElement tab_Product;

    @FindBy(id = "settings.abt__ut2.products.mask_images_gallery")
    public WebElement setting_ProductMaskForProductImages;

    @FindBy(id = "settings.abt__ut2.products.bordered_images_gallery")
    public WebElement setting_ProductBorderForProductImages;
}