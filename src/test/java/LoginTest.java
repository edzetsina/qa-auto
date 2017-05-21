import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Created by Admin on 20.05.2017.
 */
public class LoginTest {

    @Test
    public void PositiveLoginTest() {

        WebDriver webDriver = new FirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        webDriver.navigate().to("https://alerts.shotspotter.biz/");


        webDriver.findElement(By.xpath("//input[@type='email']")).sendKeys("denvert1@shotspotter.net");
        webDriver.findElement(By.xpath("//input[@type='password']")).sendKeys("Test123!");
        webDriver.findElement(By.xpath("//*[@class='button' and text()='GO']")).click();
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(webDriver.getCurrentUrl());
        SoftAssert assertion = new SoftAssert();
        assertion.assertEquals(webDriver.getCurrentUrl(),"https://alerts.shotspotter.biz/main");

        System.out.println(webDriver.findElement(By.xpath("//*[@class='settings']")).isDisplayed());
        assertion.assertTrue(webDriver.findElement(By.xpath("//*[@class='settings']")).isDisplayed());

        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        webDriver.quit();
        assertion.assertAll();
    }

    @Test
    public void NegativeLoginTest1() {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        webDriver.navigate().to("https://alerts.shotspotter.biz/");


        webDriver.findElement(By.xpath("//input[@type='email']")).sendKeys("incorrectEmail");
        webDriver.findElement(By.xpath("//input[@type='password']")).sendKeys("IncorrectPassword");
        webDriver.findElement(By.xpath("//*[@class='button' and text()='GO']")).click();
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SoftAssert assertion = new SoftAssert();
        assertion.assertTrue(webDriver.findElement(By.xpath("//*[@class='invalid-credentials']")).isDisplayed());
        webDriver.quit();
        assertion.assertAll();

    }

}
