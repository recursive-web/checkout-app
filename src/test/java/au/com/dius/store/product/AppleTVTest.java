package au.com.dius.store.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AppleTVTest {

	Item sut;

	@Before
	public void setup() {
		sut = new AppleTV("atv", "Apple TV", 109.50);
	}

	@Test
	public void testGet() {
		Object o = sut.get();
		assertNotNull(o);
		assertTrue(o instanceof AppleTV);
	}

	@Test
	public void testIPad() {
		assertEquals("atv", sut.getSku());
		assertEquals("Apple TV", sut.getName());
		assertEquals(109.50, sut.getUnitPrice(), 0.0);
	}

	@Test
	public void testToString() {
		assertEquals("IPad [sku=atv, name=Apple TV, unitPrice=109.5]", sut.toString());
	}

}
