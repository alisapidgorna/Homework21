import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class TestSearchHomework {
    @Test(dataProvider="getData")
    public void doSearch(String text, String browser) throws MalformedURLException {

        System.out.println(browser);
        DesiredCapabilities capabilities = null;
        if(browser.equals("firefox")){

            capabilities = DesiredCapabilities.firefox();
            capabilities.setBrowserName("firefox");
            capabilities.setPlatform(Platform.ANY);

        }else if(browser.equals("ie")){

            capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setBrowserName("iexplore");
            capabilities.setPlatform(Platform.WINDOWS);

        }else if(browser.equals("chrome")){

            capabilities = DesiredCapabilities.chrome();
            capabilities.setBrowserName("chrome");

            capabilities.setPlatform(Platform.ANY);
        }

        //require for WebDriver
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
//RemoteWebDriver driver = new FirefoxDriver();
        driver.get("https://reston.ua/");
        driver.findElement(By.name("q")).sendKeys(text);
    }
    @DataProvider
    public Object[][] getData(){

        Object[][] data = new Object[2][2];
        data[0][0] = "River Grill";
        data[0][1] = "firefox";

        data[1][0] = "River Grill";
        data[1][1] = "chrome";

        return data;
    }
}