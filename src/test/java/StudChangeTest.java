import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class StudChangeTest {

    public void addUpdateDeleteStud(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        driver.findElement(By.linkText("Изменение студентов")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Изменение студентов"));
        Assert.assertEquals(driver.findElements(By.tagName("tr")).size(), 7);
        driver.findElement(By.linkText("Добавить студента")).click();
        wait.until(ExpectedConditions.titleIs("Добавление студента"));
        driver.findElement(By.id("lastname")).sendKeys("Тестов");
        driver.findElement(By.id("firstname")).sendKeys("Тест");
        driver.findElement(By.id("patronymic")).sendKeys("Тестович");
        Select select = new Select(driver.findElement(By.id("groupId")));
        select.selectByValue("1");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Изменение студентов"));
        assert driver.findElement(By.tagName("td")).getText().contains("Тестов Тест Тестович");
        assert driver.findElements(By.tagName("td")).get(1).getText().contains("102");

        driver.findElement(By.linkText("Изменить")).click();
        wait.until(ExpectedConditions.titleIs("Изменение студента"));
        driver.findElement(By.id("lastname")).sendKeys("1Тестов");
        driver.findElement(By.id("firstname")).sendKeys("1Тест");
        driver.findElement(By.id("patronymic")).sendKeys("1Тестович");
        select = new Select(driver.findElement(By.id("groupId")));
        select.selectByValue("2");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Изменение студентов"));
        assert driver.findElements(By.tagName("td")).get(3).getText().contains("1Тестов 1Тест 1Тестович");
        assert driver.findElements(By.tagName("td")).get(4).getText().contains("202");

        driver.findElements(By.linkText("Удалить")).get(1).click();
        driver.findElement(By.linkText("На главную")).click();
        wait.until(ExpectedConditions.titleIs("Главная"));
        driver.findElement(By.linkText("Изменение студентов")).click();
        wait.until(ExpectedConditions.titleIs("Изменение студентов"));
        Assert.assertEquals(driver.findElements(By.tagName("tr")).size(), 7);
        driver.close();
    }

    public void addUpdateDeleteTeacher(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        driver.findElement(By.linkText("Изменение преподавателей")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Изменение преподавателей"));
        Assert.assertEquals(driver.findElements(By.tagName("tr")).size(), 10);
        driver.findElement(By.linkText("Добавить преподавателя")).click();
        wait.until(ExpectedConditions.titleIs("Добавить преподавателя"));
        driver.findElement(By.id("lastname")).sendKeys("Тестов");
        driver.findElement(By.id("firstname")).sendKeys("Тест");
        driver.findElement(By.id("patronymic")).sendKeys("Тестович");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Изменение преподавателей"));
        assert driver.findElements(By.tagName("td")).get(2).getText().contains("Тестов Тест Тестович");

        driver.findElements(By.linkText("Изменить")).get(1).click();
        wait.until(ExpectedConditions.titleIs("Изменение преподавателя"));
        driver.findElement(By.id("lastname")).sendKeys("1Тестов");
        driver.findElement(By.id("firstname")).sendKeys("1Тест");
        driver.findElement(By.id("patronymic")).sendKeys("1Тестович");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Изменение преподавателей"));
        assert driver.findElements(By.tagName("td")).get(0).getText().contains("1Тестов 1Тест 1Тестович");

        driver.findElements(By.linkText("Удалить")).get(0).click();
        driver.findElement(By.linkText("На главную")).click();
        wait.until(ExpectedConditions.titleIs("Главная"));
        driver.findElement(By.linkText("Изменение преподавателей")).click();
        wait.until(ExpectedConditions.titleIs("Изменение преподавателей"));
        Assert.assertEquals(driver.findElements(By.tagName("tr")).size(), 10);
        driver.close();
    }

}
