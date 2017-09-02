package au.com.dius.store.promotion.impl;

import au.com.dius.store.cache.Products;
import au.com.dius.store.cache.ShoppingCart;
import au.com.dius.store.promotion.ProductPromotion;

public class MacBookPromotionImpl implements ProductPromotion {
	
	private static final String VGA = "vga"; 
	
	/*
	 * Current Promo:
	 * bundle in a free VGA adapter free of charge with every MacBook Pro sold
	 */
	@Override
	public double applyPromotion(String sku, int quantity) {
		double vgaRefundCost = 0;
		
		//check VGAs
		if (ShoppingCart.INSTANCE.containsProduct(VGA)){
			int vgaQuantity = ShoppingCart.INSTANCE.getQuantity(VGA);
			if(vgaQuantity == quantity) {
				//cost of VGA needs to be refunded.
				vgaRefundCost = Products.INSTANCE.get(VGA).getUnitPrice() * quantity;
			} 
			
			if(vgaQuantity < quantity) {
				//add complimentary VGAs
				ShoppingCart.INSTANCE.updateProductQuantity(VGA, quantity);
				//refund the VGAs that are already in the cart
				vgaRefundCost = Products.INSTANCE.get(VGA).getUnitPrice() * vgaQuantity;
			}
			
			if (vgaQuantity > quantity) {
				//refund the number of VGAs equivalent to the number of macs
				vgaRefundCost = Products.INSTANCE.get(VGA).getUnitPrice() * quantity;
			}
		} else { //Add  complimentary VGAs
			ShoppingCart.INSTANCE.add(Products.INSTANCE.get(VGA));
			ShoppingCart.INSTANCE.updateProductQuantity(VGA, quantity);
		}
		
		return (Products.INSTANCE.get(sku).getUnitPrice()*quantity) - vgaRefundCost;
	}
}
