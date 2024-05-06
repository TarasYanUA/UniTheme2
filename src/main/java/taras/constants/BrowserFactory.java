package taras.constants;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class BrowserFactory {
    private static Properties getProperties(){
        String current = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        String resourcesFolder = "src" + separator + "test" + separator + "resources";
        String resourcesFile = "data.properties";
        Path file = Paths.get(current + separator + resourcesFolder + separator + resourcesFile);

        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(file));
        } catch (IOException e) {
            throw new RuntimeException("File " + resourcesFile + " is NOT found.");
        }
        return properties;
    }

    public WebDriver createDriver(){
        WebDriver webDriver;
        BrowserType browserType = BrowserType.valueOf(getProperties().getProperty("browser"));
        switch (browserType) {
            case CHROME:
                System.setProperty(Constants.CHROME_DRIVER_PROPERTY, Constants.CHROME_DRIVER_PATH);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                webDriver = new ChromeDriver(options);
                break;
            case EDGE:
                System.setProperty(Constants.EDGE_DRIVER_PROPERTY, Constants.EDGE_DRIVER_PATH);
                webDriver = new EdgeDriver();
                break;
/*            case OPERA:
                System.setProperty(Constants.OPERA_DRIVER_PROPERTY, Constants.OPERA_DRIVER_PATH);
                webDriver = new OperaDriver());
                break;*/
            default:
                throw new IllegalStateException("Browser type does not exist: " + browserType);
        }
        return webDriver;
    }
}