import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class ScheduleTest {

    public void scheduleStudTest(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        driver.findElement(By.linkText("Просмотр расписания")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Просмотр расписания"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/h/schedule_form");
        driver.findElement(By.id("lastname")).sendKeys("Пенёк");
        driver.findElement(By.id("firstname")).sendKeys("Армен");
        driver.findElement(By.id("patronymic")).sendKeys("Кашпович");
        driver.findElement(By.id("daysStr")).sendKeys("1,2,3");
        driver.findElement(By.xpath("//button[@value=\"schedule_show_stud\"]")).click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/h/schedule_show_stud"));
        Assert.assertEquals(driver.findElements(By.tagName("td")).size(), 18);
        driver.close();
    }

    public void scheduleStudFailTest(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        driver.findElement(By.linkText("Просмотр расписания")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Просмотр расписания"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/h/schedule_form");
        driver.findElement(By.id("lastname")).sendKeys("Пенёк");
        driver.findElement(By.id("firstname")).sendKeys("Арме");
        driver.findElement(By.id("patronymic")).sendKeys("Кашпович");
        driver.findElement(By.id("daysStr")).sendKeys("1,2,3");
        driver.findElement(By.xpath("//button[@value=\"schedule_show_stud\"]")).click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/h/schedule_show_stud"));
        assert driver.findElement(By.tagName("body")).getText().contains("Введены неверные данные");
        driver.findElement(By.tagName("input")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/h/schedule_form");
        driver.close();
    }

    public void scheduleTeacherTest(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        driver.findElement(By.linkText("Просмотр расписания")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Просмотр расписания"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/h/schedule_form");
        driver.findElement(By.id("lastname")).sendKeys("Бахтин");
        driver.findElement(By.id("firstname")).sendKeys("Владимир");
        driver.findElement(By.id("patronymic")).sendKeys("Александрович");
        driver.findElement(By.id("daysStr")).sendKeys("1,2,5");
        driver.findElement(By.xpath("//button[@value=\"schedule_show_teacher\"]")).click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/h/schedule_show_teacher"));
        Assert.assertEquals(driver.findElements(By.tagName("td")).size(), 18);
        driver.close();
    }

    public void scheduleClassTest(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        driver.findElement(By.linkText("Просмотр расписания")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Просмотр расписания"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/h/schedule_form");
        driver.findElement(By.id("classroom")).sendKeys("607");
        driver.findElement(By.id("daysStr")).sendKeys("3,5");
        driver.findElement(By.xpath("//button[@value=\"schedule_show_classroom\"]")).click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/h/schedule_show_classroom"));
        Assert.assertEquals(driver.findElements(By.tagName("td")).size(), 12);
        driver.close();
    }

}
