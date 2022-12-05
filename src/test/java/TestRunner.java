import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;
import taras.DriverProvider;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import static taras.Constants.BASIC_URL;

/*
Данный проект разработан для проверки отображения витрины с различными комбинациями настроек Юни темы.
Актуальная версия Юни темы 4.15.2d. Можно установить только саму тему, а также Пакет UniTheme2 (UltRu).
Рекомендуется запускать проект через файл testng.xml
 */

public class TestRunner {

    @BeforeMethod
    public void beforeMethod() {
        DriverProvider.getDriver().get(BASIC_URL);
        DriverProvider.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(4)); //Общая задержка
        DriverProvider.getDriver().manage().window().maximize();    //Размер браузера на весь экран
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot)DriverProvider.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("errorScreenshots\\" + testResult.getName() + "-"
                    + Arrays.toString(testResult.getParameters()) + ".jpg"));
        }
        DriverProvider.getDriver().quit();
        DriverProvider.destroyDriver();
    }
}
