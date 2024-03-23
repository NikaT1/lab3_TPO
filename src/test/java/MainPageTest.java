import java.io.File;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterAll;
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
        webDriver.manage()
                 .timeouts()
                 .implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void oldVersionButton_shouldRedirectToOldPageSuite() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOldVersionButton();
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("oldVersionPage");
        assertEquals(expected, actual);
    }

    @Test
    public void faqButton_shouldRedirectToFaqPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOnFaqButton();
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("faqPage");
        assertEquals(expected, actual);
    }

    @Test
    public void aboutServiceButton_shouldRedirectToFaqPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOnAboutServiceButton();
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("aboutServicePage");
        assertEquals(expected, actual);
    }

    @Test
    public void rulesButton_shouldRedirectToFaqPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOnRuleButton();
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("rulesPage");
        assertEquals(expected, actual);
    }

    @Test
    public void uploaderButton_shouldRedirectToFaqPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOnUploaderButton();
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("uploaderPage");
        assertEquals(expected, actual);
    }

    @Test
    public void feedbackButton_shouldRedirectToFaqPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOnFeedBackButton();
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("feedbackPage");
        assertEquals(expected, actual);
    }

    @Test
    public void androidUpButton_shouldDownloadApp() {
        MainPage mainPage = new MainPage(webDriver);
        File dir = new File(ConfProperties.getProperty("downloadPath"));
        int before = dir.list().length;
        mainPage.clickOnAndroidAppButton();
        int after = dir.list().length;
        assertEquals((before + 1), after);
    }

    @AfterAll
    public static void closeDriver() {
        webDriver.quit();
    }
}
