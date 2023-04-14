package taras.adminPanel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import taras.constants.AbstractPage;

public class ColorSchemeSettings extends AbstractPage {
    public ColorSchemeSettings(){super();}

    @FindBy(css = "a[id^='sw_select_'][id$='_wrap_currency']")
    public WebElement fieldOfActiveColorScheme;
    @FindBy(xpath = "//div[@class=\"language-wrap\"]//a[contains(text(),\"CS-Cart\")]")
    public WebElement activeColorScheme;

    @FindBy(css = ".nav-tabs #product_list")
    public WebElement tab_ProductLists;
    @FindBy(id = "settings.abt__ut2.product_list.show_grid_border")
    private WebElement setting_FrameType;
    @FindBy(id = "settings.abt__ut2.product_list.mask_images_gallery")
    public WebElement setting_ProductListsMaskForProductImages;

    public Select getSetting_FrameType(){return new Select(setting_FrameType);}
    public void selectSetting_FrameType(String value){
        getSetting_FrameType().selectByValue(value);
    }


    @FindBy(css = ".nav-tabs #products")
    public WebElement tab_Product;
    @FindBy(id = "settings.abt__ut2.products.mask_images_gallery")
    public WebElement setting_ProductMaskForProductImages;
    @FindBy(id = "settings.abt__ut2.products.bordered_images_gallery")
    public WebElement setting_ProductBorderForProductImages;



/*    public void chooseActiveColorScheme(){
        //fieldOfActiveColorScheme.click();


        for (int i=0; i<listOfColorSchemeNames.size(); i++){
            System.out.println(listOfColorSchemeNames.get(i).getText());

        }
    }*/
}
