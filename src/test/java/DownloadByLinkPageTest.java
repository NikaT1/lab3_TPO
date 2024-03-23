import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import tpo.lab.DownloadByLinkPage;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DownloadByLinkPageTest extends AbstractTest {
    public static final String CORRECT_URL_1 = "https://img.freepik.com/free-photo/fresh-yellow-daisy-single-flower-close-up-beauty-generated-by-ai_188544-15543.jpg?size=626&ext=jpg&ga=GA1.1.967060102.1711065600&semt=ais";
    public static final String CORRECT_URL_2 = "https://img.freepik.com/free-photo/cute-kitten-sitting-outdoors-looking-at-camera-surrounded-by-snow-generated-by-artificial-intelligence_188544-84910.jpg?size=626&ext=jpg&ga=GA1.1.967060102.1711065600&semt=ais";
    public static final String INCORRECT_URL = "https://github.com/NikaT1/lab3_TPO/tree/master";

    @Test
    public void checkCorrectlyDownloadImageByLink() throws InterruptedException {
        DownloadByLinkPage downloadByLinkPage = new DownloadByLinkPage(webDriver);
        downloadByLinkPage.addUrl(CORRECT_URL_1);
        downloadByLinkPage.beginDownload();
        waitDownloading(downloadByLinkPage);
        checkIfDownloaded();
    }

    @Test
    public void checkEmptyDownloadImageByLink() throws InterruptedException {
        DownloadByLinkPage downloadByLinkPage = new DownloadByLinkPage(webDriver);
        downloadByLinkPage.addUrl("");
        downloadByLinkPage.beginDownload();
        waitDownloading(downloadByLinkPage);
        checkIfNotDownloadedWIthErrorPage();
    }

    @Test
    public void checkIncorrectDownloadImageByLink() throws InterruptedException {
        DownloadByLinkPage downloadByLinkPage = new DownloadByLinkPage(webDriver);
        downloadByLinkPage.addUrl(INCORRECT_URL);
        downloadByLinkPage.beginDownload();
        waitDownloading(downloadByLinkPage);
        checkIfNotDownloadedWIthErrorPage();
    }

    @Test
    public void checkDownloadingWithSomeUrls() throws InterruptedException {
        DownloadByLinkPage downloadByLinkPage = new DownloadByLinkPage(webDriver);
        downloadByLinkPage.addUrl(CORRECT_URL_1 + "\n" + CORRECT_URL_2);
        downloadByLinkPage.beginDownload();
        waitDownloading(downloadByLinkPage);
        checkIfDownloaded();
        String imagesXpath = "/html/body/div[2]/main/div/div[5]";
        assertEquals(3, webDriver.findElement(By.xpath(imagesXpath))
                                 .findElements(By.className("col-md-3"))
                                 .size());
    }

    @Test
    public void checkDownloadingWithSameUrls() throws InterruptedException {
        DownloadByLinkPage downloadByLinkPage = new DownloadByLinkPage(webDriver);
        downloadByLinkPage.addUrl(CORRECT_URL_1 + "\n" + CORRECT_URL_1);
        downloadByLinkPage.beginDownload();
        waitDownloading(downloadByLinkPage);
        checkIfDownloaded();
        String imagesXpath = "/html/body/div[2]/main/div/div[5]";
        assertEquals(2, webDriver.findElement(By.xpath(imagesXpath))
                                 .findElements(By.className("col-md-3"))
                                 .size());
    }

    @Test
    public void checkDownloadingWithCorrectAndIncorrectUrl() throws InterruptedException {
        DownloadByLinkPage downloadByLinkPage = new DownloadByLinkPage(webDriver);
        downloadByLinkPage.addUrl(CORRECT_URL_1 + "\n" + INCORRECT_URL);
        downloadByLinkPage.beginDownload();
        waitDownloading(downloadByLinkPage);
        checkIfDownloaded();
        String imagesXpath = "/html/body/div[2]/main/div/div[5]";
        assertEquals(2, webDriver.findElement(By.xpath(imagesXpath))
                                 .findElements(By.className("col-md-3"))
                                 .size());
    }

    @Test
    public void checkDownloadingLimit30() throws InterruptedException {
        DownloadByLinkPage downloadByLinkPage = new DownloadByLinkPage(webDriver);
        String stringBuilder = CORRECT_URL_1
                + "\n"
                + CORRECT_URL_2
                + "\n"
                + "https://rookee.ru/upload/medialibrary/b9b/content_img.png"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/11/08/23/57/flower-8376030_1280.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/03/05/22/04/bird-8615360_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/05/30/08/34/apple-8027938_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/07/11/13/03/dachshund-8120554_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/09/02/18/07/escalator-8229360_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/10/05/18/34/toadstool-8296596_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/02/22/00/19/hexagon-8588837_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/09/04/10/12/bird-8232427_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/08/03/21/04/poppy-8167943_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/11/07/22/56/skyscraper-8373617_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/03/06/11/09/plane-8616271_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/12/01/18/05/fish-8424257_640.png"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/03/17/11/39/mountain-7858482_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/03/05/19/26/duck-8615153_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/03/06/09/32/liverworts-8616125_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/03/04/21/49/tracks-8613278_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/01/17/10/29/snowman-8514136_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/04/29/09/51/polana-kalatowki-7958161_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/02/24/10/48/solar-panels-8593759_640.png"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/02/16/19/22/green-parrot-8578205_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/02/25/13/56/clouds-7813335_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/02/28/07/42/european-shorthair-8601492_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/03/14/00/30/plant-7851213_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/02/25/13/30/art-8595775_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/03/05/20/48/church-8615302_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/11/24/17/22/pigeon-8410348_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/06/10/15/40/berlin-8054311_640.jpg"
                + "\n";
        downloadByLinkPage.addUrl(stringBuilder);
        downloadByLinkPage.beginDownload();
        waitDownloading(downloadByLinkPage);
        checkIfDownloaded();
        String imagesXpath = "/html/body/div[2]/main/div/div[5]";
        assertEquals(31, webDriver.findElement(By.xpath(imagesXpath))
                                  .findElements(By.className("col-md-3"))
                                  .size());
    }

    @Test
    public void checkDownloadingMoreThanLimit30() throws InterruptedException {
        DownloadByLinkPage downloadByLinkPage = new DownloadByLinkPage(webDriver);
        String stringBuilder = CORRECT_URL_1
                + "\n"
                + CORRECT_URL_2
                + "\n"
                + "https://rookee.ru/upload/medialibrary/b9b/content_img.png"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/11/08/23/57/flower-8376030_1280.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/03/05/22/04/bird-8615360_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/05/30/08/34/apple-8027938_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/07/11/13/03/dachshund-8120554_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/09/02/18/07/escalator-8229360_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/10/05/18/34/toadstool-8296596_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/02/22/00/19/hexagon-8588837_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/09/04/10/12/bird-8232427_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/08/03/21/04/poppy-8167943_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/11/07/22/56/skyscraper-8373617_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/03/06/11/09/plane-8616271_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/12/01/18/05/fish-8424257_640.png"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/03/17/11/39/mountain-7858482_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/03/05/19/26/duck-8615153_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/03/06/09/32/liverworts-8616125_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/03/04/21/49/tracks-8613278_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/01/17/10/29/snowman-8514136_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/04/29/09/51/polana-kalatowki-7958161_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/02/24/10/48/solar-panels-8593759_640.png"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/02/16/19/22/green-parrot-8578205_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/02/25/13/56/clouds-7813335_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/02/28/07/42/european-shorthair-8601492_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/03/14/00/30/plant-7851213_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/02/25/13/30/art-8595775_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2024/03/05/20/48/church-8615302_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/11/24/17/22/pigeon-8410348_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/09/25/19/58/piran-8275931_640.jpg"
                + "\n"
                + "https://cdn.pixabay.com/photo/2023/06/10/15/40/berlin-8054311_640.jpg"
                + "\n";
        downloadByLinkPage.addUrl(stringBuilder);
        downloadByLinkPage.beginDownload();
        waitDownloading(downloadByLinkPage, 20000);
        checkIfDownloaded();
        String imagesXpath = "/html/body/div[2]/main/div/div[5]";
        assertEquals(31, webDriver.findElement(By.xpath(imagesXpath))
                                  .findElements(By.className("col-md-3"))
                                  .size());
    }


}
