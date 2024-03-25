package tpo.lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyDownloadsPage extends Page {

    public MyDownloadsPage(WebDriver driver) {
        super(driver);
        driver.manage()
                .timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
        goToTheDownloadsPage(driver);
    }

    public void goToTheDownloadsPage(WebDriver driver) {
        driver.get(ConfProperties.getProperty("myDownloadsPage"));
    }

    @FindBy(xpath = "/html/body/div[2]/main/div/div[2]/div[1]/div/div[2]/div[1]")
    private WebElement firstAlbumField;
    @FindBy(xpath = "/html/body/div[2]/main/div/div[2]/div[1]/div/div[2]/div[2]/div/button[2]/i")
    private WebElement deleteAlbumButton;
    @FindBy(xpath = "/html/body/div[2]/main/div/div[2]/div[1]/div/div[2]/div[2]/div/button[1]/i")
    private WebElement copyAlbumLinkButton;
    @FindBy(xpath = "//*[@id=\"deleteModal\"]/div/div/div[3]/button[2]")
    private WebElement confirmDeleteButton;
    @FindBy(xpath = "/html/body/div[2]/main/div/div[2]/div[1]/div/div[1]/a/img")
    private WebElement firstAlbumButton;
    @FindBy(xpath = "/html/body/div[2]/main/div/div[1]/div/nav/ol/li[2]/a/i")
    private WebElement changeNameAlbumButton;
    @FindBy(xpath = "//*[@id=\"newAlbumName\"]")
    private WebElement newNameAlbumField;
    @FindBy(xpath = "//*[@id=\"renameAlbum\"]")
    private WebElement confirmChangeAlbumNameButton;
    @FindBy(xpath = "/html/body/div[2]/main/div/div[1]/div/nav/ol/li[2]/span")
    private WebElement albumNameField;
    @FindBy(xpath = "/html/body/div[2]/main/div/div/div/div/div/div/div/div[2]/div/div/button/i")
    private WebElement copyImageCodesButton;
    @FindBy(xpath = "/html/body/div[2]/main/div/div[5]/div[2]/div/span/a")
    private WebElement addImageToAlbumButton;
    @FindBy(xpath = "/html/body/div[2]/main/div/div[1]/div/nav/ol/li/div/div/a")
    private WebElement openAllImagesButton;
    @FindBy(xpath = "//*[@id=\"dropdownMenuLink\"]")
    private WebElement openAlbumMenuButton;


    private List<WebElement> allBlocksOfAlbum = driver.findElements(By.xpath("/html/body/div[2]/main/div/div[5]/div"));


    public String getFirstAlbumName() {
        return firstAlbumField.getText().strip();
    }

    public void deleteFirstAlbum() {
        deleteAlbumButton.click();
        confirmDeleteButton.click();
    }

    public void copyLinkFirstAlbum() {
        copyAlbumLinkButton.click();
    }

    public void addImageToAlbum() {
        addImageToAlbumButton.click();
    }

    public void openFirstAlbum() {
        firstAlbumButton.click();
    }

    public String getAlbumName() {
        return albumNameField.getText();
    }

    public void renameAlbum(String newName) {
        changeNameAlbumButton.click();
        newNameAlbumField.sendKeys(newName);
        confirmChangeAlbumNameButton.click();
    }

    public void copyImageCodes() {
        copyImageCodesButton.click();
    }

    public void openAllImages() {
        openAlbumMenuButton.click();
        openAllImagesButton.click();
    }

    public int getCountOfImagesInAlbum() {
        return allBlocksOfAlbum.size() - 1;
    }

}
