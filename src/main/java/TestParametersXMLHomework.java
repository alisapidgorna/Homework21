import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestParametersXMLHomework {
    public WebDriver driver;
    public DesiredCapabilities cap = new DesiredCapabilities();
    @Parameters({"browser"})
    @Test
    public void launchBrowser(String browser) throws MalformedURLException {
        if(browser.equals("chrome")){
            cap.setPlatform(Platform.ANY);
            cap.setBrowserName("chrome");
            ChromeOptions options = new ChromeOptions();
            options.merge(cap);
        }else if(browser.equals("firefox")){
            cap.setPlatform(Platform.ANY);
            cap.setBrowserName("firefox");
            FirefoxOptions options = new FirefoxOptions();
            options.merge(cap);
        }
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
        driver.get("https://smart-fox.com.ua/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.findElement(By.id("search")).sendKeys("ARYA");
        driver.quit();
    }
}