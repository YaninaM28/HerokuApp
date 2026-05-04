import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class SortableDataTablesTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkTableData() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/tables");
        String lastName = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td[1]")).getText();
        softAssert.assertEquals(lastName, "Smith");
        String firstName = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td[2]")).getText();
        softAssert.assertEquals(firstName, "John");
        String email = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td[3]")).getText();
        softAssert.assertEquals(email, "jsmith@gmail.com");
        String due = driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td[4]")).getText();
        softAssert.assertEquals(due, "$50.00");
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
