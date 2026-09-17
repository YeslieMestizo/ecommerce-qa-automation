package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    LoginPage loginPage;
    WebDriver driver;

    @BeforeEach
    void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
    }

    @AfterEach
    void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    void loginWithInvalidCredentials() {

        loginPage.open();

        loginPage.enterEmail("invalid@test.com");
        loginPage.enterPassword("wrongPassword123");
        loginPage.clickLogin();

            String actualMessage =  loginPage.getErrorMessage();

            String expectedMessage = "Your email or password is incorrect!";

            assertEquals(expectedMessage, actualMessage);

    }

    @Test
    void loginWithValidCredentials() {

        String email = System.getenv("AE_EMAIL");
        String password = System.getenv("AE_PASSWORD");

        if (email == null || password == null) {
            throw new IllegalStateException(
                    "AE_EMAIL y AE_PASSWORD deben estar configuradas como variables de entorno"
            );
        }

        loginPage.open();

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        String loggedInText = loginPage.getLoggedInText();

        assertTrue(
                loggedInText.contains("Logged in as"),
                "El usuario no inició sesión correctamente"
        );

    }

    @Test
    void loginWithEmptyEmail() {

        loginPage.open();

        loginPage.enterPassword("wrongPassword123");
        loginPage.clickLogin();

        String validationMessage =
                loginPage.getEmailValidationMessage();

        assertFalse(validationMessage.isEmpty());
    }

    @Test
    void loginWithEmptyPassword() {

        loginPage.open();

        loginPage.enterEmail("invalid@test.com");
        loginPage.clickLogin();

        String validationMessage =
                loginPage.getPasswordValidationMessage();

        assertFalse(validationMessage.isEmpty());
    }

    @Test
    void loginWithEmptyEmailAndPassword() {

        loginPage.open();

        loginPage.clickLogin();

        assertFalse(loginPage.isEmailValid());
        assertFalse(loginPage.isPasswordValid());
    }

    @ParameterizedTest
    @CsvSource({
            "user1@example.invalid, WrongPass123",
            "user2@example.invalid, 12345678",
            "testing@example.invalid, passwordXYZ"
    })
    void loginWithDifferentInvalidCredentials(
            String email,
            String password
    ) {

        loginPage.open();

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        String actualMessage =
                loginPage.getErrorMessage();

        assertEquals(
                "Your email or password is incorrect!",
                actualMessage
        );
    }

}
