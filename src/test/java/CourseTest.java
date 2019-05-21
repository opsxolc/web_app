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
public class CourseTest {

    public void addUpdateDeletePredmTest(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        driver.findElement(By.linkText("Изменение курсов")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Изменение курсов"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/h/update_courses_show");
        driver.findElement(By.linkText("Добавить предмет")).click();
        wait.until(ExpectedConditions.titleIs("Добавить предмет"));
        driver.findElement(By.id("courseName")).sendKeys("Тестовый предмет");
        Select select = new Select(driver.findElement(By.id("cover")));
        select.selectByValue("flow");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Изменение курсов"));

        assert driver.findElements(By.tagName("td")).get(46).getText().contains("Тестовый предмет");
        assert driver.findElements(By.tagName("td")).get(47).getText().contains("Поток");

        driver.findElements(By.linkText("Изменить")).get(11).click();
        wait.until(ExpectedConditions.titleIs("Изменить предмет"));
        driver.findElement(By.id("courseName")).sendKeys("Тестовый предмет1");
        select = new Select(driver.findElement(By.id("cover")));
        select.selectByValue("student");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Изменение курсов"));

        assert driver.findElements(By.tagName("td")).get(46).getText().contains("Тестовый предмет1");
        assert driver.findElements(By.tagName("td")).get(47).getText().contains("Спецкурс");

        driver.findElements(By.linkText("Удалить")).get(11).click();

        driver.close();
    }

    public void addUpdateDeleteCourseTest(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        driver.findElement(By.linkText("Изменение курсов")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Изменение курсов"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/h/update_courses_show");
        driver.findElement(By.linkText("Добавить курс")).click();
        wait.until(ExpectedConditions.titleIs("Добавить курс"));
        Select select = new Select(driver.findElement(By.id("courseId")));
        select.selectByValue("3");
        select = new Select(driver.findElement(By.id("teacherId")));
        select.selectByValue("1");
        driver.findElement(By.id("year")).sendKeys("2019");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Изменение курсов"));

        WebElement tableCourse = driver.findElements(By.tagName("table")).get(2);

        assert tableCourse.findElements(By.tagName("td")).get(0).getText().contains("Линейная алгебра");
        assert tableCourse.findElements(By.tagName("td")).get(1).getText().contains("Поток");
        assert tableCourse.findElements(By.tagName("td")).get(2).getText().contains("Бахтин Владимир");


        tableCourse.findElement(By.linkText("Изменить")).click();
        wait.until(ExpectedConditions.titleIs("Изменение курса"));

        select = new Select(driver.findElement(By.id("courseId")));
        select.selectByValue("1");
        select = new Select(driver.findElement(By.id("teacherId")));
        select.selectByValue("8");
        driver.findElement(By.id("year")).sendKeys("2020");
        driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.titleIs("Изменение курсов"));

        tableCourse = driver.findElements(By.tagName("table")).get(2);

        assert tableCourse.findElements(By.tagName("td")).get(0).getText().contains("Конструирование ядра ОС");
        assert tableCourse.findElements(By.tagName("td")).get(1).getText().contains("Группа");
        assert tableCourse.findElements(By.tagName("td")).get(2).getText().contains("Вылиток Алексей");

        tableCourse.findElements(By.linkText("Удалить")).get(11).click();

        driver.close();
    }

}
