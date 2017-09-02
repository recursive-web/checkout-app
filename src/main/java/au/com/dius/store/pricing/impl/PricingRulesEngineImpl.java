package au.com.dius.store.pricing.impl;

import java.util.Map.Entry;

import au.com.dius.store.cache.ShoppingCart;
import au.com.dius.store.pricing.PricingRulesEngine;
import au.com.dius.store.promotion.ProductPromotion;
import au.com.dius.store.promotion.ProductPromotionFactory;

/*
 * This class is injected into the Checkout class at instantiation.
 * 
 * It handles the calculation of the final cost of a shopping cart.
 */
public class PricingRulesEngineImpl implements PricingRulesEngine {
	
	/*
	 * This implementation passes on the responsibility of calculating the cost
	 * to a Promotion instance of each Product type. 
	 */
	@Override
	public double calculateTotal() {
		double totalCost = 0;
		
		for (Entry<String, Integer> entry: ShoppingCart.INSTANCE.getCart()) {
			//delegate to Product Promotion implementation to calculate cost
			ProductPromotion promo = ProductPromotionFactory.getInstance(entry.getKey());
			totalCost += promo.applyPromotion(entry.getKey(), entry.getValue());
		}
		return totalCost;
	}
}
