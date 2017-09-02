package au.com.dius.store.promotion;

/*
 * Interface to apply promotions to products
 */
public interface ProductPromotion {
	/*
	 * Takes the number of items as input as most promotions are based on the items
	 */
	double applyPromotion(String sku, int quantity);
}
