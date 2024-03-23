package tpo.lab;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FeedbackPage extends Page {
    @FindBy(xpath = "//*[@id=\"fb-email\"]")
    private WebElement inputEmail;
    @FindBy(xpath = "//*[@id=\"fb-message\"]")
    private WebElement inputMessage;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/form/button")
    private WebElement sendMessage;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/div/div/div/h2")
    private WebElement success;

    public FeedbackPage(WebDriver driver) {
        super(driver);
        driver.manage()
              .timeouts()
              .implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("feedbackPage"));
        PageFactory.initElements(driver, this);
    }

    public void enterMail(String text) {
        inputEmail.sendKeys(text);
    }

    public void enterMessage(String text) {
        sendMessage.sendKeys(text);
    }

    public void clickOnSendMessage() {
        sendMessage.click();
    }

    public boolean isSuccess() {
        return success.getText()
                      .equals("Успешно!");
    }
}
