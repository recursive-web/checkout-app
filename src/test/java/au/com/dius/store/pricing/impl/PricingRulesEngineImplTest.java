package au.com.dius.store.pricing.impl;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.dius.store.cache.Products;
import au.com.dius.store.cache.ShoppingCart;
import au.com.dius.store.pricing.PricingRulesEngine;
import au.com.dius.store.product.AppleTV;
import au.com.dius.store.product.IPad;
import au.com.dius.store.product.MacBookPro;
import au.com.dius.store.product.VGAAdapter;

public class PricingRulesEngineImplTest {

	PricingRulesEngine sut;
	
	@Before
	public void setup(){
		sut = new PricingRulesEngineImpl();		
	}
	
	@Test
	public void testCalculateTotal_atv_atv_atv_vga() {
		Products.INSTANCE.add(new VGAAdapter("vga", "VGA", 30.0));
		Products.INSTANCE.add(new AppleTV("atv", "APL", 109.50));
		
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("vga"));
		
		double cost = sut.calculateTotal();
		assertEquals(249.0, cost, 0.0);
	}
	
	@Test
	public void testCalculateTotal_atv_ipd_ipd_atv_ipd_ipd_ipd() {
		Products.INSTANCE.add(new AppleTV("atv", "APL", 109.50));
		Products.INSTANCE.add(new IPad("ipd", "IPad", 549.99));
		
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("atv"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));
				
		double cost = sut.calculateTotal();
		assertEquals(2718.95, cost, 0.0);
	}
	
	@Test
	public void testCalculateTotal_mbp_vga_ipd() {
		Products.INSTANCE.add(new MacBookPro("mbp", "Mac", 1399.99));
		Products.INSTANCE.add(new VGAAdapter("vga", "VGA", 30.0));
		Products.INSTANCE.add(new IPad("ipd", "IPad", 549.99));
		
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("mbp"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("vga"));
		ShoppingCart.INSTANCE.add(Products.INSTANCE.get("ipd"));
		
		
		double cost = sut.calculateTotal();
		assertEquals(1949.98, cost, 0.0);		
	}
	
		
	
	@After
	public void tearDown() {
		Products.INSTANCE.truncate();
		ShoppingCart.INSTANCE.truncate();
	}

}
