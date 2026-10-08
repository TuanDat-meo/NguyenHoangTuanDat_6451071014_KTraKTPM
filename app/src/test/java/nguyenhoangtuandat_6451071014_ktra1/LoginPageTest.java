package nguyenhoangtuandat_6451071014_ktra1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoginPageTest extends BaseWebTest {
    private LoginPage loginPage;

    @BeforeEach
    void openLoginPage() {
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @Test
    @DisplayName("TC_LOGIN_01 - Trang đăng nhập tải thành công")
    void loginPageLoads() {
        assertThat(loginPage.getCurrentUrl()).contains("/Login");
        assertThat(loginPage.getTitle()).contains("Đăng nhập");
    }

    @Test
    @DisplayName("TC_LOGIN_02 - Ô tên đăng nhập hiển thị và bật")
    void usernameFieldIsVisibleAndEnabled() {
        assertThat(loginPage.getUsernameField().isDisplayed()).isTrue();
        assertThat(loginPage.getUsernameField().isEnabled()).isTrue();
    }

    @Test
    @DisplayName("TC_LOGIN_03 - Ô mật khẩu được che")
    void passwordFieldIsVisibleAndMasked() {
        assertThat(loginPage.getPasswordField().isDisplayed()).isTrue();
        assertThat(loginPage.getPasswordField().getAttribute("type")).isEqualTo("password");
    }

    @Test
    @DisplayName("TC_LOGIN_04 - Nút đăng nhập hiển thị và bật")
    void submitButtonIsVisibleAndEnabled() {
        assertThat(loginPage.getSubmitButton().isDisplayed()).isTrue();
        assertThat(loginPage.getSubmitButton().isEnabled()).isTrue();
    }
}
