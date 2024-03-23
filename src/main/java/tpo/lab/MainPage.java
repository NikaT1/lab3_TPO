package tpo.lab;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page{


    public MainPage(WebDriver driver) {
        super(driver);
        driver.manage()
              .timeouts()
              .implicitlyWait(10, TimeUnit.SECONDS);
        goToTheMainPage(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToTheMainPage(WebDriver driver) {
        driver.get(ConfProperties.getProperty("mainPage"));
    }

    @FindBy(xpath = "/html/body/div[2]/footer/div/ul/li[1]/a")
    private WebElement faqbutton;

    @FindBy(xpath = "/html/body/div[2]/footer/div/ul/li[2]/a")
    private WebElement aboutServiceButton;
    @FindBy(xpath = "/html/body/div[2]/footer/div/ul/li[3]/a")
    private WebElement ruleseButton;
    @FindBy(xpath = "/html/body/div[2]/footer/div/ul/li[4]/a")
    private WebElement uploaderButton;
    @FindBy(xpath = "/html/body/div[2]/footer/div/ul/li[5]/a")
    private WebElement androidAppButton;
    @FindBy(xpath = "/html/body/div[2]/footer/div/ul/li[6]/a")
    private WebElement feedbackButton;
    @FindBy(xpath = "/html/body/div[2]/footer/div/ul/li[7]/a")
    private WebElement oldVersionButton;


    public void clickOldVersionButton() {
        oldVersionButton.click();
    }
    public void clickOnFaqButton(){
        faqbutton.click();
    }
    public void clickOnRuleButton(){
        ruleseButton.click();
    }
    public void clickOnUploaderButton(){
        uploaderButton.click();
    }
    public void clickOnAndroidAppButton(){
        androidAppButton.click();
    }
    public void clickOnFeedBackButton(){
        feedbackButton.click();
    }
    public void clickOnAboutServiceButton(){
        aboutServiceButton.click();
    }


}
