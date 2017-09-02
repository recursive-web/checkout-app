package au.com.dius.store.promotion;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import au.com.dius.store.promotion.impl.AppleTVPromotionImpl;
import au.com.dius.store.promotion.impl.IPadPromotionImpl;
import au.com.dius.store.promotion.impl.MacBookPromotionImpl;
import au.com.dius.store.promotion.impl.VGAPromotionImpl;

public class ProductionPromotionFactoryTest {

	@Test
	public void testGetInstance_valid() {
		assertTrue(ProductPromotionFactory.getInstance("vga") instanceof VGAPromotionImpl);
		assertTrue(ProductPromotionFactory.getInstance("ipd") instanceof IPadPromotionImpl);
		assertTrue(ProductPromotionFactory.getInstance("mbp") instanceof MacBookPromotionImpl);
		assertTrue(ProductPromotionFactory.getInstance("atv") instanceof AppleTVPromotionImpl);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetInstance_error() {
		ProductPromotionFactory.getInstance("foo");
	}

}
