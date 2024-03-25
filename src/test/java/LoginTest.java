import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tpo.lab.LoginPage;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends AbstractTest {

    // for log in account
    private static final String CORRECT_NICKNAME = "nika";
    private static final String INCORRECT_NICKNAME = "dftghkjklbnmkl";
    private static final String CORRECT_PASSWORD = "trtrtry";
    private static final String INCORRECT_PASSWORD = "gfhjklcgvhbjnkm";

    // for creation new account
    private static final String CORRECT_EMAIL = "test@gmail.com";
    private static final String INCORRECT_EMAIL = "test@test";

    private static final String CORRECT_CREATION_NICKNAME = "jjjjjjjj";
    private static final String INCORRECT_CREATION_NICKNAME = "jjjj']";
    private static final String CORRECT_CREATION_PASSWORD = "trtrtry";
    private static final String INCORRECT_CREATION_PASSWORD = "fghj";

    private static final String DUBLICATE_CREATION_NICKNAME = "pony";
    private static final String DUBLICATE_CREATION_EMAIL = "dubl@gmail.com";

    // error messages
    private static final String ERROR_MESSAGE_LOGIN = "Неверный логин или пароль";
    private static final String ERROR_MESSAGE_EMAIL_CREATE_ACCOUNT = "Неверный формат электронного адреса";
    private static final String ERROR_MESSAGE_LOGIN_CREATE_ACCOUNT = "Имя пользователя неверного формата, разрешены латинские буквы, цифры, _ и -";
    private static final String ERROR_MESSAGE_LOGIN_DUBL_CREATE_ACCOUNT = "Имя пользователя уже существует, выберите другое";
    private static final String ERROR_MESSAGE_EMAIL_DUBL_CREATE_ACCOUNT = "Пользователь с таким адресом уже существует";
    private static final String ERROR_MESSAGE_PASSWORD_RECOVERY = "Введен несуществующий логин или E-mail";


    @Test
    public void correctDataLoginTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(CORRECT_PASSWORD, CORRECT_NICKNAME);
        sleep(2000);
        assertTrue(loginPage.isSuccessLogin(CORRECT_NICKNAME));
        loginPage.logout();
    }

    @Test
    public void incorrectPasswordLoginTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(INCORRECT_PASSWORD, CORRECT_NICKNAME);
        sleep(2000);
        assertFalse(loginPage.isSuccessLogin(CORRECT_NICKNAME));
    }

    @Test
    public void incorrectNicknameLoginTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(CORRECT_PASSWORD, INCORRECT_NICKNAME);
        sleep(2000);
        assertFalse(loginPage.isSuccessLogin(INCORRECT_NICKNAME));
    }

    @Test
    public void errorMessageNicknameLoginTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(CORRECT_PASSWORD, INCORRECT_NICKNAME);
        sleep(2000);
        assertEquals(ERROR_MESSAGE_LOGIN, loginPage.getErrorMessageLogin());
    }

    @Test
    public void errorMessagePasswordLoginTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(INCORRECT_PASSWORD, CORRECT_NICKNAME);
        sleep(2000);
        assertEquals(ERROR_MESSAGE_LOGIN, loginPage.getErrorMessageLogin());
    }

    @ParameterizedTest
    @ValueSource(strings = {"df", "fdghjkcvbhnjmkvbnjmkvbnjkmvbhnjb", "fd]]]]"})
    public void incorrectLoginCreateAccountTest(String nickname) throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.createAccount(CORRECT_CREATION_PASSWORD, nickname, CORRECT_EMAIL);
        sleep(2000);
        assertFalse(loginPage.isSuccessLogin(nickname));
    }

    @Test
    public void incorrectPasswordCreateAccountTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.createAccount(INCORRECT_CREATION_PASSWORD, CORRECT_CREATION_NICKNAME, CORRECT_EMAIL);
        sleep(2000);
        assertFalse(loginPage.isSuccessLogin(CORRECT_CREATION_NICKNAME));
    }

    @ParameterizedTest
    @ValueSource(strings = {"fghj@ghbjn", "fgghbjn"})
    public void incorrectEmailCreateAccountTest(String email) throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.createAccount(CORRECT_CREATION_PASSWORD, CORRECT_CREATION_NICKNAME, email);
        sleep(2000);
        assertFalse(loginPage.isSuccessLogin(CORRECT_CREATION_NICKNAME));
    }

    // сайт поменял свое поведение?????? - вроде опять reCaptcha
    @Test
    public void errorMessageNicknameCreateAccountTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.createAccount(CORRECT_CREATION_PASSWORD, INCORRECT_CREATION_NICKNAME, CORRECT_EMAIL);
        sleep(2000);
        assertEquals(ERROR_MESSAGE_LOGIN_CREATE_ACCOUNT, loginPage.getErrorMessageNickname());
    }

    @Test
    public void errorMessageEmailCreateAccountTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.createAccount(CORRECT_CREATION_PASSWORD, CORRECT_CREATION_NICKNAME, INCORRECT_EMAIL);
        sleep(2000);
        assertEquals(ERROR_MESSAGE_EMAIL_CREATE_ACCOUNT, loginPage.getErrorMessageEmail());
    }


    @Test
    public void alreadyExistNicknameCreateAccountTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.createAccount(CORRECT_PASSWORD, DUBLICATE_CREATION_NICKNAME, CORRECT_EMAIL);
        sleep(2000);
        assertFalse(loginPage.isSuccessLogin(DUBLICATE_CREATION_NICKNAME));
    }

    @Test
    public void alreadyExistEmailCreateAccountTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.createAccount(CORRECT_PASSWORD, CORRECT_CREATION_NICKNAME, DUBLICATE_CREATION_EMAIL);
        sleep(2000);
        assertFalse(loginPage.isSuccessLogin(CORRECT_CREATION_NICKNAME));
    }

    @Test
    public void errorMessageAlreadyExistNicknameCreateAccountTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.createAccount(CORRECT_CREATION_PASSWORD, DUBLICATE_CREATION_NICKNAME, CORRECT_EMAIL);
        sleep(2000);
        assertEquals(ERROR_MESSAGE_LOGIN_DUBL_CREATE_ACCOUNT, loginPage.getErrorMessageNickname());
    }

    @Test
    public void errorMessageAlreadyExistEmailCreateAccountTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.createAccount(CORRECT_CREATION_PASSWORD, CORRECT_CREATION_NICKNAME, DUBLICATE_CREATION_EMAIL);
        sleep(2000);
        assertEquals(ERROR_MESSAGE_EMAIL_DUBL_CREATE_ACCOUNT, loginPage.getErrorMessageEmail());
    }

    //перестал проходить тест reCaptcha
    @Test
    public void newAccountCreateAccountTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        String newLogin = "pony" + System.currentTimeMillis();
        loginPage.createAccount("tail" + System.currentTimeMillis(), newLogin, (100 + System.currentTimeMillis()) + "@gmail.com");
        sleep(2000);
        assertTrue(loginPage.isSuccessLogin(newLogin));
        loginPage.logout();
    }

    //перестал проходить тест reCaptcha
    @Test
    public void correctPasswordRecoveryTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.recoverPassword(CORRECT_EMAIL);
        sleep(2000);
        assertTrue(loginPage.isPasswordRecoverySuccess());
    }

    @Test
    public void incorrectPasswordRecoveryTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.recoverPassword(INCORRECT_EMAIL);
        sleep(2000);
        assertEquals(ERROR_MESSAGE_PASSWORD_RECOVERY, loginPage.getErrorMessagePasswordRecovery());
    }
}
