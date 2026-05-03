import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DropdownTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkDropdown() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        // найти дропдаун
        WebElement dropdownElement = driver.findElement(By.id("dropdown"));
        Select dropdown = new Select(dropdownElement);
        // все опции в лист
        List<WebElement> options = dropdown.getOptions();
        // проверить 2 опции в дропдауне
        Assert.assertEquals(options.get(1).getText(), "Option 1");
        Assert.assertEquals(options.get(2).getText(), "Option 2");
        // выбираем опции и проверяем что выбрались
        dropdown.selectByVisibleText("Option 1");
        Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "Option 1");
        dropdown.selectByVisibleText("Option 2");
        Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "Option 2");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
