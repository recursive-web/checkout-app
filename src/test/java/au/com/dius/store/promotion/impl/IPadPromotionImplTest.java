package au.com.dius.store.promotion.impl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.dius.store.cache.Products;
import au.com.dius.store.cache.ShoppingCart;
import au.com.dius.store.product.IPad;
import au.com.dius.store.promotion.ProductPromotion;

public class IPadPromotionImplTest {

	ProductPromotion sut;

	@Before
	public void setUp() throws Exception {
		sut = new IPadPromotionImpl();
	}

	@After
	public void tearDown() throws Exception {
		Products.INSTANCE.truncate();
		ShoppingCart.INSTANCE.truncate();
	}

	@Test
	public void testApplyPromotion_for_one_ipad() {
		Products.INSTANCE.add(new IPad("ipd", "IPad", 549.99));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));

		double cost = sut.applyPromotion(Products.INSTANCE.get("ipd").getSku(),
				ShoppingCart.INSTANCE.getQuantity("ipd"));
		assertEquals(549.99, cost, 0.0);
	}

	@Test
	public void testApplyPromotion_for_three_ipad() {
		Products.INSTANCE.add(new IPad("ipd", "Ipad", 549.99));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));

		double cost = sut.applyPromotion(Products.INSTANCE.get("ipd").getSku(),
				ShoppingCart.INSTANCE.getQuantity("ipd"));
		assertEquals(1649.97, cost, 0.0);
	}

	@Test
	public void testApplyPromotion_for_five_ipad() {
		Products.INSTANCE.add(new IPad("ipd", "IPad", 549.99));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));

		double cost = sut.applyPromotion(Products.INSTANCE.get("ipd").getSku(),
				ShoppingCart.INSTANCE.getQuantity("ipd"));
		assertEquals(2499.95, cost, 0.0);
	}

	@Test
	public void testApplyPromotion_for_seven_ipad() {
		Products.INSTANCE.add(new IPad("ipd", "IPad", 549.99));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));

		double cost = sut.applyPromotion(Products.INSTANCE.get("ipd").getSku(),
				ShoppingCart.INSTANCE.getQuantity("ipd"));
		assertEquals(3499.93, cost, 0.000001);
	}

}
