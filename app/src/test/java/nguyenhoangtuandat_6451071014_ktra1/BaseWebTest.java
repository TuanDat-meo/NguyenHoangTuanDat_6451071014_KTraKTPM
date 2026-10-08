package nguyenhoangtuandat_6451071014_ktra1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class BaseWebTest {
    protected WebDriver driver;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getenv("CI"))) {
            options.addArguments("--headless=new", "--disable-dev-shm-usage");
        }
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
