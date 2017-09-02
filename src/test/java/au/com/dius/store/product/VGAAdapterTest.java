package au.com.dius.store.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class VGAAdapterTest {

Item sut;
	
	@Before
	public void setup() {
		sut = new IPad("vga", "VGA adapter", 30.0);
	}
	
	@Test
	public void testGet() {
		Object o = sut.get();
		assertNotNull(o);
		assertTrue(o instanceof IPad);
	}

	@Test
	public void testIPad() {
		assertEquals("vga", sut.getSku());
		assertEquals("VGA adapter", sut.getName());
		assertEquals(30, sut.getUnitPrice(), 0.0);
	}

	@Test
	public void testToString() {
		assertEquals("IPad [sku=vga, name=VGA adapter, unitPrice=30.0]", sut.toString());
	}

}
