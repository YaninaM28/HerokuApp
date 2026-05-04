import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class SeleniumTest {

    WebDriver driver;

    // BeforeMethod выполняется метод перед любым блоком
    @BeforeMethod
    public void setup() {
        /*
        Options - настройка нашего браузера перед его запуском
         */
        ChromeOptions options = new ChromeOptions();
        //открытие браузера в максимальном размере
        options.addArguments("--start-maximized");
        // открытие браузера в режиме инкогнито
        options.addArguments("--incognito");
        // открытие браузера без pop-up
        options.addArguments("--disable-notifications");
        // открытие браузера без ui интерфейса (то есть без видимого прохождения теста)
        // options.addArguments("--headless");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        // сворачивает браузер
        // driver.manage().window().minimize();
        // как нажать f12 полноэкранный режим
        driver.manage().window().fullscreen();
//        driver.manage().window().setSize(new Dimension(1920,1080));
//        driver.manage().window().setPosition(new Point(300, 300));
        //время ожидания появл элемента - нефвное ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        бесполезно, есть и так достаточно времени ожидания загрузкт страницы и прохождения скрипта
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
//        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
    }

    @Test
    public void test() {
        driver.get("https://www.onliner.by/");
        // покажет размер окна в котором тест запускается
        // System.out.println(driver.manage().window().getSize());
    }

    @Test
    public void cookiesTest() {
        driver.get("https://www.onliner.by/");
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie: cookies) {
            System.out.println(cookie.getName() + " :" + cookie.getValue());
        }
    }

    @Test
    public void checkHandle() {
        driver.get("https://www.onliner.by/");
//        driver.switchTo().newWindow(WindowType.WINDOW);
//        driver.switchTo().newWindow(WindowType.TAB);
        String mainHandle = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("---link-");
        String catalogHandle = driver.getWindowHandle();
        driver.switchTo().window(mainHandle);
        driver.switchTo().window(catalogHandle);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
