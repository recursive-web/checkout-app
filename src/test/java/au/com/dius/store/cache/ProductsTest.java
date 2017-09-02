package au.com.dius.store.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.dius.store.product.IPad;
import au.com.dius.store.product.MacBookPro;

public class ProductsTest {

	@Before
	public void setup() {
		Products.INSTANCE.truncate();
	}
	
	@Test
	public void testProducts() {
		assertEquals(0, Products.INSTANCE.size());
		Products.INSTANCE.add(new IPad("foo", "Bar", 100));
		assertEquals(1, Products.INSTANCE.size());
		Products.INSTANCE.add(new MacBookPro("mac", "Mac", 333));
		assertEquals(2, Products.INSTANCE.size());
		
		assertNull(Products.INSTANCE.get("mobile"));
		Object o = Products.INSTANCE.get("mac");
		assertNotNull(o);
		assertTrue(o instanceof MacBookPro);
		assertEquals("Mac", ((MacBookPro)o).getName());
		
		Products.INSTANCE.truncate();
		assertEquals(0, Products.INSTANCE.size());
		
	}

		
	
	@After
	public void tearDown() {
		Products.INSTANCE.truncate();
	}

}
