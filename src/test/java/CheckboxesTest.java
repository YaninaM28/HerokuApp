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
import java.util.List;

public class CheckboxesTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkCheckboxes() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        // сделать лист с чекбаксами
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        // обозначить первый чекбокс
        WebElement firstCheckbox = checkboxes.get(0);
        // второй чекбокс
        WebElement secondCheckbox = checkboxes.get(1);
        // проверить что первый чекбокс анчекд
        softAssert.assertFalse(firstCheckbox.isSelected());
        // нажать чтобы сделать первый чекбокс чекнутым
        firstCheckbox.click();
        // проверить еще раз, что первый чекбокс чекнутый
        softAssert.assertTrue(firstCheckbox.isSelected());
        // проверить что второй чекбокс чекнутый
        softAssert.assertTrue(secondCheckbox.isSelected());
        // нажать чтобы второй стал анчек
        secondCheckbox.click();
        // проверка что второй чекбокс уже анчекед
        softAssert.assertFalse(secondCheckbox.isSelected());
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
