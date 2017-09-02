package au.com.dius.store.promotion;

import au.com.dius.store.promotion.impl.AppleTVPromotionImpl;
import au.com.dius.store.promotion.impl.IPadPromotionImpl;
import au.com.dius.store.promotion.impl.MacBookPromotionImpl;
import au.com.dius.store.promotion.impl.VGAPromotionImpl;

/*
 * Factory class to return an instance of a Product Promotion Type
 */
public class ProductPromotionFactory {
	
	public static ProductPromotion getInstance(String sku) {
		switch(sku) {
			case "vga": return new VGAPromotionImpl();
			case "atv": return new AppleTVPromotionImpl();
			case "ipd": return new IPadPromotionImpl();
			case "mbp": return new MacBookPromotionImpl();
			
			default: throw new IllegalArgumentException("invalid SKU:" + sku);
		}
	}

}
