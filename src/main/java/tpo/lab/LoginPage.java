package tpo.lab;

import org.openqa.selenium.By;
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
    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/p[2]/a")
    private WebElement passwordRecoveryLink;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/div[1]/input")
    private WebElement passwordRecoveryField;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/div[2]/button")
    private WebElement passwordRecoveryButton;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/div[1]/input")
    private WebElement usernameCreateField;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/div[3]/input")
    private WebElement passwordCreateField;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/div[2]/input")
    private WebElement emailCreateField;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/div[4]/button")
    private WebElement createAccButton;
    @FindBy(xpath = "//*[@id=\"navbarNavAltMarkup\"]/div[2]/span")
    private WebElement accountNickname;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/div[2]/div[2]/h6")
    private WebElement errorMessageLogin;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/div[1]/div[2]")
    private WebElement errorMessageNickname;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/div[2]/div[2]")
    private WebElement errorMessageEmail;
    @FindBy(xpath = "//*[@id=\"navbarNavAltMarkup\"]/div[2]/a[2]")
    private WebElement logoutButton;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/div/div/div/h2")
    private WebElement passwordRecoverySuccessMessage;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/article/form/div[1]/div[2]")
    private WebElement errorMessagePasswordRecovery;

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

    public void recoverPassword(String email) {
        passwordRecoveryLink.click();
        passwordRecoveryField.clear();
        passwordRecoveryField.sendKeys(email);
        passwordRecoveryButton.click();
    }

    public void logout() {
        logoutButton.click();
    }

    public String getAccountName() {
        return accountNickname.getText().strip();
    }

    public boolean isSuccessLogin(String name) {
        return driver.getCurrentUrl().equals(ConfProperties.getProperty("mainPage")) && getAccountName().equals(name);
    }

    public String getErrorMessageLogin() {
        return errorMessageLogin.getText();
    }

    public String getErrorMessageNickname() {
        return errorMessageNickname.getText();
    }

    public String getErrorMessageEmail() {
        return errorMessageEmail.getText();
    }

    public String getErrorMessagePasswordRecovery() {
        return errorMessagePasswordRecovery.getText();
    }

    public boolean isPasswordRecoverySuccess(){
        return passwordRecoverySuccessMessage.getText().strip().equals("Успешно!");
    }
}
