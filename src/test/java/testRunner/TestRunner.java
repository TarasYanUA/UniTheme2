package testRunner;

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
import static taras.constants.DriverProvider.getDriver;

public class TestRunner {
    @BeforeMethod
    public void prepareBrowser() {
        getDriver().get(BASIC_URL);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); //Общая задержка
        getDriver().manage().window().maximize();    //Размер браузера на весь экран
        getDriver().findElement(By.cssSelector(".btn.btn-primary")).click();
        getDriver().findElement(By.id("bp_off_bottom_panel")).click(); //Закрываем нижнюю панель
    }

    @AfterMethod
    public void takeScreenShotOnFailure_closeBrowser(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("myErrorScreenshots\\" + testResult.getName() + "-"
                    + Arrays.toString(testResult.getParameters()) + ".jpg"));
        }
        getDriver().quit();
        DriverProvider.destroyDriver();
    }

    public void focusBrowserTab(int tabNum) {
        ArrayList<String> tabs = new ArrayList<> (getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(tabNum));
    }

    public void takeScreenShot_withScroll(String screenshotName) {
        ((JavascriptExecutor) getDriver()).executeScript("scroll(0,130);");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("mySuccessScreenshots\\" + screenshotName + ".jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void takeScreenShot(String screenshotName) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
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

    public String getBlockID(String blockName) {
        String getBlockID = DriverProvider.getDriver().findElement(By.cssSelector("div[title='" + blockName + "'] small[data-ca-block-manager='block_id']")).getText();
        String blockID = getBlockID.trim().split("#")[1];
        System.out.println("ID блока '" + blockName + "': " + blockID);
        return blockID;
    }
}