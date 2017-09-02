package au.com.dius.store.promotion.impl;

import au.com.dius.store.cache.Products;
import au.com.dius.store.promotion.ProductPromotion;

public class VGAPromotionImpl implements ProductPromotion {
	
	/*
	 * Currently No promo for VGA
	 */
	@Override
	public double applyPromotion(String sku, int quantity) {
		double unitPrice = Products.INSTANCE.get(sku).getUnitPrice();
		
		return unitPrice * quantity;
	}

}
