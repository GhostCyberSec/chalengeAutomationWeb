package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class CheckOutPage {

	private DSL dsl;

	public CheckOutPage(WebDriver driver) {
		dsl = new DSL(driver);
	}

	//Static Elements:

	//INPUT
	public String firstNameInputField = "//input[@id='first-name']";
	public String lastNameInputField = "//input[@id='last-name']";
	public String zipPostalCodeInputField = "//input[@id='postal-code']";

	public String cartItemList = "//div[@class='cart_item']";
	public String checkOutCompleteMsg = "//h2[contains(text(), 'Thank you for your order!')]";

	//BUTTONS
	public String continueBtn = "//input[@id='continue']";
	public String finishButton = "//button[@id='finish']";


	//Dynamic Elements:
	public String cartItem = "//div[@class='cart_item'][*option*]/div[@class='cart_quantity']";
	public String removeItemCart = "//div[@class='cart_item'][*option*]/div[@class='cart_item_label']/div[@class='item_pricebar']/button";


	public void fillFirstNameField() {
		dsl.write(By.xpath(firstNameInputField), "John");
	}

	public void fillLastNameField() {
		dsl.write(By.xpath(lastNameInputField), "Smith");
	}
	public void fillZipPostalCode() {
		dsl.write(By.xpath(zipPostalCodeInputField), "123456789");
	}

	public void clickContinueButton() {
		dsl.clickButtonByXpath(continueBtn);
	}

	public void clickFinishButton() {
		dsl.clickButtonByXpath(finishButton);
	}

	public Boolean validateCheckOutCompleteMessage() {
		return dsl.createWebElement(checkOutCompleteMsg).getText().equalsIgnoreCase("Thank you for your order!");
	}
}
