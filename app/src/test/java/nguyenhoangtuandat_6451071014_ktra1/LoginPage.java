package nguyenhoangtuandat_6451071014_ktra1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class LoginPage {
    private static final String LOGIN_URL =
            "https://vanphongdientu.utc.edu.vn/Login?r=https%3A%2F%2Fvanphongdientu.utc.edu.vn%2F";
    private static final By USERNAME_FIELD = By.cssSelector("input[type='text']");
    private static final By PASSWORD_FIELD = By.cssSelector("input[type='password']");
    private static final By SUBMIT_BUTTON = By.xpath(
            "//button[normalize-space()='Đăng nhập'] | //input[@type='submit' and @value='Đăng nhập']");
    private static final By FORGOT_PASSWORD_LINK = By.partialLinkText("quên mật khẩu");
    private static final By UTC_EMAIL_LOGIN_LINK = By.partialLinkText("Đăng nhập bằng e-mail UTC");
    private static final By REMEMBER_ME_CHECKBOX = By.id("persistent");
    private static final By REMEMBER_ME_LABEL = By.cssSelector("label[for='persistent']");

    private final WebDriver driver;
    private final WebDriverWait wait;

    LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    void open() {
        driver.get(LOGIN_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_FIELD));
        wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_FIELD));
    }

    String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    String getTitle() {
        return driver.getTitle();
    }

    WebElement getUsernameField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_FIELD));
    }

    WebElement getPasswordField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_FIELD));
    }

    WebElement getSubmitButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BUTTON));
    }

    void openForgotPasswordPage() {
        wait.until(ExpectedConditions.elementToBeClickable(FORGOT_PASSWORD_LINK)).click();
        wait.until(ExpectedConditions.urlContains("/Login/GetPass"));
    }

    WebElement getRememberMeCheckbox() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(REMEMBER_ME_CHECKBOX));
    }

    WebElement getRememberMeLabel() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(REMEMBER_ME_LABEL));
    }

    String getUtcEmailLoginUrl() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(UTC_EMAIL_LOGIN_LINK))
                .getAttribute("href");
    }
}
