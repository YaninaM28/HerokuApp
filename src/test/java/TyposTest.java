import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TyposTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkTypos() {
        driver.get("https://the-internet.herokuapp.com/typos");
        SoftAssert softAssert = new SoftAssert();
        String expectedParagraph = "Sometimes you'll see a typo, other times you won't.";
        for (int i = 0; i < 10; i++) {
            WebElement paragraph = driver.findElement(By.tagName("p"));
            String actualParagraph = paragraph.getText();
            // проверить текст
            softAssert.assertEquals(actualParagraph, expectedParagraph, "Орфографическая ошибка на итерации: " + i);
            // обновить страницу
            driver.navigate().refresh();
        }
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
