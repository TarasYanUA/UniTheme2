import org.testng.annotations.Test;

/*
ссылка на чеклист:https://docs.google.com/spreadsheets/d/1YPAkjqk12kPh7LBDU1tq7qdwLmCo-Rly00TdfW8h-Wo/edit#gid=718159332

*/
public class ColorSchemeSettings_ProductLists_Var1 extends TestRunner {
    @Test (priority = 1)
    public void setConfigurationsForColorSchemeSettings_ProductLists_Var1(){

    }
}

/*
        //Работаем на странице категории
        StHomePage stHomePage = new StHomePage();
        StCategoryPage stCategoryPage = new StCategoryPage();
        stHomePage.navigateToMenuPhones();
        takeScreenShot("1465 Category - Grid, Var1 (RTL)");
        //Быстрый просмотр в категории "Телефоны"
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("1470 QuickView, Var1 (RTL)");
        stCategoryPage.clickCloseQuickView();
        stCategoryPage.clickListWithoutOptions_ProductListView();
        takeScreenShot("1475 Category - List without options, Var1 (RTL)");
        stCategoryPage.clickCompactList_ProductListView();
        takeScreenShot("1480 Category - Compact list, Var1 (RTL)");
        stHomePage.selectLanguage_RU();
        takeScreenShot("1485 Category - Compact list, Var1");
        stCategoryPage.clickListWithoutOptions_ProductListView();
        takeScreenShot("1490 Category - List without options, Var1");
        stCategoryPage.clickGrid_ProductListView();
        takeScreenShot("1495 Category - Grid, Var1");
        //Быстрый просмотр в категории "Телефоны"
        stCategoryPage.hoverToPhoneProduct();
        stCategoryPage.clickQuickViewOfPhoneProduct();
        (new WebDriverWait((getDriver()), Duration.ofSeconds(4)))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ty-product-review-product-rating-overview-short")));
        takeScreenShot("1498 QuickView, Var1");
 */