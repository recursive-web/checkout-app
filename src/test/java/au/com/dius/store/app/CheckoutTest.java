package au.com.dius.store.app;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import au.com.dius.store.cache.Products;
import au.com.dius.store.cache.ShoppingCart;
import au.com.dius.store.pricing.PricingRulesEngine;
import au.com.dius.store.pricing.impl.PricingRulesEngineImpl;
import au.com.dius.store.product.AppleTV;
import au.com.dius.store.product.IPad;
import au.com.dius.store.product.Item;
import au.com.dius.store.product.MacBookPro;
import au.com.dius.store.product.VGAAdapter;

public class CheckoutTest {

	Checkout sut;
	
	PricingRulesEngine engine;
		
	@Before
	public void setup() {
		engine = new PricingRulesEngineImpl();
		sut = new Checkout(engine);
		Products.INSTANCE.truncate();
		ShoppingCart.INSTANCE.truncate();
	}
	
	@Test
	public void testInit() {
		sut.init();		
		assertEquals(0, ShoppingCart.INSTANCE.getProductCount());
	}
	
	@Test
	public void testScan() {
		//shopping cart should be empty
		assertEquals(0, ShoppingCart.INSTANCE.getProductCount());
		sut.scan(new VGAAdapter("vga", "VGA", 1));
		//shopping car should have a product added
		assertEquals(1, ShoppingCart.INSTANCE.getProductCount());
		//Shopping cart should have just one VGA
		assertEquals(1, ShoppingCart.INSTANCE.getQuantity("vga"));
		//Another VGA adapter
		sut.scan(new VGAAdapter("vga", "VGA", 1));
		//shopping car should still have only one product added
		assertEquals(1, ShoppingCart.INSTANCE.getProductCount());
		//but the quantity should have increased
		assertEquals(2, ShoppingCart.INSTANCE.getQuantity("vga"));
		//Next is an Apple TV
		sut.scan(new AppleTV("atv", "ATV", 3));
		//shopping cart should have 2 products added
		assertEquals(2, ShoppingCart.INSTANCE.getProductCount());
		//Shopping cart should have just one Apple TV
		assertEquals(1, ShoppingCart.INSTANCE.getQuantity("atv"));
	}
	
	@Test
	public void testCost_atv_atv_atv_vga(){
		loadProductData();
		sut.init();
		
		Item item1 = Products.INSTANCE.get("atv");
		Item item2 = Products.INSTANCE.get("atv");
		Item item3 = Products.INSTANCE.get("atv");
		Item item4 = Products.INSTANCE.get("vga");
		
		sut.scan(item1);
		sut.scan(item2);
		sut.scan(item3);
		sut.scan(item4);
		sut.total();
		assertEquals(249.0, sut.getTotalCost(),0.0);
	}
	
	@Test
	public void testCost_atv_ipd_ipd_atv_ipd_ipd_ipd(){
		loadProductData();
		sut.init();
		
		Item item1 = Products.INSTANCE.get("atv");
		Item item2 = Products.INSTANCE.get("ipd");
		Item item3 = Products.INSTANCE.get("ipd");
		Item item4 = Products.INSTANCE.get("atv");
		Item item5 = Products.INSTANCE.get("ipd");
		Item item6 = Products.INSTANCE.get("ipd");
		Item item7 = Products.INSTANCE.get("ipd");
		
		sut.scan(item1);
		sut.scan(item2);
		sut.scan(item3);
		sut.scan(item4);
		sut.scan(item5);
		sut.scan(item6);
		sut.scan(item7);
		sut.total();
		assertEquals(2718.95, sut.getTotalCost(),0.0);
	}
	
	@Test
	public void testCost_mbp_vga_ipd(){
		loadProductData();
		sut.init();
		
		Item item1 = Products.INSTANCE.get("mbp");
		Item item2 = Products.INSTANCE.get("vga");
		Item item3 = Products.INSTANCE.get("ipd");
				
		sut.scan(item1);
		sut.scan(item2);
		sut.scan(item3);
		sut.total();
		assertEquals(1949.98, sut.getTotalCost(),0.0);
	}
	
	@Test
	public void testCost_vga_vga_vga_vga_vga_vga_atv_atv_atv_atv_atv_ipd_ipd_ipd_ipd_ipd_ipd_mbp_mbp_mbp(){
		loadProductData();
		sut.init();
		
		Item item1 = Products.INSTANCE.get("vga");
		Item item2 = Products.INSTANCE.get("vga");
		Item item3 = Products.INSTANCE.get("vga");
		Item item4 = Products.INSTANCE.get("vga");
		Item item5 = Products.INSTANCE.get("vga");
		Item item6 = Products.INSTANCE.get("vga");
		Item item7 = Products.INSTANCE.get("atv");
		Item item8 = Products.INSTANCE.get("atv");
		Item item9 = Products.INSTANCE.get("atv");
		Item item10 = Products.INSTANCE.get("atv");
		Item item11 = Products.INSTANCE.get("atv");
		Item item12 = Products.INSTANCE.get("ipd");
		Item item13 = Products.INSTANCE.get("ipd");
		Item item14 = Products.INSTANCE.get("ipd");
		Item item15 = Products.INSTANCE.get("ipd");
		Item item16 = Products.INSTANCE.get("ipd");
		Item item17 = Products.INSTANCE.get("ipd");
		Item item18 = Products.INSTANCE.get("mbp");
		Item item19 = Products.INSTANCE.get("mbp");
		Item item20 = Products.INSTANCE.get("mbp");
		
				
		sut.scan(item12);
		sut.scan(item1);
		sut.scan(item18);
		sut.scan(item7);
		sut.scan(item2);
		sut.scan(item3);
		sut.scan(item13);
		sut.scan(item20);
		sut.scan(item14);
		sut.scan(item8);
		sut.scan(item9);
		sut.scan(item4);
		sut.scan(item15);
		sut.scan(item10);
		sut.scan(item5);
		sut.scan(item19);
		sut.scan(item16);
		sut.scan(item17);
		sut.scan(item6);
		sut.scan(item11);
		sut.total();
		assertEquals(7727.91, sut.getTotalCost(),0.0);
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
