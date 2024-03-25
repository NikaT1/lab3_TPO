import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import tpo.lab.ConfProperties;
import tpo.lab.Page;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbstractTest {
    private final static long MAX_DELAY = 20000;
    protected static WebDriver webDriver;

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

    protected static void checkIfDownloaded() {
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("albumPage");
        System.out.println(actual);
        assertTrue(actual.startsWith(expected));
    }

    protected static void checkIfNotDownloaded() {
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("albumPage");
        assertFalse(actual.startsWith(expected));
    }

    protected static void checkIfNotDownloadedWIthErrorPage() {
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("errorPage");
        assertEquals(actual, expected);
    }

    protected static void waitDownloading(Page mainPage) throws InterruptedException {
        long start = System.currentTimeMillis();
        while (!mainPage.isFinishedDownloading()) {
            sleep(1000);
            if (System.currentTimeMillis() - MAX_DELAY > start) {
                break;
            }
        }
    }

    protected static void waitDownloading(Page mainPage, Integer waitTime) throws InterruptedException {
        long start = System.currentTimeMillis();
        while (!mainPage.isFinishedDownloading()) {
            sleep(1000);
            if (System.currentTimeMillis() - waitTime > start) {
                break;
            }
        }
    }

    @AfterAll
    public static void closeDriver() {
        webDriver.quit();
    }

}
