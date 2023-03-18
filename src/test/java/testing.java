
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;

import static java.time.Duration.*;
import static org.testng.AssertJUnit.assertEquals;

public class testing {
    WebDriver driver;
    @BeforeTest

    public void openBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver(options);
        //driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://elmomawel.com/sign-up");
    }
    @Test
    public void register() throws InterruptedException {

        driver.manage().window().maximize();

        driver.findElement(By.className("SignUp_Radio__1DbAe")).click();

        driver.findElement(By.name("first_name")).sendKeys("Sarah");

        driver.findElement(By.name("middle_name")).sendKeys("Hisham");

        driver.findElement(By.name("last_name")).sendKeys("Emara");

        //Picking birth date
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.scrollTo(0,0)");
        js1.executeScript("window.scrollTo(0,(document.body.scrollHeight/3))");
        driver.findElement(By.name("date_of_birth")).click();
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(60));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div[2]/div/div/div/div/div/div[2]/form/div[4]/div[1]/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div[4]")));
        option.click();

        //Choosing Gender
        Thread.sleep(1000);

        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("window.scrollTo(0,0)");
        js2.executeScript("window.scrollTo(0, (document.body.scrollHeight/3))");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@class='react-select__value-container css-1hwfws3']")).click();
        wait = new WebDriverWait(driver, ofSeconds(10));
        option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-select-2-option-0")));
        option.click();

        driver.findElement(By.name("phone")).sendKeys("01111608740");

        //Choosing the preferred language
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='react-select__placeholder css-1wa3eu0-placeholder']")).click();
        wait = new WebDriverWait(driver, ofSeconds(60));
        option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-select-3-option-0")));
        option.click();

        driver.findElement(By.name("email")).sendKeys("sarahh123@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Sarah1");
        driver.findElement(By.name("password_confirmation")).sendKeys("Sarah1");

        //Clicking on not robot check box
        WebElement elementToClick = driver.findElement(By.xpath("//*[@id='root']/div[2]/div/div/div/div/div/div[2]/form/div[11]/label/span"));
        Actions actions = new Actions(driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementToClick);
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 200)");
        elementToClick.click();

        //Submit Button
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/div/div/div/div[2]/form/div[12]/button")).click();

        //To assert that the current url equals the expected one
        String currentUrl;
        Thread.sleep(5000);

        currentUrl = driver.getCurrentUrl();
        assertEquals("https://elmomawel.com/", currentUrl);

    }

    @AfterTest
    public void close()
    {
        driver.quit();
    }
}