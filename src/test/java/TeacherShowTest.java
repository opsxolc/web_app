import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class TeacherShowTest {

    public void teacherByCourse(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        WebElement selectElem = driver.findElement(By.id("courseName"));
        Select select = new Select(selectElem);
        select.selectByVisibleText("ОС и ЯП распределённых ВС");
        selectElem.submit();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Список учителей"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/h/teacher_show");
        List<WebElement> table = driver.findElements(By.tagName("td"));
        Assert.assertEquals(table.get(0).getText(), "\n" +
                "                    Бахтин\n" +
                "                    Владимир\n" +
                "                    Александрович\n" +
                "                ");
        Assert.assertEquals(table.size(), 1);
        driver.close();
    }

    public void teacherShowFail(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        WebElement selectElem = driver.findElement(By.id("courseName"));
        Select select = new Select(selectElem);
        select.selectByVisibleText("Выберите курс");
        selectElem.submit();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Список учителей"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/h/teacher_show");
        assert driver.findElement(By.tagName("body")).getText().contains("Список пуст");
        driver.close();
    }

    public void teacherShowAll(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        driver.findElements(By.linkText("Показать полный список")).get(1).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Список учителей"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/h/teacher_show_all");
        List<WebElement> table = driver.findElements(By.tagName("tr"));
        Assert.assertEquals(table.size(), 9);
        driver.close();
    }

}
