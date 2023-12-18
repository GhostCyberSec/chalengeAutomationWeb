import PageObjects.CartPage;
import PageObjects.DSL;
import PageObjects.LoginPage;
import PageObjects.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class eCommerceTest {

    private WebDriver driver;
    private DSL dsl;
    LoginPage loginPage;
    MainPage mainPage;
    CartPage cartPage;
    @BeforeMethod
    public void inicializa() {
        System.setProperty("webdriver.gecko.driver", "/home/igor/estudos/estudos_selenium_java/drivers/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        dsl = new DSL(driver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterMethod
    public void finaliza() {
        driver.quit();
    }

    @Test (priority=1)
    public void addItemIntoCart() {
        loginPage.doLogin("standard_user", "secret_sauce");
        mainPage.clickRandomItem(mainPage.getProductListSize());
        mainPage.clickRandomItem(mainPage.getProductListSize());
    }

    @Test (priority=2)
    public void updateQuantitytemIntoCart() {
        loginPage.doLogin("standard_user", "secret_sauce");
        mainPage.clickRandomItem(mainPage.getProductListSize());
        mainPage.clickRandomItem(mainPage.getProductListSize());
        mainPage.clickCartIcon();
        cartPage.updateCartQuantity(3);
    }

    @Test (priority=3)
    public void removeItemFromCart() {
        loginPage.doLogin("standard_user", "secret_sauce");
        mainPage.clickRandomItem(mainPage.getProductListSize());
        mainPage.clickRandomItem(mainPage.getProductListSize());
        mainPage.clickCartIcon();
        cartPage.removeCartItems();
    }

    @Test (priority=4)
    public void jumpToItemDetails() {
        loginPage.doLogin("standard_user", "secret_sauce");
        mainPage.clickRandomDescriptionItem(mainPage.getProductListSize());
        mainPage.clickBackToProductsButton();
        mainPage.clickRandomDescriptionItem(mainPage.getProductListSize());
        mainPage.clickBackToProductsButton();
    }


}
