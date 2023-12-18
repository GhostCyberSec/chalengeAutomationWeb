package PageObjects;

import org.openqa.selenium.WebDriver;

import java.util.Random;

public class CartPage {

	private DSL dsl;

	public CartPage(WebDriver driver) {
		dsl = new DSL(driver);
	}

	//Static Elements:

	//BUTTONS
	public String checkOutBtn = "//button[@id='checkout']";

	//OTHERS
	public String cartItemList = "//div[@class='cart_item']";

	//Dynamic Elements:
	public String cartItem = "//div[@class='cart_item'][*option*]/div[@class='cart_quantity']";
	public String removeItemCart = "//div[@class='cart_item'][*option*]/div[@class='cart_item_label']/div[@class='item_pricebar']/button";


	//Methods:

	/**
	 * This function get the number of lines(items) on cart screen.
	 * @return size of list items.
	 */
	public int cartItemListSize() {
		return dsl.getListSize(cartItemList);
	}

	public void clickCheckOutButton() {
		dsl.clickButtonByXpath(checkOutBtn);
	}

	/**
	 * This function update the quantity of each product on cart screen.
	 * @param value quantity of each product.
	 */
	public void updateCartQuantity(int value) {
		for (int i = 1; i <= cartItemListSize(); i++) {
			String productChoosed = dsl.replaceString(cartItem, "*option*", String.valueOf(i));
			//dsl.clickCartQuantity(productChoosed);
			dsl.writeQuantityOfProducts(productChoosed, value);
		}
	}

	/**
	 * This function remove all products from cart screen.
	 */
	public void removeCartItems() {
		int itemListSize = cartItemListSize();
		System.out.println(itemListSize);
		for (int i = 1; i <= itemListSize; i++) {
			String productToRemove = dsl.replaceString(removeItemCart, "*option*", String.valueOf(1));
			dsl.clickButtonByXpath(productToRemove);
		}
	}
}
