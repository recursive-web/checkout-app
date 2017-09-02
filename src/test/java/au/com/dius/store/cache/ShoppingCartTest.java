package au.com.dius.store.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map.Entry;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.dius.store.product.IPad;
import au.com.dius.store.product.MacBookPro;

public class ShoppingCartTest {

	@Test(expected=IllegalArgumentException.class)
	public void testUpdateQuantity_invalid_sku() {
		ShoppingCart.INSTANCE.add(new IPad("sku", "name", 1.99));
		
		ShoppingCart.INSTANCE.updateProductQuantity("foo", 10);
	}
	
	@Test
	public void testShoppingCart() {
		assertEquals(0,  ShoppingCart.INSTANCE.getProductCount());
		ShoppingCart.INSTANCE.add(new IPad("sku", "name", 1.99));
		assertEquals(1,  ShoppingCart.INSTANCE.getProductCount());
		assertEquals(1,  ShoppingCart.INSTANCE.getQuantity("sku"));
		
		ShoppingCart.INSTANCE.add(new IPad("sku", "name", 1.99));
		assertEquals(1,  ShoppingCart.INSTANCE.getProductCount());
		assertEquals(2,  ShoppingCart.INSTANCE.getQuantity("sku"));
		
		ShoppingCart.INSTANCE.add(new MacBookPro("mac", "name", 0.99));
		assertEquals(2,  ShoppingCart.INSTANCE.getProductCount());
		assertEquals(2,  ShoppingCart.INSTANCE.getQuantity("sku"));
		assertEquals(1,  ShoppingCart.INSTANCE.getQuantity("mac"));
		
		assertTrue(ShoppingCart.INSTANCE.containsProduct("mac"));
		assertFalse(ShoppingCart.INSTANCE.containsProduct("ibm"));
		
		//update quantity
		ShoppingCart.INSTANCE.updateProductQuantity("mac", 5);
		assertEquals(5,  ShoppingCart.INSTANCE.getQuantity("mac"));
		
		Set<Entry<String, Integer>> cart = ShoppingCart.INSTANCE.getCart();
		assertEquals(2,  cart.size());		
		
		
		ShoppingCart.INSTANCE.truncate();
		assertEquals(0,  ShoppingCart.INSTANCE.getProductCount());
		cart = ShoppingCart.INSTANCE.getCart();
		assertTrue(cart.isEmpty());
	}
	
	@Before
	public void setup() {
		ShoppingCart.INSTANCE.truncate();
	}
	
	@After
	public void tearDown() {
		ShoppingCart.INSTANCE.truncate();
	}

}
