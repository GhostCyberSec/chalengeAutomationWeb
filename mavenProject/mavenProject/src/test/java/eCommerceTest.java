import PageObjects.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Utils.*;

//import static Utils.AuxiliarMethods.takeScreenshot;
import static Utils.AuxiliarMethods.takeScreenshot2;

public class eCommerceTest {

    ConfigReader configReader;
    String driverPath, appURL, userName, passWord;
    private WebDriver driver;
    private DSL dsl;
    LoginPage loginPage;
    MainPage mainPage;
    CartPage cartPage;
    CheckOutPage checkOutPage;

    String destinationFolder = "screenshots";


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
    public void finish() {
        driver.quit();
    }

    @Test (priority=1)
    public void addItemIntoCart() throws InterruptedException {
        mainPage.clickRandomItem(mainPage.getProductListSize());
        mainPage.clickRandomItem(mainPage.getProductListSize());
        Thread.sleep(3000);
        takeScreenshot2(driver, destinationFolder,"addItems.png");
    }

    @Test (priority=2)
    public void updateQuantityItemIntoCart() throws InterruptedException {
        cartPage = new CartPage(driver);
        mainPage.clickRandomItem(mainPage.getProductListSize());
        mainPage.clickRandomItem(mainPage.getProductListSize());
        mainPage.clickCartButton();
        Thread.sleep(2000);
        takeScreenshot2(driver, destinationFolder,"oldQuantityItems.png");
        cartPage.updateCartQuantity(3);
        Thread.sleep(2000);
        takeScreenshot2(driver, destinationFolder,"updateQuantityItems.png");
    }

    @Test (priority=3)
    public void removeItemFromCart() throws InterruptedException {
        cartPage = new CartPage(driver);
        mainPage.clickRandomItem(mainPage.getProductListSize());
        mainPage.clickRandomItem(mainPage.getProductListSize());
        mainPage.clickCartButton();
        Thread.sleep(2000);
        takeScreenshot2(driver, destinationFolder,"cartWithItems.png");
        cartPage.removeCartItems();
        Thread.sleep(2000);
        takeScreenshot2(driver, destinationFolder,"cartWihtOutItems.png");
    }

    @Test (priority=4)
    public void jumpToItemDetails() throws InterruptedException {
        mainPage.clickRandomDescriptionItem(mainPage.getProductListSize());
        Thread.sleep(2000);
        takeScreenshot2(driver, destinationFolder,"jumpToItemDetails_Produtc_"+ mainPage.getItemDetailsTitle() + ".png");
        mainPage.clickBackToProductsButton();
        mainPage.clickRandomDescriptionItem(mainPage.getProductListSize());
        Thread.sleep(2000);
        takeScreenshot2(driver, destinationFolder,"jumpToItemDetails_Produtc_"+ mainPage.getItemDetailsTitle() + ".png");
        mainPage.clickBackToProductsButton();
    }

    @Test (priority=5)
    public void startCheckOutProcess() throws InterruptedException {
        cartPage = new CartPage(driver);
        checkOutPage = new CheckOutPage(driver);
        mainPage.clickRandomItem(mainPage.getProductListSize());
        mainPage.clickRandomItem(mainPage.getProductListSize());
        Thread.sleep(2000);
        takeScreenshot2(driver, destinationFolder,"startCheckOutProcess_ProductsSelected.png");
        mainPage.clickCartButton();
        Thread.sleep(2000);
        takeScreenshot2(driver, destinationFolder,"startCheckOutProcess_CartScreen.png");
        cartPage.clickCheckOutButton();
        Assert.assertTrue(checkOutPage.validateYourInformationField());
        checkOutPage.fillFirstNameField();
        checkOutPage.fillLastNameField();
        checkOutPage.fillZipPostalCode();
        Thread.sleep(2000);
        takeScreenshot2(driver, destinationFolder,"startCheckOutProcess_CheckOutInformationScreen.png");
        checkOutPage.clickContinueButton();
        checkOutPage.clickFinishButton();
        Thread.sleep(2000);
        takeScreenshot2(driver, destinationFolder,"startCheckOutProcess_CheckOutCompleteMessage.png");
        Assert.assertTrue(checkOutPage.validateCheckOutCompleteMessage());
    }
}
