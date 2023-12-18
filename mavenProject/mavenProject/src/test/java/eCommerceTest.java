import PageObjects.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Utils.*;

public class eCommerceTest {

    ConfigReader configReader;
    String driverPath, appURL, userName, passWord;
    private WebDriver driver;
    private DSL dsl;
    LoginPage loginPage;
    MainPage mainPage;
    CartPage cartPage;
    CheckOutPage checkOutPage;


    @BeforeMethod
    public void setup() {
        configReader = new ConfigReader();
        userName = configReader.getUserName();
        passWord = configReader.getPassWord();
        appURL = configReader.getAppURL();
        driverPath = configReader.getDriverPath();
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(appURL);
        dsl = new DSL(driver);
        loginPage = new LoginPage(driver);
        loginPage.doLogin(userName, passWord);
        mainPage = new MainPage(driver);
    }

    @AfterMethod
    public void finaliza() {
        driver.quit();
    }

    @Test (priority=1)
    public void addItemIntoCart() {
        mainPage.clickRandomItem(mainPage.getProductListSize());
        mainPage.clickRandomItem(mainPage.getProductListSize());
    }

    @Test (priority=2)
    public void updateQuantitytemIntoCart() {
        cartPage = new CartPage(driver);
        mainPage.clickRandomItem(mainPage.getProductListSize());
        mainPage.clickRandomItem(mainPage.getProductListSize());
        mainPage.clickCartButton();
        cartPage.updateCartQuantity(3);
    }

    @Test (priority=3)
    public void removeItemFromCart() {
        cartPage = new CartPage(driver);
        mainPage.clickRandomItem(mainPage.getProductListSize());
        mainPage.clickRandomItem(mainPage.getProductListSize());
        mainPage.clickCartButton();
        cartPage.removeCartItems();
    }

    @Test (priority=4)
    public void jumpToItemDetails() {
        mainPage.clickRandomDescriptionItem(mainPage.getProductListSize());
        mainPage.clickBackToProductsButton();
        mainPage.clickRandomDescriptionItem(mainPage.getProductListSize());
        mainPage.clickBackToProductsButton();
    }

    @Test (priority=5)
    public void startCheckOutProcess() {
        cartPage = new CartPage(driver);
        checkOutPage = new CheckOutPage(driver);
        mainPage.clickRandomItem(mainPage.getProductListSize());
        mainPage.clickRandomItem(mainPage.getProductListSize());
        mainPage.clickCartButton();
        cartPage.clickCheckOutButton();
        checkOutPage.fillFirstNameField();
        checkOutPage.fillLastNameField();
        checkOutPage.fillZipPostalCode();
        checkOutPage.clickContinueButton();
        checkOutPage.clickFinishButton();
        Assert.assertTrue(checkOutPage.validateCheckOutCompleteMessage());
    }
}
