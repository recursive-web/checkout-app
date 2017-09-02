package au.com.dius.store.promotion.impl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.dius.store.cache.Products;
import au.com.dius.store.cache.ShoppingCart;
import au.com.dius.store.product.VGAAdapter;
import au.com.dius.store.promotion.ProductPromotion;

public class AppleTVPromotionImplTest {

	ProductPromotion sut;
	
	@Before
	public void setUp() throws Exception {
		sut = new AppleTVPromotionImpl();
	}

	@After
	public void tearDown() throws Exception {
		Products.INSTANCE.truncate();
		ShoppingCart.INSTANCE.truncate();
	}

	@Test
	public void testApplyPromotion_for_one_appletv() {
		Products.INSTANCE.add(new VGAAdapter("atv", "Apple", 109.50));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		
		double cost = sut.applyPromotion(Products.INSTANCE.get("atv").getSku(), ShoppingCart.INSTANCE.getQuantity("atv"));
		assertEquals(109.5, cost, 0.0);
	}
	
	@Test
	public void testApplyPromotion_for_three_appletv() {
		Products.INSTANCE.add(new VGAAdapter("atv", "Apple TV", 109.5));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		
		
		double cost = sut.applyPromotion(Products.INSTANCE.get("atv").getSku(), ShoppingCart.INSTANCE.getQuantity("atv"));
		assertEquals(219.0, cost, 0.0);
	}
	
	@Test
	public void testApplyPromotion_for_five_appletv() {
		Products.INSTANCE.add(new VGAAdapter("atv", "Apple TV", 109.5));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		
		
		double cost = sut.applyPromotion(Products.INSTANCE.get("atv").getSku(), ShoppingCart.INSTANCE.getQuantity("atv"));
		assertEquals(438.0, cost, 0.0);
	}
	
	@Test
	public void testApplyPromotion_for_six_appletv() {
		Products.INSTANCE.add(new VGAAdapter("atv", "Apple TV", 109.5));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		
		
		double cost = sut.applyPromotion(Products.INSTANCE.get("atv").getSku(), ShoppingCart.INSTANCE.getQuantity("atv"));
		assertEquals(438.0, cost, 0.0);
	}
	
	@Test
	public void testApplyPromotion_for_seven_appletv() {
		Products.INSTANCE.add(new VGAAdapter("atv", "Apple TV", 109.5));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		
		
		double cost = sut.applyPromotion(Products.INSTANCE.get("atv").getSku(), ShoppingCart.INSTANCE.getQuantity("atv"));
		assertEquals(547.50, cost, 0.0);
	}

}
