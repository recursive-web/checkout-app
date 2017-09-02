package au.com.dius.store.promotion.impl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.dius.store.cache.Products;
import au.com.dius.store.cache.ShoppingCart;
import au.com.dius.store.product.MacBookPro;
import au.com.dius.store.product.VGAAdapter;
import au.com.dius.store.promotion.ProductPromotion;

public class MacBookPromotionImplTest {

	ProductPromotion sut;

	@Before
	public void setUp() throws Exception {
		sut = new MacBookPromotionImpl();
	}

	@After
	public void tearDown() throws Exception {
		Products.INSTANCE.truncate();
		ShoppingCart.INSTANCE.truncate();
	}

	@Test
	public void testApplyPromotion_for_one_mac_wihtout_vga() {
		Products.INSTANCE.add(new MacBookPro("mbp", "Mac", 1399.99));
		Products.INSTANCE.add(new VGAAdapter("vga", "VGA", 30));
		
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("mbp"));

		double cost = sut.applyPromotion(Products.INSTANCE.get("mbp").getSku(),
				ShoppingCart.INSTANCE.getQuantity("mbp"));
		assertEquals(1399.99, cost, 0.0);
		
		assertEquals(2, ShoppingCart.INSTANCE.getProductCount());
		assertEquals(1, ShoppingCart.INSTANCE.getQuantity("vga"));
		
	}
	
	@Test
	public void testApplyPromotion_for_three_mac_wihtout_vga() {
		Products.INSTANCE.add(new MacBookPro("mbp", "Mac", 1399.99));
		Products.INSTANCE.add(new VGAAdapter("vga", "VGA", 30));
		
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("mbp"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("mbp"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("mbp"));

		double cost = sut.applyPromotion(Products.INSTANCE.get("mbp").getSku(),
				ShoppingCart.INSTANCE.getQuantity("mbp"));
		assertEquals(4199.97, cost, 0.00001);
		
		assertEquals(2, ShoppingCart.INSTANCE.getProductCount());
		assertEquals(3, ShoppingCart.INSTANCE.getQuantity("vga"));
		
	}
	
	@Test
	public void testApplyPromotion_for_one_mac_and_one_vga() {
		Products.INSTANCE.add(new MacBookPro("mbp", "Mac", 1399.99));
		Products.INSTANCE.add(new VGAAdapter("vga", "VGA", 30));
		
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("mbp"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("vga"));
		

		double cost = sut.applyPromotion(Products.INSTANCE.get("mbp").getSku(),
				ShoppingCart.INSTANCE.getQuantity("mbp"));
		assertEquals(1369.99, cost, 0.00001);
		
		assertEquals(2, ShoppingCart.INSTANCE.getProductCount());
		assertEquals(1, ShoppingCart.INSTANCE.getQuantity("vga"));
		
	}
	
	@Test
	public void testApplyPromotion_for_two_mac_and_one_vga() {
		Products.INSTANCE.add(new MacBookPro("mbp", "Mac", 1399.99));
		Products.INSTANCE.add(new VGAAdapter("vga", "VGA", 30));
		
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("mbp"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("mbp"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("vga"));
		

		double cost = sut.applyPromotion(Products.INSTANCE.get("mbp").getSku(),
				ShoppingCart.INSTANCE.getQuantity("mbp"));
		assertEquals(1399.99 + 1399.99 - 30.0, cost, 0.00001);
		
		assertEquals(2, ShoppingCart.INSTANCE.getProductCount());
		assertEquals(2, ShoppingCart.INSTANCE.getQuantity("vga"));
		
	}
	
	@Test
	public void testApplyPromotion_for_five_mac_and_two_vga() {
		Products.INSTANCE.add(new MacBookPro("mbp", "Mac", 1399.99));
		Products.INSTANCE.add(new VGAAdapter("vga", "VGA", 30));
		
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("mbp"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("mbp"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("mbp"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("mbp"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("mbp"));
		
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("vga"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("vga"));
		

		double cost = sut.applyPromotion(Products.INSTANCE.get("mbp").getSku(),
				ShoppingCart.INSTANCE.getQuantity("mbp"));
		assertEquals((1399.99 * 5) - (30.0 * 2), cost, 0.00001);
		
		assertEquals(2, ShoppingCart.INSTANCE.getProductCount());
		assertEquals(5, ShoppingCart.INSTANCE.getQuantity("vga"));
		
	}	
	
	@Test
	public void testApplyPromotion_for_one_mac_and_two_vga() {
		Products.INSTANCE.add(new MacBookPro("mbp", "Mac", 1399.99));
		Products.INSTANCE.add(new VGAAdapter("vga", "VGA", 30));
		
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("mbp"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("vga"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("vga"));
		

		double cost = sut.applyPromotion(Products.INSTANCE.get("mbp").getSku(),
				ShoppingCart.INSTANCE.getQuantity("mbp"));
		/*
		 * VGA cost will be calculated separately. 
		 * therefore no need to add this value here.
		 * Just need get the refund
		 */
		assertEquals(1399.99 - 30.0, cost, 0.00001);
		
		assertEquals(2, ShoppingCart.INSTANCE.getProductCount());
		assertEquals(2, ShoppingCart.INSTANCE.getQuantity("vga"));
		
	}
	
	@Test
	public void testApplyPromotion_for_two_mac_and_four_vga() {
		Products.INSTANCE.add(new MacBookPro("mbp", "Mac", 1399.99));
		Products.INSTANCE.add(new VGAAdapter("vga", "VGA", 30));
		
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("mbp"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("mbp"));
		
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("vga"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("vga"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("vga"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("vga"));
		

		double cost = sut.applyPromotion(Products.INSTANCE.get("mbp").getSku(),
				ShoppingCart.INSTANCE.getQuantity("mbp"));
		/*
		 * VGA cost will be calculated separately. 
		 * therefore no need to add this value here.
		 * Just need get the refund
		 */
		assertEquals(1399.99*2 - 30.0*2, cost, 0.00001);
		
		assertEquals(2, ShoppingCart.INSTANCE.getProductCount());
		assertEquals(4, ShoppingCart.INSTANCE.getQuantity("vga"));
		
	}

}
