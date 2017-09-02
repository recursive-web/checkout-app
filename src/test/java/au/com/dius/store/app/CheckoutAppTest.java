package au.com.dius.store.app;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.dius.store.cache.Products;
import au.com.dius.store.product.AppleTV;
import au.com.dius.store.product.IPad;
import au.com.dius.store.product.Item;
import au.com.dius.store.product.MacBookPro;
import au.com.dius.store.product.VGAAdapter;

public class CheckoutAppTest {

	CheckoutApp sut;
	@Before
	public void setUp() throws Exception {
		sut = new CheckoutApp();
	}

	@After
	public void tearDown() throws Exception {
		Products.INSTANCE.truncate();
	}

	@Test
	public void testValidateSKU_valid() {
		loadProductData();
		String s = "atv,ipd,atv,vga,mbp,atv";
		List<String> list = sut.getSkuList(s);
		assertEquals(6, list.size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testValidateSKU_invalid() {
		loadProductData();
		String s = "atv,ipd,atv,vga,foo,atv";
		sut.getSkuList(s);		
	}

	@Test
	public void testLoadProductData() {
		assertEquals(0,  Products.INSTANCE.size());
		sut.loadProductData();
		assertEquals(4,  Products.INSTANCE.size());
	}
	
	void loadProductData() {
		Item ipad = new IPad("ipd", "Super iPad", 549.99);
		Item mac = new MacBookPro("mbp", "MacBook Pro", 1399.99);
		Item atv = new AppleTV("atv", "Apple TV", 109.5);
		Item vga = new VGAAdapter("vga", "VGA adapter", 30.0);
		
		Products.INSTANCE.add(ipad);
		Products.INSTANCE.add(mac);
		Products.INSTANCE.add(atv);
		Products.INSTANCE.add(vga);
		
		return;
	}

}
