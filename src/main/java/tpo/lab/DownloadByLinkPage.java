package tpo.lab;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DownloadByLinkPage extends Page {
    @FindBy(xpath = "/html/body/div[2]/main/div/section/div/div[1]/div[1]/form/div/button")
    private WebElement beginDownload;
    @FindBy(xpath = "//*[@id=\"uploadByUrl\"]")
    private WebElement textArea;

    public DownloadByLinkPage(WebDriver driver) {
        super(driver);
        driver.manage()
              .timeouts()
              .implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("downloadByLinkPage"));
        PageFactory.initElements(driver, this);
    }

    public void beginDownload() {
        beginDownload.click();
    }

    public void addUrl(String text) {
        textArea.sendKeys(text);
    }
}
