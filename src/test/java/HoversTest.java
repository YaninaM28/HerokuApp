import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class HoversTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkHovers() {
        SoftAssert softAssert = new SoftAssert();
        Actions actions = new Actions(driver);
        driver.get("https://the-internet.herokuapp.com/hovers");
        for (int i = 0; i < 3; i++) {
            List<WebElement> profiles = driver.findElements(By.className("figure"));
            WebElement profile = profiles.get(i);
            actions.moveToElement(profile).perform();
            WebElement name = profile.findElement(By.tagName("h5"));
            String userName = name.getText();
            softAssert.assertTrue(userName.contains("user"), "Нет такого юзера!!!");
            WebElement link = profile.findElement(By.tagName("a"));
            link.click();
            softAssert.assertTrue(driver.getPageSource().contains("Not Found"), "Ошибка на странице!!!");
            driver.navigate().back();
        }
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
