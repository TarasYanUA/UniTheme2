import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;
import taras.constants.DriverProvider;
import taras.adminPanel.CsCartSettings;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import static taras.constants.Constants.BASIC_URL;

/*
Данный проект разработан для проверки отображения витрины с различными комбинациями настроек Юни темы.
Актуальная версия Юни темы 4.16.1b. Можно установить как саму тему отдельно, так и Пакет UniTheme2 (UltRu). Использовать макеты Light v2 и Default.

Рекомендуется запускать проект через файл TestNG.xml. Но можно также через Surefire отчёт:
перейти в "Терминал" и ввести "mvn clean test". После этого в папке "target -> surefire reports"
открыть файл "index.html" с помощью браузера.
 */

public class TestRunner {

    @BeforeMethod
    public void prepareBrowser() {
        DriverProvider.getDriver().get(BASIC_URL);
        DriverProvider.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(4)); //Общая задержка
        DriverProvider.getDriver().manage().window().maximize();    //Размер браузера на весь экран
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.clickButtonAuthorization();
        csCartSettings.closeBottomAdminPanel();
    }
    @AfterMethod
    public void takeScreenShotOnFailure_closeBrowser(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot)DriverProvider.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("myErrorScreenshots\\" + testResult.getName() + "-"
                    + Arrays.toString(testResult.getParameters()) + ".jpg"));
        }
        DriverProvider.getDriver().quit();
        DriverProvider.destroyDriver();
    }
    public void focusBrowserTab(int tabNum) {
        ArrayList tabs = new ArrayList<> (DriverProvider.getDriver().getWindowHandles());
        DriverProvider.getDriver().switchTo().window(tabs.get(tabNum).toString());
        if(DriverProvider.getDriver().findElements(By.cssSelector(".cm-btn-success")).size()>=1){
            DriverProvider.getDriver().findElement(By.cssSelector(".cm-btn-success")).click();
        }
    }
    public void takeScreenShot(String screenshotName) throws IOException {
        File scrFile = ((TakesScreenshot) DriverProvider.getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("mySuccessScreenshots\\" + screenshotName + ".jpg"));
    }
    public void makePause(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
