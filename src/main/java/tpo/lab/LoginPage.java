package tpo.lab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LoginPage extends Page {

    public LoginPage(WebDriver driver) {
        super(driver);
        driver.manage()
                .timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
        goToTheEnterPage(driver);
    }

    public void goToTheEnterPage(WebDriver driver) {
        driver.get(ConfProperties.getProperty("enterPage"));
    }

    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/div[1]/input")
    private WebElement usernameField;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/div[2]/input")
    private WebElement passwordField;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/div[3]/button")
    private WebElement submitButton;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/p[1]/a")
    private WebElement createAccLink;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/div[1]/input")
    private WebElement usernameCreateField;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/div[3]/input")
    private WebElement passwordCreateField;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/div[2]/input")
    private WebElement emailCreateField;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/div[4]/button")
    private WebElement createAccButton;

    public void login(String pass, String login) {
        usernameField.clear();
        passwordField.clear();
        usernameField.sendKeys(login);
        passwordField.sendKeys(pass);
        submitButton.click();
    }

    public void createAccount(String pass, String login, String email) {
        createAccLink.click();
        usernameCreateField.clear();
        passwordCreateField.clear();
        emailCreateField.clear();
        usernameCreateField.sendKeys(login);
        passwordCreateField.sendKeys(pass);
        emailCreateField.sendKeys(email);
        createAccButton.click();
    }

}
