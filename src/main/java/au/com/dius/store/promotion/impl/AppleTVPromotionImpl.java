package au.com.dius.store.promotion.impl;

import au.com.dius.store.cache.Products;
import au.com.dius.store.promotion.ProductPromotion;

public class AppleTVPromotionImpl implements ProductPromotion {
	/*
	 * Current promo:
	 * 3 for 2 deal on Apple TVs. 
	 * For example, if you buy 3 Apple TVs, you will pay the price of 2 only
	 */
	@Override
	public double applyPromotion(String sku, int quantity) {
		int promoUnits = (quantity / 3) * 2;
		int additionalUnits = quantity % 3;
		
		int updatedUnits = promoUnits + additionalUnits; 
		
		return Products.INSTANCE.get(sku).getUnitPrice() * updatedUnits;
	}

}
