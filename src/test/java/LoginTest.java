import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import tpo.lab.ConfProperties;
import tpo.lab.LoginPage;
import tpo.lab.MainPage;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    private static WebDriver driver;
    private final static long MAX_DELAY = 10000;
    private static String correctLogin;
    private static String incorrectLogin;
    private static String correctPassword;
    private static String incorrectPassword;
    private static String correctEmail;
    private static String incorrectEmail;

    @BeforeAll
    public static void initializeWebDriver() {
        if (ConfProperties.getProperty("driver")
                .equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
            driver = new ChromeDriver();
        } else if (ConfProperties.getProperty("driver")
                .equals("firefox")) {
            System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("firefoxdriver"));
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("there is not his driver: " + ConfProperties.getProperty("driver"));
        }
        correctLogin = ConfProperties.getProperty("correctLogin");
        incorrectLogin = ConfProperties.getProperty("incorrectLogin");
        correctEmail = ConfProperties.getProperty("correctEmail");
        incorrectEmail = ConfProperties.getProperty("incorrectEmail");
        correctPassword = ConfProperties.getProperty("correctPassword");
        incorrectPassword = ConfProperties.getProperty("incorrectPassword");
        driver.manage()
                .timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
    }


    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void correctLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(correctPassword, correctLogin);
        MainPage mainPage = new MainPage(driver);
        assertEquals(correctLogin, mainPage.getAccountName());
    }

    @Test
    public void incorrectPasswordLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(incorrectPassword, correctLogin);
        assertDoesNotThrow(() -> driver.findElement(By.xpath("/html/body/div[2]/main/div/div/article/form/div[2]/div[2]/h6")));
    }

    @Test
    public void incorrectNicknameLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(correctPassword, incorrectLogin);
        assertDoesNotThrow(() -> driver.findElement(By.xpath("/html/body/div[2]/main/div/div/article/form/div[2]/div[2]/h6")));
    }

    @Test
    public void errorMessageLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(incorrectPassword, incorrectLogin);
        assertEquals("Неверный логин или пароль", driver.findElement(By.xpath("/html/body/div[2]/main/div/div/article/form/div[2]/div[2]/h6")).getText());
    }

    @Test
    public void incorrectLoginCreateAccountTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.createAccount(correctPassword, "df", correctEmail);
        assertEquals("https://new.fastpic.org/register/", driver.getCurrentUrl());
    }

    @Test
    public void incorrectPasswordCreateAccountTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.createAccount("1111", correctLogin, correctEmail);
        assertEquals("https://new.fastpic.org/register/", driver.getCurrentUrl());
    }

    @Test
    public void incorrectEmailCreateAccountTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.createAccount(correctPassword, correctLogin, incorrectEmail);
        assertDoesNotThrow(() -> driver.findElement(By.xpath("/html/body/div[2]/main/div/div/article/form/div[2]/div[2]")));
    }

    @Test
    public void alreadyExistCreateAccountTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.createAccount(correctPassword, correctLogin, correctEmail);
        assertDoesNotThrow(() -> driver.findElement(By.xpath("/html/body/div[2]/main/div/div/article/form/div[1]/div[2]")));
        assertDoesNotThrow(() -> driver.findElement(By.xpath("/html/body/div[2]/main/div/div/article/form/div[2]/div[2]")));
    }

//    @Test
//    public void newAccountCreateAccountTest() {
//        LoginPage loginPage = new LoginPage(driver);
//        String newLogin = String.valueOf(System.currentTimeMillis());
//        loginPage.createAccount(newLogin, String.valueOf(System.currentTimeMillis()),  System.currentTimeMillis() + "@gmail.com");
//        MainPage mainPage = new MainPage(driver);
//        assertEquals(newLogin, mainPage.getAccountName());
//    }
}
