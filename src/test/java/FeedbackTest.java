import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import tpo.lab.FeedbackPage;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FeedbackTest extends AbstractTest {
    @Test
    public void checkCorrectlyFillFeedbackForm() throws InterruptedException {
        FeedbackPage feedbackPage = new FeedbackPage(webDriver);
        feedbackPage.enterMail("haha@mail.ru");
        sleep(2000);
        feedbackPage.enterMessage("it's spam, please, i'm just testing");
        sleep(2000);
        feedbackPage.clickOnSendMessage();
        sleep(2000);
        assertTrue(feedbackPage.isSuccess());
    }

    @Test
    public void checkFeedbackFormWithEmptyMessage() throws InterruptedException {
        FeedbackPage feedbackPage = new FeedbackPage(webDriver);
        feedbackPage.enterMail("haha@mail.ru");
        feedbackPage.enterMessage("");
        feedbackPage.clickOnSendMessage();
        sleep(2000);
        assertThrows(NoSuchElementException.class, () -> feedbackPage.isSuccess());
    }

    @Test
    public void checkFeedbackFormWithWrongEmail() throws InterruptedException {
        FeedbackPage feedbackPage = new FeedbackPage(webDriver);
        feedbackPage.enterMail("haha@mail");
        feedbackPage.enterMessage("spam");
        feedbackPage.clickOnSendMessage();
        sleep(2000);
        assertThrows(NoSuchElementException.class, () -> feedbackPage.isSuccess());

    }

    @Test
    public void checkFeedbackFormWithEmptyEmail() throws InterruptedException {
        FeedbackPage feedbackPage = new FeedbackPage(webDriver);
        feedbackPage.enterMail("");
        feedbackPage.enterMessage("spam");
        feedbackPage.clickOnSendMessage();
        sleep(2000);
        assertThrows(NoSuchElementException.class, () -> feedbackPage.isSuccess());

    }
}
