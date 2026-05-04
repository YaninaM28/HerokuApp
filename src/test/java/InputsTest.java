import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InputsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkInputs() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/inputs");
        // найти input поле
        WebElement inputElement = driver.findElement(By.tagName("input"));
        // ввести число
        inputElement.sendKeys("1");
        // проверить что число введено
        softAssert.assertEquals(inputElement.getAttribute("value"), "1");
        // увеличить число на 1 с помощью стрелки
        inputElement.sendKeys(Keys.ARROW_UP);
        // проверить, что число увеличилось
        softAssert.assertEquals(inputElement.getAttribute("value"), "2");
        // уменьшить число тоже с помощью стрелки
        inputElement.sendKeys(Keys.ARROW_DOWN);
        // снова проверить
        softAssert.assertEquals(inputElement.getAttribute("value"), "1");
        // очистить поле
        inputElement.clear();
        // проверить ввод букв (нельзя ввести все, крове "е")
        inputElement.sendKeys("1e5");
        softAssert.assertEquals(inputElement.getAttribute("value"), "1e5");
        inputElement.clear();
        inputElement.sendKeys("aaa");
        softAssert.assertEquals(inputElement.getAttribute("value"), "");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
