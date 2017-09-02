package au.com.dius.store.product;

public class AppleTV extends Item {

	public AppleTV(String sku, String name, double unitPrice) {
		super(sku, name, unitPrice);
	}

	public AppleTV get() {
		return new AppleTV(sku, name, unitPrice);
	}

}
