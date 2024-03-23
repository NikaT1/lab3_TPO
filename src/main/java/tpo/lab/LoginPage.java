package tpo.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By submitButton = By.name("submit");
    private final By createAccButton = By.linkText("Создать аккаунт");
    private final By emailField = By.name("email");
    private final By errorMessage = By.id("alert");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage login(String pass, String login) {
        driver.findElement(usernameField)
              .clear();
        driver.findElement(passwordField)
              .clear();
        driver.findElement(usernameField)
              .sendKeys(login);
        driver.findElement(passwordField)
              .sendKeys(pass);
        driver.findElement(submitButton)
              .click();
        return new MainPage(driver);
    }

}
