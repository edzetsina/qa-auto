package test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.MainPage;

/**
 * Created by Admin on 20.05.2017.
 */
public class LoginTest {

    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new FirefoxDriver();
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    @Test
    public void PositiveLoginTest() {
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(loginPage.getPageURL(), "https://alerts.shotspotter.biz/", "Wrong url on Login Page");
        Assert.assertEquals(loginPage.getPageTitle(), "Shotspotter - Login", "Main page title is wrong");

        MainPage mainPage = loginPage.LoginToTheMainPage("denvert1@shotspotter.net","Test123!");
        Assert.assertTrue(mainPage.getPageURL().contains("https://alerts.shotspotter.biz/main"),"Wrong url after Login");
    }

    @Test
    public void NegativeLoginTest1() {
        String expectedErrorMsg = "The provided credentials are not correct.";

        LoginPage loginPage = new LoginPage(webDriver);

        loginPage = loginPage.LoginNegative("IncorrectEmail", "IncorrectPassword");
        Assert.assertTrue(loginPage.IsInvalidCredentialsDisplayed(), "Invalid credentials is not displayed");
        Assert.assertEquals(loginPage.getErrorText(), expectedErrorMsg, "Error text is wrong");
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded");
    }

}
