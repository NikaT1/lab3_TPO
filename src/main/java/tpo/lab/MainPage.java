package tpo.lab;

import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class MainPage extends Page {


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
    @FindBy(xpath = "/html/body/div[2]/nav/a")
    private WebElement fastPicButton;
    @FindBy(xpath = "/html/body/div[2]/nav/button")
    private WebElement toolBarButton;
    @FindBy(xpath = "//*[@id=\"navbarNavAltMarkup\"]/div[1]/a[1]")
    private WebElement downloadButton;
    @FindBy(xpath = "//*[@id=\"navbarNavAltMarkup\"]/div[1]/a[2]")
    private WebElement downloadByLink;
    @FindBy(xpath = "//*[@id=\"navbarNavAltMarkup\"]/div[2]/a[1]")
    private WebElement myDownloadsButton;
    @FindBy(xpath = "//*[@id=\"navbarNavAltMarkup\"]/div[2]/a[2]")
    private WebElement enterButton;
    @FindBy(xpath = "/html/body/div[2]/main/div/section/div/div[5]/button[2]")
    private WebElement restrictionsButton;
    @FindBy(xpath = "/html/body/div[2]/main/div/section/div/div[5]/button[1]")
    private WebElement settingButton;
    @FindBy(xpath = "//*[@id=\"select\"]")
    private WebElement selectImageButton;
    @FindBy(xpath = "/html/body/input")
    private WebElement hiddenSelectImageButton;
    @FindBy(xpath = "/html/body/div[2]/main/div/section/div/div[4]/div/button")
    private WebElement beginDownloadButton;
    @Getter
    private final Preview preview = new Preview();
    @Getter
    private final Image image = new Image();
    @Getter
    private final DownloadSettings downloadSettings = new DownloadSettings();
    public MainPage(WebDriver driver) {
        super(driver);
        driver.manage()
              .timeouts()
              .implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("mainPage"));
        PageFactory.initElements(driver, this);
    }

    public void clickOldVersionButton() {
        oldVersionButton.click();
    }

    public void clickOnFaqButton() {
        faqbutton.click();
    }

    public void clickOnRuleButton() {
        ruleseButton.click();
    }

    public void clickOnUploaderButton() {
        uploaderButton.click();
    }

    public void clickOnAndroidAppButton() {
        androidAppButton.click();
    }

    public void clickOnFeedBackButton() {
        feedbackButton.click();
    }

    public void clickOnAboutServiceButton() {
        aboutServiceButton.click();
    }

    public void clickOnFastPicButton() {
        fastPicButton.click();
    }

    public void clickOnToolBarButton() {
        toolBarButton.click();
    }

    public void clickOnDownloadButton() {
        downloadButton.click();
    }

    public void clickOnDownloadByLinkButton() {
        downloadByLink.click();
    }

    public void clickOnMyDownloadsButton() {
        myDownloadsButton.click();
    }

    public void clickOnEnterButton() {
        enterButton.click();
    }

    public void selectImage(String path) {
        selectImageButton.click();
        hiddenSelectImageButton.sendKeys(path);
    }

    public void clickOnSettingsButton() {
        settingButton.click();
    }

    public void clickOnRestrictionsButton() {
        restrictionsButton.click();
    }

    public void clickOnBeginDownloadButton() {
        beginDownloadButton.click();
    }


    public class DownloadSettings {
        @FindBy(xpath = "/html/body/div[2]/main/div/section/div/div[2]/div/select")
        private WebElement deleteSettings;
        @FindBy(xpath = "/html/body/div[2]/main/div/section/div/div[3]/div/input")
        private WebElement albumName;
        @FindBy(xpath = "//*[@id=\"choose-album\"]")
        private WebElement selectAlbum;
        public DownloadSettings() {
            PageFactory.initElements(driver, this);
        }

        public void addAlbumName(String text) {
            albumName.sendKeys(text);
        }

        public void selectDeleteOptionByIndex(Integer index) {
            new Select(deleteSettings).selectByIndex(index);
        }

        public void selectAlbumByIndex(Integer index) {
            new Select(selectAlbum).selectByIndex(index);
        }

        public List<WebElement> getMyAlbums() {
            return new Select(selectAlbum).getOptions();
        }

    }

    public class Image {
        @FindBy(xpath = "//*[@id=\"collapseSettings\"]/div[2]/div/div/div/div[1]/label")
        private WebElement decreaseTillButton;
        @FindBy(xpath = "//*[@id=\"orig-resize\"]")
        private WebElement decreaseTillText;
        @FindBy(xpath = "//*[@id=\"collapseSettings\"]/div[2]/div/div/div/div[2]/label")
        private WebElement decreaseInBrowser;
        @FindBy(xpath = "//*[@id=\"collapseSettings\"]/div[2]/div/div/div/div[3]/label")
        private WebElement optimizeInJPG;
        @FindBy(xpath = "//*[@id=\"jpeg-quality\"]")
        private WebElement optimizeInJPGText;
        @FindBy(xpath = "//*[@id=\"collapseSettings\"]/div[2]/div/div/div/div[4]/label")
        private WebElement cover;
        public Image() {
            PageFactory.initElements(driver, this);
        }

        public void clickOnDeacreaseTillButton() {
            decreaseTillButton.click();
        }

        public void addDeacreaseText(String text) {
            decreaseTillText.sendKeys(text);
        }

        public void addOptimiseInJpgText(String text) {
            optimizeInJPGText.sendKeys(text);
        }

        public void clickOnDecreaseInBrowser() {
            decreaseInBrowser.click();
        }

        public void clickOnOptimizeJPG() {
            optimizeInJPG.click();
        }

        public void clickOnCover() {
            cover.click();
        }
    }

    public class Preview {
        @FindBy(xpath = "//*[@id=\"collapseSettings\"]/div[1]/div/div/div/div[1]/label")
        private WebElement imageSizeRadio;
        @FindBy(xpath = "//*[@id=\"collapseSettings\"]/div[1]/div/div/div/div[2]/label")
        private WebElement inscriptionRadio;
        @FindBy(xpath = "//*[@id=\"thumb_text\"]")
        private WebElement inscriptionRadioText;
        @FindBy(xpath = "//*[@id=\"collapseSettings\"]/div[1]/div/div/div/div[3]/label")
        private WebElement writeFilenameRadio;
        @FindBy(xpath = "//*[@id=\"collapseSettings\"]/div[1]/div/div/div/div[3]/label")
        private WebElement withoutInscriptionRadio;
        @Getter
        private final PreviewSize previewSize = new PreviewSize();
        public Preview() {
            PageFactory.initElements(driver, this);
        }

        public void clickOnImageSizeRadio() {
            imageSizeRadio.click();
        }

        public void clickONInscriptionRadio() {
            inscriptionRadio.click();
        }

        public void clickOnWriteFileNameRadio() {
            writeFilenameRadio.click();
        }

        public void clickOnWithoutInscriptionRation() {
            withoutInscriptionRadio.click();
        }

        public void addInscriptionText(String text) {
            inscriptionRadioText.sendKeys(text);
        }

        public class PreviewSize {
            @FindBy(xpath = "//*[@id=\"txt-thumb-size\"]")
            private WebElement input;
            @FindBy(xpath = "//*[@id=\"collapseSettings\"]/div[1]/div/div/div/div[5]/div[3]/label")
            private WebElement byHeight;
            public PreviewSize() {
                PageFactory.initElements(driver, this);
            }

            public void changePreviewSize(String newSize) {
                input.sendKeys(newSize);
            }

            public void clickOnHeight() {
                byHeight.click();
            }
        }
    }

}
