import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ThreadParallelGridHomework {

    //safe threads
    public ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();
    public WebDriver driver;

    public ThreadLocal<DesiredCapabilities> dc = new ThreadLocal<DesiredCapabilities>();
    public DesiredCapabilities cap = null;

    //returns class with independent thread
    public WebDriver getDriver(){
        return dr.get();
    }

    public void setWebDriver(WebDriver driver){
        dr.set(driver);
    }

    public DesiredCapabilities getDesiredCapabilities(){
        return dc.get();
    }
    public void setDesiredCapabilities(DesiredCapabilities cap){
        dc.set(cap);
    }


    @Test(dataProvider = "getData")
    public void launchBrowser(String browser, String text) throws MalformedURLException {

        Date d = new Date();
        System.out.println(browser+" " + d.toString());

        if (browser.equals("chrome")) {

            cap = new DesiredCapabilities();
            setDesiredCapabilities(cap);
            getDesiredCapabilities().setPlatform(Platform.ANY);
            getDesiredCapabilities().setBrowserName("chrome");
            ChromeOptions options = new ChromeOptions();
            options.merge(getDesiredCapabilities());
        } else if (browser.equals("firefox")) {

            cap = new DesiredCapabilities();
            setDesiredCapabilities(cap);
            getDesiredCapabilities().setPlatform(Platform.ANY);
            getDesiredCapabilities().setBrowserName("firefox");

            FirefoxOptions options = new FirefoxOptions();
            options.merge(getDesiredCapabilities());

        }
        //require for WebDriver
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
        setWebDriver(driver);
        getDriver().get("https://smart-fox.com.ua/");
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
        getDriver().findElement(By.id("search")).sendKeys(text);
        getDriver().quit();
    }

    @DataProvider(parallel = true)
    public Object[][] getData() {

        Object[][] data = new Object[2][2];
        data[0][0] = "chrome";
        data[0][1] = "ARYA";

        data[1][0] = "firefox";
        data[1][1] = "ARYA";

        return data;
    }
}