package au.com.dius.store.cache;

import java.util.HashMap;
import java.util.Map;

import au.com.dius.store.product.Item;
/*
 * Singleton cache object used to store Product Master data
 */
public enum Products {
	INSTANCE;
	
	private final Map<String, Item> products = new HashMap<>();
	
	public void add(Item item) {
		products.put(item.getSku(), item);
		
	}
	
	public Item get(String sku) {
		Item itm = products.get(sku);
		return itm  == null?null:itm.get();
	}
	
	public void truncate() {
		products.clear();
	}
	
	public int size() {
		return products.size();
	}
}
