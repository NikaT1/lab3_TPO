import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class template {
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
    public void firsttest() {
        driver.get("https://new.fastpic.org/");
        driver.manage()
              .window()
              .setSize(new Dimension(1184, 692));
        driver.findElement(By.id("select"))
              .click();
        {
            WebElement element = driver.findElement(By.id("select"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element)
                   .perform();
        }
        driver.findElement(By.cssSelector(".fa-sign-in-alt"))
              .click();
        driver.findElement(By.name("username"))
              .click();
        driver.findElement(By.name("username"))
              .sendKeys("nika");
        driver.findElement(By.name("password"))
              .click();
        driver.findElement(By.name("password"))
              .sendKeys("121212");
        driver.findElement(By.name("submit"))
              .click();
        driver.findElement(By.name("username"))
              .click();
        driver.findElement(By.linkText("Создать аккаунт"))
              .click();
        driver.findElement(By.name("username"))
              .click();
        driver.findElement(By.name("username"))
              .sendKeys("troynikv@gmail.com");
        driver.findElement(By.name("email"))
              .click();
        driver.findElement(By.name("username"))
              .click();
        driver.findElement(By.name("email"))
              .click();
        driver.findElement(By.name("email"))
              .sendKeys("troynikv@gmail.com");
        driver.findElement(By.name("username"))
              .click();
        driver.findElement(By.name("username"))
              .sendKeys("nika");
        driver.findElement(By.name("password"))
              .click();
        {
            WebElement element = driver.findElement(By.name("submit"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element)
                   .perform();
        }
        driver.findElement(By.name("password"))
              .sendKeys("ms8=p9Zn]]r,GJ4");
        driver.findElement(By.name("submit"))
              .click();
        driver.findElement(By.linkText("Войти"))
              .click();
        driver.findElement(By.name("username"))
              .click();
        driver.findElement(By.name("password"))
              .click();
        driver.findElement(By.name("username"))
              .click();
        driver.findElement(By.name("username"))
              .sendKeys("nika");
        driver.findElement(By.name("password"))
              .click();
        driver.findElement(By.name("password"))
              .sendKeys("tytytyty");
        driver.findElement(By.name("password"))
              .sendKeys(Keys.ENTER);
        driver.findElement(By.linkText("Мои загрузки"))
              .click();
    }
}
