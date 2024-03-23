import java.io.File;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import tpo.lab.ConfProperties;
import tpo.lab.MainPage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest extends AbstractTest {

    @Test
    public void oldVersionButton_shouldRedirectToOldPageSuite() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOldVersionButton();
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("oldVersionPage");
        assertEquals(expected, actual);
    }

    @Test
    public void faqButton_shouldRedirectToFaqPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOnFaqButton();
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("faqPage");
        assertEquals(expected, actual);
    }

    @Test
    public void aboutServiceButton_shouldRedirectToFaqPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOnAboutServiceButton();
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("aboutServicePage");
        assertEquals(expected, actual);
    }

    @Test
    public void rulesButton_shouldRedirectToFaqPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOnRuleButton();
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("rulesPage");
        assertEquals(expected, actual);
    }

    @Test
    public void uploaderButton_shouldRedirectToFaqPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOnUploaderButton();
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("uploaderPage");
        assertEquals(expected, actual);
    }

    @Test
    public void feedbackButton_shouldRedirectToFaqPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOnFeedBackButton();
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("feedbackPage");
        assertEquals(expected, actual);
    }

    @Test
    public void fastPic_shouldRedirectToMainPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOnFastPicButton();
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("mainPage");
        assertEquals(expected, actual);
    }

    @Test
    public void androidUpButton_shouldDownloadApp() {
        MainPage mainPage = new MainPage(webDriver);
        File dir = new File(ConfProperties.getProperty("downloadPath"));
        int before = dir.list().length;
        mainPage.clickOnAndroidAppButton();
        int after = dir.list().length;
        assertEquals((before + 1), after);
    }

    @Test
    public void toolBarButton_shouldDownloadApp() {
        //todo
    }

    @Test
    public void downloadButton_shouldRedirectTiMainPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOnDownloadButton();
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("mainPage");
        assertEquals(expected, actual);
    }

    @Test
    public void downloadByLinkButton_shouldRedirectToDownloadByLinkPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOnDownloadByLinkButton();
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("downloadByLinkPage");
        assertEquals(expected, actual);
    }

    @Test
    public void myDownloadsButton_shouldRedirectToMyDownloadsPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOnMyDownloadsButton();
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("myDownloadsPage");
        assertEquals(expected, actual);
    }

    @Test
    public void enterButton_shouldRedirectToEnterPage() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOnEnterButton();
        String actual = webDriver.getCurrentUrl();
        String expected = ConfProperties.getProperty("enterPage");
        assertEquals(expected, actual);
    }

    @Test
    public void restrictionsButton_shouldOpenRestrictionMessage() {
        //todo
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.png",
            "/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.gif",
            "/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.jpeg",
            "/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.bmp",
            "/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.webp",
    })
    protected void downloadCorrectImage(String imagePath) throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectImage(imagePath);
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/tooBigImage.jpg",
            "/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/wrongExtention.txt"
    })
    public void downloadWrongImage(String imagePath) throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectImage(imagePath);
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfNotDownloaded();
    }


    @Test
    public void downloadALotOfImages() throws InterruptedException {
//        MainPage mainPage = new MainPage(webDriver);
//        for(int i=0;i<1001;i++) {
//            mainPage.selectImage("/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.png");
//        }
//        mainPage.clickOnBeginDownloadButton();
//        long start = System.currentTimeMillis();
//        while (!mainPage.isFinishedDownloading()){
//            sleep(1000);
//            if(System.currentTimeMillis()-MAX_DELAY>start){
//                fail();
//            }
//        }
//        String actual = webDriver.getCurrentUrl();
//        String expected = ConfProperties.getProperty("albumPage");
//        assertFalse(actual.startsWith(expected));
    }

    @Test
    public void checkCorrectPreviewRestrictions() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectImage("/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.png");
        mainPage.clickOnSettingsButton();
        mainPage.getPreview()
                .getPreviewSize()
                .changePreviewSize(String.valueOf(400));
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();

    }

    //error suite work
    @Test
    public void checkWrongSizeMoreThan400PreviewRestrictions() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectImage("/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.png");
        mainPage.clickOnSettingsButton();
        mainPage.getPreview()
                .getPreviewSize()
                .changePreviewSize(String.valueOf(500));
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfNotDownloaded();
    }

    //error suite work
    @Test
    public void checkWrongRestrictionRestrictions() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectImage("/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.png");
        mainPage.clickOnSettingsButton();
        mainPage.getPreview()
                .getPreviewSize()
                .changePreviewSize("hh");
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfNotDownloaded();
    }

    @Test
    public void checkWrongPreviewInscriptionRestrictions() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectImage("/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.png");
        mainPage.clickOnSettingsButton();
        mainPage.getPreview()
                .clickONInscriptionRadio();
        mainPage.getPreview()
                .addInscriptionText("kkk");
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
    }

    @Test
    public void checkEmptyPreviewInscriptionRestrictions() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectImage("/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.png");
        mainPage.clickOnSettingsButton();
        mainPage.getPreview()
                .clickONInscriptionRadio();
        mainPage.getPreview()
                .addInscriptionText("");
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
    }

    @Test
    public void checkWriteSizeOfImageRationPreview() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectImage("/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.png");
        mainPage.clickOnSettingsButton();
        mainPage.getPreview()
                .clickOnImageSizeRadio();
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
    }

    @Test
    public void checkWriteFilenameRationPreview() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectImage("/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.png");
        mainPage.clickOnSettingsButton();
        mainPage.getPreview()
                .clickOnWriteFileNameRadio();
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
    }

    @Test
    public void checkWithoutFilenameRationPreview() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectImage("/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.png");
        mainPage.clickOnSettingsButton();
        mainPage.getPreview()
                .clickOnWithoutInscriptionRation();
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
    }

    @Test
    public void checkDecreaseTillCorrectImage() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectImage("/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.png");
        mainPage.clickOnSettingsButton();
        mainPage.getImage()
                .clickOnDeacreaseTillButton();
        mainPage.getImage()
                .addDeacreaseText("200");
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
    }

    //error suite work
    @Test
    public void checkDecreaseTillWrongImage() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectImage("/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.png");
        mainPage.clickOnSettingsButton();
        mainPage.getImage()
                .clickOnDeacreaseTillButton();
        mainPage.getImage()
                .addDeacreaseText("hh");
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfNotDownloaded();
    }

    @Test
    public void checkDecreaseInBrowserImage() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectImage("/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.png");
        mainPage.clickOnSettingsButton();
        mainPage.getImage()
                .clickOnDecreaseInBrowser();
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
    }

    @Test
    public void checkOptimizeInJpgCorrectImage() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectImage("/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.png");
        mainPage.clickOnSettingsButton();
        mainPage.getImage()
                .clickOnOptimizeJPG();
        mainPage.getImage()
                .addOptimiseInJpgText("23");
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
    }

    @Test
    public void checkOptimizeInJpgIncorrectImage() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectImage("/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.png");
        mainPage.clickOnSettingsButton();
        mainPage.getImage()
                .clickOnOptimizeJPG();
        mainPage.getImage()
                .addOptimiseInJpgText("hh");
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
    }

    @Test
    public void checkPocterOptionImage() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectImage("/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.png");
        mainPage.clickOnSettingsButton();
        mainPage.getImage()
                .clickOnCover();
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
        String xPathToUrl = "//*[@id=\"summary-textarea\"]";
        System.out.println(webDriver.findElement(By.xpath(xPathToUrl))
                                    .getAttribute("value"));
        assertTrue(webDriver.findElement(By.xpath(xPathToUrl))
                            .getAttribute("value")
                            .startsWith("https://"));
    }

    @Test
    public void checkThatImageAddedToTheWrittenPoster() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectImage("/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.png");
        mainPage.clickOnSettingsButton();
        mainPage.getImage()
                .clickOnCover();
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
        String xPathToUrl = "//*[@id=\"summary-textarea\"]";
        assertTrue(webDriver.findElement(By.xpath(xPathToUrl))
                            .getAttribute("value")
                            .startsWith("https://"));
    }

    @Test
    public void checkThatImageAddedToTheAlbum() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.selectImage("/home/runtic/IdeaProjects/lab3_TPO/src/test/resources/images/test.png");
        mainPage.clickOnSettingsButton();
        String expectedAlbumName = "f";
        mainPage.getDownloadSettings()
                .addAlbumName(expectedAlbumName);
        waitDownloading(mainPage);
        checkIfDownloaded();
        String albumNameXpath = "/html/body/div[2]/main/div/div[1]/div/nav/ol/li[2]/span";
        assertEquals(expectedAlbumName, webDriver.findElement(By.xpath(albumNameXpath))
                                                 .getText());
    }


}
