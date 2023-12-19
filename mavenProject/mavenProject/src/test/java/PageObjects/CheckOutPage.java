package PageObjects;

import Utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Random;

public class CheckOutPage {
	ConfigReader configReader;


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

	public String requiredMsg= "//h3[@data-test='error']";

	//BUTTONS
	public String continueBtn = "//input[@id='continue']";
	public String finishButton = "//button[@id='finish']";


	//Dynamic Elements:
	public String cartItem = "//div[@class='cart_item'][*option*]/div[@class='cart_quantity']";
	public String removeItemCart = "//div[@class='cart_item'][*option*]/div[@class='cart_item_label']/div[@class='item_pricebar']/button";


	public void fillFirstNameField() {
		configReader = new ConfigReader();
		dsl.write(By.xpath(firstNameInputField), configReader.getUserInformationFirstName());
	}

	public void fillLastNameField() {
		configReader = new ConfigReader();
		dsl.write(By.xpath(lastNameInputField), configReader.getUserInformationLastName());
	}
	public void fillZipPostalCode() {
		configReader = new ConfigReader();
		dsl.write(By.xpath(zipPostalCodeInputField), configReader.getUserInformationZipPostalCode());
	}

	/**
	 * Validate the message error when the fields are not filled on "Checkout: Your information" screen
	 * @return if all the messages is right return true. Else return false.
	 */
	public boolean validateYourInformationField() {
		Boolean allFieldsValidated;
		try {
			clickContinueButton();
			Assert.assertTrue(dsl.createWebElement(requiredMsg).getText().equalsIgnoreCase("Error: First Name is required"));
			fillFirstNameField();
			clickContinueButton();
			Assert.assertTrue(dsl.createWebElement(requiredMsg).getText().equalsIgnoreCase("Error: Last Name is required"));
			fillFirstNameField();
			fillLastNameField();
			clickContinueButton();
			Assert.assertTrue(dsl.createWebElement(requiredMsg).getText().equalsIgnoreCase("Error: Postal Code is required"));
			allFieldsValidated = true;
		} catch (Exception e) {
			allFieldsValidated = false;
		}
		return allFieldsValidated;
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
