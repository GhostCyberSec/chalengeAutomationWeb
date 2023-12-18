package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class DSL {
    private WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    public void write(By by, String texto) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(texto);
    }

    public void write(String id_campo, String texto) {
        write(By.id(id_campo), texto);
    }

    public void writeQuantityOfProducts(String xpath, int quantity) {
        WebElement element = driver.findElement(By.xpath(xpath));
        // Use JavascriptExecutor to change the content of the <div>
        ((JavascriptExecutor) driver).executeScript("arguments[0].innerText = arguments[1];", element, quantity);
    }

    public int getListSize(String xpath) {
        List<WebElement> list = driver.findElements(By.xpath(xpath));
        return list.size();
    }


    public String replaceString(String elementToReplace, String oldChar, String newChar) {
         String stringReplaced = elementToReplace.replace(oldChar, newChar);
        return stringReplaced;
    }


    public void clickButton(String id) {
        driver.findElement(By.id(id)).click();
    }

    public void clickButtonByXpath(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public void clickAddToCartButton(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        if(!element.getText().equalsIgnoreCase("Remove")) {
            element.click();
        }
    }

    public WebElement createWebElement(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        return element;
    }
}
