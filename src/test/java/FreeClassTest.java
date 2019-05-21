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
public class FreeClassTest {

    public void freeClassTest(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        WebElement selectElem = driver.findElement(By.id("day"));
        Select select = new Select(selectElem);
        select.selectByVisibleText("Пятница");
        driver.findElement(By.id("pairNumsStr")).sendKeys("1,2,3,4,5");
        selectElem.submit();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Список аудиторий"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/h/classroom_show");
        List<WebElement> table = driver.findElements(By.tagName("tr"));
        Assert.assertEquals(table.size(), 4);
        driver.close();
    }

    public void freeClassTestFail(){
        WebDriver driver = new SafariDriver();
        driver.get("http://localhost:8080/h/");
        Assert.assertEquals(driver.getTitle(), "Главная");
        WebElement selectElem = driver.findElement(By.id("day"));
        Select select = new Select(selectElem);
        select.selectByVisibleText("День недели");
        selectElem.submit();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs("Список аудиторий"));
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/h/classroom_show");
        assert driver.findElement(By.tagName("body")).getText().contains("Вы ввели неверные данные.");
        driver.close();
    }

}
