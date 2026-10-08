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
}
