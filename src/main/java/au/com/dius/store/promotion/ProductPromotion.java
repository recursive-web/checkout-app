package au.com.dius.store.promotion;

/*
 * Interface to apply promotions to products
 */
public interface ProductPromotion {
	/*
	 * Takes the sku and number of items as input.
	 */
	double applyPromotion(String sku, int quantity);
}
