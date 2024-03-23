import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import tpo.lab.LoginPage;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    static JavascriptExecutor js;
    private static WebDriver driver;
    private static Map<String, Object> vars;

    @BeforeAll
    public static void setUp() {
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void correctLoginTest() {
        driver.get("https://new.fastpic.org/");
        driver.manage()
              .window()
              .setSize(new Dimension(1184, 692));
        driver.findElement(By.cssSelector(".fa-sign-in-alt"))
              .click();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("trtrtry", "nika");
        WebElement login = driver.findElement(By.xpath("//div[@id='navbarNavAltMarkup']/div[2]/span"));
        assertEquals("nika", login.getText()
                                  .strip());
    }
}
