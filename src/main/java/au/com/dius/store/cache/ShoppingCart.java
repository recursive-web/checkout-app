package au.com.dius.store.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import au.com.dius.store.product.Item;
/*
 * Single object to maintain a shopping cart for a checkout session
 */
public enum ShoppingCart {
	INSTANCE;

	private final Map<String, Integer> cart = new HashMap<>();

	public void add(Item item) {
		if(cart.containsKey(item.getSku())) {
			int newVal = cart.get(item.getSku()) + 1;
			cart.put(item.getSku(), newVal);
			return;
		}
		cart.put(item.getSku(), 1);
		return;

	}

	public int getQuantity(String sku) {
		return cart.get(sku);
	}

	public void truncate() {
		cart.clear();
	}

	public int getProductCount() {
		return cart.size();
	}
	
	public Set<Entry<String, Integer>> getCart() {
		return cart.entrySet();
	}
	
	public boolean containsProduct(String sku) {
		return cart.containsKey(sku);
	}
	
	public void updateProductQuantity(String sku, int quantity) {
		if(cart.containsKey(sku)) {
			cart.put(sku, quantity);
			return;
		}
		throw new IllegalArgumentException("Product with SKU:" + sku + " does not Exist");
		
	}
}
