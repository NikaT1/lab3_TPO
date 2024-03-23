package tpo.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By submitButton = By.name("submit");
    private By createAccButton = By.linkText("Создать аккаунт");
    private By emailField = By.name("email");
    private By errorMessage = By.id("alert");

    public MainPage login(String pass, String login) {
        driver.findElement(usernameField).clear();
        driver.findElement(passwordField).clear();
        driver.findElement(usernameField).sendKeys(login);
        driver.findElement(passwordField).sendKeys(pass);
        driver.findElement(submitButton).click();
        return new MainPage(driver);
    }

}
