
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import tpo.lab.ConfProperties;
import tpo.lab.MainPage;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageTest {
    private static WebDriver webDriver;

    @BeforeAll
    public static void initializeWebDriver() {
        if (ConfProperties.getProperty("driver")
                          .equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
            webDriver = new ChromeDriver();
        } else if (ConfProperties.getProperty("driver")
                                 .equals("firefox")) {
            System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("firefoxdriver"));
            webDriver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("there is not his driver: " + ConfProperties.getProperty("driver"));
        }
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void oldVersionButton_shouldRedirectToOldPageSuite() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOldVersionButton();
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("oldVersionPage");
        assertEquals(expected, actual);
    }
}
