package au.com.dius.store.pricing.impl;

import java.util.Map.Entry;

import au.com.dius.store.cache.ShoppingCart;
import au.com.dius.store.pricing.PricingRulesEngine;
import au.com.dius.store.promotion.ProductPromotion;
import au.com.dius.store.promotion.ProductPromotionFactory;

public class PricingRulesEngineImpl implements PricingRulesEngine {

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
