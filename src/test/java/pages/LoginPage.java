package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By emailInput =
            By.cssSelector("[data-qa='login-email']");

    private By passwordInput =
            By.cssSelector("[data-qa='login-password']");

    private By loginButton =
            By.cssSelector("[data-qa='login-button']");

    private By errorMessage =
            By.cssSelector("form[action='/login'] p");

    private By loggedInUser =
            By.xpath("//a[contains(.,'Logged in as')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void open() {
        driver.get("https://automationexercise.com/login");
    }

    public void enterEmail(String email) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailInput)
        ).sendKeys(email);
    }


    public void enterPassword(String password) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordInput)
        ).sendKeys(password);
    }


    public void clickLogin() {
        wait.until(
                ExpectedConditions.elementToBeClickable(loginButton)
        ).click();
    }

    public String getErrorMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).getText();
    }


    public String getLoggedInText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(loggedInUser)
        ).getText();
    }

    public String getEmailValidationMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailInput)
        ).getDomProperty("validationMessage");
    }

    public String getPasswordValidationMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordInput)
        ).getDomProperty("validationMessage");
    }

    public boolean isEmailValid() {

        WebElement email = wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailInput)
        );

        return (Boolean) ((JavascriptExecutor) driver)
                .executeScript(
                        "return arguments[0].checkValidity();",
                        email
                );
    }

    public boolean isPasswordValid() {

        WebElement password = wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordInput)
        );

        return (Boolean) ((JavascriptExecutor) driver)
                .executeScript(
                        "return arguments[0].checkValidity();",
                        password
                );
    }

}
