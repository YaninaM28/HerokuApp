import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class FileDownloadTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkFileDownload() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/download");
        // скачиваем первый файл
        driver.findElement(By.cssSelector("a[href*='download']")).click();
        // ждём загрузку
        Thread.sleep(5000);
        String downloadPath = "D:\\Мои документы\\Загрузки";
        File folder = new File(downloadPath);
        // получаем файлы
        File[] files = folder.listFiles();
        // проверяем что папка не пустая
        assertTrue(files != null && files.length > 0,
                "File was not downloaded");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
