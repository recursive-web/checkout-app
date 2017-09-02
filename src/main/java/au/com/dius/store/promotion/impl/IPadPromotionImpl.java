package au.com.dius.store.promotion.impl;

import au.com.dius.store.cache.Products;
import au.com.dius.store.promotion.ProductPromotion;

public class IPadPromotionImpl implements ProductPromotion {
	
	/*
	 * Current Promo:
	 * the brand new Super iPad will have a bulk discounted applied, 
	 * where the price will drop to $499.99 each, 
	 * if someone buys more than 4
	 */
	@Override
	public double applyPromotion(String sku, int quantity) {
		double unitPrice = Products.INSTANCE.get(sku).getUnitPrice(); 
		if (quantity > 4) {
			unitPrice = 499.99;
		}
		return  unitPrice * quantity;		
	}

}
