package taras.adminPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import taras.constants.AbstractPage;
import taras.constants.DriverProvider;

public class CategoryPage extends AbstractPage {
    public CategoryPage() {super();}

    @FindBy(css = ".nav__actions-bar .dropdown-icon--tools")
    public WebElement gearwheelOnCategoryPage;

    @FindBy(css = "a[href*='dispatch=categories.m_add']")
    public WebElement button_AddBulkCategory;

    @FindBy(css = "a[id*='opener_picker_location_category_']")
    WebElement button_CategoryLocation;

    @FindBy(css = ".span3")
    public WebElement field_CategoryName;

    @FindBy(css = ".btn-clone")
    public WebElement button_Clone;

    @FindBy(css = "a[data-ca-dispatch='dispatch[categories.m_add]']")
    public WebElement button_Create;

    @FindBy(xpath = "//span[@class='ui-button-icon ui-icon ui-icon-closethick']")
    WebElement button_CrossOnTop;


    public void addNewCategoryLocations_Computers() {
        if (!DriverProvider.getDriver().findElements(By.cssSelector("select[name='categories_data[0][parent_id]']")).isEmpty()) {
            DriverProvider.getDriver().findElement(By.cssSelector("select[name='categories_data[0][parent_id]']")).click();
            DriverProvider.getDriver().findElement(By.cssSelector("option[value=\"166\"]")).click();
            UtilsAdm.clickAndType(field_CategoryName,"AutoTestCategory");
            for (int i = 1; i < 80; i++) {
                button_Clone.click();
            }
            button_Create.click();
        } else {
            button_CategoryLocation.click();
            if (!DriverProvider.getDriver().findElements(By.xpath("//span[text()='Магазин: CS-Cart']")).isEmpty())
                DriverProvider.getDriver().findElement(By.xpath("//span[text()='Магазин: CS-Cart']")).click();
            DriverProvider.getDriver().findElement(By.cssSelector("#category_166")).click();
            UtilsAdm.makePause(2000);
            if (DriverProvider.getDriver().findElements(By.xpath("//label[text()='AutoTestCategory']")).isEmpty()) {
                DriverProvider.getDriver().findElement(By.cssSelector("#category_166")).click();
                UtilsAdm.clickAndType(field_CategoryName,"AutoTestCategory");
                for (int i = 1; i < 80; i++) {
                    button_Clone.click();
                }
                button_Create.click();
            } else
                button_CrossOnTop.click();
        }
    }
}