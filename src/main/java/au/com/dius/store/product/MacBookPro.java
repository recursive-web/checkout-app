package au.com.dius.store.product;

public class MacBookPro extends Item {

	public MacBookPro(String sku, String name, double unitPrice){
		super(sku, name, unitPrice);
	  }
	
	@Override
	public Item get() {
		return new MacBookPro(sku, name, unitPrice);
	}

}
