package PageObjects;

import org.openqa.selenium.WebDriver;

import java.util.Random;

public class MainPage {

	private DSL dsl;

	public MainPage(WebDriver driver) {
		dsl = new DSL(driver);
	}

	//Static Elements:
	public String listOfProducts = "//div[@class='inventory_list']/div";
	public String backToProductsBtn = "//button[@id='back-to-products']";
	public String cartBtn = "//div[@id='shopping_cart_container']/a/span";

	//Dynamic Elements:
	public String itemDescription = "//div[@class='inventory_list']/div[*option*]/div[@class='inventory_item_description']/div[@class='inventory_item_label']/a/div";
	public String product = "//div[@class='inventory_list']/div[*option*]/div[@class='inventory_item_description']/div[@class='pricebar']/button";


	//Methods:

	public int randomNumberGenerator(int range) {
		Random random = new Random();
		return random.nextInt(range) + 1;
	}

	/**
	 * This function get the quantity of products on the main page.
	 * @return number of products showed on main screen.
	 */
	public int getProductListSize() {
		return dsl.getListSize(listOfProducts);
	}

	public void clickBackToProductsButton() {
		dsl.clickButtonByXpath(backToProductsBtn);
	}

	/**
	 * This function click on add button of a random product.
	 * @param range
	 */
	public void clickRandomItem(int range) {
		int randomNumber = randomNumberGenerator(range);
		String productChoosed = dsl.replaceString(product, "*option*", String.valueOf(randomNumber));
		dsl.clickAddToCartButton(productChoosed);
	}

	/**
	 * This function click on description of a random product.
	 * @param range
	 */
	public void clickRandomDescriptionItem(int range) {
		int randomNumber = randomNumberGenerator(range);
		String productChoosed = dsl.replaceString(itemDescription, "*option*", String.valueOf(randomNumber));
		dsl.clickButtonByXpath(productChoosed);
	}

	public void clickCartButton() {
		dsl.clickButtonByXpath(cartBtn);
	}
}
