
import org.junit.jupiter.api.Test;
import tpo.lab.ConfProperties;
import tpo.lab.LoginPage;
import tpo.lab.MainPage;
import tpo.lab.MyDownloadsPage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

public class MyDownloadsTest extends AbstractTest {
    private static final String TEST_IMAGE = "src\\test\\resources\\images\\test.png";
    private static final String TEST_IMAGE_NAME = "test.png";
    private static final String TEST_IMAGE2 = "src\\test\\resources\\images\\test.gif";
    private static final String TEST_IMAGE_NAME2 = "test.gif";
    private static final String CORRECT_NICKNAME = "nika";
    private static final String CORRECT_PASSWORD = "trtrtry";
    private static final String NEW_ALBUM_NAME = "test";

    @Test
    public void authDownloadsTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(CORRECT_PASSWORD, CORRECT_NICKNAME);
        sleep(2000);
        MainPage mainPage = new MainPage(webDriver);
        File file = new File(TEST_IMAGE);
        mainPage.selectImage(file.getAbsolutePath());
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
        loginPage.logout();
        loginPage.goToTheEnterPage(webDriver);
        loginPage.login(CORRECT_PASSWORD, CORRECT_NICKNAME);
        sleep(2000);
        MyDownloadsPage myDownloadsPage = new MyDownloadsPage(webDriver);
        assertEquals(TEST_IMAGE_NAME, myDownloadsPage.getFirstAlbumName());
    }

    @Test
    public void notAuthDownloadsTest() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        File file = new File(TEST_IMAGE);
        mainPage.selectImage(file.getAbsolutePath());
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
        MyDownloadsPage myDownloadsPage = new MyDownloadsPage(webDriver);
        assertEquals(TEST_IMAGE_NAME, myDownloadsPage.getFirstAlbumName());
    }

    @Test
    public void deleteImageDownloadsTest() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        File file = new File(TEST_IMAGE);
        mainPage.selectImage(file.getAbsolutePath());
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
        file = new File(TEST_IMAGE2);
        webDriver.get(ConfProperties.getProperty("mainPage"));
        mainPage.selectImage(file.getAbsolutePath());
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
        MyDownloadsPage myDownloadsPage = new MyDownloadsPage(webDriver);
        myDownloadsPage.deleteFirstAlbum();
        webDriver.get(ConfProperties.getProperty("myDownloadsPage"));
        assertEquals(TEST_IMAGE_NAME, myDownloadsPage.getFirstAlbumName());
    }

    @Test
    public void imageLinkCopyDownloadsTest() throws InterruptedException, IOException, UnsupportedFlavorException {
        MainPage mainPage = new MainPage(webDriver);
        File file = new File(TEST_IMAGE);
        mainPage.selectImage(file.getAbsolutePath());
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();

        MyDownloadsPage myDownloadsPage = new MyDownloadsPage(webDriver);
        myDownloadsPage.copyLinkFirstAlbum();
        myDownloadsPage.openFirstAlbum();
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        assertEquals(webDriver.getCurrentUrl(), "https://new.fastpic.org" + c.getData(DataFlavor.stringFlavor));
    }

    @Test
    public void renameAlbumDownloadsTest() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        File file = new File(TEST_IMAGE);
        mainPage.selectImage(file.getAbsolutePath());
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
        MyDownloadsPage myDownloadsPage = new MyDownloadsPage(webDriver);
        myDownloadsPage.openFirstAlbum();
        myDownloadsPage.renameAlbum(NEW_ALBUM_NAME);
        sleep(2000);
        assertEquals(NEW_ALBUM_NAME, myDownloadsPage.getAlbumName());
    }

    @Test
    public void rightCodesForPublicationDownloadsTest() throws InterruptedException, IOException, UnsupportedFlavorException {
        MyDownloadsPage myDownloadsPage = new MyDownloadsPage(webDriver);
        MainPage mainPage = new MainPage(webDriver);
        File file = new File(TEST_IMAGE);
        mainPage.selectImage(file.getAbsolutePath());
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
        myDownloadsPage.copyImageCodes();
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        String initCodes = (String) c.getData(DataFlavor.stringFlavor);

        myDownloadsPage.goToTheDownloadsPage(webDriver);
        myDownloadsPage.openFirstAlbum();
        myDownloadsPage.copyImageCodes();
        String myDownloadsCodes = (String) c.getData(DataFlavor.stringFlavor);
        assertEquals(initCodes, myDownloadsCodes);
    }

    //todo хз почему не работает - не находит кнопку повторной загрузки (selectImage)
    @Test
    public void addImageToAlbumFromDownloadsTest() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver);
        File file = new File(TEST_IMAGE);
        mainPage.selectImage(file.getAbsolutePath());
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
        MyDownloadsPage myDownloadsPage = new MyDownloadsPage(webDriver);
        myDownloadsPage.openFirstAlbum();
        myDownloadsPage.addImageToAlbum();
        sleep(2000);

        file = new File(TEST_IMAGE2);
        mainPage.selectImage(file.getAbsolutePath());
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();

        myDownloadsPage.goToTheDownloadsPage(webDriver);
        myDownloadsPage.openFirstAlbum();
        assertEquals(2, myDownloadsPage.getCountOfImagesInAlbum());
    }

    @Test
    public void openAllImagesDownloadsTest() throws InterruptedException, IOException, UnsupportedFlavorException {
        MyDownloadsPage myDownloadsPage = new MyDownloadsPage(webDriver);
        MainPage mainPage = new MainPage(webDriver);
        File file = new File(TEST_IMAGE);
        mainPage.selectImage(file.getAbsolutePath());
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
        myDownloadsPage.copyImageCodes();
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        String initCodes = (String) c.getData(DataFlavor.stringFlavor);

        file = new File(TEST_IMAGE2);
        webDriver.get(ConfProperties.getProperty("mainPage"));
        mainPage.selectImage(file.getAbsolutePath());
        mainPage.clickOnBeginDownloadButton();
        waitDownloading(mainPage);
        checkIfDownloaded();
        myDownloadsPage.copyImageCodes();
        initCodes = c.getData(DataFlavor.stringFlavor) + " " + initCodes;

        myDownloadsPage.goToTheDownloadsPage(webDriver);
        myDownloadsPage.openAllImages();
        myDownloadsPage.copyImageCodes();
        String myDownloadsCodes = (String) c.getData(DataFlavor.stringFlavor);
        assertEquals(initCodes, myDownloadsCodes);
    }

}
