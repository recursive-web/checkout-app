package au.com.dius.store.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MacBookProTest {

    Item sut;
	
	@Before
	public void setup() {
		sut = new MacBookPro("mbp", "MBP", 500.00);
	}
	
	@Test
	public void testGet() {
		Object o = sut.get();
		assertNotNull(o);
		assertTrue(o instanceof MacBookPro);
	}

	@Test
	public void testMacBookPro() {
		assertEquals("mbp", sut.getSku());
		assertEquals("MBP", sut.getName());
		assertEquals(500.0, sut.getUnitPrice(), 0.0);
	}

	@Test
	public void testToString() {
		assertEquals("IPad [sku=mbp, name=MBP, unitPrice=500.0]", sut.toString());
	}

}
