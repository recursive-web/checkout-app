package au.com.dius.store.app;

import au.com.dius.store.cache.ShoppingCart;
import au.com.dius.store.pricing.PricingRulesEngine;
import au.com.dius.store.product.Item;

/*
 * The main checkout class
 */
public class Checkout {

	private PricingRulesEngine pricingRules;
	private double totalCost;
	
	public Checkout(PricingRulesEngine pricingRules) {
		this.pricingRules = pricingRules;
		init();
	}
	
	/*
	 * Empties shopping cart of previous values (if existed)
	 */
	public void init() {
		emptyCart();
		
	}
	
	public void scan(Item item) throws IllegalArgumentException {
		ShoppingCart.INSTANCE.add(item);
	}
	
	public void total() {
		this.totalCost = pricingRules.calculateTotal();
	}
	
	private void emptyCart() {
		ShoppingCart.INSTANCE.truncate();
	}
	
	public double getTotalCost() {
		return totalCost;
	}
	
	
}
