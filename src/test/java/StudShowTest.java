import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class StudShowTest {

    public void testStudByGroup(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        WebElement groupNum = driver.findElement(By.id("groupNumber"));
        groupNum.sendKeys("328");
        groupNum.submit();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Список студентов"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/h/stud_show");
        List<WebElement> table = driver.findElements(By.tagName("td"));
        Assert.assertEquals(table.get(4).getText(), "\n" +
                "                    Рунышкин\n" +
                "                    Вячеслав\n" +
                "                    Дилшодович\n" +
                "                ");
        Assert.assertEquals(table.get(2).getText(), "\n" +
                "                    Пенёк\n" +
                "                    Армен\n" +
                "                    Кашпович\n" +
                "                ");
        driver.close();
    }

    public void testStudByGroupFail(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        WebElement groupNum = driver.findElement(By.id("groupNumber"));
        groupNum.sendKeys("-1");
        groupNum.submit();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Список студентов"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/h/stud_show");
        assert driver.findElement(By.tagName("body")).getText().contains("Введены неверные данные");
        driver.close();
    }

    public void testStudByFlow(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        WebElement flowNumber = driver.findElement(By.id("flowNumber"));
        flowNumber.sendKeys("1");
        WebElement yearOfStudy = driver.findElement(By.id("yearOfStudy"));
        yearOfStudy.sendKeys("1");
        flowNumber.submit();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Список студентов"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/h/stud_show");
        List<WebElement> table = driver.findElements(By.tagName("td"));
        Assert.assertEquals(table.get(2).getText(), "\n" +
                "                    Орнамов\n" +
                "                    Колыван\n" +
                "                    Георгиевич\n" +
                "                ");
        Assert.assertEquals(table.size(), 4);
        driver.close();
    }

    public void testStudByFlowFail(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        WebElement flowNumber = driver.findElement(By.id("flowNumber"));
        flowNumber.sendKeys("1");
        flowNumber.submit();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Список студентов"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/h/stud_show");
        assert driver.findElement(By.tagName("body")).getText().contains("Введены неверные данные");
        driver.close();
    }

    public void testStudFull(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        driver.findElements(By.linkText("Показать полный список")).get(0).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Список студентов"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/h/stud_show_all");
        List<WebElement> table = driver.findElements(By.tagName("tr"));
        Assert.assertEquals(table.size(), 9);
        driver.close();
    }

}
