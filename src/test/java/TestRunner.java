import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import taras.constants.DriverProvider;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

import static taras.constants.Constants.BASIC_URL;

public class TestRunner {
    @BeforeMethod
    public void prepareBrowser() {
        DriverProvider.getDriver().get(BASIC_URL);
        DriverProvider.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(4)); //Общая задержка
        DriverProvider.getDriver().manage().window().maximize();    //Размер браузера на весь экран
        DriverProvider.getDriver().findElement(By.cssSelector(".btn.btn-primary")).click();
        DriverProvider.getDriver().findElement(By.id("bp_off_bottom_panel")).click(); //Закрываем нижнюю панель
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
    }
    public void takeScreenShot_withScroll(String screenshotName) {
        ((JavascriptExecutor) DriverProvider.getDriver()).executeScript("scroll(0,130);");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File scrFile = ((TakesScreenshot) DriverProvider.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("mySuccessScreenshots\\" + screenshotName + ".jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void takeScreenShot(String screenshotName) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File scrFile = ((TakesScreenshot) DriverProvider.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("mySuccessScreenshots\\" + screenshotName + ".jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void makePause(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}