package au.com.dius.store.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class IPadTest {

	Item sut;
	
	@Before
	public void setup() {
		sut = new IPad("ipd", "Super iPad", 549.99);
	}
	
	@Test
	public void testGet() {
		Object o = sut.get();
		assertNotNull(o);
		assertTrue(o instanceof IPad);
	}

	@Test
	public void testIPad() {
		assertEquals("ipd", sut.getSku());
		assertEquals("Super iPad", sut.getName());
		assertEquals(549.99, sut.getUnitPrice(), 0.0);
	}

	@Test
	public void testToString() {
		assertEquals("IPad [sku=ipd, name=Super iPad, unitPrice=549.99]", sut.toString());
	}

}
