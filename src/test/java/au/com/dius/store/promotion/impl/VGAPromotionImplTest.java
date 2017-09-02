package au.com.dius.store.promotion.impl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.dius.store.cache.Products;
import au.com.dius.store.cache.ShoppingCart;
import au.com.dius.store.product.VGAAdapter;
import au.com.dius.store.promotion.ProductPromotion;

public class VGAPromotionImplTest {

	ProductPromotion sut;
	
	@Before
	public void setUp() throws Exception {
		sut = new VGAPromotionImpl();
	}

	@After
	public void tearDown() throws Exception {
		Products.INSTANCE.truncate();
		ShoppingCart.INSTANCE.truncate();
	}

	@Test
	public void testApplyPromotion_for_one_vga() {
		Products.INSTANCE.add(new VGAAdapter("vga", "VGA", 100));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("vga"));
		
		double cost = sut.applyPromotion(Products.INSTANCE.get("vga").getSku(), ShoppingCart.INSTANCE.getQuantity("vga"));
		assertEquals(100.0, cost, 0.0);
	}
	
	@Test
	public void testApplyPromotion_for_five_vga() {
		Products.INSTANCE.add(new VGAAdapter("vga", "VGA", 100));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("vga"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("vga"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("vga"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("vga"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("vga"));
		
		double cost = sut.applyPromotion(Products.INSTANCE.get("vga").getSku(), ShoppingCart.INSTANCE.getQuantity("vga"));
		assertEquals(500.0, cost, 0.0);
	}

}
