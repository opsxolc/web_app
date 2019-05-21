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
public class CreateScheduleTest {

    public void addUpdateDeleteSemTest(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        driver.findElements(By.id("yearOfStudy")).get(1).sendKeys("3");
        driver.findElements(By.id("flowNumber")).get(1).sendKeys("3");
        driver.findElement(By.xpath("//button[@value=\"schedule_create\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Составление расписания"));
        driver.findElement(By.linkText("Добавить семинар")).click();
        wait.until(ExpectedConditions.titleIs("Добавить пару"));
        Select select = new Select(driver.findElement(By.id("tcId")));
        select.selectByValue("5");
        select = new Select(driver.findElement(By.id("classroomId")));
        select.selectByValue("4");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Составление расписания"));
        WebElement newSem = driver.findElement(By.tagName("td"));
        assert newSem.getText().contains("Конструирование ядра ОС");
        assert newSem.getText().contains("607");

        driver.findElement(By.linkText("Изменить")).click();
        wait.until(ExpectedConditions.titleIs("Изменить пару"));
        select = new Select(driver.findElement(By.id("tcId")));
        select.selectByValue("8");
        select = new Select(driver.findElement(By.id("classroomId")));
        select.selectByValue("6");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Составление расписания"));
        newSem = driver.findElement(By.tagName("td"));
        assert newSem.getText().contains("Уравнения математической физики");
        assert newSem.getText().contains("707");

        driver.findElement(By.linkText("Удалить")).click();
        wait.until(ExpectedConditions.urlContains("delete_pair"));
        assert driver.findElement(By.tagName("td")).getText().contains("Добавить семинар");

        driver.close();
    }

}
